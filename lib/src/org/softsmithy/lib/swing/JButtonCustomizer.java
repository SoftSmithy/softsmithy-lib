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

import javax.swing.*;
import org.softsmithy.lib.swing.customizer.*;

/**
 *
 * @author  puce
 */
public abstract class JButtonCustomizer extends AbstractTextCustomizer {
  
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
