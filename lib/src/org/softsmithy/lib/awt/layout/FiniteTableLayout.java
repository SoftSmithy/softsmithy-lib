package puce.awt.layout;

import java.awt.*;
import java.util.*;
import puce.swing.customizer.*;




/**
 * TableLayout is a layout manager that arranges components in rows and columns
 * like a spreadsheet.  TableLayout allows each row or column to be a different
 * size.  A row or column can be given an absolute size in pixels, a percentage
 * of the available space, or it can grow and shrink to fill the remaining space
 * after other rows and columns have been resized.
 *
 * <p>Using spreadsheet terminology, a cell is the intersection of a row and
 * column.  Cells have finite, non-negative sizes measured in pixels.  The
 * dimensions of a cell depend solely upon the dimensions of its row and column.
 * </p>
 *
 * <p>A component occupies a rectangular group of one or more cells.  If the
 * component occupies more than one cell, the component is resized to fit
 * perfectly in the rectangular region of cells.  If the component occupies a
 * single cell, it can be aligned in four ways within that cell.</p>
 *
 * <p>A single cell component can be stretched horizontally to fit the cell
 * (full justification), or it can be placed in the center of the cell.  The
 * component could also be left justified or right justified.  Similarly, the
 * component can be full, center, top, or bottom justified in the vertical.</p>
 *
 * <pre>
 * public static void main (String args[])
 * {
 *     // Create a frame
 *     Frame frame = new Frame("Example of TableLayout");
 *     frame.setBounds (100, 100, 300, 300);
 * <spc>
 *     // Create a TableLayout for the frame
 *     double border = 10;
 *     double size[][] =
 *         {{border, 0.10, 20, TableLayout.FILL, 20, 0.20, border},  // Columns
 *          {border, 0.20, 20, TableLayout.FILL, 20, 0.20, border}}; // Rows
 * <spc>
 *     frame.setLayout (new TableLayout(size));
 * <spc>
 *     // Create some buttons
 *     String label[] = {"Top", "Bottom", "Left", "Right", "Center", "Overlap"};
 *     Button button[] = new Button[label.length];
 * <spc>
 *     for (int i = 0; i < label.length; i++)
 *         button[i] = new Button(label[i]);
 * <spc>
 *     // Add buttons
 *     frame.add (button[0], "1, 1, 5, 1"); // Top
 *     frame.add (button[1], "1, 5, 5, 5"); // Bottom
 *     frame.add (button[2], "1, 3      "); // Left
 *     frame.add (button[3], "5, 3      "); // Right
 *     frame.add (button[4], "3, 3, c, c"); // Center
 *     frame.add (button[5], "3, 3, 3, 5"); // Overlap
 * <spc>
 *     // Allow user to close the window to terminate the program
 *     frame.addWindowListener
 *         (new WindowListener()
 *             {
 *                 public void windowClosing (WindowEvent e)
 *                 {
 *                     System.exit (0);
 *                 }
 * <spc>
 *                 public void windowOpened (WindowEvent e) {}
 *                 public void windowClosed (WindowEvent e) {}
 *                 public void windowIconified (WindowEvent e) {}
 *                 public void windowDeiconified (WindowEvent e) {}
 *                 public void windowActivated (WindowEvent e) {}
 *                 public void windowDeactivated (WindowEvent e) {}
 *             }
 *         );
 * <spc>
 *     // Show frame
 *     frame.show();
 * }
 * </pre>
 *
 * http://java.sun.com/products/jfc/tsc/articles/tablelayout/
 * @author  Daniel E. Barbalace
 */

public class FiniteTableLayout extends AbstractTableLayout{
  
  
  
  /** Default row/column size */
  protected static final double defaultSize[][] = {{}, {}};
  
  
  /** Offsets of columns in pixels.  The left boarder of column n is at
   * columnOffset[n] and the right boarder is at columnOffset[n + 1] for all
   * columns including the last one.  columnOffset.length = columnSize.length + 1 */
//  protected int columnOffset[];
  
  /** Offsets of rows in pixels.  The left boarder of row n is at
   * rowOffset[n] and the right boarder is at rowOffset[n + 1] for all
   * rows including the last one.  rowOffset.length = rowSize.length + 1 */
//  protected int rowOffset[];
  
  /** List of components and their sizes */
  protected Map constraints = new HashMap();
  private FiniteAxis columns;
  private FiniteAxis rows;
  
  
  
  //******************************************************************************
  //** Constructors                                                            ***
  //******************************************************************************
  
  
  
  /**
   * Constructs an instance of TableLayout.  This TableLayout will have one row
   * and one column.
   */
  
  public FiniteTableLayout() {
    this(defaultSize);
  }
  
  
  
  /**
   * Constructs an instance of TableLayout.
   *
   * @param size    widths of columns and heights of rows in the format,
   *                {{col0, col1, col2, ..., colN}, {row0, row1, row2, ..., rowM}}
   *                If this parameter is invalid, the TableLayout will have
   *                exactly one row and one column.
   */
  
  public FiniteTableLayout(double size[][]) {
    // Make sure rows and columns and nothing else is specified
    if ((size != null) && (size.length == 2)) {
      // Get the rows and columns
      double columnSpecs[] = size[0];
      double rowSpecs[] = size[1];
      setColumns(columnSpecs);
      setRows(rowSpecs);
    } else {
      setColumns({FILL});
      setRows({FILL});
    }
  }
  
  
  
  //******************************************************************************
  //** Get/Set methods                                                         ***
  //******************************************************************************
  
  
  
  /**
   * Gets the constraints of a given component.
   *
   * @param component    desired component
   *
   * @return If the given component is found, the constraints associated with
   *         that component.  If the given component is null or is not found,
   *         null is returned.
   */
  
  public CustomizerConstraints getConstraints(Component comp) {
    return (TableConstraints) constraints.get(comp);
  }
  
  /**
   * Sets the constraints of a given component.
   *
   * @param component     desired component.  This parameter cannot be null.
   * @param constraint    new set of constraints.  This parameter cannot be null.
   *
   * @return If the given component is found, the constraints associated with
   *         that component.  If the given component is null or is not found,
   *         null is returned.
   */
  
  public void setConstraints(Component component, CustomizerConstraints constr) {
    if (!(constr instanceof TableConstraints)){
      throw new IllegalArgumentException();
    }
    TableConstraints tc = (TableConstraints) constr;
    constraints.put(component, tc);
  }
  
  
  
  /**
   * Adjusts the number and sizes of rows in this layout.  After calling this
   * method, the caller should request this layout manager to perform the
   * layout.  This can be done with the following code:
   *
   * <pre>
   *     layout.layoutContainer(container);
   *     container.repaint();
   * </pre>
   *
   * or
   *
   * <pre>
   *     window.pack()
   * </pre>
   *
   * If this is not done, the changes in the layout will not be seen until the
   * container is resized.
   *
   * @param column    heights of each of the columns
   *
   * @see getColumn
   */
  
  public void setColumns(double colSpecs[]) {
    columns = new FiniteAxis(colSpecs);
  }
  
  
  
