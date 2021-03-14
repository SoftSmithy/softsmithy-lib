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
package org.softsmithy.lib;

import java.util.Comparator;

import static java.lang.Integer.signum;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author puce
 */
public class Tests {

    public static <T> void testComparatorConsistentWithEquals(Comparator<? super T> comparator, T o, T smaller, T equal, T greater, boolean nullValueSupported) {
        testComparator(comparator, o, smaller, equal, greater, nullValueSupported);
        testConsistencyWithEquals(comparator, o, smaller, equal, greater);
    }

    public static <T> void testComparator(Comparator<? super T> comparator, T o, T smaller, T equal, T greater, boolean nullValueSupported) {
        assertAsymmetry(comparator, o, smaller, greater);
        assertSymmetry(comparator, o, equal);
        assertSignum(comparator, o, smaller, equal, greater);
        assertTransitivity(comparator, o, smaller, greater);

        assertEquals(0, comparator.compare(o, o));

        assertNullValue(comparator, o, nullValueSupported);
    }

    private static <T> void assertNullValue(Comparator<? super T> comparator, T o, boolean nullValueSupported) {
        try {
            comparator.compare(o, null);
            assertTrue(nullValueSupported, "No NullPointerException but null value not supported!");
        } catch (NullPointerException ex) {
            assertFalse(nullValueSupported, "NullPointerException thrown but null value supported!!");
        }
    }

    private static <T> void assertAsymmetry(Comparator<? super T> comparator, T o, T smaller, T greater) {
        assertTrue(comparator.compare(o, smaller) > 0);
        assertTrue(comparator.compare(smaller, o) < 0);

        assertTrue(comparator.compare(o, greater) < 0);
        assertTrue(comparator.compare(greater, o) > 0);
    }

    private static <T> void assertSymmetry(Comparator<? super T> comparator, T o, T equal) {
        assertEquals(0, comparator.compare(o, equal));
        assertEquals(0, comparator.compare(equal, o));
    }

    private static <T> void assertSignum(Comparator<? super T> comparator, T o, T smaller, T equal, T greater) {
        assertSignum(comparator, o, smaller);
        assertSignum(comparator, o, equal);
        assertSignum(comparator, o, greater);
    }

    private static <T> void assertSignum(Comparator<? super T> comparator, T o1, T o2) {
        assertEquals(signum(comparator.compare(o1, o2)), -signum(comparator.compare(o2, o1)));
    }

    private static <T> void assertTransitivity(Comparator<? super T> comparator, T o, T smaller, T greater) {
        // when
        assertTrue(comparator.compare(smaller, o) < 0);
        assertTrue(comparator.compare(o, greater) < 0);

        // then
        assertTrue(comparator.compare(smaller, greater) < 0);
    }

    private static <T> void testConsistencyWithEquals(Comparator<? super T> comparator, T o, T smaller, T equal, T greater) {
        assertEquals(o.equals(smaller), comparator.compare(o, smaller) == 0);
        assertEquals(o.equals(equal), comparator.compare(o, equal) == 0);
        assertEquals(o.equals(greater), comparator.compare(o, greater) == 0);
    }

    public static <T extends Comparable<? super T>> void testComparableConsistentWithEquals(T o, T smaller, T equal, T greater) {
        testComparatorConsistentWithEquals(Comparator.naturalOrder(), o, smaller, equal, greater, false);
    }

    public static <T extends Comparable<? super T>> void testComparable(T o, T smaller, T equal, T greater) {
        testComparator(Comparator.naturalOrder(), o, smaller, equal, greater, false);
    }
}
