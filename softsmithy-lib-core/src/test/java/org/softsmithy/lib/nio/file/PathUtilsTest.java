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

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author puce
 */
public class PathUtilsTest {


    /**
     * Test of resolve method, of class PathUtils.
     */
    @Test
    public void testResolve() throws IOException {
        System.out.println("resolve");
        Path testJarPath = JarFilesTestUtils.createEmptyTempJarFile("test", ".jar");
        try (FileSystem jarFS = JarFilesTestUtils.newJarFileSystem(testJarPath)) {
            Path rootPath = jarFS.getRootDirectories().iterator().next();
            Path firstPath = rootPath.resolve("first");
            Path secondPath = firstPath.resolve("second");
            Files.createDirectories(secondPath);
            Path bPath = secondPath.resolve("b.txt");
            Files.createFile(bPath);

            Path targetDirPath = Files.createTempDirectory("targetDir");
            targetDirPath.toFile().deleteOnExit();

            // path1: default file system, path2: relative path in Jar file system
            Path targetBPath = PathUtils.resolve(targetDirPath, rootPath.relativize(bPath));
            assertEquals(targetDirPath.resolve(Paths.get("first", "second", "b.txt")), targetBPath);

            // path1: Jar file system, path2: relative path in default file system
            assertEquals(secondPath.resolve("third").resolve("forth"), PathUtils.resolve(secondPath, Paths.get("third",
                    "forth")));

            // path1: default file system, path2: absolute path in Jar file system
            assertEquals(bPath, PathUtils.resolve(targetDirPath, bPath));

            // path1: Jar file system, path2: absolute path in default file system
            assertEquals(targetDirPath, PathUtils.resolve(bPath, targetDirPath));


            // path1: default file system, path2: relative path in default file system
            assertEquals(targetDirPath.resolve(Paths.get("first", "second", "b.txt")), PathUtils.resolve(targetDirPath,
                    Paths.get("first", "second", "b.txt")));


            // path1: default file system, path2: absolute path in default file system
            Path targetDir2Path = Files.createTempDirectory("targetDir2");
            targetDir2Path.toFile().deleteOnExit();
            Path b2Path = targetDir2Path.resolve(Paths.get("first", "second", "b2.txt"));
            assertEquals(b2Path, PathUtils.resolve(targetDirPath, b2Path));
        }
    }
}
