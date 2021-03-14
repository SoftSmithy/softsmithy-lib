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

import org.softsmithy.lib.text.AbstractParser;
import org.softsmithy.lib.text.Parser;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;

/**
 * A {@link Parser} for {@link TemporalAccessor}.
 *
 * @author puce
 */
public class TemporalAccessorParser<R> extends AbstractParser<R> {

    private final DateTimeFormatter dateTimeFormatter;
    private final TemporalQuery<R> query;

    /**
     * Creates a new instance of this class.
     *
     * @param dateTimeFormatter a {@link DateTimeFormatter}
     * @param query the query
     *
     */
    public TemporalAccessorParser(DateTimeFormatter dateTimeFormatter, TemporalQuery<R> query) {
        this.dateTimeFormatter = dateTimeFormatter;
        this.query = query;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public R parseString(String text) throws ParseException {
        try {
            return dateTimeFormatter.parse(text)
                    .query(query);
        } catch (DateTimeParseException ex) {
            ParseException parseException = new ParseException(ex.getMessage(), ex.getErrorIndex());
            parseException.initCause(ex);
            throw parseException;
        } catch (RuntimeException ex) {
            ParseException parseException = new ParseException(ex.getMessage(), 0);
            parseException.initCause(ex);
            throw parseException;
        }
    }
}
