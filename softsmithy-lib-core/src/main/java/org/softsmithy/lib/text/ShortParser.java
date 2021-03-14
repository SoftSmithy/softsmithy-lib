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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.NumberFormat;
import java.text.ParseException;

/**
 * A {@link Parser} implementation to parse {@link Short}s.
 *
 * @author puce
 */
public class ShortParser extends AbstractNumberParser<Short> {

    private static final Logger LOG = LoggerFactory.getLogger(ShortParser.class);

    /**
     * Creates a new instance of this class.
     *
     * @param numberFormat the {@link NumberFormat} to use for parsing the texts.
     */
    public ShortParser(NumberFormat numberFormat) {
        super(numberFormat);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Short parseString(String text) throws ParseException {
        try {
            return parseNumber(text).shortValue();
        } catch (ParseException | RuntimeException e) {
            LOG.debug("First number conversion failed: {}", text);
            try {
                return Short.parseShort(text);
            } catch (NumberFormatException nfe) {
                ParseException pe = new ParseException(text, -1);
                pe.initCause(nfe);
                pe.addSuppressed(e);
                throw pe;
            }
        }
    }
}
