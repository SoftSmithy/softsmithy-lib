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
 * CoolButtonController.java
 *
 * Created on 6. Oktober 2002, 17:00
 */
package org.softsmithy.lib.swing;

import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author puce
 */
public class CoolButtonController extends MouseAdapter implements ItemListener {

    private final AbstractButton button;
    private boolean mouseIsOver = false;

    /**
     * Creates a new instance of CoolButtonController
     *
     * @param button the button to control
     */
    public CoolButtonController(AbstractButton button) { // TODO: should be public?
        this.button = button;
        button.setBorderPainted(false);
        // button.setFocusPainted(false);
    }

    /**
     * Invoked when the mouse enters a component.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        if (!button.isBorderPainted() && button.isEnabled()) {
            button.setBorderPainted(true);
        }
        mouseIsOver = true;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void mouseExited(MouseEvent e) {
        if (button.isBorderPainted() && !button.isSelected()) {
            button.setBorderPainted(false);
        }
        mouseIsOver = false;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            if (!mouseIsOver && button.isBorderPainted()) {
                button.setBorderPainted(false);
            }
        } else { // ItemEvent.SELECTED
            if (!mouseIsOver && !button.isBorderPainted() && button.isEnabled()) {
                button.setBorderPainted(true);
            }
        }
    }

    public static void addCoolButtonController(AbstractButton button) {
        CoolButtonController controller = new CoolButtonController(button);
        button.addMouseListener(controller);
        button.addItemListener(controller);
    }

    public static void removeCoolButtonController(AbstractButton button) {
        CoolButtonController controller = new CoolButtonController(button);
        button.removeMouseListener(controller);
        button.removeItemListener(controller);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode = 37 * hashCode + button.hashCode();
        return hashCode;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CoolButtonController)) {
            return false;
        }
        CoolButtonController controller = (CoolButtonController) obj;
        return (button == controller.button || (button != null && button.equals(controller.button))); // TODO: don't test for mouseIsOver!?
    }
}
