package puce.swing;

import java.net.URL;

import java.util.*;
import javax.swing.*;

import puce.util.*;

/**
 * A utility class for XActions. <br>
 * Note: The API may change in a future version!
 *
 * @author    Florian Brunner
 * @created   March 8, 2002
 * @see       XAction
 */

public class XActions {

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
   * @param rb                         a ResourceBundle (must not specify all Action properties)
   * @return                           a XAction configured by a ResourceBundle
   * @exception NoSuchMethodException  if no such method found
   */
  public static XAction createXAction(String name, Object target, ResourceBundle rb)
       throws NoSuchMethodException {
    XAction action = new ReflectiveXAction(target, name);
    String[] string_properties = {Action.NAME};
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
    for (int i = 0; i < string_properties.length; i++) {
      try {
        action.putValue(string_properties[i], rb.getString(name + string_properties[i]));
      } catch (MissingResourceException ex) {
        //System.out.println("Couln'dt find: " + name + string_properties[i] );
        // ignore it
      }
    }
    for (int i = 0; i < icon_properties.length; i++) {
      try {
        URL url = (XActions.class).getClass().getResource(rb.getString(name + icon_properties[i]));
        if (url != null) {
          action.putValue(icon_properties[i], new ImageIcon(url));
        }
      } catch (MissingResourceException ex) {
        //System.out.println("Couln'dt find: " + name + icon_properties[i] );
        // ignore it
      }
    }
    return action;
  }

  /**
   * Creates a configured button.
   *
   * @param action    the XAction with the configuration data
   * @param iconType  the icon type
   * @param showText  if the label text should be shown
   * @return          a configured button
   */
  public static JButton createButton(XAction action, IconType iconType, boolean showText) {
    JButton button = new JButton();
    configureButton(button, action, iconType, showText);
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
  public static JToggleButton createToggleButton(XAction action, IconType iconType, boolean showText) {
    JToggleButton button = new JToggleButton();
    configureToggleButton(button, action, iconType, showText);
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
  public static JRadioButton createRadioButton(XAction action, IconType iconType, boolean showText) {
    JRadioButton button = new JRadioButton();
    configureRadioButton(button, action, iconType, showText);
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
  public static JCheckBox createCheckBox(XAction action, IconType iconType, boolean showText) {
    JCheckBox checkBox = new JCheckBox();
    configureCheckBox(checkBox, action, iconType, showText);
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
  public static JMenuItem createMenuItem(XAction action, IconType iconType, boolean showText) {
    JMenuItem item = new JMenuItem();
    configureMenuItem(item, action, iconType, showText);
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
  public static JRadioButtonMenuItem createRadioButtonMenuItem(XAction action, IconType iconType, boolean showText) {
    JRadioButtonMenuItem item = new JRadioButtonMenuItem();
    configureRadioButtonMenuItem(item, action, iconType, showText);
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
  public static JCheckBoxMenuItem createCheckBoxMenuItem(XAction action, IconType iconType, boolean showText) {
    JCheckBoxMenuItem item = new JCheckBoxMenuItem();
    configureCheckBoxMenuItem(item, action, iconType, showText);
    return item;
  }

  /**
   * Configures a button.
   *
   * @param button    the button to configure
   * @param action    the XAction with the configuration data
   * @param iconType  the icon type
   * @param showText  if the label text should be shown
   */
  public static void configureButton(JButton button, XAction action, IconType iconType, boolean showText) {
    configureAbstractButton(button, action, iconType, showText);
  }

  /**
   * Configures a toggle button.
   *
   * @param button    the toggle button to configure
   * @param action    the XAction with the configuration data
   * @param iconType  the icon type
   * @param showText  if the label text should be shown
   */
  public static void configureToggleButton(JToggleButton button, XAction action, IconType iconType, boolean showText) {
    configureAbstractButton(button, action, iconType, showText);
  }

  /**
   * Configures a radio button.
   *
   * @param button    the radio button to configure
   * @param action    the XAction with the configuration data
   * @param iconType  the icon type
   * @param showText  if the label text should be shown
   */
  public static void configureRadioButton(JRadioButton button, XAction action, IconType iconType, boolean showText) {
    configureAbstractButton(button, action, iconType, showText);
  }

  /**
   * Configures a check box.
   *
   * @param checkBox  the check box  to configure
   * @param action    the XAction with the configuration data
   * @param iconType  the icon type
   * @param showText  if the label text should be shown
   */
  public static void configureCheckBox(JCheckBox checkBox, XAction action, IconType iconType, boolean showText) {
    configureAbstractButton(checkBox, action, iconType, showText);
  }

  /**
   * Configures a menu item.
   *
   * @param item      the menu item  to configure
   * @param action    the XAction with the configuration data
   * @param iconType  the icon type
   * @param showText  if the label text should be shown
   */
  public static void configureMenuItem(JMenuItem item, XAction action, IconType iconType, boolean showText) {
    configureAbstractButton(item, action, iconType, showText);
  }

  /**
   * Configures a radio button menu item.
   *
   * @param item      the radio button menu item to configure
   * @param action    the XAction with the configuration data
   * @param iconType  the icon type
   * @param showText  if the label text should be shown
   */
  public static void configureRadioButtonMenuItem(JRadioButtonMenuItem item, XAction action, IconType iconType, boolean showText) {
    configureAbstractButton(item, action, iconType, showText);
  }

  /**
   * Configures a check box menu item.
   *
   * @param item      the check box menu item to configure
   * @param action    the XAction with the configuration data
   * @param iconType  the icon type
   * @param showText  if the label text should be shown
   */
  public static void configureCheckBoxMenuItem(JCheckBoxMenuItem item, XAction action, IconType iconType, boolean showText) {
    configureAbstractButton(item, action, iconType, showText);
  }


  /**
   * Configures an abstract button.
   *
   * @param button    the abstract button  to configure
   * @param action    the XAction with the configuration data
   * @param iconType  the icon type
   * @param showText  if the label text should be shown
   */
  public static void configureAbstractButton(AbstractButton button, XAction action, IconType iconType, boolean showText) {

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

}
