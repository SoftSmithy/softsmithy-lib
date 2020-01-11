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

package org.softsmithy.lib.swing.action;

import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.beans.Introspector;
import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import org.softsmithy.lib.swing.CoolButtonController;


/**
 * A utility class for XActions. <br>
 * Note: The API may change in a future version!
 *
 * @author    Florian Brunner
 * @see       XAction
 */
// * @created   March 8, 2002
public class XActions {
    
    //private static final String STANDARD_ACTIONS_RB_BASE_NAME ="org.softsmithy.lib.swing.StandardActions";
    /**
     * The ResourceBundle base name of the standard menus.
     */
    private static final String STANDARD_MENUS_RB_BASE_NAME = "org.softsmithy.lib.swing.action.StandardMenus";
    /**
     * A large empty icon. (24x24)
     */
    private static final Icon LARGE_NULL_ICON = new ImageIcon(new BufferedImage(24,24,BufferedImage.TYPE_INT_ARGB));//(XActions.class).getResource("/org/softsmithy/lib/buttonGraphics/general/null24.gif"));
    /**
     * A small empty icon. (16x16)
     */
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
     * @param name the method name (must take an ActionEvent
     *                                   obect as its single parameter) that gets
     *                                   called when an ActionEvent occurs
     * @param target the object with the specified method
     * @param rb a ResourceBundle (doesn't have to specify all XAction properties)
     * @return a XAction configured by a ResourceBundle
     * @exception NoSuchMethodException if no such method found
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
     * <table border="1">
     * <caption>XAction property keys</caption>
     *      <tr>
     *           <th>Key</th>
     *           <th>Value</th>
     *       </tr>
     *       <tr>
     *           <td>&lt;name&gt;.name</td>
     *           <td>The name to be displayed</td>
     *       </tr>
     *       <tr>
     *           <td>&lt;name&gt;.shortDescription</td>
     *           <td>Tool Tip</td>
     *       </tr>
     *       <tr>
     *           <td>&lt;name&gt;.acceleratorKey</td>
     *           <td>Shortcut</td>
     *       </tr>
     *       <tr>
     *           <td>&lt;name&gt;.mnemonicKey</td>
     *           <td>Mnemonic</td>
     *       </tr>
     *       <tr>
     *           <td>&lt;name&gt;.largeDisabledIcon</td>
     *           <td>File Name</td>
     *       </tr>
     *           <tr>
     *               <td>&lt;name&gt;.largeDisabledSelectedIcon</td>
     *               <td>File Name</td>
     *           </tr>
     *           <tr>
     *               <td>&lt;name&gt;.largeIcon</td>
     *               <td>File Name</td>
     *           </tr>
     *           <tr>
     *               <td>&lt;name&gt;.largePressedIcon</td>
     *               <td>File Name</td>
     *           </tr>
     *           <tr>
     *               <td>&lt;name&gt;.largeRolloverIcon</td>
     *               <td>File Name</td>
     *           </tr>
     *           <tr>
     *               <td>&lt;name&gt;.largeRolloverSelectedIcon</td>
     *               <td>File Name</td>
     *           </tr>
     *           <tr>
     *               <td>&lt;name&gt;.largeSelectedIcon</td>
     *               <td>File Name</td>
     *           </tr>
     *           <tr>
     *               <td>&lt;name&gt;.smallDisabledIcon</td>
     *               <td>File Name</td>
     *           </tr>
     *           <tr>
     *               <td>&lt;name&gt;.smallDisabledSelectedIcon</td>
     *               <td>File Name</td>
     *           </tr>
     *           <tr>
     *               <td>&lt;name&gt;.smallIcon</td>
     *               <td>File Name</td>
     *           </tr>
     *           <tr>
     *               <td>&lt;name&gt;.smallPressedIcon</td>
     *               <td>File Name</td>
     *           </tr>
     *           <tr>
     *               <td>&lt;name&gt;.smallRolloverIcon</td>
     *               <td>File Name</td>
     *           </tr>
     *           <tr>
     *               <td>&lt;name&gt;.smallRolloverSelectedIcon</td>
     *               <td>File Name</td>
     *           </tr>
     *           <tr>
     *               <td>&lt;name&gt;.smallSelectedIcon</td>
     *               <td>File Name</td>
     *           </tr>
     *       </table>
     * <br>
     * E.g. <br>
     * myAction.name = MyAction <br>
     * myAction.shortDescription = My Action <br>
     * myAction.acceleratorKey = Control M <br>
     * myAction.mnemonicKey = A <br>
     * myAction.largeIcon = /myGraphics/MyAction24.gif <br>
     * myAction.smallIcon = /myGraphics/MyAction16.gif <br>
     *
     * @param action the XAction to configure
     * @param name the base name of the keys
     * @param rb a ResourceBundle (doesn't have to specify all XAction properties)
     */
    public static void configureXAction(XAction action, String name, ResourceBundle rb){
        configureStringProperties(action, name, rb);
        configureIconProperties(action, name, rb);
        configureKeyStrokeProperties(action, name, rb);
        configureMnemonicKeyProperties(action, name, rb);
    }
    
    
    /**
     * Configures the string properties of a XAction from a ResourceBundle.
     * @param action the XAction to configure
     * @param name the base name of the keys
     * @param rb a ResourceBundle (doesn't have to specify all string properties of XAction)
     */
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
    
    /**
     * Configures the icon properties of a XAction from a ResourceBundle.
     * @param action the XAction to configure
     * @param name the base name of the keys
     * @param rb a ResourceBundle (doesn't have to specify all icon properties of XAction)
     */
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
    
    /**
     * Configures the KeyStroke properties of a XAction from a ResourceBundle.
     * @param action the XAction to configure
     * @param name the base name of the keys
     * @param rb a ResourceBundle (doesn't have to specify all KeyStroke properties of XAction)
     */
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
    
    /**
     * Configures the mnemonic key properties of a XAction from a ResourceBundle.
     * @param action the XAction to configure
     * @param name the base name of the keys
     * @param rb a ResourceBundle (doesn't have to specify all mnemonic key properties of XAction)
     */
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
     * Creates a configured button.<br>
     * <br>
     * If cool style is set to true, then the border of the button will be drawn
     * only if the mouse pointer is over the button.
     * Note: Since Java 1.4 you can create a similar effect by setting the
     * rollover property of the toolbar to true. However look and feels may
     * ignore this property and the Java Look and Feel does so.
     * @param action the XAction with the configuration data
     * @param iconType the icon type
     * @param showText if the label text should be shown
     * @param coolStyle specifies if the cool style should be applied
     * @return a configured button
     */
    public static JButton createButton(XAction action, IconType iconType,
            boolean showText, boolean coolStyle) {
        JButton button = new JButton();
        configureButton(button, action, iconType, showText, coolStyle);
        return button;
    }
    
    /**
     * Creates a configured toggle button.<br>
     * <br>
     * If cool style is set to true, then the border of the button will be drawn
     * only if the mouse pointer is over the button.
     * Note: Since Java 1.4 you can create a similar effect by setting the
     * rollover property of the toolbar to true. However look and feels may
     * ignore this property and the Java Look and Feel does so.
     *
     * @param action the XAction with the configuration data
     * @param listener a listener, which listens to the item state
     * @param iconType the icon type
     * @param showText if the label text should be shown
     * @param coolStyle specifies if the cool style should be applied
     * @return a configured toggle button
     */
    public static JToggleButton createToggleButton(XAction action, ItemListener listener, IconType iconType, boolean showText, boolean coolStyle) {
        JToggleButton button = new JToggleButton();
        configureButton(button, action, listener, iconType, showText, coolStyle);
        return button;
    }
    
    /**
     * Creates a configured toggle button.<br>
     * <br>
     * If cool style is set to true, then the border of the button will be drawn
     * only if the mouse pointer is over the button.
     * Note: Since Java 1.4 you can create a similar effect by setting the
     * rollover property of the toolbar to true. However look and feels may
     * ignore this property and the Java Look and Feel does so.
     *
     * @param action the XAction with the configuration data
     * @param group a ButtonGroup to which this button will be added
     * @param iconType the icon type
     * @param showText if the label text should be shown
     * @param coolStyle specifies if the cool style should be applied
     * @return a configured toggle button
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
     * @param action the XAction with the configuration data
     * @param group a ButtonGroup to which this button will be added
     * @param iconType the icon type
     * @param showText if the label text should be shown
     * @return a configured radio button
     */
    public static JRadioButton createRadioButton(XAction action, ButtonGroup group,
            IconType iconType, boolean showText) {
        JRadioButton button = new JRadioButton();
        configureButton(button, action, group, iconType, showText, false);
        return button;
    }
    
    /**
     * Creates a configured check box.
     * @param action the XAction with the configuration data
     * @param listener a listener, which listens to the item state
     * @param iconType the icon type
     * @param showText if the label text should be shown
     * @return a configured check box
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
     * @param action the XAction with the configuration data
     * @param group a ButtonGroup to which this button will be added
     * @param iconType the icon type
     * @param showText if the label text should be shown
     * @return a configured radio button menu item
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
     * @param action the XAction with the configuration data
     * @param listener a listener, which listens to the item state
     * @param iconType the icon type
     * @param showText if the label text should be shown
     * @return a configured check button menu item
     */
    public static JCheckBoxMenuItem createCheckBoxMenuItem(XAction action,
            ItemListener listener, IconType iconType, boolean showText) {
        JCheckBoxMenuItem item = new JCheckBoxMenuItem();
        configureButton(item, action, listener, iconType, showText, false);
        return item;
    }
    
    /**
     * Configures a button from a XAction.<br>
     * <br>
     * If cool style is set to true, then the border of the button will be drawn
     * only if the mouse pointer is over the button.
     * Note: Since Java 1.4 you can create a similar effect by setting the
     * rollover property of the toolbar to true. However look and feels may
     * ignore this property and the Java Look and Feel does so.
     *
     * @param button the button to configure
     * @param action the XAction with the configuration data
     * @param listener a listener, which listens to the item state
     * @param iconType the icon type
     * @param showText if the label text should be shown
     * @param coolStyle specifies if the cool style should be applied
     */
    public static void configureButton(AbstractButton button, XAction action,
            ItemListener listener, IconType iconType, boolean showText, boolean coolStyle) {
        button.addItemListener(listener);
        configureButton(button, action, iconType, showText, coolStyle);
    }
    
    
    /**
     * Configures a button from a XAction.<br>
     * <br>
     * If cool style is set to true, then the border of the button will be drawn
     * only if the mouse pointer is over the button.
     * Note: Since Java 1.4 you can create a similar effect by setting the
     * rollover property of the toolbar to true. However look and feels may
     * ignore this property and the Java Look and Feel does so.
     *
     * @param button the button to configure
     * @param action the XAction with the configuration data
     * @param group a ButtonGroup to which this button will be added
     * @param iconType the icon type
     * @param showText if the label text should be shown
     * @param coolStyle specifies if the cool style should be applied
     */
    public static void configureButton(AbstractButton button, XAction action,
            ButtonGroup group, IconType iconType, boolean showText, boolean coolStyle) {
        group.add(button);
        configureButton(button, action, iconType, showText, coolStyle);
    }
    
    /**
     * Configures a button from a XAction.<br>
     * <br>
     * If cool style is set to true, then the border of the button will be drawn
     * only if the mouse pointer is over the button.
     * Note: Since Java 1.4 you can create a similar effect by setting the
     * rollover property of the toolbar to true. However look and feels may
     * ignore this property and the Java Look and Feel does so.
     *
     * @param button the button to configure
     * @param action the XAction with the configuration data
     * @param iconType the icon type
     * @param showText if the label text should be shown
     * @param coolStyle specifies if the cool style should be applied
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
    private static void setLargeIcons(AbstractButton button, XAction action) {
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
    private static void setSmallIcons(AbstractButton button, XAction action) {
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
    
    /**
     * Creates a file menu with a localized text.
     * @param locale the locale
     * @return a file menu with a localized text
     */
    public static JMenu createFileMenu(Locale locale) {
        return createStandardMenu("file", locale);
    }
    
    /**
     * Creates an edit menu with a localized text.
     * @param locale the locale
     * @return an edit menu with a localized text
     */
    public static JMenu createEditMenu(Locale locale) {
        return createStandardMenu("edit", locale);
    }
    
  /*public static JWindowMenu createWindowMenu(Locale locale) {
    JWindowMenu menu = new JWindowMenu();
    configureStandardMenu(menu, "window", locale);
    return menu;
  }*/
    
    /**
     * Creates a help menu with a localized text.
     * @param locale the locale
     * @return a help menu with a localized text
     */
    public static JMenu createHelpMenu(Locale locale) {
        return createStandardMenu("help", locale);
    }
    
    /**
     * Creates a standard menu with a localized text.
     * @param name the base name of the ResourceBundle keys
     * @param locale the locale
     * @return a standard menu with a localized text
     */
    private static JMenu createStandardMenu(String name, Locale locale){
        JMenu menu = new JMenu();
        configureStandardMenu(menu, name, locale);
        return menu;
    }
    
    /**
     * Creates a standard menu with a localized text.
     *
     * @param menu the menu to configure
     * @param name the base name of the ResourceBundle keys
     * @param locale the locale
     */
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
