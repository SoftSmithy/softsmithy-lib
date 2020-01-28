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
public class ResourceAccessException extends RuntimeException {

    private static final long serialVersionUID = -1L;

    public ResourceAccessException(String moduleResourcePath, Module owningModule, Class<?> accessingClass) {
        super(accessingClass + " (in " + accessingClass.getModule() + ") cannot access file " + moduleResourcePath + " (in " + owningModule + ") because "
                + owningModule + " does not open " + Resources.getPackageName(moduleResourcePath) + " to " + accessingClass.getModule()
        );
    }

    public ResourceAccessException(String message) {
        super(message);
    }

    public ResourceAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceAccessException(Throwable cause) {
        super(cause);
    }

    public ResourceAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
