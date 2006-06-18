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

 * FormattedEditor.java

 *

 * Created on 7. Oktober 2002, 14:48

 */



package org.softsmithy.lib.swing;



import java.awt.*;

import java.text.*;

import javax.swing.*;

import javax.swing.table.*;



/**

 *

 * @author  puce

 */

public class FormattedCellEditor extends DefaultCellEditor implements TableCellEditor {

  

  /** Creates a new instance of FormattedEditor */

  public FormattedCellEditor(Format format) {

    this(format, new JFormattedTextField().getHorizontalAlignment());

  }

  

  public FormattedCellEditor(Format format, int horizontalAlignment) {

    this(new JFormattedTextField(format));

    getFormattedTextField().setHorizontalAlignment(horizontalAlignment);

  }

  

  public FormattedCellEditor(JFormattedTextField ftf){

    super(ftf);

  }

  

  public JFormattedTextField getFormattedTextField(){

    return (JFormattedTextField) getComponent();

  }

  

  

  /** Returns the value contained in the editor.

   * @return the value contained in the editor

   *

   */

  public Object getCellEditorValue() {

    System.out.println(getFormattedTextField().getValue().getClass());

    System.out.println(getFormattedTextField().getValue());

    return getValue();

  }

  

  /**  Sets an initial <code>value</code> for the editor.  This will cause

   *  the editor to <code>stopEditing</code> and lose any partially

   *  edited value if the editor is editing when this method is called. <p>

   *

   *  Returns the component that should be added to the client's

   *  <code>Component</code> hierarchy.  Once installed in the client's

   *  hierarchy this component will then be able to draw and receive

   *  user input.

   *

   * @param	table		the <code>JTable</code> that is asking the

   * 				editor to edit; can be <code>null</code>

   * @param	value		the value of the cell to be edited; it is

   * 				up to the specific editor to interpret

   * 				and draw the value.  For example, if value is

   * 				the string "true", it could be rendered as a

   * 				string or it could be rendered as a check

   * 				box that is checked.  <code>null</code>

   * 				is a valid value

   * @param	isSelected	true if the cell is to be rendered with

   * 				highlighting

   * @param	row     	the row of the cell being edited

   * @param	column  	the column of the cell being edited

   * @return	the component for editing

   *

   */

  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

    setValue(value);

    return getFormattedTextField();

  }

  

  protected void setValue(Object value){

    getFormattedTextField().setValue(value);

  }

  

  protected Object getValue(){

    return getFormattedTextField().getValue();

  }

  

}

