/*
 * EditableStateManager.java
 *
 * Created on 5. September 2002, 15:58
 */

package org.softsmithy.lib.swing.customizer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import org.softsmithy.lib.swing.*;

/**
 *
 * @author  puce
 */


public class HiddenStateManager extends EditableStateManager {
  
  private StateWrapper editableAwareState;
  private State editableState;
  
  /** Creates a new instance of EditableStateManager */
  public HiddenStateManager(final JTextCustomizer customizer){
    super(customizer);
    
  }
  
  protected void setState(State state){
    if (state instanceof ResizeState){
      super.setState(new HiddenState(state));
    } else {
      super.setState(state);
    }
  }
  
  public static class HiddenState extends StateWrapper{
    
    public HiddenState(State state){
      super(state);
    }
    
    public void configureCustomizer(){
      super.configureCustomizer();
      JCustomizer customizer = getCustomizer();
      Component comp = customizer.getComponent();
      comp.setVisible(false);
    }
    
    public void unconfigureCustomizer(){
      JCustomizer customizer = getCustomizer();
      Component comp = customizer.getComponent();
      comp.setVisible(true);
      super.unconfigureCustomizer();
    }
    
  }
  
}
