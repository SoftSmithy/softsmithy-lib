/*
 * UnlimitedTableLayout.java
 *
 * Created on 22. August 2002, 18:12
 */

package puce.swing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.awt.*;
import puce.awt.*;

/**
 *
 * @author  puce
 */
public class InfiniteTableLayout extends AbstractTableLayout {
  
  /** Holds value of property constraints. */
  private Map constraints = new HashMap();
  
  private final Axis columns;
  
  private final Axis rows;
  
  private Dimension occupiedDimension = new Dimension(0,0);
  
  /** Creates a new instance of UnlimitedTableLayout */
  public InfiniteTableLayout(int defaultColumnWidth, int defaultRowHeight) {
    columns = new Axis(defaultColumnWidth){
      public void drawLine(int startPixel, int endPixel, int offset, Graphics g){
        g.drawLine(offset, startPixel, offset, endPixel);
      }
    };
    rows = new Axis(defaultRowHeight){
      public void drawLine(int startPixel, int endPixel, int offset, Graphics g){
        g.drawLine(startPixel, offset, endPixel, offset);
      }
    };
  }
  
  public InfiniteTableLayout() {
    this(5, 5);
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
  public void setDefaultColumnWidth(int defaultColumnWidth) {
    columns.setDefaultSize(defaultColumnWidth);
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
  public void setDefaultRowHeight(int defaultRowHeight) {
    rows.setDefaultSize(defaultRowHeight);
  }
  
  protected void calculateColumnOffsets(int x, int width) {
    columns.calculateOffsets(x, width);
  }
  
  protected void calculateColumnSizes(int innerWidth) {
  }
  
  protected void calculateRowOffsets(int y, int height) {
    rows.calculateOffsets(y, height);
  }
  
  protected void calculateRowSizes(int innerHeight) {
  }
  
  /** If the layout manager uses a per-component string,
   * adds the component <code>comp</code> to the layout,
   * associating it
   * with the string specified by <code>name</code>.
   *
   * @param name the string to be associated with the component
   * @param comp the component to be added
   */
  public void addLayoutComponent(String name, Component comp) {
    throw new IllegalArgumentException();
  }
  
  /** Adds the specified component to the layout, using the specified
   * constraint object.
   * @param comp the component to be added
   * @param constraints  where/how the component is added to the layout.
   */
  public void addLayoutComponent(Component comp, Object constraints) {
    if (!(constraints instanceof TableConstraints)){
      throw new IllegalArgumentException();
    }
    TableConstraints constr = (TableConstraints) constraints;
    int preferredWidth = constr.getX() + constr.getWidth(); //??
    if (preferredWidth > occupiedDimension.width){
      occupiedDimension.width = preferredWidth;
    }
    int preferredHeight = constr.getY() + constr.getHeight(); //??
    if (preferredHeight > occupiedDimension.height){
      occupiedDimension.height = preferredHeight;
    }
    
    setConstraints(comp, (TableConstraints) constraints);
  }
  
  /** Getter for property constraints.
   * @return Value of property constraints.
   */
  public TableConstraints getConstraints(Component comp) {
    return (TableConstraints) constraints.get(comp);
  }
  
  /** Setter for property constraints.
   * @param constraints New value of property constraints.
   */
  public void setConstraints(Component comp, TableConstraints constr){
    constraints.put(comp, constr);
  }
  
  public void deleteColumn(int i) {
  }
  
  public void deleteRow(int i) {
  }
  
  public void drawGrid(Container container, Graphics g) {
    Rectangle innerArea = AWTUtilities.calculateInnerArea(container, null);
    columns.draw(innerArea.x, innerArea.width, g);
    rows.draw(innerArea.y, innerArea.height, g);
  }
  
  public void insertColumn(int i, double width) {
  }
  
  public void insertRow(int i, double height) {
  }
  
  /**
   * Lays out the specified container.
   * @param parent the container to be laid out
   */
  public void layoutContainer(Container parent) {
    ensureValidity(parent);
    for (Iterator i=constraints.keySet().iterator(); i.hasNext();){
      Component comp = (Component) i.next();
      TableConstraints constr = getConstraints(comp);
      int x = columns.getOffset(constr.getX());
      if (x >= 0){
        int y = rows.getOffset(constr.getY());
        if (y >= 0){
          int width = columns.getOffset(constr.getX() + constr.getWidth()) - x; //??
          if (width > 0){
            int height = rows.getOffset(constr.getY() + constr.getHeight()) - y;// ??
            if (height > 0){
              comp.setBounds(x, y, width, height);
            }
          }
        }
      }
    }
  }
  
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
    return new Dimension(columns.preferredLayoutSize(occupiedDimension.width),
    rows.preferredLayoutSize(occupiedDimension.height));
  }
  
  /** Removes the specified component from the layout.
   * @param comp the component to be removed
   */
  public void removeLayoutComponent(Component comp) {
    constraints.remove(comp);
  }
  
  public void setColumn(int i, double size) {
  }
  
  public void setRow(int i, double size) {
  }
  
  public int columnIndex(int pixel) {
    return columns.index(pixel);
  }  
  
  public int rowIndex(int pixel) {
    return rows.index(pixel);
  }  
  
  public int colSpan(int fromIndex, int pixelWidth) {
    return columns.span(fromIndex, pixelWidth);
  }
  
  public int rowSpan(int fromIndex, int pixelHeight) {
    return rows.span(fromIndex, pixelHeight);
  }
  
  public int width(int fromIndex, int colSpan) {
    return columns.size(fromIndex, colSpan);
  }
  
  public int height(int fromIndex, int rowSpan) {
    return rows.size(fromIndex, rowSpan);
  }
  
  private static abstract class Axis{
    
    /** Holds value of property defaultSize. */
    private int defaultSize;
    
    /** Holds value of property sizes. */
    private Map sizes = new TreeMap();
    
    private List offsets = new ArrayList();
    
    public Axis(int defaultSize) {
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
    }
    
    public void makeDefault(int index){
      sizes.remove(new Integer(index));
    }
    
    public boolean isDefault(int index){
      return ! sizes.containsKey(new Integer(index));
    }
    
    public void calculateOffsets(int start, int length) {
      offsets.add(new Integer(start));
      for (int availableLength = length, i=0; availableLength > 0; i++){
        int size = getSize(i);
        if (size < availableLength){ //?? right edge is not needed (?)
          offsets.add(new Integer(((Integer) offsets.get(i)).intValue() + size));
        }
        availableLength -= size;
      }
    }
    
    public int preferredLayoutSize(int occupiedSize) {//??
      int size = 0;
      for (int i=0; i<occupiedSize; i++){
        size += getSize(i);
      }
      return size;
    }
    
    public int getOffset(int index) {
      int offset = -1;
      if (index < offsets.size()){
        offset = ((Integer) offsets.get(index)).intValue();
      }
      return offset;
    }
    
    public int index(int pixel) {
      int index = offsets.size(); // out of offsets
      for (int i=0; i<offsets.size(); i++){
        if (pixel <= getOffset(i) + getSize(i)/2){
          index = i;
          break;
        }
      }
      return index;
    }
    
    public int span(int fromIndex, int pixelSize){
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
    
    public int size(int fromIndex, int span){
      int size=0;
      int toIndex = span+fromIndex;
       for (int i=fromIndex; i<toIndex; i++){
        size += getSize(i);
      }
      return size;
    }
    
    public void draw(int startPixel, int pixelSize, Graphics g){
      int endPixel = startPixel + pixelSize - 1;
      for (int i=0; i< offsets.size(); i++){
        int offset = getOffset(i);
        drawLine(startPixel, endPixel, offset, g);
      }
    }
    
    public abstract void drawLine(int startPixel, int endPixel, int offset, Graphics g);
  }
  
}
