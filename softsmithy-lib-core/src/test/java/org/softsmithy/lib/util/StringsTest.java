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

 /*
 * StringsTest.java
 * NetBeans JUnit based test
 *
 * Created on 30. Oktober 2002, 10:57
 */
package org.softsmithy.lib.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author puce
 */
public class StringsTest {

    /**
     * Test of join method, of class org.softsmithy.lib.util.Strings.
     */
    @Test
    public void testJoin() {
        System.out.println("testJoin");
        String[] strings = {"foo1", "bar1", "foo2", "bar2"};
        assertEquals("foo1/bar1/foo2/bar2", Strings.join(strings, false, "/", false));
        assertEquals("foo1/bar1/foo2/bar2/", Strings.join(strings, false, "/", true));
        assertEquals("/foo1/bar1/foo2/bar2", Strings.join(strings, true, "/", false));
        assertEquals("/foo1/bar1/foo2/bar2/", Strings.join(strings, true, "/", true));
        assertEquals(Strings.join(strings, false, "/", false), Strings.join(strings, "/"));
        assertEquals(Strings.join(strings, false, "/", false), Strings.join(strings, false, "/"));
        assertEquals(Strings.join(strings, true, "/", false), Strings.join(strings, true, "/"));
        assertEquals(Strings.join(strings, false, "/", false), Strings.join(strings, "/", false));
        assertEquals(Strings.join(strings, false, "/", true), Strings.join(strings, "/", true));

    }

}
