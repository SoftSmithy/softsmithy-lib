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

import org.junit.Test;
import org.softsmithy.lib.Tests;

/**
 *
 * @author puce
 */
public class PositionableComparatorTest {

    private final PositionableComparator testee = new PositionableComparator();

    @Test
    public void testCompare() {
        System.out.println("compare");

        Tests.testComparatorConsistentWithEquals(testee,
                new TestPositionable(5),
                new TestPositionable(3),
                new TestPositionable(5),
                new TestPositionable(7),
                false);
    }

    private static class TestPositionable implements Positionable {

        private final int position;

        public TestPositionable(int position) {
            this.position = position;
        }

        @Override
        public int getPosition() {
            return position;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 13 * hash + this.position;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof TestPositionable)) {
                return false;
            }
            final TestPositionable other = (TestPositionable) obj;
            return this.position == other.position;
        }
    }
}