  /**
   * Adjusts the number and sizes of rows in this layout.  After calling this
   * method, the caller should request this layout manager to perform the
   * layout.  This can be done with the following code:
   *
   * <code>
   *     layout.layoutContainer(container);
   *     container.repaint();
   * </code>
   *
   * or
   *
   * <pre>
   *     window.pack()
   * </pre>
   *
   * If this is not done, the changes in the layout will not be seen until the
   * container is resized.
   *
   * @param row    widths of each of the rows.  This parameter cannot be null.
   *
   * @see getRow
   */
  
  public void setRows(double rowSpecs[]) {
    rows = new FiniteAxis(rowSpecs);
  }
  
  
  
  /**
   * Adjusts the width of a single column in this layout.  After calling this
   * method, the caller should request this layout manager to perform the
   * layout.  This can be done with the following code:
   *
   * <code>
   *     layout.layoutContainer(container);
   *     container.repaint();
   * </code>
   *
   * or
   *
   * <pre>
   *     window.pack()
   * </pre>
   *
   * If this is not done, the changes in the layout will not be seen until the
   * container is resized.
   *
   * @param i       zero-based index of column to set.  If this parameter is not
   *                valid, an ArrayOutOfBoundsException will be thrown.
   * @param size    width of the column.  This parameter cannot be null.
   *
   * @see getColumn
   */
  
  public void setColumn(int index, double colSpec) {
    columns.setUnit(index, colSpec);
  }
  
  
  
  /**
   * Adjusts the height of a single row in this layout.  After calling this
   * method, the caller should request this layout manager to perform the
   * layout.  This can be done with the following code:
   *
   * <code>
   *     layout.layoutContainer(container);
   *     container.repaint();
   * </code>
   *
   * or
   *
   * <pre>
   *     window.pack()
   * </pre>
   *
   * If this is not done, the changes in the layout will not be seen until the
   * container is resized.
   *
   * @param i       zero-based index of row to set.  If this parameter is not
   *                valid, an ArrayOutOfBoundsException will be thrown.
   * @param size    height of the row.  This parameter cannot be null.
   *
   * @see getRow
   */
  
  public void setRow(int index, double rowSpec) {
    rows.setUnit(index, rowSpec);
  }
  
  
  
  /**
   * Gets the width of a single column in this layout.
   *
   * @param i    zero-based index of row to get.  If this parameter is not valid,
   *             an ArrayOutOfBoundsException will be thrown.
   *
   * @return width of the requested column
   *
   * @see setRow
   */
  
//  public double getColumn(int i) {
//    return columnSpec[i];
//  }
  
  
  
  /**
   * Gets the sizes of a row in this layout.
   *
   * @param i    zero-based index of row to get.  If this parameter is not valid,
   *             an ArrayOutOfBoundsException will be thrown.
   *
   * @return height of each of the requested row
   *
   * @see setRow
   */
  
//  public double getRow(int i) {
//    return rowSpec[i];
//  }
  
  
  
  
  
  /**
   * Gets the number of rows in this layout.
   *
   * @return the number of rows
   */
  
//  public int getNumRow() {
//    return rowSpec.length;
//  }
  
  
  
  //******************************************************************************
  //** Insertion/Deletion methods                                              ***
  //******************************************************************************
  
  
  
  /**
   * Inserts a column in this layout.  All components to the right of the
   * insertion point are moved right one column.  The container will need to
   * be laid out after this method returns.  See <code>setColumn</code>.
   *
   * @param i       zero-based index at which to insert the column.
   * @param size    size of the column to be inserted
   *
   * @see setColumn
   * @see deleteColumn
   */
  
  public void insertColumn(int index, double colSpec) {
    insertionConstraintColumnsUpdate(index);
    columns.insertUnit(index, colSpec);
  }
  
  private void insertionConstraintColumnsUpdate(int index){
    // Move all components that are to the right of new row
    for (Iterator i=constraints.keySet().iterator(); i.hasNext();){
      Component comp = (Component) i.next();
      TableConstraints constr = (TableConstraints) constraints.get(comp);
      Rectangle bounds = constr.getRelativeBounds(comp, this);
      if (bounds.x >= index){
        bounds.x++;
      } else if (bounds.x + bounds.width - 1>= index){
        bounds.width++;
      }
      constr.setRelativeBounds(bounds, this);
    }
  }
  
  /**
   * Inserts a row in this layout.  All components below the insertion point
   * are moved down one row.  The container will need to be laid out after this
   * method returns.  See <code>setRow</code>.
   *
   * @param i       zero-based index at which to insert the column.
   * @param size    size of the row to be inserted
   *
   * @see setRow
   * @see deleteRow
   */
  
  public void insertRow(int index, double rowSpec) {
    insertionConstraintRowsUpdate(index);
    rows.insertUnit(index, rowSpec);
  }
  
    private void insertionConstraintRowsUpdate(int index){
    // Move all components that are to the right of new row
    for (Iterator i=constraints.keySet().iterator(); i.hasNext();){
      Component comp = (Component) i.next();
      TableConstraints constr = (TableConstraints) constraints.get(comp);
      Rectangle bounds = constr.getRelativeBounds(comp, this);
      if (bounds.y >= index){
        bounds.y++;
      } else if (bounds.y + bounds.height - 1>= index){
        bounds.height++;
      }
      constr.setRelativeBounds(bounds, this);
    }
  }
  
  /**
   * Deletes a column in this layout.  All components to the right of the
   * deletion point are moved left one column.  The container will need to
   * be laid out after this method returns.  See <code>setColumn</code>.
   *
   * @param i    zero-based index of column to delete
   *
   * @see setColumn
   * @see deleteColumn
   */
  
  public void deleteColumn(int index) {
    deletionConstraintColumnsUpdate(index);
    columns.deleteUnit(index);
  }
  
  private void deletionConstraintColumnsUpdate(int index){
    // Move all components that are to the right of new row
    for (Iterator i=constraints.keySet().iterator(); i.hasNext();){
      Component comp = (Component) i.next();
      TableConstraints constr = (TableConstraints) constraints.get(comp);
      Rectangle bounds = constr.getRelativeBounds(comp, this);
      if (bounds.x >= index){
        bounds.x--;
      } else if (bounds.x + bounds.width - 1>= index){
        bounds.width--;
      }
      constr.setRelativeBounds(bounds, this);
    }
  }
  
  
  
  /**
   * Deletes a row in this layout.  All components below the deletion point are
   * moved up one row.  The container will need to be laid out after this method
   * returns.  See <code>setRow</code>.  There must be at least two rows in order
   * to delete a row.
   *
   * @param i    zero-based index of column to delete
   *
   * @see setRow
   * @see deleteRow
   */
  
  public void deleteRow(int index) {
    deletionConstraintRowsUpdate(index);
    rows.deleteUnit(index);
  }
  
  private void deletionConstraintRowsUpdate(int index){
    // Move all components that are to the right of new row
    for (Iterator i=constraints.keySet().iterator(); i.hasNext();){
      Component comp = (Component) i.next();
      TableConstraints constr = (TableConstraints) constraints.get(comp);
      Rectangle bounds = constr.getRelativeBounds(comp, this);
      if (bounds.y >= index){
        bounds.y--;
      } else if (bounds.y + bounds.height - 1>= index){
        bounds.height--;
      }
      constr.setRelativeBounds(bounds, this);
    }
  }
  
  //******************************************************************************
  //** Misc methods                                                            ***
  //******************************************************************************
  
  
  
