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
package org.softsmithy.lib.swing.table;

import org.softsmithy.lib.swing.JXTextField;

import javax.swing.*;

public class TextFieldTableCellRenderer extends PanelTableCellRenderer {

    private final JXTextField textfield = new JXTextField("");

    public TextFieldTableCellRenderer() {
        textfield.setEditable(false);
        setRenderer(textfield);
        textfield.setStyle(textfield.getParentStyle());
    }

    /**
     * Sets the
     * <code>String</code> object for the cell being rendered to
     * <code>value</code>.
     *
     * @param value the string value for this cell; if value is <code>null</code> it sets the text value to an empty
     * string
     * @see JLabel#setText(java.lang.String)
     *
     *
     */
    @Override
    public void setValue(Object value) {
        textfield.setText((value == null) ? "" : value.toString());
    }

    public String getText() {
        return textfield.getText();
    }
}
