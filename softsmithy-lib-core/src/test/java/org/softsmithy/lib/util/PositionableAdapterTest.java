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

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author puce
 */
public class PositionableAdapterTest {

    @Test
    public void testGetAdapted() {
        System.out.println("getAdapted");
        String adapted = "test";
        PositionableAdapter<String> adapter = new PositionableAdapter<>(adapted, 42);
        assertEquals(adapted, adapter.getAdapted());
    }

    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        int position = 42;
        PositionableAdapter<String> adapter = new PositionableAdapter<>("test", position);
        assertEquals(position, adapter.getPosition());
    }
}