  /**
   * Converts this TableLayout to a string.
   *
   * @return a string representing the columns and row sizes in the form
   *         "{{col0, col1, col2, ..., colN}, {row0, row1, row2, ..., rowM}}"
   */
  
//  public String toString() {
//    int counter;
//    
//    String value = "TableLayout {{";
//    
//    if (columnSpec.length > 0) {
//      for (counter = 0; counter < columnSpec.length - 1; counter++)
//        value += columnSpec[counter] + ", ";
//      
//      value += columnSpec[columnSpec.length - 1] + "}, {";
//    }
//    else
//      value += "}, {";
//    
//    if (rowSpec.length > 0) {
//      for (counter = 0; counter < rowSpec.length - 1; counter++)
//        value += rowSpec[counter] + ", ";
//      
//      value += rowSpec[rowSpec.length - 1] + "}}";
//    }
//    else
//      value += "}}";
//    
//    return value;
//  }
  
  
  
  /**
   * Draws a grid on the given container.  This is useful for seeing where the
   * rows and columns go.  In the container's paint method, call this method.
   *
   * @param container    container using this TableLayout
   * @param g            graphics content of container (can be offscreen)
   */
  
  public void drawGrid(Container container, Graphics g) {
    int counter; // Counting variable;
    
    
    ensureValidity(container);
    
    // Initialize y
    int y = 0;
    
    for (int row = 0; row < rowSize.length; row++) {
      // Initialize x
      int x = 0;
      
      for (int column = 0; column < columnSize.length; column++) {
        // Use a random color to make things easy to see
        Color color = new Color((int) (Math.random() * 0xFFFFFFL));
        g.setColor(color);
        
        // Draw the cell as a solid rectangle
        g.fillRect(x, y, columnSize[column], rowSize[row]);
        
        // Increment x
        x += columnSize[column];
      }
      
      // Increment y
      y += rowSize[row];
    }
  }
  
  
  
  /**
   * Determines whether or not there are any hidden components.  A hidden
   * component is one that will not be shown with this layout's current
   * configuration.  Such a component is, at least partly, in an invalid row
   * or column.  For example, on a table with five rows, row -1 and row 5 are both
   * invalid.  Valid rows are 0 through 4, inclusively.
   *
   * @return    True, if there are any hidden components.  False, otherwise.
   *
   * @see overlapping
   */
  
  public boolean hidden() {
    // Assume no components are hidden
    boolean hidden = false;
    
    // Check all components
    ListIterator iterator = list.listIterator(0);
    
    while (iterator.hasNext()) {
      // Get next entry
      Entry entry = (Entry) iterator.next();
      
      // Is this component valid
      if ((entry.getRow1() < 0) || (entry.getCol1() < 0) ||
      (entry.getRow2() > rowSpec.length) ||
      (entry.getCol2() > columnSpec.length)) {
        hidden = true;
        break;
      }
    }
    
    return hidden;
  }
  
  
  
  /**
   * Determines whether or not there are any overlapping components.  Two
   * components overlap if they cover at least one common cell.
   *
   * @return    True, if there are any overlapping components.  False, otherwise.
   *
   * @see hidden
   */
  
  public boolean overlapping() {
    // Count contraints
    int numEntry = list.size();
    
    // If there are no components, they can't be overlapping
    if (numEntry == 0)
      return false;
    
    // Assume no components are overlapping
    boolean overlapping = false;
    
    // Put entries in an array
    Entry entry[] = (Entry[]) list.toArray(new Entry[numEntry]);
    
    // Check all components
    for (int knowUnique = 1; knowUnique < numEntry; knowUnique++)
      for (int checking = knowUnique - 1; checking >= 0; checking--)
        if
        (
        (
        (entry[checking].getCol1() >= entry[knowUnique].getCol1()) &&
        (entry[checking].getCol1() <= entry[knowUnique].getCol2()) &&
        (entry[checking].getRow1() >= entry[knowUnique].getRow1()) &&
        (entry[checking].getRow1() <= entry[knowUnique].getRow2())
        )
        ||
        (
        (entry[checking].getCol2() >= entry[knowUnique].getCol1()) &&
        (entry[checking].getCol2() <= entry[knowUnique].getCol2()) &&
        (entry[checking].getRow2() >= entry[knowUnique].getRow1()) &&
        (entry[checking].getRow2() <= entry[knowUnique].getRow2())
        )
        ) {
          overlapping = true;
          break;
        }
    
    return overlapping;
  }
  
  
  
  
  
  
  private void calculatePreferredRowSizes() {
    // Assign preferred and minimum heights; this reduces available height.
    // Assignment of preferred/minimum with is like assignment of absolute
    // heights except that each row must determine the maximum
    // preferred/minimum height of the components that are completely contained
    // within the row.
    for (int counter = 0; counter < rowSpec.length; counter++){
      // Is the current row a preferred size
      if (rowHasPreferredSize(counter)){
        calculatePreferredRowSize(counter);
      }
    }
  }
  
  
  
  private void calculatePreferredColumnSizes() {
    // Assign preferred and minimum widths; this reduces available width.
    // Assignment of preferred/minimum with is like assignment of absolute
    // widths except that each column must determine the maximum
    // preferred/minimum width of the components that are completely contained
    // within the column.
    for (int counter = 0; counter < columnSpec.length; counter++){
      // Is the current column a preferred size
      if (columnHasPreferredSize(counter)) {
        calculatePreferredColumnSize(counter);
      }
    }
  }
  
  
  
  
  
  private void calculatePreferredRowSize(final int row) {
    // Assume a maximum height of zero
    int maxHeight = 0;
    
    // Find maximum preferred height of all components completely
    // contained within this row
    ListIterator iterator = list.listIterator(0);
    
    while (iterator.hasNext()) {
      Entry entry = (Entry) iterator.next();
      
      if ((entry.getRow1() == row) && (entry.getRow2() == row)) {
        Dimension p = (rowSpec[row] == PREFERRED) ?
        entry.component.getPreferredSize() :
          entry.component.getMinimumSize();
          
          int height = (p == null) ? 0 : p.height;
          
          if (maxHeight < height)
            maxHeight = height;
      }
    }
    
    // Assign preferred height
    rowSize[row] = maxHeight;
  }
  
  private void calculatePreferredColumnSize(final int column) {
    // Assume a maximum width of zero
    int maxWidth = 0;
    
    // Find maximum preferred width of all components completely
    // contained within this column
    ListIterator iterator = list.listIterator(0);
    
    while (iterator.hasNext()) {
      Entry entry = (Entry) iterator.next();
      
      if ((entry.getCol1() == column) && (entry.getCol2() == column)) {
        Dimension p = (columnSpec[column] == PREFERRED) ?
        entry.component.getPreferredSize() :
          entry.component.getMinimumSize();
          
          int width = (p == null) ? 0 : p.width;
          
          if (maxWidth < width)
            maxWidth = width;
      }
    }
    
    // Assign preferred width
    columnSize[column] = maxWidth;
  }
  
  
  
