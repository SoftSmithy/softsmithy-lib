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
 * XMathTest.java
 * JUnit based test
 *
 * Created on 4. Juli 2002, 22:25
 */

package org.softsmithy.lib.util;

import junit.framework.*;

/**
 *
 * @author puce
 */
public class XMathTest extends TestCase {
  
  public XMathTest(java.lang.String testName) {
    super(testName);
  }
  
  public static void main(java.lang.String[] args) {
    junit.textui.TestRunner.run(suite());
  }
  
  public static Test suite() {
    TestSuite suite = new TestSuite(XMathTest.class);
    
    return suite;
  }
  
  /** Test of toStandardInterval method, of class puce.util.XMath. */
  public void testToStandardInterval() {
    assertTrue((XMath.toStandardInterval(5*Math.PI/2) == Math.PI/2) &&
               (XMath.toStandardInterval(3*Math.PI) == Math.PI) &&
               (XMath.toStandardInterval(7*Math.PI/2) == 3*Math.PI/2) &&
               (XMath.toStandardInterval(4*Math.PI) == 0) &&
               (XMath.toStandardInterval(-5*Math.PI/2) == 3*Math.PI/2) &&
               (XMath.toStandardInterval(-3*Math.PI) == Math.PI) &&
               (XMath.toStandardInterval(-7*Math.PI/2) == Math.PI/2) &&
               (XMath.toStandardInterval(-4*Math.PI) == 0) &&
               (XMath.toStandardInterval(-Math.PI - 1) == Math.PI - 1));
  }
  
  // Add test methods here, they have to start with 'test' name.
  // for example:
  // public void testHello() {}
  
  
}
