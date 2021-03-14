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

/**
 * Signals that a resource file couldn't be found.
 *
 * @author puce
 */
public class ResourceFileNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7205771392436334019L;

    private final Module module;
    private final String moduleResourcePath;

    public ResourceFileNotFoundException(Module module, String moduleResourcePath) {
        super("Resource not found: " + moduleResourcePath + " in " + module);
        this.module = module;
        this.moduleResourcePath = moduleResourcePath;
    }

    public ResourceFileNotFoundException(Module module, String moduleResourcePath, String message) {
        super(message);
        this.module = module;
        this.moduleResourcePath = moduleResourcePath;
    }

    public ResourceFileNotFoundException(Module module, String moduleResourcePath, Throwable cause) {
        super(cause);
        this.module = module;
        this.moduleResourcePath = moduleResourcePath;
    }

    public ResourceFileNotFoundException(Module module, String moduleResourcePath, String message, Throwable cause) {
        super(message, cause);
        this.module = module;
        this.moduleResourcePath = moduleResourcePath;
    }

    /**
     * Gets the module path to the resource, which couldn't be found.
     *
     * @return the moduleResourcePath the module path to the resource, which couldn't be found
     */
    public String getModuleResourcePath() {
        return moduleResourcePath;
    }

    /**
     *
     * @return
     */
    public Module getModule() {
        return module;
    }
}
