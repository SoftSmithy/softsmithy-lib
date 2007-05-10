/*
 * ObjectEqulatiyVerifier.java
 *
 * Created on 11. Mai 2007, 00:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.softsmithy.lib.util;

/**
 *
 * @author puce
 */
// Not yet used
public class ObjectEqulatiyVerifier implements EqualityVerifier{
    
    /** Creates a new instance of ObjectEqulatiyVerifier */
    public ObjectEqulatiyVerifier() {
    }

    public boolean equals(Object a, Object b) {
        return a.equals(b);
    }
    
}
