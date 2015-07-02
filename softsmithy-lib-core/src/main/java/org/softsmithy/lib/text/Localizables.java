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

/*
 * Localizables.java
 *
 * Created on 26. Dezember 2004, 16:17
 */
package org.softsmithy.lib.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Locale;

/**
 * A utility class for {@link Localizable}s.
 * @see Localizable
 * @see Localizer
 * @see LocalizedCollator
 * @see Locale
 * @author puce
 */
public class Localizables {

    /** Should not be used. */
    private Localizables() {
    }

    // useful???
    private static List<String> getDisplayStrings(Localizable... localizables) {
        return getDisplayStrings(Locale.getDefault(), localizables);
    }

    private static List<String> getDisplayStrings(Locale inLocale,
            Localizable... localizables) {
        return getDisplayStrings(new LocalizableLocalizer(), inLocale,
                localizables);
    }

    private static <T> List<String> getDisplayStrings(
            Localizer<? super T> localizer, T... objects) {
        return getDisplayStrings(localizer, Locale.getDefault(), objects);
    }

    private static <T> List<String> getDisplayStrings(
            Localizer<? super T> localizer, Locale inLocale,
            T... objects) {
        List<String> displayStrings = new ArrayList<String>(objects.length);
        for (T object : objects) {
            displayStrings.add(localizer.getDisplayString(object, inLocale));
        }
        return displayStrings;
    }

    private static List<String> getDisplayStrings(
            List<? extends Localizable> localizables) {
        return getDisplayStrings(Locale.getDefault(), localizables);
    }

    private static List<String> getDisplayStrings(Locale inLocale,
            List<? extends Localizable> localizables) {
        return getDisplayStrings(new LocalizableLocalizer(), inLocale,
                localizables);
    }

    private static <T> List<String> getDisplayStrings(
            Localizer<? super T> localizer, List<T> objects) {
        return getDisplayStrings(localizer, Locale.getDefault(), objects);
    }

    private static <T> List<String> getDisplayStrings(
            Localizer<? super T> localizer, Locale inLocale,
            List<T> objects) {
        List<String> displayStrings = new ArrayList<String>(objects.size());
        for (T object : objects) {
            displayStrings.add(localizer.getDisplayString(object, inLocale));
        }
        return displayStrings;
    }

    /**
     * Sorts an array of {@link Localizable}s in the natural, locale-sensitive order of
     * their {@link Localizable#getDisplayString(java.util.Locale)}-representation.
     * @param inLocale selects the localized display strings and the natural,
     * locale-sensitive order for this locale
     * @param localizables the localizables to sort
     */
    public static void sort(Locale inLocale, Localizable... localizables) {
        sort(new LocalizableLocalizer(), inLocale, localizables);
    }

    /**
     * Sorts an array of {@link Localizable}s in the natural, locale-sensitive order of
     * their {@link Localizable#getDisplayString(java.util.Locale)}-representation
     * as specified by the collator.
     * @param collator selects the localized display strings and specifies the natural,
     * locale-sensitive order for this locale
     * @param localizables the localizables to sort
     */
    public static void sort(LocalizedCollator collator,
            Localizable... localizables) {
        sort(new LocalizableLocalizer(), collator, localizables);
    }

    /**
     * Sorts an array of {@link Localizable}s in the natural, locale-sensitive order of
     * their {@link Localizable#getDisplayString(java.util.Locale)}-representation
     * (uses the default locale).
     * @param localizables the localizables to sort
     * @see Locale#getDefault()
     */
    public static void sort(Localizable... localizables) {
        sort(Locale.getDefault(), localizables);
    }

    /**
     * Sorts an array of objects in the natural, locale-sensitive order of 
     * their {@link Localizer#getDisplayString(java.lang.Object, java.util.Locale)}-representation.
     * @param localizer gets the localized display strings of the objects
     * @param inLocale selects the localized display strings and the natural,
     * locale-sensitive order for this locale
     * @param objects the objects to sort
     */
    public static <T> void sort(Localizer<? super T> localizer, Locale inLocale,
            T... objects) {
        sort(localizer, new LocalizedCollator(inLocale), objects);
    }

    /**
     * Sorts an array of objects in the natural, locale-sensitive order of
     * their {@link Localizer#getDisplayString(java.lang.Object, java.util.Locale)}-representation
     * as specified by the collator.
     * @param localizer gets the localized display strings of the objects
     * @param collator selects the localized display strings and specifies the natural,
     * locale-sensitive order for this locale
     * @param objects the objects to sort
     */
    public static <T> void sort(Localizer<? super T> localizer,
            LocalizedCollator collator, T... objects) {
        List<GenericCollationKey<T>> keys = getLocalizableCollationKeys(objects,
                localizer, collator);
        Collections.sort(keys);
        int i = 0;
        for (GenericCollationKey<T> key : keys) {
            objects[i] = key.getSource();
            i++;
        }
    }

