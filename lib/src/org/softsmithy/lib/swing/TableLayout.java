package puce.swing;

import java.awt.*;


public interface TableLayout extends LayoutManager2{

  TableConstraints getConstraints(Component component);
  
  void setConstraints(Component component, TableConstraints constraint);
  
  void drawGrid(Container container, Graphics g);

  void deleteRow(int i);

  void insertColumn(int i, double width);

  void setRowHeight(int i, double height);

  void setColumnWidth(int i, double width);

  double getColumnWidth(int i);

  double getRowHeight(int i);

  void insertRow(int i, double height);

  void deleteColumn(int i);
  
  int columnIndex(int pixel);
  
  int rowIndex(int pixel);


}
