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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import junit.framework.TestCase;

/**
 *
 * @author puce
 */
public class LocalizablesTest extends TestCase {

    private static final String[] STRINGS_ARRAY = new String[]{
        "Ändern", "Andere", "Aber", "Baden", "Auf",
        "ändern", "andere", "aber", "baden", "auf"};
    private static final String[] EXPECTED_STRINGS_ARRAY_DEFAULT = new String[]{
        "aber", "Aber", "andere", "Andere", "ändern", "Ändern", "auf", "Auf",
        "baden", "Baden"};
    private static final String[] EXPECTED_STRINGS_ARRAY_SECONDARY = new String[]{
        "Aber", "aber", "Andere", "andere", "Ändern", "ändern", "Auf", "auf",
        "Baden", "baden"};
    private static final List<String> STRINGS_LIST = Arrays.asList(
            STRINGS_ARRAY);
    private static final List<String> EXPECTED_STRINGS_LIST_DEFAULT = Arrays.
            asList(EXPECTED_STRINGS_ARRAY_DEFAULT);
    private static final List<String> EXPECTED_STRINGS_LIST_SECONDARY = Arrays.
            asList(EXPECTED_STRINGS_ARRAY_SECONDARY);
    private static final LocalizedString[] LOCALIZED_STRINGS_ARRAY = getLocalizedStringArray(
            STRINGS_ARRAY);
    private static final LocalizedString[] EXPECTED_LOCALIZED_STRINGS_ARRAY_DEFAULT = getLocalizedStringArray(
            EXPECTED_STRINGS_ARRAY_DEFAULT);
    private static final LocalizedString[] EXPECTED_LOCALIZED_STRINGS_ARRAY_SECONDARY = getLocalizedStringArray(
            EXPECTED_STRINGS_ARRAY_SECONDARY);
    private static final List<LocalizedString> LOCALIZED_STRINGS_LIST = Arrays.
            asList(LOCALIZED_STRINGS_ARRAY);
    private static final List<LocalizedString> EXPECTED_LOCALIZED_STRINGS_LIST_DEFAULT = Arrays.
            asList(EXPECTED_LOCALIZED_STRINGS_ARRAY_DEFAULT);
    private static final List<LocalizedString> EXPECTED_LOCALIZED_STRINGS_LIST_SECONDARY = Arrays.
            asList(EXPECTED_LOCALIZED_STRINGS_ARRAY_SECONDARY);
    private static final Locale LOCALE = Locale.GERMAN;

