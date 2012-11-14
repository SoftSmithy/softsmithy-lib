/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.lib;

import java.util.Comparator;
import static org.junit.Assert.*;

/**
 *
 * @author puce
 */
public class Tests {

    // TODO: complete
    public static <T> void testComparatorConsistentWithEquals(Comparator<? super T> comparator, T o, T smaller, T equal, T greater) {
        testComparator(comparator, o, smaller, equal, greater);
    }

    // TODO: complete
    public static <T> void testComparator(Comparator<? super T> comparator, T o, T smaller, T equal, T greater) {
        assertTrue(comparator.compare(o, smaller) > 0);
        assertTrue(comparator.compare(smaller, o) < 0);

        assertEquals(0, comparator.compare(o, equal));
        assertEquals(0, comparator.compare(equal, o));

        assertTrue(comparator.compare(o, greater) < 0);
        assertTrue(comparator.compare(greater, o) > 0);

        assertEquals(0, comparator.compare(o, o));
    }

    // TODO: complete
    public static <T extends Comparable<? super T>> void testComparatorConsistentWithEquals(T o, T smaller, T equal, T greater) {
        testComparator(o, smaller, equal, greater);
    }

    // TODO: complete
    public static <T extends Comparable<? super T>> void testComparator(T o, T smaller, T equal, T greater) {
        assertTrue(o.compareTo(smaller) > 0);
        assertTrue(smaller.compareTo(o) < 0);

        assertEquals(0, o.compareTo(equal));
        assertEquals(0, equal.compareTo(o));

        assertTrue(o.compareTo(greater) < 0);
        assertTrue(greater.compareTo(o) > 0);

        assertEquals(0, o.compareTo(o));
    }
}
