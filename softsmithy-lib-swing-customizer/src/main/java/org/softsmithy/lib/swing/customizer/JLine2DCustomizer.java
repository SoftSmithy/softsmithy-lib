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
 * JLine2DCustomizer.java
 *
 * Created on 8. November 2002, 22:19
 */

package org.softsmithy.lib.swing.customizer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Line2D;
import org.softsmithy.lib.swing.icon.ShapeIcon;
import org.softsmithy.lib.swing.icon.XIcon;


/**
 *
 * @author  puce
 */
public class JLine2DCustomizer extends JXIconCustomizer {
  
  /** Holds value of property orientation. */
  private Line2DOrientation orientation;
  
  private final Line2DIcon lineIcon = new Line2DIcon();
  
  /** Creates a new instance of JLine2DCustomizer */
  public JLine2DCustomizer() {
    setOrientation(Line2DOrientation.HORIZONTAL);
  }
  
  /** Getter for property line2D.
   * @return Value of property line2D.
   *
   */
  public Line2D getLine2D() {
    return lineIcon.getLine2D();
  }
  
  /** Setter for property line2D.
   * @param line2D New value of property line2D.
   *
   */
  public void setLine2D(Line2D line2D) {
    lineIcon.setShape(line2D);
    setXIcon(lineIcon);
    revalidate(); // seems to be necessary in some cases
  }
  
  /** Setter for property icon.
   * @param icon New value of property icon.
   *
   *
   */
  @Override
  public void setXIcon(XIcon icon){
    if (! (icon instanceof Line2DIcon)){
      throw new IllegalArgumentException("icon must be a Line2DIcon");
    }
    super.setXIcon((Line2DIcon) icon);
    //getXIconLabel().revalidate();    
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
  }
  
  /** Getter for property thickness.
   * @return Value of property thickness.
   *
   */
  public float getThickness() {
    return ((BasicStroke) lineIcon.getStroke()).getLineWidth();
  }
  
  /** Setter for property thickness.
   * @param thickness New value of property thickness.
   *
   */
  public void setThickness(float thickness) {
    BasicStroke stroke = (BasicStroke) lineIcon.getStroke();
    lineIcon.setStroke(new BasicStroke(thickness, stroke.getEndCap(), stroke.getLineJoin(),
    stroke.getMiterLimit(), stroke.getDashArray(),  stroke.getDashPhase()));
    setXIcon(lineIcon);
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
  
  private static class Line2DIcon extends ShapeIcon{
    @Override
    public void setShape(Shape shape){
      if (! (shape instanceof Line2D)){
        throw new IllegalArgumentException("shape must be an instance of Line2D");
      }
      super.setShape(shape);
    }
    
    public Line2D getLine2D(){
      return (Line2D) getShape();
    }
    
  }
}
