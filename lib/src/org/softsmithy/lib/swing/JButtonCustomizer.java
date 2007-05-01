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
public class JButtonCustomizer extends AbstractTextCustomizer {
  
  /** Creates a new instance of JLabelCustomizer */
  public JButtonCustomizer() {
    this("");
  }
  
  public JButtonCustomizer(AbstractButton button){
    super(button);
  }
  
  public JButtonCustomizer(String text){
    this(new JButton(text));
    getButton().setBorder(BorderFactory.createRaisedBevelBorder());
  }

  
  public String getText() {
    AbstractButton button = getButton();
    return button != null ? button.getText() : "";
  }
  
  public void setText(String text) {
    AbstractButton button = getButton();
    if (button != null){
      button.setText(text);
    }
  }
  
  /** Setter for property fComponent.
   * @param fComponent New value of property fComponent.
   *
   */
  public void setComponent(JComponent component) {
    if (! (component instanceof AbstractButton)){
      throw new IllegalArgumentException("comp must be an AbstractButton");
    }
    super.setComponent(component);
  }
    protected void setHorizontalAlignmentOnly(HorizontalAlignment alignment) {
    AbstractButton button = getButton();
    if (button != null){
      button.setHorizontalAlignment(alignment.getSwingConstant());
    }
  }

   public HorizontalAlignment getHorizontalAlignment() {
    AbstractButton button = getButton();
    return button != null ? HorizontalAlignment.getHorizontalAlignment(button.getHorizontalAlignment()) : HorizontalAlignment.getHorizontalAlignment(new JButton().getHorizontalAlignment());
  }
 
  // TODO replace with generic getComponent!?
  private AbstractButton getButton(){
      return (AbstractButton) getComponent();
  }

}

