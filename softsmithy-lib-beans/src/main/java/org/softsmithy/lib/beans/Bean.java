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
package org.softsmithy.lib.beans;

import java.beans.PropertyChangeListener;

/**
 * This interface specifies common methods of a JavaBean such as support for bound properties.
 *
 * @author puce
 */
// TODO: would a type parameter be useful?
public interface Bean {
    /**
     * Registers a {@link PropertyChangeListener} for the specified property.
     *
     * @param propertyName the property to observe
     * @param listener the PropertyChangeListener to register
     */
    void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);

    /**
     * Unegisters a {@link PropertyChangeListener} for the specified property.
     *
     * @param propertyName the property to stop to observe
     * @param listener the PropertyChangeListener to unregister
     */
    void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);
}
