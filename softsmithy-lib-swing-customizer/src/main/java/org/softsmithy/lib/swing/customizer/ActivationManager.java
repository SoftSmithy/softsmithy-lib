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
 * ActivationManager.java
 *
 * Created on 11. Dezember 2002, 17:49
 */
package org.softsmithy.lib.swing.customizer;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.softsmithy.lib.swing.customizer.event.CustomizerSelectionEvent;
import org.softsmithy.lib.swing.customizer.event.CustomizerSelectionListener;

/**
 *
 * @author puce
 */
public class ActivationManager implements FocusListener, CustomizerSelectionListener {

    private JCustomizerPane activePane;
    /**
     * Utility field holding list of ActivationListeners.
     */
//  private transient ArrayList activationListenerList;
    private final Set<JCustomizerPane> panes = new HashSet<>();
    /**
     * Utility field used by bound properties.
     */
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Creates a new instance of ActivationManager
     */
    public ActivationManager() {
    }

    public void setActiveCustomizerPane(JCustomizerPane pane) {
        if (!((pane == null && activePane == null) || pane.equals(activePane))) {
            JCustomizerPane oldActivePane = this.activePane;
            if (pane != null) {
                pane.setActive(true);
//        pane.setBorder(BorderFactory.createLineBorder(Color.GREEN));
            }
            if (activePane != null) {
                activePane.setActive(false);
//        setDefaultBorder(activePane);
            }
            activePane = pane;
            propertyChangeSupport.firePropertyChange("active", oldActivePane, activePane);
        }
    }

    public JCustomizerPane getActiveCustomizerPane() {
        return activePane;
    }

    @Override
    public void selectionChanged(CustomizerSelectionEvent e) {
        if (e.getActiveCustomizer() != null) {
            if (panes.contains(e.getActiveCustomizer().getParentCustomizerPane())) {
                setActiveCustomizerPane(e.getActiveCustomizer().getParentCustomizerPane());//.equals(JCustomizerPane.this)){
            }
        }
    }

    public void addCustomizerPane(JCustomizerPane pane) {
        pane.addFocusListener(this);
        pane.getSelectionManager().addCustomizerSelectionListener(this);
        pane.setActivationBorderEnabled(true);
//    setDefaultBorder(pane);
        panes.add(pane);
    }

    /**
     * Invoked when a component gains the keyboard focus.
     *
     */
    @Override
    public void focusGained(FocusEvent e) {
        setActiveCustomizerPane((JCustomizerPane) e.getComponent());
    }

    /**
     * Invoked when a component loses the keyboard focus.
     *
     */
    @Override
    public void focusLost(FocusEvent e) {
    }

    public void removeCustomizerPane(JCustomizerPane pane) {
        pane.removeFocusListener(this);
        panes.remove(pane);
        boolean same = false;
        for (Iterator i = panes.iterator(); i.hasNext();) {
            if (((JCustomizerPane) i.next()).getSelectionManager().equals(pane.getSelectionManager())) {
                same = true;
                break;
            }
        }
        if (!same) {
            pane.getSelectionManager().removeCustomizerSelectionListener(this);
        }
    }

    /**
     * Adds a PropertyChangeListener to the listener list.
     *
     * @param l The listener to add.
     *
     */
    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener("active", l);
    }

    /**
     * Removes a PropertyChangeListener from the listener list.
     *
     * @param l The listener to remove.
     *
     */
    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener("active", l);
    }
//  private void setDefaultBorder(JCustomizerPane pane){
//    pane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
//  }
}
