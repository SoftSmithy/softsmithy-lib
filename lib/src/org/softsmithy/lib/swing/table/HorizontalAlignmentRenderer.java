/*
 * HorizontalAlignmentCellRenderer.java
 *
 * Created on 7. Oktober 2002, 22:32
 */

package puce.swing.table;

import java.util.*;
import javax.swing.table.*;
import puce.swing.*;


/**
 *
 * @author  puce
 */
public class HorizontalAlignmentRenderer extends DefaultTableCellRenderer{
  
  /** Holds value of property locale. */
  private Locale locale;
  
  /** Creates a new instance of HorizontalAlignmentCellRenderer */
  public HorizontalAlignmentRenderer(Locale locale) {
    this.locale = locale;
  }

  /** Sets the <code>String</code> object for the cell being rendered to
   * <code>value</code>.
   *
   * @param value  the string value for this cell; if value is
   * 		<code>null</code> it sets the text value to an empty string
   * @see JLabel#setText
   *
   *
   */
  protected void setValue(Object value) {
    super.setValue(((HorizontalAlignment) value).toString(getLocale()));
  }  
  
  /** Getter for property locale.
   * @return Value of property locale.
   *
   */
  public Locale getLocale() {
    return this.locale;
  }
  
  /** Setter for property locale.
   * @param locale New value of property locale.
   *
   */
  public void setLocale(Locale locale) {
    this.locale = locale;
  }
  
}