  //******************************************************************************
  //** java.awt.event.LayoutManager methods                                    ***
  //******************************************************************************
  
  
  /**
   * Determines the preferred size of the container argument using this layout.
   * The preferred size is the smallest size that, if used for the container's
   * size, will ensure that all components are at least as large as their
   * preferred size.  This method cannot guarantee that all components will be
   * their preferred size.  For example, if component A and component B are each
   * allocate half of the container's width and component A wants to be 10 pixels
   * wide while component B wants to be 100 pixels wide, they cannot both be
   * accommodated.  Since in general components rather be larger than their
   * preferred size instead of smaller, component B's request will be fulfilled.
   * The preferred size of the container would be 200 pixels.
   *
   * @param container    container being served by this layout manager
   *
   * @return a dimension indicating the container's preferred size
   */
  
  public Dimension preferredLayoutSize(Container container) {
    Dimension size;       // Preferred size of current component
    int scaledWidth = 0;  // Preferred width of scalled components
    int scaledHeight = 0; // Preferred height of scalled components
    int temp;             // Temporary variable used to compare sizes
    int counter;          // Counting variable
    
    // Determine percentage of space allocated to fill components.  This is
    // one minus the sum of all scalable components.
    double fillWidthRatio = 1.0;
    double fillHeightRatio = 1.0;
    int    numFillWidth = 0;
    int    numFillHeight = 0;
    
    for (counter = 0; counter < columnSpec.length; counter++)
      if ((columnSpec[counter] > 0.0) && (columnSpec[counter] < 1.0))
        fillWidthRatio -= columnSpec[counter];
      else if (columnSpec[counter] == FILL)
        numFillWidth++;
    
    for (counter = 0; counter < rowSpec.length; counter++)
      if ((rowSpec[counter] > 0.0) && (rowSpec[counter] < 1.0))
        fillHeightRatio -= rowSpec[counter];
      else if (rowSpec[counter] == FILL)
        numFillHeight++;
    
    // Adjust fill ratios to reflect number of fill rows/columns
    if (numFillWidth > 1)
      fillWidthRatio /= numFillWidth;
    
    if (numFillHeight > 1)
      fillHeightRatio /= numFillHeight;
    
    // Cap fill ratio bottoms to 0.0
    if (fillWidthRatio < 0.0)
      fillWidthRatio = 0.0;
    
    if (fillHeightRatio < 0.0)
      fillHeightRatio = 0.0;
    
    // Calculate preferred/minimum column widths
    int columnPrefMin[] = new int[columnSpec.length];
    
    for (counter = 0; counter < columnSpec.length; counter++)
      // Is the current column a preferred/minimum size
      if ((columnSpec[counter] == PREFERRED) ||
      (columnSpec[counter] == MINIMUM)) {
        // Assume a maximum width of zero
        int maxWidth = 0;
        
        // Find maximum preferred/minimum width of all components completely
        // contained within this column
        ListIterator iterator = list.listIterator(0);
        
        while (iterator.hasNext()) {
          Entry entry = (Entry) iterator.next();
          
          if ((entry.getCol1() == counter) && (entry.getCol2() == counter)) {
            Dimension p = (columnSpec[counter] == PREFERRED) ?
            entry.component.getPreferredSize() :
              entry.component.getMinimumSize();
              
              int width = (p == null) ? 0 : p.width;
              
              if (maxWidth < width)
                maxWidth = width;
          }
        }
        
        // Set column's preferred/minimum width
        columnPrefMin[counter] = maxWidth;
      }
    
    
    // Calculate preferred/minimum row heights
    int rowPrefMin[] = new int[rowSpec.length];
    
    for (counter = 0; counter < rowSpec.length; counter++)
      // Is the current row a preferred/minimum size
      if ((rowSpec[counter] == PREFERRED) ||
      (rowSpec[counter] == MINIMUM)) {
        // Assume a maximum height of zero
        int maxHeight = 0;
        
        // Find maximum preferred height of all components completely
        // contained within this row
        ListIterator iterator = list.listIterator(0);
        
        while (iterator.hasNext()) {
          Entry entry = (Entry) iterator.next();
          
          if ((entry.getRow1() == counter) && (entry.getRow1() == counter)) {
            Dimension p = (rowSpec[counter] == PREFERRED) ?
            entry.component.getPreferredSize() :
              entry.component.getMinimumSize();
              
              int height = (p == null) ? 0 : p.height;
              
              if (maxHeight < height)
                maxHeight = height;
          }
        }
        
        // Add preferred height
        rowPrefMin[counter] += maxHeight;
      }
    
    // Find maximum preferred size of all scaled components
    ListIterator iterator = list.listIterator(0);
    
    while (iterator.hasNext()) {
      // Get next entry
      Entry entry = (Entry) iterator.next();
      
      // Make sure entry is in valid rows and columns
      if ((entry.getCol1() < 0) || (entry.getCol1() >= columnSpec.length) ||
      (entry.getCol2() >= columnSpec.length) ||
      (entry.getRow1() < 0) || (entry.getRow1() >= rowSpec.length)    ||
      (entry.getRow2() >= rowSpec.length)) {
        // Skip the bad component
        continue;
      }
      
      // Get preferred size of current component
      size = entry.component.getPreferredSize();
      
      // Calculate portion of component that is not absolutely sized
      int scalableWidth = size.width;
      int scalableHeight = size.height;
      
      for (counter = entry.getCol1(); counter <= entry.getCol2(); counter++)
        if (columnSpec[counter] >= 1.0)
          scalableWidth -= columnSpec[counter];
        else if ((columnSpec[counter] == PREFERRED) ||
        (columnSpec[counter] == MINIMUM)) {
          scalableWidth -= columnPrefMin[counter];
        }
      
      for (counter = entry.getRow1(); counter <= entry.getRow2(); counter++)
        if (rowSpec[counter] >= 1.0)
          scalableHeight -= rowSpec[counter];
        else if ((rowSpec[counter] == PREFERRED) ||
        (rowSpec[counter] == MINIMUM)) {
          scalableHeight -= rowPrefMin[counter];
        }
      
      //----------------------------------------------------------------------
      
      // Determine total percentage of scalable space that the component
      // occupies by adding the relative columns and the fill columns
      double relativeWidth = 0.0;
      
      for (counter = entry.getCol1(); counter <= entry.getCol2(); counter++) {
        // Column is scaled
        if ((columnSpec[counter] > 0.0) && (columnSpec[counter] < 1.0))
          // Add scaled size to relativeWidth
          relativeWidth += columnSpec[counter];
        // Column is fill
        else if ((columnSpec[counter] == FILL) && (fillWidthRatio != 0.0))
          // Add fill size to relativeWidth
          relativeWidth += fillWidthRatio;
      }
      
      // Determine the total scaled width as estimated by this component
      if (relativeWidth == 0)
        temp = 0;
      else
        temp = (int) (scalableWidth / relativeWidth + 0.5);
      
      // If the container needs to be bigger, make it so
      if (scaledWidth < temp)
        scaledWidth = temp;
      
      //----------------------------------------------------------------------
      
      // Determine total percentage of scalable space that the component
      // occupies by adding the relative columns and the fill columns
      double relativeHeight = 0.0;
      
      for (counter = entry.getRow1(); counter <= entry.getRow2(); counter++) {
        // Row is scaled
        if ((rowSpec[counter] > 0.0) && (rowSpec[counter] < 1.0))
          // Add scaled size to relativeHeight
          relativeHeight += rowSpec[counter];
        // Row is fill
        else if ((rowSpec[counter] == FILL) && (fillHeightRatio != 0.0))
          // Add fill size to relativeHeight
          relativeHeight += fillHeightRatio;
      }
      
      // Determine the total scaled width as estimated by this component
      if (relativeHeight == 0)
        temp = 0;
      else
        temp = (int) (scalableHeight / relativeHeight + 0.5);
      
      // If the container needs to be bigger, make it so
      if (scaledHeight < temp)
        scaledHeight = temp;
    }
    
    // totalWidth is the scaledWidth plus the sum of all absolute widths and all
    // preferred widths
    int totalWidth = scaledWidth;
    
    for (counter = 0; counter < columnSpec.length; counter++)
      // Is the current column an absolute size
      if (columnSpec[counter] >= 1.0)
        totalWidth += (int) (columnSpec[counter] + 0.5);
    // Is the current column a preferred/minimum size
      else if ((columnSpec[counter] == PREFERRED) ||
      (columnSpec[counter] == MINIMUM)) {
        // Add preferred/minimum width
        totalWidth += columnPrefMin[counter];
      }
    
    // totalHeight is the scaledHeight plus the sum of all absolute heights and
    // all preferred widths
    int totalHeight = scaledHeight;
    
    for (counter = 0; counter < rowSpec.length; counter++)
      // Is the current row an absolute size
      if (rowSpec[counter] >= 1.0)
        totalHeight += (int) (rowSpec[counter] + 0.5);
    // Is the current row a preferred size
      else if ((rowSpec[counter] == PREFERRED) ||
      (rowSpec[counter] == MINIMUM)) {
        // Add preferred/minimum width
        totalHeight += rowPrefMin[counter];
      }
    
    // Compensate for container's insets
    Insets inset = container.getInsets();
    totalWidth += inset.left + inset.right;
    totalHeight += inset.top + inset.bottom;
    
    return new Dimension(totalWidth, totalHeight);
  }
  
  
  
