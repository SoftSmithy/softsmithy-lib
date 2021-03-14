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
 * UnlimitedTableLayout.java
 *
 * Created on 22. August 2002, 18:12
 */
package org.softsmithy.lib.swing.customizer.layout;

import org.softsmithy.lib.awt.AWTUtilities;
import org.softsmithy.lib.math.BigIntegers;

import java.awt.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author puce
 */
public class InfiniteTableLayout extends AbstractTableLayout {

    /**
     * Holds value of property constraints.
     */
    private final Map<Component, TableConstraints> constraints = new HashMap<>();
    private final InfiniteAxis columns;
    private final InfiniteAxis rows;
    private final Dimension occupiedDimension = new Dimension(0, 0);
    private final Container parent;
    private final Map<Component, Integer> compHeights = new HashMap<>();
    private final SortedSet<Component> sortedCompHeights = new TreeSet<>(new CompHeightsComparator());
    private final Map<Component, Integer> compWidths = new HashMap<>();
    private final SortedSet<Component> sortedCompWidths = new TreeSet<>(new CompWidthsComparator());

    /**
     * Creates a new instance of UnlimitedTableLayout
     */
    public InfiniteTableLayout(int defaultColumnWidth, int defaultRowHeight,
            Container parent) {
        columns = new InfiniteAxis(defaultColumnWidth) {

            @Override
            public void drawLine(int startPixel, int endPixel, int offset,
                    Graphics g) {
                g.drawLine(offset, startPixel, offset, endPixel);
            }
        };
        rows = new InfiniteAxis(defaultRowHeight) {

            @Override
            public void drawLine(int startPixel, int endPixel, int offset,
                    Graphics g) {
                g.drawLine(startPixel, offset, endPixel, offset);
            }
        };
        ensureValidity(parent);
        this.parent = parent;
    }

    public InfiniteTableLayout(Container parent) {
        this(10, 10, parent);
    }

    public Container getParent() {
        return this.parent;
    }

    @Override
    public void setColumnWidth(int column, double width) {
        columns.setSize(column, (int) width);
    }

    @Override
    public double getColumnWidth(int column) {
        return columns.getSize(column);
    }

    public void makeColumnDefault(int column) {
        columns.makeDefault(column);
    }

    public boolean isDefaultColumn(int column) {
        return columns.isDefault(column);
    }

    @Override
    public void setRowHeight(int row, double height) {
        rows.setSize(row, (int) height);
    }

    @Override
    public double getRowHeight(int row) {
        return rows.getSize(row);
    }

    public void makeRowDefault(int row) {
        rows.makeDefault(row);
    }

    public boolean isDefaultRow(int row) {
        return rows.isDefault(row);
    }

    /**
     * Getter for property defaultColumnWidth.
     *
     * @return Value of property defaultColumnWidth.
     */
    public int getDefaultColumnWidth() {
        return columns.getDefaultSize();
    }

    /**
     * Setter for property defaultColumnWidth.
     *
     * @param defaultColumnWidth New value of property defaultColumnWidth.
     */
    public void setDefaultColumnWidth(int defaultColumnWidth, boolean absolute) {
        Map<Component, Rectangle> bounds = getBounds(absolute);
        columns.setDefaultSize(defaultColumnWidth);
        setBounds(bounds, absolute);

    }

    /**
     * Getter for property defaultRowHeight.
     *
     * @return Value of property defaultRowHeight.
     */
    public int getDefaultRowHeight() {
        return rows.getDefaultSize();
    }

    /**
     * Setter for property defaultRowHeight.
     *
     * @param defaultRowHeight New value of property defaultRowHeight.
     */
    public void setDefaultRowHeight(int defaultRowHeight, boolean absolute) {
        Map<Component, Rectangle> bounds = getBounds(absolute);
        rows.setDefaultSize(defaultRowHeight);
        setBounds(bounds, absolute);
    }

    /**
     * Getter for property constraints.
     *
     * @return Value of property constraints.
     */
    @Override
    public TableConstraints getTableConstraints(Component comp) {
        return constraints.get(comp);
    }

    @Override
    public CustomizerConstraints getCustomizerConstraints(Component comp) {
        return getTableConstraints(comp);
    }

