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

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A utility class for {@link Path}s.
 *
 * @author puce
 */
public class PathUtils {

    private PathUtils() {
    }

    /**
     * Resolves paths, even if their file system are different.
     *
     * If the file system is the same, {@link Path#resolve(java.nio.file.Path)} is used.
     *
     * If {@code path2} is {@link Path#isAbsolute() absolute}, {@code path2} is returned.
     *
     * Else the resolved path gets calculated.
     *
     * @param path1 the given path
     * @param path2 the path to resolve against the given path
     * @return the resolved path
     */
    public static Path resolve(Path path1, Path path2) {
        if (path1.getFileSystem().equals(path2.getFileSystem())) {
            return path1.resolve(path2);
        } else if (path2.isAbsolute()) {
            return path2;
        } else {
            Path resolvedPath = path1;
            for (Path pathPart : path2) {
                resolvedPath = resolvedPath.resolve(pathPart.toString());
            }
            return resolvedPath;
        }
    }

    /**
     * Gets the extension of a file.
     *
     * @param path the file path whose extension should be fetched
     * @return the extension of the specified file path
     */
    public static String getExtension(Path path) {
        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException("Not a regular file: " + path.toString());
        }
        String extension = "";
        String fileName = path.getFileName().toString();
        int index = fileName.lastIndexOf('.');

        if (index > 0 && index < fileName.length() - 1) {
            extension = fileName.substring(index + 1).toLowerCase();
        }
        return extension;
    }
}
