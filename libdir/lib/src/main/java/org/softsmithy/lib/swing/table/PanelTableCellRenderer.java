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

/*Adapted from javax.swing.table.DefaultTableCellRenderer*/
/*
 * @(#)DefaultTableCellRenderer.java	1.31 01/12/03
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package org.softsmithy.lib.swing.table;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;




/**
 * The standard class for rendering (displaying) individual cells
 * in a <code>JTable</code>.
 * <p>
 *
 * <strong><a name="override">Implementation Note:</a></strong>
 * This class inherits from <code>JLabel</code>, a standard component class.
 * However <code>JTable</code> employs a unique mechanism for rendering
 * its cells and therefore requires some slightly modified behavior
 * from its cell renderer.
 * The table class defines a single cell renderer and uses it as a
 * as a rubber-stamp for rendering all cells in the table;
 * it renders the first cell,
 * changes the contents of that cell renderer,
 * shifts the origin to the new location, re-draws it, and so on.
 * The standard <code>JLabel</code> component was not
 * designed to be used this way and we want to avoid
 * triggering a <code>revalidate</code> each time the
 * cell is drawn. This would greatly decrease performance because the
 * <code>revalidate</code> message would be
 * passed up the hierarchy of the container to determine whether any other
 * components would be affected.  So this class
 * overrides the <code>validate</code>, <code>revalidate</code>,
 * <code>repaint</code>, and <code>firePropertyChange</code> methods to be
 * no-ops.  If you write your own renderer,
 * please keep this performance consideration in mind.
 * <p>
 *
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running
 * the same version of Swing.  As of 1.4, support for long term storage
 * of all JavaBeans<sup><font size="-2">TM</font></sup>
 * has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @version 1.31 12/03/01
 * @author Philip Milne
 * @see JTable
 */
public abstract class PanelTableCellRenderer extends JPanel implements Serializable, TableCellRenderer {
  
