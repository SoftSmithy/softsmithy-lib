/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.lib.nio.file;

import java.nio.file.Path;

/**
 *
 * @author puce
 */
public class PathUtils {

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
