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
package org.softsmithy.lib.swing.style;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.swing.*;

/**
 *
 * @author  puce
 */
public class StyleCellEditor extends DefaultCellEditor {

    private static final JCustomizer DUMMY_CUSTOMIZER = new JCustomizer();
    /** Holds value of property locale. */
    private Locale locale;

    /** Creates a new instance of HorizontalAlignmentCellEditor */
    public StyleCellEditor(Locale locale) {
        super(new StyleProviderComboBox(locale));
        setLocale(locale);
    }

    /** Getter for property locale.
     * @return Value of property locale.
     *
     */
    public Locale getLocale() {
        return this.locale;
    }

    /** Setter for property locale.
     * @param locale New value of property locale.
     *
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
        getComponent().setLocale(locale);
    }

    /** Implements the <code>TableCellEditor</code> interface.  */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        reloadComboBox();
        Style style = (Style) value;
        return super.getTableCellEditorComponent(table, style.getStyleProvider(), isSelected, row, column);
    }

    private void reloadComboBox() {
        StyleProviderComboBox comboBox = (StyleProviderComboBox) this.getComponent();
        comboBox.reloadModel();
    }

    /** Forwards the message from the <code>CellEditor</code> to
     * the <code>delegate</code>.
     * @see EditorDelegate#getCellEditorValue
     *
     */
    @Override
    public Object getCellEditorValue() {
        StyleProvider provider = (StyleProvider) super.getCellEditorValue();
        return provider.getStyle(DUMMY_CUSTOMIZER);
    }
}
