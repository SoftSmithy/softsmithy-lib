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

/**
 * An abstract base class for {@link Formatter} implementations.
 *
 * @param <T> the object type to format
 * @author puce
 */
public abstract class AbstractFormatter<T> implements Formatter<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public String format(T t) throws FormatException {
        StringBuilder sb = new StringBuilder();
        return format(t, sb)
                .toString();
    }
}
