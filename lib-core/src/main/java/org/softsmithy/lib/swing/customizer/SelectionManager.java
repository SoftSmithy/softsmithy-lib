/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

/*
 * SelectionManager.java
 *
 * Is this the right place? Should it be nested or in puce.swing?
 *
 * Created on 30. August 2002, 11:55
 */
package org.softsmithy.lib.swing.customizer;

import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.beans.*;
import java.util.*;
import org.softsmithy.lib.beans.*;
import org.softsmithy.lib.swing.*;
import org.softsmithy.lib.swing.event.*;

/**
 *
 * @author  puce
 */
public class SelectionManager implements PropertyChangeListener, CustomizerListener {

//  private static final Set RECTANGLE_PROPERTIES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[] {"x", "y", "width", "height"})));
    private final List<JCustomizer> selectedList = new ArrayList<JCustomizer>();
    private final Set<JCustomizer> selectedSet = new LinkedHashSet<JCustomizer>();
    private JCustomizer activeCustomizer = null;
    private Set<CustomizerSelectionListener> listeners = new HashSet<CustomizerSelectionListener>();
    /** Holds value of property fireingSelectionChanged. */
    private boolean fireingSelectionChanged = false;
    /** Holds value of property commonCustomizableProperties. */
    private Set<String> commonCustomizableProperties = new LinkedHashSet<String>();
//  private Set commonCustomizableBoundProperties = new HashSet();
    private int dxFactor = 0;
    private int dyFactor = 0;
    private int dwidthFactor = 0;
    private int dheightFactor = 0;

    /** Creates a new instance of SelectionManager */
    public SelectionManager() {
    }

    /**
     * Add the specified customizer to the selection.
     * If the specified point is in the region of a resize handle the state of the
     * customizer will be changed to the accordant resize state. Otherwise the
     * state will be changed to "move".
     * @param customizer the customizer to be added to the current selection
     * @param point If the specified point is in the region of a resize handle the
     * state of the customizer will be changed to the accordant resize state.
     * Otherwise the state will be changed to "move".
     */
    public void select(JCustomizer customizer, Point point) {
        selectOnly(customizer);
        customizer.getStateManager().setStateBound(point);
        fireSelectionChanged();
    }

    /**
     * Add the specified customizer to the selection.
     * The state of the customizer will be changed to "move".
     * @param customizer the customizer to be added to the current selection
     */
    public void select(JCustomizer customizer) {
        selectOnly(customizer);
        customizer.getStateManager().setStateMove();
        fireSelectionChanged();
    }

    private void selectOnly(JCustomizer customizer) {
        if (getActiveCustomizer() != null) {
            getActiveCustomizer().getStateManager().setStateSelected();
        }
        setSelected(customizer);
        //customizer.addCustomizerListener(this);
        if (!customizer.hasFocus()) {
            customizer.requestFocus();
        }
        revalidate();
    }

    /**
     * Clear the current selection and then add the specified customizer to the
     * selection. If the specified point is in the region of a resize handle the
     * state of the customizer will be changed to the accordant resize state.
     * Otherwise the state will be changed to "move".
     * @param customizer the customizer to be added to the current selection
     * @param point If the specified point is in the region of a resize handle the
     * state of the customizer will be changed to the accordant resize state.
     * Otherwise the state will be changed to "move".
     */
    public void singleSelect(JCustomizer customizer, Point point) {
        clearSelectionOnly();
        select(customizer, point);
    }

    /**
     * Clear the current selection and then add the specified customizer to the
     * selection. The state of the customizer will be changed to "move".
     * @param customizer the customizer to be added to the current selection
     */
    public void singleSelect(JCustomizer customizer) {
        clearSelectionOnly();
        select(customizer);
    }

    public void deselect(JCustomizer customizer) {
        JCustomizer last = null;
        JCustomizer secondLast = null;
        int size = selectedList.size();
        if (size > 0) {
            last = selectedList.get(size - 1); // == activeCustomizer
            if (size > 1) {
                secondLast = selectedList.get(size - 2); // == new activeCustomizer
            }
        }

        if (customizer.equals(last)) { // 'equals' or '==' ??
            if (secondLast != null) {
                secondLast.getStateManager().setStateMove();
            }
        }
        customizer.getStateManager().setStateNormal();
        setDeselected(customizer);
        revalidate();
        //customizer.removeCustomizerListener(this);
        fireSelectionChanged();
    }

    public boolean isSelected(JCustomizer customizer) {
        return selectedSet.contains(customizer);
    }

    private void setSelected(JCustomizer customizer) {
        ensureAppendedLast(customizer);
        selectedList.add(customizer);
        selectedSet.add(customizer);
    }

    private void setDeselected(JCustomizer customizer) {
        selectedList.remove(customizer);
        selectedSet.remove(customizer);
    }

    public void clearSelection() {
        clearSelectionOnly();
        fireSelectionChanged();
    }

    private void clearSelectionOnly() {
        for (Iterator<JCustomizer> i = selectedList.iterator(); i.hasNext();) {
            JCustomizer customizer = i.next();
            customizer.getStateManager().setStateNormal();
        //customizer.removeCustomizerListener(this);
        }
        selectedList.clear();
        selectedSet.clear();
        revalidate();
    }

    private void ensureAppendedLast(JCustomizer customizer) {
        if (isSelected(customizer)) {
            setDeselected(customizer);
        }
    }

