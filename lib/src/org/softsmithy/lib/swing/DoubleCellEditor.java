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
 * IntegerCellEditor.java
 *
 * Created on 7. Oktober 2002, 16:21
 */

package org.softsmithy.lib.swing;

import java.text.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class DoubleCellEditor extends FormattedCellEditor {
  
  /** Creates a new instance of IntegerCellEditor */
  public DoubleCellEditor(Locale locale) {
    super(NumberFormat.getInstance(locale), JTextField.RIGHT);
  }
  
  /** Returns the value contained in the editor.
   * @return the value contained in the editor
   *
   *
   */
  public Object getCellEditorValue() {
    System.out.println(super.getCellEditorValue().getClass());
    System.out.println(super.getCellEditorValue());
    Number number = (Number) super.getCellEditorValue();
    return new Double(number.doubleValue());
  }
  
}