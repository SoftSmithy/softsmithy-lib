/*
 * HorizontalAlignmentCellEditor.java
 *
 * Created on 7. Oktober 2002, 22:14
 */

package puce.swing;

import java.util.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class HorizontalAlignmentCellEditor extends DefaultCellEditor {
  
  /** Holds value of property locale. */
  private Locale locale;
  
  /** Creates a new instance of HorizontalAlignmentCellEditor */
  public HorizontalAlignmentCellEditor(Locale locale) {
    super(new JComboBox());
    JComboBox comboBox = (JComboBox) this.getComponent();
    Vector horizontalAlignments = new Vector();
    for (Iterator i=HorizontalAlignment.VALUES.iterator(); i.hasNext();){
      horizontalAlignments.add(new HorizontalAlignmentItem((HorizontalAlignment) i.next()));
    }
    comboBox.setModel(new DefaultComboBoxModel(horizontalAlignments));
    setLocale(locale);
  }
  
  /** Forwards the message from the <code>CellEditor</code> to
   * the <code>delegate</code>.
   * @see EditorDelegate#getCellEditorValue
   *
   */
  public Object getCellEditorValue() {
    return ((HorizontalAlignmentItem) super.getCellEditorValue()).getHorizontalAlignment();
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
  
  private class HorizontalAlignmentItem{
    
    /** Holds value of property fontStyle. */
    private HorizontalAlignment horizontalAlignment;
    
    public HorizontalAlignmentItem(HorizontalAlignment horizontalAlignment){
      this.horizontalAlignment = horizontalAlignment;
    }
    
    public HorizontalAlignment getHorizontalAlignment() {
      return this.horizontalAlignment;
    }
    
    /** Returns a string representation of the object. In general, the
     * <code>toString</code> method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The <code>toString</code> method for class <code>Object</code>
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `<code>@</code>', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return  a string representation of the object.
     *
     */
    public String toString() {
      return horizontalAlignment.toString(getLocale());
    }
    
  }
}
