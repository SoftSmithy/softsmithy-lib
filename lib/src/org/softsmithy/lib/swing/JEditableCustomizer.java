/*
 * JEditableCustomizer.java
 *
 * Created on 5. September 2002, 12:15
 */

package puce.swing;

import javax.swing.*;
import javax.swing.text.*;
import puce.swing.customizer.*;

/**
 *
 * @author  puce
 */
public abstract class JEditableCustomizer extends JCustomizer {
  
  private JTextComponent editor = new JTextField();
  
  /** Creates a new instance of JEditableCustomizer */
  public JEditableCustomizer() {
    setStateManager(new EditableStateManager(this));
  }
  
  public void setEditor(JTextComponent editor){
    this.editor = editor;
  }
  
  public JTextComponent getEditor(){
    return editor;
  }
  
  public abstract String getText();
  public abstract void setText(String text);
  
  //  public void setComponent(JComponent comp){
  //    if (! (comp instanceof JTextComponent)){
  //      throw new IllegalArgumentException("comp must be a JTextComponent");
  //    }
  //    super.setComponent(comp);
  //  }
  
  public EditableStateManager getEditableStateManager(){
    return (EditableStateManager) getStateManager();
  }
  
  public String toString(){
    return getClass().getName() + "[" + getText() + "]";
  }
}
