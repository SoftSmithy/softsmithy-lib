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
 * ParentStyle.java
 *
 * Created on 11. Juli 2003, 18:15
 */
package org.softsmithy.lib.swing.style;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Locale;

/**
 *
 * @author puce
 */
public class ParentStyle extends AbstractStyle {

    private final HierarchyListener parentListener = new ParentListener();
    private final PropertyChangeListener parentBackgroundListener = new ParentBackgroundListener();
    private final PropertyChangeListener parentForegroundListener = new ParentForegroundListener();
    private final PropertyChangeListener parentFontListener = new ParentFontListener();
    private final PropertyChangeListener parentOpaqueListener = new ParentOpaqueListener();
    /**
     * Holds value of property styleable.
     */
    private final Styleable styleable;

    public ParentStyle(Styleable styleable) {
        this.styleable = styleable;
    }

    @Override
    public Color getBackground() {
        return getStyleable().getParent() != null ? getStyleable().getParent().getBackground()
                : getStyleable().getNoneStyle().getBackground();
    }

    @Override
    public Font getFont() {
        return getStyleable().getParent() != null ? getStyleable().getParent().getFont()
                : getStyleable().getNoneStyle().getFont();
    }

    @Override
    public Color getForeground() {
        return getStyleable().getParent() != null ? getStyleable().getParent().getForeground()
                : getStyleable().getNoneStyle().getForeground();
    }

    @Override
    public String getName(Locale locale) {
        return Styles.getParentStyleName(locale);
    }

    @Override
    public boolean isOpaque() {
        return getStyleable().getParent() != null ? getStyleable().getParent().isOpaque()
                : getStyleable().getNoneStyle().isOpaque();
    }

    @Override
    public StyleProvider getStyleProvider() {
        return ParentStyleProvider.INSTANCE;
    }

    @Override
    public void startListening() {
        super.startListening();
        getStyleable().addHierarchyListener(parentListener);
        if (getStyleable().getParent() != null) {
            getStyleable().getParent().addPropertyChangeListener("background", parentBackgroundListener);
            getStyleable().getParent().addPropertyChangeListener("foreground", parentForegroundListener);
            getStyleable().getParent().addPropertyChangeListener("font", parentFontListener);
            getStyleable().getParent().addPropertyChangeListener("opaque", parentOpaqueListener);
        }

    }

    @Override
    public void stopListening() {
        super.stopListening();
        getStyleable().removeHierarchyListener(parentListener);
        if (getStyleable().getParent() != null) {
            getStyleable().getParent().removePropertyChangeListener("background", parentBackgroundListener);
            getStyleable().getParent().removePropertyChangeListener("foreground", parentForegroundListener);
            getStyleable().getParent().removePropertyChangeListener("font", parentFontListener);
            getStyleable().getParent().removePropertyChangeListener("opaque", parentOpaqueListener);
        }
    }

    /**
     * Getter for property styleable.
     *
     * @return Value of property styleable.
     *
     */
    public Styleable getStyleable() {
        return this.styleable;
    }

    //TODO: Check if an AncestorListener is better than HierarchyListener (or addNotify in JComponent?)
 /* private class ParentListener implements AncestorListener{
    
     public void ancestorAdded(AncestorEvent event) {
     System.out.println("ancestorAdd");
     getStyleable().getParent().addPropertyChangeListener("background", parentBackgroundListener);
     getStyleable().getParent().addPropertyChangeListener("foreground", parentForegroundListener);
     getStyleable().getParent().addPropertyChangeListener("font", parentFontListener);
     getStyleable().getParent().addPropertyChangeListener("opaque", parentOpaqueListener);
     }
    
     public void ancestorMoved(AncestorEvent event) {
     System.out.println("ancestorMoved - do nothing!?");
     }
    
     public void ancestorRemoved(AncestorEvent event) {
     System.out.println("ancestorRemoved");
     getStyleable().getParent().removePropertyChangeListener("background", parentBackgroundListener);
     getStyleable().getParent().removePropertyChangeListener("foreground", parentForegroundListener);
     getStyleable().getParent().removePropertyChangeListener("font", parentFontListener);
     getStyleable().getParent().removePropertyChangeListener("opaque", parentOpaqueListener);
     }
    
     }*/
    private class ParentListener implements HierarchyListener {

        /**
         * Called when the hierarchy has been changed. To discern the actual type of change, call
         * <code>HierarchyEvent.getChangeFlags()</code>.
         *
         * @see HierarchyEvent#getChangeFlags()
         *
         */
        @Override
        public void hierarchyChanged(HierarchyEvent e) {
            //System.out.println("hierarchyChanged");
            // optimize??
            getStyleable().getParent().removePropertyChangeListener("background", parentBackgroundListener);
            getStyleable().getParent().removePropertyChangeListener("foreground", parentForegroundListener);
            getStyleable().getParent().removePropertyChangeListener("font", parentFontListener);
            getStyleable().getParent().removePropertyChangeListener("opaque", parentOpaqueListener);
            getStyleable().getParent().addPropertyChangeListener("background", parentBackgroundListener);
            getStyleable().getParent().addPropertyChangeListener("foreground", parentForegroundListener);
            getStyleable().getParent().addPropertyChangeListener("font", parentFontListener);
            getStyleable().getParent().addPropertyChangeListener("opaque", parentOpaqueListener);
        }
    }

    private class ParentBackgroundListener implements PropertyChangeListener {

        /**
         * This method gets called when a bound property is changed.
         *
         * @param evt A PropertyChangeEvent object describing the event source and the property that has changed.
         *
         */
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            getStyleable().setDefaultBackground((Color) evt.getNewValue());
        }
    }

    private class ParentForegroundListener implements PropertyChangeListener {

        /**
         * This method gets called when a bound property is changed.
         *
         * @param evt A PropertyChangeEvent object describing the event source and the property that has changed.
         *
         */
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            getStyleable().setDefaultForeground((Color) evt.getNewValue());
        }
    }

    private class ParentFontListener implements PropertyChangeListener {

        /**
         * This method gets called when a bound property is changed.
         *
         * @param evt A PropertyChangeEvent object describing the event source and the property that has changed.
         *
         */
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            getStyleable().setDefaultFont((Font) evt.getNewValue());
        }
    }

    private class ParentOpaqueListener implements PropertyChangeListener {

        /**
         * This method gets called when a bound property is changed.
         *
         * @param evt A PropertyChangeEvent object describing the event source and the property that has changed.
         *
         */
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            getStyleable().setDefaultOpaque(((Boolean) evt.getNewValue()));
        }
    }
}
