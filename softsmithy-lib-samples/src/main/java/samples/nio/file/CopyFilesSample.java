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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.softsmithy.lib.nio.file.CopyFileVisitor;

/**
 *
 * @author puce
 */
public class CopyFilesSample {

    /**
     * java
     *
     * @param args srcPath targetDirPath
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Path srcPath = Paths.get(args[0]);
        Path targetDirPath = Paths.get(args[1]);

        CopyFilesSample copyFilesSample = new CopyFilesSample();
        copyFilesSample.copyFiles(srcPath, targetDirPath);

    }

    public void copyFiles(Path srcPath, Path targetDirPath) throws IOException {
        // Adds the src directory name to the target directory. You can omit this if you just want to copy the contents.
        Path finalTargetPath = targetDirPath.resolve(srcPath.getFileName());
        Files.createDirectories(finalTargetPath);

        CopyFileVisitor.copy(srcPath, finalTargetPath);
    }
}
