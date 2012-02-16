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
package org.softsmithy.lib.nio.file;

import java.nio.file.FileSystem;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author puce
 */
public class CopyFileVisitorTest {

    public CopyFileVisitorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }



    /**
     * Test of copy method, of class CopyFileVisitor.
     */
    @Test
    public void testCopy() throws Exception {
        System.out.println("copy");
        Path testJarPath = JarFiles.createEmptyTempJarFile("test", ".jar");
        try (FileSystem jarFS = JarFiles.newJarFileSystem(testJarPath)) {
            Path rootPath = jarFS.getRootDirectories().iterator().next();
            Path firstPath = rootPath.resolve("first");
            Path secondPath = firstPath.resolve("second");
            Path thirdPath = secondPath.resolve("third");
            Files.createDirectories(thirdPath);
            Path aPath = firstPath.resolve("a.txt");
            Files.createFile(aPath);
            Path bPath = secondPath.resolve("b.txt");
            Files.createFile(bPath);
            Path cPath = thirdPath.resolve("c.txt");
            Files.createFile(cPath);
            
            
            Path targetDirPath = Files.createTempDirectory("targetDir");
            targetDirPath.toFile().deleteOnExit();
            
            CopyFileVisitor.copy(secondPath, targetDirPath);
            Path targetThirdPath = targetDirPath.resolve("third");
            assertTrue(Files.exists(targetThirdPath));
            assertTrue(Files.exists(targetDirPath.resolve("b.txt")));
            assertTrue(Files.exists(targetThirdPath.resolve("c.txt")));
        }
    }
}
