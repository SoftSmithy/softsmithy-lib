package puce.awt;

import java.util.*;
import java.io.*;
import java.awt.*;
import puce.awt.*;



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

public class FiniteTableLayout extends AbstractTableLayout implements TableLayoutConstants, Serializable{
  
  
  
  /** Default row/column size */
  protected static final double defaultSize[][] = {{}, {}};
  
  
  
  /** Widths of columns expressed in absolute and relative terms */
  protected double columnSpec[];
  
  /** Heights of rows expressed in absolute and relative terms */
  protected double rowSpec[];
  
  /** Widths of columns in pixels */
  protected int columnSize[];
  
  /** Heights of rows in pixels */
  protected int rowSize[];
  
  /** Offsets of columns in pixels.  The left boarder of column n is at
   * columnOffset[n] and the right boarder is at columnOffset[n + 1] for all
   * columns including the last one.  columnOffset.length = columnSize.length + 1 */
  protected int columnOffset[];
  
  /** Offsets of rows in pixels.  The left boarder of row n is at
   * rowOffset[n] and the right boarder is at rowOffset[n + 1] for all
   * rows including the last one.  rowOffset.length = rowSize.length + 1 */
  protected int rowOffset[];
  
  /** List of components and their sizes */
  protected LinkedList list;
  
  
  
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
      double tempCol[] = size[0];
      double tempRow[] = size[1];
      
      // Create new rows and columns
      columnSpec = new double[tempCol.length];
      rowSpec = new double[tempRow.length];
      
      // Copy rows and columns
      System.arraycopy(tempCol, 0, columnSpec, 0, columnSpec.length);
      System.arraycopy(tempRow, 0, rowSpec, 0, rowSpec.length);
      
      // Make sure rows and columns are valid
      for (int counter = 0; counter < columnSpec.length; counter++)
        if ((columnSpec[counter] < 0.0) &&
        (columnSpec[counter] != FILL) &&
        (columnSpec[counter] != PREFERRED) &&
        (columnSpec[counter] != MINIMUM)) {
          columnSpec[counter] = 0.0;
        }
      
