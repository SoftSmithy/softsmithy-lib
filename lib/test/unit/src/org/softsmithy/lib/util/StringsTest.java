/*
 *                  Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
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

import junit.framework.*;

/**
 *
 * @author puce
 */
public class StringsTest extends TestCase {
  
  public StringsTest(java.lang.String testName) {
    super(testName);
  }
  
  public static void main(java.lang.String[] args) {
    junit.textui.TestRunner.run(suite());
  }
  
  public static Test suite() {
    TestSuite suite = new TestSuite(StringsTest.class);
    
    return suite;
  }
  
  /** Test of join method, of class org.softsmithy.lib.util.Strings. */
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
  
  // Add test methods here, they have to start with 'test' name.
  // for example:
  // public void testHello() {}
  
  
  
}