    /**
     * Sorts an array of objects in the natural, locale-sensitive order of
     * their {@link Localizer#getDisplayString(java.lang.Object, java.util.Locale)}-representation
     * (uses the default locale).
     * @param localizer gets the localized display strings of the objects
     * @param objects the objects to sort
     */
    public static <T> void sort(Localizer<? super T> localizer, T... objects) {
        sort(localizer, Locale.getDefault(), objects);
    }

    /**
     * Sorts a list of {@link Localizable}s in the natural, locale-sensitive order of their
     * {@link Localizable#getDisplayString(java.util.Locale)}-representation.
     * @param inLocale selects the localized display strings and the natural,
     * locale-sensitive order for this locale
     * @param localizables the localizables to sort
     */
    public static void sort(Locale inLocale,
            List<? extends Localizable> localizables) {
        sort(new LocalizableLocalizer(), inLocale, localizables);
    }

    /**
     * Sorts a list of {@link Localizable}s in the natural, locale-sensitive order of their
     * {@link Localizable#getDisplayString(java.util.Locale)}-representation
     * as specified by the collator.
     * @param collator selects the localized display strings and specifies the natural,
     * locale-sensitive order for this locale
     * @param localizables the localizables to sort
     */
    public static void sort(LocalizedCollator collator,
            List<? extends Localizable> localizables) {
        sort(new LocalizableLocalizer(), collator, localizables);
    }

    /**
     * Sorts a list of {@link Localizable}s in the natural, locale-sensitive order of their
     * {@link Localizable#getDisplayString(java.util.Locale)}-representation
     * (uses the default locale).
     * @param localizables the localizables to sort
     * @see Locale#getDefault()
     */
    public static void sort(List<? extends Localizable> localizables) {
        sort(Locale.getDefault(), localizables);
    }

    /**
     * Sorts a list of objects in the natural, locale-sensitive order of
     * their {@link Localizer#getDisplayString(java.lang.Object, java.util.Locale)}-representation.
     * @param localizer gets the localized display strings of the objects
     * @param inLocale selects the localized display strings and the natural,
     * locale-sensitive order for this locale
     * @param list the objects to sort
     */
    public static <T> void sort(Localizer<? super T> localizer, Locale inLocale,
            List<T> list) {
        sort(localizer, new LocalizedCollator(inLocale), list);
    }

    /**
     * Sorts a list of objects in the natural, locale-sensitive order of
     * their {@link Localizer#getDisplayString(java.lang.Object, java.util.Locale)}-representation
     * as specified by the collator.
     * @param localizer gets the localized display strings of the objects
     * @param collator selects the localized display strings and specifies the natural,
     * locale-sensitive order for this locale
     * @param list the objects to sort
     */
    public static <T> void sort(Localizer<? super T> localizer,
            LocalizedCollator collator, List<T> list) {
        List<GenericCollationKey<T>> keys = getLocalizableCollationKeys(list,
                localizer, collator);
        Collections.sort(keys);
        ListIterator<T> listIterator = list.listIterator();
        for (GenericCollationKey<T> key : keys) {
            listIterator.next();
            listIterator.set(key.getSource());
        }
    }

    /**
     * Sorts a list of objects in the natural, locale-sensitive order of
     * their {@link Localizer#getDisplayString(java.lang.Object, java.util.Locale)}-representation
     * (uses the default locale).
     * @param localizer gets the localized display strings of the objects
     * @param list the objects to sort
     * @see Locale#getDefault()
     */
    public static <T> void sort(Localizer<? super T> localizer,
            List<T> list) {
        sort(localizer, Locale.getDefault(), list);
    }

    private static <T> List<GenericCollationKey<T>> getLocalizableCollationKeys(
            T[] array, Localizer<? super T> localizer,
            LocalizedCollator collator) {
        List<GenericCollationKey<T>> keys = new ArrayList<GenericCollationKey<T>>(
                array.length);
        for (T element : array) {
            keys.add(collator.getCollationKey(element, localizer));
        }
        return keys;
    }

    private static <T> List<GenericCollationKey<T>> getLocalizableCollationKeys(
            Collection<T> collection, Localizer<? super T> localizer,
            LocalizedCollator collator) {
        List<GenericCollationKey<T>> keys = new ArrayList<GenericCollationKey<T>>(collection.
                size());
        for (T element : collection) {
            keys.add(collator.getCollationKey(element, localizer));
        }
        return keys;
    }

    private static class LocalizableLocalizer implements Localizer<Localizable> {

        @Override
        public String getDisplayString(Localizable localizable, Locale locale) {
            return localizable.getDisplayString(locale);
        }
    }
}
