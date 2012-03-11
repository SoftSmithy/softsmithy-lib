/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.lib.nio.file;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author puce
 */
//TODO: package ok? or better: org.softsmithy.lib.util.jar ?
public class JarFiles {

    /**
     * Length of the jar URI prefix "jar:file:"
     */
    private static int FULL_JAR_URI_PREFIX_LENGTH = 9;

    private JarFiles() {
    }

    public static Path getJarPath(Class<?> type) throws URISyntaxException {
        return Paths.get(getJarURI(type).toString().substring(FULL_JAR_URI_PREFIX_LENGTH));
    }

    public static URI getJarURI(Class<?> type) throws URISyntaxException {
        return getJarURI(type.getResource("/" + type.getName().replace(".", "/") + ".class").toURI());
    }

    public static URI getJarURI(URI jarResourceURI) {
        String jarResourceURIString = jarResourceURI.toString();
        int endOfJarPathIndex = jarResourceURIString.indexOf("!/");
        return URI.create(jarResourceURIString.substring(0, endOfJarPathIndex));
    }
}
