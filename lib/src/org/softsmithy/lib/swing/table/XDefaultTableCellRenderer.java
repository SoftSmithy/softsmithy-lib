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
 * This is an adapter class to use a CellRenderer as a TableCellRenderer.
 * @author puce
 * @see org.softsmithy.lib.swing.CellRenderer
 * @see javax.swing.table.TableCellRenderer
 */
public class XDefaultTableCellRenderer extends DefaultTableCellRenderer {
  
  /** Holds value of property cellRenderer. */
  private CellRenderer cellRenderer;
  
  /**
     * Creates a new instance of XDefaultTableCellRenderer
     * @param cellRenderer the CellRenderer delegate
     */
  public XDefaultTableCellRenderer(CellRenderer cellRenderer) {
    this.cellRenderer = cellRenderer;
  }
  
  /**
     * Gets the CellRenderer delegate.
     * @return the CellRenderer delegate
     */
  public CellRenderer getCellRenderer() {
    return this.cellRenderer;
  }
  

  protected void setValue(Object value) {
    super.setValue(cellRenderer.getDisplayValue(value, getLocale()));
  }
  

  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    configureFromTable(table);
    configureFromCellRenderer(); // TODO: shouldn't this method be called in the constructor instead?
    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
  }
  
    /**
     * Configures this TableCellRenderer from the JTable. <br/>
     * <br/>
     * By default sets the Locale of this renderer to the Locale of the JTable.
     * @param table the table to paint
     */
  protected void configureFromTable(JTable table){
    setLocale(table.getLocale());
  }
  
    /**
     * Configures this TableCellRenderer from the CellRenderer delegate. <br/>
     * <br/>
     * By default sets the horizontal alignment of this renderer to the horizontal 
     * alignment of the CellRenderer delegate.
     */
  protected void configureFromCellRenderer(){
    setHorizontalAlignment(getCellRenderer().getHorizontalAlignment().getSwingConstant());
  }
  
}
