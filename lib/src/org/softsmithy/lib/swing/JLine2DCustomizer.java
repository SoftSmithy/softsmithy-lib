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
 * JLine2DCustomizer.java
 *
 * Created on 8. November 2002, 22:19
 */

package org.softsmithy.lib.swing;

import java.awt.*;
import java.awt.geom.*;
import org.softsmithy.lib.swing.customizer.*;

/**
 *
 * @author  puce
 */
public class JLine2DCustomizer extends JShapeCustomizer {
  
  /** Holds value of property orientation. */
  private Line2DOrientation orientation;
  
  /** Creates a new instance of JLine2DCustomizer */
  public JLine2DCustomizer() {
  }
  
  /** Getter for property line2D.
   * @return Value of property line2D.
   *
   */
  public Line2D getLine2D() {
    return (Line2D) getShape();
  }
  
  /** Setter for property line2D.
   * @param line2D New value of property line2D.
   *
   */
  public void setLine2D(Line2D line2D) {
    setShape(line2D);
  }
  
  /** Setter for property imageSrc.
   * @param imageSrc New value of property imageSrc.
   *
   *
   */
  public void setShape(Shape shape) {
    if (! (shape instanceof Line2D)){
      throw new IllegalArgumentException("shape must be a Line2D");
    }
    super.setShape(shape);
  }
  
  /** Getter for property orientation.
   * @return Value of property orientation.
   *
   */
  public Line2DOrientation getOrientation() {
    return this.orientation;
  }
  
  /** Setter for property orientation.
   * @param orientation New value of property orientation.
   *
   */
  public void setOrientation(Line2DOrientation orientation) {
    this.orientation = orientation;
    setLine2D(orientation.getLine2D());
    adjustIcon();
  }
  
  /** Getter for property thickness.
   * @return Value of property thickness.
   *
   */
  public float getThickness() {
    return ((BasicStroke) getStroke()).getLineWidth();
  }  
  
  /** Setter for property thickness.
   * @param thickness New value of property thickness.
   *
   */
  public void setThickness(float thickness) {
    BasicStroke stroke = (BasicStroke) getStroke();
    setStroke(new BasicStroke(thickness, stroke.getEndCap(), stroke.getLineJoin(), 
    stroke.getMiterLimit(), stroke.getDashArray(),  stroke.getDashPhase()));
  }
  
  /** Getter for property color.
   * @return Value of property color.
   *
   */
  public Color getColor() {
    return getForeground();
  }
  
  /** Setter for property color.
   * @param color New value of property color.
   *
   */
  public void setColor(Color color) {
    setForeground(color);
  }
  
}
