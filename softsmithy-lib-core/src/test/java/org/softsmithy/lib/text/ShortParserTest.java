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
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author puce
 */
public class ShortParserTest {
    
    private NumberFormat numberFormat;
    private ShortParser shortParser;
    
    public ShortParserTest() {
        // TODO: does this still work with SLF4J?
        Logger logger = Logger.getLogger(ShortParser.class.getPackage().getName());
        logger.setLevel(Level.ALL);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
    }
    
    @BeforeEach
    public void setUp() {
        numberFormat = NumberFormat.getInstance();
        shortParser = new ShortParser(numberFormat);
    }
    
    
    @Test
    public void testParse() throws ParseException{
        System.out.println("parse");
        assertEquals(1, shortParser.parse("1").shortValue());
        assertEquals(1, shortParser.parse("+1").shortValue());
        assertEquals(-1, shortParser.parse("-1").shortValue());
        assertEquals(1, shortParser.parse("01").shortValue());
        assertEquals(1, shortParser.parse("+01").shortValue());
        assertEquals(-1, shortParser.parse("-01").shortValue());
        String maxValString = numberFormat.format(Short.MAX_VALUE);
        System.out.println(maxValString);
        assertEquals(Short.MAX_VALUE, shortParser.parse(maxValString).shortValue());
    }
}
