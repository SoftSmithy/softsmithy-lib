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
package org.softsmithy.lib.util;

import java.io.InputStream;
import java.net.URL;

/**
 * A resource loader.
 *
 * @author puce
 */
public class ResourceLoader {

    private final ClassLoader classLoader;
    private final String defaultResourceDir;

    public ResourceLoader(Class<?> type) {
        this(type.getClassLoader(), type.getPackage());
    }

    public ResourceLoader(ClassLoader classLoader, Package defaultPackage) {
        this(classLoader, defaultPackage.getName());
    }

    public ResourceLoader(ClassLoader classLoader, String defaultPackage) {
        this.classLoader = classLoader;
        this.defaultResourceDir = getResourceDir(defaultPackage);
    }

    private String getResourceDir(String defaultPackage) {
        String resourceDir = defaultPackage.replace(".", "/");
        if (resourceDir.startsWith("/")) {
            resourceDir = resourceDir.substring(1);
        }
        if (!resourceDir.endsWith("/")) {
            resourceDir = resourceDir + "/";
        }
        return resourceDir;
    }

    public URL getResource(String resourcePath) {
        String absoluteResourcePath = getAbsoluteResourcePath(resourcePath);
        return classLoader.getResource(absoluteResourcePath);
    }

    public InputStream getResourceAsStream(String resourcePath) {
        String absoluteResourcePath = getAbsoluteResourcePath(resourcePath);
        return classLoader.getResourceAsStream(absoluteResourcePath);
    }

    private String getAbsoluteResourcePath(String resourcePath) {
        if (resourcePath.startsWith("/")) {
            return resourcePath.substring(1);
        } else {
            return defaultResourceDir + resourcePath;
        }
    }
}
