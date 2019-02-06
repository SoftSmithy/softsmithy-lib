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
 * A generic property which can be read from / written to independent contexts.<br>
 * <br>
 * Usage:
 * <pre>
 * Map&lt;String, Object&gt; someMap = ...;
 * ThreadLocalStorage storage = ...;
 * T propertyValue = contextProperty.getValue(someMap::get);
 * contextProperty.setValue(storage::setPropertyValue, propertyValue);
 * </pre>
 *
 * @param <T> the property type
 */
public class ContextProperty<T> {

    private final String name;
    private final Class<T> propertyType;

    /**
     * Creates a new instance of this class.
     *
     * @param name the property name
     * @param type the property type
     */
    public ContextProperty(String name, Class<T> type) {
        this.name = name;
        this.propertyType = type;
    }

    /**
     * Gets the property value from a context.
     *
     * @param reader the reader
     * @return the property value
     */
    public T getValue(ContextPropertyReader reader) {
        return propertyType.cast(reader.readPropertyValue(name));
    }

    /**
     * Sets the property value in a context.writer
     *
     * @param writer the reader
     * @param value the property value
     */
    public void setValue(ContextPropertyWriter writer, T value) {
        writer.writePropertyValue(name, value);
    }

    /**
     * Removes the property from a context.
     *
     * @param remover the remover
     */
    public void removeProperty(ContextPropertyRemover remover) {
        remover.removeProperty(name);
    }
}
