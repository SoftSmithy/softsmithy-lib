/*
 * JCustomizerTest.java
 * JUnit based test
 *
 * Created on 27. August 2002, 08:54
 */

package puce.swing;

import puce.swing.border.HandleBorder.*;
import java.awt.event.*;
import junit.framework.*;
import puce.swing.JCustomizer.StateManager.*;
import puce.swing.JCustomizerTest.StateManagerTest.*;
import puce.swing.JCustomizerTest.*;
import java.awt.*;
import junit.textui.*;

/**
 *
 * @author puce
 */
public class JCustomizerTest extends TestCase {
  
  private JCustomizer customizer;
  private JCustomizerPane pane;
  
  public JCustomizerTest(java.lang.String testName) {
    super(testName);
  }
  
  public static void main(java.lang.String[] args) {
    TestRunner.run(suite());
  }
  
  public static Test suite() {
    TestSuite suite = new TestSuite(JCustomizerTest.class);
    // suite.addTest(StateManagerTest.suite());
    
    return suite;
  }
  
  protected void setUp() throws java.lang.Exception {
    super.setUp();
    customizer = new JCustomizer();
    pane = new JCustomizerPane();
    pane.add(customizer, new DefaultTableConstraints(5,5, 30, 10));
    pane.doLayout();
  }
  
  /** Test of moveRel method, of class puce.swing.JCustomizer. */
  public void testMoveRel() {
    System.out.println("testMoveRel");
    customizer.moveRel(10, 15);
    pane.doLayout();
    Rectangle bounds = customizer.getBounds();
    assertEquals(35, bounds.x);
    assertEquals(40, bounds.y);
  }
  
  /** Test of resizeRel method, of class puce.swing.JCustomizer. */
  public void testResizeRel() {
    System.out.println("testResizeRel");
    customizer.resizeRel(-5, 5);
    pane.doLayout();
    Rectangle bounds = customizer.getBounds();
    assertEquals(145, bounds.width);
    assertEquals(55, bounds.height);
  }
  
  /** Test of reshapeRel method, of class puce.swing.JCustomizer. */
  public void testReshapeRel() {
    System.out.println("testReshapeRel");
    customizer.reshapeRel(10, 15, -5, 5);
    pane.doLayout();
    Rectangle bounds = customizer.getBounds();
    assertEquals(35, bounds.x);
    assertEquals(40, bounds.y);
    assertEquals(145, bounds.width);
    assertEquals(55, bounds.height);
  }
  
  
  public static class StateManagerTest extends TestCase {
    
    public StateManagerTest(java.lang.String testName) {
      super(testName);
    }
    
    public static void main(java.lang.String[] args) {
      TestRunner.run(suite());
    }
    
    public static Test suite() {
      TestSuite suite = new TestSuite(StateManagerTest.class);
      suite.addTest(StateTest.suite());
      suite.addTest(BoundStateTest.suite());
      suite.addTest(ResizeStateTest.suite());
      
      return suite;
    }
    
    /** Test of mouseClicked method, of class puce.swing.JCustomizer.StateManager. */
/*    public void testMouseClicked() {
      System.out.println("testMouseClicked");
 
      // Add your test code below by replacing the default call to fail.
      fail("The test case is empty.");
    }*/
    
    /** Test of mouseDragged method, of class puce.swing.JCustomizer.StateManager. */
/*    public void testMouseDragged() {
      System.out.println("testMouseDragged");
 
      // Add your test code below by replacing the default call to fail.
      fail("The test case is empty.");
    }*/
    
    /** Test of mouseEntered method, of class puce.swing.JCustomizer.StateManager. */
 /*   public void testMouseEntered() {
      System.out.println("testMouseEntered");
  
      // Add your test code below by replacing the default call to fail.
      fail("The test case is empty.");
    }*/
    
    /** Test of mouseExited method, of class puce.swing.JCustomizer.StateManager. */
 /*   public void testMouseExited() {
      System.out.println("testMouseExited");
  
      // Add your test code below by replacing the default call to fail.
      fail("The test case is empty.");
    }*/
    
