/*
 * EqualityVerifier.java
 *
 * Created on 11. Mai 2007, 00:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.softsmithy.lib.util;

/**
 *
 * Useful for generated classes, which don't override the equals method (e.g. some JAXB class generators)
 *
 * @author puce
 */
// TODO use generics with jdk v1,5
public interface EqualityVerifier {
    
    public boolean equals(Object a, Object b);
    
}
