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
 * DefaultTableLayoutConstraints.java
 *
 * Created on 23. August 2002, 12:48
 */

package org.softsmithy.lib.awt.layout;

import java.awt.*;
import org.softsmithy.lib.swing.customizer.*;

/**
 *
 * @author  puce
 */
public final class AbsoluteTableConstraints implements TableConstraints{
  
  /** Holds value of property x. */
  private int x;
  
  /** Holds value of property y. */
  private int y;
  
  /** Holds value of property width. */
  private int width;
  
  /** Holds value of property height. */
  private int height;
  
  /** Creates a new instance of DefaultTableLayoutConstraints */
  public AbsoluteTableConstraints(int x, int y, int width, int height,
  TableLayout tl){
    setAbsoluteBounds(new Rectangle(x, y, width, height), tl);
  }
  
  /** Getter for property x.
   * @return Value of property x.
   */
  public int getX(Component comp, TableLayout tl) {
    return this.x;
  }
  
  public void setX(int x){
    this.x = x;
  }
  
  /** Getter for property y.
   * @return Value of property y.
   */
  public int getY(Component comp, TableLayout tl){
    return this.y;
  }
  
    public void setY(int y){
    this.y = y;
  }
  
  /** Getter for property width.
   * @return Value of property width.
   */
  public int getWidth(Component comp, TableLayout tl)  {
    return this.width;
  }
  
    public void setWidth(int width){
    this.width = width;
  }
    
  /** Getter for property height.
   * @return Value of property height.
   */
  public int getHeight(Component comp, TableLayout tl) {
    return this.height;
  }
  
    public void setHeight(int height){
    this.height = height;
  }
    
  public void setAbsoluteBounds(Rectangle bounds, CustomizerLayout cl){
    if (!(cl instanceof TableLayout)){
      throw new IllegalArgumentException("cl must be a Tablelayout!");
    }
    TableLayout tl = (TableLayout) cl;
    Rectangle adjustedBounds = tl.adjustBounds(bounds);
    this.x= adjustedBounds.x;
    this.y = adjustedBounds.y;
    this.width = adjustedBounds.width;
    this.height = adjustedBounds.height;
  }
  
  public Rectangle getAbsoluteBounds(Component comp, TableLayout tl) {
    return new Rectangle(x, y, width, height);
  }  
  
  public Rectangle getRelativeBounds(Component comp, TableLayout tl) {
    int column = tl.columnIndex(x);
    int row = tl.rowIndex(y);
    return new Rectangle(column, row, tl.colSpan(column, width), tl.rowSpan(row, height));
  }
  
  public void setRelativeBounds(Rectangle bounds, TableLayout tl) {
    setX(tl.xLocation(bounds.x));
    setY(tl.yLocation(bounds.y));
    setWidth(tl.width(bounds.x, bounds.width));
    setHeight(tl.height(bounds.y, bounds.height));
  }
  
  
}
