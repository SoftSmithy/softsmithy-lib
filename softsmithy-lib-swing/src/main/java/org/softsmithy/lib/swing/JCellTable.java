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

package org.softsmithy.lib.swing;

import javax.swing.table.*;
import org.softsmithy.lib.swing.table.*;

/**
 * JCellTable uses CellTableModel as its model and supports classes, renderers
 * and editors per cell.
 */
public class JCellTable extends JXTable {
  
  /**
   * Constructs a <code>JCellTable</code> that is initialized with
   * <code>ctm</code> as the data model, a default column model,
   * and a default selection model.
   *
   * @param ctm        the data model for the table
   * @see #createDefaultColumnModel
   * @see #createDefaultSelectionModel
   */
  public JCellTable(CellTableModel ctm) {
    super(ctm);
  }
  
  /**
   * Returns an appropriate renderer for the cell specified by this <code>row</code> and
   * <code>column</code>. This implementation does not respect renderers specified for
   * <code>TableColumn</code>!
   * It finds the class of the data in this cell (using <code>getCellClass</code>)
   * and returns the default renderer for this type of data.
   *
   * @param row       the row of the cell to render, where 0 is the first row
   * @param column    the column of the cell to render,
   *			where 0 is the first column
   * @return the default renderer for this type of object
   * @see javax.swing.table.DefaultTableCellRenderer
   * @see javax.swing.table.TableColumn#setCellRenderer
   * @see #setDefaultRenderer
   * @see #getCellClass
   */
  
  @Override
  public TableCellRenderer getCellRenderer(int row, int column) {
    TableCellRenderer renderer = getDefaultRenderer(getCellClass(row, column));
    return renderer;
  }
  
  /** Returns an appropriate editor for the cell specified by
   * <code>row</code> and <code>column</code>. This implementation does not
   * respect editors specified for <code>TableColumn</code>!
   * It finds the class of the data in this
   * cell (using <code>getCellClass</code>)
   * and returns the default editor for this type of data.
   *
   * @param row       the row of the cell to edit, where 0 is the first row
   * @param column    the column of the cell to edit,
   * 			where 0 is the first column
   * @return          the default editor for this type of object
   * @see javax.swing.DefaultCellEditor
   * @see #setDefaultEditor
   * @see #getCellClass
   *
   */
  @Override
  public TableCellEditor getCellEditor(int row, int column) {
    return getDefaultEditor(getCellClass(row, column));
  }
  
  /**
   * Returns the type of the cell appearing at position <code>row</code>,
   * <code>column</code>.
   *
   * @param   row      the row of the cell being queried
   * @param   column   the column of the cell being queried
   * @return the type of the cell at position <code>row</code>, <code>column</code>
   *         where the first row is row 0 and the first column is column 0
   * @see CellTableModel#getCellClass
   */
  public Class getCellClass(int row, int column){
    return getCellTableModel().getCellClass(row, column);
  }
  
  
  /**
   * Sets the data model for this table to <code>cellTableModel</code>
   * (which must be an instance of <code>CellTableModel</code>) and registers
   * with it for listener notifications from the new data model.
   *
   * @param   cellTableModel        the new data source for this table
   * @exception IllegalArgumentException      if <code>cellTableModel</code> is
   * not an instance of <code>CellTableModel</code>
   * @see     #getModel
   *
   */
  @Override
  public void setModel(TableModel cellTableModel) {
    if (! (cellTableModel instanceof CellTableModel)){
      throw new IllegalArgumentException("cellTableModel must be a CellTableModel");
    }
    super.setModel(cellTableModel);
  }
  
  /**
   * Returns the <code>CellTableModel</code> that provides the data displayed by this
   * <code>JCellTable</code>.
   *
   * @return  the <code>CellTableModel</code> that provides the data displayed by this <code>JCellTable</code>
   * @see     #setCellTableModel
   */
  public CellTableModel getCellTableModel(){
    return (CellTableModel) getModel();
  }
  
  /**
   * Sets the data model for this table to <code>cellTableModel</code> and registers
   * with it for listener notifications from the new data model.
   *
   * @param   dataModel        the new data source for this table
   * @exception IllegalArgumentException      if <code>cellTableModel</code> is <code>null</code>
   * @see     #getCellTableModel
   */
  public void setCellTableModel(CellTableModel cellTableModel){
    setModel(cellTableModel);
  }
  
}
