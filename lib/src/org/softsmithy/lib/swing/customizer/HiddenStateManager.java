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

/**
 *
 * @author  puce
 */
public class HiddenStateManager extends EditableStateManager {

    private StateWrapper editableAwareState;
    private State editableState;

    /** Creates a new instance of EditableStateManager */
    public HiddenStateManager(final AbstractTextCustomizer customizer) {
        super(customizer);

    }

    protected void setState(State state) {
        if (state instanceof ResizeState) {
            super.setState(new HiddenState(state));
        } else {
            super.setState(state);
        }
    }

    public static class HiddenState extends StateWrapper {

        public HiddenState(State state) {
            super(state);
        }

        public void configureCustomizer() {
            super.configureCustomizer();
            JCustomizer customizer = getCustomizer();
            Component comp = customizer.getComponent();
            comp.setVisible(false);
        }

        public void unconfigureCustomizer() {
            JCustomizer customizer = getCustomizer();
            Component comp = customizer.getComponent();
            comp.setVisible(true);
            super.unconfigureCustomizer();
        }
    }
}
