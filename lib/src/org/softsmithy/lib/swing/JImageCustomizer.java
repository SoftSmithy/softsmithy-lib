/*
 * JImageCustomizer.java
 *
 * Created on 12. September 2002, 15:01
 */

package puce.swing;

import java.awt.*;
import javax.swing.*;
import puce.awt.*;

/**
 *
 * @author  puce
 */
public class JImageCustomizer extends JCustomizer {
  
  //  private static final String HTML_START = "<html><body><img src='";
  //  private static final String HTML_WIDTH = "' width='";
  //  private static final String HTML_HEIGHT = "' height='";
  //  private static final String HTML_END = "'></body></html>";
  /** Holds value of property imageSrc. */
  private Image image = null;
  
  /** Creates a new instance of JImageCustomizer */
  public JImageCustomizer() {
    setComponent(new JLabel());
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
    adjustImage();
  }
  
  /** Setter for property fComponent.
   * @param fComponent New value of property fComponent.
   *
   */
  public void setComponent(JComponent component) {
    if (! (component instanceof JLabel)){
      throw new IllegalArgumentException("comp must be a JLabel");
    }
    JLabel label = (JLabel) component;
    label.setOpaque(false);
    super.setComponent(label);
    adjustImage();
  }
  
  private void adjustImage(){
    Rectangle innerArea = SwingUtilities.calculateInnerArea(this, null);
    Icon icon;
    if (image != null){
      icon =  new ImageIcon(image.getScaledInstance(innerArea.width, 
      innerArea.height, Image.SCALE_DEFAULT));
    } else {
      icon = new ImageIcon();
    }
    ((JLabel) getComponent()).setIcon(icon);
    //    ((JLabel) getComponent()).setText(HTML_START + imageSrc + HTML_WIDTH + innerArea.width +
    //    HTML_HEIGHT + innerArea.height + HTML_END);
  }
  
  public void reshapeRel(int dx, int dy, int dwidth, int dheight) {
    super.reshapeRel(dx, dy, dwidth, dheight);
    adjustImage();
  }
  
}