    public void deleteSelection() {
        for (int i = 0; i < selectedList.size(); i++) {
            JCustomizer customizer = selectedList.get(i);
            Container parent = customizer.getParent();
            if (!parent.hasFocus()) {
                parent.requestFocus();
            }
            Rectangle bounds = customizer.getBounds();
            parent.remove(customizer); //TODO: unregister listeners eg. in StateCustomizer
            parent.repaint(bounds.x, bounds.y, bounds.width, bounds.height);
        }
        clearSelection(); // this causes an ConcurrentModificationException since it
    // tries to remove itself from the customizerListenerList
    }

    public void addCustomizerSelectionListener(CustomizerSelectionListener listener) {
        listeners.add(listener);
    }

    public void removeCustomizerSelectionListener(CustomizerSelectionListener listener) {
        listeners.remove(listener);
    }

    private void fireSelectionChanged() {
        fireingSelectionChanged = true;
        CustomizerSelectionEvent e = new CustomizerSelectionEvent(this, Collections.unmodifiableSet(selectedSet), getActiveCustomizer(), getCommonCustomizableProperties());
        for (Iterator<CustomizerSelectionListener> i = listeners.iterator(); i.hasNext();) {
            i.next().selectionChanged(e);
        }
        fireingSelectionChanged = false;
    }

    public JCustomizer[] getSelectedCustomizers() {
        return selectedList.toArray(new JCustomizer[selectedList.size()]);
    }

    //  private void resetActiveCustomizer(){
    //  }
    public JCustomizer getActiveCustomizer() {
        return activeCustomizer;
    }

    /** Getter for property fireingSelectionChanged.
     * @return Value of property fireingSelectionChanged.
     *
     */
    public boolean isFireingSelectionChanged() {
        return this.fireingSelectionChanged;
    }

    /** Getter for property commonCustomizableProperties.
     * @return Value of property commonCustomizableProperties.
     *
     */
    public Set<String> getCommonCustomizableProperties() {
        return this.commonCustomizableProperties;
    }

    private void revalidate() {
        if (getActiveCustomizer() != null) {
            getActiveCustomizer().removeCustomizerListener(this);
            for (Iterator<String> i = commonCustomizableProperties.iterator(); i.hasNext();) {
                getActiveCustomizer().removePropertyChangeListener(i.next(), this);
            }
        }
        this.activeCustomizer = selectedList.isEmpty() ? null : selectedList.get(selectedList.size() - 1);
        this.commonCustomizableProperties = JCustomizer.getCommonCustomizableProperties(selectedSet); // Collections.unmodifiableSet(JCustomizer.getCommonCustomizableProperties(selectedSet));
        //System.out.println("Properties: "+this.commonCustomizableProperties);
//    Set commonCustomizableRectangleProperties = new HashSet(RECTANGLE_PROPERTIES);
//    commonCustomizableRectangleProperties.retainAll(this.commonCustomizableProperties); // only the wanted rectangle properties
//    System.out.println("Rectangle Properties: "+commonCustomizableRectangleProperties);
//    this.commonCustomizableBoundProperties = new HashSet(this.commonCustomizableProperties);
//    this.commonCustomizableBoundProperties.removeAll(commonCustomizableRectangleProperties); // only the bound properties
//    System.out.println("Bound properties: "+this.commonCustomizableBoundProperties);
        resetFactors();
        if (getActiveCustomizer() != null) {
            getActiveCustomizer().addCustomizerListener(this);
            for (Iterator<String> i = this.commonCustomizableProperties.iterator(); i.hasNext();) {
                String property = i.next();
                //System.out.println("Property Change Listener added for: " + property);
                getActiveCustomizer().addPropertyChangeListener(property, this);
            }
        }
    }

    /** This method gets called when a bound property is changed.
     * @param evt A PropertyChangeEvent object describing the event source
     *   	and the property that has changed.
     *
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //System.out.println("Property Changed!!!!!! - " + evt.getPropertyName());
        for (int i = 0; i < selectedList.size() - 1; i++) {
            JCustomizer customizer = selectedList.get(i);
            try {
                BeanIntrospector.setPropertyValue(evt.getPropertyName(), evt.getNewValue(), customizer, null);
                customizer.repaint();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void customizerResetBoundsRel(CustomizerEvent e) {
        for (int i = 0; i < selectedList.size() - 1; i++) {
            JCustomizer customizer = selectedList.get(i);
            customizer.setBoundsRel(dxFactor * e.getDx(), dyFactor * e.getDy(), dwidthFactor * e.getDwidth(), dheightFactor * e.getDheight());
        //customizer.doLayout();
        }
    }

    @Override
    public void customizerReshapeRel(CustomizerEvent e) {
        for (int i = 0; i < selectedList.size() - 1; i++) {
            selectedList.get(i).reshapeRel(dxFactor * e.getDx(), dyFactor * e.getDy(), dwidthFactor * e.getDwidth(), dheightFactor * e.getDheight());
        }
    }

    private void resetFactors() {
        dxFactor = commonCustomizableProperties.contains("x") ? 1 : 0;
        dyFactor = commonCustomizableProperties.contains("y") ? 1 : 0;
        dwidthFactor = commonCustomizableProperties.contains("width") ? 1 : 0;
        dheightFactor = commonCustomizableProperties.contains("height") ? 1 : 0;
    }
}
