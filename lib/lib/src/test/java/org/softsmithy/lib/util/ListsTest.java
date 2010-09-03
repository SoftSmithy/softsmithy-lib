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
 * ListsTest.java
 * JUnit based test
 *
 * Created on 20. Mai 2007, 13:27
 */

package org.softsmithy.lib.util;

import java.util.Arrays;
import junit.framework.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author puce
 */
public class ListsTest extends TestCase {
    
    private Comparator testClassComparator;
    
    public ListsTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        testClassComparator = new Comparator() {
            public int compare(Object object1, Object object2) {
                TestClass testObj1 = (TestClass) object1;
                TestClass testObj2 = (TestClass) object2;
                int prop1Comparison = testObj1.getProp1().compareTo(testObj2.getProp1());
                if (prop1Comparison != 0){
                    return prop1Comparison;
                } else {
                    return testObj1.getProp2().compareTo(testObj2.getProp2());
                }
            }
        };
        
       
    }
    
    @Override
    protected void tearDown() throws Exception {
    }
    
    private List createList(){
          // create new instances with the same content
         TestClass obj1 = new TestClass("obj1Prop1", "obj1Prop2");
        TestClass obj2 = new TestClass("obj2Prop1", "obj2Prop2");
        TestClass obj3 = new TestClass("obj3Prop1", "obj3Prop2");
        TestClass obj4 = new TestClass("obj4Prop1", "obj4Prop2");
        TestClass obj5 = new TestClass("obj5Prop1", "obj5Prop2");
        return Arrays.asList(new TestClass[]{obj1, obj2, obj3, obj4, obj5});
    }
    /**
     * Test of equals method, of class org.softsmithy.lib.util.Lists.
     */
    public void testEquals() {
        System.out.println("equals");
        
        List listA = createList();
        List listB = createList();
        
        
        assertTrue(Lists.equals(listA, listB, new ComparatorEqualityVerifier(testClassComparator)));

    }
    
    /**
     * Test of equalsIgnoreOrder method, of class org.softsmithy.lib.util.Lists.
     */
    public void testEqualsIgnoreOrder() {
        System.out.println("equalsIgnoreOrder");
        
        List listA = createList();
        List listB = createList();
        Collections.reverse(listB);


        assertTrue(Lists.equalsIgnoreOrder(listA, listB, testClassComparator));
        
    }
    
    private static class TestClass{
        /**
         * Holds value of property prop1.
         */
        private final String prop1;
        
        
        /**
         * Holds value of property prop2.
         */
        private final String prop2;
        
        public TestClass(String prop1, String prop2){
            this.prop1 = prop1;
            this.prop2 = prop2;
        }
        
        /**
         * Getter for property prop1.
         * @return Value of property prop1.
         */
        public String getProp1() {
            return this.prop1;
        }
        
        
        /**
         * Getter for property prop2.
         * @return Value of property prop2.
         */
        public String getProp2() {
            return this.prop2;
        }
        
    }
    
    
}
