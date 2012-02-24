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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author puce
 */
public class ShortParser extends AbstractNumberParser<Short> {

    private static final Logger LOGGER = Logger.getLogger(ShortParser.class.getName());

    public ShortParser(NumberFormat numberFormat) {
        super(numberFormat);
    }

    @Override
    public Short parseString(String text) throws ParseException {
        try {
            return parseNumber(text).shortValue();
        } catch (Exception e) {
            LOGGER.log(Level.FINE, "First number conversion failed: {0}", text);
            try {
                return Short.parseShort(text);
            } catch (NumberFormatException nfe) {
                ParseException pe = new ParseException(text, -1);
                pe.initCause(nfe);
                throw pe;
            }
        }
    }
}
