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
 * LeadingCellRenderer.java
 *
 * Created on 11. September 2003, 19:51
 */

package org.softsmithy.lib.swing;

import javax.swing.Icon;

/**
 *
 * @author  puce
 */
public abstract class AbstractCellRenderer implements CellRenderer {
  
  /** Holds value of property horizontalAlignment. */
  private HorizontalAlignment horizontalAlignment;
  
  /** Creates a new instance of LeadingCellRenderer */
  public AbstractCellRenderer(HorizontalAlignment horizontalAlignment) {
    this.horizontalAlignment = horizontalAlignment;
  }
  
  /** Getter for property horizontalAlignment.
   * @return Value of property horizontalAlignment.
   *
   */
  public HorizontalAlignment getHorizontalAlignment() {
    return this.horizontalAlignment;
  }  

  /** Setter for property horizontalAlignment.
   * @param horizontalAlignment New value of property horizontalAlignment.
   *
   */
  public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
    this.horizontalAlignment = horizontalAlignment;
  }

    public Icon getIcon(Object value) {
        return null;
    }
  
  
  
}
