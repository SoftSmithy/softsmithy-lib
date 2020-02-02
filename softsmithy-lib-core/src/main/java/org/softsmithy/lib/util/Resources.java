/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (GitHub user: puce77). All Rights Reserved.
 *
 * Contributor(s): .
 */
package org.softsmithy.lib.util;

/**
 * Utility class to work with resources in modular (Java 11+) applications.
 *
 * @version 3.0
 * @author puce
 */
public final class Resources {
    public static final char RESOURCE_PATH_DELIMITER = '/';
    private static final char PACKAGE_NAME_DELIMITER = '.';

    private Resources() {
    }

    /**
     * Converts a package name to module resource path.
     *
     * @param typePackage the package
     * @return the module resource path for the provided package.
     * @see #toModulePackageResourcePath(java.lang.String)
     * @see Module#getResourceAsStream(java.lang.String)
     */
    public static final String toModulePackageResourcePath(Package typePackage) {
        return toModulePackageResourcePath(typePackage.getName());
    }

    /**
     * Converts a package name to module resource path.
     *
     * @param packageName the package name
     * @return the module resource path for the provided package.
     * @see Module#getResourceAsStream(java.lang.String)
     */
    public static final String toModulePackageResourcePath(String packageName) {
        return packageName.replace(PACKAGE_NAME_DELIMITER, RESOURCE_PATH_DELIMITER);
    }

    /**
     * Gets the converted package name for the provided module resource path.
     *
     * @param moduleResourcePath the module resource path
     * @return the converted package name for the provided module resource path
     */
    public static final String getPackageName(String moduleResourcePath) {
        int lastIndex = moduleResourcePath.lastIndexOf(RESOURCE_PATH_DELIMITER);
        return (lastIndex != -1) ? moduleResourcePath.substring(0, lastIndex).replace(RESOURCE_PATH_DELIMITER, PACKAGE_NAME_DELIMITER) : "";
    }

    /**
     * Checks if the package of the resource is open to the accessing class. If it is not open a {@link ResourceAccessException} gets thrown.
     *
     * @param moduleResourcePath the module resource path
     * @param owningModule the owning module of the resource
     * @param accessingClass the class accessing the resource
     */
    public static final void checkPackageIsOpen(String moduleResourcePath, Module owningModule, Class<?> accessingClass) {
        if (owningModule.isNamed() && !owningModule.isOpen(getPackageName(moduleResourcePath), accessingClass.getModule())) {
            throw new ResourceAccessException(moduleResourcePath, owningModule, accessingClass);
        }
    }

//    public static final InputStream getResourceAsStream(String moduleResourcePath, Module owningModule) throws IOException {
//        InputStream is = owningModule.getResourceAsStream(moduleResourcePath);
//        // avoid NullPointerException
//        if (is == null) {
//            checkPackageIsOpen(moduleResourcePath, owningModule, Resources.class);
//            throw new ResourceFileNotFoundException(owningModule, moduleResourcePath);
//        }
//        return is;
//    }
}
