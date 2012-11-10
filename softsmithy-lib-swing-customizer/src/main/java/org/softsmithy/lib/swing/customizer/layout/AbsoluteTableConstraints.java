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
 * DefaultTableLayoutConstraints.java
 *
 * Created on 23. August 2002, 12:48
 */
package org.softsmithy.lib.swing.customizer.layout;

import org.softsmithy.lib.swing.customizer.layout.AbstractTableConstraints;
import java.awt.Component;
import java.awt.Rectangle;

/**
 *
 * @author puce
 */
public class AbsoluteTableConstraints extends AbstractTableConstraints {

    /**
     * Holds value of property x.
     */
    private int x;
    /**
     * Holds value of property y.
     */
    private int y;
    /**
     * Holds value of property width.
     */
    private int width;
    /**
     * Holds value of property height.
     */
    private int height;

    /**
     * Creates a new instance of DefaultTableLayoutConstraints
     */
    public AbsoluteTableConstraints(int x, int y, int width, int height,
            Component component, TableLayout tl) {
        this(new Rectangle(x, y, width, height), component, tl);
    }

    public AbsoluteTableConstraints(Rectangle bounds, Component component,
            TableLayout tl) {
        super(component, tl);
        setAbsoluteBounds(bounds);
    }

    /**
     * Getter for property x.
     *
     * @return Value of property x.
     */
    @Override
    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        setXOnly(x);
        updateTableLayout();
    }

    private void setXOnly(int x) {
        this.x = getTableLayout().adjustX(x);
    }

    /**
     * Getter for property y.
     *
     * @return Value of property y.
     */
    @Override
    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        setYOnly(y);
        updateTableLayout();
    }

    private void setYOnly(int y) {
        this.y = getTableLayout().adjustY(y);
    }

    /**
     * Getter for property width.
     *
     * @return Value of property width.
     */
    @Override
    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        setWidthOnly(width);
        updateTableLayout();
    }

    private void setWidthOnly(int width) {
        this.width = getTableLayout().adjustWidth(getX(), width);
    }

    /**
     * Getter for property height.
     *
     * @return Value of property height.
     */
    @Override
    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        setHeightOnly(height);
        updateTableLayout();
    }

    private void setHeightOnly(int height) {
        this.height = getTableLayout().adjustHeight(getY(), height);
    }

    @Override
    protected void setAbsoluteBoundsOnly(Rectangle bounds) {
        //    if (!(cl instanceof TableLayout)){
        //      throw new IllegalArgumentException("cl must be a Tablelayout!");
        //    }
        //    TableLayout tl = (TableLayout) cl;
        Rectangle adjustedBounds = getTableLayout().adjustBounds(bounds);
        this.x = adjustedBounds.x;
        this.y = adjustedBounds.y;
        this.width = adjustedBounds.width;
        this.height = adjustedBounds.height;
    }

    @Override
    public Rectangle getAbsoluteBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public Rectangle getRelativeBounds() {
        int column = getTableLayout().columnIndex(x);
        int row = getTableLayout().rowIndex(y);
        return new Rectangle(column, row,
                getTableLayout().colSpan(column, width),
                getTableLayout().rowSpan(row, height));
    }

    @Override
    protected void setRelativeBoundsOnly(Rectangle bounds) {
        setX(getTableLayout().xLocation(bounds.x));
        setY(getTableLayout().yLocation(bounds.y));
        setWidth(getTableLayout().width(bounds.x, bounds.width));
        setHeight(getTableLayout().height(bounds.y, bounds.height));
    }
}
