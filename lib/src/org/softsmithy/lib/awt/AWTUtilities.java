/*
 * AWTUtilities.java
 *
 * Created on 22. August 2002, 12:34
 */

package puce.awt;

import java.awt.*;

/**
 *
 * @author  puce
 */
public class AWTUtilities {
  
  /** Creates a new instance of AWTUtilities */
  private AWTUtilities() {
  }
  
  /** 
   * Copied from SwingUtilities which uses JComponent instead of Container :-(
   *
   * Stores the position and size of
   * the inner painting area of the specified container
   * in <code>r</code> and returns <code>r</code>.
   * The position and size specify the bounds of the container,
   * adjusted so as not to include the insets.
   * This method is useful for classes
   * that implement painting code.
   *
   * @param c  the Container in question; if null, this method returns null
   * @param r  the Rectangle instance to be modified;
   *           may be null
   * @return null if the Container is null;
   *         otherwise, returns the passed-in rectangle (if non-null)
   *         or a new rectangle specifying position and size information
   *
   * @since 1.4
   */
  public static Rectangle calculateInnerArea(Container c, Rectangle r) {
    if (c == null) {
      return null;
    }
    Rectangle rect = r;
    Insets insets = c.getInsets();
    
    if (rect == null) {
      rect = new Rectangle();
    }
    
    rect.x = insets.left;
    rect.y = insets.top;
    rect.width = c.getWidth() - insets.left - insets.right;
    rect.height = c.getHeight() - insets.top - insets.bottom;
    
    return rect;
  }
  
}
