/*
 * ClassesTest.java
 * JUnit based test
 *
 * Created on 4. Juli 2002, 22:28
 */

package puce.lang.reflect;

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