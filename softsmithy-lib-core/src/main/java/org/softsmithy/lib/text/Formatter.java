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
 * A Formatter allows to format objects.
 *
 * @param <T> the object type to format
 * @author puce
 */
public interface Formatter<T> {

    /**
     * Formats the given object to a {@link String}.
     *
     * @param t the object to format
     * @return the formatted String
     * @throws FormatException if the object could not be formatted
     */
    String format(T t) throws FormatException;

    /**
     * Formats the given object and appends it to the given {@link Appendable}.
     *
     * @param t          the object to format
     * @param appendable the Appendable to append the formatted object to
     * @return the modified Appendable
     * @throws FormatException if the object could not be formatted
     */
    Appendable format(T t, Appendable appendable) throws FormatException;
}
