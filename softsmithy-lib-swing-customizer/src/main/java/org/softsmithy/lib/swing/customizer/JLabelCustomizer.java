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
 * JLabelCustomizer.java
 *
 * Created on 5. September 2002, 17:11
 */

package org.softsmithy.lib.swing.customizer;

import java.awt.*;
import javax.swing.*;
import org.softsmithy.lib.swing.HorizontalAlignment;
import org.softsmithy.lib.swing.customizer.*;

/**
 * A customizer to customize the text of a JLabel.
 * @author puce
 */
public class JLabelCustomizer extends AbstractTextCustomizer {
    
    
    /**
     * Creates a new instance of this class by creating a new JLabel with an empty string.
     */
    public JLabelCustomizer() {
        this(new JLabel(""));
        initForDefaultLabel();
    }
    
    /**
     * Creates a new text customizer for the specified JLabel.
     * @param label the label to customize
     */
    public JLabelCustomizer(JLabel label){
        super(label);
        //label.setOpaque(true);
    }
    
    /**
     * Creates a new instance of this class by creating a new JLabel with the specified String.
     * @param text the initial text
     */
    public JLabelCustomizer(String text){
        this(new JLabel(text));
        initForDefaultLabel();
    }
    
    /**
     * Initializes the default JLabel.
     */
    private void initForDefaultLabel(){
        setBackground(Color.WHITE);
    }
    
    /**
     * Gets the text from the wrapped JLabel.
     * @return the text from the wrapped JLabel
     */
    public String getText() {
        JLabel label = (JLabel) getComponent();
        return label != null ? label.getText() : "";
    }
    
    /**
     * Sets the text of the wrapped JLabel.
     * @param text the text to be set
     */
    public void setText(String text) {
        JLabel label = (JLabel) getComponent();
        if (label != null){
            label.setText(text);
        }
    }
    
    /**
     * Sets the JLabel to wrap.
     * @param component the JLabel to wrap
     * @throws IllegalArgumentException if the component is not a JLabel
     */
    @Override
    public void setComponent(JComponent component) {
        if (! (component instanceof JLabel)){
            throw new IllegalArgumentException("comp must be a JLabel");
        }
        JLabel label = (JLabel) component;
        super.setComponent(label);
    }
    
    /**
     * Sets the horizontal alignment of the text. This method should not
     * fire any events!
     * @param alignment the horizontal alignment of the text
     */
    protected void setHorizontalAlignmentOnly(HorizontalAlignment alignment) {
        JLabel label = (JLabel) getComponent();
        if (label != null){
            label.setHorizontalAlignment(alignment.getSwingConstant());
        }
    }
    
    /**
     * Gets the horizontal alignment of the text.
     * @return the horizontal alignment of the text
     */
    public HorizontalAlignment getHorizontalAlignment() {
        JLabel label = (JLabel) getComponent();
        return label != null ? HorizontalAlignment.getHorizontalAlignment(label.getHorizontalAlignment()) : HorizontalAlignment.getHorizontalAlignment(new JLabel().getHorizontalAlignment());
    }
    
}