    public LocalizablesTest(String testName) {
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
     * Test of sort method, of class Localizables.
     */
    public void testSort_Locale_LocalizableArr() {
        System.out.println("sort");
        LocalizedString[] localizables = getLocalizedTestStringArray();
        Localizables.sort(LOCALE, localizables);
        assertTrue(Arrays.equals(EXPECTED_LOCALIZED_STRINGS_ARRAY_DEFAULT,
                localizables));
    }

    /**
     * Test of sort method, of class Localizables.
     */
    public void testSort_LocalizableCollator_LocalizableArr() {
        System.out.println("sort");
        LocalizedCollator collator = new LocalizedCollator(LOCALE);
        Localizable[] localizables = getLocalizedTestStringArray();
        Localizables.sort(collator, localizables);
        assertTrue(Arrays.equals(EXPECTED_LOCALIZED_STRINGS_ARRAY_DEFAULT,
                localizables));

        localizables = getLocalizedTestStringArray();
        collator.getCollator().setStrength(Collator.SECONDARY);
        Localizables.sort(collator, localizables);
        assertTrue(Arrays.equals(EXPECTED_LOCALIZED_STRINGS_ARRAY_SECONDARY,
                localizables));
    }

    /**
     * Test of sort method, of class Localizables.
     */
    public void testSort_LocalizableArr() {
        System.out.println("sort");
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(LOCALE);
        try {
            Localizable[] localizables = getLocalizedTestStringArray();
            Localizables.sort(localizables);
            assertTrue(Arrays.equals(EXPECTED_LOCALIZED_STRINGS_ARRAY_DEFAULT,
                    localizables));
        } finally {
            Locale.setDefault(origDefault);
        }
    }

    /**
     * Test of sort method, of class Localizables.
     */
    public void testSort_3args_1() {
        System.out.println("sort");
        String[] strings = getTestStringArray();
        Localizables.sort(new StringLocalizer(),
                LOCALE, strings);
        assertTrue(Arrays.equals(EXPECTED_STRINGS_ARRAY_DEFAULT,
                strings));
    }

    /**
     * Test of sort method, of class Localizables.
     */
    public void testSort_3args_2() {
        System.out.println("sort");
        LocalizedCollator collator = new LocalizedCollator(LOCALE);
        String[] strings = getTestStringArray();
        Localizables.sort(new StringLocalizer(), collator, strings);
        assertTrue(Arrays.equals(EXPECTED_STRINGS_ARRAY_DEFAULT,
                strings));

        strings = getTestStringArray();
        collator.getCollator().setStrength(Collator.SECONDARY);
        Localizables.sort(new StringLocalizer(), collator, strings);
        assertTrue(Arrays.equals(EXPECTED_STRINGS_ARRAY_SECONDARY,
                strings));
    }

    /**
     * Test of sort method, of class Localizables.
     */
    public void testSort_Localizer_GenericType() {
        System.out.println("sort");
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(LOCALE);
        try {
            String[] strings = getTestStringArray();
            Localizables.sort(new StringLocalizer(), strings);
            assertTrue(Arrays.equals(EXPECTED_STRINGS_ARRAY_DEFAULT,
                    strings));
        } finally {
            Locale.setDefault(origDefault);
        }
    }

    /**
     * Test of sort method, of class Localizables.
     */
    public void testSort_Locale_List() {
        System.out.println("sort");
        List<? extends Localizable> localizables = getLocalizedTestStringList();
        Localizables.sort(LOCALE, localizables);
        assertEquals(EXPECTED_LOCALIZED_STRINGS_LIST_DEFAULT, localizables);
    }

    /**
     * Test of sort method, of class Localizables.
     */
    public void testSort_LocalizableCollator_List() {
        System.out.println("sort");
        LocalizedCollator collator = new LocalizedCollator(LOCALE);
        List<? extends Localizable> localizables = getLocalizedTestStringList();
        Localizables.sort(collator, localizables);
        assertEquals(EXPECTED_LOCALIZED_STRINGS_LIST_DEFAULT, localizables);

        localizables = getLocalizedTestStringList();
        collator.getCollator().setStrength(Collator.SECONDARY);
        Localizables.sort(collator, localizables);
        assertEquals(EXPECTED_LOCALIZED_STRINGS_LIST_SECONDARY, localizables);
    }

    /**
     * Test of sort method, of class Localizables.
     */
    public void testSort_List() {
        System.out.println("sort");
        Locale origDefault = Locale.getDefault();
        Locale.setDefault(LOCALE);
        try {
            List<? extends Localizable> localizables =
                    getLocalizedTestStringList();
            Localizables.sort(localizables);
            assertEquals(EXPECTED_LOCALIZED_STRINGS_LIST_DEFAULT, localizables);
        } finally {
            Locale.setDefault(origDefault);
        }
    }

    /**
     * Test of sort method, of class Localizables.
     */
    public void testSort_3args_3() {
        System.out.println("sort");
        List<String> strings = getTestStringList();
        Localizables.sort(new StringLocalizer(), LOCALE, strings);
        assertEquals(EXPECTED_STRINGS_LIST_DEFAULT, strings);
    }

    /**
     * Test of sort method, of class Localizables.
     */
    public void testSort_3args_4() {
        System.out.println("sort");
        LocalizedCollator collator = new LocalizedCollator(LOCALE);
        List<String> strings = getTestStringList();
        Localizables.sort(new StringLocalizer(), collator, strings);
        assertEquals(EXPECTED_STRINGS_LIST_DEFAULT, strings);

        strings = getTestStringList();
        collator.getCollator().setStrength(Collator.SECONDARY);
        Localizables.sort(new StringLocalizer(), collator, strings);
        assertEquals(EXPECTED_STRINGS_LIST_SECONDARY, strings);
    }

    /**
     * Test of sort method, of class Localizables.
     */
    public void testSort_Localizer_List() {
        System.out.println("sort");
        List<String> strings = getTestStringList();
        Localizables.sort(new StringLocalizer(), strings);
        assertEquals(EXPECTED_STRINGS_LIST_DEFAULT, strings);
    }

    private static LocalizedString[] getLocalizedStringArray(
            String... localizedStrings) {
        LocalizedString[] localizedStringArray =
                new LocalizedString[localizedStrings.length];
        for (int i = 0; i < localizedStrings.length; i++) {
            localizedStringArray[i] = new LocalizedString(localizedStrings[i]);
        }
        return localizedStringArray;
    }

    private static String[] getTestStringArray() {
        return Arrays.copyOf(STRINGS_ARRAY, STRINGS_ARRAY.length);
    }

    private static List<String> getTestStringList() {
        return new ArrayList<String>(STRINGS_LIST);
    }

    private static LocalizedString[] getLocalizedTestStringArray() {
        return Arrays.copyOf(LOCALIZED_STRINGS_ARRAY,
                LOCALIZED_STRINGS_ARRAY.length);
    }

    private static List<LocalizedString> getLocalizedTestStringList() {
        return new ArrayList<LocalizedString>(LOCALIZED_STRINGS_LIST);
    }

    private static class LocalizedString implements Localizable,
            Comparable<String> {

        private final String localizedString;

        public LocalizedString(String localizedString) {
            this.localizedString = localizedString;
        }

        @Override
        public String getDisplayString(Locale inLocale) {
            return localizedString;
        }

        @Override
        public int compareTo(String o) {
            return localizedString.compareTo(o);
        }

        @Override
        public String toString() {
            return localizedString;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof LocalizedString)) {
                return false;
            }
            final LocalizedString other = (LocalizedString) obj;
            return (localizedString == other.localizedString || (localizedString
                    != null && localizedString.equals(other.localizedString)));
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + localizedString.hashCode();
            return result;
        }
    }

    private static class StringLocalizer implements Localizer<String> {

        @Override
        public String getDisplayString(String localizedString, Locale inLocale) {
            return localizedString;
        }
    }
}
