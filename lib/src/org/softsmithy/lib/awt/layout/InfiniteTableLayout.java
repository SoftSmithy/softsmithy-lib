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
 * UnlimitedTableLayout.java
 *
 * Created on 22. August 2002, 18:12
 */

package org.softsmithy.lib.awt.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.*;
import org.softsmithy.lib.awt.*;
import org.softsmithy.lib.swing.customizer.*;

/**
 *
 * @author  puce
 */
public class InfiniteTableLayout extends AbstractTableLayout {
  
  /** Holds value of property constraints. */
  private Map constraints = new HashMap();
  
  private final InfiniteAxis columns;
  
  private final InfiniteAxis rows;
  
  private Dimension occupiedDimension = new Dimension(0,0);
  
  private final Container parent;
  
  private final Map compHeights = new HashMap();
  private final SortedSet sortedCompHeights = new TreeSet(new CompHeightsComparator());
  private final Map compWidths = new HashMap();
  private final SortedSet sortedCompWidths = new TreeSet(new CompWidthsComparator());
 
  
  /** Creates a new instance of UnlimitedTableLayout */
  public InfiniteTableLayout(int defaultColumnWidth, int defaultRowHeight, Container parent) {
    columns = new InfiniteAxis(defaultColumnWidth){
      public void drawLine(int startPixel, int endPixel, int offset, Graphics g){
        g.drawLine(offset, startPixel, offset, endPixel);
      }
    };
    rows = new InfiniteAxis(defaultRowHeight){
      public void drawLine(int startPixel, int endPixel, int offset, Graphics g){
        g.drawLine(startPixel, offset, endPixel, offset);
      }
    };
    ensureValidity(parent);
    this.parent = parent;
  }
  
  public InfiniteTableLayout(Container parent) {
    this(10, 10, parent);
  }
  
  public Container getParent(){
    return this.parent;
  }
  
  public void setColumnWidth(int column, double width) {
    columns.setSize(column, (int) width);
  }
  
  public double getColumnWidth(int column){
    return columns.getSize(column);
  }
  
  public void makeColumnDefault(int column){
    columns.makeDefault(column);
  }
  
  public boolean isDefaultColumn(int column){
    return columns.isDefault(column);
  }
  
  public void setRowHeight(int row, double height) {
    rows.setSize(row, (int) height);
  }
  
  public double getRowHeight(int row){
    return rows.getSize(row);
  }
  
  public void makeRowDefault(int row){
    rows.makeDefault(row);
  }
  
  public boolean isDefaultRow(int row){
    return rows.isDefault(row);
  }
  /** Getter for property defaultColumnWidth.
   * @return Value of property defaultColumnWidth.
   */
  public int getDefaultColumnWidth() {
    return columns.getDefaultSize();
  }
  
  /** Setter for property defaultColumnWidth.
   * @param defaultColumnWidth New value of property defaultColumnWidth.
   */
  public void setDefaultColumnWidth(int defaultColumnWidth, boolean absolute) {
    Map bounds = getBounds(absolute);
    columns.setDefaultSize(defaultColumnWidth);
    setBounds(bounds, absolute);
    
  }
  
  /** Getter for property defaultRowHeight.
   * @return Value of property defaultRowHeight.
   */
  public int getDefaultRowHeight() {
    return rows.getDefaultSize();
  }
  
  /** Setter for property defaultRowHeight.
   * @param defaultRowHeight New value of property defaultRowHeight.
   */
  public void setDefaultRowHeight(int defaultRowHeight, boolean absolute) {
    Map bounds = getBounds(absolute);
    rows.setDefaultSize(defaultRowHeight);
    setBounds(bounds, absolute);
  }
  
  /** Getter for property constraints.
   * @return Value of property constraints.
   */
  public TableConstraints getTableConstraints(Component comp) {
    return (TableConstraints) constraints.get(comp);
  }
  
  public CustomizerConstraints getCustomizerConstraints(Component comp){
    return getTableConstraints(comp);
  }
  
