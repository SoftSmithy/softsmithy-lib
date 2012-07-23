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
public class ColorCellRenderer extends JPanel implements TableCellRenderer {

    private Border unselectedBorder = null;
    private Border selectedBorder = null;
    private boolean isBordered = true;
    /** Holds value of property color. */
    private Color color = null;

    public ColorCellRenderer(boolean isBordered) {
        super();
        this.isBordered = isBordered;
        setOpaque(false);
        setLayout(new GridLayout(1, 2));
        JLabel textLabel = new JLabel() {
//      public String getText(){
//        return ((color != null) ? "[" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "]" : "");
//      }
        };
        JLabel colorLabel = new JLabel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (color != null) {
                    Rectangle innerArea = SwingUtilities.calculateInnerArea(this, null);
                    if (innerArea.height > 0) {
                        Color oldColor = g.getColor();
                        g.setColor(Color.BLACK);
                        int x = innerArea.x + innerArea.width / 2 - innerArea.height / 2;
                        int y = innerArea.y;
                        int size = innerArea.height - 1;
                        g.drawRect(x, y, size, size);
                        if (size > 1) {
                            g.setColor(color);
                            g.fillRect(x + 1, y + 1, size - 1, size - 1);
                        }
                        g.setColor(oldColor);
                    }
                }
            }
        };
        textLabel.setOpaque(false);
        colorLabel.setOpaque(false);
        add(colorLabel);
        add(textLabel);
    }

    public Component getTableCellRendererComponent(JTable table, Object color, boolean isSelected, boolean hasFocus, int row, int column) {
        setColor((Color) color);
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

    /** Getter for property color.
     * @return Value of property color.
     *
     */
    public Color getColor() {
        return this.color;
    }

    /** Setter for property color.
     * @param color New value of property color.
     *
     */
    public void setColor(Color color) {
        this.color = color;
    //setBackground(color);
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
    //    Rectangle innerArea = SwingUtilities.calculateInnerArea(this, null);
    //    Color oldColor = g.getColor();
    //    g.setColor(color);
    //    g.fillRect(innerArea.x, innerArea.y, innerArea.height, innerArea.height);
    //    g.setColor(oldColor);
    //    //g.drawString("[" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "]",
    //  }
    //
    //  /**
    //   * Returns the text string that the label displays.
    //   *
    //   * @return a String
    //   * @see #setText
    //   *
    //   */
    //  public String getText() {
    //    return (color != null) ? "[" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "]" : "";
    //  }
    //
}