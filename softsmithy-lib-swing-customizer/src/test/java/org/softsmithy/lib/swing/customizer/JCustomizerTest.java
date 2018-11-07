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
 * JCustomizerTest.java
 * JUnit based test
 *
 * Created on 27. August 2002, 08:54
 */
package org.softsmithy.lib.swing.customizer;

import java.awt.Rectangle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.softsmithy.lib.swing.customizer.layout.AbsoluteTableConstraints;
import org.softsmithy.lib.swing.customizer.layout.TableLayout;

/**
 *
 * @author puce
 */
public class JCustomizerTest {

    private JCustomizer customizer;
    private JCustomizerPane pane;

    @BeforeEach
    public void setUp() throws java.lang.Exception {
        customizer = new JCustomizer();
        pane = new JCustomizerPane();
        TableLayout layout = (TableLayout) pane.getLayout();
        pane.add(customizer, new AbsoluteTableConstraints(5, 5, 30, 10, customizer, layout));
        pane.doLayout();
    }

    /**
     * Test of moveRel method, of class puce.swing.JCustomizer.
     */
//  @Test
//  public void testMoveRel() {
//    System.out.println("testMoveRel");
//    customizer.moveRel(10, 15);
//    pane.doLayout();
//    Rectangle bounds = customizer.getBounds();
//    assertEquals(35, bounds.x);
//    assertEquals(40, bounds.y);
//  }
    
    /**
     * Test of resizeRel method, of class puce.swing.JCustomizer.
     */
//  @Test
//  public void testResizeRel() {
//    System.out.println("testResizeRel");
//    customizer.resizeRel(-5, 5);
//    pane.doLayout();
//    Rectangle bounds = customizer.getBounds();
//    assertEquals(145, bounds.width);
//    assertEquals(55, bounds.height);
//  }
    
    /**
     * Test of reshapeRel method, of class puce.swing.JCustomizer.
     */
    @Test
    public void testReshapeRel() {
        System.out.println("testReshapeRel");
        customizer.reshapeRel(10, 15, -5, 5);
        pane.doLayout();
        Rectangle bounds = customizer.getBounds();
        assertEquals(10, bounds.x);
        assertEquals(10, bounds.y);
        assertEquals(20, bounds.width);
        assertEquals(10, bounds.height);
    }
  
}
