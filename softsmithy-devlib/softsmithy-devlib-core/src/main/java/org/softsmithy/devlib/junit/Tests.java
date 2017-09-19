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
package org.softsmithy.devlib.junit;

import static java.lang.Integer.signum;
import java.util.Comparator;
import static org.junit.Assert.*;

/**
 * JUnit test utility methods.
 *
 * @author puce
 */
public class Tests {

    private Tests() {
    }

    /**
     * Tests if equal objects have equal hashCode.
     *
     * @param o1 object 1
     * @param o2 object 2
     */
    public static void testHashCode(Object o1, Object o2) {
        // given

        // when
        assertEquals(o1, o2);

        // then
        assertEquals(o1.hashCode(), o2.hashCode());
    }

    /**
     * Tests the equals method of an object for reflexivity, symmetry, transitivity and non-nullity and tests if unequal objects are not equal.
     *
     * @param o1 object 1
     * @param o2 object 2
     * @param o3 object 3
     * @param unequalObjects unequal objects
     */
    public static void testEquals(Object o1, Object o2, Object o3,
            Object... unequalObjects) {
        //given

        // when
        assertEquals(o1, o2);
        assertEquals(o2, o3);

        // then
        // reflexivity
        assertEquals(o1, o1);

        // symmetry
        assertEquals(o2, o1);

        // transitivity
        assertEquals(o1, o3);

        // non-nullity
        assertFalse(o1.equals(null));

        // unequal
        for (Object obj : unequalObjects) {
            assertFalse(o1.equals(obj));
            assertFalse(obj.equals(o1));
        }
    }

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
            assertTrue("No NullPointerException but null value not supported!", nullValueSupported);
        } catch (NullPointerException ex) {
            assertFalse("NullPointerException thrown but null value supported!!", nullValueSupported);
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
