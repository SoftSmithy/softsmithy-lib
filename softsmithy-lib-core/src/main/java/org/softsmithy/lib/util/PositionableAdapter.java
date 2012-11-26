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

import java.util.Objects;

/**
 *
 * @author puce
 */
public class PositionableAdapter<T> implements Positionable {

    private final T adapted;
    private final int position;

    public PositionableAdapter(T adapted, int position) {
        this.adapted = adapted;
        this.position = position;
    }

    public T getAdapted() {
        return adapted;
    }

    @Override
    public int getPosition() {
        return position;
    }
}
