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

/**
 *
 * @author puce
 */
public class MatchableObject<T> {

    private final T object;
    private final Matcher<T> matcher;

    public MatchableObject(T object, Matcher<T> matcher) throws MatchingException {
        this.object = object;
        this.matcher = matcher;
        matcher.verify(object);
    }

    public T getObject() {
        return object;
    }

    public Matcher<T> getMatcher() {
        return matcher;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + matcher.hashCode();
        result = 37 * result + matcher.hashCode(object);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof MatchableObject)) {
            return false;
        }

        MatchableObject<?> matchableObject = (MatchableObject<?>) obj;

        if (!(matcher == matchableObject.matcher || (matcher != null && matcher.
                equals(matchableObject.matcher)))) {
            return false;
        }

        if (!matcher.getType().isInstance(matchableObject.object)) {
            return false;
        }

        return matcher.equals(object, matcher.getType().cast(
                matchableObject.object));
    }

    @Override
    public String toString() {
        return MatchableObject.class.getSimpleName() + "(matcher=" + matcher
                + ", object=" + object + ")";
    }
}
