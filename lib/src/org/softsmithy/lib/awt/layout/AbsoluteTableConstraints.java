/*
 * DefaultTableLayoutConstraints.java
 *
 * Created on 23. August 2002, 12:48
 */

package puce.awt.layout;

import java.awt.*;
import puce.swing.customizer.*;

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
    setBounds(new Rectangle(x, y, width, height), tl);
  }
  
  /** Getter for property x.
   * @return Value of property x.
   */
  public int getX(TableLayout tl) {
    return this.x;
  }
  
  /** Getter for property y.
   * @return Value of property y.
   */
  public int getY(TableLayout tl){
    return this.y;
  }
  
  /** Getter for property width.
   * @return Value of property width.
   */
  public int getWidth(TableLayout tl)  {
    return this.width;
  }
  
  /** Getter for property height.
   * @return Value of property height.
   */
  public int getHeight(TableLayout tl) {
    return this.height;
  }
  
  public void setBounds(Rectangle bounds, CustomizerLayout cl){
    if (!(cl instanceof TableLayout)){
      throw new IllegalArgumentException();
    }
    TableLayout tl = (TableLayout) cl;
    Rectangle adjustedBounds = tl.adjustBounds(bounds);
    this.x= adjustedBounds.x;
    this.y = adjustedBounds.y;
    this.width = adjustedBounds.width;
    this.height = adjustedBounds.height;
  }
  
  
}
