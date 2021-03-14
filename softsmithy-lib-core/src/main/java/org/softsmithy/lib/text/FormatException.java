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
package org.softsmithy.lib.text;

/**
 * A FormatException indicates something went wrong during formatting an object.
 *
 * @author puce
 */
public class FormatException extends Exception {

    private static final long serialVersionUID = -4043623270852222462L;

    /**
     * Creates a new instance of this class.
     */
    public FormatException() {
    }

    /**
     * Creates a new instance of this class.
     *
     * @param message the message
     */
    public FormatException(String message) {
        super(message);
    }

    /**
     * Creates a new instance of this class.
     *
     * @param cause the cause
     */
    public FormatException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new instance of this class.
     *
     * @param message the message
     * @param cause   the cause
     */
    public FormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
