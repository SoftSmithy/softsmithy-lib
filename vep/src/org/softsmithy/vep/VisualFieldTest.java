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
        public Set<Integer> getColorSwitchingIndices(int nSections, int nDeviderSectionsFromMiddle) {
            return getLeftColorSwitchingIndices(nSections, nDeviderSectionsFromMiddle);
        }
        
    },
    RIGHT_INNER(true, false){
        public Set<Integer> getColorSwitchingIndices(int nSections, int nDeviderSectionsFromMiddle) {
            return getRightColorSwitchingIndices(nSections, nDeviderSectionsFromMiddle);
        }
        
    },
    LEFT_OUTER(false, true){
        public Set<Integer> getColorSwitchingIndices(int nSections, int nDeviderSectionsFromMiddle) {
            return getLeftColorSwitchingIndices(nSections, nDeviderSectionsFromMiddle);
        }
        
    },
    RIGHT_OUTER(false, true){
        public Set<Integer> getColorSwitchingIndices(int nSections, int nDeviderSectionsFromMiddle) {
            return getRightColorSwitchingIndices(nSections, nDeviderSectionsFromMiddle);
        }
    },
    LEFT_HALF(true, true){
        public Set<Integer> getColorSwitchingIndices(int nSections, int nDeviderSectionsFromMiddle) {
            return getLeftColorSwitchingIndices(nSections, nDeviderSectionsFromMiddle);
        }
    },
    RIGHT_HALF(true, true){
        public Set<Integer> getColorSwitchingIndices(int nSections, int nDeviderSectionsFromMiddle) {
            return getRightColorSwitchingIndices(nSections, nDeviderSectionsFromMiddle);
        }
    },
    FULL(true, true){
        public Set<Integer> getColorSwitchingIndices(int nSections, int nDeviderSectionsFromMiddle) {
            return getAllColorSwitchingIndices(nSections, nDeviderSectionsFromMiddle);
        }
        
    };
    
    private final boolean inner;
    private final boolean outer;
    
    private VisualFieldTest(boolean inner, boolean outer){
        this.inner = inner;
        this.outer = outer;
    }
    public abstract Set<Integer> getColorSwitchingIndices(int nSections, int nDeviderSectionsFromMiddle);
    
    public boolean isInner(){
        return inner;
    }
    
    
    public boolean isOuter() {
        return outer;
    }
    
    private static Set<Integer> getLeftColorSwitchingIndices(int nSections, int nDeviderSectionsFromMiddle){
        Set<Integer> set = new HashSet<Integer>();
        for (int i=nSections/2+nDeviderSectionsFromMiddle; i<nSections-nDeviderSectionsFromMiddle; i++){
            set.add(i);
        }
        return set; //return new HashSet<Integer>(Arrays.asList(nSections/2));
    }
    
    private static Set<Integer> getRightColorSwitchingIndices(int nSections, int nDeviderSectionsFromMiddle){
        Set<Integer> set = new HashSet<Integer>();
        for (int i=nDeviderSectionsFromMiddle; i<nSections/2-nDeviderSectionsFromMiddle; i++){
            set.add(i);
        }
        return set; //new HashSet<Integer>(Arrays.asList(0, nSections/2));
    }
    
    private static Set<Integer> getAllColorSwitchingIndices(int nSections, int nDeviderSectionsFromMiddle){
        Set<Integer> set = getRightColorSwitchingIndices(nSections, nDeviderSectionsFromMiddle);
        set.addAll(getLeftColorSwitchingIndices(nSections, nDeviderSectionsFromMiddle));
        return set; //new HashSet<Integer>(Arrays.asList(0, nSections/2));
    }

}
