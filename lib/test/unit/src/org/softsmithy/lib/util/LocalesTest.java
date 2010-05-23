/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.lib.util;

import java.util.Locale;
import junit.framework.TestCase;

/**
 *
 * @author puce
 */
public class LocalesTest extends TestCase {

    public LocalesTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of valueOf method, of class Locales.
     */
    public void testValueOf() {
        System.out.println("valueOf");

        assertEquals(new Locale("de"), Locales.valueOf("de"));
        assertEquals(new Locale("de"), Locales.valueOf(
                new Locale("de").toString()));
        assertEquals(new Locale("de", "CH"), Locales.valueOf("de_CH"));
        assertEquals(new Locale("de", "CH"), Locales.valueOf(new Locale("de",
                "CH").toString()));
        assertEquals(new Locale("", "CH"), Locales.valueOf("_CH"));
        assertEquals(new Locale("", "CH"), Locales.valueOf(new Locale("", "CH").
                toString()));

        assertEquals(new Locale("es"), Locales.valueOf("es"));
        assertEquals(new Locale("es"), Locales.valueOf(
                new Locale("es").toString()));
        assertEquals(new Locale("es", "ES"), Locales.valueOf("es_ES"));
        assertEquals(new Locale("es", "ES"), Locales.valueOf(new Locale("es",
                "ES").toString()));
        assertEquals(new Locale("es", "ES", "Traditional"), Locales.valueOf(
                "es_ES_Traditional"));
        assertEquals(new Locale("es", "ES", "Traditional"), Locales.valueOf(new Locale(
                "es", "ES", "Traditional").toString()));
        assertEquals(new Locale("es", "ES", "Traditional_WIN"), Locales.valueOf(
                "es_ES_Traditional_WIN"));
        assertEquals(new Locale("es", "ES", "Traditional_WIN"), Locales.valueOf(new Locale(
                "es", "ES", "Traditional_WIN").toString()));
        assertEquals(new Locale("", "ES"), Locales.valueOf("_ES"));
        assertEquals(new Locale("", "ES"), Locales.valueOf(new Locale("", "ES").
                toString()));
        assertEquals(new Locale("es", "", "Traditional"), Locales.valueOf(
                "es__Traditional"));
        assertEquals(new Locale("es", "", "Traditional"), Locales.valueOf(new Locale(
                "es", "", "Traditional").toString()));
        assertEquals(new Locale("es", "", "Traditional_WIN"), Locales.valueOf(
                "es__Traditional_WIN"));
        assertEquals(new Locale("es", "", "Traditional_WIN"), Locales.valueOf(new Locale(
                "es", "", "Traditional_WIN").toString()));

    }
}