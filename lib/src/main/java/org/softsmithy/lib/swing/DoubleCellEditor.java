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
 * DoubleCellEditor.java
 *
 * Created on 7. Oktober 2002, 16:21
 */
package org.softsmithy.lib.swing;

import java.text.*;

/**
 *
 * @author  puce
 */
public class DoubleCellEditor extends FormattedCellEditor {

    /** Creates a new instance of DoubleCellEditor */
    public DoubleCellEditor() {
        super(new JDoubleField());
    }

    public DoubleCellEditor(NumberFormat format) {
        super(new JDoubleField(format));
    }

    public DoubleCellEditor(double minValue, double maxValue) {
        super(new JDoubleField(minValue, maxValue));
    }

    public DoubleCellEditor(NumberFormat format, double minValue, double maxValue) {
        super(new JDoubleField(format, minValue, maxValue));
    }

    public JDoubleField getDoubleField() {
        return (JDoubleField) getFormattedTextField();
    }

    /** Returns the value contained in the editor.
     * @return the value contained in the editor
     *
     *
     */
    //  public Object getCellEditorValue() {
    //    System.out.println(super.getCellEditorValue().getClass());
    //    System.out.println(super.getCellEditorValue());
    //    Number number = (Number) super.getCellEditorValue(); //sometimes an Integer is returned, sometimes a Long???
    //    return new Integer(number.intValue());
    //  }
    @Override
    protected void setValue(Object value) {
        getDoubleField().setDoubleValue(((Double) value).doubleValue());
    }

    @Override
    protected Object getValue() {
        return new Double(getDoubleField().getDoubleValue());
    }
}
