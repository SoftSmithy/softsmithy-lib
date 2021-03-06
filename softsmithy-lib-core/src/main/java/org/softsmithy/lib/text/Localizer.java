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
package org.softsmithy.lib.text;

import java.util.Locale;

/**
 * Specifies a (possible localizable) string representation of an object that is
 * appropriate to display to the user.
 * @param <T> the type of objects to display
 * @see Localizable
 * @see Localizables
 * @author puce
 */
public interface Localizer<T> {

    /**
     * Returns a string that is appropriate to display to the user.
     * If possible, the string returned will be localized according to inLocale.
     * @param o the object to display
     * @param inLocale the string returned will be localized according to this value, if possible
     * @return a string that is appropriate to display to the user.
     */
    public String getDisplayString(T o, Locale inLocale);
}
