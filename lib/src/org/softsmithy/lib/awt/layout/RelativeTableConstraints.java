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


/**
 * The following inner class is used to bind components to their
 * constraints.
 *
 * http://java.sun.com/products/jfc/tsc/articles/tablelayout/
 * @author  Daniel E. Barbalace
 */

public class RelativeTableConstraints implements TableConstraints{
  
  
  /** Cell in which the upper left corner of the component lays */
  private int column;
  private int row;
  
  /** Holds value of property colSpan. */
  private int colSpan;
  
  /** Holds value of property rowSpan. */
  private int rowSpan;
  
  
  public RelativeTableConstraints() {
    this(0, 0, 1, 1);
  }
  

  
  public RelativeTableConstraints(int column, int row, int colSpan, int rowSpan) {
    setColumn(column);
    setRow(row);
    setColSpan(colSpan);
    setRowSpan(rowSpan);
  }
  
  
  
  /**
   * Gets a string representation of this TableLayoutConstraints.
   *
   * @return a string in the form "row 1, column 1, row 2, column 2" or
   *         "row, column, horizontal justification, vertical justification"
   */
  
  //  public String toString() {
  //    StringBuffer buffer = new StringBuffer();
  //
  //    buffer.append(row1);
  //    buffer.append(", ");
  //    buffer.append(col1);
  //    buffer.append(", ");
  //
  //    if ((row1 == row2) && (col1 == col2)) {
  //      final char h[] = {'L', 'C', 'F', 'R'};
  //      final char v[] = {'T', 'C', 'F', 'B'};
  //
  //      buffer.append(h[hAlign]);
  //      buffer.append(", ");
  //      buffer.append(v[vAlign]);
  //    }
  //    else {
  //      buffer.append(row2);
  //      buffer.append(", ");
  //      buffer.append(col2);
  //    }
  //
  //    return buffer.toString();
  //  }
  
  
  
  public int getColumn() {
    return column;
  }
  
  public int getRow() {
    return row;
  }
  
  /** Getter for property colSpan.
   * @return Value of property colSpan.
   *
   */
  public int getColSpan() {
    return this.colSpan;
  }
  
  /** Getter for property rowSpan.
   * @return Value of property rowSpan.
   *
   */
  public int getRowSpan() {
    return this.rowSpan;
  }
  
  public int getHeight(Component comp, TableLayout tl) {
    return tl.height(getRow(), getRowSpan());
  }
  
  public int getWidth(Component comp, TableLayout tl) {
    return tl.width(getColumn(), getColSpan());
  }
  
  public int getX(Component comp, TableLayout tl) {
    return tl.xLocation(getColumn());
  }
  
  public int getY(Component comp, TableLayout tl) {
    return tl.yLocation(getRow());
  }
  
  public void setAbsoluteBounds(Rectangle bounds, CustomizerLayout cl) {
    if (!(cl instanceof TableLayout)){
      throw new IllegalArgumentException("cl must be a Tablelayout!");
    }
    TableLayout tl = (TableLayout) cl;
    column = tl.columnIndex(bounds.x);
    row = tl.rowIndex(bounds.y);
    colSpan = tl.colSpan(column, bounds.width);
    rowSpan = tl.rowSpan(row, bounds.height);
  }
  
  /** Setter for property colSpan.
   * @param colSpan New value of property colSpan.
   *
   */
  public void setColSpan(int colSpan) {
    this.colSpan = colSpan >= 1 ? colSpan : 1;
  }
  
  /** Setter for property column.
   * @param column New value of property column.
   *
   */
  public void setColumn(int column) {
    this.column = column;
  }
  
  /** Setter for property row.
   * @param row New value of property row.
   *
   */
  public void setRow(int row) {
    this.row = row;
  }
  
  /** Setter for property rowSpan.
   * @param rowSpan New value of property rowSpan.
   *
   */
  public void setRowSpan(int rowSpan) {
    this.rowSpan = rowSpan >= 1 ? rowSpan : 1;
  }
  
  public Rectangle getAbsoluteBounds(Component comp, TableLayout tl) {
    return new Rectangle(getX(comp, tl), getY(comp, tl),
    getWidth(comp, tl), getHeight(comp, tl));
  }
  
  public Rectangle getRelativeBounds(Component comp, TableLayout tl) {
    return new Rectangle(column, row, colSpan,  rowSpan);
  }
  
  public void setRelativeBounds(Rectangle bounds, TableLayout tl) {
    setColumn(bounds.x);
    setRow(bounds.y);
    setColSpan(bounds.width);
    setRowSpan(bounds.height);
  }
  
}
