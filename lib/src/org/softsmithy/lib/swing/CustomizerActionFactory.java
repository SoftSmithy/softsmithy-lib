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

import java.awt.event.*;
import java.beans.*;
import java.lang.reflect.*;
import java.util.*;
import org.softsmithy.lib.beans.*;
import org.softsmithy.lib.swing.customizer.*;

/**
 *
 * @author  puce
 */
public class CustomizerActionFactory {
  
  private SelectionManager selectionManager;
  
  /** Creates a new instance of CustomizerActionFactory */
  public CustomizerActionFactory(SelectionManager selectionManager) {
    this.selectionManager = selectionManager;
  }
  
  public XAction createAlignLeftAction(Locale locale){
    XAction action = null;
    try{
      action = XActions.getAlignLeftAction(this, locale);
    } catch(NoSuchMethodException ex){
      ex.printStackTrace(); // should not happen here
    }
    return action;
  }
  
  public XAction createAlignTopAction(Locale locale){
    XAction action = null;
    try{
      action = XActions.getAlignTopAction(this, locale);
    } catch(NoSuchMethodException ex){
      ex.printStackTrace(); // should not happen here
    }
    return action;
  }
  
  public XAction createAlignRightAction(Locale locale){
    XAction action = null;
    try{
      action = XActions.getAlignRightAction(this, locale);
    } catch(NoSuchMethodException ex){
      ex.printStackTrace(); // should not happen here
    }
    return action;
  }
  
  public XAction createAlignBottomAction(Locale locale){
    XAction action = null;
    try{
      action = XActions.getAlignBottomAction(this, locale);
    } catch(NoSuchMethodException ex){
      ex.printStackTrace(); // should not happen here
    }
    return action;
  }
  
  public XAction createAlignJustifyHorizontalAction(Locale locale){
    XAction action = null;
    try{
      action = XActions.getAlignJustifyHorizontalAction(this, locale);
    } catch(NoSuchMethodException ex){
      ex.printStackTrace(); // should not happen here
    }
    return action;
  }
  
  public XAction createAlignJustifyVerticalAction(Locale locale){
    XAction action = null;
    try{
      action = XActions.getAlignJustifyVerticalAction(this, locale);
    } catch(NoSuchMethodException ex){
      ex.printStackTrace(); // should not happen here
    }
    return action;
  }
  
  public XAction createTextAlignLeftAction(Locale locale){
    XAction action = null;
    try{
      action = XActions.getTextAlignLeftAction(this, locale);
    } catch(NoSuchMethodException ex){
      ex.printStackTrace(); // should not happen here
    }
    return action;
  }
  
  public XAction createTextAlignCenterAction(Locale locale){
    XAction action = null;
    try{
      action = XActions.getTextAlignCenterAction(this, locale);
    } catch(NoSuchMethodException ex){
      ex.printStackTrace(); // should not happen here
    }
    return action;
  }
  
  public XAction createTextAlignRightAction(Locale locale){
    XAction action = null;
    try{
      action = XActions.getTextAlignRightAction(this, locale);
    } catch(NoSuchMethodException ex){
      ex.printStackTrace(); // should not happen here
    }
    return action;
  }
  
  public XAction createTextBoldAction(Locale locale){
    XAction action = null;
    try{
      action = XActions.getTextBoldAction(this, locale);
    } catch(NoSuchMethodException ex){
      ex.printStackTrace(); // should not happen here
    }
    return action;
  }
  
  public XAction createTextItalicAction(Locale locale){
    XAction action = null;
    try{
      action = XActions.getTextItalicAction(this, locale);
    } catch(NoSuchMethodException ex){
      ex.printStackTrace(); // should not happen here
    }
    return action;
  }
  
  public void alignLeft(ActionEvent e){
    JCustomizer[] customizers = selectionManager.getSelectedCustomizers();
    if (customizers.length > 0){
      JCustomizer focusCustomizer = customizers[customizers.length-1];
      for (int i=0; i<customizers.length-1; i++){
        customizers[i].setX(focusCustomizer.getX());
      }
    }
  }
  
