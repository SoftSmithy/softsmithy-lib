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

package org.softsmithy.lib.swing;

import java.awt.Component;
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.util.*;

/**
 *
 * @author  puce
 */
public class TypesafeEnumCellEditor extends DefaultCellEditor {
  
  /** Holds value of property locale. */
  private Locale locale;
  
  /** Creates a new instance of HorizontalAlignmentCellEditor */
  public TypesafeEnumCellEditor(Locale locale) {
    super(new JTypesafeEnumComboBox());
    setLocale(locale);
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
    getComponent().setLocale(locale);
  }
  
  /** Implements the <code>TableCellEditor</code> interface.  */
  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
    System.out.println("start edit");
    fillComboBox(getClass(table, row, column));
    System.out.println(value.getClass());
    return super.getTableCellEditorComponent(table, value, isSelected, row, column);
  }
  
  protected Class getClass(JTable table, int row, int column){
    if (table instanceof JCellTable){ // should be raplaced with polymorphism
      return ((JCellTable) table).getCellClass(row, column);
    } else {
      return table.getColumnClass(column);
    }
  }
  
  private void fillComboBox(Class typesafeEnumClass){
    JTypesafeEnumComboBox comboBox = (JTypesafeEnumComboBox) this.getComponent();
    comboBox.setModel(new TypesafeEnumComboBoxModel(typesafeEnumClass, getLocale()));
  }
  
}
