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
 * HorizontalAlignmentCellEditor.java
 *
 * Created on 7. Oktober 2002, 22:14
 */
package org.softsmithy.lib.swing;

import java.util.*;
import javax.swing.*;

/**
 *
 * @author puce
 */
public class HorizontalAlignmentCellEditor extends DefaultCellEditor {

    /**
     * Holds value of property locale.
     */
    private Locale locale;

    /**
     * Creates a new instance of HorizontalAlignmentCellEditor
     * @param locale the locale
     */
    public HorizontalAlignmentCellEditor(Locale locale) {
        super(new JComboBox());
        JComboBox comboBox = (JComboBox) this.getComponent();
        Vector<HorizontalAlignmentItem> horizontalAlignments = new Vector<>();
        for (HorizontalAlignment horizontalAlignment : HorizontalAlignment.VALUES) {
            horizontalAlignments.add(new HorizontalAlignmentItem(horizontalAlignment));
        }
        comboBox.setModel(new DefaultComboBoxModel(horizontalAlignments));
        setLocale(locale);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Object getCellEditorValue() {
        return ((HorizontalAlignmentItem) super.getCellEditorValue()).getHorizontalAlignment();
    }

    /**
     * Getter for property locale.
     *
     * @return Value of property locale.
     *
     */
    public Locale getLocale() {
        return this.locale;
    }

    /**
     * Setter for property locale.
     *
     * @param locale New value of property locale.
     *
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    private class HorizontalAlignmentItem {

        /**
         * Holds value of property fontStyle.
         */
        private final HorizontalAlignment horizontalAlignment;

        public HorizontalAlignmentItem(HorizontalAlignment horizontalAlignment) {
            this.horizontalAlignment = horizontalAlignment;
        }

        public HorizontalAlignment getHorizontalAlignment() {
            return this.horizontalAlignment;
        }

        /**
         * {@inheritDoc }
         */
        @Override
        public String toString() {
            return horizontalAlignment.getDisplayString(getLocale());
        }
    }
}
