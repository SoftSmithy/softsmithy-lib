package org.softsmithy.lib.swing.table;

import org.softsmithy.lib.swing.*;

public class TextFieldTableCellRenderer extends PanelTableCellRenderer{
  
  private JXTextField textfield = new JXTextField("");
  
  public TextFieldTableCellRenderer(){
    textfield.setEditable(false);
    setRenderer(textfield);
    textfield.setStyle(textfield.getParentStyle());
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
  public void setValue(Object value) {
    textfield.setText((value == null) ? "" : value.toString());
  }
 
  public String getText(){
    return textfield.getText();
  }
  
}


