/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
class JarFiles {

    private JarFiles() {
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
