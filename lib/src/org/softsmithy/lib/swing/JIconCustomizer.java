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
public abstract class JIconCustomizer extends JCustomizer {
  
  
  /** Creates a new instance of JImageCustomizer */
  public JIconCustomizer() {
    setComponent(new JLabel());
  }
  
  /** Getter for property imageSrc.
   * @return Value of property imageSrc.
   *
   */
  public Icon getIcon() {
    return ((JLabel) getComponent()).getIcon();
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
    adjustIcon();
  }
  
  protected abstract void adjustIcon();
  
  public void reshapeRel(int dx, int dy, int dwidth, int dheight) {
    super.reshapeRel(dx, dy, dwidth, dheight);
    adjustIcon();
  }
  
}
