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
 * JImageCustomizer.java
 *
 * Created on 12. September 2002, 15:01
 */

package org.softsmithy.lib.swing;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class JShapeCustomizer extends JIconCustomizer {
  
  /** Holds value of property imageSrc. */
  private ShapeIcon shapeIcon = new ShapeIcon();
  
  /** Creates a new instance of JImageCustomizer */
  public JShapeCustomizer() {
    ((JLabel) getComponent()).setIcon(shapeIcon);
  }
  
  /** Getter for property imageSrc.
   * @return Value of property imageSrc.
   *
   */
  public Shape getShape() {
    return this.shapeIcon.getShape();
  }
  
  /** Setter for property imageSrc.
   * @param imageSrc New value of property imageSrc.
   *
   */
  public void setShape(Shape shape) {
    this.shapeIcon.setShape(shape);
  }
  
  
  protected void adjustIcon(){
    Rectangle innerArea = SwingUtilities.calculateInnerArea(this, null);
    Icon icon;
    if (shapeIcon != null && shapeIcon.getShape() != null){
      double sx = (double) innerArea.width / shapeIcon.getIconWidth();
      double sy = (double) innerArea.height / shapeIcon.getIconHeight();
      shapeIcon.transform(AffineTransform.getScaleInstance(sx, sy));
    }
    repaint();
    //((JLabel) getComponent()).setIcon(icon);
  }
  
  /** Getter for property filled.
   * @return Value of property filled.
   *
   */
  public boolean isFilled() {
    return this.shapeIcon.isFilled();
  }
  
  /** Setter for property filled.
   * @param filled New value of property filled.
   *
   */
  public void setFilled(boolean filled) {
    this.shapeIcon.setFilled(filled);
  }
  
  /** Getter for property stroke.
   * @return Value of property stroke.
   *
   */
  public Stroke getStroke() {
    return this.shapeIcon.getStroke();
  }
  
  /** Setter for property stroke.
   * @param stroke New value of property stroke.
   *
   */
  public void setStroke(Stroke stroke) {
    this.shapeIcon.setStroke(stroke);
  }
  
}
