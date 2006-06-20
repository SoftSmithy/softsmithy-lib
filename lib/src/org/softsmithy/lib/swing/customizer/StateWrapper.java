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

  

  public void resetBorder(Color borderColor) {

    state.resetBorder(borderColor);

  }  

  

  /** Getter for property active.

   * @return Value of property active.

   *

   *

   */

  public boolean isActive() {

    return state.isActive();

  }

  

  /** Setter for property active.

   * @param active New value of property active.

   *

   *

   */

  public void setActive(boolean active) {

    state.setActive(active);

  }

  

}