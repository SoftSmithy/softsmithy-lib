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
 *
 * @version 3.0
 * @author puce
 */
public final class Resources {
    public static final char RESOURCE_PATH_DELIMITER = '/';

    private Resources() {
    }

    public static final String toModulePackageResourcePath(Package typePackage) {
        return toModulePackageResourcePath(typePackage.getName());
    }

    public static final String toModulePackageResourcePath(String packageName) {
        return packageName.replace('.', RESOURCE_PATH_DELIMITER);
    }


    public static final String getPackageName(String moduleResourcePath) {
        int lastIndex = moduleResourcePath.lastIndexOf(RESOURCE_PATH_DELIMITER);
        return (lastIndex != -1) ? moduleResourcePath.substring(0, lastIndex).replace(RESOURCE_PATH_DELIMITER, '.') : "";
    }

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
