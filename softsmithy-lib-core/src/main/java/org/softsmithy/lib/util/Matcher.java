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
 * Matcher.java
 *
 * Created on 11. Mai 2007, 00:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.softsmithy.lib.util;

/**
 *
 * Useful for generated classes, which don't override the equals method 
 * (e.g. some JAXB class generators)
 * or if a custom matching algorithm is needed.
 *
 * @author puce
 */
public interface Matcher<T> {

    /**
     * Verifies that the specified object can be used with this matcher.
     *
     * TODO: useful?
     * @param t the object to test
     * @throws MatchingException
     */
    public void verify(T t) throws MatchingException;

    public int hashCode(T t);

    public boolean equals(T a, T b);

    public String toString(T t);

    /**
     * TODO: needed? Better way to implement MatchableObject#equals?
     */
    public Class<T> getType();
}
