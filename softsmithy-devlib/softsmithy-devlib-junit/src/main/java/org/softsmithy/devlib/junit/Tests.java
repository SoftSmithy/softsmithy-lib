/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (GitHub user: puce77). All Rights Reserved.
 *
 * Contributor(s): .
 */
package org.softsmithy.devlib.junit;

import static java.lang.Integer.signum;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * JUnit 5 test utility methods.
 *
 * @author puce
 */
public final class Tests {

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
        assertEquals(o1, o2, "o1 is not equal to o2");

        // then
        assertEquals(o1.hashCode(), o2.hashCode(), "o1.hashCode() is not equal to o2.hashCode()");
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
        assertEquals(o1, o2, "o1 is not equal to o2");
        assertEquals( o2, o3, "o2 is not equal to o3");

        // then
        // reflexivity
        assertEquals( o1, o1, "reflexivity: o1 is not equal to o1");

        // symmetry
        assertEquals( o2, o1, "symmetry: o2 is not equal to o1");

        // transitivity
        assertEquals(o1, o3, "transitivity: o1 is not equal to o3");

        // non-nullity
        assertThat("non-nullity: o1 is not unequal to null", o1, not(equalTo(null)));

        // unequal
        int i = 0;
        for (Object obj : unequalObjects) {
            assertThat("unequal: o1 is not unequal to unequalObjects[" + i + "]", o1, not(equalTo(obj)));
            assertThat("unequal: unequalObjects[" + i + "] is not unequal to o1", obj, not(equalTo(o1)));
            i++;
        }
    }

    /**
     * Tests the contract of the {@link Comparator} interface and the consistency with {@link Object#equals(java.lang.Object) }.
     *
     * @param <T> the type of the objects to compare
     * @param comparator the comparator to test
     * @param o the reference object
     * @param smaller an object smaller than the reference object
     * @param equal an object equal to the reference object
     * @param greater an object greater than the reference object
     * @param nullValueSupported true if the comparator supports null arguments, else false
     */
    public static <T> void testComparatorConsistentWithEquals(Comparator<? super T> comparator, T o, T smaller, T equal, T greater, boolean nullValueSupported) {
        testComparator(comparator, o, smaller, equal, greater, nullValueSupported);
        testConsistencyWithEquals(comparator, o, smaller, equal, greater);
    }

    /**
     * Tests the contract of the {@link Comparator} interface without consistency with {@link Object#equals(java.lang.Object) }.
     *
     * @param <T> the type of the objects to compare
     * @param comparator the comparator to test
     * @param o the reference object
     * @param smaller an object smaller than the reference object
     * @param equal an object equal to the reference object
     * @param greater an object greater than the reference object
     * @param nullValueSupported true if the comparator supports null arguments, else false
     */
    public static <T> void testComparator(Comparator<? super T> comparator, T o, T smaller, T equal, T greater, boolean nullValueSupported) {
        assertAsymmetry(comparator, o, smaller, greater);
        assertSymmetry(comparator, o, equal);
        assertSignum(comparator, o, smaller, equal, greater);
        assertTransitivity(comparator, o, smaller, greater);

        assertEquals(0, comparator.compare(o, o), "reflexivity: o compared to o is not equal");

        assertNullValue(comparator, o, nullValueSupported);
    }

    private static <T> void assertNullValue(Comparator<? super T> comparator, T o, boolean nullValueSupported) {
        try {
            comparator.compare(o, null);
            if (!nullValueSupported) {
                fail("NullPointerException expected but not thrown!");
            }
        } catch (NullPointerException ex) {
            if (nullValueSupported) {
                fail("NullPointerException not expected but thrown!");
            }
        }
    }

    private static <T> void assertAsymmetry(Comparator<? super T> comparator, T o, T smaller, T greater) {
        assertTrue(comparator.compare(o, smaller) > 0, "o is not greater than smaller");
        assertTrue(comparator.compare(smaller, o) < 0, "smaller is not smaller than o");

        assertTrue(comparator.compare(o, greater) < 0, "o is not smaller than greater");
        assertTrue(comparator.compare(greater, o) > 0, "greater is not greater than o");
    }

    private static <T> void assertSymmetry(Comparator<? super T> comparator, T o, T equal) {
        assertEquals(0, comparator.compare(o, equal), "o is not equal to equal");
        assertEquals(0, comparator.compare(equal, o), "equal is not equal to o");
    }

    private static <T> void assertSignum(Comparator<? super T> comparator, T o, T smaller, T equal, T greater) {
        assertSignum(comparator, o, smaller, "signum not correct for o and smaller");
        assertSignum(comparator, o, equal, "signum not correct for o and equal");
        assertSignum(comparator, o, greater, "signum not correct for o and greater");
    }

    private static <T> void assertSignum(Comparator<? super T> comparator, T o1, T o2, String message) {
        assertEquals(signum(comparator.compare(o1, o2)), -signum(comparator.compare(o2, o1)), message);
    }

    private static <T> void assertTransitivity(Comparator<? super T> comparator, T o, T smaller, T greater) {
        // when
        assertTrue(comparator.compare(greater, o) > 0, "greater is not greater than o");
        assertTrue(comparator.compare(o, smaller) > 0, "o is not greater than smaller");

        // then
        assertTrue(comparator.compare(greater, smaller) > 0, "greater is not greater than smaller");
    }

    private static <T> void testConsistencyWithEquals(Comparator<? super T> comparator, T o, T smaller, T equal, T greater) {
        assertEquals(o.equals(smaller), comparator.compare(o, smaller) == 0, "not consistent with equals for o and smaller");
        assertEquals(o.equals(equal), comparator.compare(o, equal) == 0, "not consistent with equals for o and equal");
        assertEquals(o.equals(greater), comparator.compare(o, greater) == 0, "not consistent with equals for o and greater");
    }

    /**
     * Tests the sorted order of the objects provided in ascending order.
     *
     * @param <T> the type of the objects to compare
     * @param comparator the comparator to test
     * @param objects the objects provided in ascending order
     */
    public static <T> void testOrder(Comparator<? super T> comparator, T... objects) {
        if (objects.length >= 2) {
            for (int i = 1; i < objects.length; i++) {
                assertTrue(comparator.compare(objects[i - 1], objects[i]) < 0, "objects[" + (i - 1) + "] is not smaller than objects[" + i + "]");
                assertTrue(comparator.compare(objects[i], objects[i - 1]) > 0, "objects[" + i + "] is not greater than objects[" + (i - 1) + "]");
            }
        }
        final List<T> sortedList = Arrays.asList(objects);
        sortedList.sort(comparator);
        assertEquals(Arrays.asList(objects), sortedList);
    }

    /**
     * Tests the contract of the {@link Comparable} interface and the consistency with {@link Object#equals(java.lang.Object)}.
     *
     * @param <T> the type of the comparable objects to compare
     * @param o the reference object
     * @param smaller an object smaller than the reference object
     * @param equal an object equal to the reference object
     * @param greater an object greater than the reference object
     */
    public static <T extends Comparable<? super T>> void testComparableConsistentWithEquals(T o, T smaller, T equal, T greater) {
        testComparatorConsistentWithEquals(Comparator.naturalOrder(), o, smaller, equal, greater, false);
    }

    /**
     * Tests the contract of the {@link Comparable} interface without consistency with {@link Object#equals(java.lang.Object) }.
     *
     * @param <T> the type of the comparable objects to compare
     * @param o the reference object
     * @param smaller an object smaller than the reference object
     * @param equal an object equal to the reference object
     * @param greater an object greater than the reference object
     */
    public static <T extends Comparable<? super T>> void testComparable(T o, T smaller, T equal, T greater) {
        testComparator(Comparator.naturalOrder(), o, smaller, equal, greater, false);
    }

    /**
     * Tests the sorted order of the objects provided in ascending order.
     *
     * @param <T> the type of the comparable objects to compare
     * @param objects the objects provided in ascending order
     */
    public static <T extends Comparable<? super T>> void testOrder(T... objects) {
        testOrder(Comparator.naturalOrder(), objects);
    }

}
