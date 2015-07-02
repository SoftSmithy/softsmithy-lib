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
 * XDefaultListCellRenderer.java
 *
 * Created on 5. März 2003, 16:12
 */

package org.softsmithy.lib.swing;

import java.awt.*;
import javax.swing.*;

/**
 * This is an adapter class to use a CellRenderer as a ListCellRenderer.
 * @author puce
 * @see CellRenderer
 * @see javax.swing.ListCellRenderer
 */
public class XDefaultListCellRenderer extends DefaultListCellRenderer {
  
  /** Holds value of property cellRenderer. */
  private CellRenderer cellRenderer;  
  
  /**
     * Creates a new instance of XDefaultListCellRenderer
     * @param cellRenderer the CellRenderer delegate
     */
  public XDefaultListCellRenderer(CellRenderer cellRenderer) {
    this.cellRenderer = cellRenderer;
  }
  
    /**
     * Configures this ListCellRenderer from the JList. <br>
     * <br>
     * By default sets the Locale of this renderer to the Locale of the 
     * JList.
     * @param list the parent JList
     */
  protected void configureFromList(JList list){
    setLocale(list.getLocale());
  }
  
    /**
     * Delegates the generation of the display value to the CellRenderer.<br>
     * <br>
     * Note: Should this method be private instead of protected?
     * @param value the source value
     * @return the display value
     */
  protected Object getDisplayValue(Object value){
    return cellRenderer.getDisplayValue(value, getLocale());
  }
  

    @Override
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    configureFromList(list);
    super.getListCellRendererComponent(list, getDisplayValue(value), index, isSelected, cellHasFocus);
    setIcon(cellRenderer.getIcon(value));
    return this;
  }
  
  /**
     * Gets the CellRenderer delegate.
     * @return the CellRenderer delegate
     */
  public CellRenderer getCellRenderer() {
    return this.cellRenderer;
  }
  
}
