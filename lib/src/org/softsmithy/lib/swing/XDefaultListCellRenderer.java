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
 * XDefaultListCellRenderer.java
 *
 * Created on 5. März 2003, 16:12
 */

package org.softsmithy.lib.swing;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class XDefaultListCellRenderer extends DefaultListCellRenderer {
  
  /** Holds value of property cellRenderer. */
  private CellRenderer cellRenderer;  
  
  /** Creates a new instance of XDefaultListCellRenderer */
  public XDefaultListCellRenderer(CellRenderer cellRenderer) {
    this.cellRenderer = cellRenderer;
  }
  
  protected void configureFromList(JList list){
    setLocale(list.getLocale());
  }
  
  protected Object getDisplayValue(Object value){
    return cellRenderer.getDisplayValue(value, getLocale());
  }
  
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    configureFromList(list);
    return  super.getListCellRendererComponent(list, getDisplayValue(value), index, isSelected, cellHasFocus);
  }
  
  /** Getter for property cellRenderer.
   * @return Value of property cellRenderer.
   *
   */
  public CellRenderer getCellRenderer() {
    return this.cellRenderer;
  }
  
}
