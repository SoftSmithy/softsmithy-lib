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
 * Signals that a resource could not be accessed.
 *
 * @version 3.0
 * @author puce
 */
public class ResourceAccessException extends RuntimeException {

    private static final long serialVersionUID = -1L;

    private final String moduleResourcePath;
    private final Module owningModule;
    private final Class<?> accessingClass;

    /**
     * Creates a new instance of this class.
     *
     * @param moduleResourcePath the module resource path
     * @param owningModule the owning module of the resource
     * @param accessingClass the class accessing the resource
     */
    public ResourceAccessException(String moduleResourcePath, Module owningModule, Class<?> accessingClass) {
        super(formatMessage(moduleResourcePath, owningModule, accessingClass));
        this.moduleResourcePath = moduleResourcePath;
        this.owningModule = owningModule;
        this.accessingClass = accessingClass;
    }

    private static String formatMessage(
            String moduleResourcePath, Module owningModule, Class<?> accessingClass) {
        return String.format("%3$s (in %4$s) cannot access file %1$s (in %2$s) because %2$s does not open %5$s to %4$s",
                moduleResourcePath, owningModule, accessingClass,
                accessingClass.getModule(),
                Resources.getPackageName(moduleResourcePath));

    }

    /**
     * Creates a new instance of this class.
     *
     * @param moduleResourcePath the module resource path
     * @param owningModule the owning module of the resource
     * @param accessingClass the class accessing the resource
     * @param message the message
     */
    public ResourceAccessException(String moduleResourcePath, Module owningModule, Class<?> accessingClass, String message) {
        super(message);
        this.moduleResourcePath = moduleResourcePath;
        this.owningModule = owningModule;
        this.accessingClass = accessingClass;
    }

    /**
     * Creates a new instance of this class.
     *
     * @param moduleResourcePath the module resource path
     * @param owningModule the owning module of the resource
     * @param accessingClass the class accessing the resource
     * @param message the message
     * @param cause the cause
     */
    public ResourceAccessException(String moduleResourcePath, Module owningModule, Class<?> accessingClass, String message, Throwable cause) {
        super(message, cause);
        this.moduleResourcePath = moduleResourcePath;
        this.owningModule = owningModule;
        this.accessingClass = accessingClass;
    }

    /**
     * Gets the module resource path which could not be accessed.
     *
     * @return the module resource path
     */
    public String getModuleResourcePath() {
        return moduleResourcePath;
    }

    /**
     * Gets the owning module of the resource;
     *
     * @return the owning module of the resource
     */
    public Module getOwningModule() {
        return owningModule;
    }

    /**
     * Gets the class accessing the resource.
     *
     * @return the class accessing the resource
     */
    public Class<?> getAccessingClass() {
        return accessingClass;
    }
}
