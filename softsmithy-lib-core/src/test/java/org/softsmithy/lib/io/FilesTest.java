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
 * FilesTest.java
 * JUnit based test
 *
 * Created on 1. März 2007, 12:51
 */
package org.softsmithy.lib.io;

import java.io.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author brunner
 */
public class FilesTest{


    /**
     * Test of delete method, of class org.softsmithy.lib.io.Files.
     */
    @Test
    public void testDelete() throws IOException {
        System.out.println("delete");

        File dir;
        do {
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
    @Test
    public void testGetPathNames() {
        System.out.println("getPathNames");

        File path = new File(new File("path1", "path2"), "path3");

        String[] expResult = new String[]{"path1", "path2", "path3"};
        String[] result1 = Files.getPathNames(path);
        assertTrue(Arrays.equals(expResult, result1));
        
        File absolutePath = new File(File.listRoots()[0], path.toString());
        String[] result2 = Files.getPathNames(absolutePath);
        assertTrue(Arrays.equals(expResult, result2));
       
        assertEquals(path, Files.getFile(Files.getRoot(path), Files.getPathNames(path)));
        
        assertEquals(absolutePath, Files.getFile(Files.getRoot(absolutePath), Files.getPathNames(absolutePath)));

    }

    @Test
    public void testGetRoot() {
        File expectedRoot = File.listRoots()[0];

        File root1 = Files.getRoot(expectedRoot);
        assertEquals(expectedRoot, root1);

        File root2 = Files.getRoot(new File(expectedRoot, "foo"));
        assertEquals(expectedRoot, root2);
        
        File root3 = Files.getRoot(new File(new File(expectedRoot, "foo"), "bar"));
        assertEquals(expectedRoot, root3);
        
        File root4 = Files.getRoot(new File("bar"));
        assertEquals(null, root4);
    }
    
}
