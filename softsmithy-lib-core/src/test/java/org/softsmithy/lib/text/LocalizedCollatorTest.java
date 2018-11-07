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

import java.text.Collator;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.softsmithy.lib.util.CountryCode;

/**
 *
 * @author puce
 */
public class LocalizedCollatorTest {

    private static final Locale LOCALE = Locale.GERMAN;
    private static final CountryCode TEST_COUNTRY_CODE = CountryCode.FRANCE;
    private static final Locale TEST_LOCALE = Locale.FRENCH;

    /**
     * Test of getLocale method, of class LocalizedCollator.
     */
    @Test
    public void testGetLocale() {
        System.out.println("getLocale");
        LocalizedCollator instance = new LocalizedCollator(LOCALE);
        Locale expResult = LOCALE;
        Locale result = instance.getLocale();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCollator method, of class LocalizedCollator.
     */
    @Test
    public void testGetCollator() {
        System.out.println("getCollator");
        LocalizedCollator instance = new LocalizedCollator(LOCALE);
        Collator expResult = Collator.getInstance(LOCALE);
        Collator result = instance.getCollator();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCollationKey method, of class LocalizedCollator.
     */
    @Test
    public void testGetCollationKey_GenericType() {
        System.out.println("getCollationKey");
        CountryCode source = TEST_COUNTRY_CODE;
        LocalizedCollator instance = new LocalizedCollator(LOCALE);
        GenericCollationKey<CountryCode> result = instance.getCollationKey(source);
        assertEquals(source, result.getSource());
        assertEquals(Collator.getInstance(LOCALE).getCollationKey(TEST_COUNTRY_CODE.getDisplayString(LOCALE)), result.getCollationKey());
    }

    /**
     * Test of getCollationKey method, of class LocalizedCollator.
     */
    @Test
    public void testGetCollationKey_GenericType_Localizer() {
        System.out.println("getCollationKey");
        Locale source = TEST_LOCALE;
        LocalizedCollator instance = new LocalizedCollator(LOCALE);
        GenericCollationKey<Locale> result =
                instance.getCollationKey(source, LocaleLocalizer.LANGUAGE);
        assertEquals(source, result.getSource());
        assertEquals(Collator.getInstance(LOCALE).getCollationKey(LocaleLocalizer.LANGUAGE.getDisplayString(source, LOCALE)), result.getCollationKey());
    }

}
