/*
 * SelectionManager.java
 *
 * Is this the right place? Should it be nested or in puce.swing?
 *
 * Created on 30. August 2002, 11:55
 */

package puce.swing.customizer;

import java.awt.Point;
import java.util.*;
import puce.swing.*;
import puce.swing.event.*;



/**
 *
 * @author  puce
 */
public class SelectionManager implements CustomizerListener{
  
  private final List selectedList = new ArrayList();
  private final Set selectedSet = new HashSet();
  
  /** Creates a new instance of SelectionManager */
  public SelectionManager() {
  }
  
  public void select(JCustomizer customizer, Point point) {
    setSelected(customizer);
    customizer.getStateManager().setStateBound(point);
    customizer.addCustomizerListener(this);
  }
  
  public void singleSelect(JCustomizer customizer, Point point){
    clearSelection();
    select(customizer, point);
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
  
  
  
}
