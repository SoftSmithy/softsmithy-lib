/*
 * ShapeIconTest.java
 * JUnit based test
 *
 * Created on 20. Oktober 2004, 14:45
 */

package org.softsmithy.lib.swing.icon;

import java.awt.Rectangle;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 *
 * @author puce
 */
public class ShapeIconTest extends TestCase {
  
  private ShapeIcon filledIcon;
  private ShapeIcon borderIcon;
  private static final int NEW_WIDTH = 20;
  private static final int NEW_HEIGHT = 30;
  
  public ShapeIconTest(String testName) {
    super(testName);
  }
  
  protected void setUp() throws Exception {
    filledIcon = new ShapeIcon(new Rectangle(10, 12));
    filledIcon.setFilled(true);
    borderIcon = new ShapeIcon(new Rectangle(10, 12));
    borderIcon.setFilled(false);
  }
  
  protected void tearDown() throws Exception {
  }
  
  public static Test suite() {
    
    TestSuite suite = new TestSuite(ShapeIconTest.class);
    
    return suite;
  }
  
  /**
   * Test of getIconHeight method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
   */
  /*public void testGetIconHeight() {
   
    System.out.println("testGetIconHeight");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
  /**
   * Test of getIconWidth method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
   */
  /*public void testGetIconWidth() {
   
    System.out.println("testGetIconWidth");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
  /**
   * Test of paintIcon method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
   */
  /*public void testPaintIcon() {
   
    System.out.println("testPaintIcon");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
  /**
   * Test of getShape method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
   */
  /*public void testGetShape() {
   
    System.out.println("testGetShape");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
  /**
   * Test of setShape method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
   */
  /*public void testSetShape() {
   
    System.out.println("testSetShape");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
  /**
   * Test of isFilled method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
   */
  /*public void testIsFilled() {
   
    System.out.println("testIsFilled");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
  /**
   * Test of setFilled method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
   */
  /*public void testSetFilled() {
   
    System.out.println("testSetFilled");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
  /**
   * Test of getStroke method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
   */
  /*public void testGetStroke() {
   
    System.out.println("testGetStroke");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
  /**
   * Test of setStroke method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
   */
  /*public void testSetStroke() {
   
    System.out.println("testSetStroke");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
  /**
   * Test of getTransformedInstance method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
   */
  /*public void testGetTransformedInstance() {
   
    System.out.println("testGetTransformedInstance");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
  /**
   * Test of getScaledInstance method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
   */
  public void testGetScaledInstance() {
    
    System.out.println("testGetScaledInstance");
    
    // TODO add your test code below by replacing the default call to fail.
    ShapeIcon newFilledIcon = (ShapeIcon) filledIcon.getScaledInstance(NEW_WIDTH, NEW_HEIGHT);
    ShapeIcon newBorderIcon = (ShapeIcon) borderIcon.getScaledInstance(NEW_WIDTH, NEW_HEIGHT);
    assertEquals(NEW_WIDTH, newFilledIcon.getIconWidth());
    assertEquals(NEW_HEIGHT, newFilledIcon.getIconHeight());
    assertEquals(NEW_WIDTH, newBorderIcon.getIconWidth());
    assertEquals(NEW_HEIGHT, newBorderIcon.getIconHeight());
    
    newFilledIcon = (ShapeIcon) filledIcon.getScaledInstance(NEW_WIDTH, -1);
    newBorderIcon = (ShapeIcon) borderIcon.getScaledInstance(NEW_WIDTH, -1);
    assertEquals(NEW_WIDTH, newFilledIcon.getIconWidth());
    assertEquals(NEW_HEIGHT, newFilledIcon.getIconHeight());
    assertEquals(NEW_WIDTH, newBorderIcon.getIconWidth());
    assertEquals(NEW_HEIGHT, newBorderIcon.getIconHeight());
    
    newFilledIcon = (ShapeIcon) filledIcon.getScaledInstance(-1, NEW_HEIGHT);
    newBorderIcon = (ShapeIcon) borderIcon.getScaledInstance(-1, NEW_HEIGHT);
    assertEquals(NEW_WIDTH, newFilledIcon.getIconWidth());
    assertEquals(NEW_HEIGHT, newFilledIcon.getIconHeight());
    assertEquals(NEW_WIDTH, newBorderIcon.getIconWidth());
    assertEquals(NEW_HEIGHT, newBorderIcon.getIconHeight());
  }
  
  // TODO add test methods here. The name must begin with 'test'. For example:
  // public void testHello() {}
  
}
