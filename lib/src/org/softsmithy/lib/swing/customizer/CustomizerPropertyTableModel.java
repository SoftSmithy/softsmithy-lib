/*
 * PropertyTableModel.java
 *
 * Created on 19. September 2002, 18:11
 */

package puce.swing.customizer;

import java.beans.*;
import java.util.*;
import javax.swing.table.*;
import puce.beans.*;
import puce.swing.*;
import puce.swing.event.*;
import puce.swing.table.*;
import puce.util.*;

/**
 *
 * @author  puce
 */
public class CustomizerPropertyTableModel extends AbstractTableModel implements CustomizerListener, CellTableModel{
  
  private List customizers;
  private List properties;
  private Class topMostCommonClass;
  private ResourceBundleCache cache = new ResourceBundleCache("puce.swing.customizer.Properties");
  
  /** Holds value of property locale. */
  private Locale locale;
  
  /** Creates a new instance of PropertyTableModel */
  public CustomizerPropertyTableModel(List properties, List customizers, Class topMostCommonClass, Locale locale) {
    this.properties = properties;
    this.customizers = customizers;
    this.topMostCommonClass = topMostCommonClass;
    if (customizers.size() > 0){
      ((JCustomizer) customizers.get(customizers.size()-1)).addCustomizerListener(this);//addPropertyChangeListener(this); // where should it be removed?
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
        case 0: value = getPropertyDescriptor(rowIndex).getDisplayName(); break;
        case 1:
          value = getPropertyDescriptor(rowIndex).getReadMethod().invoke(customizers.get(customizers.size()-1), new Object[]{});
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
      JCustomizer customizer = (JCustomizer) i.next();
//      try{
//        getPropertyDescriptor(rowIndex).getWriteMethod().invoke(customizer, new Object[]{new Integer((String) aValue)});
//      } catch (Exception ex1){
        try{
          getPropertyDescriptor(rowIndex).getWriteMethod().invoke(customizer, new Object[]{aValue});
        } catch (Exception ex2){
          ex2.printStackTrace();
        }
//      }
      customizer.repaint();
    }
  }
  
  private PropertyDescriptor getPropertyDescriptor(int index) throws IntrospectionException{
    return BeanIntrospector.getPropertyDescriptor((String) properties.get(index), topMostCommonClass, cache.getBundle(getLocale()));
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
  
  public void customizerDelete(CustomizerEvent e) {
  }
  
  public void customizerFinishReshapeRel(CustomizerEvent e) {
    updateBounds();
  }
  
  public void customizerReshapeRel(CustomizerEvent e) {
    updateBounds();
  }
  
  private void updateBounds(){
    String[] bounds = {"x", "y", "width", "height"};
    for (int i=0; i<bounds.length; i++){
      int index = properties.indexOf(bounds[i]);
      if (index >= 0){
        fireTableCellUpdated(index, 1);
      }
    }
  }
  
  public void stopCustomizerListening() {
    if (customizers.size() > 0){
      ((JCustomizer) customizers.get(customizers.size()-1)).removeCustomizerListener(this);
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
        case 1: cellClass = getPropertyDescriptor(row).getPropertyType(); break;
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
  }
  
}
