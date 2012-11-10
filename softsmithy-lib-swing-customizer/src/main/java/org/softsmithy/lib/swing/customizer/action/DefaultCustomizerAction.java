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
 * DefaultCustomizerAction.java
 *
 * Created on 23. Juni 2004, 18:47
 */
package org.softsmithy.lib.swing.customizer.action;

import java.util.Collections;
import java.util.Set;
import org.softsmithy.lib.swing.action.DefaultXAction;

/**
 *
 * @author puce
 */
public class DefaultCustomizerAction extends DefaultXAction implements CustomizerAction {

    /**
     * Holds value of property neededCustomizableProperties.
     */
    private Set<String> neededCustomizableProperties;

    /**
     * Creates a new instance of DefaultCustomizerAction
     */
    public DefaultCustomizerAction() {
        this(Collections.<String>emptySet());
    }

    public DefaultCustomizerAction(Set<String> neededCustomizableProperties) {
        this.neededCustomizableProperties = neededCustomizableProperties;
    }

    /**
     * Getter for property neededCustomizableProperties.
     *
     * @return Value of property neededCustomizableProperties.
     */
    @Override
    public Set<String> getNeededCustomizableProperties() {
        return this.neededCustomizableProperties;
    }

    /**
     * Setter for property neededCustomizableProperties.
     *
     * @param neededCustomizableProperties New value of property neededCustomizableProperties.
     */
    @Override
    public void setNeededCustomizableProperties(Set<String> neededCustomizableProperties) {
        this.neededCustomizableProperties = neededCustomizableProperties;
    }
}
