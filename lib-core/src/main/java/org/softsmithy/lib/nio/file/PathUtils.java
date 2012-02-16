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

import java.nio.file.Path;

/**
 *
 * @author puce
 */
public class PathUtils {

    private PathUtils() {
    }

    public static Path resolve(Path path1, Path path2) {
        if (path1.getFileSystem().equals(path2.getFileSystem())) {
            return path1.resolve(path2);
        } else {
            if (path2.isAbsolute()) {
                return path2;
            } else {
                Path resolvedPath = path1;
                for (Path pathPart : path2) {
                    resolvedPath = resolvedPath.resolve(pathPart.toString());
                }
                return resolvedPath;
            }
        }
    }
}
