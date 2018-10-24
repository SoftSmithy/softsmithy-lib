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
package org.softsmithy.lib.swing.event;

import java.awt.event.*;
import javax.swing.*;

/**
 * A MouseListener that listens for popup events. Adapted from <a
 * href="http://java.sun.com/docs/books/tutorial/uiswing/components/menu.html#popup">
 * Bringing Up a Popup Menu</a>
 *
 * @author Florian Brunner
 */
public class PopupListener extends MouseAdapter {

    private final JPopupMenu popupMenu;

    /**
     * Creates a new listener for popup events.
     *
     * @param popupMenu the popup menu to show when a popup event occurs
     */
    public PopupListener(JPopupMenu popupMenu) {
        this.popupMenu = popupMenu;
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e a MouseEvent object
     */
    @Override
    public void mousePressed(MouseEvent e) {
        maybeShowPopup(e);
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e a MouseEvent object
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
    }

    /**
     * Returns always true by default. May be overriden to provide more context sensitivity.
     *
     * @param e a MouseEvent object
     * @return true if the popup menu should be showed; false otherwise
     */
    protected boolean shouldShowPopup(MouseEvent e) {
        return true;
    }

    /**
     * Shows the popup menu if this mouse event is a popup trigger and shouldShowPopup return true.
     *
     * @param e a MouseEvent object
     */
    private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger() && shouldShowPopup(e)) {
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

}
