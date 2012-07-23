/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

/*
 *
 * Created on 6. November 2002, 12:17
 */

package org.softsmithy.lib.swing.action;

import java.awt.event.*;
import java.beans.*;
import java.lang.reflect.*;
import java.util.*;
import org.softsmithy.lib.beans.*;
import org.softsmithy.lib.swing.*;
import org.softsmithy.lib.swing.customizer.*;

/**
 * The CustomizerActionFactory provides default implementations of some actions
 * which can be usefull when working with customizers. Just use the createXXXAction
 * methods to create the Actions.
 * @author  puce
 */
public class CustomizerActionFactory {
  
  private SelectionManager selectionManager;
  
  /** Creates a new instance of CustomizerActionFactory
   * @param selectionManager some actions work on the current selection
   */
  public CustomizerActionFactory(SelectionManager selectionManager) {
    this.selectionManager = selectionManager;
  }
  
  
  public CustomizerAction createAlignLeftAction(Locale locale){
    return createCustomizerAction(GeneralActionFactory.ALIGN_LEFT, new String[]{"x"}, locale);
  }
  
  private CustomizerAction createCustomizerAction(StandardActionFactory saf, String[] neededCustomizableProperties, Locale locale){
    CustomizerAction action = null;
    try{
      action = new ReflectiveCustomizerAction(this, saf.toString());
      saf.configureXAction(action, locale);
      action.setNeededCustomizableProperties(new HashSet<>(Arrays.asList(neededCustomizableProperties)));
    } catch(NoSuchMethodException ex){
      ex.printStackTrace(); // should not happen here // TODO: exception handling
    }
    return action;
  }
  
  private CustomizerAction createDefaultCustomizerAction(StandardActionFactory saf, String[] neededCustomizableProperties, Locale locale){
    CustomizerAction action = new DefaultCustomizerAction();
    saf.configureXAction(action, locale);
    action.setNeededCustomizableProperties(new HashSet<>(Arrays.asList(neededCustomizableProperties)));
    return action;
  }
  
  public CustomizerAction createAlignTopAction(Locale locale){
    return createCustomizerAction(GeneralActionFactory.ALIGN_TOP, new String[]{"y"}, locale);
  }
  
  public CustomizerAction createAlignRightAction(Locale locale){
    return createCustomizerAction(GeneralActionFactory.ALIGN_RIGHT, new String[]{"x", "width"}, locale);
  }
  
  public CustomizerAction createAlignBottomAction(Locale locale){
    return createCustomizerAction(GeneralActionFactory.ALIGN_BOTTOM, new String[]{"y", "height"}, locale);
  }
  
  public CustomizerAction createAlignJustifyHorizontalAction(Locale locale){
    return createCustomizerAction(GeneralActionFactory.ALIGN_JUSTIFY_HORIZONTAL, new String[]{"width"}, locale);
  }
  
  public CustomizerAction createAlignJustifyVerticalAction(Locale locale){
    return createCustomizerAction(GeneralActionFactory.ALIGN_JUSTIFY_VERTICAL, new String[]{"height"}, locale);
  }
  
  public CustomizerAction createTextAlignLeftAction(Locale locale){
    return createCustomizerAction(TextActionFactory.ALIGN_LEFT, new String[]{"horizontalAlignment"}, locale);
  }
  
  public CustomizerAction createTextAlignCenterAction(Locale locale){
    return createCustomizerAction(TextActionFactory.ALIGN_CENTER, new String[]{"horizontalAlignment"}, locale);
  }
  
  public CustomizerAction createTextAlignRightAction(Locale locale){
    return createCustomizerAction(TextActionFactory.ALIGN_RIGHT, new String[]{"horizontalAlignment"}, locale);
  }
  
  public CustomizerAction createTextBoldAction(Locale locale){
    return createDefaultCustomizerAction(TextActionFactory.BOLD, new String[]{"font"}, locale);
  }
  
  public CustomizerAction createTextItalicAction(Locale locale){
    return createDefaultCustomizerAction(TextActionFactory.ITALIC, new String[]{"font"}, locale);
  }
  
  public void alignLeft(ActionEvent e){
    alignHorizontal(HorizontalAlignment.LEFT, selectionManager.getActiveCustomizer().getX());
  }
  
  private void alignHorizontal(HorizontalAlignment ha, int position){
    Set<JCustomizer> customizers = new HashSet<>(Arrays.asList(selectionManager.getSelectedCustomizers()));
    customizers.remove(selectionManager.getActiveCustomizer());
    if (customizers.size() > 0){
      ha.alignCustomizers(customizers, position,
      selectionManager.getActiveCustomizer().getComponentOrientation());
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
    alignHorizontal(HorizontalAlignment.RIGHT,
    selectionManager.getActiveCustomizer().getX()
    + selectionManager.getActiveCustomizer().getWidth());
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
    //System.out.println("textAlignLeft start");
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
    //System.out.println("textAlignCenter start");
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
    //System.out.println("textAlignRight start");
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
  
  
}