    @Override
    public void setTableConstraints(Component comp, TableConstraints tc) {
        //    if (!(constr instanceof TableConstraints)){
        //      throw new IllegalArgumentException();
        //    }
        //    TableConstraints tc = (TableConstraints) constr;
        constraints.put(comp, tc);
        int preferredWidth = getRightPosition(comp);//??
        if (sortedCompWidths.contains(comp)) {
            sortedCompWidths.remove(comp);
        }
        compWidths.put(comp, preferredWidth);
        sortedCompWidths.add(comp);
        int preferredHeight = getBottomPosition(comp);//??
        if (sortedCompHeights.contains(comp)) {
            sortedCompHeights.remove(comp);
        }
        compHeights.put(comp, preferredHeight);
        sortedCompHeights.add(comp);
        resetOccupiedDimension();
    }

    @Override
    public void setCustomizerConstraints(Component comp,
            CustomizerConstraints cc) {
        if (!(cc instanceof TableConstraints)) {
            throw new IllegalArgumentException(
                    "CustomizerConstraints must be an instance of TableConstraints!");
        }
        setTableConstraints(comp, (TableConstraints) cc);
    }

    private int getRightPosition(Component comp) {
        TableConstraints tc = constraints.get(comp);
        BigInteger rightPosition = BigInteger.valueOf(tc.getX()).add(BigInteger.
                valueOf(tc.getWidth()));
        rightPosition = rightPosition.min(BigIntegers.MAX_INTEGER); // ???
        return rightPosition.intValue();
    }

    private int getBottomPosition(Component comp) {
        TableConstraints tc = constraints.get(comp);
        BigInteger leftPosition = BigInteger.valueOf(tc.getY()).add(BigInteger.
                valueOf(tc.getHeight()));
        leftPosition = leftPosition.min(BigIntegers.MAX_INTEGER); //???
        return leftPosition.intValue();
    }

    private void resetOccupiedDimension() {
        occupiedDimension.width = sortedCompWidths.isEmpty() ? 0 : compWidths.
                get(sortedCompWidths.last());
        occupiedDimension.height = sortedCompHeights.isEmpty() ? 0 : compHeights.
                get(sortedCompHeights.last());
    }

    @Override
    public void deleteColumn(int i, boolean absolute) {
        Map<Component, Rectangle> bounds = getBounds(absolute);
        columns.delete(i);
        setBounds(bounds, absolute);
    }

    @Override
    public void deleteRow(int i, boolean absolute) {
        Map<Component, Rectangle> bounds = getBounds(absolute);
        rows.delete(i);
        setBounds(bounds, absolute);
    }

    @Override
    public void drawGrid(Container container, Graphics g) {
        Rectangle innerArea = AWTUtilities.calculateInnerArea(container, null);
        ensureValidity(innerArea);
        columns.draw(innerArea.y, innerArea.height, g);
        rows.draw(innerArea.x, innerArea.width, g);
    }

    @Override
    public void drawLayoutHelp(Container container, Graphics g) {
        drawGrid(container, g);
    }

    @Override
    public void insertColumn(int i, boolean absolute) {
        Map<Component, Rectangle> bounds = getBounds(absolute);
        columns.insert(i);
        setBounds(bounds, absolute);

    }

    @Override
    public void insertRow(int i, boolean absolute) {
        Map<Component, Rectangle> bounds = getBounds(absolute);
        rows.insert(i);
        setBounds(bounds, absolute);
    }

    private Map<Component, Rectangle> getBounds(boolean absolute) {
        Map<Component, Rectangle> bounds;
        if (absolute) {
            bounds = getAbsoluteBounds();
        } else {
            bounds = getRelativeBounds();
        }
        return bounds;
    }

    private void setBounds(Map<Component, Rectangle> bounds, boolean absolute) {
        if (absolute) {
            setAbsoluteBounds(bounds);
        } else {
            setRelativeBounds(bounds);
        }
    }

    private Map<Component, Rectangle> getAbsoluteBounds() {
        Map<Component, Rectangle> bounds = new HashMap<>();
        Component[] components = getComponents(parent);
        for (Component component : components) {
            bounds.put(component, getTableConstraints(component).getAbsoluteBounds());
        }
        return bounds;
    }

