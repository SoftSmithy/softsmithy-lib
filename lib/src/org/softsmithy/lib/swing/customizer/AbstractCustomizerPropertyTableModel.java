/*
 * AbstractCustomizerPropertyTableModel.java
 *
 * Created on 6. Februar 2003, 19:24
 */

package org.softsmithy.lib.swing.customizer;

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
public abstract class AbstractCustomizerPropertyTableModel extends AbstractTableModel implements PropertyChangeListener, CellTableModel{
  
  private final Set customizers;
  private final List properties;
  private final AbstractCustomizer activeCustomizer;
  private final ResourceBundleCache cache = new ResourceBundleCache("org.softsmithy.lib.swing.customizer.Properties");
  
  /** Holds value of property locale. */
  private Locale locale;
  
  /** Creates a new instance of AbstractCustomizerPropertyTableModel */
  public AbstractCustomizerPropertyTableModel(List properties, Set abstractCustomizers, AbstractCustomizer activeCustomizer, Locale locale) {
    this.properties = properties;
    this.customizers = abstractCustomizers;
    this.activeCustomizer = activeCustomizer;
    if (activeCustomizer != null){
      for (Iterator i=properties.iterator(); i.hasNext();){
        activeCustomizer.addPropertyChangeListener((String) i.next(), this);
      }
    }
    setLocale(locale);
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
    return customizers.size() > 0 ? properties.size() : 0;
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
        case 0: value = getPropertyDescriptor(rowIndex, activeCustomizer).getDisplayName(); break;
        case 1:
          value = getPropertyDescriptor(rowIndex, activeCustomizer).getReadMethod().invoke(activeCustomizer, new Object[]{});
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
    for (Iterator i=customizers.iterator(); i.hasNext();){
      AbstractCustomizer customizer = (AbstractCustomizer) i.next();
      //      try{
      //        getPropertyDescriptor(rowIndex).getWriteMethod().invoke(customizer, new Object[]{new Integer((String) aValue)});
      //      } catch (Exception ex1){
      try{
        getPropertyDescriptor(rowIndex, customizer).getWriteMethod().invoke(customizer, new Object[]{aValue});
      } catch (Exception ex2){
        ex2.printStackTrace();
      }
      //      }
      customizer.repaint();
    }
  }
  
  private PropertyDescriptor getPropertyDescriptor(int index, AbstractCustomizer customizer) throws IntrospectionException{
    return BeanIntrospector.getPropertyDescriptor((String) properties.get(index), customizer.getClass(), cache.getBundle(getLocale()));
  }
  
    
  /**  Returns false.  This is the default implementation for all cells.
   *
   *  @param  rowIndex  the row being queried
   *  @param  columnIndex the column being queried
   *  @return false
   *
   */
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return columnIndex == 1;
  }
  
  public void stopListening(){
    stopPropertyChangeListening();
  }
  
  private void stopPropertyChangeListening(){
    if (activeCustomizer != null){
      for (Iterator i=properties.iterator(); i.hasNext();){
        activeCustomizer.removePropertyChangeListener((String) i.next(), this);
      }
    }
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
        case 1: cellClass = getPropertyDescriptor(row, activeCustomizer).getPropertyType(); break;
        default: cellClass = getColumnClass(column); // should not happen
      }
    } catch (IntrospectionException ex){
      cellClass = getColumnClass(column);
    }
    //System.out.print(cellClass+": ");
    return cellClass;
  }
  
  /** Getter for property locale.
   * @return Value of property locale.
   *
   */
  public Locale getLocale() {
    return this.locale;
  }
  
  /** Setter for property locale.
   * @param locale New value of property locale.
   *
   */
  public void setLocale(Locale locale) {
    this.locale = locale;
    // fireTableDataChanged(); // necessary??
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
  
  public AbstractCustomizer getActiveCustomizer(){
    return this.activeCustomizer;
  }
  
}

