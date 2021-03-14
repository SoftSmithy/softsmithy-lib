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

import java.io.IOException;
import java.text.Format;

/**
 * Formats an object by using the specified {@link Format} (adaptor).
 *
 * @param <T> the object type to format
 * @author puce
 */
public class FormatFormatter<T> extends AbstractFormatter<T> {

    private final Format format;

    /**
     * Creates a new instance of this class.
     *
     * @param format the {@link Format} to use
     */
    public FormatFormatter(Format format) {
        this.format = format;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Appendable format(T t, Appendable appendable) throws FormatException {
        try {
            return appendable.append(format.format(t));
        } catch (IOException | RuntimeException ex) {
            throw new FormatException(ex.getMessage(), ex);
        }
    }
}
