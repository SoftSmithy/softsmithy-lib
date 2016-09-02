package org.softsmithy.lib.util;

/**
 *
 * @author puce
 * @param <T> the type of the unique key
 */
public interface UniqueKeyProvider<T> {

    T getUniqueKey();
}
