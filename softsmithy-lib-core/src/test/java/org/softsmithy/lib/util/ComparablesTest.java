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
package org.softsmithy.lib.util;

import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


/**
 *
 * @author puce
 */
public class ComparablesTest {

    /**
     * Test of isGreater method, of class Comparables.
     */
    @Test
    public void testIsGreater() {
        System.out.println("testIsGreater");
        Integer i1 = 1;
        Integer i2 = 2;
        assertFalse(Comparables.isGreater(i1, i2));
        assertTrue(Comparables.isGreater(i2, i1));
        assertFalse(Comparables.isGreater(i1, i1));
    }

    /**
     * Test of isGreater method, of class Comparables.
     */
    @Test
    public void testIsGreaterWithComparator() {
        System.out.println("testIsGreaterWithComparator");
        TestObject i1 = new TestObject(1, 10);
        TestObject i2 = new TestObject(2, 20);
        Comparator<? super TestObject> comparator = new TestObjectBaseComparator();
        assertFalse(Comparables.isGreater(i1, i2, comparator));
        assertTrue(Comparables.isGreater(i2, i1, comparator));
        assertFalse(Comparables.isGreater(i1, i1, comparator));
    }

    /**
     * Test of isGreaterEqual method, of class Comparables.
     */
    @Test
    public void testIsGreaterEqual() {
        System.out.println("testIsGreaterEqual");
        Integer i1 = 1;
        Integer i2 = 2;
        assertFalse(Comparables.isGreaterEqual(i1, i2));
        assertTrue(Comparables.isGreaterEqual(i2, i1));
        assertTrue(Comparables.isGreaterEqual(i1, i1));
    }

    /**
     * Test of isGreaterEqual method, of class Comparables.
     */
    @Test
    public void testIsGreaterEqualWithComparator() {
        System.out.println("testIsGreaterEqualWithComparator");
        TestObject i1 = new TestObject(1, 10);
        TestObject i2 = new TestObject(2, 20);
        Comparator<? super TestObject> comparator = new TestObjectBaseComparator();
        assertFalse(Comparables.isGreaterEqual(i1, i2, comparator));
        assertTrue(Comparables.isGreaterEqual(i2, i1, comparator));
        assertTrue(Comparables.isGreaterEqual(i1, i1, comparator));
    }

    /**
     * Test of isLess method, of class Comparables.
     */
    @Test
    public void testIsLess() {
        System.out.println("testIsLess");
        Integer i1 = 1;
        Integer i2 = 2;
        assertTrue(Comparables.isLess(i1, i2));
        assertFalse(Comparables.isLess(i2, i1));
        assertFalse(Comparables.isLess(i1, i1));
    }

    /**
     * Test of isLess method, of class Comparables.
     */
    @Test
    public void testIsLessWithComparator() {
        System.out.println("testIsLessWithComparator");
        TestObject i1 = new TestObject(1, 10);
        TestObject i2 = new TestObject(2, 20);
        Comparator<? super TestObject> comparator = new TestObjectBaseComparator();
        assertTrue(Comparables.isLess(i1, i2, comparator));
        assertFalse(Comparables.isLess(i2, i1, comparator));
        assertFalse(Comparables.isLess(i1, i1, comparator));
    }

    /**
     * Test of isLessEqual method, of class Comparables.
     */
    @Test
    public void testIsLessEqual() {
        System.out.println("testIsLessEqual");
        Integer i1 = 1;
        Integer i2 = 2;
        assertTrue(Comparables.isLessEqual(i1, i2));
        assertFalse(Comparables.isLessEqual(i2, i1));
        assertTrue(Comparables.isLessEqual(i1, i1));
    }

    /**
     * Test of isLessEqual method, of class Comparables.
     */
    @Test
    public void testIsLessEqualWithComparator() {
        System.out.println("testIsLessEqualWithComparator");
        TestObject i1 = new TestObject(1, 10);
        TestObject i2 = new TestObject(2, 20);
        Comparator<? super TestObject> comparator = new TestObjectBaseComparator();
        assertTrue(Comparables.isLessEqual(i1, i2, comparator));
        assertFalse(Comparables.isLessEqual(i2, i1, comparator));
        assertTrue(Comparables.isLessEqual(i1, i1, comparator));
    }

