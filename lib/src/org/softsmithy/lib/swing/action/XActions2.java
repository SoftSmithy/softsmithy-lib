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

import java.net.URL;

import java.util.*;
import javax.swing.*;

/**
 * A utility class for XActions (revised version). Not yet a public class.
 * Under development!<br>
 * Note: The API may change in a future version!
 *
 * @author    Florian Brunner
 * @see       XAction
 */
class XActions2 {

    private XActions2() {
    }

    /**
     * Description of the Method
     *
     * @param name                       Description of the Parameter
     * @param target                     Description of the Parameter
     * @param rb                         Description of the Parameter
     * @return                           Description of the Return Value
     * @exception NoSuchMethodException  Description of the Exception
     */
    public static XAction createAction(String name, Object target, ResourceBundle rb)
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
     * Description of the Method
     *
     * @param action    Description of the Parameter
     * @param iconType  Description of the Parameter
     * @param showText  Description of the Parameter
     * @return          Description of the Return Value
     */
    public static JButton createButton(XAction action, IconType iconType, boolean showText) {
        JButton button = new JButton();
        configureButton(button, action, iconType, showText);
        return button;
    }

    /**
     * Description of the Method
     *
     * @param action    Description of the Parameter
     * @param iconType  Description of the Parameter
     * @param showText  Description of the Parameter
     * @return          Description of the Return Value
     */
    public static JToggleButton createToggleButton(XAction action, IconType iconType, boolean showText) {
        JToggleButton button = new JToggleButton();
        configureToggleButton(button, action, iconType, showText);
        return button;
    }

    /**
     * Description of the Method
     *
     * @param action    Description of the Parameter
     * @param iconType  Description of the Parameter
     * @param showText  Description of the Parameter
     * @return          Description of the Return Value
     */
    public static JRadioButton createRadioButton(XAction action, IconType iconType, boolean showText) {
        JRadioButton button = new JRadioButton();
        configureRadioButton(button, action, iconType, showText);
        return button;
    }

    /**
     * Description of the Method
     *
     * @param action    Description of the Parameter
     * @param iconType  Description of the Parameter
     * @param showText  Description of the Parameter
     * @return          Description of the Return Value
     */
    public static JCheckBox createCheckBox(XAction action, IconType iconType, boolean showText) {
        JCheckBox checkBox = new JCheckBox();
        configureCheckBox(checkBox, action, iconType, showText);
        return checkBox;
    }

    /**
     * Description of the Method
     *
     * @param action    Description of the Parameter
     * @param iconType  Description of the Parameter
     * @param showText  Description of the Parameter
     * @return          Description of the Return Value
     */
    public static JMenuItem createMenuItem(XAction action, IconType iconType, boolean showText) {
        JMenuItem item = new JMenuItem();
        configureMenuItem(item, action, iconType, showText);
        return item;
    }

    /**
     * Description of the Method
     *
     * @param action    Description of the Parameter
     * @param iconType  Description of the Parameter
     * @param showText  Description of the Parameter
     * @return          Description of the Return Value
     */
    public static JRadioButtonMenuItem createRadioButtonMenuItem(XAction action, IconType iconType, boolean showText) {
        JRadioButtonMenuItem item = new JRadioButtonMenuItem();
        configureRadioButtonMenuItem(item, action, iconType, showText);
        return item;
    }

    /**
     * Description of the Method
     *
     * @param action    Description of the Parameter
     * @param iconType  Description of the Parameter
     * @param showText  Description of the Parameter
     * @return          Description of the Return Value
     */
    public static JCheckBoxMenuItem createCheckBoxMenuItem(XAction action, IconType iconType, boolean showText) {
        JCheckBoxMenuItem item = new JCheckBoxMenuItem();
        configureCheckBoxMenuItem(item, action, iconType, showText);
        return item;
    }

    /**
     * Description of the Method
     *
     * @param button    Description of the Parameter
     * @param action    Description of the Parameter
     * @param iconType  Description of the Parameter
     * @param showText  Description of the Parameter
     * @return          Description of the Return Value
     */
    public static JButton configureButton(JButton button, XAction action, IconType iconType, boolean showText) {
        configureAbstractButton(button, action, iconType, showText);
        return button;
    }

    /**
     * Description of the Method
     *
     * @param button    Description of the Parameter
     * @param action    Description of the Parameter
     * @param iconType  Description of the Parameter
     * @param showText  Description of the Parameter
     * @return          Description of the Return Value
     */
    public static JToggleButton configureToggleButton(JToggleButton button, XAction action, IconType iconType, boolean showText) {
        configureAbstractButton(button, action, iconType, showText);
        return button;
    }

