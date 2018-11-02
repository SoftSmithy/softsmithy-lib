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
package org.softsmithy.lib.swing.multisplit;

import java.awt.*;
import javax.swing.*;
import org.softsmithy.lib.util.*;

public abstract class SplitOrientation extends TypesafeEnum {

    private SplitOrientation(String name) {
        super(name);
    }

    public abstract int getSplitPaneConstant();

    public abstract int getPreferredSize(Component component);

    public abstract int getSize(Component component);

    public static final SplitOrientation HORIZONTAL = new SplitOrientation("horizontal") {
        @Override
        public int getSplitPaneConstant() {
            return JSplitPane.HORIZONTAL_SPLIT;
        }

        @Override
        public int getPreferredSize(Component component) {
            return component.getPreferredSize().width;
        }

        @Override
        public int getSize(Component component) {
            return component.getWidth();
        }
    };

    public static final SplitOrientation VERTICAL = new SplitOrientation("vertical") {
        @Override
        public int getSplitPaneConstant() {
            return JSplitPane.VERTICAL_SPLIT;
        }

        @Override
        public int getPreferredSize(Component component) {
            return component.getPreferredSize().height;
        }

        @Override
        public int getSize(Component component) {
            return component.getHeight();
        }
    };

}
