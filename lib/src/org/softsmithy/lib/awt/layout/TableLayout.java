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

package org.softsmithy.lib.awt.layout;

import java.awt.*;
import org.softsmithy.lib.swing.customizer.*;


public interface TableLayout extends CustomizerLayout{
  
  //  TableConstraints getConstraints(Component component);
  //
  //  void setConstraints(Component component, TableConstraints constraint);
  
  void drawGrid(Container container, Graphics g);
  
  void deleteRow(int i, boolean absolute);
  
  void insertColumn(int i, boolean absolute);
  
  void setRowHeight(int i, double height);
  
  void setColumnWidth(int i, double width);
  
  double getColumnWidth(int i);
  
  double getRowHeight(int i);
  
  void insertRow(int i, boolean absolute);
  
  void deleteColumn(int i, boolean absolute);
  
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
