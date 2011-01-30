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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author puce
 */
public class TestsTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of testHashCode method, of class Tests.
     */
    @Test
    public void testTestHashCode() {
        System.out.println("testHashCode");

        // given
        String s1 = "test1";
        String s2 = new String("test1");
        String s3 = new String("test1");
        String unequal1 = new String("test2");
        String unequal2 = new String("test3");
        
        // when

        // then
        Tests.testHashCode(s1, s2);
    }

    /**
     * Test of testEquals method, of class Tests.
     */
    @Test
    public void testTestEquals() {
        System.out.println("testEquals");

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
}
