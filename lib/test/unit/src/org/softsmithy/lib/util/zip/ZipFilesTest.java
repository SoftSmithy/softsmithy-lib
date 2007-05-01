/*
 * ZipFilesTest.java
 * JUnit based test
 *
 * Created on 1. März 2007, 10:12
 */

package org.softsmithy.lib.util.zip;

import junit.framework.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.softsmithy.lib.io.Streams;
import org.softsmithy.lib.io.Files;
import org.softsmithy.lib.util.Strings;

/**
 *
 * @author brunner
 */
public class ZipFilesTest extends TestCase {
    
    public ZipFilesTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of extract method, of class org.softsmithy.lib.util.zip.ZipFiles.
     */
    public void testExtract() throws Exception {
        System.out.println("extract");
        
        String testZip = "/home/brunner/JavaApplication1.zip"; // TODO: other zip
        String toDirName = "/home/brunner/tmp"; // TODO: other dir
        ZipFile zipFile = new ZipFile(testZip);
        File toDir = new File(toDirName);
        
        ZipFiles.extract(zipFile, toDir);
        
        // TODO: delete extracted directories and files
        // TODO review the generated test code and remove the default call to fail.
        assertTrue("The test case is a prototype.", true);
    }

    /**
     * Test of createEntryName method, of class org.softsmithy.lib.util.zip.ZipFiles.
     */
    public void testCreateEntryName() {
        System.out.println("createEntryName");
        
        File file = new File(new File("path1", "path2"), "path3");
        
        String expResult = "path1/path2/path3";
        String result1 = ZipFiles.createEntryName(file);
        assertEquals(expResult, result1);
        
        String result2 = ZipFiles.createEntryName(new File(File.listRoots()[0], file.getPath()));
        assertEquals(expResult, result2);
    }
    
}
