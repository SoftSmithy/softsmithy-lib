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
 * FormattedEditor.java
 *
 * Created on 7. Oktober 2002, 14:48
 */
package org.softsmithy.lib.swing;

import java.awt.*;
import java.text.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author puce
 */
public class FormattedCellEditor extends DefaultCellEditor implements TableCellEditor {

    /**
     * Creates a new instance of FormattedEditor
     * @param format the format
     */
    public FormattedCellEditor(Format format) {
        this(format, new JFormattedTextField().getHorizontalAlignment());
    }

    public FormattedCellEditor(Format format, int horizontalAlignment) {
        this(new JFormattedTextField(format));
        getFormattedTextField().setHorizontalAlignment(horizontalAlignment);
    }

    public FormattedCellEditor(JFormattedTextField ftf) {
        super(ftf);
    }

    public JFormattedTextField getFormattedTextField() {
        return (JFormattedTextField) getComponent();
    }

    /**
     * Returns the value contained in the editor.
     *
     * @return the value contained in the editor
     */
    @Override
    public Object getCellEditorValue() {
        //System.out.println(getFormattedTextField().getValue().getClass());
        //System.out.println(getFormattedTextField().getValue());
        return getValue();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        setValue(value);
        return getFormattedTextField();
    }

    protected void setValue(Object value) {
        getFormattedTextField().setValue(value);
    }

    protected Object getValue() {
        return getFormattedTextField().getValue();
    }
}