  /** Setter for property constraints.
   * @param constraints New value of property constraints.
   */
  public void setTableConstraints(Component comp, TableConstraints tc){
    //    if (!(constr instanceof TableConstraints)){
    //      throw new IllegalArgumentException();
    //    }
    //    TableConstraints tc = (TableConstraints) constr;
    constraints.put(comp, tc);
    int preferredWidth =  getRightPosition(comp);//??
    if (sortedCompWidths.contains(comp)){
      sortedCompWidths.remove(comp);
    }
    compWidths.put(comp, new Integer(preferredWidth));
    sortedCompWidths.add(comp);
    int preferredHeight =  getBottomPosition(comp);//??
    if (sortedCompHeights.contains(comp)){
      sortedCompHeights.remove(comp);
    }
    compHeights.put(comp, new Integer(preferredHeight));
    sortedCompHeights.add(comp);
    resetOccupiedDimension();
  }
  
  public void setCustomizerConstraints(Component comp, CustomizerConstraints cc){
    if (! (cc instanceof TableConstraints)){
      throw new IllegalArgumentException("CustomizerConstraints must be an instance of TableConstraints!");
    }
    setTableConstraints(comp, (TableConstraints) cc);
  }
  
  private int getRightPosition(Component comp){
    TableConstraints tc = (TableConstraints) constraints.get(comp);
    return tc.getX() + tc.getWidth();
  }
  
  private int getBottomPosition(Component comp){
    TableConstraints tc = (TableConstraints) constraints.get(comp);
    return tc.getY() + tc.getHeight();
  }
  
  private void resetOccupiedDimension(){
    occupiedDimension.width = sortedCompWidths.isEmpty() ? 0 :
      ((Integer) compWidths.get(sortedCompWidths.last())).intValue();
      occupiedDimension.height = sortedCompHeights.isEmpty() ? 0 :
        ((Integer) compHeights.get(sortedCompHeights.last())).intValue();
  }
  
  public void deleteColumn(int i, boolean absolute) {
    Map bounds = getBounds(absolute);
    columns.delete(i);
    setBounds(bounds, absolute);
  }
  
  public void deleteRow(int i, boolean absolute) {
    Map bounds = getBounds(absolute);
    rows.delete(i);
    setBounds(bounds, absolute);
  }
  
  public void drawGrid(Container container, Graphics g) {
    Rectangle innerArea = AWTUtilities.calculateInnerArea(container, null);
    ensureValidity(innerArea);
    columns.draw(innerArea.y, innerArea.height, g);
    rows.draw(innerArea.x, innerArea.width, g);
  }
  
  public void drawLayoutHelp(Container container, Graphics g) {
    drawGrid(container, g);
  }
  
  public void insertColumn(int i, boolean absolute) {
    Map bounds = getBounds(absolute);
    columns.insert(i);
    setBounds(bounds, absolute);
    
  }
  
  public void insertRow(int i, boolean absolute) {
    Map bounds = getBounds(absolute);
    rows.insert(i);
    setBounds(bounds, absolute);
  }
  
  private Map getBounds(boolean absolute){
    Map bounds;
    if (absolute){
      bounds = getAbsoluteBounds();
    } else {
      bounds = getRelativeBounds();
    }
    return bounds;
  }
  
  private void setBounds(Map bounds, boolean absolute){
    if (absolute){
      setAbsoluteBounds(bounds);
    } else {
      setRelativeBounds(bounds);
    }
  }
  
  private Map getAbsoluteBounds(){
    Map bounds = new HashMap();
    Component[] components = getComponents(parent);
    for (int i=0; i < components.length; i++){
      bounds.put(components[i], getTableConstraints(components[i]).
      getAbsoluteBounds());
    }
    return bounds;
  }
  
