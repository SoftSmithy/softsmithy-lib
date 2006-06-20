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

import org.softsmithy.lib.swing.customizer.*;

import org.softsmithy.lib.swing.customizer.StateManager.*;



/**

 *

 * @author  puce

 */





public class EditableStateManager extends StateManager {

  

  //private StateWrapper editableAwareState;

  private final State editableState;

  private final EditableListener editableListener = new EditableListener();

  

  /** Creates a new instance of EditableStateManager */

  public EditableStateManager(AbstractTextCustomizer customizer){

    super(customizer);

    editableState = new EditableState(customizer);

  }

  

  public State getEditableState() {

    return editableState;

  }

  

  public void setStateEditable(){

    setState(getEditableState());

  }

  

  public AbstractTextCustomizer getTextCustomizer(){

    return (AbstractTextCustomizer) getCustomizer();

  }

  

  public void configureCustomizer() {

    super.configureCustomizer();

    getCustomizer().addActionListener(editableListener);

  }

  

  public void unconfigureCustomizer() {

    getCustomizer().removeActionListener(editableListener);

    super.unconfigureCustomizer();

  }

  

  //  protected void setState(State state){

  //    super.setState(new EditableAwareState(state));

  //  }

  

  private class EditableListener implements ActionListener{

    public void actionPerformed(ActionEvent ev){

      if (getTextCustomizer().isEditable()){

        setStateEditable();

      }

    }

  }

  

  private static class EditableState extends DefaultState{

    

    private Component component = null;

    

    public EditableState(AbstractTextCustomizer customizer){

      super(customizer);

    }

    

    //      private FocusListener focusListener = new FocusAdapter(){

    //        public void focusLost(FocusEvent e){

    //          focusLostNow(e);

    //        }

    //      };

    public void applyCursor(){

      getCustomizer().setCursor(null);//Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

    }

    public void configureCustomizer(){

      super.configureCustomizer();

      JTextComponent editor = ((AbstractTextCustomizer) getCustomizer()).getEditor();

      editor.setText(((AbstractTextCustomizer) getCustomizer()).getText());

      component = editor;

      if (((AbstractTextCustomizer) getCustomizer()).isEditorScrollable()){

        component = new JScrollPane(editor);

      }

      getCustomizer().getGlassPane().add(component, BorderLayout.CENTER);

      //System.out.println("Component added!!!!!!!!!!!!!");

      getCustomizer().getGlassPane().revalidate();

      if(!editor.hasFocus()){

        editor.requestFocus();

      }

      //        editor.addFocusListener(focusListener);

    }

    //      public void focusLostNow(FocusEvent e){

    //         super.focusLost(e);

    //      }

    public void unconfigureCustomizer(){

      JTextComponent editor = ((AbstractTextCustomizer) getCustomizer()).getEditor();

      getCustomizer().getGlassPane().remove(component);

      //System.out.println("Component removed!!!!!!!!!!!!!");

      ((AbstractTextCustomizer) getCustomizer()).setText(editor.getText());

      //        editor.removeFocusListener(focusListener);

      getCustomizer().repaint();

      super.unconfigureCustomizer();

    }

    //      public void focusLost(FocusEvent e){}

  }

  

  //  public static class EditableAwareState extends StateWrapper{

  //

  //    public EditableAwareState(State state){

  //      super(state);

  //    }

  //

  //    /** Invoked when the mouse button has been clicked (pressed

  //     * and released) on a component.

  //     */

  //    public void mouseClicked(MouseEvent e) {

  //      if (e.getClickCount() > 1){

  //        JCustomizerPane pane = (JCustomizerPane) getCustomizer().getParent();

  //        pane.getSelectionManager().singleSelect(getCustomizer(), e.getPoint());

  //        ((JTextCustomizer) getCustomizer()).getEditableStateManager().setStateEditable();

  //      }

  //    }

  //  }

  

}

