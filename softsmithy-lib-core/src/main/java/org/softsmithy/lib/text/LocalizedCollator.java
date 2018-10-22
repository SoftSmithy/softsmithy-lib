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

import java.text.Collator;
import java.util.Locale;

/**
 * A {@link Locale} specific {@link Collator}.
 * TODO: good name?
 * @see Localizable
 * @see Collator
 * @see Locale
 * @see Localizable
 * @see Localizer
 * @see GenericCollationKey
 * @author puce
 */
public class LocalizedCollator {

    private final Locale locale;
    private final Collator collator;

    /**
     * Creates a new instance of this class.
     * @param locale the locale
     */
    public LocalizedCollator(Locale locale) {
        this.locale = locale;
        this.collator = Collator.getInstance(locale);
    }

    /**
     * Gets the locale.
     * @return the locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Gets the collator.
     * @return the collator
     */
    public Collator getCollator() {
        return collator;
    }

    /**
     * Gets the collation key for the given {@link Localizable} source.
     * @see #getCollationKey(java.lang.Object, org.softsmithy.lib.text.Localizer) 
     * @param <T> the type of the source
     * @param source the source
     * @return the collation key for the given source
     */
    public <T extends Localizable> GenericCollationKey<T> getCollationKey(T source) {
        return new GenericCollationKey<>(source, getCollator().getCollationKey(source.getDisplayString(getLocale())));
    }

    /**
     * Gets the collation key for the given source using the specified {@link Localizer}.
     * @see #getCollationKey(org.softsmithy.lib.text.Localizable) 
     * @param <T> the type of the source
     * @param source the source
     * @param localizer the localizer
     * @return the collation key for the given source
     */
    public <T> GenericCollationKey<T> getCollationKey(T source, Localizer<? super T> localizer) {
        return new GenericCollationKey<>(source, getCollator().getCollationKey(localizer.getDisplayString(source, getLocale())));
    }
}
