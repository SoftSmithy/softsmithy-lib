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
 * LocaleLocalizer.java
 *
 * Created on 12. März 2003, 22:50
 */
package org.softsmithy.lib.text;

import java.util.Locale;


/**
 * This class specifies, which display String should be used with Locales 
 * (see strategy pattern).<br/>
 * <br/>
 * Note: Is this really a ennum? Better way??
 * @author puce
 */
public enum LocaleLocalizer implements Localizer<Locale> {

    /**
     * Selects the Locale.getDisplayName method.
     */
    NAME {

        public String getDisplayString(Locale locale, Locale inLocale) {
            return locale.getDisplayName(inLocale);
        }
    },
    /**
     * Selects the Locale.getDisplayLanguage method.
     */
    LANGUAGE {

        public String getDisplayString(Locale locale, Locale inLocale) {
            return locale.getDisplayLanguage(inLocale);
        }
    },
    /**
     * Selects the Locale.getDisplayCountry method.
     */
    COUNTRY {

        public String getDisplayString(Locale locale, Locale inLocale) {
            return locale.getDisplayCountry(inLocale);
        }
    },
    /**
     * Selects the Locale.getDisplayVariant method.
     */
    VARIANT {

        public String getDisplayString(Locale locale, Locale inLocale) {
            return locale.getDisplayVariant(inLocale);
        }
    };
    
    // Note: this redeclaration seems to be need by Ant!???
    public abstract String getDisplayString(Locale locale, Locale inLocale);
}
