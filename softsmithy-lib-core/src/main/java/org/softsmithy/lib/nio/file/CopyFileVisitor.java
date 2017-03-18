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
import java.nio.file.CopyOption;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * A {@link FileVisitor} which copies the source {@link Path} to the target {@link Path}. <br>
 * <br>
 * If the source is a directory, it content gets copied recursively. <br>
 * <br>
 * The source and the target don't have to be on the same file system and thus this class can be used to e.g. extract a
 * directory from a jar/ zip file.
 *
 * @see Files#walkFileTree(java.nio.file.Path, java.nio.file.FileVisitor)
 * @see #copy(java.nio.file.Path, java.nio.file.Path, java.nio.file.CopyOption...)
 * @author puce
 */
public class CopyFileVisitor extends SimpleFileVisitor<Path> {

    private final Path source;
    private final Path target;
    private final CopyOption[] options;

    /**
     * Creates a new instance of this class.
     *
     * @param source the source
     * @param target the target
     * @param options copy options
     * @see #copy(java.nio.file.Path, java.nio.file.Path, java.nio.file.CopyOption...)
     */
    public CopyFileVisitor(Path source, Path target, CopyOption... options) {
        this.source = source;
        this.target = target;
        this.options = options;
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
                Files.copy(dir, targetDir, options);
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
            Files.copy(file, targetFile, options);
        }
        return FileVisitResult.CONTINUE;
    }

    /**
     * Copies the source to the target. <br>
     * <br>
     * If the source is a directory, its content gets copied recursively. <br>
     * <br>
     * The source and the target don't have to be on the same file system and thus this method can be used to e.g.
     * extract a directory from a jar/ zip file.
     *
     * @param source the source
     * @param target the target
     * @param options the copy options
     * @return the source
     * @throws IOException if an I/O error is thrown
     */
    public static Path copy(Path source, Path target, CopyOption... options) throws IOException {
        return Files.walkFileTree(source, new CopyFileVisitor(source, target, options));
    }
}
