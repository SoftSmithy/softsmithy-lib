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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.jar.JarOutputStream;

/**
 *
 * @author puce
 */
class JarFilesTestUtils {

    private JarFilesTestUtils() {
    }

    public static FileSystem newJarFileSystem(Path jarFilePath) throws IOException {
        return FileSystems.newFileSystem(URI.create("jar:" + jarFilePath.toUri().toString()),
                new HashMap<String, Object>());
    }

    public static Path createEmptyTempJarFile(String prefix, String suffix) throws IOException {
        Path testJarPath = Files.createTempFile(prefix, suffix);
        File testFile = testJarPath.toFile();
        testFile.deleteOnExit();
        try (JarOutputStream jos = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(testFile)))) {
        }
        return testJarPath;
    }
}
