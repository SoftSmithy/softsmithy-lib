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
 * JLabelCustomizer.java
 *
 * Created on 5. September 2002, 17:11
 */

package org.softsmithy.lib.swing;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class JLabelCustomizer extends JTextCustomizer {
  
  
  /** Creates a new instance of JLabelCustomizer */
  public JLabelCustomizer() {
    this(new JLabel(""));
    initForDefaultLabel();
  }
  
  public JLabelCustomizer(JLabel label){
    super(label);
    //label.setOpaque(true);
  }
  
  public JLabelCustomizer(String text){
    this(new JLabel(text));
    initForDefaultLabel();
  }
  
  private void initForDefaultLabel(){
    setBackground(Color.WHITE);
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
  
  protected void setHorizontalAlignmentOnly(HorizontalAlignment alignment) {
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
