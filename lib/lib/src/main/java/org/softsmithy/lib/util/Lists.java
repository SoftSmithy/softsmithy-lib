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
public class Lists {
    
    /** Creates a new instance of Lists */
    private Lists() {
    }
    
    /**
     * Useful for generated classes, which don't override the equals method (e.g. some JAXB class generators)
     *
     */
    // TODO use generics with jdk v1.5
    public static boolean equals(List listA, List listB, EqualityVerifier verifier){
        if (listA.size() != listB.size()){
            return false;
        }
        for (Iterator iteratorA = listA.iterator(), iteratorB = listB.iterator(); iteratorA.hasNext();){
            if (! verifier.equals(iteratorA.next(), iteratorB.next())){
                return false;
            }
        }
        return true;
    }
    
    /**
     * Useful for generated classes, which don't override the equals method (e.g. some JAXB class generators)
     *
     */
    // TODO use generics with jdk v1.5
    public static boolean equalsIgnoreOrder(List listA, List listB, Comparator comparator){
        if (listA.size() != listB.size()){
            return false;
        }
        List sortedList = new ArrayList(listB); // make a copy, so the orignial list doesn't get modified
        Collections.sort(sortedList, comparator);
        // use enhanced for loop with jdk v1.5
        for (Iterator iterator = listA.iterator(); iterator.hasNext();){
            Object obj = iterator.next();
            int index = Collections.binarySearch(sortedList, obj, comparator);
            if (index < 0){
                return false;
            }
            sortedList.remove(index); // to reduce search
        }
        return true;
    }
}
