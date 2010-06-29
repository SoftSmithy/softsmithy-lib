/*
 * StreamsTest.java
 * JUnit based test
 *
 * Created on 28. Februar 2007, 19:46
 */

package org.softsmithy.lib.io;

import java.nio.ByteBuffer;
import junit.framework.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author brunner
 */
public class StreamsTest extends TestCase {
    
    public StreamsTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of readLines method, of class org.softsmithy.lib.io.Streams.
     */
    public void testReadLines() throws Exception {
        System.out.println("readLines");
        
        InputStream input = null;
        
        String[] expResult = null;
        String[] result = Streams.readLines(input);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class org.softsmithy.lib.io.Streams.
     */
    public void testCopy() throws Exception {
        System.out.println("copy");
        byte[] testInBytes = {5, 8, -128, 127, 42};

        InputStream in = new  ByteArrayInputStream(testInBytes);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        Streams.copy(in, out);
        
        assertTrue(Arrays.equals(testInBytes, out.toByteArray()));
    }
    
}
