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
 * CoolButtonController.java
 *
 * Created on 6. Oktober 2002, 17:00
 */

package org.softsmithy.lib.swing;

import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class CoolButtonController extends MouseAdapter {
  
  private AbstractButton button;
  
  /** Creates a new instance of CoolButtonController */
  public CoolButtonController(AbstractButton button) {
    this.button = button;
    button.setBorderPainted(false);
   // button.setFocusPainted(false);
  }
  
  /** Invoked when the mouse enters a component.
   *
   */
  public void mouseEntered(MouseEvent e) {
    if (! button.isBorderPainted() && button.isEnabled()){
      button.setBorderPainted(true);
    }
  }
  
  /** Invoked when the mouse exits a component.
   *
   */
  public void mouseExited(MouseEvent e) {
    if (button.isBorderPainted() && ! button.isSelected()){
      button.setBorderPainted(false);
    }
  }
  
}
