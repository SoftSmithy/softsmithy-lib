/*
 * JLabelCustomizer.java
 *
 * Created on 5. September 2002, 17:11
 */

package puce.swing;

import javax.swing.*;

/**
 *
 * @author  puce
 */
public class JButtonCustomizer extends JTextCustomizer {
  
  /** Creates a new instance of JLabelCustomizer */
  public JButtonCustomizer() {
  }
  
  public String getText() {
    JButton button = (JButton) getComponent();
    return button != null ? button.getText() : "";
  }
  
  public void setText(String text) {
    JButton button = (JButton) getComponent();
    if (button != null){
      button.setText(text);
    }
  }
  
  /** Setter for property fComponent.
   * @param fComponent New value of property fComponent.
   *
   */
  public void setComponent(JComponent component) {
    if (! (component instanceof JButton)){
      throw new IllegalArgumentException("comp must be a JButton");
    }
    super.setComponent(component);
  }
  
}
