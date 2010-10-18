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
 * VerticalAlignment.java
 *
 * Created on 7. Oktober 2002, 19:49
 */
package org.softsmithy.lib.swing;

import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.util.*;

/**
 *
 * @author  puce
 */
public abstract class VerticalAlignment extends TypesafeEnum {

    private static final String RB_BASE_NAME = "org.softsmithy.lib.swing.VerticalAlignment";

    /** Creates a new instance of VerticalAlignment */
    private VerticalAlignment(String s) {
        super(s);
    }

    public String toString(Locale locale) {
        return ResourceBundle.getBundle(RB_BASE_NAME, locale).getString(toString());
    }

    public abstract int getSwingConstant();
    public static final VerticalAlignment TOP = new VerticalAlignment("top") {

        public int getSwingConstant() {
            return SwingConstants.TOP;
        }
    };
    public static final VerticalAlignment CENTER = new VerticalAlignment("center") {

        public int getSwingConstant() {
            return SwingConstants.CENTER;
        }
    };
    public static final VerticalAlignment BOTTOM = new VerticalAlignment("bottom") {

        public int getSwingConstant() {
            return SwingConstants.BOTTOM;
        }
    };
    private static final VerticalAlignment[] PRIVATE_VALUES = {TOP, CENTER, BOTTOM};
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
    private static final Map alignments = new HashMap();


    static {
        for (int i = 0; i < PRIVATE_VALUES.length; i++) {
            alignments.put(new Integer(PRIVATE_VALUES[i].getSwingConstant()), PRIVATE_VALUES[i]);
        }
    }

    public static VerticalAlignment getVerticalAlignment(int verticalAlignment) {
        return (VerticalAlignment) alignments.get(new Integer(verticalAlignment));
    }
}
