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
    if (! button.isBorderPainted()){
      button.setBorderPainted(true);
    }
  }
  
  /** Invoked when the mouse exits a component.
   *
   */
  public void mouseExited(MouseEvent e) {
    if (! button.isSelected()){
      button.setBorderPainted(false);
    }
  }
  
}
