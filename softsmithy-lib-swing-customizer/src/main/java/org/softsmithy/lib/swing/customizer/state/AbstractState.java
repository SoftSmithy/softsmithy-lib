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
package org.softsmithy.lib.swing.customizer.state;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;

public abstract class AbstractState implements State {

    /**
     * Holds value of property active.
     */
    private boolean active;

    /**
     * Invoked when a mouse button is pressed on a component and then dragged.
     * <code>MOUSE_DRAGGED</code> events will continue to be delivered to the component where the drag originated until
     * the mouse button is released (regardless of whether the mouse position is within the bounds of the component).
     * <p> Due to platform-dependent Drag&amp;Drop implementations,
     * <code>MOUSE_DRAGGED</code> events may not be delivered during a native Drag&amp;Drop operation.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Invoked when the mouse enters a component.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Invoked when the mouse exits a component.
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Invoked when the mouse button has been moved on a component (with no buttons down).
     */
    @Override
    public void mouseMoved(MouseEvent e) {
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Invoked when a mouse button has been released on a component.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Invoked when a component gains the keyboard focus.
     */
    @Override
    public void focusGained(FocusEvent e) {
    }

    /**
     * Invoked when a component loses the keyboard focus.
     */
    @Override
    public void focusLost(FocusEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void configureCustomizer() {
    }

    @Override
    public void applyBorder() {
    }

    @Override
    public void applyCursor() {
    }

    @Override
    public void unconfigureCustomizer() {
    }

    @Override
    public void resetBorder(Color borderColor) {
    }

    /**
     * Getter for property active.
     *
     * @return Value of property active.
     *
     */
    @Override
    public boolean isActive() {
        return this.active;
    }

    /**
     * Setter for property active.
     *
     * @param active New value of property active.
     *
     */
    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}