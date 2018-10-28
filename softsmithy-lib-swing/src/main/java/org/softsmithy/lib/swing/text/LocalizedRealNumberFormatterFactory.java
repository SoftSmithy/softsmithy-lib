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
package org.softsmithy.lib.swing.text;

import java.util.*;

/**
 *
 * @author puce
 */
public class LocalizedRealNumberFormatterFactory extends RealNumberFormatterFactory {

    /**
     * Holds value of property locale.
     */
    private Locale locale;

    /**
     * Creates a new instance of WholeNumberFormatterFactory
     * @param formatter the formatter
     */
    public LocalizedRealNumberFormatterFactory(LocalizedRealNumberFormatter formatter) {
        this(formatter, Locale.getDefault());
    }

    public LocalizedRealNumberFormatterFactory(LocalizedRealNumberFormatter formatter, Locale locale) {
        super(formatter);
        setLocale(locale);
    }

    /**
     * Getter for property locale.
     *
     * @return Value of property locale.
     *
     */
    public Locale getLocale() {
        return this.locale;
    }

    /**
     * Setter for property locale.
     *
     * @param locale New value of property locale.
     *
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
        getLocalizedRealNumberFormatter().setLocale(locale);
    }

    public LocalizedRealNumberFormatter getLocalizedRealNumberFormatter() {
        return (LocalizedRealNumberFormatter) getRealNumberFormatter();
    }

}
