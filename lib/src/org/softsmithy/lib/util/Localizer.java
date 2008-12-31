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
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.softsmithy.lib.util;

import java.util.Locale;

/**
 *
 * @author puce
 */
public interface Localizer<T> {

     /**
     * Returns a string that is appropriate for display to the user.
     * If possible, the string returned will be localized according to inLocale.
     * @param inLocale the string returned will be localized according to this value, if possible
     * @return a string that is appropriate for display to the user.
     */
    public String getDisplayString(T o, Locale inLocale);
}
