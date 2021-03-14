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

import java.text.ParseException;

/**
 * A Parser allows to parse objects
 *
 * @param <T> the object type to parse
 * @author puce
 */
public interface Parser<T> {

    /**
     * Parses the given text to an object.
     *
     * @param text the text to parse
     * @return the parsed object
     * @throws ParseException if the text could not be parsed
     */
    T parse(CharSequence text) throws ParseException;
}