    /**
     * Description of the Method
     *
     * @param button    Description of the Parameter
     * @param action    Description of the Parameter
     * @param iconType  Description of the Parameter
     * @param showText  Description of the Parameter
     * @return          Description of the Return Value
     */
    public static JRadioButton configureRadioButton(JRadioButton button, XAction action, IconType iconType, boolean showText) {
        configureAbstractButton(button, action, iconType, showText);
        return button;
    }

    /**
     * Description of the Method
     *
     * @param checkBox  Description of the Parameter
     * @param action    Description of the Parameter
     * @param iconType  Description of the Parameter
     * @param showText  Description of the Parameter
     * @return          Description of the Return Value
     */
    public static JCheckBox configureCheckBox(JCheckBox checkBox, XAction action, IconType iconType, boolean showText) {
        configureAbstractButton(checkBox, action, iconType, showText);
        return checkBox;
    }

    /**
     * Description of the Method
     *
     * @param item      Description of the Parameter
     * @param action    Description of the Parameter
     * @param iconType  Description of the Parameter
     * @param showText  Description of the Parameter
     * @return          Description of the Return Value
     */
    public static JMenuItem configureMenuItem(JMenuItem item, XAction action, IconType iconType, boolean showText) {
        configureAbstractButton(item, action, iconType, showText);
        return item;
    }

    /**
     * Description of the Method
     *
     * @param item      Description of the Parameter
     * @param action    Description of the Parameter
     * @param iconType  Description of the Parameter
     * @param showText  Description of the Parameter
     * @return          Description of the Return Value
     */
    public static JRadioButtonMenuItem configureRadioButtonMenuItem(JRadioButtonMenuItem item, XAction action, IconType iconType, boolean showText) {
        configureAbstractButton(item, action, iconType, showText);
        return item;
    }

    /**
     * Description of the Method
     *
     * @param item      Description of the Parameter
     * @param action    Description of the Parameter
     * @param iconType  Description of the Parameter
     * @param showText  Description of the Parameter
     * @return          Description of the Return Value
     */
    public static JCheckBoxMenuItem configureCheckBoxMenuItem(JCheckBoxMenuItem item, XAction action, IconType iconType, boolean showText) {
        configureAbstractButton(item, action, iconType, showText);
        return item;
    }

    /**
     * Description of the Method
     *
     * @param button    Description of the Parameter
     * @param action    Description of the Parameter
     * @param iconType  Description of the Parameter
     * @param showText  Description of the Parameter
     * @return          Description of the Return Value
     */
    public static AbstractButton configureAbstractButton(AbstractButton button, XAction action, IconType iconType, boolean showText) {

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

        return button;
    }

    /**
     * Sets the largeIcons attribute of the Actions2 class
     *
     * @param button  The new largeIcons value
     * @param action  The new largeIcons value
     * @return        Description of the Return Value
     */
    protected static AbstractButton setLargeIcons(AbstractButton button, XAction action) {
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

        return button;
    }

    /**
     * Sets the smallIcons attribute of the Actions2 class
     *
     * @param button  The new smallIcons value
     * @param action  The new smallIcons value
     * @return        Description of the Return Value
     */
    protected static AbstractButton setSmallIcons(AbstractButton button, XAction action) {
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

        return button;
    }

    /**
     * A typesafe enum class
     *
     * @author    Florian Brunner
     * @created   March 7, 2002
     */
    private static class IconType {

        private final String fName;

        /**
         * Constructor for the ArgTypes object
         *
         * @param name  Description of the Parameter
         */
        private IconType(String name) {
            fName = name;
        }

        /**
         * Description of the Method
         *
         * @return   Description of the Return Value
         */
        public String toString() {
            return fName;
        }
        /**
         * Description of the Field
         */
        public final static IconType LARGE_ICON = new IconType("largeIcon");
        /**
         * Description of the Field
         */
        public final static IconType SMALL_ICON = new IconType("smallIcon");
        /**
         * Description of the Field
         */
        public final static IconType NO_ICON = new IconType("noIcon");
        private final static IconType[] PRIVATE_VALUES = {LARGE_ICON, SMALL_ICON, NO_ICON};
        /**
         * Description of the Field
         */
        public final static List VALUES =
                Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
    }

    /**
     * Description of the Class
     *
     * @author    puce
     * @created   March 7, 2002
     */
    private static class XActionButton extends JButton {

        private AbstractXActionButton fAbstractButton;
    }

    private static class AbstractXActionButton {

        private IconType fIconType;
        private boolean fShowText;
        private boolean fShowToolTip;

        /**
         * Constructor for the XActionButton object
         *
         * @param xaction      Description of the Parameter
         * @param iconType     Description of the Parameter
         * @param showText     Description of the Parameter
         * @param showToolTip  Description of the Parameter
         */
        public AbstractXActionButton(XAction xaction, IconType iconType, boolean showText,
                boolean showToolTip) {
            super();
        //???

        }
    }
}
