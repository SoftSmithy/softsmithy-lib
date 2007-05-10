/*
 * Lists.java
 *
 * Created on 11. Mai 2007, 00:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.softsmithy.lib.util;

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
    
    // TODO use generics with jdk v1.5
    public static boolean equalsIgnoreOrder(List listA, List listB, Comparator comparator){
        if (listA.size() != listB.size()){
            return false;
        }
        Collections.sort(listB, comparator);
        EqualityVerifier verifier = new ComparatorEqualityVerifier(comparator);
        // use enhanced for loop with jdk v1.5
        for (Iterator iterator = listA.iterator(); iterator.hasNext();){
            Object obj = iterator.next();
            int index = Collections.binarySearch(listB, obj, comparator);
            if (index < listB.size()){
                if (! verifier.equals(obj, listB.get(index))){
                    return false;
                }
            } else {
                return false;
            }
            listB.remove(index); // to reduce search
        }
        return true;
    }
}