  /**
   * Determines the minimum size of the container argument using this layout.
   * The minimum size is the smallest size that, if used for the container's
   * size, will ensure that all components are at least as large as their
   * minimum size.  This method cannot guarantee that all components will be
   * their minimum size.  For example, if component A and component B are each
   * allocate half of the container's width and component A wants to be 10 pixels
   * wide while component B wants to be 100 pixels wide, they cannot both be
   * accommodated.  Since in general components rather be larger than their
   * minimum size instead of smaller, component B's request will be fulfilled.
   * The minimum size of the container would be 200 pixels.
   *
   * @param container    container being served by this layout manager
   *
   * @return a dimension indicating the container's minimum size
   */
  
  public Dimension minimumLayoutSize(Container container) {
    Dimension size;       // Minimum size of current component
    int scaledWidth = 0;  // Minimum width of scalled components
    int scaledHeight = 0; // Minimum height of scalled components
    int fillWidth = 0;    // Minimum width of fill components
    int fillHeight = 0;   // Minimum height of fill components
    int temp;             // Temporary variable used to compare sizes
    int counter;          // Counting variable
    
    // Determine percentage of space allocated to fill components.  This is
    // one minus the sum of all scalable components.
    double fillWidthRatio = 1.0;
    double fillHeightRatio = 1.0;
    int    numFillWidth = 0;
    int    numFillHeight = 0;
    
    for (counter = 0; counter < columnSpec.length; counter++)
      if ((columnSpec[counter] > 0.0) && (columnSpec[counter] < 1.0))
        fillWidthRatio -= columnSpec[counter];
      else if (columnSpec[counter] == FILL)
        numFillWidth++;
    
    for (counter = 0; counter < rowSpec.length; counter++)
      if ((rowSpec[counter] > 0.0) && (rowSpec[counter] < 1.0))
        fillHeightRatio -= rowSpec[counter];
      else if (rowSpec[counter] == FILL)
        numFillHeight++;
    
    // Adjust fill ratios to reflect number of fill rows/columns
    if (numFillWidth > 1)
      fillWidthRatio /= numFillWidth;
    
    if (numFillHeight > 1)
      fillHeightRatio /= numFillHeight;
    
    // Cap fill ratio bottoms to 0.0
    if (fillWidthRatio < 0.0)
      fillWidthRatio = 0.0;
    
    if (fillHeightRatio < 0.0)
      fillHeightRatio = 0.0;
    
    // Find maximum minimum size of all scaled components
    ListIterator iterator = list.listIterator(0);
    
    while (iterator.hasNext()) {
      // Get next entry
      Entry entry = (Entry) iterator.next();
      
      // Make sure entry is in valid rows and columns
      if ((entry.getCol1() < 0) || (entry.getCol1() >= columnSpec.length) ||
      (entry.getCol2() >= columnSpec.length) ||
      (entry.getRow1() < 0) || (entry.getRow1() >= rowSpec.length)    ||
      (entry.getRow2() >= rowSpec.length)) {
        // Skip the bad component
        continue;
      }
      
      // Get minimum size of current component
      size = entry.component.getMinimumSize();
      
      // Calculate portion of component that is not absolutely sized
      int scalableWidth = size.width;
      int scalableHeight = size.height;
      
      for (counter = entry.getCol1(); counter <= entry.getCol2(); counter++)
        if (columnSpec[counter] >= 1.0)
          scalableWidth -= columnSpec[counter];
      
      for (counter = entry.getRow1(); counter <= entry.getRow2(); counter++)
        if (rowSpec[counter] >= 1.0)
          scalableHeight -= rowSpec[counter];
      
      //----------------------------------------------------------------------
      
      // Determine total percentage of scalable space that the component
      // occupies by adding the relative columns and the fill columns
      double relativeWidth = 0.0;
      
      for (counter = entry.getCol1(); counter <= entry.getCol2(); counter++) {
        // Column is scaled
        if ((columnSpec[counter] > 0.0) && (columnSpec[counter] < 1.0))
          // Add scaled size to relativeWidth
          relativeWidth += columnSpec[counter];
        // Column is fill
        else if ((columnSpec[counter] == FILL) && (fillWidthRatio != 0.0))
          // Add fill size to relativeWidth
          relativeWidth += fillWidthRatio;
      }
      
      // Determine the total scaled width as estimated by this component
      if (relativeWidth == 0)
        temp = 0;
      else
        temp = (int) (scalableWidth / relativeWidth + 0.5);
      
      // If the container needs to be bigger, make it so
      if (scaledWidth < temp)
        scaledWidth = temp;
      
      //----------------------------------------------------------------------
      
      // Determine total percentage of scalable space that the component
      // occupies by adding the relative columns and the fill columns
      double relativeHeight = 0.0;
      
      for (counter = entry.getRow1(); counter <= entry.getRow2(); counter++) {
        // Row is scaled
        if ((rowSpec[counter] > 0.0) && (rowSpec[counter] < 1.0))
          // Add scaled size to relativeHeight
          relativeHeight += rowSpec[counter];
        // Row is fill
        else if ((rowSpec[counter] == FILL) && (fillHeightRatio != 0.0))
          // Add fill size to relativeHeight
          relativeHeight += fillHeightRatio;
      }
      
      // Determine the total scaled width as estimated by this component
      if (relativeHeight == 0)
        temp = 0;
      else
        temp = (int) (scalableHeight / relativeHeight + 0.5);
      
      // If the container needs to be bigger, make it so
      if (scaledHeight < temp)
        scaledHeight = temp;
    }
    
    // totalWidth is the scaledWidth plus the sum of all absolute widths and all
    // preferred widths
    int totalWidth = scaledWidth;
    
    for (counter = 0; counter < columnSpec.length; counter++)
      // Is the current column an absolute size
      if (columnSpec[counter] >= 1.0)
        totalWidth += (int) (columnSpec[counter] + 0.5);
    // Is the current column a preferred size
      else if ((columnSpec[counter] == PREFERRED) ||
      (columnSpec[counter] == MINIMUM)) {
        // Assume a maximum width of zero
        int maxWidth = 0;
        
        // Find maximum preferred width of all components completely
        // contained within this column
        iterator = list.listIterator(0);
        
        while (iterator.hasNext()) {
          Entry entry = (Entry) iterator.next();
          
          if ((entry.getCol1() == counter) && (entry.getCol2() == counter)) {
            Dimension p = (columnSpec[counter] == PREFERRED) ?
            entry.component.getPreferredSize() :
              entry.component.getMinimumSize();
              
              int width = (p == null) ? 0 : p.width;
              
              if (maxWidth < width)
                maxWidth = width;
          }
        }
        
        // Add preferred width
        totalWidth += maxWidth;
      }
    
    // totalHeight is the scaledHeight plus the sum of all absolute heights and
    // all preferred widths
    int totalHeight = scaledHeight;
    
    for (counter = 0; counter < rowSpec.length; counter++)
      // Is the current row an absolute size
      if (rowSpec[counter] >= 1.0)
        totalHeight += (int) (rowSpec[counter] + 0.5);
    // Is the current row a preferred size
      else if ((rowSpec[counter] == PREFERRED) ||
      (rowSpec[counter] == MINIMUM)) {
        // Assume a maximum height of zero
        int maxHeight = 0;
        
        // Find maximum preferred height of all components completely
        // contained within this row
        iterator = list.listIterator(0);
        
        while (iterator.hasNext()) {
          Entry entry = (Entry) iterator.next();
          
          if ((entry.getRow1() == counter) && (entry.getRow1() == counter)) {
            Dimension p = (rowSpec[counter] == PREFERRED) ?
            entry.component.getPreferredSize() :
              entry.component.getMinimumSize();
              
              int height = (p == null) ? 0 : p.height;
              
              if (maxHeight < height)
                maxHeight = height;
          }
        }
        
        // Add preferred height
        totalHeight += maxHeight;
      }
    
    // Compensate for container's insets
    Insets inset = container.getInsets();
    totalWidth += inset.left + inset.right;
    totalHeight += inset.top + inset.bottom;
    
    return new Dimension(totalWidth, totalHeight);
  }
  
  
  
  
  
