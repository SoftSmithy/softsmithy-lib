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
public class JLabelCustomizer extends JTextCustomizer {
  
  
  /** Creates a new instance of JLabelCustomizer */
  public JLabelCustomizer() {
    JLabel label = new JLabel("");
    //label.setOpaque(true);
    setComponent(label);
  }
  
  public String getText() {
    JLabel label = (JLabel) getComponent();
    return label != null ? label.getText() : "";
  }
  
  public void setText(String text) {
    JLabel label = (JLabel) getComponent();
    if (label != null){
      label.setText(text);
    }
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
    super.setComponent(label);
  }
  
  public void setHorizontalAlignment(HorizontalAlignment alignment) {
    JLabel label = (JLabel) getComponent();
    if (label != null){
      label.setHorizontalAlignment(alignment.getSwingConstant());
    }
  }
  
  public HorizontalAlignment getHorizontalAlignment() {
    JLabel label = (JLabel) getComponent();
    return label != null ? HorizontalAlignment.getHorizontalAlignment(label.getHorizontalAlignment()) : HorizontalAlignment.getHorizontalAlignment(new JLabel().getHorizontalAlignment());
  }
  
}
