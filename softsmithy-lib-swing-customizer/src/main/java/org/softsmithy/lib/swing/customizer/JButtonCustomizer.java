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

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import org.softsmithy.lib.swing.HorizontalAlignment;

/**
 * A customizer to customize the text of an AbstractButton.
 *
 * @author puce
 */
public class JButtonCustomizer extends AbstractTextCustomizer {

    /**
     * Creates a new instance of this class by creating a new JButtton with an empty String.
     */
    public JButtonCustomizer() {
        this("");
    }

    /**
     * Creates a new text customizer fo the specified AbstractButton.
     *
     * @param button the AbstractButton to customize
     */
    public JButtonCustomizer(AbstractButton button) {
        super(button);
    }

    /**
     * Creates a new instance of this class by creating a new JButton with the specified String.
     *
     * @param text the initial text
     */
    public JButtonCustomizer(String text) {
        this(new JButton(text));
        getButton().setBorder(BorderFactory.createRaisedBevelBorder());
    }

    /**
     * Gets the text from the wrapped AbstractButton.
     *
     * @return the text from the wrapped AbstractButton
     */
    @Override
    public String getText() {
        AbstractButton button = getButton();
        return button != null ? button.getText() : "";
    }

    /**
     * Sets the text of the wrapped AbstractButton.
     *
     * @param text the text to be set
     */
    @Override
    public void setText(String text) {
        AbstractButton button = getButton();
        if (button != null) {
            button.setText(text);
        }
    }

    /**
     * Sets the AbstractButton to wrap.
     *
     * @param component the AbstractButton to wrap
     * @throws IllegalArgumentException if the component is not an AbstractButton
     */
    @Override
    public void setComponent(JComponent component) {
        if (!(component instanceof AbstractButton)) {
            throw new IllegalArgumentException("comp must be an AbstractButton");
        }
        super.setComponent(component);
    }

    /**
     * Sets the horizontal alignment of the text. This method should not fire any events!
     *
     * @param alignment the horizontal alignment of the text
     */
    @Override
    protected void setHorizontalAlignmentOnly(HorizontalAlignment alignment) {
        AbstractButton button = getButton();
        if (button != null) {
            button.setHorizontalAlignment(alignment.getSwingConstant());
        }
    }

    /**
     * Gets the horizontal alignment of the text.
     *
     * @return the horizontal alignment of the text
     */
    @Override
    public HorizontalAlignment getHorizontalAlignment() {
        AbstractButton button = getButton();
        return button != null ? HorizontalAlignment.getHorizontalAlignment(button.getHorizontalAlignment()) : HorizontalAlignment.getHorizontalAlignment(
                new JButton().getHorizontalAlignment());
    }

    // TODO replace with generic getComponent!?
    /**
     * Gets the wrapped AbstractButton.
     *
     * @return the wrapped AbstractButton
     */
    private AbstractButton getButton() {
        return (AbstractButton) getComponent();
    }
}