  private Map getRelativeBounds(){
    Map bounds = new HashMap();
    Component[] components = getComponents(parent);
    for (int i=0; i < components.length; i++){
      bounds.put(components[i], getTableConstraints(components[i]).
      getRelativeBounds());
    }
    return bounds;
  }
  
  private void setAbsoluteBounds(Map bounds){
    Component[] components = getComponents(parent);
    for (int i=0; i < components.length; i++){
      setAbsoluteBounds(components[i], (Rectangle) bounds.get(components[i]));
      //setTableConstraints(components[i], constraints);
    }
  }
  
  private void setRelativeBounds(Map bounds){
    Component[] components = getComponents(parent);
    for (int i=0; i < components.length; i++){
      setRelativeBounds(components[i], (Rectangle) bounds.get(components[i]));
      //setTableConstraints(components[i], constraints);
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
   * Calculates the minimum size dimensions for the specified
   * container, given the components it contains.
   * @param parent the component to be laid out
   * @see #preferredLayoutSize
   */
  public Dimension minimumLayoutSize(Container parent) {
    return new Dimension(0,0); //???
  }
  
  /** Calculates the preferred size dimensions for the specified
   * container, given the components it contains.
   * @param parent the container to be laid out
   *
   * @see #minimumLayoutSize
   */
  public Dimension preferredLayoutSize(Container parent) { //??
    //    return new Dimension(columns.preferredLayoutSize(occupiedDimension.width),
    //    rows.preferredLayoutSize(occupiedDimension.height));
    return new Dimension(occupiedDimension);
  }
  
  /** Removes the specified component from the layout.
   * @param comp the component to be removed
   */
  public void removeLayoutComponent(Component comp) {
    constraints.remove(comp);
    sortedCompWidths.remove(comp);
    compWidths.remove(comp);
    sortedCompHeights.remove(comp);
    compHeights.remove(comp);
    resetOccupiedDimension();
  }
  
  
  public int columnIndex(int pixel) {
    return columns.offsetIndex(pixel);
  }
  
  public int rowIndex(int pixel) {
    return rows.offsetIndex(pixel);
  }
  
  public int colSpan(int fromIndex, int pixelWidth) {
    return columns.offsetSpan(fromIndex, pixelWidth);
  }
  
  public int rowSpan(int fromIndex, int pixelHeight) {
    return rows.offsetSpan(fromIndex, pixelHeight);
  }
  
  public int width(int fromIndex, int colSpan) {
    return columns.size(fromIndex, colSpan);
  }
  
  public int height(int fromIndex, int rowSpan) {
    return rows.size(fromIndex, rowSpan);
  }
  
  public int yLocation(int index) {
    return rows.location(index);
  }
  
  public int xLocation(int index) {
    return columns.location(index);
  }
  
  public int adjustX(int pixel) {
    ensureValidity(parent);
    return columns.location(columns.offsetIndex(pixel));
  }
  
  public int adjustY(int pixel) {
    ensureValidity(parent);
    return rows.location(rows.offsetIndex(pixel));
  }
  
  public int adjustWidth(int xPixel, int pixelWidth) {
    ensureValidity(parent);
    int xIndex = columns.index(xPixel);
    return columns.size(xIndex, columns.span(xIndex, pixelWidth));
  }
  
  public int adjustHeight(int yPixel, int pixelHeight) {
    ensureValidity(parent);
    int yIndex = rows.index(yPixel);
    return rows.size(yIndex, rows.span(yIndex, pixelHeight));
  }
  
  public Rectangle adjustBounds(Rectangle bounds){
    ensureValidity(parent);
    int xIndex = columns.index(bounds.x);
    int yIndex = rows.index(bounds.y);
    bounds.x = columns.location(xIndex);
    bounds.y = rows.location(yIndex);
    bounds.width = columns.size(xIndex, columns.span(xIndex, bounds.width));
    bounds.height = rows.size(yIndex, rows.span(yIndex, bounds.height));
    return bounds;
  }
  
  /** Indicates whether or not the size of the cells are known for the last known
   * size of the container.  If valid is false or the container has been resized,
   * the cell sizes must be recalculated using calculateSize.
   *
   */
  protected AbstractAxis getColumns() {
    return columns;
  }
  
  protected AbstractAxis getRows() {
    return rows;
  }
  
  public void setAbsoluteBounds(Component component, Rectangle bounds) {
    TableConstraints tc = getTableConstraints(component);
    if (tc == null){
      //      tc = new AbsoluteTableConstraints(bounds, component, this);
      //      getParent().add(component, tc);
    } else {
      tc.setAbsoluteBounds(bounds);
      setTableConstraints(component, tc);
      layoutComponent(getParent(), component);
    }
  }
  
  public void setRelativeBounds(Component component, Rectangle bounds) {
    TableConstraints tc = getTableConstraints(component);
    if (tc == null){
      //      tc = new RelativeTableConstraints(bounds, component, this);
      //      getParent().add(component, tc);
    } else {
      tc.setRelativeBounds(bounds);
      setTableConstraints(component, tc);
      layoutComponent(getParent(), component);
    }
  }
  

  
  private static abstract class InfiniteAxis extends AbstractTableLayout.AbstractAxis{
    
    /** Holds value of property defaultSize. */
    private int defaultSize;
    
    /** Holds value of property sizes. */
    private SortedMap sizes = new TreeMap();
    
    private List offsets = new ArrayList();
    
    public InfiniteAxis(int defaultSize) {
      this.defaultSize = defaultSize;
    }
    
    /** Getter for property defaultSize.
     * @return Value of property defaultSize.
     */
    public int getDefaultSize() {
      return this.defaultSize;
    }
    
    /** Setter for property defaultSize.
     * @param defaultSize New value of property defaultSize.
     */
    public void setDefaultSize(int defaultSize) {
      this.defaultSize = defaultSize;
      invalidate();
    }
    
    /** Indexed getter for property sizes.
     * @param index Index of the property.
     * @return Value of the property at <CODE>index</CODE>.
     */
    public int getSize(int index) {
      Object obj = sizes.get(new Integer(index));
      return obj != null ? ((Integer) obj).intValue() : getDefaultSize();
    }
    
    /** Indexed setter for property sizes.
     * @param index Index of the property.
     * @param sizes New value of the property at <CODE>index</CODE>.
     */
    public void setSize(int index, int size) {
      sizes.put(new Integer(index), new Integer(size));
      invalidate();
    }
    
    public void makeDefault(int index){
      sizes.remove(new Integer(index));
      invalidate();
    }
    
    public boolean isDefault(int index){
      return ! sizes.containsKey(new Integer(index));
    }
    
    public void delete(int index){
      Integer i = new Integer(index);
      if (sizes.containsKey(i)){
        sizes.remove(i);
      }
      for (Iterator it= new TreeMap(sizes.tailMap(i)).keySet().iterator(); it.hasNext();){
        Integer j = (Integer) it.next();
        sizes.put(new Integer(j.intValue()-1), sizes.get(j));
        sizes.remove(j);
      }
      invalidate();
    }
    
    public void insert(int index){
      Integer i = new Integer(index);
      SortedMap map = new TreeMap();
      for (Iterator it= new TreeMap(sizes.tailMap(i)).keySet().iterator(); it.hasNext();){
        Integer j = (Integer) it.next();
        map.put(new Integer(j.intValue()+1), sizes.get(j));
        sizes.remove(j);
      }
      sizes.putAll(map);
      invalidate();
    }
    
    public void calculateOffsets(int start, int length) {
      offsets.clear();
      offsets.add(new Integer(start));
      for (int availableLength = length, i=0; availableLength > 0; i++){
        int size = getSize(i);
        if (size < availableLength){ //?? right edge is not needed (?)
          offsets.add(new Integer(((Integer) offsets.get(i)).intValue() + size));
        }
        availableLength -= size;
      }
    }
    
    public int getOffset(int index) {
      int offset = -1;
      if (index < offsets.size()){
        offset = ((Integer) offsets.get(index)).intValue();
      }
      return offset;
    }
    
    public int preferredLayoutSize(int occupiedSize) {//??
      int size = 0;
      for (int i=0; i<occupiedSize; i++){
        size += getSize(i);
      }
      return size;
    }
    
    
    public int location(int index){
      int location;
      if (index < offsets.size()){
        location = getOffset(index);
      } else {
        location = getOffset(offsets.size()-1);
        for (int i=offsets.size(); i<= index; i++){
          location += getSize(i);
        }
      }
      return location;
    }
    
    public int offsetIndex(int pixel) {
      int index = offsets.size(); // out of offsets
      for (int i=0; i<offsets.size(); i++){
        if (pixel <= getOffset(i) + getSize(i)/2){
          index = i;
          break;
        }
      }
      return index;
    }
    
    public int index(int pixel){
      int index;
      if (pixel < getOffset(offsets.size()-1) + getSize(offsets.size()-1)/2){
        index = offsetIndex(pixel);
      } else {
        int i = offsets.size();
        for (int offset=getOffset(offsets.size()-1)+getSize(offsets.size()-1);
        pixel>offset+getSize(i)/2; i++){
          offset += getSize(i);
        }
        index = i;
      }
      return index;
    }
    
    public int offsetSpan(int fromIndex, int pixelSize){
      int span = 1;
      for (int i=fromIndex; i<offsets.size(); i++){
        if (pixelSize <= getOffset(i) + getSize(i)/2 - getOffset(fromIndex)){
          span = i - fromIndex;
          break;
        }
      }
      if (span < 1){
        span = 1;
      }
      return span;
    }
    
    public int span(int fromIndex, int pixelSize){
      int lastIndex = offsets.size()-1;
      int fromLocation = location(fromIndex);
      int span = 0;
      for (int i=fromIndex; pixelSize>location(i)+getSize(i)/2-fromLocation; i++){
        span++;
      }
      if (span < 1){
        span = 1;
      }
      return span;
    }
    
    public int size(int fromIndex, int span){
      int size=0;
      int toIndex = span+fromIndex;
      for (int i=fromIndex; i<toIndex; i++){
        size += getSize(i);
      }
      return size;
    }
    
    protected void calculateSizes(int innerSize) {
    }
    
    public void draw(int startPixel, int pixelSize, Graphics g){
      int endPixel = startPixel + pixelSize - 1;
      for (int i=0; i< offsets.size(); i++){
        int offset = getOffset(i);
        drawLine(startPixel, endPixel, offset, g);
      }
    }
    
    protected abstract void drawLine(int startPixel, int endPixel, int offset, Graphics g);
  }
  
  private class CompHeightsComparator implements Comparator{
    
    public int compare(Object o1, Object o2) {
      Component comp1 = (Component) o1;
      Component comp2 = (Component) o2;
      Integer bottomPosition1 = compHeights.containsKey(comp1) ? (Integer) compHeights.get(comp1) :
        new Integer(getBottomPosition(comp1));
        Integer bottomPosition2 = compHeights.containsKey(comp2) ? (Integer) compHeights.get(comp2) :
          new Integer(getBottomPosition(comp2));
          return bottomPosition1.compareTo(bottomPosition2);
    }
    
  }
  
  private class CompWidthsComparator implements Comparator{
    
    public int compare(Object o1, Object o2) {
      Component comp1 = (Component) o1;
      Component comp2 = (Component) o2;
      Integer rightPosition1 = compWidths.containsKey(comp1) ? (Integer) compWidths.get(comp1) :
        new Integer(getRightPosition(comp1));
        Integer rightPosition2 = compWidths.containsKey(comp2) ? (Integer) compWidths.get(comp2) :
          new Integer(getRightPosition(comp2));
          return rightPosition1.compareTo(rightPosition2);
    }
    
  }
  
}
