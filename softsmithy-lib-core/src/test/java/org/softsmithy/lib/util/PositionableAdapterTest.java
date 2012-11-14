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

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.softsmithy.lib.Tests;

/**
 *
 * @author puce
 */
public class PositionableAdapterTest {

    public PositionableAdapterTest() {
    }

    @Ignore
    @Test
    public void testGetAdapted() {
        System.out.println("getAdapted");
        PositionableAdapter instance = null;
        Object expResult = null;
        Object result = instance.getAdapted();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Ignore
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        PositionableAdapter instance = null;
        int expResult = 0;
        int result = instance.getPosition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Tests.testComparatorConsistentWithEquals(
                new PositionableAdapter<>("test1", 5),
                new PositionableAdapter<>("test2", 3),
                new PositionableAdapter<>("test3", 5),
                new PositionableAdapter<>("test4", 7));
    }
}
