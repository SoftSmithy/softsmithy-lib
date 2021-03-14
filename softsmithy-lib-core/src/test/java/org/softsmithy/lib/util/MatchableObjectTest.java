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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;



/**
 *
 * @author puce
 */
public class MatchableObjectTest {



    /**
     * Test of hashCode method, of class MatchableObject.
     */
    @Test
    public void testHashCode() throws MatchingException {
        System.out.println("hashCode");
        // given
        MatchableObject<String> object1 = new MatchableObject<>("test", new ObjectMatcher<>(
                String.class));
        MatchableObject<String> object2 = new MatchableObject<>("test", new ObjectMatcher<>(
                String.class));

        // when
        assertEquals(object1, object2);

        // then
        assertEquals(object1.hashCode(), object2.hashCode());
    }

    /**
     * Test of equals method, of class MatchableObject.
     */
    @Test
    public void testEquals() throws MatchingException {
        System.out.println("equals");
        // given
        MatchableObject<String> object1 = new MatchableObject<>("test1", new ObjectMatcher<>(String.class));
        MatchableObject<String> object2 = new MatchableObject<>("test1", new ObjectMatcher<>(String.class));
        MatchableObject<String> object3 = new MatchableObject<>("test1", new ObjectMatcher<>(String.class));
        MatchableObject<String> object4 = new MatchableObject<>("test2", new ObjectMatcher<>(String.class));
        MatchableObject<String> object5 = new MatchableObject<>("test1", new ComparatorMatcher<>(String.class,
                String.CASE_INSENSITIVE_ORDER));

        // when
        assertEquals(object1, object2);
        assertEquals(object2, object3);

        // then

        // reflexivity
        assertEquals(object1, object1);

        // symmetry
        assertEquals(object2, object1);

        //transitivity
        assertEquals(object1, object3);

        //non-nullity
        assertFalse(object1.equals(null));

        // unequal
        assertFalse(object1.equals(object4));
        assertFalse(object1.equals(object5));
        assertFalse(object4.equals(object1));
        assertFalse(object5.equals(object1));
    }
}
