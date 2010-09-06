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
package org.softsmithy.lib.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/**
 * From: http://java.sun.com/docs/books/tutorial/uiswing/components/example-swing/TableRenderDemo.java
 */
public class FontCellRenderer extends JLabel implements TableCellRenderer {

    private Border unselectedBorder = null;
    private Border selectedBorder = null;
    private boolean isBordered = true;
    /** Holds value of property selectedFont. */
    private Font selectedFont;

    public FontCellRenderer(boolean isBordered) {
        super();
        this.isBordered = isBordered;
        setOpaque(false);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object font, boolean isSelected, boolean hasFocus, int row, int column) {
        setSelectedFont((Font) font);
        if (isBordered) {
            if (isSelected) {
                if (selectedBorder == null) {
                    selectedBorder =
                            BorderFactory.createMatteBorder(2, 5, 2, 5, table.getSelectionBackground());
                }
                setBorder(selectedBorder);
            } else {
                if (unselectedBorder == null) {
                    unselectedBorder = BorderFactory.createMatteBorder(2, 5, 2, 5, table.getBackground());
                }
                setBorder(unselectedBorder);
            }
        }
        return this;
    }

    /**
     * Returns the text string that the label displays.
     *
     * @return a String
     * @see #setText
     *
     */
    @Override
    public String getText() {
        return (getSelectedFont() != null ? getSelectedFont().getName() + " " + getSelectedFont().getSize() : "");
    }

    /** Getter for property selectedFont.
     * @return Value of property selectedFont.
     *
     */
    public Font getSelectedFont() {
        return this.selectedFont;
    }

    /** Setter for property selectedFont.
     * @param selectedFont New value of property selectedFont.
     *
     */
    public void setSelectedFont(Font selectedFont) {
        this.selectedFont = selectedFont;
    }
    /** Calls the UI delegate's paint method, if the UI delegate
     * is non-<code>null</code>.  We pass the delegate a copy of the
     * <code>Graphics</code> object to protect the rest of the
     * paint code from irrevocable changes
     * (for example, <code>Graphics.translate</code>).
     * <p>
     * If you override this in a subclass you should not make permanent
     * changes to the passed in <code>Graphics</code>. For example, you
     * should not alter the clip <code>Rectangle</code> or modify the
     * transform. If you need to do these operations you may find it
     * easier to create a new <code>Graphics</code> from the passed in
     * <code>Graphics</code> and manipulate it. Further, if you do not
     * invoker super's implementation you must honor the opaque property,
     * that is
     * if this component is opaque, you must completely fill in the background
     * in a non-opaque color. If you do not honor the opaque property you
     * will likely see visual artifacts.
     *
     * @param g the <code>Graphics</code> object to protect
     * @see #paint
     * @see ComponentUI
     *
     */
//  protected void paintComponent(Graphics g) {
//    super.paintComponent(g);
//  }
}