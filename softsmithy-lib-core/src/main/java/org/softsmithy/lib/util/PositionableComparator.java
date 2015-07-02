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

import java.util.Comparator;

/**
 * {@link Comparator} for {@link Positionable}s.
 *
 * @author puce
 */
public class PositionableComparator implements Comparator<Positionable> {

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(Positionable o1, Positionable o2) {
        if (o1.getPosition() < o2.getPosition()) {
            return -1;
        } else if (o1.getPosition() > o2.getPosition()) {
            return 1;
        } else {
            return 0;
        }
    }
}