    /** Test of mouseMoved method, of class puce.swing.JCustomizer.StateManager. */
  /*  public void testMouseMoved() {
      System.out.println("testMouseMoved");
   
      // Add your test code below by replacing the default call to fail.
      fail("The test case is empty.");
    }*/
    
    /** Test of mousePressed method, of class puce.swing.JCustomizer.StateManager. */
/*    public void testMousePressed() {
      System.out.println("testMousePressed");
 
      // Add your test code below by replacing the default call to fail.
      fail("The test case is empty.");
    }*/
    
    /** Test of mouseReleased method, of class puce.swing.JCustomizer.StateManager. */
 /*   public void testMouseReleased() {
      System.out.println("testMouseReleased");
  
      // Add your test code below by replacing the default call to fail.
      fail("The test case is empty.");
    }*/
    
    /** Test of focusGained method, of class puce.swing.JCustomizer.StateManager. */
  /*  public void testFocusGained() {
      System.out.println("testFocusGained");
   
      // Add your test code below by replacing the default call to fail.
      fail("The test case is empty.");
    }*/
    
    /** Test of focusLost method, of class puce.swing.JCustomizer.StateManager. */
 /*   public void testFocusLost() {
      System.out.println("testFocusLost");
  
      // Add your test code below by replacing the default call to fail.
      fail("The test case is empty.");
    }*/
    
    public static class StateTest extends TestCase {
      
      public StateTest(java.lang.String testName) {
        super(testName);
      }
      
      public static void main(java.lang.String[] args) {
        TestRunner.run(suite());
      }
      
      public static Test suite() {
        TestSuite suite = new TestSuite(StateTest.class);
        
        return suite;
      }
      
      /** Test of applyBorder method, of class puce.swing.JCustomizer.StateManager.State. */
/*      public void testApplyBorder() {
        System.out.println("testApplyBorder");
 
        // Add your test code below by replacing the default call to fail.
        fail("The test case is empty.");
      }*/
      
      /** Test of applyCursor method, of class puce.swing.JCustomizer.StateManager.State. */
 /*     public void testApplyCursor() {
        System.out.println("testApplyCursor");
  
        // Add your test code below by replacing the default call to fail.
        fail("The test case is empty.");
      }*/
      
      /** Test of getCustomizer method, of class puce.swing.JCustomizer.StateManager.State. */
    /*  public void testGetCustomizer() {
        System.out.println("testGetCustomizer");
     
        // Add your test code below by replacing the default call to fail.
        fail("The test case is empty.");
      }*/
      
      /** Test of mouseClicked method, of class puce.swing.JCustomizer.StateManager.State. */
 /*     public void testMouseClicked() {
        System.out.println("testMouseClicked");
  
        // Add your test code below by replacing the default call to fail.
        fail("The test case is empty.");
      }*/
      
      /** Test of mouseDragged method, of class puce.swing.JCustomizer.StateManager.State. */
    /*  public void testMouseDragged() {
        System.out.println("testMouseDragged");
     
        // Add your test code below by replacing the default call to fail.
        fail("The test case is empty.");
      }*/
      
      /** Test of mouseEntered method, of class puce.swing.JCustomizer.StateManager.State. */
 /*     public void testMouseEntered() {
        System.out.println("testMouseEntered");
  
        // Add your test code below by replacing the default call to fail.
        fail("The test case is empty.");
      }*/
      
      /** Test of mouseExited method, of class puce.swing.JCustomizer.StateManager.State. */
 /*     public void testMouseExited() {
        System.out.println("testMouseExited");
  
        // Add your test code below by replacing the default call to fail.
        fail("The test case is empty.");
      }*/
      
      /** Test of mouseMoved method, of class puce.swing.JCustomizer.StateManager.State. */
 /*     public void testMouseMoved() {
        System.out.println("testMouseMoved");
  
        // Add your test code below by replacing the default call to fail.
        fail("The test case is empty.");
      }*/
      
      /** Test of mousePressed method, of class puce.swing.JCustomizer.StateManager.State. */
 /*     public void testMousePressed() {
        System.out.println("testMousePressed");
  
        // Add your test code below by replacing the default call to fail.
        fail("The test case is empty.");
      }*/
      
