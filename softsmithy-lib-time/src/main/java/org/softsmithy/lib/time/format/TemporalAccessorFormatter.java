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
package org.softsmithy.lib.time.format;

import org.softsmithy.lib.text.FormatException;
import org.softsmithy.lib.text.Formatter;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

/**
 * A {@link Formatter} for {@link TemporalAccessor}.
 *
 * @author puce
 */
public class TemporalAccessorFormatter implements Formatter<TemporalAccessor> {

    private final DateTimeFormatter dateTimeFormatter;

    /**
     * Creates a new instance of this class. Uses
     * {@link DateTimeFormatter#ofLocalizedDate(java.time.format.FormatStyle)}
     * and {@link FormatStyle#FULL} by default.
     *
     * @see DateTimeFormatter#ofLocalizedDate(java.time.format.FormatStyle)
     * @see FormatStyle#FULL
     */
    public TemporalAccessorFormatter() {
        this(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }

    /**
     * Creates a new instance of this class.
     *
     * @param dateTimeFormatter a {@link DateTimeFormatter}
     */
    public TemporalAccessorFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String format(TemporalAccessor temporalAccessor) throws FormatException {
        try {
            return dateTimeFormatter.format(temporalAccessor);
        } catch (RuntimeException ex) {
            throw new FormatException(ex.getMessage(), ex);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Appendable format(TemporalAccessor temporalAccessor, Appendable appendable) throws FormatException {
        try {
            dateTimeFormatter.formatTo(temporalAccessor, appendable);
            return appendable;
        } catch (RuntimeException ex) {
            throw new FormatException(ex.getMessage(), ex);
        }
    }
}
