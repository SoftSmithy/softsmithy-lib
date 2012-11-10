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
 * ReflectiveCustomizerAction.java
 *
 * Created on 2. Juni 2004, 02:09
 */
package org.softsmithy.lib.swing.customizer.action;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import org.softsmithy.lib.swing.action.ReflectiveXAction;
import org.softsmithy.lib.swing.action.StandardActionFactory;

/**
 *
 * @author puce
 */
public class ReflectiveCustomizerAction extends ReflectiveXAction implements CustomizerAction {

    /**
     * Holds value of property neededCustomizableProperties.
     */
    private Set<String> neededCustomizableProperties;

    /**
     * Creates a new instance of ReflectiveCustomizerAction
     */
    public ReflectiveCustomizerAction(Object target, String methodName) throws NoSuchMethodException {
        this(target, methodName, Collections.<String>emptySet());
    }

    public ReflectiveCustomizerAction(Object target, StandardActionFactory saf, Locale locale) throws NoSuchMethodException {
        this(target, saf, locale, Collections.<String>emptySet());
    }

    public ReflectiveCustomizerAction(Object target, StandardActionFactory saf, Locale locale, Set<String> neededCustomizableProperties) throws NoSuchMethodException {
        this(target, saf.toString(), neededCustomizableProperties);
        saf.configureXAction(this, locale);
    }

    /**
     * Creates a new instance of ReflectiveCustomizerAction
     */
    public ReflectiveCustomizerAction(Object target, String methodName, Set<String> neededCustomizableProperties) throws NoSuchMethodException {
        super(target, methodName);
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
