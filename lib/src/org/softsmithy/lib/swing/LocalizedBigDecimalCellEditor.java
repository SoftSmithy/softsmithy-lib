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
 * LocalizedRealNumberCellEditor.java
 *
 * Created on 7. Oktober 2002, 16:21
 */

package org.softsmithy.lib.swing;

import java.math.*;
import java.util.*;

/**
 *
 * @author  puce
 */
public class LocalizedBigDecimalCellEditor extends FormattedCellEditor {
  
  /** Creates a new instance of LocalizedRealNumberCellEditor */
  public LocalizedBigDecimalCellEditor(Locale locale) {
    super(new JLocalizedRealNumberField(locale));
  }
  
  public LocalizedBigDecimalCellEditor(BigDecimal minValue, BigDecimal maxValue, Locale locale) {
    super(new JLocalizedRealNumberField(minValue, maxValue, locale));
  }
  
  public JLocalizedRealNumberField getLocalizedRealNumberField(){
    return (JLocalizedRealNumberField) getFormattedTextField();
  }
  
  /** Returns the value contained in the editor.
   * @return the value contained in the editor
   *
   *
   */
//  public Object getCellEditorValue() {
//    System.out.println(super.getCellEditorValue().getClass());
//    System.out.println(super.getCellEditorValue());
//    Number number = (Number) super.getCellEditorValue(); //sometimes an Integer is returned, sometimes a Long???
//    return new Integer(number.intValue());
//  }
  
  protected void setValue(Object value) {
    getLocalizedRealNumberField().setBigDecimalValue((BigDecimal) value);
  }
  
  protected Object getValue() {
    return getLocalizedRealNumberField().getBigDecimalValue();
  }
  
}
