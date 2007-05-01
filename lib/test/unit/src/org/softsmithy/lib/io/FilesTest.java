/*
 * FilesTest.java
 * JUnit based test
 *
 * Created on 1. März 2007, 12:51
 */

package org.softsmithy.lib.io;

import junit.framework.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author brunner
 */
public class FilesTest extends TestCase {
    
    public FilesTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of getExtension method, of class org.softsmithy.lib.io.Files.
     */
    public void testGetExtension() {
        System.out.println("getExtension");
        
        File f = null;
        
        String expResult = "";
        String result = Files.getExtension(f);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstName method, of class org.softsmithy.lib.io.Files.
     */
    public void testGetFirstName() {
        System.out.println("getFirstName");
        
        File f = null;
        
        String expResult = "";
        String result = Files.getFirstName(f);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class org.softsmithy.lib.io.Files.
     */
    public void testGetName() {
        System.out.println("getName");
        
        File f = null;
        
        String expResult = "";
        String result = Files.getName(f);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readLines method, of class org.softsmithy.lib.io.Files.
     */
    public void testReadLines() throws Exception {
        System.out.println("readLines");
        
        File file = null;
        
        String[] expResult = null;
        String[] result = Files.readLines(file);
        assertEquals(expResult, result);
        
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class org.softsmithy.lib.io.Files.
     */
    public void testDelete() throws IOException {
        System.out.println("delete");
        
        File dir;
        do{
            dir = new File(System.getProperty("java.io.tmpdir"), "filesTest-" + Calendar.getInstance().getTimeInMillis());
        } while (dir.exists());
        dir.mkdirs();
        File dir1 = new File(dir, "dir1");
        dir1.mkdir();
        File dir2 = new File(dir, "dir2");
        dir2.mkdir();
        File file1 = new File(dir, "file1");
        file1.createNewFile();
        File file2 = new File(dir1, "file2");
        file2.createNewFile();
        

        boolean result = Files.delete(dir, true);
        assertTrue(result);
        assertFalse(dir.exists());
        
    }

    /**
     * Test of getPathNames method, of class org.softsmithy.lib.io.Files.
     */
    public void testGetPathNames() {
        System.out.println("getPathNames");
        
        File path = new File(new File("path1", "path2"), "path3");
        
        String[] expResult = new String[]{"path1", "path2", "path3"};
        String[] result = Files.getPathNames(path);
        assertTrue(Arrays.equals(expResult, result));
        
    }
    
}
