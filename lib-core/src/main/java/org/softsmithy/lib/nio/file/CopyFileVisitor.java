/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.lib.nio.file;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 *
 * @author puce
 */
public class CopyFileVisitor extends SimpleFileVisitor<Path> {

    private final Path source;
    private final Path target;

    public CopyFileVisitor(Path source, Path target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
            throws IOException {
        Path targetdir = target.resolve(source.relativize(dir));
        try {
            Files.copy(dir, targetdir);
        } catch (FileAlreadyExistsException e) {
            if (!Files.isDirectory(targetdir)) {
                throw e;
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {
        Path targetFile = target.resolve(source.relativize(file));
        if (!Files.exists(targetFile)) {
            Files.copy(file, targetFile);
        }
        return FileVisitResult.CONTINUE;
    }

    public static Path copy(Path source, Path target) throws IOException {
        return Files.walkFileTree(source, new CopyFileVisitor(source, target));
    }
}