    private Map<Component, Rectangle> getRelativeBounds() {
        Map<Component, Rectangle> bounds = new HashMap<>();
        Component[] components = getComponents(parent);
        for (Component component : components) {
            bounds.put(component, getTableConstraints(component).getRelativeBounds());
        }
        return bounds;
    }

    private void setAbsoluteBounds(Map<Component, Rectangle> bounds) {
        Component[] components = getComponents(parent);
        for (Component component : components) {
            setAbsoluteBounds(component, bounds.get(component));
            //setTableConstraints(component, constraints);
        }
    }

    private void setRelativeBounds(Map<Component, Rectangle> bounds) {
        Component[] components = getComponents(parent);
        for (Component component : components) {
            setRelativeBounds(component, bounds.get(component));
            //setTableConstraints(component, constraints);
        }
    }

    //  public void layoutComponent(Container parent, Component comp) {
    //    TableConstraints constr = (TableConstraints) getConstraints(comp);
    //    if (constr != null){
    //      ensureValidity(parent);
    //      //constr.layoutComponent(comp, this);
    //      int x = constr.getX(this); //columns.getOffset(constr.getX());
    //      if (x >= 0){
    //        int y = constr.getY(this); //rows.getOffset(constr.getY());
    //        if (y >= 0){
    //          int width = constr.getWidth(this); //columns.getOffset(constr.getX() + constr.getWidth()) - x; //??
    //          if (width > 0){
    //            int height = constr.getHeight(this); //rows.getOffset(constr.getY() + constr.getHeight()) - y;// ??
    //            if (height > 0){
    //              comp.setBounds(x, y, width, height);
    //            }
    //          }
    //        }
    //      }
    //    }
    //  }
    /**
     * Calculates the minimum size dimensions for the specified container, given the components it contains.
     *
     * @param parent the component to be laid out
     * @see #preferredLayoutSize
     */
    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return new Dimension(0, 0); //???
    }

    /**
     * Calculates the preferred size dimensions for the specified container, given the components it contains.
     *
     * @param parent the container to be laid out
     *
     * @see #minimumLayoutSize
     */
    @Override
    public Dimension preferredLayoutSize(Container parent) { //??
        //    return new Dimension(columns.preferredLayoutSize(occupiedDimension.width),
        //    rows.preferredLayoutSize(occupiedDimension.height));
        return new Dimension(occupiedDimension);
    }

    /**
     * Removes the specified component from the layout.
     *
     * @param comp the component to be removed
     */
    @Override
    public void removeLayoutComponent(Component comp) {
        constraints.remove(comp);
        sortedCompWidths.remove(comp);
        compWidths.remove(comp);
        sortedCompHeights.remove(comp);
        compHeights.remove(comp);
        resetOccupiedDimension();
    }

    @Override
    public int columnIndex(int pixel) {
        return columns.offsetIndex(pixel);
    }

    @Override
    public int rowIndex(int pixel) {
        return rows.offsetIndex(pixel);
    }

    @Override
    public int colSpan(int fromIndex, int pixelWidth) {
        return columns.offsetSpan(fromIndex, pixelWidth);
    }

    @Override
    public int rowSpan(int fromIndex, int pixelHeight) {
        return rows.offsetSpan(fromIndex, pixelHeight);
    }

    @Override
    public int width(int fromIndex, int colSpan) {
        return columns.size(fromIndex, colSpan);
    }

    @Override
    public int height(int fromIndex, int rowSpan) {
        return rows.size(fromIndex, rowSpan);
    }

    @Override
    public int yLocation(int index) {
        return rows.location(index);
    }

    @Override
    public int xLocation(int index) {
        return columns.location(index);
    }

    @Override
    public int adjustX(int pixel) {
        ensureValidity(parent);
        return columns.location(columns.offsetIndex(pixel));
    }

    @Override
    public int adjustY(int pixel) {
        ensureValidity(parent);
        return rows.location(rows.offsetIndex(pixel));
    }

    @Override
    public int adjustWidth(int xPixel, int pixelWidth) {
        ensureValidity(parent);
        int xIndex = columns.index(xPixel);
        return columns.size(xIndex, columns.span(xIndex, pixelWidth));
    }

    @Override
    public int adjustHeight(int yPixel, int pixelHeight) {
        ensureValidity(parent);
        int yIndex = rows.index(yPixel);
        return rows.size(yIndex, rows.span(yIndex, pixelHeight));
    }

    @Override
    public Rectangle adjustBounds(Rectangle bounds) {
        ensureValidity(parent);
        int xIndex = columns.index(bounds.x);
        int yIndex = rows.index(bounds.y);
        bounds.x = columns.location(xIndex);
        bounds.y = rows.location(yIndex);
        bounds.width = columns.size(xIndex, columns.span(xIndex, bounds.width));
        bounds.height = rows.size(yIndex, rows.span(yIndex, bounds.height));
        return bounds;
    }

    /**
     * Indicates whether or not the size of the cells are known for the last known size of the container. If valid is false or the container has been resized, the cell sizes must be recalculated using
     * calculateSize.
     *
     */
    @Override
    protected AbstractAxis getColumns() {
        return columns;
    }

    @Override
    protected AbstractAxis getRows() {
        return rows;
    }

    @Override
    public void setAbsoluteBounds(Component component, Rectangle bounds) {
        TableConstraints tc = getTableConstraints(component);
        if (tc == null) {
            //      tc = new AbsoluteTableConstraints(bounds, component, this);
            //      getParent().add(component, tc);
        } else {
            tc.setAbsoluteBounds(bounds);
            setTableConstraints(component, tc);
            layoutComponent(getParent(), component);
        }
    }

    @Override
    public void setRelativeBounds(Component component, Rectangle bounds) {
        TableConstraints tc = getTableConstraints(component);
        if (tc == null) {
            //      tc = new RelativeTableConstraints(bounds, component, this);
            //      getParent().add(component, tc);
        } else {
            tc.setRelativeBounds(bounds);
            setTableConstraints(component, tc);
            layoutComponent(getParent(), component);
        }
    }

    private abstract static class InfiniteAxis extends AbstractAxis {

        /**
         * Holds value of property defaultSize.
         */
        private int defaultSize;
        /**
         * Holds value of property sizes.
         */
        private final SortedMap<Integer, Integer> sizes = new TreeMap<>();
        private final List<Integer> offsets = new ArrayList<>();

        public InfiniteAxis(int defaultSize) {
            this.defaultSize = defaultSize;
        }

        /**
         * Getter for property defaultSize.
         *
         * @return Value of property defaultSize.
         */
        public int getDefaultSize() {
            return this.defaultSize;
        }

        /**
         * Setter for property defaultSize.
         *
         * @param defaultSize New value of property defaultSize.
         */
        public void setDefaultSize(int defaultSize) {
            this.defaultSize = defaultSize;
            invalidate();
        }

        /**
         * Indexed getter for property sizes.
         *
         * @param index Index of the property.
         * @return Value of the property at <CODE>index</CODE>.
         */
        public int getSize(int index) {
            Integer size = sizes.get(index);
            return size != null ? size : getDefaultSize();
        }

        /**
         * Indexed setter for property sizes.
         *
         * @param index Index of the property.
         * @param sizes New value of the property at <CODE>index</CODE>.
         */
        public void setSize(int index, int size) {
            sizes.put(index, size);
            invalidate();
        }

        public void makeDefault(int index) {
            sizes.remove(index);
            invalidate();
        }

        public boolean isDefault(int index) {
            return !sizes.containsKey(index);
        }

        public void delete(int index) {
            Integer i = index;
            if (sizes.containsKey(i)) {
                sizes.remove(i);
            }
            for (Integer j : new TreeMap<>(sizes.tailMap(i)).keySet()) {
                sizes.put(j - 1, sizes.get(j));
                sizes.remove(j);
            }
            invalidate();
        }

        public void insert(int index) {
            Integer i = index;
            SortedMap<Integer, Integer> map = new TreeMap<>();
            for (Integer j : new TreeMap<>(sizes.tailMap(i)).keySet()) {
                map.put(j + 1, sizes.get(j));
                sizes.remove(j);
            }
            sizes.putAll(map);
            invalidate();
        }

        @Override
        public void calculateOffsets(int start, int length) {
            offsets.clear();
            offsets.add(start);
            for (int availableLength = length, i = 0; availableLength > 0; i++) {
                int size = getSize(i);
                if (size < availableLength) { //?? right edge is not needed (?)
                    offsets.add(offsets.get(i) + size);
                }
                availableLength -= size;
            }
        }

        public int getOffset(int index) {
            int offset = -1;
            if (index < offsets.size()) {
                offset = offsets.get(index);
            }
            return offset;
        }

//    public int preferredLayoutSize(int occupiedSize) {//??
//      int size = 0;
//      for (int i=0; i<occupiedSize; i++){
//        size += getSize(i);
//      }
//      return size;
//    }
        public int location(int index) {
            BigInteger location;
            if (index < offsets.size()) {
                location = BigInteger.valueOf(getOffset(index));
            } else {
                location = BigInteger.valueOf(getOffset(offsets.size() - 1));
                for (int i = offsets.size(); i <= index; i++) {
                    location = location.add(BigInteger.valueOf(getSize(i)));
                }
            }
            return location.min(BigIntegers.MAX_INTEGER).intValue();
        }

        public int offsetIndex(int pixel) {
            int index = offsets.size(); // out of offsets
            for (int i = 0; i < offsets.size(); i++) {
                if (pixel <= getOffset(i) + getSize(i) / 2) {
                    index = i;
                    break;
                }
            }
            return index;
        }

        public int index(int pixel) {
            int index;
            if (pixel < getOffset(offsets.size() - 1) + getSize(offsets.size()
                    - 1) / 2) {
                index = offsetIndex(pixel);
            } else {
                int i = offsets.size();
                for (int offset = getOffset(offsets.size() - 1) + getSize(offsets.
                        size() - 1);
                        pixel > offset + getSize(i) / 2; i++) {
                    offset += getSize(i);
                }
                index = i;
            }
            return index;
        }

        public int offsetSpan(int fromIndex, int pixelSize) {
            int span = 1;
            for (int i = fromIndex; i < offsets.size(); i++) {
                if (pixelSize <= getOffset(i) + getSize(i) / 2 - getOffset(
                        fromIndex)) {
                    span = i - fromIndex;
                    break;
                }
            }
            if (span < 1) {
                span = 1;
            }
            return span;
        }

        public int span(int fromIndex, int pixelSize) {
            int lastIndex = offsets.size() - 1;
            BigInteger fromLocation = BigInteger.valueOf(location(fromIndex));
            int span = 0;
            BigInteger pSize = BigInteger.valueOf(pixelSize);
            for (int i = fromIndex; pSize.compareTo(BigInteger.valueOf(location(
                    i)).add(BigInteger.valueOf(getSize(i) / 2)).subtract(
                    fromLocation)) > 0; i++) {
                span++;
            }
            if (span < 1) {
                span = 1;
            }
            return span;
        }

        public int size(int fromIndex, int span) {
            BigInteger size = BigInteger.ZERO;
            int toIndex = span + fromIndex;
            for (int i = fromIndex; i < toIndex; i++) {
                size = size.add(BigInteger.valueOf(getSize(i)));
            }
            return size.min(BigIntegers.MAX_INTEGER).intValue();
        }

        @Override
        protected void calculateSizes(int innerSize) {
        }

        public void draw(int startPixel, int pixelSize, Graphics g) {
            int endPixel = startPixel + pixelSize - 1;
            for (int i = 0; i < offsets.size(); i++) {
                int offset = getOffset(i);
                drawLine(startPixel, endPixel, offset, g);
            }
        }

        protected abstract void drawLine(int startPixel, int endPixel,
                int offset, Graphics g);
    }

    private class CompHeightsComparator implements Comparator<Component> {

        @Override
        public int compare(Component comp1, Component comp2) {
            Integer bottomPosition1 = compHeights.containsKey(comp1) ? compHeights.
                    get(comp1) : getBottomPosition(comp1);
            Integer bottomPosition2 = compHeights.containsKey(comp2) ? compHeights.
                    get(comp2) : getBottomPosition(comp2);
            return bottomPosition1.compareTo(bottomPosition2);
        }
    }

    private class CompWidthsComparator implements Comparator<Component> {

        @Override
        public int compare(Component comp1, Component comp2) {
            Integer rightPosition1 = compWidths.containsKey(comp1) ? compWidths.
                    get(comp1) : getRightPosition(comp1);
            Integer rightPosition2 = compWidths.containsKey(comp2) ? compWidths.
                    get(comp2) : getRightPosition(comp2);
            return rightPosition1.compareTo(rightPosition2);
        }
    }
}
