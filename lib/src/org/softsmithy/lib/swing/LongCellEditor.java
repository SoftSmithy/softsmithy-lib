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
 * IntegerCellEditor.java
 *
 * Created on 7. Oktober 2002, 16:21
 */
package org.softsmithy.lib.swing;

import java.util.*;

/**
 *
 * @author  puce
 */
public class LongCellEditor extends FormattedCellEditor {

    /** Creates a new instance of IntegerCellEditor */
    public LongCellEditor(Locale locale) {
        super(new JLongField(locale));
    }

    public LongCellEditor(long minValue, long maxValue, Locale locale) {
        super(new JLongField(minValue, maxValue, locale));
    }

    public JLongField getLongField() {
        return (JLongField) getFormattedTextField();
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
    protected void setValue(Object value) {
        getLongField().setLongValue(((Long) value).longValue());
    }

    protected Object getValue() {
        return new Long(getLongField().getLongValue());
    }
}
