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

 * JEditableCustomizer.java

 *

 * Created on 5. September 2002, 12:15

 */



package org.softsmithy.lib.swing.customizer;



import java.awt.*;

import javax.swing.*;

import javax.swing.text.*;

import org.softsmithy.lib.swing.*;

import org.softsmithy.lib.swing.customizer.*;



/**

 *

 * @author  puce

 */

public abstract class AbstractTextCustomizer extends JCustomizer {

  

  private JTextComponent editor = new JTextField();

  

  /** Holds value of property editorScrollable. */

  private boolean editorScrollable = false;

  

  /** Holds value of property editable. */

  private boolean editable;

  

  private boolean inited = false;

  //  private final StateManager stateManager = new StateManager(this);

  

  /** Creates a new instance of JEditableCustomizer */

  public AbstractTextCustomizer() {

    init();

  }

  

  public AbstractTextCustomizer(JComponent component){

    super(component);

    init();

  }

  

  public AbstractTextCustomizer(String text){

    init();

    setText(text);

  }

  

  private void init(){

    setEditable(true);

    setStateManager(new EditableStateManager(this));

    inited = true;

  }

  

  

  public void setEditor(JTextComponent editor){

    this.editor = editor;

    editor.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR)); // really needed???

  }

  

  public JTextComponent getEditor(){

    return editor;

  }

  

  public abstract String getText();

  public abstract void setText(String text);

  protected abstract void setHorizontalAlignmentOnly(HorizontalAlignment alignment);

  public abstract HorizontalAlignment getHorizontalAlignment();

  

  public void setHorizontalAlignment(HorizontalAlignment alignment){

    HorizontalAlignment oldValue = getHorizontalAlignment();

    if (! alignment.equals(oldValue)){

      setHorizontalAlignmentOnly(alignment);

      repaint();

      firePropertyChange("horizontalAlignment", oldValue, alignment);

    }

  }

  //  public void setComponent(JComponent comp){

  //    if (! (comp instanceof JTextComponent)){

  //      throw new IllegalArgumentException("comp must be a JTextComponent");

  //    }

  //    super.setComponent(comp);

  //  }

  

  protected EditableStateManager getEditableStateManager(){

    return (EditableStateManager) getStateManager();

  }

  

  protected void setEditableStateManager(EditableStateManager stateManager){

    setStateManager(stateManager);

  }

  

  public String toString(){

    return getClass().getName() + "[" + getText() + "]";

  }

  

  /** Getter for property editorScrollable.

   * @return Value of property editorScrollable.

   *

   */

  public boolean isEditorScrollable() {

    return this.editorScrollable;

  }

  

  /** Setter for property editorScrollable.

   * @param editorScrollable New value of property editorScrollable.

   *

   */

  public void setEditorScrollable(boolean editorScrollable) {

    this.editorScrollable = editorScrollable;

  }

  

  /** Getter for property editable.

   * @return Value of property editable.

   *

   */

  public boolean isEditable() {

    return this.editable;

  }

  

  /** Setter for property editable.

   * @param editable New value of property editable.

   *

   */

  public void setEditable(boolean editable) {

    this.editable = editable;

    //    if (editable){

    //      setStateManager(editableStateManager);

    //    } else {

    //      setStateManager(stateManager);

    //    }

  }

  

  protected void setStateManager(StateManager manager) {

    if (inited && ! (manager instanceof EditableStateManager)){

      throw new IllegalArgumentException("manager must be an instance of EditableStateManager!");

    }

    super.setStateManager(manager);

  }

  

}

