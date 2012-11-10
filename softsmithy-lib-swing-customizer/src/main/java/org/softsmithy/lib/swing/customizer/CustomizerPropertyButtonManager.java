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
package org.softsmithy.lib.swing.customizer;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.AbstractButton;
import org.softsmithy.lib.beans.BeanIntrospector;
import org.softsmithy.lib.beans.ReflectivePropertyChangeListener;
import org.softsmithy.lib.swing.HorizontalAlignment;
import org.softsmithy.lib.swing.customizer.event.CustomizerSelectionEvent;
import org.softsmithy.lib.swing.customizer.event.CustomizerSelectionListener;

/**
 *
 * @author puce
 */
public class CustomizerPropertyButtonManager implements CustomizerSelectionListener {

    private JCustomizer activeCustomizer = null;
    private final Set<AbstractButton> textBoldButtons = new HashSet<>();
    private final Set<AbstractButton> textItalicButtons = new HashSet<>();
    private final Set<AbstractButton> textAlignLeftButtons = new HashSet<>();
    private final Set<AbstractButton> textAlignCenterButtons = new HashSet<>();
    private final Set<AbstractButton> textAlignRightButtons = new HashSet<>();
    private final Set<PropertyChangeListener> listeners = new HashSet<>();
    private CustomizerPropertyChangeListener textBold = null;
    private CustomizerPropertyChangeListener textItalic = null;
    private CustomizerPropertyChangeListener textAlignLeft = null;
    private CustomizerPropertyChangeListener textAlignCenter = null;
    private CustomizerPropertyChangeListener textAlignRight = null;

    /**
     * Creates a new instance of CustomizerActionFactory
     */
    public CustomizerPropertyButtonManager() {
    }

    public void textBold(PropertyChangeEvent e) {
        setSelection(textBoldButtons, ((Font) e.getNewValue()).isBold());
    }

    public void textItalic(PropertyChangeEvent e) {
        setSelection(textItalicButtons, ((Font) e.getNewValue()).isItalic());
    }

    public void textAlignLeft(PropertyChangeEvent e) {
        if (((HorizontalAlignment) e.getNewValue()).orient(activeCustomizer.getComponentOrientation()).equals(
                HorizontalAlignment.LEFT)) {
            setSelection(textAlignLeftButtons, true);
        }
    }

    public void textAlignCenter(PropertyChangeEvent e) {
        if (((HorizontalAlignment) e.getNewValue()).equals(HorizontalAlignment.CENTER)) {
            setSelection(textAlignCenterButtons, true);
        }
    }

    public void textAlignRight(PropertyChangeEvent e) {
        if (((HorizontalAlignment) e.getNewValue()).orient(activeCustomizer.getComponentOrientation()).equals(
                HorizontalAlignment.RIGHT)) {
            setSelection(textAlignRightButtons, true);
        }
    }

    private void setSelection(Set buttons, boolean selected) {
        for (Iterator i = buttons.iterator(); i.hasNext();) {
            ((AbstractButton) i.next()).setSelected(selected);
        }
    }

    @Override
    public void selectionChanged(CustomizerSelectionEvent e) {
        removeListeners();
        activeCustomizer = e.getActiveCustomizer();
        addListeners();
        initButtons(activeCustomizer, e.getCommonCustomizableProperties());
    }

    private void removeListeners() {
        if (activeCustomizer != null) {
            for (Iterator i = listeners.iterator(); i.hasNext();) {
                CustomizerPropertyChangeListener cl = (CustomizerPropertyChangeListener) i.next();
                activeCustomizer.removePropertyChangeListener(cl.getPropertyName(), cl);
            }
        }
    }

    private void addListeners() {
        if (activeCustomizer != null) {
            for (Iterator i = listeners.iterator(); i.hasNext();) {
                CustomizerPropertyChangeListener cl = (CustomizerPropertyChangeListener) i.next();
                activeCustomizer.addPropertyChangeListener(cl.getPropertyName(), cl);
            }
        }
    }

    private void initButtons(JCustomizer activeCustomizer, Set commonCustomizableProperties) {
        if (activeCustomizer != null) {
            if (commonCustomizableProperties.contains("font")) {
                Font font = activeCustomizer.getFont();
                setSelection(textBoldButtons, font.isBold());
                setSelection(textItalicButtons, font.isItalic());
            } else {
                setDefaultFontSelection();
            }
            if (commonCustomizableProperties.contains("horizontalAlignment")) {
                try {
                    HorizontalAlignment ha = ((HorizontalAlignment) BeanIntrospector.getPropertyDescriptor(
                            "horizontalAlignment",
                            activeCustomizer.getClass(), null).getReadMethod().invoke(activeCustomizer,
                            new Object[]{})).orient(activeCustomizer.getComponentOrientation());
                    if (ha.equals(HorizontalAlignment.LEFT)) {
                        setSelection(textAlignLeftButtons, true);
                    } else if (ha.equals(HorizontalAlignment.CENTER)) {
                        setSelection(textAlignCenterButtons, true);
                    } else {
                        setSelection(textAlignRightButtons, true);
                    }
                } catch (Exception ex1) {
                    ex1.printStackTrace();
                    setDefaultHorizontalAlignmentSelection();
                }
            } else {
                setDefaultHorizontalAlignmentSelection();
            }
        } else {
            setDefaultFontSelection();
            setDefaultHorizontalAlignmentSelection();
        }
    }

