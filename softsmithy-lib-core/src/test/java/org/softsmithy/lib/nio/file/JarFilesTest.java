/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.lib.nio.file;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author puce
 */
public class JarFilesTest {


    @Test
    public void testGetJarURI4JarResourceURI() throws IOException {
        Path testJarFilePath = JarFilesTestUtils.createEmptyTempJarFile("test", ".jar");
        Path bPath;
        try (FileSystem jarFS = JarFilesTestUtils.newJarFileSystem(testJarFilePath)) {
            Path rootPath = jarFS.getRootDirectories().iterator().next();
            bPath = rootPath.resolve("b.txt");
            Files.createFile(bPath);
        }
        String bPathString = bPath.toString();
        if (bPathString.startsWith("/")) { // Unix/ Linux
            bPathString = bPathString.substring(1);
        }
        URI jarResourceURI = URI.create("jar:" + testJarFilePath.toUri() + "!/" + bPathString);
        URI jarURI = JarFiles.getJarURI(jarResourceURI);
        assertEquals(testJarFilePath.toUri(), jarURI);
    }
}
