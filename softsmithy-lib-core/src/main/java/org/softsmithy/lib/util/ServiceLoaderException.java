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

import java.util.ServiceLoader;

/**
 * @see ServiceLoader
 * @author puce
 */
public class ServiceLoaderException extends RuntimeException {
    private static final long serialVersionUID = -326132137354684616L;

    public ServiceLoaderException() {
    }

    public ServiceLoaderException(String message) {
        super(message);
    }

    public ServiceLoaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceLoaderException(Throwable cause) {
        super(cause);
    }

}