  public void alignTop(ActionEvent e){
    JCustomizer[] customizers = selectionManager.getSelectedCustomizers();
    if (customizers.length > 0){
      JCustomizer focusCustomizer = customizers[customizers.length-1];
      for (int i=0; i<customizers.length-1; i++){
        customizers[i].setY(focusCustomizer.getY());
      }
    }
  }
  
  public void alignRight(ActionEvent e){
    JCustomizer[] customizers = selectionManager.getSelectedCustomizers();
    if (customizers.length > 0){
      JCustomizer focusCustomizer = customizers[customizers.length-1];
      for (int i=0; i<customizers.length-1; i++){
        customizers[i].setX(focusCustomizer.getX()+focusCustomizer.getWidth()-customizers[i].getWidth());
      }
    }
  }
  
  public void alignBottom(ActionEvent e){
    JCustomizer[] customizers = selectionManager.getSelectedCustomizers();
    if (customizers.length > 0){
      JCustomizer focusCustomizer = customizers[customizers.length-1];
      for (int i=0; i<customizers.length-1; i++){
        customizers[i].setY(focusCustomizer.getY()+focusCustomizer.getHeight()-customizers[i].getHeight());
      }
    }
  }
  
  public void alignJustifyHorizontal(ActionEvent e){
    JCustomizer[] customizers = selectionManager.getSelectedCustomizers();
    if (customizers.length > 0){
      JCustomizer focusCustomizer = customizers[customizers.length-1];
      for (int i=0; i<customizers.length-1; i++){
        customizers[i].setWidth(focusCustomizer.getWidth());
      }
    }
  }
  
  public void alignJustifyVertical(ActionEvent e){
    JCustomizer[] customizers = selectionManager.getSelectedCustomizers();
    if (customizers.length > 0){
      JCustomizer activeCustomizer = customizers[customizers.length-1];
      for (int i=0; i<customizers.length-1; i++){
        customizers[i].setHeight(activeCustomizer.getHeight());
      }
    }
  }
  
  public void textAlignLeft(ActionEvent e){
    System.out.println("textAlignLeft start");
    JCustomizer[] customizers = selectionManager.getSelectedCustomizers();
    for (int i=0; i<customizers.length; i++){
      try{
        BeanIntrospector.setPropertyValue("horizontalAlignment", HorizontalAlignment.LEFT, customizers[i], null);
      } catch(IntrospectionException ex1){
        ex1.printStackTrace();
      } catch(IllegalAccessException ex2){
        ex2.printStackTrace();
      } catch(InvocationTargetException ex3){
        ex3.printStackTrace();
      }
    }
  }
  
  public void textAlignCenter(ActionEvent e){
    System.out.println("textAlignCenter start");
    JCustomizer[] customizers = selectionManager.getSelectedCustomizers();
    for (int i=0; i<customizers.length; i++){
      try{
        BeanIntrospector.setPropertyValue("horizontalAlignment", HorizontalAlignment.CENTER, customizers[i], null);
      } catch(IntrospectionException ex1){
        ex1.printStackTrace();
      } catch(IllegalAccessException ex2){
        ex2.printStackTrace();
      } catch(InvocationTargetException ex3){
        ex3.printStackTrace();
      }
    }
  }
  
  public void textAlignRight(ActionEvent e){
    System.out.println("textAlignRight start");
    JCustomizer[] customizers = selectionManager.getSelectedCustomizers();
    for (int i=0; i<customizers.length; i++){
      try{
        BeanIntrospector.setPropertyValue("horizontalAlignment", HorizontalAlignment.RIGHT, customizers[i], null);
      } catch(IntrospectionException ex1){
        ex1.printStackTrace();
      } catch(IllegalAccessException ex2){
        ex2.printStackTrace();
      } catch(InvocationTargetException ex3){
        ex3.printStackTrace();
      }
    }
  }
  
  public void textBold(ActionEvent e){
  }
  
  public void textItalic(ActionEvent e){
  }
  
}
