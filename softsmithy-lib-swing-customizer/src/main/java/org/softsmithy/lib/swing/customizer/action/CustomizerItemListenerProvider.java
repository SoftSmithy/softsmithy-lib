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
 * CustomizerActionFactory.java
 *
 * Created on 6. November 2002, 12:17
 */
package org.softsmithy.lib.swing.customizer.action;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import org.softsmithy.lib.swing.customizer.JCustomizer;
import org.softsmithy.lib.swing.customizer.SelectionManager;

/**
 *
 * @author puce
 */
public class CustomizerItemListenerProvider {

//    private final static Class<?>[] PARAMETER_TYPES = new Class<?>[]{ItemEvent.class};
    private final SelectionManager selectionManager;
    private ItemListener textBold = null;
    private ItemListener textItalic = null;

    /**
     * Creates a new instance of CustomizerActionFactory
     */
    public CustomizerItemListenerProvider(SelectionManager selectionManager) {
        this.selectionManager = selectionManager;
    }

    public ItemListener getTextBoldItemListener() {
        if (textBold == null) {
            textBold = new TextBoldItemListener();
        }
        return textBold;
    }

    public ItemListener getTextItalicItemListener() {
        if (textItalic == null) {
            textItalic = new TextItalicItemListener();
        }
        return textItalic;
    }

    private class TextBoldItemListener extends FontStyleItemListener {

        @Override
        protected int calculateFontStyle(ItemEvent e, Font font) {
            int style;
            if (e.getStateChange() == ItemEvent.SELECTED) {
                style = Font.BOLD;
                if (font.isItalic()) {
                    style |= Font.ITALIC;
                }
            } else {
                if (font.isItalic()) {
                    style = Font.ITALIC;
                } else {
                    style = Font.PLAIN;
                }
            }
            return style;
        }
    }

    private class TextItalicItemListener extends FontStyleItemListener {

        @Override
        protected int calculateFontStyle(ItemEvent e, Font font) {
            int style;
            if (e.getStateChange() == ItemEvent.SELECTED) {
                style = Font.ITALIC;
                if (font.isBold()) {
                    style |= Font.BOLD;
                }
            } else {
                if (font.isBold()) {
                    style = Font.BOLD;
                } else {
                    style = Font.PLAIN;
                }
            }
            return style;
        }
    }

    private abstract class FontStyleItemListener extends CustomizerItemListener {

        public FontStyleItemListener() {
            super("font");
        }

        @Override
        protected Object getProperty() {
            Object property = null;
            if (selectionManager.getActiveCustomizer() != null) {
                Font font = selectionManager.getActiveCustomizer().getFont();
                int style = font.getStyle();
                property = style;
            }
            return property;
        }

        @Override
        protected void setProperty(Object value) {
            int style = ((Integer) value);
            JCustomizer[] customizers = selectionManager.getSelectedCustomizers();
            for (JCustomizer customizer : customizers) {
                Font font = customizer.getFont();
                customizer.setFont(font.deriveFont(style));
            }
        }

        @Override
        protected Object calculatePropertyValue(ItemEvent e) {
            int style = Font.PLAIN;
            if (selectionManager.getActiveCustomizer() != null) {
                Font font = selectionManager.getActiveCustomizer().getFont();
                style = calculateFontStyle(e, font);
            }
            return style;
        }

        protected abstract int calculateFontStyle(ItemEvent e, Font font);
    }

    private abstract class CustomizerItemListener implements ItemListener {

        /**
         * Holds value of property property.
         */
        private final String propertyName;

        public CustomizerItemListener(String propertyName) {
            this.propertyName = propertyName;
        }

        /**
         * Getter for property property.
         *
         * @return Value of property property.
         *
         */
        public String getPropertyName() {
            return this.propertyName;
        }

        /**
         * Invoked when an item has been selected or deselected by the user. Springs to the method specified in the constructor.
         *
         * @param e an ActionEvent object
         *
         */
        @Override
        public void itemStateChanged(ItemEvent e) {
            Object newPropertyValue = calculatePropertyValue(e);
            if (propertyReallyChanged(newPropertyValue)) {
                setProperty(newPropertyValue);
            }
        }

        protected abstract Object calculatePropertyValue(ItemEvent e);

        protected abstract void setProperty(Object newPropertyValue);

        protected abstract Object getProperty();

        private boolean propertyReallyChanged(Object newPropertyValue) {
            return (notJustSelectionChanged()
                    && isAllowedToChange()
                    && isNoDriggeredChange(newPropertyValue));
        }

        private boolean notJustSelectionChanged() {
            return !selectionManager.isFireingSelectionChanged();
        }

        private boolean isAllowedToChange() {
            return JCustomizer.getCommonCustomizableProperties(Arrays.asList(
                    selectionManager.getSelectedCustomizers())).contains(getPropertyName());
        }

        private boolean isNoDriggeredChange(Object newPropertyValue) {
            return !getProperty().equals(newPropertyValue);
        }
    }
}
