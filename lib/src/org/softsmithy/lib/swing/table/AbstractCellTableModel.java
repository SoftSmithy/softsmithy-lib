/*
 * XAbstractTableModel.java
 *
 * Created on 26. September 2002, 12:17
 */

package org.softsmithy.lib.swing.table;

import javax.swing.table.*;

/**
 *
 * @author  puce
 */
public abstract class AbstractCellTableModel extends AbstractTableModel implements CellTableModel {
  
  /** Creates a new instance of XAbstractTableModel */
  public AbstractCellTableModel() {
  }
  
  public Class getCellClass(int row, int column) {
    return getValueAt(row, column).getClass();
  }
  
  /**  Returns <code>Object.class</code> regardless of <code>columnIndex</code>.
   *
   *  @param columnIndex  the column being queried
   *  @return the Object.class
   *
   */
  public Class getColumnClass(int columnIndex) {
    return getCellClass(0, columnIndex);
  }
  
}