    /**
     * Test of isEqual method, of class Comparables.
     */
    @Test
    public void testIsEqual() {
        System.out.println("testIsEqual");
        Integer i1 = 1;
        Integer i2 = 2;
        assertFalse(Comparables.isEqual(i1, i2));
        assertFalse(Comparables.isEqual(i2, i1));
        assertTrue(Comparables.isEqual(i1, i1));
    }

    /**
     * Test of isEqual method, of class Comparables.
     */
    @Test
    public void testIsEqualWithComparator() {
        System.out.println("testIsEqualWithComparator");
        TestObject i1 = new TestObject(1, 10);
        TestObject i2 = new TestObject(2, 20);
        Comparator<? super TestObject> comparator = new TestObjectBaseComparator();
        assertFalse(Comparables.isEqual(i1, i2, comparator));
        assertFalse(Comparables.isEqual(i2, i1, comparator));
        assertTrue(Comparables.isEqual(i1, i1, comparator));
    }

    /**
     * Test of min method, of class Comparables.
     */
    @Test
    public void testMin() {
        System.out.println("testMin");
        Integer i1 = 1;
        Integer i2 = 2;
        assertEquals(i1, Comparables.min(i1, i2));
        assertEquals(i1, Comparables.min(i2, i1));
    }

    /**
     * Test of min method, of class Comparables.
     */
    @Test
    public void testMinWithComparator() {
        System.out.println("testMinWithComparator");
        TestObject i1 = new TestObject(1, 10);
        TestObject i2 = new TestObject(2, 20);
        Comparator<? super TestObject> comparator = new TestObjectBaseComparator();
        assertEquals(i1, Comparables.min(i1, i2, comparator));
        assertEquals(i1, Comparables.min(i2, i1, comparator));
    }

    /**
     * Test of max method, of class Comparables.
     */
    @Test
    public void testMax() {
        System.out.println("testMax");
        Integer i1 = 1;
        Integer i2 = 2;
        assertEquals(i2, Comparables.max(i1, i2));
        assertEquals(i2, Comparables.max(i2, i1));
    }

    /**
     * Test of max method, of class Comparables.
     */
    @Test
    public void testMaxWithComparator() {
        System.out.println("testMaxWithComparator");
        TestObject i1 = new TestObject(1, 10);
        TestObject i2 = new TestObject(2, 20);
        Comparator<? super TestObject> comparator = new TestObjectBaseComparator();
        assertEquals(i2, Comparables.max(i1, i2, comparator));
        assertEquals(i2, Comparables.max(i2, i1, comparator));
    }

    /**
     * Test of toRange method, of class Comparables.
     */
    @Test
    public void testToRange() {
        System.out.println("testToRange");
        Integer i5 = 5;
        Integer i10 = 10;
        Integer i15 = 15;
        Integer i20 = 20;
        Integer i25 = 25;
        assertEquals(i15, Comparables.toRange(i15, i10, i20));
        assertEquals(i10, Comparables.toRange(i5, i10, i20));
        assertEquals(i20, Comparables.toRange(i25, i10, i20));
        assertEquals(i10, Comparables.toRange(i10, i10, i20));
        assertEquals(i20, Comparables.toRange(i20, i10, i20));

        assertEquals(i5, Comparables.toRange(i5, null, i20));
        assertEquals(i20, Comparables.toRange(i25, null, i20));

        assertEquals(i10, Comparables.toRange(i5, i10, null));
        assertEquals(i25, Comparables.toRange(i25, i10, null));

        assertEquals(i15, Comparables.toRange(i15, null, null));
    }