  //******************************************************************************
  //** java.awt.event.LayoutManager2 methods                                   ***
  //******************************************************************************
  
  
  
  
  
  
  
  /**
   * Removes the specified component from the layout.
   *
   * @param component    component being removed
   */
  
  public void removeLayoutComponent(Component component) {
    list.remove(component);
  }
  
  public Rectangle adjustBounds(Rectangle bounds) {
  }
  
  public int adjustHeight(int yPixel, int pixelHeight) {
  }
  
  public int adjustWidth(int xPixel, int pixelWidth) {
  }
  
  public int adjustX(int pixel) {
  }
  
  public int adjustY(int pixel) {
  }
  
  public int colSpan(int fromIndex, int pixelWidth) {
  }
  
  public int columnIndex(int pixel) {
  }
  
  public double getColumnWidth(int i) {
  }
  
  public double getRowHeight(int i) {
  }
  
  public int height(int fromIndex, int rowSpan) {
  }
  
  public int rowIndex(int pixel) {
  }
  
  public int rowSpan(int fromIndex, int pixelHeight) {
  }
  
  public void setColumnWidth(int i, double width) {
  }
  
  public void setConstraints(Component component, CustomizerConstraints constraint) {
  }
  
  public void setRowHeight(int i, double height) {
  }
  
  public int width(int fromIndex, int colSpan) {
  }
  
  public int xLocation(int index) {
  }
  
  public int yLocation(int index) {
  }
  
  /** Indicates whether or not the size of the cells are known for the last known
   * size of the container.  If valid is false or the container has been resized,
   * the cell sizes must be recalculated using calculateSize.
   *
   */
  protected AbstractAxis getColumns() {
  }
  
  protected AbstractAxis getRows() {
  }
  
  private static class FiniteAxis extends AbstractTableLayout.AbstractAxis{
    private int[] sizes;
    private int[] offsets;
    private FiniteAxisUnit[] units;
    private Map absoluteIndices = new LinkedHashMap();
    private Map componentIndices = new LinkedHashMap();
    private Map relativeIndices = new LinkedHashMap();
    private Map fillIndices = new LinkedHashMap();
    
    public FiniteAxis(int size){
      sizes = new int[size];
      offsets = new int[size+1];
      units = new FiniteAxisUnits[size];
      for (int i=0; i<size; i++){
        setFillUnit(i);
      }
    }
    
    public FiniteAxis(double[] unitSpecs){
      sizes = new int[unitSpec.length];
      offsets = new int[unitSpec.length+1];
      units = new FiniteAxisUnits[unitSpec.length];
      for (int i=0; i<unitSpec.length; i++){
        setUnit(i, unitSpecs[i]);
      }
    }
    
    public void setUnit(int index, double unitSpec){
      if (unitSpec == FILL){
        setFillUnit(index);
      } else if(unitSpec == PREFERRED){
        setPreferredUnit(index);
      } else if(unitSpec == MINIMUM){
        setMinimumUnit(index);
      } else if (unitSpec > 0 && unitSpec < 1){
        setRelativeUnit(index, unitSpec);
      } else {
        setAbsoluteUnit(index, unitSpec>0 ? unitSpec:0);
      }
    }
    
    public void setFillUnit(int index){
      removeOldUnit(index);
      units[index] = new FillUnit();
      fillUnits.put(units[index],  new Integer(index));
    }
    public void setPreferredUnit(int index){
      removeOldUnit(index);
      units[index] = new PreferredUnit();
      componentUnits.put(units[index],  new Integer(index));
    }
    public void setMinimumUnit(int index){
      removeOldUnit(index);
      units[index] = new MinimumUnit();
      componentUnits.put(units[index],  new Integer(index));
    }
    public void setRelativeUnit(int index, int relativeSize){
      removeOldUnit(index);
      units[index] = new RelativeUnit(relativeSize);
      relativeUnits.put(units[index],  new Integer(index));
    }
    public void setAbsoluteUnit(int index, int absoluteSize){
      removeOldUnit(index);
      units[index] = new AbsoluteUnit(absoluteSize);
      absoluteUnits.put(units[index],  new Integer(index));
    }
    
    private void removeOldUnit(int index){
      if (units[index] != null){
        units[index].removeYourself();
      }
    }
    
