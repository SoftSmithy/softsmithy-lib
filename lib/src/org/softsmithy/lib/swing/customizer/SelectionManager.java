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
import java.util.*;
import org.softsmithy.lib.swing.*;
import org.softsmithy.lib.swing.event.*;



/**
 *
 * @author  puce
 */
public class SelectionManager implements CustomizerListener{
  
  private final List selectedList = new ArrayList();
  private final Set selectedSet = new LinkedHashSet();
  
  private Set listeners = new HashSet();
  
  /** Holds value of property fireingSelectionChanged. */
  private boolean fireingSelectionChanged = false;
  
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
    if (selectedList.size()>0){
      getActiveCustomizer().getStateManager().setStateSelected();
    }
    setSelected(customizer);
    customizer.addCustomizerListener(this);
    if(!customizer.hasFocus()){
      customizer.requestFocus();
    }
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
      last = (JCustomizer) selectedList.get(size-1);
      if (size > 1){
        secondLast = (JCustomizer) selectedList.get(size-2);
      }
    }
    
    if (customizer.equals(last)){ // 'equals' or '==' ??
      if (secondLast != null){
        secondLast.getStateManager().setStateMove();
      }
    }
    customizer.getStateManager().setStateNormal();
    setDeselected(customizer);
    customizer.removeCustomizerListener(this);
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
      customizer.removeCustomizerListener(this);
    }
    selectedList.clear();
    selectedSet.clear();
  }
  
  
  private void ensureAppendedLast(JCustomizer customizer){
    if (isSelected(customizer)){
      setDeselected(customizer);
    }
  }
  
  public void customizerReshapeRel(CustomizerEvent e){
    for (int i=0; i<selectedList.size(); i++){
      JCustomizer customizer = (JCustomizer) selectedList.get(i);
      customizer.setBoundsRel(e.getDx(), e.getDy(), e.getDwidth(), e.getDheight());
      customizer.doLayout();
    }
  }
  public void customizerFinishReshapeRel(CustomizerEvent e){
    for (int i=0; i<selectedList.size(); i++){
      ((JCustomizer) selectedList.get(i)).reshapeRel(e.getDx(), e.getDy(), e.getDwidth(), e.getDheight());
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
      parent.remove(customizer);
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
    CustomizerSelectionEvent e = new CustomizerSelectionEvent(this, Collections.unmodifiableSet(selectedSet), getActiveCustomizer());
    for(Iterator i = listeners.iterator(); i.hasNext();){
      ((CustomizerSelectionListener) i.next()).selectionChanged(e);
    }
    fireingSelectionChanged = false;
  }
  
  public JCustomizer[] getSelectedCustomizers(){
    return (JCustomizer[]) selectedList.toArray(new JCustomizer[selectedList.size()]);
  }
  
  public JCustomizer getActiveCustomizer(){
    return selectedList.isEmpty() ? null : (JCustomizer) selectedList.get(selectedList.size()-1);
  }
  
  /** Getter for property fireingSelectionChanged.
   * @return Value of property fireingSelectionChanged.
   *
   */
  public boolean isFireingSelectionChanged() {
    return this.fireingSelectionChanged;
  }
  
}
