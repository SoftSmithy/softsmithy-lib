package puce.swing.table;

import javax.swing.table.*;


public interface CellTableModel extends TableModel{

  Class getCellClass(int row, int column);

}
