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
 *
 * @author puce
 */
public class FormatException extends Exception {

    private static final long serialVersionUID = -4043623270852222462L;

    public FormatException() {
    }

    public FormatException(String message) {
        super(message);
    }

    public FormatException(Throwable cause) {
        super(cause);
    }

    public FormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
