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
package org.softsmithy.lib.util.concurrent;

import java.util.HashMap;
import java.util.Map;

/**
 * A {@link ThreadLocal} which manages generic properties.
 */
public final class ThreadLocalStorage extends InheritableThreadLocal<Map<String, Object>> {

    private static final ThreadLocalStorage INSTANCE = new ThreadLocalStorage();
    
    private ThreadLocalStorage(){}

    /**
     * Gets a property value.
     *
     * @param propertyName the property name
     * @return the property value
     */
    public static Object getPropertyValue(String propertyName) {
        return INSTANCE.get().get(propertyName);
    }

    /**
     * Sets a property value.
     *
     * @param propertyName the property name
     * @param propertyValue the property value
     * @return the previous property value
     */
    public static Object setPropertyValue(String propertyName, Object propertyValue) {
        return INSTANCE.get().put(propertyName, propertyValue);
    }

    /**
     * Removes a property.
     *
     * @param propertyName the property name
     * @return the previous property value
     */
    public static Object removeProperty(String propertyName) {
        return INSTANCE.get().remove(propertyName);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    protected Map<String, Object> initialValue() {
        return new HashMap<>();
    }

}
