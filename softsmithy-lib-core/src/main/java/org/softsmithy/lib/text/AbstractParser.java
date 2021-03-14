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
 * An abstract base class for {@link Parser} implementations.
 *
 * @param <T> the object type to parse
 * @author puce
 */
public abstract class AbstractParser<T> implements Parser<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public T parse(CharSequence text) throws ParseException {
        return parseString(text.toString());
    }

    /**
     * Parses the given text to an object.
     *
     * @param text the text to parse
     * @return the parsed object
     * @throws ParseException if the text could not be parsed
     */
    protected abstract T parseString(String text) throws ParseException;
}
