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
 * CustomizerActionFactory.java
 *
 * Created on 6. November 2002, 12:17
 */

package org.softsmithy.lib.swing;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import org.softsmithy.lib.awt.event.*;
import org.softsmithy.lib.swing.customizer.*;


/**
 *
 * @author  puce
 */
public class CustomizerItemListenerProvider {
  
  private SelectionManager selectionManager;
  private ItemListener textBold = null;
  private ItemListener textItalic = null;
  
  /** Creates a new instance of CustomizerActionFactory */
  public CustomizerItemListenerProvider(SelectionManager selectionManager) {
    this.selectionManager = selectionManager;
  }
  
  public ItemListener getTextBoldItemListener(){
    if (textBold == null){
      try{
        textBold = new CustomizerItemListener(this, "textBold", "font");
      } catch(NoSuchMethodException ex){
        ex.printStackTrace(); // should not happen here
      }
    }
    return textBold;
  }
  
  public ItemListener getTextItalicItemListener(){
    if (textItalic == null){
      try{
        textItalic = new CustomizerItemListener(this, "textItalic", "font");
      } catch(NoSuchMethodException ex){
        ex.printStackTrace(); // should not happen here
      }
    }
    return textItalic;
  }
  
  
  public void textBold(ItemEvent e){
    JCustomizer[] customizers = selectionManager.getSelectedCustomizers();
    for (int i=0; i<customizers.length; i++){
      Font font = customizers[i].getFont();
      int style;
      if (e.getStateChange() == ItemEvent.SELECTED){
        style = Font.BOLD;
        if (font.isItalic()){
          style |= Font.ITALIC;
        }
      } else {
        if (font.isItalic()){
          style = Font.ITALIC;
        } else {
          style = Font.PLAIN;
        }
      }
      customizers[i].setFont(font.deriveFont(style));
    }
  }
  
  public void textItalic(ItemEvent e){
    JCustomizer[] customizers = selectionManager.getSelectedCustomizers();
    for (int i=0; i<customizers.length; i++){
      Font font = customizers[i].getFont();
      int style;
      if (e.getStateChange() == ItemEvent.SELECTED){
        style = Font.ITALIC;
        if (font.isBold()){
          style |= Font.BOLD;
        }
      } else {
        if (font.isBold()){
          style = Font.BOLD;
        } else {
          style = Font.PLAIN;
        }
      }
      customizers[i].setFont(font.deriveFont(style));
    }
  }
  
  private class CustomizerItemListener extends ReflectiveItemListener{
    
    /** Holds value of property property. */
    private String propertyName;
    
    public CustomizerItemListener(Object target, String methodName, String propertyName) throws NoSuchMethodException{
      super(target, methodName);
      this.propertyName = propertyName;
    }
    
    /** Getter for property property.
     * @return Value of property property.
     *
     */
    public String getPropertyName() {
      return this.propertyName;
    }
    
    /** Invoked when an item has been selected or deselected by the user.
     * Springs to the method specified in the constructor.
     *
     * @param e  an ActionEvent object
     *
     */
    public void itemStateChanged(ItemEvent e) {
      if (JCustomizer.getCommonCustomizableProperties(Arrays.asList(
      selectionManager.getSelectedCustomizers())).contains(getPropertyName())){
        super.itemStateChanged(e);
      }
    }
    
  }
}
