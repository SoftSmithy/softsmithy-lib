/*
 * XDefaultTableCellRenderer.java
 *
 * Created on 5. März 2003, 18:22
 */

package org.softsmithy.lib.swing.table;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import org.softsmithy.lib.swing.*;

/**
 *
 * @author  puce
 */
public class XDefaultTableCellRenderer extends DefaultTableCellRenderer {
  
  /** Holds value of property cellRenderer. */
  private CellRenderer cellRenderer;
  
  /** Creates a new instance of XDefaultTableCellRenderer */
  public XDefaultTableCellRenderer(CellRenderer cellRenderer) {
    this.cellRenderer = cellRenderer;
  }
  
  /** Getter for property cellRenderer.
   * @return Value of property cellRenderer.
   *
   */
  public CellRenderer getCellRenderer() {
    return this.cellRenderer;
  }
  
  /** Sets the <code>String</code> object for the cell being rendered to
   * <code>value</code>.
   *
   * @param value  the string value for this cell; if value is
   * 		<code>null</code> it sets the text value to an empty string
   * @see JLabel#setText
   *
   *
   */
  protected void setValue(Object value) {
    super.setValue(cellRenderer.getDisplayValue(value, getLocale()));
  }
  
  /**
   * Returns the default table cell renderer.
   *
   * @param table  the <code>JTable</code>
   * @param value  the value to assign to the cell at
   * 			<code>[row, column]</code>
   * @param isSelected true if cell is selected
   * @param isFocus true if cell has focus
   * @param row  the row of the cell to render
   * @param column the column of the cell to render
   * @return the default table cell renderer
   *
   */
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    configureFromTable(table);
    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
  }
  
  protected void configureFromTable(JTable table){
    setLocale(table.getLocale());
  }
}
