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
 *
 * @author puce
 */
public class Localizables {

    /** Creates a new instance of Localizables */
    private Localizables() {
    }

    /**
     * Sorts an array of Localizables in the natural, locale-sensitive order of their getDisplayString(Locale)-representation
     */
    public static void sort(Locale inLocale, Localizable... localizables) {
        sort(new LocalizableLocalizer(), inLocale, localizables);
    }

    public static void sort(LocalizedCollator collator, Localizable... localizables) {
        sort(new LocalizableLocalizer(), collator, localizables);
    }

    public static void sort(Localizable... localizables) {
        sort(Locale.getDefault(), localizables);
    }

    public static <T> void sort(Localizer<? super T> localizer, Locale inLocale, T... array) {
        sort(localizer, new LocalizedCollator(inLocale), array);
    }
    
    public static <T> void sort(Localizer<? super T> localizer, LocalizedCollator collator, T... objects) {
        List<GenericCollationKey<T>> keys = getLocalizableCollationKeys(objects, localizer, collator);
        Collections.sort(keys);
        int i = 0;
        for (GenericCollationKey<T> key : keys) {
            objects[i] = key.getSource();
            i++;
        }
    }

    public static <T> void sort(Localizer<? super T> localizer, T... array) {
        sort(localizer, Locale.getDefault(), array);
    }

    /**
     * Sorts a List of Localizables in the natural, locale-sensitive order of their getDisplayString(Locale)-representation
     */
    public static void sort(Locale inLocale, List<? extends Localizable> localizables) {
        sort(new LocalizableLocalizer(), inLocale, localizables);
    }

    public static void sort(LocalizedCollator collator, List<? extends Localizable> localizables) {
        sort(new LocalizableLocalizer(), collator, localizables);
    }

    public static void sort(List<? extends Localizable> localizables) {
        sort(Locale.getDefault(), localizables);
    }

    public static <T> void sort(Localizer<? super T> localizer, Locale inLocale, List<T> list) {
        sort(localizer, new LocalizedCollator(inLocale), list);
    }
    
    public static <T> void sort(Localizer<? super T> localizer, LocalizedCollator collator, List<T> list) {
        List<GenericCollationKey<T>> keys = getLocalizableCollationKeys(list, localizer, collator);
        Collections.sort(keys);
        ListIterator<T> listIterator = list.listIterator();
        for (GenericCollationKey<T> key : keys) {
            listIterator.next();
            listIterator.set(key.getSource());
        }
    }

    public static <T> void sort(Localizer<? super T> localizer, List<T> localizables) {
        sort(localizer, Locale.getDefault(), localizables);
    }

    private static <T> List<GenericCollationKey<T>> getLocalizableCollationKeys(T[] array, Localizer<? super T> localizer, LocalizedCollator collator) {
        List<GenericCollationKey<T>> keys = new ArrayList<GenericCollationKey<T>>(array.length);
        for (T element : array) {
            keys.add(collator.getCollationKey(element, localizer));
        }
        return keys;
    }

    private static <T> List<GenericCollationKey<T>> getLocalizableCollationKeys(Collection<T> collection, Localizer<? super T> localizer, LocalizedCollator collator) {
        List<GenericCollationKey<T>> keys = new ArrayList<GenericCollationKey<T>>(collection.size());
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
