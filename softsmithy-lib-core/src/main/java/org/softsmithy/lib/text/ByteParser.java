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

import java.text.NumberFormat;
import java.text.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author puce
 */
public class ByteParser extends AbstractNumberParser<Byte> {

    private static final Logger LOG = LoggerFactory.getLogger(ByteParser.class);

    public ByteParser(NumberFormat numberFormat) {
        super(numberFormat);
    }

    @Override
    public Byte parseString(String text) throws ParseException {
        try {
            return parseNumber(text).byteValue();
        } catch (ParseException | RuntimeException e) {
            LOG.debug("First number conversion failed: {}", text);
            try {
                return Byte.parseByte(text);
            } catch (NumberFormatException nfe) {
                ParseException pe = new ParseException(text, -1);
                pe.initCause(nfe);
                pe.addSuppressed(e);
                throw pe;
            }
        }
    }
}
