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
