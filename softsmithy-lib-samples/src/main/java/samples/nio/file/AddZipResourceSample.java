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
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.softsmithy.lib.nio.file.CopyFileVisitor;
import org.softsmithy.lib.nio.file.JarFiles;
import org.softsmithy.lib.nio.file.PathUtils;

/**
 * Adds a source directory to a ZIP file.
 *
 * @author puce
 */
public class AddZipResourceSample {

    /**
     * Adds a source directory to a ZIP file.
     *
     * @param args zipPath targetDirPath srcPath targetInZipPath
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Path zipPath = Paths.get(args[0]);
        Path targetDirPath = Paths.get(args[1]);
        Path srcPath = Paths.get(args[2]);
        String targetInZipPath = args[3];

        AddZipResourceSample addZipResourceSample = new AddZipResourceSample();
        addZipResourceSample.addResource(zipPath, targetDirPath, srcPath, targetInZipPath);
    }

    public void addResource(Path zipPath, Path targetDirPath, Path srcPath, String targetInZipPathString) throws IOException {
        Path targetZipPath = copyZipFile(zipPath, targetDirPath);

        try (FileSystem jarFS = JarFiles.newJarFileSystem(targetZipPath.toUri())) {
            Path targetInZipPath = jarFS.getPath(targetInZipPathString);

            // Adds the src directory name to the zip. You can omit this if you just want to copy the contents.
            Path finalTargetInZipPath = PathUtils.resolve(targetInZipPath, srcPath.getFileName());
            Files.createDirectories(finalTargetInZipPath);

            CopyFileVisitor.copy(srcPath, finalTargetInZipPath);
        }
    }

    private Path copyZipFile(Path zipPath, Path targetDirPath) throws IOException {
        Files.createDirectories(targetDirPath);
        Path targetZipPath = targetDirPath.resolve(zipPath.getFileName());
        Files.copy(zipPath, targetZipPath);
        return targetZipPath;
    }
}
