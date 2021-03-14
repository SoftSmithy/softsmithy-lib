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

import org.junit.jupiter.api.Test;

import java.util.Comparator;

/**
 *
 * @author puce
 */
public class TestsTest {

    /**
     * Test of testHashCode method, of class Tests.
     */
    @Test
    public void testTestHashCode() {
        // given
        String s1 = "test1";
        String s2 = new String("test1");

        // when
        // then
        Tests.testHashCode(s1, s2);
    }

    /**
     * Test of testEquals method, of class Tests.
     */
    @Test
    public void testTestEquals() {
        // given
        String s1 = "test1";
        String s2 = new String("test1");
        String s3 = new String("test1");
        String unequal1 = new String("test2");
        String unequal2 = new String("test3");

        // when
        // then
        Tests.testEquals(s1, s2, s3, unequal1, unequal2);
    }

    @Test
    public void validInput_testComparableConsistentWithEquals_noException() {
        // given
        String o = "test2";
        String smaller = new String("test1");
        String equal = new String("test2");
        String greater = new String("test3");

        // when
        // then
        Tests.testComparableConsistentWithEquals(o, smaller, equal, greater);
    }

    @Test
    public void nullValueSupported_testComparatorConsistentWithEquals_noException() {
        // given
        String o = "test2";
        String smaller = new String("test1");
        String equal = new String("test2");
        String greater = new String("test3");

        // when
        // then
        Tests.testComparatorConsistentWithEquals(Comparator.nullsFirst(Comparator.naturalOrder()), o, smaller, equal, greater, true);
    }

    @Test
    public void nullValueNotSupported_testComparatorConsistentWithEquals_noException() {
        // given
        String o = "test2";
        String smaller = new String("test1");
        String equal = new String("test2");
        String greater = new String("test3");

        // when
        // then
        Tests.testComparatorConsistentWithEquals(String::compareToIgnoreCase, o, smaller, equal, greater, false);
    }

    @Test
    public void validInput_testOrder_noException() {
        String s1 = new String("test1");
        String s2 = new String("test2");
        String s3 = new String("test3");
        String s4 = new String("test4");

        Tests.testOrder(s1, s2, s3, s4);
    }
}
