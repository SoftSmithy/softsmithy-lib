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
package org.softsmithy.lib.util.context;

/**
 * A functional interface to write a property to a context.
 */
@FunctionalInterface
public interface ContextPropertyWriter {

    /**
     * Writes a property value to a context.
     *
     * @param propertyName the property name
     * @param propertyValue the property value
     */
    void writePropertyValue(String propertyName, Object propertyValue);
}