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
 * ObjectMatcher.java
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
public class ObjectMatcher<T> extends AbstractMatcher<T> {

    public ObjectMatcher(Class<T> type) {
        super(type);
    }


    @Override
    public void verify(T t) throws MatchingException {
        // nothing to do
    }

    @Override
    public int hashCode(T t) {
        return t.hashCode();
    }

    @Override
    public boolean equals(T a, T b) {
        return a.equals(b);
    }

    @Override
    public String toString(T t) {
        return t.toString();
    }

    @Override
    public int hashCode() {
         int result = 17;
         // unique hashCode per implementation
        result = 37 * result + ObjectMatcher.class.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ObjectMatcher)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ObjectMatcher.class.getSimpleName() + "()";
    }

    
}
