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

package org.softsmithy.lib.swing.customizer;

import java.awt.*;
import java.awt.event.*;

public abstract class AbstractState implements State {
  
  /** Holds value of property active. */
  private boolean active;
  
  /** Invoked when a mouse button is pressed on a component and then
   * dragged.  <code>MOUSE_DRAGGED</code> events will continue to be
   * delivered to the component where the drag originated until the
   * mouse button is released (regardless of whether the mouse position
   * is within the bounds of the component).
   * <p>
   * Due to platform-dependent Drag&Drop implementations,
   * <code>MOUSE_DRAGGED</code> events may not be delivered during a native
   * Drag&Drop operation.
   */
  public void mouseDragged(MouseEvent e) {
  }
  
  /** Invoked when the mouse enters a component.
   */
  public void mouseEntered(MouseEvent e) {
  }
  
  /** Invoked when the mouse exits a component.
   */
  public void mouseExited(MouseEvent e) {
  }
  
  /** Invoked when the mouse button has been moved on a component
   * (with no buttons down).
   */
  public void mouseMoved(MouseEvent e) {
  }
  
  /** Invoked when a mouse button has been pressed on a component.
   */
  public void mousePressed(MouseEvent e) {
  }
  
  /** Invoked when a mouse button has been released on a component.
   */
  public void mouseReleased(MouseEvent e) {
  }
  
  /** Invoked when a component gains the keyboard focus.
   */
  public void focusGained(FocusEvent e) {
  }
  
  /** Invoked when a component loses the keyboard focus.
   */
  public void focusLost(FocusEvent e) {
  }
  
  public void mouseClicked(MouseEvent e){}
  
  public void configureCustomizer(){}
  public void applyBorder(){}
  public void applyCursor(){}
  
  public void unconfigureCustomizer() {}
  
  public void resetBorder(Color borderColor) {
  }
  
  /** Getter for property active.
   * @return Value of property active.
   *
   */
  public boolean isActive() {
    return this.active;
  }  

  /** Setter for property active.
   * @param active New value of property active.
   *
   */
  public void setActive(boolean active) {
    this.active = active;
  }  
  
}