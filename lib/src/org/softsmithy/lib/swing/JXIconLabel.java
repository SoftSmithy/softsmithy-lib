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
 * JXLabel.java
 *
 * Created on 30. Juli 2003, 22:03
 */

package org.softsmithy.lib.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.softsmithy.lib.swing.icon.*;

/**
 *
 * @author  puce
 */
public class JXIconLabel extends JXLabel {
  
  //private ShapeIcon shapeIcon = new ShapeIcon();
  private XIcon originalIcon;
  
  /**
   * Holds value of property zoomingStrategy.
   */
  private ZoomingStrategy zoomingStrategy = new PercentageZooming();
  
  public JXIconLabel(){
    super();
    init(null);
  }
  
  public JXIconLabel(XIcon icon){
    super(icon);
    init(icon);
    //shapeIcon = (ShapeIcon) getIcon();
  }
  
  public JXIconLabel(XIcon image, int horizontalAlignment){
    super(image, horizontalAlignment);
    init(image);
    //shapeIcon = (ShapeIcon) getIcon();
  }
  
  public JXIconLabel(String text){
    super(text);
    init(null);
  }
  
  public JXIconLabel(String text, XIcon icon, int horizontalAlignment){
    super(text, icon, horizontalAlignment);
    init(icon);
    //shapeIcon = (ShapeIcon) getIcon();
  }
  
  public JXIconLabel(String text, int horizontalAlignment){
    super(text, horizontalAlignment);
    init(null);
  }
  
  private void init(XIcon icon){
    originalIcon = icon;
    addComponentListener(new SizeListener());
    setMinimumSize(new Dimension(1, 1)); // TODO: Check: Ok?
  }
  
  public void setIcon(Icon icon){
    if (icon != null && ! (icon instanceof XIcon)){
      throw new IllegalArgumentException("icon must be an instance of XIcon or null!");
    }
    originalIcon = icon instanceof XIcon ? (XIcon) icon : null;
    //setIconOnly(originalIcon);
    resizeIcon();
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
  
  private void resizeIcon(){
    Rectangle innerArea = SwingUtilities.calculateInnerArea(this, null);
    if (originalIcon != null && innerArea.width > 0 && innerArea.height > 0){
      //      Dimension bounds = calculateIconBounds(innerArea.width, innerArea.height);
      Dimension bounds = getZoomingStrategy().calculateDimension(originalIcon.getIconWidth(),
      originalIcon.getIconHeight(), innerArea.width, innerArea.height);
      setIconOnly(originalIcon.getScaledInstance(bounds.width, bounds.height));
      
    }
  }
  
  //  private Dimension calculateIconBounds(int width, int height){
  //    Dimension bounds;
  //    if (isRespectingAspectRatio()){
  //      if (((double) width) / originalIcon.getIconWidth() <= ((double) height) / originalIcon.getIconHeight()){
  //        bounds = new Dimension(width, -1); //(int) Math.round(((double) width * height) / getIcon().getIconWidth()));
  //      } else {
  //        bounds = new Dimension(-1, height); //(int) Math.round(((double) width * height) / getIcon().getIconHeight()), height);
  //      }
  //    } else {
  //      bounds = new Dimension(width, height);
  //    }
  //    return bounds;
  //  }
  
  private void setIconOnly(XIcon icon){
    super.setIcon(icon);
  }
  
  /**
   * Getter for property zoomingStrategy.
   * @return Value of property zoomingStrategy.
   */
  public ZoomingStrategy getZoomingStrategy() {
    return this.zoomingStrategy;
  }
  
  /**
   * Setter for property zoomingStrategy.
   * @param zoomingStrategy New value of property zoomingStrategy.
   */
  public void setZoomingStrategy(ZoomingStrategy zoomingStrategy) {
    this.zoomingStrategy = zoomingStrategy;
  }
  
  private static class SizeListener extends ComponentAdapter{
    
    public void componentResized(ComponentEvent e) {
      //System.out.println("Component resized!");
      ((JXIconLabel) e.getComponent()).resizeIcon();
    }
    
  }
  
  
  
}
