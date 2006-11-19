/*
 * VisualFieldTest.java
 *
 * Created on 18. Juni 2006, 15:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.softsmithy.vep;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author florian.brunner
 */
public enum VisualFieldTest {
    LEFT_INNER(true, false){
        public Set<Integer> getColorSwitchingIndices(int nSections) {
            return getLeftColorSwitchingIndices(nSections);
        }
        
    },
    RIGHT_INNER(true, false){
        public Set<Integer> getColorSwitchingIndices(int nSections) {
            return getRightColorSwitchingIndices(nSections);
        }
        
    },
    LEFT_OUTER(false, true){
        public Set<Integer> getColorSwitchingIndices(int nSections) {
            return getLeftColorSwitchingIndices(nSections);
        }
        
    },
    RIGHT_OUTER(false, true){
        public Set<Integer> getColorSwitchingIndices(int nSections) {
            return getRightColorSwitchingIndices(nSections);
        }
    },
    FULL(true, true){
        public Set<Integer> getColorSwitchingIndices(int nSections) {
            return getAllIndices(nSections);
        }
        
    };
    
    private final boolean inner;
    private final boolean outer;
    
    private VisualFieldTest(boolean inner, boolean outer){
        this.inner = inner;
        this.outer = outer;
    }
    public abstract Set<Integer> getColorSwitchingIndices(int nSections);
    
    public boolean isInner(){
        return inner;
    }
    
    
    public boolean isOuter() {
        return outer;
    }
    
    private static Set<Integer> getLeftColorSwitchingIndices(int nSections){
        Set<Integer> set = new HashSet<Integer>();
        for (int i=nSections/2; i<nSections; i++){
            set.add(i);
        }
        return set; //return new HashSet<Integer>(Arrays.asList(nSections/2));
    }
    
    private static Set<Integer> getRightColorSwitchingIndices(int nSections){
        Set<Integer> set = new HashSet<Integer>();
        for (int i=0; i<nSections/2; i++){
            set.add(i);
        }
        return set; //new HashSet<Integer>(Arrays.asList(0, nSections/2));
    }
    
    private static Set<Integer> getAllIndices(int nSections){
        Set<Integer> set = new HashSet<Integer>();
        for (int i=0; i<nSections; i++){
            set.add(i);
        }
        return set; //new HashSet<Integer>(Arrays.asList(0, nSections/2));
    }

}
