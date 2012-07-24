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
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

/**
 *
 * @author puce
 */
//TODO: package ok? or better: org.softsmithy.lib.util.jar ?
public class JarFiles {

    /**
     * The jar URI prefix "jar:"
     */
    private static String FULL_JAR_URI_PREFIX = "jar:";
    /**
     * Length of the jar URI prefix "jar:"
     */
    private static int FULL_JAR_URI_PREFIX_LENGTH = 4;

    private JarFiles() {
    }

    /**
     * Gets the {@link Path} of the JAR for the specified type.
     *
     * @param type a type included in the JAR
     * @return the {@link Path} of the JAR for the specified type
     * @throws URISyntaxException
     */
    // TODO: useful enough to keep it?
    public static Path getJarPath(Class<?> type) throws URISyntaxException {
        return Paths.get(getJarURI(type));
    }

    /**
     * Gets the {@link URI} of the JAR for the specified type.
     *
     * @param type a type included in the JAR
     * @return the {@link URI} of the JAR for the specified type
     * @throws URISyntaxException
     */
    public static URI getJarURI(Class<?> type) throws URISyntaxException {
        return getJarURI(type.getResource(getRoot() + type.getName().replace(".", "/") + ".class").toURI());
    }

    // TODO: in OSGi there might be different roots.
    private static String getRoot() {
        return "/";
    }

    /**
     * Extracts the jar URI part from a jar resource URI ({@code jar:<jarURIPart>!/{entry}}).
     *
     * @param jarResourceURI a jar resource URI
     * @return the jar URI
     */
    public static URI getJarURI(URI jarResourceURI) {
        String jarResourceURIString = jarResourceURI.toString();
        int endOfJarPathIndex = jarResourceURIString.indexOf("!/");
        String jarURIString = endOfJarPathIndex >= 0 ? jarResourceURIString.substring(0, endOfJarPathIndex) : jarResourceURIString;
        if (jarURIString.startsWith(FULL_JAR_URI_PREFIX)) {
            jarURIString = jarURIString.substring(FULL_JAR_URI_PREFIX_LENGTH);
        }
        return URI.create(jarURIString);
    }

    /**
     * Creates a new JAR {@link FileSystem} for the specified JAR {@link URI}.
     *
     * @param jarURI the JAR {@link URI}
     * @return a new JAR {@link FileSystem} for the specified JAR {@link URI}
     * @throws IOException
     */
    public static FileSystem newJarFileSystem(URI jarURI) throws IOException {
        return FileSystems.newFileSystem(URI.create("jar:" + jarURI), Collections.<String, Object>emptyMap());
    }
}
