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
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author puce
 */
public class IntegerParserTest {

    private NumberFormat numberFormat;
    private IntegerParser integerParser;

    public IntegerParserTest() {
    }

    @Before
    public void setUp() {
        numberFormat = NumberFormat.getInstance();
        integerParser = new IntegerParser(numberFormat);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of parse method, of class IntegerParser.
     */
    @Test
    public void testParse() throws Exception {
        System.out.println("parse");
        assertEquals(1, integerParser.parse("1").intValue());
        assertEquals(1, integerParser.parse("+1").intValue());
        assertEquals(-1, integerParser.parse("-1").intValue());
        assertEquals(1, integerParser.parse("01").intValue());
        assertEquals(1, integerParser.parse("+01").intValue());
        assertEquals(-1, integerParser.parse("-01").intValue());
        String maxValString = numberFormat.format(Integer.MAX_VALUE);
        System.out.println(maxValString);
        assertEquals(Integer.MAX_VALUE, integerParser.parse(maxValString).intValue());
    }
}