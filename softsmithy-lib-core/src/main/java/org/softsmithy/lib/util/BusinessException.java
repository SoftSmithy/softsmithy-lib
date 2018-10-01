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
 * A base class for business exceptions.
 */
public class BusinessException extends Exception {

    private static final long serialVersionUID = -5277539401535691349L;

    /**
     * Creates a new instance of this class.
     */
    public BusinessException() {
    }

    /**
     * Creates a new instance of this class.
     *
     * @param message the exception message
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * Creates a new instance of this class.
     *
     * @param message the exception message
     * @param cause the cause of this exception
     */
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Creates a new instance of this class.
     *
     * @param cause the cause of this exception
     */
    public BusinessException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new instance of this class.
     *
     * @param message the exception message
     * @param cause the cause of this exception
     * @param enableSuppression flag if suppression should be enabled
     * @param writableStackTrace flag if the stack trace should be writable
     */
    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
