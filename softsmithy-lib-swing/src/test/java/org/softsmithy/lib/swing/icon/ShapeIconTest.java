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
 * ShapeIconTest.java
 * JUnit based test
 *
 * Created on 20. Oktober 2004, 14:45
 */
package org.softsmithy.lib.swing.icon;

import java.awt.Rectangle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author puce
 */
public class ShapeIconTest {

    private ShapeIcon filledIcon;
    private ShapeIcon borderIcon;
    private static final int NEW_WIDTH = 20;
    private static final int NEW_HEIGHT = 30;

    @BeforeEach
    public void setUp() throws Exception {
        filledIcon = new ShapeIcon(new Rectangle(10, 12));
        filledIcon.setFilled(true);
        borderIcon = new ShapeIcon(new Rectangle(10, 12));
        borderIcon.setFilled(false);
    }

    /**
     * Test of getIconHeight method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
     */
    //@Test
    /*public void testGetIconHeight() {
   
    System.out.println("testGetIconHeight");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
    /**
     * Test of getIconWidth method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
     */
    //@Test
    /*public void testGetIconWidth() {
   
    System.out.println("testGetIconWidth");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
    /**
     * Test of paintIcon method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
     */
    //@Test
    /*public void testPaintIcon() {
   
    System.out.println("testPaintIcon");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
    /**
     * Test of getShape method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
     */
    //@Test
    /*public void testGetShape() {
   
    System.out.println("testGetShape");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
    /**
     * Test of setShape method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
     */
    //@Test
    /*public void testSetShape() {
   
    System.out.println("testSetShape");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
    /**
     * Test of isFilled method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
     */
    //@Test
    /*public void testIsFilled() {
   
    System.out.println("testIsFilled");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
    /**
     * Test of setFilled method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
     */
    //@Test
    /*public void testSetFilled() {
   
    System.out.println("testSetFilled");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
    /**
     * Test of getStroke method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
     */
    //@Test
    /*public void testGetStroke() {
   
    System.out.println("testGetStroke");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
    /**
     * Test of setStroke method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
     */
    //@Test
    /*public void testSetStroke() {
   
    System.out.println("testSetStroke");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
    /**
     * Test of getTransformedInstance method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
     */
    //@Test
    /*public void testGetTransformedInstance() {
   
    System.out.println("testGetTransformedInstance");
   
    // TODO add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }*/
  
    /**
     * Test of getScaledInstance method, of class org.softsmithy.lib.swing.icon.ShapeIcon.
     */
    @Test
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
        assertEquals(24, newFilledIcon.getIconHeight());
        assertEquals(NEW_WIDTH, newBorderIcon.getIconWidth());
        assertEquals(24, newBorderIcon.getIconHeight());

        newFilledIcon = (ShapeIcon) filledIcon.getScaledInstance(-1, NEW_HEIGHT);
        newBorderIcon = (ShapeIcon) borderIcon.getScaledInstance(-1, NEW_HEIGHT);
        assertEquals(25, newFilledIcon.getIconWidth());
        assertEquals(NEW_HEIGHT, newFilledIcon.getIconHeight());
        assertEquals(25, newBorderIcon.getIconWidth());
        assertEquals(NEW_HEIGHT, newBorderIcon.getIconHeight());
    }

}
