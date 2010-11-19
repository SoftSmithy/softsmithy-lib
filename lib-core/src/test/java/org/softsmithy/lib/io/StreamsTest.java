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
 * StreamsTest.java
 * JUnit based test
 *
 * Created on 28. Februar 2007, 19:46
 */

package org.softsmithy.lib.io;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import junit.framework.TestCase;
import org.softsmithy.lib.util.Strings;

/**
 *
 * @author brunner
 */
public class StreamsTest extends TestCase {
    
    public StreamsTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    /**
     * Test of readLines method, of class org.softsmithy.lib.io.Streams.
     */
    public void testReadLines() throws Exception {
        System.out.println("readLines");
        
        String[] expResult = {"This is the first line", "this the second line", "and this the third."};
        String buffer = Strings.join(expResult, "\n");
        ByteArrayOutputStream byteArrayOutputStream =
                new ByteArrayOutputStream();
        OutputStreamWriter outputStreamWriter =
                new OutputStreamWriter(byteArrayOutputStream);
        outputStreamWriter.write(buffer);
        outputStreamWriter.close();

        InputStream input = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        
        
        String[] result = Streams.readLines(input);

        input.close();

        assertTrue(Arrays.equals(expResult, result));
        
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class org.softsmithy.lib.io.Streams.
     */
    public void testCopyStream() throws Exception {
        System.out.println("testCopyStream");
        byte[] testInBytes = {5, 8, -128, 127, 42};

        InputStream in = new  ByteArrayInputStream(testInBytes);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        Streams.copy(in, out);
        
        assertTrue(Arrays.equals(testInBytes, out.toByteArray()));
    }

     /**
     * Test of copy method, of class org.softsmithy.lib.io.Streams.
     */
    public void testCopyReader() throws Exception {
        System.out.println("testCopyReader");
        String testString = "This is a test string!\n And this another liner.\n And this a third.";

        Reader in = new StringReader(testString);
        Writer out = new StringWriter();

        Streams.copy(in, out);

        assertEquals(testString, out.toString());
    }
}
