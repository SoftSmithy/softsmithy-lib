/*
 * LeadingCellRenderer.java
 *
 * Created on 11. September 2003, 19:51
 */

package org.softsmithy.lib.swing;

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
  
}
