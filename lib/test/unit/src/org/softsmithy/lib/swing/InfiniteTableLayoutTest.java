/*
 * InfiniteTableLayoutTest.java
 * JUnit based test
 *
 * Created on 27. August 2002, 09:32
 */

package puce.swing;

import junit.framework.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.awt.*;
import puce.awt.*;

/**
 *
 * @author puce
 */
public class InfiniteTableLayoutTest extends TestCase {
  
  private JCustomizer customizer;
  
  private InfiniteTableLayout tl;
  
  private JCustomizerPane pane;
  
  public InfiniteTableLayoutTest(java.lang.String testName) {
    super(testName);
  }
  
  public static void main(java.lang.String[] args) {
    junit.textui.TestRunner.run(suite());
  }
  
  protected void setUp() throws java.lang.Exception {
    super.setUp();
    customizer = new JCustomizer();
    pane = new JCustomizerPane();
    tl = (InfiniteTableLayout) pane.getLayout();
    tl.addLayoutComponent(customizer, new DefaultTableConstraints(5,5,30,10));
    tl.setColumnWidth(100, 20);
    tl.setRowHeight(100, 20);
    pane.doLayout();
  }
  
  /** Test of makeColumnDefault method, of class puce.swing.InfiniteTableLayout. */
  public void testMakeColumnDefault() {
    System.out.println("testMakeColumnDefault");
    tl.makeColumnDefault(100);
    assertTrue(tl.isDefaultColumn(100));
  }
  
  /** Test of isDefaultColumn method, of class puce.swing.InfiniteTableLayout. */
  public void testIsDefaultColumn() {
    System.out.println("testIsDefaultColumn");
    assertTrue(tl.isDefaultColumn(0));
    assertTrue(! tl.isDefaultColumn(100));
  }
  
  
  /** Test of makeRowDefault method, of class puce.swing.InfiniteTableLayout. */
  public void testMakeRowDefault() {
    System.out.println("testMakeRowDefault");
    tl.makeRowDefault(100);
    assertTrue(tl.isDefaultRow(100));
  }
  
  /** Test of isDefaultRow method, of class puce.swing.InfiniteTableLayout. */
  public void testIsDefaultRow() {
    System.out.println("testIsDefaultRow");
    assertTrue(tl.isDefaultRow(0));
    assertTrue(! tl.isDefaultRow(100));
  }
  
  
  /** Test of calculateColumnOffsets method, of class puce.swing.InfiniteTableLayout. */
  public void testCalculateColumnOffsets() {
    System.out.println("testCalculateColumnOffsets");
    
    // Add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
  /** Test of calculateColumnSizes method, of class puce.swing.InfiniteTableLayout. */
  public void testCalculateColumnSizes() {
    System.out.println("testCalculateColumnSizes");
    
    // Add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
  /** Test of calculateRowOffsets method, of class puce.swing.InfiniteTableLayout. */
  public void testCalculateRowOffsets() {
    System.out.println("testCalculateRowOffsets");
    
    // Add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
  /** Test of calculateRowSizes method, of class puce.swing.InfiniteTableLayout. */
  public void testCalculateRowSizes() {
    System.out.println("testCalculateRowSizes");
    
    // Add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
  
  /** Test of deleteColumn method, of class puce.swing.InfiniteTableLayout. */
  public void testDeleteColumn() {
    System.out.println("testDeleteColumn");
    
    // Add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
  /** Test of deleteRow method, of class puce.swing.InfiniteTableLayout. */
  public void testDeleteRow() {
    System.out.println("testDeleteRow");
    
    // Add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
  /** Test of drawGrid method, of class puce.swing.InfiniteTableLayout. */
  public void testDrawGrid() {
    System.out.println("testDrawGrid");
    
    // Add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
  /** Test of insertColumn method, of class puce.swing.InfiniteTableLayout. */
  public void testInsertColumn() {
    System.out.println("testInsertColumn");
    
    // Add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
  /** Test of insertRow method, of class puce.swing.InfiniteTableLayout. */
  public void testInsertRow() {
    System.out.println("testInsertRow");
    
    // Add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
  /** Test of layoutContainer method, of class puce.swing.InfiniteTableLayout. */
  public void testLayoutContainer() {
    System.out.println("testLayoutContainer");
    tl.layoutContainer(pane);
    assertEquals(new Rectangle(25, 25, 150, 50), customizer.getBounds());
  }
  
  /** Test of minimumLayoutSize method, of class puce.swing.InfiniteTableLayout. */
  public void testMinimumLayoutSize() {
    System.out.println("testMinimumLayoutSize");
    
    // Add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
  /** Test of preferredLayoutSize method, of class puce.swing.InfiniteTableLayout. */
  public void testPreferredLayoutSize() {
    System.out.println("testPreferredLayoutSize");
    
    // Add your test code below by replacing the default call to fail.
    fail("The test case is empty.");
  }
  
  
  

  
  /** Test of columnIndex method, of class puce.swing.InfiniteTableLayout. */
  public void testColumnIndex() {
    System.out.println("testColumnIndex");
    assertEquals(2, tl.columnIndex(10));
    assertEquals(2, tl.columnIndex(12));
    assertEquals(3, tl.columnIndex(13));
    assertEquals(3, tl.columnIndex(15));
  }
  
  /** Test of rowIndex method, of class puce.swing.InfiniteTableLayout. */
  public void testRowIndex() {
    System.out.println("testRowIndex");
    assertEquals(2, tl.rowIndex(10));
    assertEquals(2, tl.rowIndex(12));
    assertEquals(3, tl.rowIndex(13));
    assertEquals(3, tl.rowIndex(15));
  }
  
  public static Test suite() {
    TestSuite suite = new TestSuite(InfiniteTableLayoutTest.class);
    
    return suite;
  }
  
  
  
  /** Test of colSpan method, of class puce.swing.InfiniteTableLayout. */
  public void testColSpan() {
    System.out.println("testColSpan");
    assertEquals(2, tl.colSpan(5, 10));
    assertEquals(2, tl.colSpan(5, 12));
    assertEquals(3, tl.colSpan(5, 13));
    assertEquals(3, tl.colSpan(5, 15));
  }
  
  /** Test of rowSpan method, of class puce.swing.InfiniteTableLayout. */
  public void testRowSpan() {
    System.out.println("testRowSpan");
    assertEquals(2, tl.rowSpan(5, 10));
    assertEquals(2, tl.rowSpan(5, 12));
    assertEquals(3, tl.rowSpan(5, 13));
    assertEquals(3, tl.rowSpan(5, 15));
  }
  
  /** Test of width method, of class puce.swing.InfiniteTableLayout. */
  public void testWidth() {
    System.out.println("testWidth");
    assertEquals(15, tl.width(5, 3));
  }
  
  /** Test of height method, of class puce.swing.InfiniteTableLayout. */
  public void testHeight() {
    System.out.println("testHeight");
    assertEquals(15, tl.height(5, 3));
  }
  
  // Add test methods here, they have to start with 'test' name.
  // for example:
  // public void testHello() {}
  
  
}
