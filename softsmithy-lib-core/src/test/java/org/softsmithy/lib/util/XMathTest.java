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
 * XMathTest.java
 * JUnit based test
 *
 * Created on 4. Juli 2002, 22:25
 */
package org.softsmithy.lib.util;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 *
 * @author puce
 */
public class XMathTest {

    /**
     * Test of toStandardInterval method, of class org.softsmithy.lib.util.XMath.
     */
    @Test
    public void testToStandardInterval() {
        assertTrue((XMath.toStandardInterval(5 * Math.PI / 2) == Math.PI / 2)
                && (XMath.toStandardInterval(3 * Math.PI) == Math.PI)
                && (XMath.toStandardInterval(7 * Math.PI / 2) == 3 * Math.PI / 2)
                && (XMath.toStandardInterval(4 * Math.PI) == 0)
                && (XMath.toStandardInterval(-5 * Math.PI / 2) == 3 * Math.PI / 2)
                && (XMath.toStandardInterval(-3 * Math.PI) == Math.PI)
                && (XMath.toStandardInterval(-7 * Math.PI / 2) == Math.PI / 2)
                && (XMath.toStandardInterval(-4 * Math.PI) == 0)
                && (XMath.toStandardInterval(-Math.PI - 1) == Math.PI - 1));
    }

}
