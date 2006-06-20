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

 * ColorCellEditor.java

 *

 * Created on 27. September 2002, 11:25

 */



package org.softsmithy.lib.swing;



import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import javax.swing.table.*;



/**

 *

 * @author  puce

 */

public class ColorCellEditor extends AbstractCellEditor implements TableCellEditor {

  

  private ColorCellRenderer renderer;

  private JButton button;

  private JPanel editor;

  private Color color = null;

  

  /** Holds value of property alpha. */

  private int alpha;

  

  /** Creates a new instance of ColorCellEditor */

  public ColorCellEditor() {

    this(255);

  }

  

  public ColorCellEditor(int alpha){

    this.alpha = alpha;

    renderer = new ColorCellRenderer(true);

    button = new JButton(". . ."){

      public Dimension getPreferredSize(){

        Dimension dim = super.getPreferredSize();

        dim.width = getFontMetrics(getFont()).stringWidth(getText());

        return dim;

      }

    };

    button.addActionListener(new ActionListener(){

      public void actionPerformed(ActionEvent e){

        Color color = JColorChooser.showDialog(editor.getParent(), "Choose Color", getColor());

        if (color != null){

          setNewColor(color);

          stopCellEditing();

          //System.out.println("Stop");

        } else {

          cancelCellEditing();

          //System.out.println("Cancel");

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

    return getColor();

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

    setColor((Color) value);

    renderer.setColor(getColor());//(Color) value);

    

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

  public Color getColor() {

    return color;

  }

  

  /** Setter for property color.

   * @param color New value of property color.

   *

   */

  public void setColor(Color color) {

    this.color = color;

  }

  

  public void setNewColor(Color newColor){

    if (getAlpha() == 255){

      setColor(newColor);

    } else {

      setColor(new Color(newColor.getRed(), newColor.getGreen(), newColor.getBlue(), getAlpha()));

    }

  }

  

  /** Getter for property alpha.

   * @return Value of property alpha.

   *

   */

  public int getAlpha() {

    return this.alpha;

  }

  

  /** Setter for property alpha.

   * @param alpha New value of property alpha.

   *

   */

  public void setAlpha(int alpha) {

    if (alpha < 0 || alpha > 255){

      throw new IllegalArgumentException("Alpha must be in the range [0, 255].");

    }

    this.alpha = alpha;

  }

  

}

