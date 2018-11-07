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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


/**
 *
 * @author puce
 */
public class MatchersTest {

    /**
     * Test of wrap method, of class Matchers.
     */
    @Test
    public void testWrap_List_Matcher() throws Exception {
        System.out.println("wrap");
        // given
        List<String> strings = getTestStringsList();
        ObjectMatcher<String> matcher = new ObjectMatcher<>(String.class);
        // when
        List<MatchableObject<String>> wrappedStrings = Matchers.wrap(strings,
                matcher);
        // then
        assertEquals(getTestWrappedStringsList(matcher), wrappedStrings);
    }

    private List<String> getTestStringsList() {
        return Arrays.asList("test1", "test2", "test3");
    }

    private List<MatchableObject<String>> getTestWrappedStringsList(
            Matcher<String> matcher)
            throws MatchingException {
        List<MatchableObject<String>> wrappedStrings = new ArrayList<>();
        wrappedStrings.add(new MatchableObject<>("test1", matcher));
        wrappedStrings.add(new MatchableObject<>("test2", matcher));
        wrappedStrings.add(new MatchableObject<>("test3", matcher));
        return wrappedStrings;
    }

    private Set<String> getTestStringsSet() {
        return new HashSet<>(Arrays.asList("test1", "test2", "test3"));
    }

    private Set<MatchableObject<String>> getTestWrappedStringsSet(
            Matcher<String> matcher)
            throws MatchingException {
        Set<MatchableObject<String>> wrappedStrings = new HashSet<>();
        wrappedStrings.add(new MatchableObject<>("test1", matcher));
        wrappedStrings.add(new MatchableObject<>("test2", matcher));
        wrappedStrings.add(new MatchableObject<>("test3", matcher));
        assertEquals(3, wrappedStrings.size());
        return wrappedStrings;
    }

    /**
     * Test of unwrap method, of class Matchers.
     */
    @Test
    public void testUnwrap_List() throws MatchingException {
        System.out.println("unwrap");
        // given
        ObjectMatcher<String> matcher = new ObjectMatcher<>(String.class);
        List<MatchableObject<String>> wrappedStrings = getTestWrappedStringsList(
                matcher);

        // when
        List<String> strings = Matchers.unwrap(wrappedStrings);

        // then
        assertEquals(getTestStringsList(), strings);
    }

    /**
     * Test of wrap method, of class Matchers.
     */
    @Test
    public void testWrap_Set_Matcher() throws Exception {
        System.out.println("wrap");
        // given
        Set<String> strings = getTestStringsSet();
        ObjectMatcher<String> matcher = new ObjectMatcher<>(String.class);
        // when
        Set<MatchableObject<String>> wrappedStrings = Matchers.wrap(strings,
                matcher);
        // then
        assertEquals(getTestWrappedStringsSet(matcher), wrappedStrings);
    }

    /**
     * Test of unwrap method, of class Matchers.
     */
    @Test
    public void testUnwrap_Set() throws MatchingException {
        System.out.println("unwrap");
        // given
        ObjectMatcher<String> matcher = new ObjectMatcher<>(String.class);
        Set<MatchableObject<String>> wrappedStrings = getTestWrappedStringsSet(
                matcher);

        // when
        Set<String> strings = Matchers.unwrap(wrappedStrings);

        // then
        assertEquals(getTestStringsSet(), strings);
    }
}
