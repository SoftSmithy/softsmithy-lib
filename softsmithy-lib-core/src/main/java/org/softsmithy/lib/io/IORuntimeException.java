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
package org.softsmithy.lib.io;

/**
 *
 * @author puce
 */
public class IORuntimeException extends RuntimeException {

    private static final long serialVersionUID = -7220063338920236161L;

    /**
     * Creates a new instance of <code>IORuntimeException</code> without detail message.
     */
    public IORuntimeException() {
    }

    /**
     * Constructs an instance of <code>IORuntimeException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public IORuntimeException(String msg) {
        super(msg);
    }

    public IORuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IORuntimeException(Throwable cause) {
        super(cause);
    }

    public IORuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
