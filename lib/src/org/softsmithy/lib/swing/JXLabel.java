/*
 * JXLabel.java
 *
 * Created on 30. Juli 2003, 22:03
 */

package org.softsmithy.lib.swing;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class JXLabel extends JLabel {
  
  private ShapeIcon shapeIcon = new ShapeIcon();
  
  public JXLabel(){
    super();
  }
  
  public JXLabel(Icon image){
    super(image);
    shapeIcon = (ShapeIcon) getIcon();
  }
  
  public JXLabel(Icon image, int horizontalAlignment){
    super(image, horizontalAlignment);
    shapeIcon = (ShapeIcon) getIcon();
  }
  
  public JXLabel(String text){
    super(text);
  }
  
  public JXLabel(String text, Icon icon, int horizontalAlignment){
    super(text, icon, horizontalAlignment);
    shapeIcon = (ShapeIcon) getIcon();
  }
  
  public JXLabel(String text, int horizontalAlignment){
    super(text, horizontalAlignment);
  }
  
  public JXLabel(Shape shape){
    super(new ShapeIcon(shape));
    shapeIcon = (ShapeIcon) getIcon();
  }
  
  public JXLabel(Shape shape, int horizontalAlignment){
    super(new ShapeIcon(shape), horizontalAlignment);
    shapeIcon = (ShapeIcon) getIcon();
  }
  
  
  public JXLabel(String text, Shape shape, int horizontalAlignment){
    super(text, new ShapeIcon(shape), horizontalAlignment);
    shapeIcon = (ShapeIcon) getIcon();
  }
 
  /** Getter for property shape.
   * @return Value of property shape.
   *
   */
  public Shape getShape() {
    return shapeIcon.getShape();
  }  
  
  /** Setter for property shape.
   * @param shape New value of property shape.
   *
   */
  public void setShape(Shape shape) {
    shapeIcon.setShape(shape);
    showShape();
  }
  
  /** Getter for property shapeFilled.
   * @return Value of property shapeFilled.
   *
   */
  public boolean isShapeFilled() {
    return shapeIcon.isFilled();
  }
  
  /** Setter for property shapeFilled.
   * @param shapeFilled New value of property shapeFilled.
   *
   */
  public void setShapeFilled(boolean shapeFilled) {
    shapeIcon.setFilled(shapeFilled);
  }
  
  /** Getter for property shapeStroke.
   * @return Value of property shapeStroke.
   *
   */
  public Stroke getShapeStroke() {
    return shapeIcon.getStroke();
  }
  
  /** Setter for property shapeStroke.
   * @param shapeStroke New value of property shapeStroke.
   *
   */
  public void setShapeStroke(Stroke shapeStroke) {
    shapeIcon.setStroke(shapeStroke);
  }
  
  public void setIcon(Icon icon) {
    if (icon instanceof ShapeIcon){
      shapeIcon = (ShapeIcon) icon;
    }
    super.setIcon(icon);
  }
  
  public boolean isShowingShape(){
    return getIcon() instanceof ShapeIcon;
  }
  
  public void showShape(){
    if (! isShowingShape()){
      setIcon(shapeIcon);
    } else {
      repaint();
    }
  }
  
}