    public void insertUnit(int index, double unitSpec){
      // Make sure position is valid
      if ((index < 0) || (index > units.length)){
        throw new IllegalArgumentException
        ("Parameter index is invalid.  index = " + index + ".  Valid range is [0, " +
        units.length + "].");
      }
      
      // Copy columns
      double newUnits[] = new FiniteAxisUnit[units.length + 1];
      System.arraycopy(units, 0, newUnits, 0, index);
      System.arraycopy(units, index, newUnits, index + 1, units.length - index);
      insertionIndicesUpdate(index);
      units = newUnits;
      setUnit(index, unitSpec);
    }
    
    private void insertionIndicesUpdate(int index){
      insertionIndicesUpdate(fillIndices, index);
      insertionIndicesUpdate(componentIndices, index);
      insertionIndicesUpdate(relativeIndices, index);
      insertionIndicesUpdate(absoluteIndices, index);
    }
    
    private void insertionIndicesUpdate(Map indices, int index){
      for (Iterator i=indices.keySet().iterator(); i.hasNext();){
        FiniteAxisUnit unit = (FiniteAxisUnit) i.next();
        int i = ((Integer) indices.get(unit)).intValue();
        if (i >= index){
          indices.put(unit, new Integer(i+1));
        }
      }
    }
    
    public void deleteUnit(int index){
      // Make sure position is valid
      if ((index < 0) || (i >= units.length))
        throw new IllegalArgumentException
        ("Parameter index is invalid.  index = " + index + ".  Valid range is [0, " +
        (units.length - 1) + "].");
      
      // Copy columns
      double newUnits[] = new double[units.length - 1];
      System.arraycopy(units, 0, newUnits, 0, index);
      System.arraycopy(units, index + 1, newUnits, index, units.length - index - 1);
      deletionUnitsUpdate(index);
      // Delete column
      units = newUnits;
    }
    
    private void deletionUnitsUpdate(int index){
      removeOldUnit(index);
      deletionUnitsUpdate(fillIndices, index);
      deletionUnitsUpdate(componentIndices, index);
      deletionUnitsUpdate(relativeIndices, index);
      deletionUnitsUpdate(absoluteIndices, index);
    }
    
    private void deletionUnitsUpdate(Map indices, int index){
      for (Iterator i=indices.keySet().iterator(); i.hasNext();){
        FiniteAxisUnit unit = (FiniteAxisUnit) i.next();
        int i = ((Integer) indices.get(unit)).intValue();
        if (i > index){
          indices.put(unit, new Integer(i-1));
        }
      }
    }
    private void calculateSizes(){
      calculateAbsoluteSizes();
      calculatComponentDependentSizes();
      int availableWidth = availableRelativeSize(innerWidth);
      calculateRelativeSizes(availableWidth);
      availableWidth = availableFillSize(availableWidth);
      calculateFillSizes(availableWidth);
    }
    
    private void calculateAbsoluteSizes() {
      // Assign absolute heights; this reduces available height
      for (Iterator i=absoluteUnits.keySet().iterator(); i.hasNext();){
        AbsoluteUnit unit = (AbsoluteUnit) i.next();
        sizes[((Integer) absoluteUnits.get(unit)).intValue()] = unit.getSize();
      }
    }
    
    private void calculatComponentDependentSizes(){
    }
    
    private int availableRelativeHeight(final int startHeight) {
      // Initially, the available space is the total space
      int availableHeight = startHeight;
      // Assign absolute heights; this reduces available height
      for (Iterator i=absoluteUnits.keySet().iterator(); i.hasNext();){
        AbsoluteUnit unit = (AbsoluteUnit) i.next();
        availableHeight -= unit.getSize();
      }
      for (Iterator i=componentDependentUnits.keySet().iterator(); i.hasNext();){
        availableHeight -= rowSize[((Integer) componentDependentUnits.get(i.next)).intValue()];
      }
      return availableHeight;
    }
    
    private void calculateRelativeSizes(int availableSize) {
      int relativeSize = availableSize;
      if (relativeSize < 0){
        relativeSize = 0;
      }
      // Assign relative widths
      for (Iterator i=relativeUnits.keySet().iterator(); i.hasNext();){
        RelativeUnit unit = (RelativeUnit) i.next();
        sizes[((Integer) relativeUnits.get(unit)).intValue()] = unit.size(relativeHeight);
      }
    }
    
    private int availableFillSize(int availableRelativeSize) {
      int availableFillSize = availableRelativeSize;
      for (Iterator i=relativeUnits.keySet().iterator(); i.hasNext();){
        availableFillSize -= sizes[((Integer) relativeUnits.get(i.next)).intValue()];
      }
      return availableFillSize > 0 ? availableFillSize : 0;
    }
    
    private void calculateFillSizes(int availableSize){
      // If there are more than one "fill" cell, slack may occur due to rounding
      // errors
      int slackSize = availableSize;
      int numFill = fillUnits.size();
      if (numFill > 1){
        int size = availableSize / numFill;
        int maxIndex = -1;
        for (Iterator i=fillUnits.keySet().iterartor(); i.hasNext();){
          int index = ((Integer) fillUnits.get(i.next())).intValue();
          if (index > maxIndex){
            maxIndex = index;
          }
          sizes[index] = size;
          slackSize -= sizes[index];
        }
        sizes[maxIndex] += slackSize;
      }
    }
    private void calculateOffsets(int x, int innerArea) {
      // Calculate offsets of each column (done for effeciency)
      offsets = new int[sizes.length + 1];
      offsets[0] = x;
      for (int i = 0; i < sizes.length; i++){
        offsets[i + 1] = offsets[i] + sizes[i];
      }
    }
      /**
   * Determines the preferred size of the container argument using this layout.
   * The preferred size is the smallest size that, if used for the container's
   * size, will ensure that all components are at least as large as their
   * preferred size.  This method cannot guarantee that all components will be
   * their preferred size.  For example, if component A and component B are each
   * allocate half of the container's width and component A wants to be 10 pixels
   * wide while component B wants to be 100 pixels wide, they cannot both be
   * accommodated.  Since in general components rather be larger than their
   * preferred size instead of smaller, component B's request will be fulfilled.
   * The preferred size of the container would be 200 pixels.
   *
   * @param container    container being served by this layout manager
   *
   * @return a dimension indicating the container's preferred size
   */
  
