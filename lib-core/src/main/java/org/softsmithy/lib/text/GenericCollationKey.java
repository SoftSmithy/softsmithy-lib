package org.softsmithy.lib.text;

import java.text.CollationKey;

/**
 * A wrapper around {@link CollationKey}, which has a reference to the orignial source (which doesn't have to be a {@link String}).
 *
 * @see LocalizedCollator#getCollationKey(org.softsmithy.lib.text.Localizable)
 * @see LocalizedCollator#getCollationKey(java.lang.Object, org.softsmithy.lib.text.Localizer)
 * @param <T>
 *
 * TODO: good name?
 */
public class GenericCollationKey<T> implements
        Comparable<GenericCollationKey<T>> {

    private final T source;
    /** Holds value of property collationKey. */
    private final CollationKey collationKey;

    /*default*/ GenericCollationKey(T source, CollationKey collationKey) {
        this.source = source;
        this.collationKey = collationKey;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(GenericCollationKey<T> o) {
        return collationKey.compareTo(o.getCollationKey());
    }

    /**
     * Gets the original source.
     * @return the original source
     */
    public T getSource() {
        return this.source;
    }

    /**
     * Gets the {@link CollationKey} for the displayString of {@link #getSource() }
     * @return the CollationKey for the displayString of the source.
     */
    public CollationKey getCollationKey() {
        return this.collationKey;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        int result = 17;
        result = 37 * result + collationKey.hashCode();
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GenericCollationKey)) {
            return false;
        }
        GenericCollationKey<?> gck = (GenericCollationKey<?>) obj;
        return collationKey == gck.collationKey || (collationKey != null && collationKey.
                equals(gck.collationKey));
    }
}
