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
 * Lists.java
 *
 * Created on 11. Mai 2007, 00:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.softsmithy.lib.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author puce
 */
public final class Lists {

    /**
     * Creates a new instance of Lists
     */
    private Lists() {
    }

    /**
     * Useful for generated classes, which don't override the equals method (e.g. some JAXB class generators)
     *
     * @param <T>
     * @param listA
     * @param listB
     * @param matcher
     * @return
     */
    public static <T> boolean equals(List<? extends T> listA,
            List<? extends T> listB, Matcher<? super T> matcher) {
        if (listA.size() != listB.size()) {
            return false;
        }
        for (Iterator<? extends T> iteratorA = listA.iterator(), iteratorB = listB.
                iterator(); iteratorA.hasNext();) {
            if (!matcher.equals(iteratorA.next(), iteratorB.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Useful for generated classes, which don't override the equals method (e.g. some JAXB class generators)
     *
     * @param <T>
     * @param listA
     * @param listB
     * @param comparator
     * @return
     */
    public static <T> boolean equalsIgnoreOrder(List<? extends T> listA,
            List<? extends T> listB, Comparator<? super T> comparator) {
        if (listA.size() != listB.size()) {
            return false;
        }
        List<T> sortedList = new ArrayList<>(listB); // make a copy, so the orignial list doesn't get modified
        Collections.sort(sortedList, comparator);
        for (T a : listA) {
            int index = Collections.binarySearch(sortedList, a, comparator);
            if (index < 0) {
                return false;
            }
            sortedList.remove(index); // to reduce search
        }
        return true;
    }

    /**
     * Gets the first element of the specified list.
     *
     * @param <E> the type of the elements of the list
     * @param list the list
     * @return the first element
     */
    public static <E> E getFirst(List<E> list) {
        return list.get(0);
    }

    /**
     * Gets the last element of the specified list.
     *
     * @param <E> the type of the elements of the list
     * @param list the list
     * @return the last element
     */
    public static <E> E getLast(List<E> list) {
        return list.get(list.size() - 1);
    }

    public static <T extends Comparable<? super T>> int getInsertionPoint(List<? extends T> sortedList, T key) {
        int index = Collections.binarySearch(sortedList, key);
        if (index < 0) {
            index = -index - 1;
        } else {
            for (T item : sortedList.subList(index, sortedList.size())) {
                if (Comparables.isEqual(item, key)) {
                    index++;
                } else {
                    break;
                }
            }
        }
        return index;
    }

    public static <T> int getInsertionPoint(List<? extends T> sortedList, T key, Comparator<? super T> comparator) {
        int index = Collections.binarySearch(sortedList, key, comparator);
        if (index < 0) {
            index = -index - 1;
        } else {
            for (T item : sortedList.subList(index, sortedList.size())) {
                if (Comparables.isEqual(item, key, comparator)) {
                    index++;
                } else {
                    break;
                }
            }
        }
        return index;
    }
}
