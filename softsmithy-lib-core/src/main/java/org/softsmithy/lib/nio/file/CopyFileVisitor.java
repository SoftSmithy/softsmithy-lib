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
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * A {@link FileVisitor} which copies the source {@link Path} to the target {@link Path}.
 *
 * If the source is a directory, it content gets copied recursively.
 *
 * The source and the target don't have to be on the same file system and thus this class can be used to e.g. extract a
 * directory from a jar/ zip file.
 *
 * @see Files#walkFileTree(java.nio.file.Path, java.nio.file.FileVisitor)
 * @see #copy(java.nio.file.Path, java.nio.file.Path)
 * @author puce
 */
public class CopyFileVisitor extends SimpleFileVisitor<Path> {

    private final Path source;
    private final Path target;

    /**
     * Creates a new instance of this class.
     *
     * @see #copy(java.nio.file.Path, java.nio.file.Path)
     * @param source the source
     * @param target the target
     */
    public CopyFileVisitor(Path source, Path target) {
        this.source = source;
        this.target = target;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
            throws IOException {
        Path targetDir = PathUtils.resolve(target, source.relativize(dir));
        try {
            if (!Files.exists(targetDir)) {
                Files.copy(dir, targetDir);
            }
        } catch (FileAlreadyExistsException e) {
            if (!Files.isDirectory(targetDir)) {
                throw e;
            }
        }
        return FileVisitResult.CONTINUE;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path targetFile = PathUtils.resolve(target, source.relativize(file));
        if (!Files.exists(targetFile)) {
            Files.copy(file, targetFile);
        }
        return FileVisitResult.CONTINUE;
    }

    /**
     * Copies the source to the target.
     *
     * If the source is a directory, its content gets copied recursively.
     *
     * The source and the target don't have to be on the same file system and thus this method can be used to e.g.
     * extract a directory from a jar/ zip file.
     *
     * @param source
     * @param target
     * @return
     * @throws IOException
     */
    public static Path copy(Path source, Path target) throws IOException {
        return Files.walkFileTree(source, new CopyFileVisitor(source, target));
    }
}
