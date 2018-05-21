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
 * Formatters.java
 *
 * Created on 25. September 2003, 19:24
 */
package org.softsmithy.lib.swing.text;

import javax.swing.text.InternationalFormatter;
import org.softsmithy.lib.util.Comparables;

/**
 *
 * @author puce
 */
public class Formatters {

    /**
     * Creates a new instance of Formatters
     */
    private Formatters() {
    }

    /**
     * TODO: still used?
     *
     * @param formatter
     * @param value
     * @return
     */
    public static Comparable valueToRange(InternationalFormatter formatter, Comparable value) {
        return Comparables.toRange(value, formatter.getMinimum(), formatter.getMaximum());
    }
}
