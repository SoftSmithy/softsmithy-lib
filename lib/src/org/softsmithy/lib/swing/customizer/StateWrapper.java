package org.softsmithy.lib.swing.customizer;

import java.awt.event.*;
import org.softsmithy.lib.swing.*;

public class StateWrapper implements State {
  
  /** Holds value of property state. */
  private State state;
  
  public StateWrapper(State state){
    this.state = state;
  }
  
  public void focusGained(FocusEvent e){
    state.focusGained(e);
  }
  public void focusLost(FocusEvent e){
    state.focusLost(e);
  }
  public void mousePressed(MouseEvent e){
    state.mousePressed(e);
  }
  public void mouseReleased(MouseEvent e){
    state.mouseReleased(e);
  }
  public void mouseClicked(MouseEvent e){
    state.mouseClicked(e);
  }
  public void mouseExited(MouseEvent e){
    state.mouseExited(e);
  }
  public void mouseEntered(MouseEvent e){
    state.mouseEntered(e);
  }
  public void mouseMoved(MouseEvent e){
    state.mouseMoved(e);
  }
  public void mouseDragged(MouseEvent e){
    state.mouseDragged(e);
  }
  public  void configureCustomizer(){
    state.configureCustomizer();
  }
   public  void unconfigureCustomizer(){
    state.unconfigureCustomizer();
  }
  public  void applyBorder(){
    state.applyBorder();
  }
  public  void applyCursor(){
    state.applyCursor();
  }
  public JCustomizer getCustomizer(){
    return state.getCustomizer();
  }
  
  /** Getter for property state.
   * @return Value of property state.
   *
   */
  public State getState() {
    return this.state;
  }
  
  
}