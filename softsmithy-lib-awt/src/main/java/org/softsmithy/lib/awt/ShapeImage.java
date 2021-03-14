/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

/*
 * ShapeImage.java
 *
 * Created on 11. Februar 2003, 19:20
 */

package org.softsmithy.lib.awt;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *
 * @author  puce
 */
class ShapeImage extends ImageWrapper{
  
  /** Holds value of property shape. */
  private final Shape shape;
  
  /** Holds value of property paint. */
  private final Paint paint;
  
  /** Holds value of property filled. */
  private final boolean filled;
  
  /** Holds value of property stroke. */
  private final Stroke stroke;
  
  /** Creates a new instance of ShapeImage */
  public ShapeImage(Shape shape, Paint paint, boolean filled, Stroke stroke) {
    super(new BufferedImage(shape.getBounds().width, shape.getBounds().height, BufferedImage.TYPE_INT_ARGB));
    this.shape = shape;
    this.paint = paint;
    this.filled = filled;
    this.stroke = stroke;
    Graphics2D big = ((BufferedImage) getImage()).createGraphics();
    big.setPaint(paint);
    if (filled){
      big.fill(shape);
    } else {
      if (stroke != null){
        Stroke oldStroke = big.getStroke();
        big.setStroke(stroke);
        big.draw(shape);
        big.setStroke(oldStroke);
      } else {
        big.draw(shape);
      }
    }
  }
  
  /** Getter for property shape.
   * @return Value of property shape.
   *
   */
  public Shape getShape() {
    return this.shape;
  }
  
  /** Getter for property shapePaint.
   * @return Value of property shapePaint.
   *
   */
  public Paint getPaint() {
    return this.paint;
  }
  
  /** Getter for property shapeFilled.
   * @return Value of property shapeFilled.
   *
   */
  public boolean isFilled() {
    return this.filled;
  }
  
  /** Getter for property stroke.
   * @return Value of property stroke.
   *
   */
  public Stroke getStroke() {
    return this.stroke;
  }
  
}