    /**
     * Test of toRange method, of class Comparables.
     */
    @Test
    public void testToRangeWithComparator() {
        System.out.println("testToRangeWithComparator");
        TestObject i5 = new TestObject(5, 50);
        TestObject i10 = new TestObject(10, 100);
        TestObject i15 = new TestObject(15, 150);
        TestObject i20 = new TestObject(20, 200);
        TestObject i25 = new TestObject(25, 250);
        Comparator<? super TestObject> comparator = new TestObjectBaseComparator();

        assertEquals(i15, Comparables.toRange(i15, i10, i20, comparator));
        assertEquals(i10, Comparables.toRange(i5, i10, i20, comparator));
        assertEquals(i20, Comparables.toRange(i25, i10, i20, comparator));
        assertEquals(i10, Comparables.toRange(i10, i10, i20, comparator));
        assertEquals(i20, Comparables.toRange(i20, i10, i20, comparator));

        assertEquals(i5, Comparables.toRange(i5, null, i20, comparator));
        assertEquals(i20, Comparables.toRange(i25, null, i20, comparator));

        assertEquals(i10, Comparables.toRange(i5, i10, null, comparator));
        assertEquals(i25, Comparables.toRange(i25, i10, null, comparator));

        assertEquals(i15, Comparables.toRange(i15, null, null, comparator));
    }

    /**
     * Test of isInRange method, of class Comparables.
     */
    @Test
    public void testIsInRange() {
        System.out.println("testIsInRange");
        Integer i5 = 5;
        Integer i10 = 10;
        Integer i15 = 15;
        Integer i20 = 20;
        Integer i25 = 25;
        assertTrue(Comparables.isInRange(i15, i10, i20));
        assertTrue(Comparables.isInRange(i10, i10, i20));
        assertTrue(Comparables.isInRange(i20, i10, i20));
        assertFalse(Comparables.isInRange(i5, i10, i20));
        assertFalse(Comparables.isInRange(i25, i10, i20));

        assertTrue(Comparables.isInRange(i5, null, i20));
        assertFalse(Comparables.isInRange(i25, null, i20));

        assertFalse(Comparables.isInRange(i5, i10, null));
        assertTrue(Comparables.isInRange(i25, i10, null));

        assertTrue(Comparables.isInRange(i15, null, null));
    }

    /**
     * Test of isInRange method, of class Comparables.
     */
    @Test
    public void testIsInRangeWithComparator() {
        System.out.println("testIsInRangeWithComparator");
        TestObject i5 = new TestObject(5, 50);
        TestObject i10 = new TestObject(10, 100);
        TestObject i15 = new TestObject(15, 150);
        TestObject i20 = new TestObject(20, 200);
        TestObject i25 = new TestObject(25, 250);
        Comparator<? super TestObject> comparator = new TestObjectBaseComparator();
        
        assertTrue(Comparables.isInRange(i15, i10, i20, comparator));
        assertTrue(Comparables.isInRange(i10, i10, i20, comparator));
        assertTrue(Comparables.isInRange(i20, i10, i20, comparator));
        assertFalse(Comparables.isInRange(i5, i10, i20, comparator));
        assertFalse(Comparables.isInRange(i25, i10, i20, comparator));

        assertTrue(Comparables.isInRange(i5, null, i20, comparator));
        assertFalse(Comparables.isInRange(i25, null, i20, comparator));

        assertFalse(Comparables.isInRange(i5, i10, null, comparator));
        assertTrue(Comparables.isInRange(i25, i10, null, comparator));

        assertTrue(Comparables.isInRange(i15, null, null, comparator));
    }

    private static class TestObjectBase {

        private final Integer baseValue;

        public TestObjectBase(Integer baseValue) {
            this.baseValue = baseValue;
        }

        /**
         * @return the value
         */
        public Integer getBaseValue() {
            return baseValue;
        }
    }

    private static class TestObject extends TestObjectBase {

        private final Integer value;

        public TestObject(Integer baseValue, Integer value) {
            super(baseValue);
            this.value = value;
        }

        /**
         * @return the value
         */
        public Integer getValue() {
            return value;
        }
    }

    private static class TestObjectBaseComparator implements Comparator<TestObjectBase> {

        @Override
        public int compare(TestObjectBase o1, TestObjectBase o2) {
            return o1.getBaseValue().compareTo(o2.getBaseValue());
        }
    }
}
