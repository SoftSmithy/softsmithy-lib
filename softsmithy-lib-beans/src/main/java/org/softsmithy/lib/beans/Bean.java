/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
