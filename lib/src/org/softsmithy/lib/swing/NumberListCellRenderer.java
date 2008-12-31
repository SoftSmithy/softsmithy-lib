/*
 *                  Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

/*
 * NumberCellRenderer.java
 *
 * Created on 1. Oktober 2002, 11:46
 */
package org.softsmithy.lib.swing;

import javax.swing.*;
import javax.swing.plaf.basic.*;

/**
 *
 * @author  puce
 */
class NumberListCellRenderer extends DefaultListCellRenderer { //BasicComboBoxRenderer {

    /** Creates a new instance of NumberCellRenderer */
    public NumberListCellRenderer() {
        setHorizontalAlignment(SwingConstants.RIGHT);
    }
}
