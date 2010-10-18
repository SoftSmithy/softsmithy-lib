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
 * ClassesTest.java
 * JUnit based test
 *
 * Created on 4. Juli 2002, 22:28
 */

package org.softsmithy.lib.lang.reflect;

import junit.framework.*;
import java.util.*;

/**
 *
 * @author puce
 */
public class ClassesTest extends TestCase {
  
  public ClassesTest(java.lang.String testName) {
    super(testName);
  }
  
  public static void main(java.lang.String[] args) {
    junit.textui.TestRunner.run(suite());
  }
  
  public static Test suite() {
    TestSuite suite = new TestSuite(ClassesTest.class);
    
    return suite;
  }
  
  // Did this work before in JBuilder?
  /*public void testInterfaceImplementsInterface() {
    boolean impl = Classes.implementsInterface(java.util.SortedSet.class,
                                               java.util.Collection.class);
    assertTrue(impl);
  }*/
  
  /*public void testClassImplementsInterface() {
    boolean impl = Classes.implementsInterface(java.util.TreeSet.class,
    java.util.Collection.class);
    assertTrue(impl);
  }*/
  
  public void testImplementsInterfaceIllegalArgumentException() {
    try{
      boolean impl = Classes.implementsInterface(java.util.TreeSet.class,
      
      java.util.AbstractCollection.class);
      fail("Should raise an IllegalArgumentException!");
    } catch(IllegalArgumentException e1){
      // assert -> true
    } catch(Exception e2){
      fail(e2.toString());
    }
    
    assertTrue(true);
  }
  
  public void testImplementsInterfaceNullPointerException() {
    try{
      boolean impl = Classes.implementsInterface(java.util.TreeSet.class,
      null);
      fail("Should raise a NullPointerException!");
    } catch(NullPointerException e1){
      // assert -> true
    } catch(Exception e2){
      fail(e2.toString());
    }
    
    assertTrue(true);
  }
  
  public void testNullImplementsInterface() {
    boolean impl = Classes.implementsInterface(null,
    java.util.Collection.class);
    assertTrue(! impl);
  }
  
  /** Test of implementsInterface method, of class puce.lang.reflect.Classes. */
  public void testImplementsInterface() {
    boolean impl = Classes.implementsInterface(java.util.TreeSet.class,
    java.util.Collection.class);
    assertTrue(impl);
  }
  
  /** Test of extendsClass method, of class puce.lang.reflect.Classes. */
  public void testExtendsClass() {
    boolean impl = Classes.implementsInterface(java.util.SortedSet.class,
    java.util.Collection.class);
  }
  
  // Add test methods here, they have to start with 'test' name.
  // for example:
  // public void testHello() {}
  
  
}
