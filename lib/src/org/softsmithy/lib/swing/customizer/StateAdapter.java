package org.softsmithy.lib.swing.customizer;

import java.awt.event.*;
import org.softsmithy.lib.swing.*;

public abstract class StateAdapter implements State {
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
  
}