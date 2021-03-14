/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class DoubleParser extends AbstractNumberParser<Double> {

    private static final Logger LOG = LoggerFactory.getLogger(DoubleParser.class);

    public DoubleParser() {
        this(NumberFormat.getInstance());
    }

    public DoubleParser(NumberFormat numberFormat) {
        super(numberFormat);
    }

    @Override
    protected Double parseString(String text) throws ParseException {
        try {
            return parseNumber(text).doubleValue();
        } catch (ParseException | RuntimeException e) {
            LOG.debug("First number conversion failed: {}", text);
            try {
                return Double.parseDouble(text);
            } catch (NumberFormatException nfe) {
                ParseException pe = new ParseException(text, -1);
                pe.initCause(nfe);
                pe.addSuppressed(e);
                throw pe;
            }
        }
    }

}
