/*
 * EditableStateManager.java
 *
 * Created on 5. September 2002, 15:58
 */

package puce.swing.customizer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import puce.swing.*;
import puce.swing.customizer.StateManager.*;

/**
 *
 * @author  puce
 */


public class EditableStateManager extends StateManager {
  
  private StateWrapper editableAwareState;
  private ActiveState editableState;
  
  /** Creates a new instance of EditableStateManager */
  public EditableStateManager(final JEditableCustomizer customizer){
    super(customizer);
    editableState = new ActiveState(customizer){
      private FocusListener focusListener = new FocusAdapter(){
        public void focusLost(FocusEvent e){
          focusLostNow(e);
        }
      };
      public void applyCursor(){
        customizer.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
      }
      public void configureCustomizer(){
        super.configureCustomizer();
        JTextComponent editor = customizer.getEditor();
        editor.setText(customizer.getText());
        customizer.getGlassPane().add(BorderLayout.CENTER, editor);
        if(!editor.hasFocus()){
          editor.requestFocus();
        }
        editor.addFocusListener(focusListener);
      }
      public void focusLostNow(FocusEvent e){
         super.focusLost(e);
      }
      public void unconfigureCustomizer(){
        super.unconfigureCustomizer();
        JTextComponent editor = customizer.getEditor();
        customizer.getGlassPane().remove(editor);
        customizer.setText(editor.getText());
        editor.removeFocusListener(focusListener);
      }
      public void focusLost(FocusEvent e){}
    };
  }
  
  public ActiveState getEditableState() {
    return editableState;
  }
  
  public void setStateEditable(){
    setState(editableState);
  }
  
  protected void setState(State state){
//    if (editableAwareState == null){
//      editableAwareState = new EditableAwareState(state);
//    } else {
//      editableAwareState.setState(state);
//    }
    super.setState(new EditableAwareState(state));
  }
  
  public static class EditableAwareState extends StateWrapper{
    
    public EditableAwareState(State state){
      super(state);
    }
    
    /** Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     */
    public void mouseClicked(MouseEvent e) {
      if (e.getClickCount() > 1){
        JCustomizerPane pane = (JCustomizerPane) getCustomizer().getParent();
        pane.getSelectionManager().singleSelect(getCustomizer(), e.getPoint());
        ((JEditableCustomizer) getCustomizer()).getEditableStateManager().setStateEditable();
      }
    }
  }
  
}
