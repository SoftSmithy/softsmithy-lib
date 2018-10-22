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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author puce
 */
public class Matchers {

    private Matchers() {
    }

    public static <T> List<MatchableObject<T>> wrap(List<T> list, Matcher<T> matcher) throws MatchingException {
        List<MatchableObject<T>> matchableObjects = new ArrayList<>();
        wrap(list, matchableObjects, matcher);
        return matchableObjects;
    }

    public static <T> List<T> unwrap(List<MatchableObject<T>> list) {
        List<T> objects = new ArrayList<>();
        unwrap(list, objects);
        return objects;
    }

    public static <T> Set<MatchableObject<T>> wrap(Set<T> list, Matcher<T> matcher) throws MatchingException {
        Set<MatchableObject<T>> matchableObjects = new LinkedHashSet<>();
        wrap(list, matchableObjects, matcher);
        return matchableObjects;
    }

    public static <T> Set<T> unwrap(Set<MatchableObject<T>> list) {
        Set<T> objects = new LinkedHashSet<>();
        unwrap(list, objects);
        return objects;
    }

    public static <T> void wrap(Collection<T> inputCollection, Collection<MatchableObject<T>> outputCollection, Matcher<T> matcher) throws MatchingException {
        for (T t : inputCollection) {
            outputCollection.add(new MatchableObject<>(t, matcher));
        }
    }

    public static <T> void unwrap(Collection<MatchableObject<T>> inputCollection, Collection<T> outputCollection) {
        inputCollection.forEach(matchableObject -> outputCollection.add(matchableObject.getObject()));
    }
}
