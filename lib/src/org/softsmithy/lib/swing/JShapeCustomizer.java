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
    //adjustIcon();
  }
  
  
  public void adjustIcon(){
    Rectangle innerArea = SwingUtilities.calculateInnerArea(this, null);
    Icon icon;
    if (shapeIcon != null && shapeIcon.getShape() != null){
      scaleIcon(innerArea);
      centerIcon(innerArea);
      
//      if (shapeIcon.getIconWidth() > innerArea.width){
//        System.out.println("width: shapeIcon > innerArea");
//      } else if (shapeIcon.getIconWidth() < innerArea.width){
//        System.out.println("width: shapeIcon < innerArea");
//      } else {
//        System.out.println("width: shapeIcon = innerArea");
//      }
//      if (shapeIcon.getIconHeight() > innerArea.height){
//        System.out.println("height: shapeIcon > innerArea");
//      } else if(shapeIcon.getIconHeight() < innerArea.height){
//        System.out.println("height: shapeIcon < innerArea");
//      } else {
//        System.out.println("height: shapeIcon = innerArea");
//      }
      repaint();
    }
    
    //((JLabel) getComponent()).setIcon(icon);
  }
  
  private void scaleIcon(Rectangle innerArea){
    double sx = (shapeIcon.getIconWidth() != 0.0) ? (double) innerArea.width / shapeIcon.getIconWidth() : 1.0;
    double sy = (shapeIcon.getIconHeight() != 0.0) ? (double) innerArea.height / shapeIcon.getIconHeight() : 1.0;
    shapeIcon.transform(AffineTransform.getScaleInstance(sx, sy));
  }
  
  private void centerIcon(Rectangle innerArea){
    Rectangle2D bounds = shapeIcon.getShape().getBounds2D();
    double dx = innerArea.getCenterX() - bounds.getCenterX();
    double dy = innerArea.getCenterY() - bounds.getCenterY();
    shapeIcon.transform(AffineTransform.getTranslateInstance(dx, dy));
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
