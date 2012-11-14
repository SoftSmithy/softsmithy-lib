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
package org.softsmithy.lib.util;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author puce
 */
public class Positionables {

    private Positionables() {
    }

    public static <T extends Positionable> int getInsertionPoint(List<? extends T> sortedList, T key) {
        PositionableComparator comparator = new PositionableComparator();
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
