/*
 * DefaultTableLayoutConstraints.java
 *
 * Created on 23. August 2002, 12:48
 */

package puce.awt;

/**
 *
 * @author  puce
 */
public final class DefaultTableConstraints implements TableConstraints{
  
  /** Holds value of property x. */
  private final int x;
  
  /** Holds value of property y. */
  private final int y;
  
  /** Holds value of property width. */
  private final int width;
  
  /** Holds value of property height. */
  private final int height;
  
  /** Creates a new instance of DefaultTableLayoutConstraints */
  public DefaultTableConstraints(int x, int y, int width, int height){
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
  
  /** Getter for property x.
   * @return Value of property x.
   */
  public int getX() {
    return this.x;
  }
 
  /** Getter for property y.
   * @return Value of property y.
   */
  public int getY() {
    return this.y;
  }  
  
  /** Getter for property width.
   * @return Value of property width.
   */
  public int getWidth() {
    return this.width;
  }
  
  /** Getter for property height.
   * @return Value of property height.
   */
  public int getHeight() {
    return this.height;
  }
  
}
