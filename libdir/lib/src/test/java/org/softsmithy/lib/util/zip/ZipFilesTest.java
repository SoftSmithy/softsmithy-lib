/*
 *                  Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

/*
 * ZipFilesTest.java
 * JUnit based test
 *
 * Created on 1. März 2007, 10:12
 */

package org.softsmithy.lib.util.zip;


import java.io.File;
import java.util.zip.ZipFile;
import junit.framework.TestCase;

/**
 *
 * @author brunner
 */
public class ZipFilesTest extends TestCase {
    
    public ZipFilesTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
    }

    @Override
    protected void tearDown() throws Exception {
    }

    /**
     * Test of extract method, of class org.softsmithy.lib.util.zip.ZipFiles.
     */
//    public void testExtract() throws Exception {
//        System.out.println("extract");
//
//        String testZip = "/home/brunner/JavaApplication1.zip"; // TODO: other zip
//        String toDirName = "/home/brunner/tmp"; // TODO: other dir
//        ZipFile zipFile = new ZipFile(testZip);
//        File toDir = new File(toDirName);
//
//        ZipFiles.extract(zipFile, toDir);
//
//        // TODO: delete extracted directories and files
//        // TODO review the generated test code and remove the default call to fail.
//        assertTrue("The test case is a prototype.", true);
//    }

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
        
        String result3 = ZipFiles.createEntryName(new File(File.separator + file.getPath()));
        assertEquals(expResult, result3);
    }
    
}
