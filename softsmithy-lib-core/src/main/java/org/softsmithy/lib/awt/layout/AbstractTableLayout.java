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
package org.softsmithy.lib.awt.layout;

import java.awt.*;
import org.softsmithy.lib.awt.event.*;

import java.util.*;
import org.softsmithy.lib.awt.*;

public abstract class AbstractTableLayout implements TableLayout {

    private static final Dimension MAXIMUM_LAYOUT_SIZE = new Dimension(
            Integer.MAX_VALUE, Integer.MAX_VALUE);

    /**
     * Indicates whether or not the size of the cells are known for the last known
     * size of the container.  If valid is false or the container has been resized,
     * the cell sizes must be recalculated using calculateSize.
     */
    //  private boolean valid = false;
    //
    //  /** Previous known width of the container */
    //  protected int oldWidth;
    //
    //  /** Previous known height of the container */
    //  protected int oldHeight;
    protected abstract AbstractAxis getColumns();

    protected abstract AbstractAxis getRows();
    private final Set<ComponentLayoutListener> allComponentLayoutListeners =
            new HashSet<ComponentLayoutListener>();
    private final Map<Component, Set<ComponentLayoutListener>> componentLayoutListeners =
            new HashMap<Component, Set<ComponentLayoutListener>>();

    /*protected void insertColumn(int i, double width);
    protected void insertRow(int i, double height);
    protected void deleteRow(int i);
    protected void deleteColumn(int i);
    protected void setColumnWidth(int i, double width);
    protected void setRowHeight(int i, double height);*/
    /** If the layout manager uses a per-component string,
     * adds the component <code>comp</code> to the layout,
     * associating it
     * with the string specified by <code>name</code>.
     *
     * @param name the string to be associated with the component
     * @param comp the component to be added
     */
    @Override
    public void addLayoutComponent(String name, Component comp) {
        throw new IllegalArgumentException();
    }

    /** Adds the specified component to the layout, using the specified
     * constraint object.
     * @param comp the component to be added
     * @param constraints  where/how the component is added to the layout.
     */
    @Override
    public void addLayoutComponent(Component comp, Object constr) {
        if (!(constr instanceof TableConstraints)) {
            throw new IllegalArgumentException();
        }
        setTableConstraints(comp, (TableConstraints) constr);
    }

    /**
     * Calculates the sizes of the rows and columns based on the absolute and
     * relative sizes specified in <code>rowSpec</code> and <code>columnSpec</code>
     * and the size of the container.  The result is stored in <code>rowSize</code>
     * and <code>columnSize</code>.
     *
     * @param container    container using this TableLayout
     */
    protected void validateLayout(Rectangle innerArea) {
        getColumns().validate(innerArea.x, innerArea.width);
        getRows().validate(innerArea.y, innerArea.height);
        // Indicate that the size of the cells are known for the container's
        // current size
        //    setValid(true);
        //    oldWidth = innerArea.width;
        //    oldHeight = innerArea.height;
    }

    //
    //  protected boolean isValid() {
    //    return valid;
    //  }
    //  protected void setValid(boolean valid){
    //    this.valid = valid;
    //  }
    /**
     * Invalidates the layout, indicating that if the layout manager has cached
     * information it should be discarded.
     */
    @Override
    public void invalidateLayout(Container target) {
        getColumns().setValid(false);
        getRows().setValid(false);
        //    setValid(false);
    }

    protected boolean isValid(Rectangle innerArea) {
        // Calculate sizes if container has changed size or components were added
        return getColumns().isValid(innerArea.x, innerArea.width)
                && getRows().isValid(innerArea.y, innerArea.height);
    }

    /**
     * Returns the alignment along the x axis.  This specifies how the component
     * would like to be aligned relative to other components.  The value should be
     * a number between 0 and 1 where 0 represents alignment along the origin, 1 is
     * aligned the furthest away from the origin, 0.5 is centered, etc.
     *
     * @return unconditionally, 0.5
     */
    @Override
    public float getLayoutAlignmentX(Container parent) {
        return 0.5f;
    }

    /**
     * Returns the alignment along the y axis.  This specifies how the component
     * would like to be aligned relative to other components.  The value should be
     * a number between 0 and 1 where 0 represents alignment along the origin, 1 is
     * aligned the furthest away from the origin, 0.5 is centered, etc.
     *
     * @return unconditionally, 0.5
     */
    @Override
    public float getLayoutAlignmentY(Container parent) {
        return 0.5f;
    }