  public Dimension preferredLayoutSize(TableLayout tl) {
    Dimension size;       // Preferred size of current component
    int scaledSize = 0;  // Preferred width of scalled components
    int temp;             // Temporary variable used to compare sizes
    int counter;          // Counting variable
    
    // Determine percentage of space allocated to fill components.  This is
    // one minus the sum of all scalable components.
    double fillSizeRatio = 1.0;
    
    for (Iterator i=relativeIndices.keySet().iterator(); i.hasNext();){
        fillSizeRatio -= ((RelativeUnit) i.next()).getPercentage();
    }
    
    
    // Adjust fill ratios to reflect number of fill rows/columns
    if (fillIndices.size() > 1)
      fillSizeRatio /= fillIndices.size();
    
    
    // Cap fill ratio bottoms to 0.0
    if (fillSizeRatio < 0.0)
      fillSizeRatio = 0.0;
    
    
    // Calculate preferred/minimum column widths
    int columnPrefMin[] = new int[units.length];
    
    for (Iterator i=componentIndices.keySet().iterator(); i.hasNext();){
        // Assume a maximum width of zero
        int maxWidth = 0;
        SortedMap constraints = tl.getConstraints();

        
        while (iterator.hasNext()) {
          Entry entry = (Entry) iterator.next();
          
          if ((entry.getCol1() == counter) && (entry.getCol2() == counter)) {
            Dimension p = (columnSpec[counter] == PREFERRED) ?
            entry.component.getPreferredSize() :
              entry.component.getMinimumSize();
              
              int width = (p == null) ? 0 : p.width;
              
              if (maxWidth < width)
                maxWidth = width;
          }
        }
        
        // Set column's preferred/minimum width
        columnPrefMin[counter] = maxWidth;
      }
    
    
    
    
    // Find maximum preferred size of all scaled components
    ListIterator iterator = list.listIterator(0);
    
    while (iterator.hasNext()) {
      // Get next entry
      Entry entry = (Entry) iterator.next();
      
      // Make sure entry is in valid rows and columns
      if ((entry.getCol1() < 0) || (entry.getCol1() >= columnSpec.length) ||
      (entry.getCol2() >= columnSpec.length) ||
      (entry.getRow1() < 0) || (entry.getRow1() >= rowSpec.length)    ||
      (entry.getRow2() >= rowSpec.length)) {
        // Skip the bad component
        continue;
      }
      
      // Get preferred size of current component
      size = entry.component.getPreferredSize();
      
      // Calculate portion of component that is not absolutely sized
      int scalableWidth = size.width;
      int scalableHeight = size.height;
      
      for (counter = entry.getCol1(); counter <= entry.getCol2(); counter++)
        if (columnSpec[counter] >= 1.0)
          scalableWidth -= columnSpec[counter];
        else if ((columnSpec[counter] == PREFERRED) ||
        (columnSpec[counter] == MINIMUM)) {
          scalableWidth -= columnPrefMin[counter];
        }
      
      for (counter = entry.getRow1(); counter <= entry.getRow2(); counter++)
        if (rowSpec[counter] >= 1.0)
          scalableHeight -= rowSpec[counter];
        else if ((rowSpec[counter] == PREFERRED) ||
        (rowSpec[counter] == MINIMUM)) {
          scalableHeight -= rowPrefMin[counter];
        }
      
      //----------------------------------------------------------------------
      
      // Determine total percentage of scalable space that the component
      // occupies by adding the relative columns and the fill columns
      double relativeWidth = 0.0;
      
      for (counter = entry.getCol1(); counter <= entry.getCol2(); counter++) {
        // Column is scaled
        if ((columnSpec[counter] > 0.0) && (columnSpec[counter] < 1.0))
          // Add scaled size to relativeWidth
          relativeWidth += columnSpec[counter];
        // Column is fill
        else if ((columnSpec[counter] == FILL) && (fillWidthRatio != 0.0))
          // Add fill size to relativeWidth
          relativeWidth += fillWidthRatio;
      }
      
      // Determine the total scaled width as estimated by this component
      if (relativeWidth == 0)
        temp = 0;
      else
        temp = (int) (scalableWidth / relativeWidth + 0.5);
      
      // If the container needs to be bigger, make it so
      if (scaledWidth < temp)
        scaledWidth = temp;
      
      //----------------------------------------------------------------------
      
      // Determine total percentage of scalable space that the component
      // occupies by adding the relative columns and the fill columns
      double relativeHeight = 0.0;
      
      for (counter = entry.getRow1(); counter <= entry.getRow2(); counter++) {
        // Row is scaled
        if ((rowSpec[counter] > 0.0) && (rowSpec[counter] < 1.0))
          // Add scaled size to relativeHeight
          relativeHeight += rowSpec[counter];
        // Row is fill
        else if ((rowSpec[counter] == FILL) && (fillHeightRatio != 0.0))
          // Add fill size to relativeHeight
          relativeHeight += fillHeightRatio;
      }
      
      // Determine the total scaled width as estimated by this component
      if (relativeHeight == 0)
        temp = 0;
      else
        temp = (int) (scalableHeight / relativeHeight + 0.5);
      
      // If the container needs to be bigger, make it so
      if (scaledHeight < temp)
        scaledHeight = temp;
    }
    
    // totalWidth is the scaledWidth plus the sum of all absolute widths and all
    // preferred widths
    int totalWidth = scaledWidth;
    
    for (counter = 0; counter < columnSpec.length; counter++)
      // Is the current column an absolute size
      if (columnSpec[counter] >= 1.0)
        totalWidth += (int) (columnSpec[counter] + 0.5);
    // Is the current column a preferred/minimum size
      else if ((columnSpec[counter] == PREFERRED) ||
      (columnSpec[counter] == MINIMUM)) {
        // Add preferred/minimum width
        totalWidth += columnPrefMin[counter];
      }
    
    // totalHeight is the scaledHeight plus the sum of all absolute heights and
    // all preferred widths
    int totalHeight = scaledHeight;
    
    for (counter = 0; counter < rowSpec.length; counter++)
      // Is the current row an absolute size
      if (rowSpec[counter] >= 1.0)
        totalHeight += (int) (rowSpec[counter] + 0.5);
    // Is the current row a preferred size
      else if ((rowSpec[counter] == PREFERRED) ||
      (rowSpec[counter] == MINIMUM)) {
        // Add preferred/minimum width
        totalHeight += rowPrefMin[counter];
      }
    
    // Compensate for container's insets
    Insets inset = container.getInsets();
    totalWidth += inset.left + inset.right;
    totalHeight += inset.top + inset.bottom;
    
    return new Dimension(totalWidth, totalHeight);
  }
  
    private class FiniteAxisUnit{
      public abstract void removeYourself();
    }
    
    private class FillUnit extends RelativeUnit{
      public FillUnit(){
        super(1);
      }
      public void removeYourself() {
        fillUnits.remove(this);
      }
    }
    
    private class PreferredUnit extends FiniteAxisUnit{
      public int size(){
        //        for (Iterator i=constraints.
        //        while (iterator.hasNext()) {
        //          Entry entry = (Entry) iterator.next();
        //
        //          if ((entry.getRow1() == row) && (entry.getRow2() == row)) {
        //            Dimension p = (rowSpec[row] == PREFERRED) ?
        //            entry.component.getPreferredSize() :
        //              entry.component.getMinimumSize();
        //
        //              int height = (p == null) ? 0 : p.height;
        //
        //              if (maxHeight < height)
        //                maxHeight = height;
        //          }
        //        }
        //      }
        //    }
        //
        //    // Assign preferred height
        //    rowSize[row] = maxHeight;
      }
      
      public void removeYourself() {
        componentUnits.remove(this);
      }
    }
    private class MinimumUnit extends FiniteAxisUnit{
      
      public void removeYourself() {
        componentUnits.remove(this);
      }
      
    }
    
    private class RelativeUnit extends FiniteAxisUnit{
      private int percentage;
      public RelativeUnit(int percentage){
        this.size = size;
      }
      public int size(int relativeSize){
        return (int) (percentage * relativeSize + 0.5);
      }
      
      public void removeYourself() {
        relativeUnits.remove(this);
      }
      
    }
    
    private class AbsoluteUnit extends FiniteAxisUnit{
      private int size;
      public AbsoluteUnit(int size){
        this.size = size;
      }
      
      public void removeYourself() {
        absoluteUnits.remove(this);
      }
      
    }
    
  }
  
}