      for (int counter = 0; counter < rowSpec.length; counter++)
        if ((rowSpec[counter] < 0.0) &&
        (rowSpec[counter] != FILL) &&
        (rowSpec[counter] != PREFERRED) &&
        (rowSpec[counter] != MINIMUM)) {
          rowSpec[counter] = 0.0;
        }
    }
    else {
      double tempCol[] = {FILL};
      double tempRow[] = {FILL};
      
      setColumn(tempCol);
      setRow(tempRow);
    }
    
    // Create an empty list of components
    list = new LinkedList();
    
    // Indicate that the cell sizes are not known
    setValid(false);
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
  
  public TableLayoutConstraints getConstraints(Component component) {
    ListIterator iterator = list.listIterator(0);
    
    while (iterator.hasNext()) {
      Entry entry = (Entry) iterator.next();
      
      if (entry.component == component)
        return new TableLayoutConstraints
        (entry.getCol1(), entry.getRow1(), entry.getCol2(), entry.getRow2(),
        entry.getHAlign(), entry.getVAlign());
    }
    
    return null;
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
  
  public void setConstraints(Component component, TableConstraints constraints) {
    // Check parameters
    if (component == null)
      throw new IllegalArgumentException
      ("Parameter component cannot be null.");
    else if (constraints == null)
      throw new IllegalArgumentException
      ("Parameter constraint cannot be null.");
    
    // Find and update constraints for the given component
    ListIterator iterator = list.listIterator(0);
    
    while (iterator.hasNext()) {
      Entry entry = (Entry) iterator.next();
      
      if (entry.component == component)
        iterator.set(new Entry(component, constraints));
    }
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
  
  public void setColumn(double column[]) {
    // Copy columns
    columnSpec = new double[column.length];
    System.arraycopy(column, 0, columnSpec, 0, columnSpec.length);
    
    // Make sure columns are valid
    for (int counter = 0; counter < columnSpec.length; counter++)
      if ((columnSpec[counter] < 0.0) &&
      (columnSpec[counter] != FILL) &&
      (columnSpec[counter] != PREFERRED) &&
      (columnSpec[counter] != MINIMUM)) {
        columnSpec[counter] = 0.0;
      }
    
    // Indicate that the cell sizes are not known
    setValid(false);
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
  
  public void setRow(double row[]) {
    // Copy rows
    rowSpec = new double[row.length];
    System.arraycopy(row, 0, rowSpec, 0, rowSpec.length);
    
    // Make sure rows are valid
    for (int counter = 0; counter < rowSpec.length; counter++)
      if ((rowSpec[counter] < 0.0) &&
      (rowSpec[counter] != FILL) &&
      (rowSpec[counter] != PREFERRED) &&
      (rowSpec[counter] != MINIMUM)) {
        rowSpec[counter] = 0.0;
      }
    
    // Indicate that the cell sizes are not known
    setValid(false);
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
  
  public void setColumn(int i, double size) {
    // Make sure size is valid
    if ((size < 0.0) &&
    (size != FILL) &&
    (size != PREFERRED) &&
    (size != MINIMUM)) {
      size = 0.0;
    }
    
    // Copy new size
    columnSpec[i] = size;
    
    // Indicate that the cell sizes are not known
    setValid(false);
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
  
  public void setRow(int i, double size) {
    // Make sure size is valid
    if ((size < 0.0) &&
    (size != FILL) &&
    (size != PREFERRED) &&
    (size != MINIMUM)) {
      size = 0.0;
    }
    
    // Copy new size
    rowSpec[i] = size;
    
    // Indicate that the cell sizes are not known
    setValid(false);
  }
  
  
  
  /**
   * Gets the sizes of columns in this layout.
   *
   * @return widths of each of the columns
   *
   * @see setColumn
   */
  
  public double [] getColumn() {
    // Copy columns
    double column[] = new double[columnSpec.length];
    System.arraycopy(columnSpec, 0, column, 0, column.length);
    
    return column;
  }
  
  
  
  /**
   * Gets the height of a single row in this layout.
   *
   * @return height of the requested row
   *
   * @see setRow
   */
  
  public double [] getRow() {
    // Copy rows
    double row[] = new double[rowSpec.length];
    System.arraycopy(rowSpec, 0, row, 0, row.length);
    
    return row;
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
  
  public double getColumn(int i) {
    return columnSpec[i];
  }
  
  
  
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
  
  public double getRow(int i) {
    return rowSpec[i];
  }
  
  
  
  /**
   * Gets the number of columns in this layout.
   *
   * @return the number of columns
   */
  
  public int getNumColumn() {
    return columnSpec.length;
  }
  
  
  
  /**
   * Gets the number of rows in this layout.
   *
   * @return the number of rows
   */
  
  public int getNumRow() {
    return rowSpec.length;
  }
  
  
  
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
  
  public void insertColumn(int i, double size) {
    // Make sure position is valid
    if ((i < 0) || (i > columnSpec.length))
      throw new IllegalArgumentException
      ("Parameter i is invalid.  i = " + i + ".  Valid range is [0, " +
      columnSpec.length + "].");
    
    // Make sure column size is valid
    if ((size < 0.0) &&
    (size != FILL) &&
    (size != PREFERRED) &&
    (size != MINIMUM)) {
      size = 0.0;
    }
    
    // Copy columns
    double column[] = new double[columnSpec.length + 1];
    System.arraycopy(columnSpec, 0, column, 0, i);
    System.arraycopy(columnSpec, i, column, i + 1, columnSpec.length - i);
    
    // Insert column
    column[i] = size;
    columnSpec = column;
    
    // Move all components that are to the right of new row
    ListIterator iterator = list.listIterator(0);
    
    while (iterator.hasNext()) {
      // Get next entry
      Entry entry = (Entry) iterator.next();
      
      // Is the first column to the right of the new column
      if (entry.getCol1() >= i)
        // Move first column
        entry.setCol1(entry.getCol1() + 1);
      
      // Is the second column to the right of the new column
      if (entry.getCol2() >= i)
        // Move second column
        entry.setCol2(entry.getCol2() + 1);
    }
    
    // Indicate that the cell sizes are not known
    setValid(false);
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
  
  public void insertRow(int i, double size) {
    // Make sure position is valid
    if ((i < 0) || (i > rowSpec.length))
      throw new IllegalArgumentException
      ("Parameter i is invalid.  i = " + i + ".  Valid range is [0, " +
      rowSpec.length + "].");
    
    // Make sure row size is valid
    if ((size < 0.0) &&
    (size != FILL) &&
    (size != PREFERRED) &&
    (size != MINIMUM)) {
      size = 0.0;
    }
    
    // Copy rows
    double row[] = new double[rowSpec.length + 1];
    System.arraycopy(rowSpec, 0, row, 0, i);
    System.arraycopy(rowSpec, i, row, i + 1, rowSpec.length - i);
    
    // Insert row
    row[i] = size;
    rowSpec = row;
    
    // Move all components that are below the new row
    ListIterator iterator = list.listIterator(0);
    
    while (iterator.hasNext()) {
      // Get next entry
      Entry entry = (Entry) iterator.next();
      
      // Is the first row to the right of the new row
      if (entry.getRow1() >= i)
        // Move first row
        entry.setRow1(entry.getRow1() + 1);
      
      // Is the second row to the right of the new row
      if (entry.getRow2() >= i)
        // Move second row
        entry.setRow2(entry.getRow2() + 1);
    }
    
    // Indicate that the cell sizes are not known
    setValid(false);
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
  
  public void deleteColumn(int i) {
    // Make sure position is valid
    if ((i < 0) || (i >= columnSpec.length))
      throw new IllegalArgumentException
      ("Parameter i is invalid.  i = " + i + ".  Valid range is [0, " +
      (columnSpec.length - 1) + "].");
    
    // Copy columns
    double column[] = new double[columnSpec.length - 1];
    System.arraycopy(columnSpec, 0, column, 0, i);
    System.arraycopy(columnSpec, i + 1, column, i, columnSpec.length - i - 1);
    
    // Delete column
    columnSpec = column;
    
    // Move all components that are to the right of row deleted
    ListIterator iterator = list.listIterator(0);
    
    while (iterator.hasNext()) {
      // Get next entry
      Entry entry = (Entry) iterator.next();
      
      // Is the first column to the right of the new column
      if (entry.getCol1() >= i)
        // Move first column
        entry.setCol1(entry.getCol1() - 1);
      
      // Is the second column to the right of the new column
      if (entry.getCol2() >= i)
        // Move second column
        entry.setCol2(entry.getCol2() - 1);
    }
    
    // Indicate that the cell sizes are not known
    setValid(false);
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
  
  public void deleteRow(int i) {
    // Make sure position is valid
    if ((i < 0) || (i >= rowSpec.length))
      throw new IllegalArgumentException
      ("Parameter i is invalid.  i = " + i + ".  Valid range is [0, " +
      (rowSpec.length - 1) + "].");
    
    // Copy rows
    double row[] = new double[rowSpec.length - 1];
    System.arraycopy(rowSpec, 0, row, 0, i);
    System.arraycopy(rowSpec, i + 1, row, i, rowSpec.length - i - 1);
    
    // Delete row
    rowSpec = row;
    
    // Move all components that are to below the row deleted
    ListIterator iterator = list.listIterator(0);
    
    while (iterator.hasNext()) {
      // Get next entry
      Entry entry = (Entry) iterator.next();
      
      // Is the first row below the new row
      if (entry.getRow1() >= i)
        // Move first row
        entry.setRow1(entry.getRow1() - 1);
      
      // Is the second row below the new row
      if (entry.getRow2() >= i)
        // Move second row
        entry.setRow2(entry.getRow2() - 1);
    }
    
    // Indicate that the cell sizes are not known
    setValid(false);
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
  
  public String toString() {
    int counter;
    
    String value = "TableLayout {{";
    
    if (columnSpec.length > 0) {
      for (counter = 0; counter < columnSpec.length - 1; counter++)
        value += columnSpec[counter] + ", ";
      
      value += columnSpec[columnSpec.length - 1] + "}, {";
    }
    else
      value += "}, {";
    
    if (rowSpec.length > 0) {
      for (counter = 0; counter < rowSpec.length - 1; counter++)
        value += rowSpec[counter] + ", ";
      
      value += rowSpec[rowSpec.length - 1] + "}}";
    }
    else
      value += "}}";
    
    return value;
  }
  
  
  
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
  
  
  
  
  
  protected void calculateRowSizes(final int innerHeight) {
    // Create array to hold actual sizes in pixels
    rowSize = new int[rowSpec.length];
    calculateAbsoluteRowSizes();
    calculatePreferredRowSizes();
    int availableHeight = availableRelativeHeight(innerHeight);
    calculateRelativeRowSizes(availableHeight);
    availableHeight = availableFillHeight(availableHeight);
    calculateFillRowSizes(availableHeight);
  }
  
  protected void calculateColumnSizes(final int innerWidth) {
    // Create array to hold actual sizes in pixels
    columnSize = new int[columnSpec.length];
    calculateAbsoluteColumnSizes();
    calculatePreferredColumnSizes();
    int availableWidth = availableRelativeWidth(innerWidth);
    calculateRelativeColumnSizes(availableWidth);
    availableWidth = availableFillWidth(availableWidth);
    calculateFillColumnSizes(availableWidth);
  }
  
  private int availableFillHeight(final int availableRelativeHeight) {
    int availableFillHeight = availableRelativeHeight;
    for (int counter = 0; counter < rowSpec.length; counter++){
      // Is the current column an relative size
      if (rowHasRelativeSize(counter)){        // Reduce available width
        availableFillHeight -= rowSize[counter];
      }
    }
    // Make sure availableWidth and availableHeight are non-negative
    if (availableFillHeight < 0){
      availableFillHeight = 0;
    }
    
    return availableFillHeight;
  }
  
  private int availableFillWidth(final int availableRelativeWidth) {
    int availableFillWidth = availableRelativeWidth;
    for (int counter = 0; counter < columnSpec.length; counter++){
      // Is the current column an relative size
      if (columnHasRelativeSize(counter)){
        // Reduce available width
        availableFillWidth -= columnSize[counter];
      }
    }
    // Make sure availableWidth and availableHeight are non-negative
    if (availableFillWidth < 0){
      availableFillWidth = 0;
    }
    
    return availableFillWidth;
  }
  
  private boolean rowHasRelativeSize(final int row) {
    return (rowSpec[row] > 0.0) && (rowSpec[row] < 1.0);
  }
  
  private void calculateRelativeRowSizes(final int availableHeight) {
    
    // Remember how much space is available for relatively sized cells
    int relativeHeight = availableHeight;
    
    // Make sure relativeWidth and relativeHeight are non-negative
    
    if (relativeHeight < 0)
      relativeHeight = 0;
    // Assign relative widths
    for (int counter = 0; counter < rowSpec.length; counter++){
      // Is the current column an relative size
      if (rowHasRelativeSize(counter)) {
        calculateRelativeRowSize(counter, relativeHeight);
        
      }
    }
  }
  
  private void calculateRelativeColumnSizes(final int availableWidth) {
    // Remember how much space is available for relatively sized cells
    int relativeWidth = availableWidth;
    
    // Make sure relativeWidth and relativeHeight are non-negative
    if (relativeWidth < 0)
      relativeWidth = 0;
    
    // Assign relative widths
    for (int counter = 0; counter < columnSpec.length; counter++){
      // Is the current column an relative size
      if (columnHasRelativeSize(counter)){
        calculateRelativeColumnSize(counter, relativeWidth);
        
      }
    }
  }
  
  private int availableRelativeHeight(final int startHeight) {
    // Initially, the available space is the total space
    int availableHeight = startHeight;
    // Assign absolute heights; this reduces available height
    for (int counter = 0; counter < rowSpec.length; counter++){
      if (rowHasAbsoluteSize(counter) || rowHasPreferredSize(counter)) {
        // Reduce available width
        availableHeight -= rowSize[counter];
      }
    }
    
    return availableHeight;
  }
  
  private int availableRelativeWidth(final int startWidth) {
    // Initially, the available space is the total space
    int availableWidth = startWidth;
    
    // Assign absolute widths; this reduces available width
    for (int counter = 0; counter < columnSpec.length; counter++){
      if (columnHasAbsoluteSize(counter) || columnHasPreferredSize(counter)){
        // Reduce available width
        availableWidth -= columnSize[counter];
      }
    }
    
    return availableWidth;
  }
  
  private boolean columnHasRelativeSize(final int column) {
    return (columnSpec[column] > 0.0) && (columnSpec[column] < 1.0);
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
  
  private void calculateAbsoluteRowSizes() {
    // Assign absolute heights; this reduces available height
    for (int counter = 0; counter < rowSpec.length; counter++){
      if (rowHasAbsoluteSize(counter)) {
        calculateAbsoluteRowSize(counter);
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
  
  private void calculateAbsoluteColumnSizes() {
    for (int counter = 0; counter < columnSpec.length; counter++){
      if (columnHasAbsoluteSize(counter)){
        calculateAbsoluteColumnSize(counter);
      }
    }
  }
  
  private boolean columnHasPreferredSize(final int column) {
    return (columnSpec[column] == PREFERRED) ||
    (columnSpec[column] == MINIMUM);
  }
  
  private boolean rowHasPreferredSize(final int row) {
    return (rowSpec[row] == PREFERRED) ||
    (rowSpec[row] == MINIMUM);
  }
  
  protected void calculateRowOffsets(int y, int innerArea) {
    // Calculate offsets of each row (done for effeciency)
    rowOffset = new int[rowSpec.length + 1];
    rowOffset[0] = startOffset;
    
    for (int counter = 0; counter < rowSpec.length; counter++){
      rowOffset[counter + 1] =
      rowOffset[counter] + rowSize[counter];
    }
  }
  
  protected void calculateColumnOffsets(int x, int innerArea) {
    // Calculate offsets of each column (done for effeciency)
    columnOffset = new int[columnSpec.length + 1];
    columnOffset[0] = startOffset;
    
    for (int counter = 0; counter < columnSpec.length; counter++){
      columnOffset[counter + 1] =
      columnOffset[counter] + columnSize[counter];
    }
  }
  
  private void calculateFillRowSizes(int availableHeight){
    // If numFillWidth (numFillHeight) is zero, the cooresponding if statements
    // will always evaluate to false and the division will not occur.
    // If there are more than one "fill" cell, slack may occur due to rounding
    // errors
    int slackHeight = availableHeight;
    int numFillHeight = numFillHeight();
    for (int counter = 0; counter < rowSpec.length; counter++)
      if (rowSpec[counter] == FILL) {
        rowSize[counter] = availableHeight / numFillHeight;
        slackHeight -= rowSize[counter];
      }
    
    
    for (int counter = rowSpec.length - 1; counter >= 0; counter--) {
      if (rowSpec[counter] == FILL) {
        rowSize[counter] += slackHeight;
        break;
      }
    }
  }
  private void calculateFillColumnSizes(int availableWidth){
    // If numFillWidth (numFillHeight) is zero, the cooresponding if statements
    // will always evaluate to false and the division will not occur.
    
    // If there are more than one "fill" cell, slack may occur due to rounding
    // errors
    int slackWidth = availableWidth;
    int numFillWidth = numFillWidth();
    // Assign "fill" cells equal amounts of the remaining space
    for (int counter = 0; counter < columnSpec.length; counter++){
      if (columnSpec[counter] == FILL) {
        columnSize[counter] = availableWidth / numFillWidth;
        slackWidth -= columnSize[counter];
      }
    }
    // Add slack to the last "fill" cell
    for (int counter = columnSpec.length - 1; counter >= 0; counter--) {
      if (columnSpec[counter] == FILL) {
        columnSize[counter] += slackWidth;
        break;
      }
    }
  }
  
  private int numFillHeight() {
    int numFillHeight = 0;
    
    for (int counter = 0; counter < rowSpec.length; counter++){
      if (rowSpec[counter] == FILL){
        numFillHeight++;
      }
    }
    
    return numFillHeight;
  }
  
  private int numFillWidth() {
    
    int numFillWidth = 0;
    
    
    for (int counter = 0; counter < columnSpec.length; counter++){
      if (columnSpec[counter] == FILL){
        numFillWidth++;
      }
    }
    
    return numFillWidth;
  }
  
  private void calculateRelativeRowSize(final int row, final int relativeHeight) {
    rowSize[row] = (int) (rowSpec[row] * relativeHeight + 0.5);
  }
  
  private void calculateRelativeColumnSize(final int column, final int relativeWidth) {
    columnSize[column] =
    (int) (columnSpec[column] * relativeWidth + 0.5);
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
  
  private void calculateAbsoluteRowSize(final int row) {
    rowSize[row] = (int) (rowSpec[row] + 0.5);
  }
  
  private boolean columnHasAbsoluteSize(final int column) {
    return (columnSpec[column] >= 1.0) || (columnSpec[column] == 0.0);
  }
  
  private boolean rowHasAbsoluteSize(final int row) {
    return (rowSpec[row] >= 1.0) || (rowSpec[row] == 0.0);
  }
  
  private int calculateAbsoluteColumnSize(final int column) {
    return columnSize[column] = (int) (columnSpec[column] + 0.5);
  }
  
  
  
  //******************************************************************************
  //** java.awt.event.LayoutManager methods                                    ***
  //******************************************************************************
  
  
  
  /**
   * To lay out the specified container using this layout.  This method reshapes
   * the components in the specified target container in order to satisfy the
   * constraints of all components.
   *
   * <p>User code should not have to call this method directly.</p>
   *
   * @param container    container being served by this layout manager
   */
  
  public void layoutContainer(Container container) {
    int x, y; // Coordinates of the currnet component in pixels
    int w, h; // Width and height of the current component in pixels
    
    
    ensureValidity(container);
    
    // Get components
    Component component[] = container.getComponents();
    
    // Layout components
    for (int counter = 0; counter < component.length; counter++) {
      try {
        // Get the entry entry for the next component
        ListIterator iterator = list.listIterator(0);
        Entry entry = null;
        
        while (iterator.hasNext()) {
          entry = (Entry) iterator.next();
          
          if (entry.component == component[counter])
            break;
          else
            entry = null;
        }
        
        // Skip any components that have not been place in a specific cell
        if (entry == null)
          break;
        
        // Does the entry occupy a single cell
        if (entry.singleCell) {
          // The following block of code has been optimized so that the
          // preferred size of the component is only obtained if it is
          // needed.  There are components in which the getPreferredSize
          // method is extremely expensive, such as data driven controls
          // with a large amount of data.
          
          // Get the preferred size of the component
          int preferredWidth = 0;
          int preferredHeight = 0;
          
          if ((entry.getHAlign() != FULL) || (entry.getVAlign() != FULL)) {
            Dimension preferredSize =
            component[counter].getPreferredSize();
            
            preferredWidth = preferredSize.width;
            preferredHeight = preferredSize.height;
          }
          
          // Determine cell width and height
          int cellWidth = columnSize[entry.getCol1()];
          int cellHeight = rowSize[entry.getRow1()];
          
          // Determine the width of the component
          if ((entry.getHAlign() == FULL) ||
          (cellWidth < preferredWidth))
            // Use the width of the cell
            w = cellWidth;
          else
            // Use the prefered width of the component
            w = preferredWidth;
          
          // Determine left and right boarders
          switch (entry.getHAlign()) {
            case LEFT :
              // Align left side along left edge of cell
              x = columnOffset[entry.getCol1()];
              break;
              
            case RIGHT :
              // Align right side along right edge of cell
              x = columnOffset[entry.getCol1() + 1] - w;
              break;
              
            case CENTER :
              // Center justify component
              x = columnOffset[entry.getCol1()] + ((cellWidth - w) >> 1);
              break;
              
            case FULL :
              // Align left side along left edge of cell
              x = columnOffset[entry.getCol1()];
              break;
              
            default :
              // This is a never should happen case, but just in case
              x = 0;
          }
          
          // Determine the height of the component
          if ((entry.getVAlign() == FULL) ||
          (cellHeight < preferredHeight))
            // Use the height of the cell
            h = cellHeight;
          else
            // Use the prefered height of the component
            h = preferredHeight;
          
          // Determine top and bottom boarders
          switch (entry.getVAlign()) {
            case TOP :
              // Align top side along top edge of cell
              y = rowOffset[entry.getRow1()];
              break;
              
            case BOTTOM :
              // Align right side along right edge of cell
              y = rowOffset[entry.getRow1() + 1] - h;
              break;
              
            case CENTER :
              // Center justify component
              y = rowOffset[entry.getRow1()] + ((cellHeight - h) >> 1);
              break;
              
            case FULL :
              // Align right side along right edge of cell
              y = rowOffset[entry.getRow1()];
              break;
              
            default :
              // This is a never should happen case, but just in case
              y = 0;
          }
        }
        else {
          // Align left side with left boarder of first column
          x = columnOffset[entry.getCol1()];
          
          // Align top side along top edge of first row
          y = rowOffset[entry.getRow1()];
          
          // Align right side with right boarder of second column
          w = columnOffset[entry.getCol2() + 1] -
          columnOffset[entry.getCol1()];
          
          // Align bottom side with bottom boarder of second row
          h = rowOffset[entry.getRow2() + 1] - rowOffset[entry.getRow1()];
        }
        
        // Move and resize component
        component[counter].setBounds(x, y, w, h);
      }
      catch (Exception error) {
        // If any error occurs, skip this component
        continue;
      }
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
  
  
  
  /**
   * Adds the specified component with the specified name to the layout.
   *
   * @param name         indicates entry's position and anchor
   * @param component    component to add
   */
  
  public void addLayoutComponent(String name, Component component) {
    addLayoutComponent(component, name);
  }
  
  
  
  //******************************************************************************
  //** java.awt.event.LayoutManager2 methods                                   ***
  //******************************************************************************
  
  
  
  /**
   * Adds the specified component with the specified name to the layout.
   *
   * @param component    component to add
   * @param constraint   indicates entry's position and alignment
   */
  
  public void addLayoutComponent(Component component, Object constraint) {
    if (constraint instanceof String) {
      // Create an entry to associate component with its constraints
      constraint = new TableLayoutConstraints((String) constraint);
      
      // Add component and constraints to the list
      list.add(new Entry(component, (TableLayoutConstraints) constraint));
    }
    else if (constraint instanceof TableLayoutConstraints) {
      // Add component and constraints to the list
      list.add(new Entry(component, (TableLayoutConstraints) constraint));
    }
    else if (constraint == null)
      throw new IllegalArgumentException("No constraint for the component");
    else
      throw new IllegalArgumentException
      ("Cannot accept a constraint of class " + constraint.getClass());
  }
  
  
  
  /**
   * Removes the specified component from the layout.
   *
   * @param component    component being removed
   */
  
  public void removeLayoutComponent(Component component) {
    list.remove(component);
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  //******************************************************************************
  //*** Inner Class                                                            ***
  //******************************************************************************
  
  
  
  // The following inner class is used to bind components to their constraints
  protected class Entry extends TableLayoutConstraints {
    /** Component bound by the constraints */
    protected Component component;
    
    /** Does the component occupy a single cell */
    protected boolean singleCell;
    
    /**
     * Constructs an Entry that binds a component to a set of constraints.
     *
     * @param component     component being bound
     * @param constranit    constraints being applied
     */
    
    public Entry(Component component, TableLayoutConstraints constraint) {
      super(constraint.getCol1(), constraint.getRow1(),
      constraint.getCol2(), constraint.getRow2(),
      constraint.getHAlign(), constraint.getVAlign());
      
      singleCell = ((getRow1() == getRow2()) && (getCol1() == getCol2()));
      this.component = component;
    }
    
    /**
     * Determines whether or not two entries are equal.
     *
     * @param object    object being compared to; must be a Component if it
     *                  is equal to this TableLayoutConstraints.
     *
     * @return    True, if the entries refer to the same component object.
     *            False, otherwise.
     */
    
    public boolean equals(Object object) {
      boolean equal = false;
      
      if (object instanceof Component) {
        Component component = (Component) object;
        equal = (this.component == component);
      }
      
      return equal;
    }
  }
}
