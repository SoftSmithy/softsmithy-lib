/*
 *                  Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
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
public class SelectionManager implements PropertyChangeListener, CustomizerListener{
  
//  private static final Set RECTANGLE_PROPERTIES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[] {"x", "y", "width", "height"})));
  
  private final List selectedList = new ArrayList();
  private final Set selectedSet = new LinkedHashSet();
  
  private JCustomizer activeCustomizer = null;
  
  private Set listeners = new HashSet();
  
  /** Holds value of property fireingSelectionChanged. */
  private boolean fireingSelectionChanged = false;
  
  /** Holds value of property commonCustomizableProperties. */
  private Set commonCustomizableProperties = new LinkedHashSet();
//  private Set commonCustomizableBoundProperties = new HashSet();

  
  private int dxFactor = 0;
  private int dyFactor = 0;
  private int dwidthFactor = 0;
  private int dheightFactor = 0;
  
  
  /** Creates a new instance of SelectionManager */
  public SelectionManager() {
  }
  
  public void select(JCustomizer customizer, Point point) {
    selectOnly(customizer);
    customizer.getStateManager().setStateBound(point);
    fireSelectionChanged();
  }
  
  public void select(JCustomizer customizer){
    selectOnly(customizer);
    customizer.getStateManager().setStateMove();
    fireSelectionChanged();
  }
  
  private void selectOnly(JCustomizer customizer) {
    if (getActiveCustomizer() != null){
      getActiveCustomizer().getStateManager().setStateSelected();
    }
    setSelected(customizer);
    //customizer.addCustomizerListener(this);
    if(!customizer.hasFocus()){
      customizer.requestFocus();
    }
    revalidate();
  }
  
  public void singleSelect(JCustomizer customizer, Point point){
    clearSelectionOnly();
    select(customizer, point);
  }
  
  public void singleSelect(JCustomizer customizer){
    clearSelectionOnly();
    select(customizer);
  }
  
  public void deselect(JCustomizer customizer){
    JCustomizer last = null;
    JCustomizer secondLast = null;
    int size = selectedList.size();
    if(size > 0){
      last = (JCustomizer) selectedList.get(size-1); // == activeCustomizer
      if (size > 1){
        secondLast = (JCustomizer) selectedList.get(size-2); // == new activeCustomizer
      }
    }
    
    if (customizer.equals(last)){ // 'equals' or '==' ??
      if (secondLast != null){
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
  
  public void clearSelection(){
    clearSelectionOnly();
    fireSelectionChanged();
  }
  
  private void clearSelectionOnly(){
    for (Iterator i=selectedList.iterator(); i.hasNext();){
      JCustomizer customizer = (JCustomizer) i.next();
      customizer.getStateManager().setStateNormal();
      //customizer.removeCustomizerListener(this);
    }
    selectedList.clear();
    selectedSet.clear();
    revalidate();
  }
  
  
  private void ensureAppendedLast(JCustomizer customizer){
    if (isSelected(customizer)){
      setDeselected(customizer);
    }
  }
  
  
  public void deleteSelection() {
    for (int i=0; i<selectedList.size(); i++){
      JCustomizer customizer = (JCustomizer) selectedList.get(i);
      Container parent = customizer.getParent();
      if (! parent.hasFocus()){
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
  
  private void fireSelectionChanged(){
    fireingSelectionChanged = true;
    CustomizerSelectionEvent e = new CustomizerSelectionEvent(this, Collections.unmodifiableSet(selectedSet), getActiveCustomizer(), getCommonCustomizableProperties());
    for(Iterator i = listeners.iterator(); i.hasNext();){
      ((CustomizerSelectionListener) i.next()).selectionChanged(e);
    }
    fireingSelectionChanged = false;
  }
  
  public JCustomizer[] getSelectedCustomizers(){
    return (JCustomizer[]) selectedList.toArray(new JCustomizer[selectedList.size()]);
  }
  
  //  private void resetActiveCustomizer(){
  //  }
  
  public JCustomizer getActiveCustomizer(){
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
  public Set getCommonCustomizableProperties() {
    return this.commonCustomizableProperties;
  }
  
  private void revalidate(){
    if (getActiveCustomizer() != null){
      getActiveCustomizer().removeCustomizerListener(this);
      for (Iterator i=commonCustomizableProperties.iterator(); i.hasNext();){
        getActiveCustomizer().removePropertyChangeListener((String) i.next(), this);
      }
    }
    this.activeCustomizer = selectedList.isEmpty() ? null : (JCustomizer) selectedList.get(selectedList.size()-1);
    this.commonCustomizableProperties = JCustomizer.getCommonCustomizableProperties(selectedSet); // Collections.unmodifiableSet(JCustomizer.getCommonCustomizableProperties(selectedSet));
    //System.out.println("Properties: "+this.commonCustomizableProperties);
//    Set commonCustomizableRectangleProperties = new HashSet(RECTANGLE_PROPERTIES);
//    commonCustomizableRectangleProperties.retainAll(this.commonCustomizableProperties); // only the wanted rectangle properties
//    System.out.println("Rectangle Properties: "+commonCustomizableRectangleProperties);
//    this.commonCustomizableBoundProperties = new HashSet(this.commonCustomizableProperties);
//    this.commonCustomizableBoundProperties.removeAll(commonCustomizableRectangleProperties); // only the bound properties
//    System.out.println("Bound properties: "+this.commonCustomizableBoundProperties);
    resetFactors();
    if (getActiveCustomizer() != null){
      getActiveCustomizer().addCustomizerListener(this);
      for (Iterator i=this.commonCustomizableProperties.iterator(); i.hasNext();){
        String property = (String) i.next();
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
  public void propertyChange(PropertyChangeEvent evt) {
    //System.out.println("Property Changed!!!!!! - " + evt.getPropertyName());
    for (int i=0; i<selectedList.size()-1; i++){
      JCustomizer customizer = (JCustomizer) selectedList.get(i);
      try{
        BeanIntrospector.setPropertyValue(evt.getPropertyName(), evt.getNewValue(), customizer, null);
        customizer.repaint();
      } catch (Exception ex){
        ex.printStackTrace();
      }
    }
  }
  
  public void customizerResetBoundsRel(CustomizerEvent e){
    for (int i=0; i<selectedList.size()-1; i++){
      JCustomizer customizer = (JCustomizer) selectedList.get(i);
      customizer.setBoundsRel(dxFactor*e.getDx(), dyFactor*e.getDy(), dwidthFactor*e.getDwidth(), dheightFactor*e.getDheight());
      //customizer.doLayout();
    }
  }
  public void customizerReshapeRel(CustomizerEvent e){
    for (int i=0; i<selectedList.size()-1; i++){
      ((JCustomizer) selectedList.get(i)).reshapeRel(dxFactor*e.getDx(), dyFactor*e.getDy(), dwidthFactor*e.getDwidth(), dheightFactor*e.getDheight());
    }
  }
  
  private void resetFactors(){
    dxFactor = commonCustomizableProperties.contains("x") ? 1 : 0;
    dyFactor = commonCustomizableProperties.contains("y") ? 1 : 0;
    dwidthFactor = commonCustomizableProperties.contains("width") ? 1 : 0;
    dheightFactor = commonCustomizableProperties.contains("height") ? 1 : 0;
  }
  
}
