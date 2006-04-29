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

package org.softsmithy.lib.swing.action;

import java.awt.event.*;
import java.awt.image.*;
import java.beans.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.util.*;
import org.softsmithy.lib.swing.*;


/**
 * A utility class for XActions. <br>
 * Note: The API may change in a future version!
 *
 * @author    Florian Brunner
 * @created   March 8, 2002
 * @see       XAction
 */

public class XActions {
  
  //private static final String STANDARD_ACTIONS_RB_BASE_NAME ="org.softsmithy.lib.swing.StandardActions";
  private static final String STANDARD_MENUS_RB_BASE_NAME = "org.softsmithy.lib.swing.action.StandardMenus";
  private static final Icon LARGE_NULL_ICON = new ImageIcon(new BufferedImage(24,24,BufferedImage.TYPE_INT_ARGB));//(XActions.class).getResource("/org/softsmithy/lib/buttonGraphics/general/null24.gif"));
  private static final Icon SMALL_NULL_ICON = new ImageIcon(new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB));//(XActions.class).getResource("/org/softsmithy/lib/buttonGraphics/general/null16.gif"));
  //  private static final Map MNEMONICS = new HashMap();
  //
  //  static{
  //    KeyEvent.VK_A
  //  }
  
  /**
   * No public constructor!
   */
  private XActions() { }
  
  /**
   * Creates a new XAction from a ResourceBundle.
   *
   * @param name                       the method name (must take an ActionEvent
   *                                   obect as its single parameter) that gets
   *                                   called when an ActionEvent occurs
   * @param target                     he object with the specified method
   * @param rb                         a ResourceBundle (must not specify all
   * Action properties)
   * @return                           a XAction configured by a ResourceBundle
   * @exception NoSuchMethodException  if no such method found
   */
  public static XAction createXAction(String name, Object target, ResourceBundle rb)
  throws NoSuchMethodException {
    XAction action = new ReflectiveXAction(target, name);
    configureXAction(action, name, rb);
    return action;
  }
  
  /**
   * Configures an XAction from a ResourceBundle. This method is looking for the following keys: <br>
   * <br>
   * name.name: the name to be displayed <br>
   * name.shortDescription: Tool Tip <br>
   * name.acceleratorKey: Shortcut <br>
   * name.mnemonicKey: Mnemonic <br>
   * name.largeDisabledIcon: File Name <br>
   * name.largeDisabledSelectedIcon: File Name <br>
   * name.largeIcon: File Name <br>
   * name.largePressedIcon: File Name <br>
   * name.largeRolloverIcon: File Name <br>
   * name.largeRolloverSelectedIcon: File Name <br>
   * name.largeSelectedIcon: File Name <br>
   * name.smallDisabledIcon: File Name <br>
   * name.smallDisabledSelectedIcon: File Name <br>
   * name.smallIcon: File Name <br>
   * name.smallPressedIcon: File Name <br>
   * name.smallRolloverIcon: File Name <br>
   * name.smallRolloverSelectedIcon: File Name <br>
   * name.smallSelectedIcon: File Name <br>
   * <br>
   * E.g. <br>
   * myAction.name = MyAction <br>
   * myAction.shortDescription = MyAction <br>
   * myAction.acceleratorKey = Control M <br>
   * myAction.mnemonicKey = A <br>
   * myAction.largeIcon = /myGraphics/MyAction24.gif <br>
   * myAction.smallIcon = /myGraphics/MyAction16.gif <br>
   */
  public static void configureXAction(XAction action, String name, ResourceBundle rb){
    configureStringProperties(action, name, rb);
    configureIconProperties(action, name, rb);
    configureKeyStrokeProperties(action, name, rb);
    configureMnemonicKeyProperties(action, name, rb);
  }
  
  private static void configureStringProperties(XAction action, String name, ResourceBundle rb){
    String[] string_properties = {Action.NAME, Action.SHORT_DESCRIPTION};
    for (int i = 0; i < string_properties.length; i++) {
      try {
        action.putValue(string_properties[i], rb.getString(name + "." +
        Introspector.decapitalize(string_properties[i])));
      } catch (MissingResourceException ex) {
        //System.out.println("Couln'dt find: " + name + string_properties[i] );
        // ignore it
      }
    }
  }
  
  private static void configureIconProperties(XAction action, String name, ResourceBundle rb){
    String[] icon_properties = {Action.SMALL_ICON,
    XAction.LARGE_DISABLED_ICON,
    XAction.LARGE_DISABLED_SELECTED_ICON,
    XAction.LARGE_ICON,
    XAction.LARGE_PRESSED_ICON,
    XAction.LARGE_ROLLOVER_ICON,
    XAction.LARGE_ROLLOVER_SELECTED_ICON,
    XAction.LARGE_SELECTED_ICON,
    XAction.SMALL_DISABLED_ICON,
    XAction.SMALL_DISABLED_SELECTED_ICON,
    XAction.SMALL_PRESSED_ICON,
    XAction.SMALL_ROLLOVER_ICON,
    XAction.SMALL_ROLLOVER_SELECTED_ICON,
    XAction.SMALL_SELECTED_ICON};
    for (int i = 0; i < icon_properties.length; i++) {
      try {
        URL url = (XActions.class).getResource(rb.getString(name +
        "." + Introspector.decapitalize(icon_properties[i])));
        if (url != null) {
          action.putValue(icon_properties[i], new ImageIcon(url));
        }
      } catch (MissingResourceException ex) {
        //System.out.println("Couln'dt find: " + name + icon_properties[i] );
        // ignore it
      }
    }
  }
  
  private static void configureKeyStrokeProperties(XAction action, String name, ResourceBundle rb){
    String[] keyStrokeProperties = {Action.ACCELERATOR_KEY};
    for (int i = 0; i < keyStrokeProperties.length; i++) {
      try {
        action.putValue(keyStrokeProperties[i], KeyStroke.getKeyStroke(rb.getString(name + "." +
        Introspector.decapitalize(keyStrokeProperties[i]))));
      } catch (MissingResourceException ex) {
        // ignore it
      }
    }
  }
  
  private static void configureMnemonicKeyProperties(XAction action, String name, ResourceBundle rb){
    String[] mnemonicKeyProperties = {Action.MNEMONIC_KEY};
    for (int i = 0; i < mnemonicKeyProperties.length; i++) {
      try {
        String mnemonicKeyString = rb.getString(name + "." +Introspector.decapitalize(mnemonicKeyProperties[i]));
        if (mnemonicKeyString.length() > 0){
          char mnemonicKey = mnemonicKeyString.substring(0,1).toUpperCase().charAt(0);
          if (Character.isUpperCase(mnemonicKey)){ //TODO: Check if this is enough (Uniocde)
            action.setMnemonicKey(mnemonicKey);
          }
        }
      } catch (MissingResourceException ex) {
        // ignore it
      }
    }
  }
  
  /**
   * Creates a configured button.
   *
   * @param action    the XAction with the configuration data
   * @param iconType  the icon type
   * @param showText  if the label text should be shown
   * @return          a configured button
   */
  public static JButton createButton(XAction action, IconType iconType,
  boolean showText, boolean coolStyle) {
    JButton button = new JButton();
    configureButton(button, action, iconType, showText, coolStyle);
    return button;
  }
  
  /**
   * Creates a configured toggle button.
   *
   * @param action    the XAction with the configuration data
   * @param iconType  the icon type
   * @param showText  if the label text should be shown
   * @return          a configured toggle button
   */
  public static JToggleButton createToggleButton(XAction action, ItemListener listener, IconType iconType, boolean showText, boolean coolStyle) {
    JToggleButton button = new JToggleButton();
    configureButton(button, action, listener, iconType, showText, coolStyle);
    return button;
  }
  
  /**
   * Creates a configured toggle button.
   *
   * @param action    the XAction with the configuration data
   * @param iconType  the icon type
   * @param showText  if the label text should be shown
   * @return          a configured toggle button
   */
  public static JToggleButton createToggleButton(XAction action, ButtonGroup group,
  IconType iconType, boolean showText, boolean coolStyle) {
    JToggleButton button = new JToggleButton();
    configureButton(button, action, group, iconType, showText, coolStyle);
    return button;
  }
  
  /**
   * Creates a configured radio button.
   *
   * @param action    the XAction with the configuration data
   * @param iconType  the icon type
   * @param showText  if the label text should be shown
   * @return          a configured radio button
   */
  public static JRadioButton createRadioButton(XAction action, ButtonGroup group,
  IconType iconType, boolean showText) {
    JRadioButton button = new JRadioButton();
    configureButton(button, action, group, iconType, showText, false);
    return button;
  }
  
  /**
   * Creates a configured check box.
   *
   * @param action    the XAction with the configuration data
   * @param iconType  the icon type
   * @param showText  if the label text should be shown
   * @return          a configured check box
   */
  public static JCheckBox createCheckBox(XAction action, ItemListener listener,
  IconType iconType, boolean showText) {
    JCheckBox checkBox = new JCheckBox();
    configureButton(checkBox, action, listener, iconType, showText, false);
    return checkBox;
  }
  
  /**
   * Creates a configured menu item.
   *
   * @param action    the XAction with the configuration data
   * @param iconType  the icon type
   * @param showText  if the label text should be shown
   * @return          a configured menu item
   */
  public static JMenuItem createMenuItem(XAction action, IconType iconType,
  boolean showText) {
    JMenuItem item = new JMenuItem();
    configureButton(item, action, iconType, showText, false);
    return item;
  }
  
  /**
   * Creates a configured radio button menu item.
   *
   * @param action    the XAction with the configuration data
   * @param iconType  the icon type
   * @param showText  if the label text should be shown
   * @return          a configured radio button menu item
   */
  public static JRadioButtonMenuItem createRadioButtonMenuItem(XAction action,
  ButtonGroup group, IconType iconType, boolean showText) {
    JRadioButtonMenuItem item = new JRadioButtonMenuItem();
    configureButton(item, action, group, iconType, showText, false);
    return item;
  }
  
  /**
   * Creates a configured check button menu item.
   *
   * @param action    the XAction with the configuration data
   * @param iconType  the icon type
   * @param showText  if the label text should be shown
   * @return          a configured check button menu item
   */
  public static JCheckBoxMenuItem createCheckBoxMenuItem(XAction action,
  ItemListener listener, IconType iconType, boolean showText) {
    JCheckBoxMenuItem item = new JCheckBoxMenuItem();
    configureButton(item, action, listener, iconType, showText, false);
    return item;
  }
  
  public static void configureButton(AbstractButton button, XAction action,
  ItemListener listener, IconType iconType, boolean showText, boolean coolStyle) {
    button.addItemListener(listener);
    configureButton(button, action, iconType, showText, coolStyle);
  }
  
  public static void configureButton(AbstractButton button, XAction action,
  ButtonGroup group, IconType iconType, boolean showText, boolean coolStyle) {
    group.add(button);
    configureButton(button, action, iconType, showText, coolStyle);
  }
  /**
   * Configures an abstract button.
   *
   * @param button    the abstract button  to configure
   * @param action    the XAction with the configuration data
   * @param iconType  the icon type
   * @param showText  if the label text should be shown
   */
  public static void configureButton(AbstractButton button, XAction action,
  IconType iconType, boolean showText, boolean coolStyle) {
    
    button.setAction(action);
    //??? noch nicht durchgedacht...
    
    if (iconType == IconType.LARGE_ICON) {
      setLargeIcons(button, action);
    } else if (iconType == IconType.SMALL_ICON) {
      setSmallIcons(button, action);
    } else if (iconType == IconType.NO_ICON) {
      button.setIcon(null);
      // was set by setAction()
    }
    
    if (!showText) {
      button.setText("");
    }
    
    if (coolStyle){
      CoolButtonController.addCoolButtonController(button);
    }
  }
  
  /**
   * Sets the large icon properties defined by a XAction for an abstract button.
   *
   * @param button  the abstract button
   * @param action  the XAction
   */
  protected static void setLargeIcons(AbstractButton button, XAction action) {
    Icon icon = action.getLargeDisabledIcon();
    if (icon != null) {
      button.setDisabledIcon(icon);
    }
    icon = action.getLargeDisabledSelectedIcon();
    if (icon != null) {
      button.setDisabledSelectedIcon(icon);
    }
    icon = action.getLargeIcon();
    if (icon != null) {
      button.setIcon(icon);
    } else {
      button.setIcon(LARGE_NULL_ICON);
    }
    icon = action.getLargePressedIcon();
    if (icon != null) {
      button.setPressedIcon(icon);
    }
    icon = action.getLargeRolloverIcon();
    if (icon != null) {
      button.setRolloverIcon(icon);
    }
    icon = action.getLargeRolloverSelectedIcon();
    if (icon != null) {
      button.setRolloverSelectedIcon(icon);
    }
    icon = action.getLargeSelectedIcon();
    if (icon != null) {
      button.setSelectedIcon(icon);
    }
  }
  
  /**
   * Sets the small icon properties defined by a XAction for an abstract button.
   *
   * @param button  the abstract button
   * @param action  the XAction
   */
  protected static void setSmallIcons(AbstractButton button, XAction action) {
    Icon icon = action.getSmallDisabledIcon();
    if (icon != null) {
      button.setDisabledIcon(icon);
    }
    icon = action.getSmallDisabledSelectedIcon();
    if (icon != null) {
      button.setDisabledSelectedIcon(icon);
    }
    icon = action.getSmallIcon();
    if (icon != null) {
      button.setIcon(icon);
    } else {
      button.setIcon(SMALL_NULL_ICON);
    }
    icon = action.getSmallPressedIcon();
    if (icon != null) {
      button.setPressedIcon(icon);
    }
    icon = action.getSmallRolloverIcon();
    if (icon != null) {
      button.setRolloverIcon(icon);
    }
    icon = action.getSmallRolloverSelectedIcon();
    if (icon != null) {
      button.setRolloverSelectedIcon(icon);
    }
    icon = action.getSmallSelectedIcon();
    if (icon != null) {
      button.setSelectedIcon(icon);
    }
  }
  
  
  
  
  
  
  //  private static XAction getStandardAction(String name, Object target, Locale
  //  locale) throws NoSuchMethodException{
  //    if (! standardActions.containsKey(locale)){
  //      standardActions.put(locale, new HashMap());
  //    }
  //    XAction standardAction;
  //    if (! ((Map) standardActions.get(locale)).containsKey(name)){
  //      standardAction = XActions.createXAction(name, target,
  //      ResourceBundle.getBundle(STANDARD_ACTIONS_RB_BASE_NAME, locale));
  //      ((Map) standardActions.get(locale)).put(name, standardAction);
  //    } else {
  //      standardAction = (XAction) ((Map) standardActions.get(locale)).get(name);
  //    }
  //    return standardAction;
  //  }
  
  public static JMenu createFileMenu(Locale locale) {
    return createStandardMenu("file", locale);
  }
  
  public static JMenu createEditMenu(Locale locale) {
    return createStandardMenu("edit", locale);
  }
  
  /*public static JWindowMenu createWindowMenu(Locale locale) {
    JWindowMenu menu = new JWindowMenu();
    configureStandardMenu(menu, "window", locale);
    return menu;
  }*/
  
  public static JMenu createHelpMenu(Locale locale) {
    return createStandardMenu("help", locale);
  }
  
  private static JMenu createStandardMenu(String name, Locale locale){
    JMenu menu = new JMenu();
    configureStandardMenu(menu, name, locale);
    return menu;
  }
  
  private static void configureStandardMenu(JMenu menu, String name, Locale
  locale){
    ResourceBundle rb = ResourceBundle.getBundle(STANDARD_MENUS_RB_BASE_NAME, locale);
    try {
      menu.setText(rb.getString(name + "." + "text"));
    } catch (MissingResourceException ex) {
      // ignore it
    }
  }
  
}
