package org.softsmithy.lib.util;

/**
 * An Injector injects objects into other objects.
 *
 * @author puce
 */
public interface Injector<T> {
    /**
     * Injects objects into the target object.
     *
     * @param target the target object
     */
    void inject(T target);
}
