/*
 * IntegerCellEditor.java
 *
 * Created on 7. Oktober 2002, 16:21
 */

package puce.swing;

import java.text.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class IntegerCellEditor extends FormattedCellEditor {
  
  /** Creates a new instance of IntegerCellEditor */
  public IntegerCellEditor(Locale locale) {
    super(NumberFormat.getIntegerInstance(locale), JTextField.RIGHT);
  }
  
  /** Returns the value contained in the editor.
   * @return the value contained in the editor
   *
   *
   */
  public Object getCellEditorValue() {
    System.out.println(super.getCellEditorValue().getClass());
    System.out.println(super.getCellEditorValue());
    Number number = (Number) super.getCellEditorValue(); //sometimes an Integer is returned, sometimes a Long???
    return new Integer(number.intValue());
  }
  
}
