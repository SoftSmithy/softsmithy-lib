/*
 * ColorCellEditor.java
 *
 * Created on 27. September 2002, 11:25
 */

package org.softsmithy.lib.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import org.softsmithy.lib.swing.chooser.*;

/**
 *
 * @author  puce
 */
public class FontCellEditor extends AbstractCellEditor implements TableCellEditor {
  
  private FontCellRenderer renderer;
  private JButton button;
  private JPanel editor;
  private Font font = null;
  private JFontChooser fontChooser = new JFontChooser();
  /** Creates a new instance of ColorCellEditor */
  public FontCellEditor() {
    renderer = new FontCellRenderer(true);
    button = new JButton(". . ."){
      public Dimension getPreferredSize(){
        Dimension dim = super.getPreferredSize();
        dim.width = getFontMetrics(getFont()).stringWidth(getText());
        return dim;
      }
    };
    button.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        fontChooser.selectFont(getFont());
        Option option = fontChooser.showDialog(editor.getParent());
        if (option.equals(Option.APPROVE)){
          setFont(fontChooser.getSelectedFont());
          stopCellEditing();
          System.out.println("Stop");
        } else {
          cancelCellEditing();
          System.out.println("Cancel");
        }
      }
    });
    //button.setPreferredSize(button.getMinimumSize());
    editor = new JPanel();
    editor.setLayout(new BorderLayout());
    editor.add(BorderLayout.EAST, button);
    editor.add(BorderLayout.CENTER, renderer);
    editor.setOpaque(false);
  }
  
  /** Returns the value contained in the editor.
   * @return the value contained in the editor
   *
   */
  public Object getCellEditorValue() {
    return font;
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
    font = (Font) value;
    renderer.setSelectedFont(font);//(Color) value);
    
    //    Color color = JColorChooser.showDialog(editor, "Choose Color", (Color) value);
    //    if (color != null){
    //      this.color = color;
    //      stopCellEditing();
    //      System.out.println("Stop");
    //    } else {
    //      cancelCellEditing();
    //      System.out.println("Cancel");
    //    }
    return editor;
    
  }
  
  /** Getter for property color.
   * @return Value of property color.
   *
   */
  public Font getFont() {
    return font;
  }
  
  /** Setter for property color.
   * @param color New value of property color.
   *
   */
  public void setFont(Font font) {
    this.font = font;
  }
  
}
