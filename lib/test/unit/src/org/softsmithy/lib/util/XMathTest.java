/*
 * XMathTest.java
 * JUnit based test
 *
 * Created on 4. Juli 2002, 22:25
 */

package puce.util;

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
