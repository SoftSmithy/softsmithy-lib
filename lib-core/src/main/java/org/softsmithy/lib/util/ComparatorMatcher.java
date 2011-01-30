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
 * ComparatorMatcher.java
 *
 * Created on 11. Mai 2007, 00:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.softsmithy.lib.util;

import java.util.Comparator;

/**
 *
 * @author puce
 */
// Not yet used!
public class ComparatorMatcher<T> extends AbstractMatcher<T> {

    private final Comparator<? super T> comparator;

    /** Creates a new instance of ComparatorMatcher */
    public ComparatorMatcher(Class<T> type, Comparator<? super T> comparator) {
        super(type);
        this.comparator = comparator;
    }

    @Override
    public void verify(T t) throws MatchingException {
        // nothing to do
    }

    @Override
    public int hashCode(T t) {
        int result = 17;
        result = 37 * result + comparator.hashCode();
        result = 37 * result + t.hashCode();
        return result;
    }

    @Override
    public boolean equals(T a, T b) {
        // TODO complete (null, exception...)
        return Comparables.isEqual(a, b, comparator);
    }

    @Override
    public String toString(T t) {
        return "comparator=" + comparator + ", object=" + t;
    }

    @Override
    public int hashCode() {
        int result = 17;
        // unique hashCode per implementation
        result = 37 * result + comparator.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ComparatorMatcher)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ComparatorMatcher.class.getSimpleName()+"(comparator=" + comparator + ")";
    }
}
