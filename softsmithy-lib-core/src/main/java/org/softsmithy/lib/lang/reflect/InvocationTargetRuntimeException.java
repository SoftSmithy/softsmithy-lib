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

package org.softsmithy.lib.lang.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * InvocationTargetRuntimeException is an unchecked exception that wraps
 * an exception thrown by an invoked method or constructor.
 *
 * @see Method
 * @see Constructor
 */
public class InvocationTargetRuntimeException extends RuntimeException {


     /**
     * This field holds the target if the 
     * InvocationTargetException(Throwable target) constructor was
     * used to instantiate the object
     * 
     * @serial 
     * 
     */
    private Throwable target;

    /**
     * Constructs an <code>InvocationTargetException</code> with 
     * <code>null</code> as the target exception.
     */
    protected InvocationTargetRuntimeException() {
	super((Throwable)null);  // Disallow initCause
    }

    /**
     * Constructs a InvocationTargetException with a target exception.
     * 
     * @param target the target exception
     */
    public InvocationTargetRuntimeException(Throwable target) {
	super((Throwable)null);  // Disallow initCause
        this.target = target;
    }

    /**
     * Constructs a InvocationTargetException with a target exception
     * and a detail message.
     *
     * @param target the target exception
     * @param s      the detail message
     */
    public InvocationTargetRuntimeException(Throwable target, String s) {
	super(s, null);  // Disallow initCause
        this.target = target;
    }


    /**
     * Returns the the cause of this exception (the thrown target exception,
     * which may be <tt>null</tt>).
     *
     * @return  the cause of this exception.
     */
    @Override
    public Throwable getCause() {
        return target;
    }
}