      /** Test of mouseReleased method, of class puce.swing.JCustomizer.StateManager.State. */
  /*    public void testMouseReleased() {
        System.out.println("testMouseReleased");
   
        // Add your test code below by replacing the default call to fail.
        fail("The test case is empty.");
      }*/
      
    }
    
    public static class BoundStateTest extends TestCase {
      
      public BoundStateTest(java.lang.String testName) {
        super(testName);
      }
      
      public static void main(java.lang.String[] args) {
        TestRunner.run(suite());
      }
      
      public static Test suite() {
        TestSuite suite = new TestSuite(BoundStateTest.class);
        
        return suite;
      }
      
      /** Test of applyBorder method, of class puce.swing.JCustomizer.StateManager.BoundState. */
 /*     public void testApplyBorder() {
        System.out.println("testApplyBorder");
  
        // Add your test code below by replacing the default call to fail.
        fail("The test case is empty.");
      }*/
      
      /** Test of mouseMoved method, of class puce.swing.JCustomizer.StateManager.BoundState. */
  /*    public void testMouseMoved() {
        System.out.println("testMouseMoved");
   
        // Add your test code below by replacing the default call to fail.
        fail("The test case is empty.");
      }*/
      
      /** Test of getStartX method, of class puce.swing.JCustomizer.StateManager.BoundState. */
   /*   public void testGetStartX() {
        System.out.println("testGetStartX");
    
        // Add your test code below by replacing the default call to fail.
        fail("The test case is empty.");
      }*/
      
      /** Test of getStartY method, of class puce.swing.JCustomizer.StateManager.BoundState. */
  /*    public void testGetStartY() {
        System.out.println("testGetStartY");
   
        // Add your test code below by replacing the default call to fail.
        fail("The test case is empty.");
      }*/
      
      /** Test of mousePressed method, of class puce.swing.JCustomizer.StateManager.BoundState. */
   /*   public void testMousePressed() {
        System.out.println("testMousePressed");
    
        // Add your test code below by replacing the default call to fail.
        fail("The test case is empty.");
      }*/
      
      /** Test of mouseDragged method, of class puce.swing.JCustomizer.StateManager.BoundState. */
   /*   public void testMouseDragged() {
        System.out.println("testMouseDragged");
    
        // Add your test code below by replacing the default call to fail.
        fail("The test case is empty.");
      }*/
      
      /** Generated implementation of abstract class puce.swing.JCustomizer.StateManager.BoundState. Please fill dummy bodies of generated methods. */
      private class BoundStateImpl extends BoundState {
        
        public BoundStateImpl(JCustomizer customizer) {
          super(customizer);
        }
        
        public void processMouseDragged(MouseEvent e) {
          //fill the body in order to provide useful implementation
        }
        
      }
      
    }
    
    public static class ResizeStateTest extends TestCase {
      
      public ResizeStateTest(java.lang.String testName) {
        super(testName);
      }
      
      public static void main(java.lang.String[] args) {
        TestRunner.run(suite());
      }
      
      public static Test suite() {
        TestSuite suite = new TestSuite(ResizeStateTest.class);
        
        return suite;
      }
      
      /** Test of contains method, of class puce.swing.JCustomizer.StateManager.ResizeState. */
  /*    public void testContains() {
        System.out.println("testContains");
   
        // Add your test code below by replacing the default call to fail.
        fail("The test case is empty.");
      }*/
      
      /** Generated implementation of abstract class puce.swing.JCustomizer.StateManager.ResizeState. Please fill dummy bodies of generated methods. */
      private class ResizeStateImpl extends ResizeState {
        
        public ResizeStateImpl(JCustomizer customizer) {
          super(customizer);
        }
        
        public Handle getHandle() {
          //fill the body in order to provide useful implementation
          
          return null;
        }
        
        public void processMouseDragged(MouseEvent e) {
          //fill the body in order to provide useful implementation
        }
        
      }
      
    }
    
  }
  
  // Add test methods here, they have to start with 'test' name.
  // for example:
  // public void testHello() {}
  
  
}
