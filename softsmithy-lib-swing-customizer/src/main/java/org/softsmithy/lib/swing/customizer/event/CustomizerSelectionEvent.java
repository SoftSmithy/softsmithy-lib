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
 * CustomizerSelectionEvent.java
 *
 * Created on 20. September 2002, 19:14
 */
package org.softsmithy.lib.swing.customizer.event;

import org.softsmithy.lib.swing.customizer.JCustomizer;

import java.util.Collections;
import java.util.EventObject;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author puce
 */
public class CustomizerSelectionEvent extends EventObject {

    private static final long serialVersionUID = -9195333618803870350L;

    /**
     * Holds value of property selectedCustomizers.
     */
    private final Set<JCustomizer> selectedCustomizers;
    /**
     * Holds value of property activeCustomizer.
     */
    private final JCustomizer activeCustomizer;
    private final Set<String> commonCustomizableProperties;

    /**
     * Creates a new instance of CustomizerSelectionEvent
     */
    public CustomizerSelectionEvent(Object source,
            Set<JCustomizer> selectedCustomizers, JCustomizer activeCustomizer,
            Set<String> commonCustomizableProperties) {
        super(source);
        this.selectedCustomizers = Collections.unmodifiableSet(new LinkedHashSet<>(
                selectedCustomizers));
        this.activeCustomizer = activeCustomizer;
        this.commonCustomizableProperties = Collections.unmodifiableSet(new LinkedHashSet<>(
                commonCustomizableProperties));
    }

    /**
     * Getter for property selectedCustomizers.
     *
     * @return Value of property selectedCustomizers.
     *
     */
    public Set<JCustomizer> getSelectedCustomizers() {
        return this.selectedCustomizers;
    }

    /**
     * Getter for property activeCustomizer.
     *
     * @return Value of property activeCustomizer.
     *
     */
    public JCustomizer getActiveCustomizer() {
        return this.activeCustomizer;
    }

    public Set<String> getCommonCustomizableProperties() {
        return commonCustomizableProperties;
    }
}