    private void setDefaultFontSelection() {
        setSelection(textBoldButtons, false);
        setSelection(textItalicButtons, false);
    }

    private void setDefaultHorizontalAlignmentSelection() {
        setSelection(textAlignLeftButtons, true);
    }

    public void addTextBoldButton(AbstractButton button) {
        if (textBoldButtons.isEmpty()) {
            listeners.add(getTextBoldPropertyChangeListener());
        }
        textBoldButtons.add(button);

    }

    public void addTextItalicButton(AbstractButton button) {
        if (textItalicButtons.isEmpty()) {
            listeners.add(getTextItalicPropertyChangeListener());
        }
        textItalicButtons.add(button);
    }

    public void addTextAlignLeftButton(AbstractButton button) {
        if (textAlignLeftButtons.isEmpty()) {
            listeners.add(getTextAlignLeftPropertyChangeListener());
        }
        textAlignLeftButtons.add(button);
    }

    public void addTextAlignCenterButton(AbstractButton button) {
        if (textAlignCenterButtons.isEmpty()) {
            listeners.add(getTextAlignCenterPropertyChangeListener());
        }
        textAlignCenterButtons.add(button);
    }

    public void addTextAlignRightButton(AbstractButton button) {
        if (textAlignRightButtons.isEmpty()) {
            listeners.add(getTextAlignRightPropertyChangeListener());
        }
        textAlignRightButtons.add(button);
    }

    public void removeTextBoldButton(AbstractButton button) {
        textBoldButtons.remove(button);
        if (textBoldButtons.isEmpty()) {
            listeners.remove(getTextBoldPropertyChangeListener());
        }
    }

    public void removeTextItalicButton(AbstractButton button) {
        textItalicButtons.remove(button);
        if (textItalicButtons.isEmpty()) {
            listeners.remove(getTextItalicPropertyChangeListener());
        }
    }

    public void removeTextAlignLeftButton(AbstractButton button) {
        textAlignLeftButtons.remove(button);
        if (textAlignLeftButtons.isEmpty()) {
            listeners.remove(getTextAlignLeftPropertyChangeListener());
        }
    }

    public void removeTextAlignCenterButton(AbstractButton button) {
        textAlignCenterButtons.remove(button);
        if (textAlignCenterButtons.isEmpty()) {
            listeners.remove(getTextAlignCenterPropertyChangeListener());
        }
    }

    public void removeTextAlignRightButton(AbstractButton button) {
        textAlignRightButtons.remove(button);
        if (textAlignRightButtons.isEmpty()) {
            listeners.remove(getTextAlignRightPropertyChangeListener());
        }
    }

    private PropertyChangeListener getTextBoldPropertyChangeListener() {
        if (textBold == null) {
            try {
                textBold = new CustomizerPropertyChangeListener(this, "textBold", "font");
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace(); // should not happen here
            }
        }
        return textBold;
    }

    private PropertyChangeListener getTextItalicPropertyChangeListener() {
        if (textItalic == null) {
            try {
                textItalic = new CustomizerPropertyChangeListener(this, "textItalic", "font");
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace(); // should not happen here
            }
        }
        return textItalic;
    }

    private PropertyChangeListener getTextAlignLeftPropertyChangeListener() {
        if (textAlignLeft == null) {
            try {
                textAlignLeft = new CustomizerPropertyChangeListener(this, "textAlignLeft", "horizontalAlignment");
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace(); // should not happen here
            }
        }
        return textAlignLeft;
    }

    private PropertyChangeListener getTextAlignCenterPropertyChangeListener() {
        if (textAlignCenter == null) {
            try {
                textAlignCenter = new CustomizerPropertyChangeListener(this, "textAlignCenter", "horizontalAlignment");
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace(); // should not happen here
            }
        }
        return textAlignCenter;
    }

    private PropertyChangeListener getTextAlignRightPropertyChangeListener() {
        if (textAlignRight == null) {
            try {
                textAlignRight = new CustomizerPropertyChangeListener(this, "textAlignRight", "horizontalAlignment");
            } catch (NoSuchMethodException ex) {
                ex.printStackTrace(); // should not happen here
            }
        }
        return textAlignRight;
    }

    private static class CustomizerPropertyChangeListener extends ReflectivePropertyChangeListener {

        /**
         * Holds value of property property.
         */
        private String propertyName;

        public CustomizerPropertyChangeListener(Object target, String methodName, String propertyName) throws NoSuchMethodException {
            super(target, methodName);
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
    }
}
