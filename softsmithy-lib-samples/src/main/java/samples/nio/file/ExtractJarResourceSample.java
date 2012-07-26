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
package samples.nio.file;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.softsmithy.lib.nio.file.CopyFileVisitor;
import org.softsmithy.lib.nio.file.JarFiles;

/**
 *
 * @author puce
 */
public class ExtractJarResourceSample {

    /**
     * java
     *
     * @param args srcJarPath targetDirPath resourcePathString
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Path targetDirPath = Paths.get(args[0]);

        ExtractJarResourceSample extractJarResourceSample = new ExtractJarResourceSample();
        extractJarResourceSample.extractResource("/data", targetDirPath);
    }

    public void extractResource(String resourcePathString, Path targetDirPath) throws IOException, URISyntaxException {
        URI jarURI = JarFiles.getJarURI(ExtractJarResourceSample.class);
        try (FileSystem jarFS = JarFiles.newJarFileSystem(jarURI)) {
            Path resourcePath = jarFS.getPath(resourcePathString);

            CopyFileVisitor.copy(resourcePath, targetDirPath);
        }
    }
}
