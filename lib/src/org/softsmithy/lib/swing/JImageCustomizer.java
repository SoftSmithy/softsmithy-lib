/*
 * JImageCustomizer.java
 *
 * Created on 12. September 2002, 15:01
 */

package org.softsmithy.lib.swing;

import java.awt.*;
import javax.swing.*;
import org.softsmithy.lib.awt.*;

/**
 *
 * @author  puce
 */
public class JImageCustomizer extends JIconCustomizer {
  
  /** Holds value of property imageSrc. */
  private Image image = null;
  
  /** Creates a new instance of JImageCustomizer */
  public JImageCustomizer() {
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
  
  
  protected void adjustIcon(){
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
  
  
}
