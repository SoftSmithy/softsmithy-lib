/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
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

/**
 * This is a base class for customizers of components, which can display a text.
 * <br>
 * Double-click: inline edit support
 * @author puce
 */
public abstract class AbstractTextCustomizer extends JCustomizer {
    
    /**
     * The text component used as editor.
     */
    private JTextComponent editor = new JTextField();
    
    /**
     * Indicates if the editor should be scrollable.
     */
    private boolean editorScrollable = false;
    
    /**
     * Indicates if this customizer is editable.
     */
    private boolean editable;
    
    /**
     * True, if the instance is initialized, else false.
     */
    private boolean inited = false;
    //  private final StateManager stateManager = new StateManager(this);
    
    /**
     * Creates a new instance of this class.
     */
    public AbstractTextCustomizer() {
        init();
    }
    
    /**
     * Creates a new instance of this class.
     * @param component the component to wrap
     */
    public AbstractTextCustomizer(JComponent component){
        super(component);
        init();
    }
    
    
    /**
     * Initializes this customizer.
     */
    private void init(){
        setEditable(true);
        setStateManager(new EditableStateManager(this));
        inited = true;
    }
    
    
    /**
     * Sets the inline text editor of this text customizer.
     * @param editor the inline text editor of this text customizer
     */
    public void setEditor(JTextComponent editor){
        this.editor = editor;
        editor.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR)); // really needed???
    }
    
    /**
     * Gets the inline text editor of this text customizer.
     * @return the inline text editor of this text customizer
     */
    public JTextComponent getEditor(){
        return editor;
    }
    
    /**
     * Gets the text from the wrapped component.
     * @return the text from the wrapped component
     */
    public abstract String getText();
    
    /**
     * Sets the text of the wrapped component.
     * @param text the text to be set
     */
    public abstract void setText(String text);
    /**
     * Sets the horizontal alignment of the text. This method should not
     * fire any events!
     * @param alignment the horizontal alignment of the text
     */
    protected abstract void setHorizontalAlignmentOnly(HorizontalAlignment alignment);
    
    /**
     * Gets the horizontal alignment of the text.
     * @return the horizontal alignment of the text
     */
    public abstract HorizontalAlignment getHorizontalAlignment();
    
    /**
     * Sets the horizontal alignment of the text. <br/>
     * 
     * This methods calls the setHoizontalAlignmentOnly and fires a property changed 
     * event if the value changed.
     * @param alignment the horizontal alignment of the text
     */
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
    
    /**
     * Gets the editable state manager.
     * @return the editable state manager
     */
    protected EditableStateManager getEditableStateManager(){
        return (EditableStateManager) getStateManager();
    }
    
    /**
     * Sets the editable state manager.
     * @param stateManager the editable state manager
     */
    protected void setEditableStateManager(EditableStateManager stateManager){
        setStateManager(stateManager);
    }
    
    /**
     * Returns a string representation of this text customizer.
     * @return a string representation of this text customizer
     */
    @Override
    public String toString(){
        return getClass().getName() + "[" + getText() + "]";
    }
    
    /**
     * Indicates if the editor should be scrollable.
     * @return True, if the editor should be scrollable, else false
     */
    public boolean isEditorScrollable() {
        return this.editorScrollable;
    }
    
    /**
     * Sets if the editor should be scrollable.
     * @param editorScrollable True, if the editor should be scrollable, else false.
     */
    public void setEditorScrollable(boolean editorScrollable) {
        this.editorScrollable = editorScrollable;
    }
    
    /**
     * Indicates if this text customizer is editable.
     * @return True, if this text customizer is editable, else false.
     */
    public boolean isEditable() {
        return this.editable;
    }
    
    /**
     * Sets if this text customizer is editable.
     * @param editable True, if this text customizer is editable, else false
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
        //    if (editable){
        //      setStateManager(editableStateManager);
        //    } else {
        //      setStateManager(stateManager);
        //    }
    }
    
    /**
     * Sets the editable state mananger of this text customizer.
     * @param manager 
     * @throws IllegalArgumentException if the state manager is not an EditableStateManager
     */
    @Override
    protected void setStateManager(StateManager manager) {
        if (inited && ! (manager instanceof EditableStateManager)){
            throw new IllegalArgumentException("manager must be an instance of EditableStateManager!");
        }
        super.setStateManager(manager);
    }
    
}
