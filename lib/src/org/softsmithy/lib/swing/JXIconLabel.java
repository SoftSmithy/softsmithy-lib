/*
 * JXLabel.java
 *
 * Created on 30. Juli 2003, 22:03
 */

package org.softsmithy.lib.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 *
 * @author  puce
 */
public class JXIconLabel extends JLabel {
  
  //private ShapeIcon shapeIcon = new ShapeIcon();
  
  /** Holds value of property iconResizing. */
  private boolean iconResizing = false;
  private XIcon originalIcon = null;
  
  /** Holds value of property respectingAspectRatio. */
  private boolean respectingAspectRatio = true;
  
  public JXIconLabel(){
    super();
    init();
  }
  
  public JXIconLabel(XIcon image){
    super(image);
    init();
    //shapeIcon = (ShapeIcon) getIcon();
  }
  
  public JXIconLabel(XIcon image, int horizontalAlignment){
    super(image, horizontalAlignment);
    init();
    //shapeIcon = (ShapeIcon) getIcon();
  }
  
  public JXIconLabel(String text){
    super(text);
    init();
  }
  
  public JXIconLabel(String text, XIcon icon, int horizontalAlignment){
    super(text, icon, horizontalAlignment);
    init();
    //shapeIcon = (ShapeIcon) getIcon();
  }
  
  public JXIconLabel(String text, int horizontalAlignment){
    super(text, horizontalAlignment);
    init();
  }
  
  private void init(){
    addComponentListener(new SizeListener());
  }
  
  /** Getter for property iconResizing.
   * @return Value of property iconResizing.
   *
   */
  public boolean isIconResizing() {
    return this.iconResizing;
  }
  
  /** Setter for property iconResizing.
   * @param iconResizing New value of property iconResizing.
   *
   */
  public void setIconResizing(boolean iconResizing) { //better setIconScaling?
    this.iconResizing = iconResizing;
  }
  
  /** Getter for property respectionAspectRatio.
   * @return Value of property respectionAspectRatio.
   *
   */
  public boolean isRespectingAspectRatio() {
    return this.respectingAspectRatio;
  }
  
  /** Setter for property respectionAspectRatio.
   * @param respectionAspectRatio New value of property respectionAspectRatio.
   *
   */
  public void setRespectingAspectRatio(boolean respectingAspectRatio) {
    this.respectingAspectRatio = respectingAspectRatio;
  }
  
  public void setIcon(Icon icon){
    if (icon != null && ! (icon instanceof XIcon)){
      throw new IllegalArgumentException("icon must be an instance of XIcon or null!");
    }
    originalIcon = icon instanceof XIcon ? (XIcon) icon : null;
    setIconOnly(originalIcon);
  }
  
  /** Getter for property xIcon.
   * @return Value of property xIcon.
   *
   */
  public XIcon getXIcon() {
    return (XIcon) getIcon();
  }
  
  /** Setter for property xIcon.
   * @param xIcon New value of property xIcon.
   *
   */
  public void setXIcon(XIcon icon){
    setIcon(icon);
  }
  
  public void resizeIcon(){
    Rectangle innerArea = SwingUtilities.calculateInnerArea(this, null);
    XIcon icon = getXIcon();
    if (originalIcon != null && innerArea.width > 0 && innerArea.height > 0){
      Dimension bounds = calculateIconBounds(innerArea.width, innerArea.height);
      //icon.resize(bounds.width, bounds.height);
      setIconOnly(originalIcon.getScaledInstance(bounds.width, bounds.height));
    }
  }
  
  private Dimension calculateIconBounds(int width, int height){
    Dimension bounds;
    if (isRespectingAspectRatio()){
      if (((double) width) / originalIcon.getIconWidth() <= ((double) height) / originalIcon.getIconHeight()){
        bounds = new Dimension(width, -1); //(int) Math.round(((double) width * height) / getIcon().getIconWidth()));
      } else {
        bounds = new Dimension(-1, height); //(int) Math.round(((double) width * height) / getIcon().getIconHeight()), height);
      }
    } else {
      bounds = new Dimension(width, height);
    }
    return bounds;
  }
  
  private void setIconOnly(XIcon icon){
    super.setIcon(icon);
  }
  
  private static class SizeListener extends ComponentAdapter{
    
    public void componentResized(ComponentEvent e) {
      System.out.println("Component resized!");
      ((JXIconLabel) e.getComponent()).resizeIcon();
    }
    
  }
  
  
  
}
