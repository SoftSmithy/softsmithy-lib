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
 * EditableStateManager.java
 *
 * Created on 5. September 2002, 15:58
 */
package org.softsmithy.lib.swing.customizer.state;

import java.awt.Component;
import org.softsmithy.lib.swing.customizer.AbstractTextCustomizer;
import org.softsmithy.lib.swing.customizer.JCustomizer;

/**
 *
 * @author puce
 */
public class HiddenStateManager extends EditableStateManager {

//    private StateWrapper editableAwareState;
//    private State editableState;

    /**
     * Creates a new instance of EditableStateManager
     */
    public HiddenStateManager(final AbstractTextCustomizer customizer) {
        super(customizer);

    }

    @Override
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

        @Override
        public void configureCustomizer() {
            super.configureCustomizer();
            JCustomizer customizer = getCustomizer();
            Component comp = customizer.getComponent();
            comp.setVisible(false);
        }

        @Override
        public void unconfigureCustomizer() {
            JCustomizer customizer = getCustomizer();
            Component comp = customizer.getComponent();
            comp.setVisible(true);
            super.unconfigureCustomizer();
        }
    }
}
