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

import java.awt.*;
import java.awt.event.*;
import org.softsmithy.lib.swing.customizer.JCustomizer;

public class StateWrapper implements State {

    /**
     * Holds value of property state.
     */
    private final State state;

    public StateWrapper(State state) {
        this.state = state;
    }

    @Override
    public void focusGained(FocusEvent e) {
        state.focusGained(e);
    }

    @Override
    public void focusLost(FocusEvent e) {
        state.focusLost(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        state.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        state.mouseReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        state.mouseClicked(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        state.mouseExited(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        state.mouseEntered(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        state.mouseMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        state.mouseDragged(e);
    }

    @Override
    public void configureCustomizer() {
        state.configureCustomizer();
    }

    @Override
    public void unconfigureCustomizer() {
        state.unconfigureCustomizer();
    }

    @Override
    public void applyBorder() {
        state.applyBorder();
    }

    @Override
    public void applyCursor() {
        state.applyCursor();
    }

    @Override
    public JCustomizer getCustomizer() {
        return state.getCustomizer();
    }

    /**
     * Getter for property state.
     *
     * @return Value of property state.
     *
     */
    public State getState() {
        return this.state;
    }

    @Override
    public void resetBorder(Color borderColor) {
        state.resetBorder(borderColor);
    }

    /**
     * Getter for property active.
     *
     * @return Value of property active.
     *
     *
     */
    @Override
    public boolean isActive() {
        return state.isActive();
    }

    /**
     * Setter for property active.
     *
     * @param active New value of property active.
     *
     *
     */
    @Override
    public void setActive(boolean active) {
        state.setActive(active);
    }
}
