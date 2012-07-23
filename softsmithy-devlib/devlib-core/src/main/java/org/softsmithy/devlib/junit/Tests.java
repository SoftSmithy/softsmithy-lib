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

import static org.junit.Assert.*;

/**
 * JUnit test utility methods.
 * @author puce
 */
public class Tests {

    private Tests() {
    }

    /**
     * Tests if equal objects have equal hashCode.
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
     * Tests the equals method of an object for reflexivity, symmetry,
     * transitivity and non-nullity and tests if unequal objects are not equal.
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
}
