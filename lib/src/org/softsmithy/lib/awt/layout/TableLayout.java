package puce.awt.layout;

import java.awt.*;
import puce.swing.customizer.*;


public interface TableLayout extends CustomizerLayout{
  
  //  TableConstraints getConstraints(Component component);
  //
  //  void setConstraints(Component component, TableConstraints constraint);
  
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
  
  int colSpan(int fromIndex, int pixelWidth);
  
  int rowSpan(int fromIndex, int pixelHeight);
  
  int xLocation(int index);
  
  int yLocation(int index);
  
  int width(int fromIndex, int colSpan);
  
  int height(int fromIndex, int rowSpan);
  
  int adjustX(int pixel);
  
  int adjustY(int pixel);
  
  int adjustWidth(int xPixel, int pixelWidth);
  
  int adjustHeight(int yPixel, int pixelHeight);
  
  Rectangle adjustBounds(Rectangle bounds);
  
  //  void layoutComponent(Container parent, Component comp);
  
  
}
