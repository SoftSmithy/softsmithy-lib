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
 * AbstractCustomizerPropertyTableModel.java
 *
 * Created on 6. Februar 2003, 19:24
 */

package org.softsmithy.lib.swing.customizer;

import java.util.*;
import org.softsmithy.lib.swing.table.*;

/**
 *
 * @author  puce
 */
public abstract class AbstractCustomizerPropertyTableModel extends PropertyTableModel{
  
  
  /** Creates a new instance of AbstractCustomizerPropertyTableModel */
  public AbstractCustomizerPropertyTableModel(List properties, AbstractCustomizer activeCustomizer, String propertiesRBBaseName, Locale locale) {
    super(properties, activeCustomizer, propertiesRBBaseName, locale);
//    if (activeCustomizer != null){
//      for (Iterator i=properties.iterator(); i.hasNext();){
//        activeCustomizer.addPropertyChangeListener((String) i.next(), this);
//      }
//    }
  }
  
  
  /**  This empty implementation is provided so users don't have to implement
   *  this method if their data model is not editable.
   *
   *  @param  aValue   value to assign to cell
   *  @param  rowIndex   row of cell
   *  @param  columnIndex  column of cell
   *
   */
  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    //    for (Iterator i=customizers.iterator(); i.hasNext();){
    //      AbstractCustomizer customizer = (AbstractCustomizer) i.next();
    //      //      try{
    //      //        getPropertyDescriptor(rowIndex).getWriteMethod().invoke(customizer, new Object[]{new Integer((String) aValue)});
    //      //      } catch (Exception ex1){
    //      try{
    //        getPropertyDescriptor(rowIndex, customizer).getWriteMethod().invoke(customizer, new Object[]{aValue});
    //      } catch (Exception ex2){
    //        ex2.printStackTrace();
    //      }
    //      }
    super.setValueAt(aValue, rowIndex, columnIndex);
    getActiveCustomizer().repaint();
  }
 
//  public void stopListening(){
//    stopPropertyChangeListening();
//  }
//  
//  private void stopPropertyChangeListening(){
//    if (getActiveCustomizer() != null){
//      for (Iterator i=getPropertyNames().iterator(); i.hasNext();){
//        getActiveCustomizer().removePropertyChangeListener((String) i.next(), this);
//      }
//    }
//  }
  
  public AbstractCustomizer getActiveCustomizer(){
    return (AbstractCustomizer) getBean();
  }
  
}

