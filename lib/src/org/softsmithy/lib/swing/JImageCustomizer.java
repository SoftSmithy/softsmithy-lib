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
import javax.swing.*;
import org.softsmithy.lib.awt.*;
import org.softsmithy.lib.awt.event.*;

/**
 *
 * @author  puce
 */
public class JImageCustomizer extends JIconCustomizer {
  
  /** Holds value of property imageSrc. */
  private Image image = null;
  
  private final ComponentLayoutListener imageUpdateListener = new ImageUpdateListener();
  
  /** Creates a new instance of JImageCustomizer */
  public JImageCustomizer() {
  }
  
  public JImageCustomizer(Image image){
    setImage(image);
  }
  
  /** Getter for property imageSrc.
   * @return Value of property imageSrc.
   *
   */
  public Image getImage() {
    return this.image;
  }
  
  /** Setter for property imageSrc.
   * @param imageSrc New value of property imageSrc.
   *
   */
  public void setImage(Image image) {
    this.image = image;
    adjustIcon();
  }
  
  
  public void adjustIcon(){
    Rectangle innerArea = SwingUtilities.calculateInnerArea(this, null);
    Icon icon;
    if (image != null){
      icon =  new ImageIcon(image.getScaledInstance(innerArea.width,
      innerArea.height, Image.SCALE_DEFAULT));
    } else {
      icon = new ImageIcon();
    }
    ((JLabel) getComponent()).setIcon(icon);
  }
  
  public void addNotify() {
    super.addNotify();
    getParentCustomizerPane().addComponentLayoutListener(this, imageUpdateListener);
  }
  
  public void removeNotify() {
    getParentCustomizerPane().removeComponentLayoutListener(this, imageUpdateListener);
    super.removeNotify();
  }
  
  private class ImageUpdateListener implements ComponentLayoutListener{
    
    public void componentLayouted(ComponentLayoutEvent e) {
      adjustIcon();
    }
    
  }
  
}