  protected static Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);
  
  // We need a place to store the color the JLabel should be returned
  // to after its foreground and background colors have been set
  // to the selection background color.
  // These ivars will be made protected when their names are finalized.
  private Color unselectedForeground;
  private Color unselectedBackground;
  
  /**
   * Creates a default table cell renderer.
   */
  public PanelTableCellRenderer() {
    super();
    setLayout(new BorderLayout());
    setOpaque(true);
    setBorder(noFocusBorder);
  }
  
  /**
   * Overrides <code>JComponent.setForeground</code> to assign
   * the unselected-foreground color to the specified color.
   *
   * @param c set the foreground color to this value
   */
  @Override
  public void setForeground(Color c) {
    super.setForeground(c);
    unselectedForeground = c;
  }
  
  /**
   * Overrides <code>JComponent.setBackground</code> to assign
   * the unselected-background color to the specified color.
   *
   * @param c set the background color to this value
   */
  @Override
  public void setBackground(Color c) {
    super.setBackground(c);
    unselectedBackground = c;
  }
  
  /**
   * Notification from the <code>UIManager</code> that the look and feel
   * [L&F] has changed.
   * Replaces the current UI object with the latest version from the
   * <code>UIManager</code>.
   *
   * @see JComponent#updateUI
   */
  @Override
  public void updateUI() {
    super.updateUI();
    setForeground(null);
    setBackground(null);
  }
  
  // implements javax.swing.table.TableCellRenderer
  /**
   *
   * Returns the default table cell renderer.
   *
   * @param table  the <code>JTable</code>
   * @param value  the value to assign to the cell at
   *			<code>[row, column]</code>
   * @param isSelected true if cell is selected
   * @param isFocus true if cell has focus
   * @param row  the row of the cell to render
   * @param column the column of the cell to render
   * @return the default table cell renderer
   */
  public Component getTableCellRendererComponent(JTable table, Object value,
  boolean isSelected, boolean hasFocus, int row, int column) {
    
    if (isSelected) {
      super.setForeground(table.getSelectionForeground());
      super.setBackground(table.getSelectionBackground());
    }
    else {
      super.setForeground((unselectedForeground != null) ? unselectedForeground
      : table.getForeground());
      super.setBackground((unselectedBackground != null) ? unselectedBackground
      : table.getBackground());
    }
    
    setFont(table.getFont());
    
    if (hasFocus) {
      setBorder( UIManager.getBorder("Table.focusCellHighlightBorder") );
      if (table.isCellEditable(row, column)) {
        super.setForeground( UIManager.getColor("Table.focusCellForeground") );
        super.setBackground( UIManager.getColor("Table.focusCellBackground") );
      }
    } else {
      setBorder(noFocusBorder);
    }
    
    setValue(value);
    
    return this;
  }
  
    /*
     * The following methods are overridden as a performance measure to
     * to prune code-paths are often called in the case of renders
     * but which we know are unnecessary.  Great care should be taken
     * when writing your own renderer to weigh the benefits and
     * drawbacks of overriding methods like these.
     */
  
  /**
   * Overridden for performance reasons.
   * See the <a href="#override">Implementation Note</a>
   * for more information.
   */
  @Override
  public boolean isOpaque() {
    Color back = getBackground();
    Component p = getParent();
    if (p != null) {
      p = p.getParent();
    }
    // p should now be the JTable.
    boolean colorMatch = (back != null) && (p != null) &&
    back.equals(p.getBackground()) &&
    p.isOpaque();
    return !colorMatch && super.isOpaque();
  }
  
  /**
   * Overridden for performance reasons.
   * See the <a href="#override">Implementation Note</a>
   * for more information.
   */
  @Override
  public void validate() {}
  
  /**
   * Overridden for performance reasons.
   * See the <a href="#override">Implementation Note</a>
   * for more information.
   */
  @Override
  public void revalidate() {}
  
  /**
   * Overridden for performance reasons.
   * See the <a href="#override">Implementation Note</a>
   * for more information.
   */
  @Override
  public void repaint(long tm, int x, int y, int width, int height) {}
  
  /**
   * Overridden for performance reasons.
   * See the <a href="#override">Implementation Note</a>
   * for more information.
   */
  @Override
  public void repaint(Rectangle r) { }
  
  /**
   * Overridden for performance reasons.
   * See the <a href="#override">Implementation Note</a>
   * for more information.
   */
  @Override
  protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
    // Strings get interned...
    if (propertyName=="text") {
      super.firePropertyChange(propertyName, oldValue, newValue);
    }
  }
  
  /**
   * Overridden for performance reasons.
   * See the <a href="#override">Implementation Note</a>
   * for more information.
   */
  @Override
  public void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) { }
  
  
  /**
   * Sets the <code>String</code> object for the cell being rendered to
   * <code>value</code>.
   *
   * @param value  the string value for this cell; if value is
   *		<code>null</code> it sets the text value to an empty string
   * @see JLabel#setText
   *
   */
  protected abstract void setValue(Object value);
  //    {
  //	setText((value == null) ? "" : value.toString());
  //    }
  
  public void setRenderer(JComponent component){
    removeAll();
    add(component, BorderLayout.CENTER);
  }
  
  /**
   * A subclass of <code>DefaultTableCellRenderer</code> that
   * implements <code>UIResource</code>.
   * <code>DefaultTableCellRenderer</code> doesn't implement
   * <code>UIResource</code>
   * directly so that applications can safely override the
   * <code>cellRenderer</code> property with
   * <code>DefaultTableCellRenderer</code> subclasses.
   * <p>
   * <strong>Warning:</strong>
   * Serialized objects of this class will not be compatible with
   * future Swing releases. The current serialization support is
   * appropriate for short term storage or RMI between applications running
   * the same version of Swing.  As of 1.4, support for long term storage
   * of all JavaBeans<sup><font size="-2">TM</font></sup>
   * has been added to the <code>java.beans</code> package.
   * Please see {@link java.beans.XMLEncoder}.
   */
  //    public static class UIResource extends PanelTableCellRenderer
  //        implements javax.swing.plaf.UIResource
  //    {
  //    }
  
  
  
  
}