    /**
     * Returns the maximum dimensions for this layout given the components in the
     * specified target container.
     *
     * @param target the component which needs to be laid out
     *
     * @return unconditionally, a Dimension of Integer.MAX_VALUE by
     *         Integer.MAX_VALUE since TableLayout does not limit the
     *         maximum size of a container
     */
    @Override
    public Dimension maximumLayoutSize(Container target) {
        return MAXIMUM_LAYOUT_SIZE;
    }

    public void ensureValidity(Container container) {
        Rectangle innerArea = AWTUtilities.calculateInnerArea(container, null);
        ensureValidity(innerArea);
    }

    public void ensureValidity(Rectangle innerArea) {
        if (!isValid(innerArea)) {
            validateLayout(innerArea);
        }
    }

    /**
     * Lays out the specified container.
     * @param parent the container to be laid out
     */
    @Override
    public void layoutContainer(Container parent) {
        ensureValidity(parent);
        Component[] components = getComponents(parent);
        for (int i = 0; i < components.length; i++) {
            layoutComponent(parent, components[i]);
        }
    }

    @Override
    public void layoutComponent(Container parent, Component comp) {
        TableConstraints constr = getTableConstraints(comp); //(TableConstraints) getConstraints(comp);
        if (constr != null) {
            ensureValidity(parent);
            Rectangle bounds = constr.getAbsoluteBounds();
            comp.setBounds(bounds);
            fireComponentLayoutEvent(
                    new ComponentLayoutEvent(this, comp, bounds));
        }
    }

    public Component[] getComponents(Container parent) {
        return parent.getComponents();
    }

    @Override
    public void addComponentLayoutListener(ComponentLayoutListener listener) {
        allComponentLayoutListeners.add(listener);
    }

    @Override
    public void addComponentLayoutListener(Component component,
            ComponentLayoutListener listener) {
        Set<ComponentLayoutListener> listeners;
        if (componentLayoutListeners.containsKey(component)) {
            listeners = componentLayoutListeners.get(component);
        } else {
            listeners = new HashSet<ComponentLayoutListener>();
            componentLayoutListeners.put(component, listeners);
        }
        listeners.add(listener);
    }

    @Override
    public void removeComponentLayoutListener(ComponentLayoutListener listener) {
        allComponentLayoutListeners.remove(listener);
    }

    @Override
    public void removeComponentLayoutListener(Component component,
            ComponentLayoutListener listener) {
        if (componentLayoutListeners.containsKey(component)) {
            ((Set) componentLayoutListeners.get(component)).remove(listener);
        }
    }

    private void fireComponentLayoutEvent(ComponentLayoutEvent evt) {
        for (Iterator i = allComponentLayoutListeners.iterator(); i.hasNext();) {
            ((ComponentLayoutListener) i.next()).componentLayouted(evt);
        }
        Set listeners = (Set) componentLayoutListeners.get(evt.getComponent()); // ClassCastException if null?
        if (listeners != null) { //?
            for (Iterator i = listeners.iterator(); i.hasNext();) {
                ((ComponentLayoutListener) i.next()).componentLayouted(evt);
            }
        }
    }

    /*
    public void insertColumn(int i, double width) {
    setValid(false);
    }

    public void insertRow(int i, double height) {
    setValid(false);
    }

    public void deleteRow(int i) {
    setValid(false);
    }

    public void deleteColumn(int i) {
    setValid(false);
    }

    public void setColumnWidth(int i, double width) {
    setValid(false);
    }

    public void setRowHeight(int i, double height) {
    setValid(false);
    }*/
    protected static abstract class AbstractAxis {

        private boolean valid = false;
        private int oldInnerStart = -1;
        private int oldInnerSize = -1;

        public void ensureValidity(int innerStart, int innerSize) {
            if (!isValid(innerStart, innerSize)) {
                validate(innerStart, innerSize);
            }
        }

        public void invalidate() {
            setValid(false);
        }

        protected boolean isValid(int innerStart, int innerSize) {
            // Calculate sizes if container has changed size or units were added
            return isValid() && (innerStart == oldInnerStart) && (innerSize
                    == oldInnerSize);
        }

        protected boolean isValid() {
            return valid;
        }

        protected void setValid(boolean valid) {
            this.valid = valid;
        }

        protected void validate(int innerStart, int innerSize) {

            calculateSizes(innerSize);
            calculateOffsets(innerStart, innerSize);

            // Indicate that the size of the cells are known for the container's
            // current size
            setValid(true);
            oldInnerStart = innerStart;
            oldInnerSize = innerSize;
        }

        protected abstract void calculateSizes(int innerSize);

        protected abstract void calculateOffsets(int innerStart, int innerSize);
    }
}
