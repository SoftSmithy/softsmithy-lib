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
 * ShapeIcon.java
 *
 * Created on 18. Oktober 2002, 18:33
 */

package org.softsmithy.lib.swing.icon;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class ShapeIcon implements XIcon {
  
  /** Holds value of property shape. */
  private Shape shape = null;
  
  /** Holds value of property filled. */
  private boolean filled = false;
  
  /** Holds value of property stroke. */
  private Stroke stroke = new BasicStroke();
  
  /** Creates a new instance of ShapeIcon */
  public ShapeIcon() {
  }
  
  public ShapeIcon(Shape shape){
    setShape(shape);
  }
  
  /** Returns the icon's height.
   *
   * @return an int specifying the fixed height of the icon.
   *
   */
  public int getIconHeight() {
    return shape != null ? shape.getBounds().height : -1; //+ shape.getBounds().y : 0;
  }
  
  /** Returns the icon's width.
   *
   * @return an int specifying the fixed width of the icon.
   *
   */
  public int getIconWidth() {
    return shape != null ? shape.getBounds().width : -1; //+ shape.getBounds().x : 0;
  }
  
  /** Draw the icon at the specified location.  Icon implementations
   * may use the Component argument to get properties useful for
   * painting, e.g. the foreground or background color.
   *
   */
  public void paintIcon(Component c, Graphics g, int x, int y) {
    if (getShape() != null){
      Graphics2D g2 = (Graphics2D) g;
      Color oldColor = g2.getColor();
      g2.setColor(c.getForeground());
      if (isFilled()){
        g2.fill(shape);
      } else {
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(getStroke());
        g2.draw(shape);
        g2.setStroke(oldStroke);
      }
      g2.setColor(oldColor);
    }
  }
  
  /** Getter for property shape.
   * @return Value of property shape.
   *
   */
  public Shape getShape() {
    return this.shape;
  }
  
  /** Setter for property shape.
   * @param shape New value of property shape.
   *
   */
  public void setShape(Shape shape) {
    this.shape = shape;
  }
  
  /** Getter for property filled.
   * @return Value of property filled.
   *
   */
  public boolean isFilled() {
    return this.filled;
  }
  
  /** Setter for property filled.
   * @param filled New value of property filled.
   *
   */
  public void setFilled(boolean filled) {
    this.filled = filled;
  }
  
  /** Getter for property stroke.
   * @return Value of property stroke.
   *
   */
  public Stroke getStroke() {
    return this.stroke;
  }
  
  /** Setter for property stroke.
   * @param stroke New value of property stroke.
   *
   */
  public void setStroke(Stroke stroke) {
    this.stroke = stroke;
  }
  
  //  public void transform(AffineTransform at){
  //    setShape(at.createTransformedShape(getShape()));
  //  }
  
  public ShapeIcon getTransformedInstance(AffineTransform at){
    ShapeIcon icon = getShape() != null ? new ShapeIcon(at.createTransformedShape(getShape()))
    : new ShapeIcon();
    icon.setFilled(isFilled());
    icon.setStroke(getStroke());
    return icon;
  }
  
  public XIcon getScaledInstance(int newWidth, int newHeight) { //????
    double sx = (getIconWidth() > 0.0) ? ((double) newWidth -0.25) / getIconWidth() : 1.0; // -0.25 seems to be necessary (tested with JXiconCustomizer)
    double sy = (getIconHeight() > 0.0) ? ((double) newHeight -0.25) / getIconHeight() : 1.0; // -0.25 seems to be necessary (tested with JXiconCustomizer)
    return getTransformedInstance(AffineTransform.getScaleInstance(sx, sy));
  }
  
}
