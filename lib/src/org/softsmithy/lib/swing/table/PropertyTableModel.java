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

package org.softsmithy.lib.swing.table;

import java.beans.*;
import java.util.*;
import javax.swing.table.*;
import org.softsmithy.lib.beans.*;
import org.softsmithy.lib.swing.*;
import org.softsmithy.lib.swing.table.*;
import org.softsmithy.lib.util.*;

/**
 *
 * @author  puce
 */
public abstract class PropertyTableModel extends AbstractTableModel implements PropertyChangeListener, CellTableModel{
  
  private final List properties;
  private final Object bean;
  private final ResourceBundle rb;
  
  /** Creates a new instance of AbstractCustomizerPropertyTableModel */
  public PropertyTableModel(Object bean, ResourceBundle rb) throws IntrospectionException{
    PropertyDescriptor[] descriptors = BeanIntrospector.getPropertyDescriptors(bean.getClass(), rb);
    Map propertyMap = new TreeMap();
    for (int i=0; i<descriptors.length; i++){
      if (BeanIntrospector.isPropertyReadable(descriptors[i].getName(), bean.getClass(), rb)){
        String key = descriptors[i].getDisplayName() != null ? descriptors[i].getDisplayName() : descriptors[i].getName();
        propertyMap.put(key, descriptors[i].getName());
      }
    }
    this.properties = Collections.unmodifiableList(new ArrayList(propertyMap.values()));
    this.bean = bean;
    this.rb = rb;
  }
  
  public PropertyTableModel(List readableProperties, Object bean, ResourceBundle rb) {
    this.properties = Collections.unmodifiableList(readableProperties);
    this.bean = bean;
    this.rb = rb;
    //    if (activeCustomizer != null){
    //      for (Iterator i=properties.iterator(); i.hasNext();){
    //        activeCustomizer.addPropertyChangeListener((String) i.next(), this);
    //      }
    //    }
  }
  
  /** Returns the number of columns in the model. A
   * <code>JTable</code> uses this method to determine how many columns it
   * should create and display by default.
   *
   * @return the number of columns in the model
   * @see #getRowCount
   *
   */
  public int getColumnCount() {
    return 2;
  }
  
  /** Returns the number of rows in the model. A
   * <code>JTable</code> uses this method to determine how many rows it
   * should display.  This method should be quick, as it
   * is called frequently during rendering.
   *
   * @return the number of rows in the model
   * @see #getColumnCount
   *
   */
  public int getRowCount() {
    return properties.size();
  }
  
  /** Returns the value for the cell at <code>columnIndex</code> and
   * <code>rowIndex</code>.
   *
   * @param	rowIndex	the row whose value is to be queried
   * @param	columnIndex 	the column whose value is to be queried
   * @return	the value Object at the specified cell
   *
   */
  public Object getValueAt(int rowIndex, int columnIndex) {
    Object value = null;
    try{
      switch (columnIndex){
        case 0: value = getPropertyDescriptor(rowIndex).getDisplayName(); break;
        case 1:
          value = BeanIntrospector.getPropertyValue((String) properties.get(rowIndex), bean, rb);
          break;
      }
    } catch (Exception e){
      e.printStackTrace();
      //value = "";
    }
    //System.out.print(value);
    return value;
  }
  
  /**  This empty implementation is provided so users don't have to implement
   *  this method if their data model is not editable.
   *
   *  @param  aValue   value to assign to cell
   *  @param  rowIndex   row of cell
   *  @param  columnIndex  column of cell
   *
   */
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    try{
      BeanIntrospector.setPropertyValue((String) properties.get(rowIndex), aValue, bean, rb);
    } catch (Exception ex2){
      ex2.printStackTrace();
    }
  }
  
  private PropertyDescriptor getPropertyDescriptor(int index) throws IntrospectionException{
    return BeanIntrospector.getPropertyDescriptor((String) properties.get(index), bean.getClass(), rb);
  }
  
  /**  Returns false.  This is the default implementation for all cells.
   *
   *  @param  rowIndex  the row being queried
   *  @param  columnIndex the column being queried
   *  @return false
   *
   */
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    boolean isCellEditable = false;
    try{
      isCellEditable = columnIndex == 1 && BeanIntrospector.isPropertyWriteable((String) properties.get(rowIndex), bean.getClass(), rb);
    } catch(IntrospectionException ex){
      ex.printStackTrace();
    }
    return isCellEditable;
  }
  
  
  /**  Returns a default name for the column using spreadsheet conventions:
   *  A, B, C, ... Z, AA, AB, etc.  If <code>column</code> cannot be found,
   *  returns an empty string.
   *
   * @param column  the column being queried
   * @return a string containing the default name of <code>column</code>
   *
   */
  public String getColumnName(int column) {
    String columnName = "";
    switch (column){
      case 0: columnName = "Eigenschaft"; break;
      case 1: columnName = "Wert"; break;
      default: columnName = super.getColumnName(column); // should not happen
    }
    return columnName;
  }
  
  /** This method gets called when a bound property is changed.
   * @param evt A PropertyChangeEvent object describing the event source
   *   	and the property that has changed.
   *
   */
  //  public void propertyChange(PropertyChangeEvent evt) {
  //    System.out.println("in");
  //    int index = customizers.indexOf(evt.getPropertyName());
  //    if (index >= 0){
  //      fireTableCellUpdated(index, 1);
  //    }
  //
  //  }
  public Class getCellClass(int row, int column){
    Class cellClass;
    try{
      switch (column){
        case 0: cellClass = String.class; break;
        case 1: cellClass = getPropertyDescriptor(row).getPropertyType(); break;
        default: cellClass = getColumnClass(column); // should not happen
      }
    } catch (IntrospectionException ex){
      cellClass = getColumnClass(column);
    }
    //System.out.print(cellClass+": ");
    return cellClass;
  }
  
  /** This method gets called when a bound property is changed.
   * @param evt A PropertyChangeEvent object describing the event source
   *   	and the property that has changed.
   *
   */
  public void propertyChange(PropertyChangeEvent evt) {
    //    System.out.println(evt.getPropertyName() + " changed");
    this.fireTableCellUpdated(properties.indexOf(evt.getPropertyName()), 1);
  }
  
  public List getProperties(){
    return this.properties;
  }
  
  public Object getBean(){
    return this.bean;
  }
  
}

