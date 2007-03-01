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
        
        String testZip = "/home/brunner/JavaApplication1.zip";
        String toDirName = "/home/brunner/tmp";
        ZipFile zipFile = new ZipFile(testZip);
        File toDir = new File(toDirName);
        
        ZipFiles.extract(zipFile, toDir);
        
        // TODO review the generated test code and remove the default call to fail.
        assertTrue("The test case is a prototype.", true);
    }
    
}
