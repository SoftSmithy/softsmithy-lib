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
 * JMultiSplitPane.java
 *
 * Created on 11. Oktober 2002, 17:03
 */
package org.softsmithy.lib.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.beans.*;
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.swing.multisplit.*;
import org.softsmithy.lib.swing.event.*;

/**
 *
 * @author  puce
 */
public class JMultiSplitPane extends JStyledPanel {

    private List splitPanes = new ArrayList();
    private Map fireLocationChangedListeners = new HashMap();
    private List dividerLocationListeners = new ArrayList();
    /** Holds value of property orientation. */
    private SplitOrientation splitOrientation;

    public JMultiSplitPane() {
        this(2);
    }

    /** Creates new form JMultiSplitPane */
    public JMultiSplitPane(int initNumber) {
        this(initNumber, SplitOrientation.HORIZONTAL);
    }

    /** Creates new form JMultiSplitPane */
    public JMultiSplitPane(SplitOrientation splitOrientation) {
        this(2, splitOrientation);
    }

    /** Creates new form JMultiSplitPane */
    public JMultiSplitPane(int initNumber, SplitOrientation splitOrientation) {
        initComponents();
        // at least 2 panes (= 1 SplitPane)
        JXSplitPane splitPane = new JXSplitPane();
        splitPane.setResizeWeight(0.0);
        splitPane.setOpaque(false);
        //splitPane.setDividerLocation(0.5);
        this.add(BorderLayout.CENTER, splitPane);
        splitPane.setStyle(splitPane.getParentStyle());
        splitPanes.add(splitPane);
        registerFireLocationChangedListeners();
        setSplitOrientation(splitOrientation);
        for (int i = 2; i < initNumber; i++) {
            splitLastPane();
        }
    }

    public void splitLastPane() {
        splitPane(splitPanes.size(), false);
    }

    public void splitPane(int index, boolean shiftComponent) {
        JXSplitPane pane = new JXSplitPane(getSplitOrientation().getSplitPaneConstant());
        pane.setResizeWeight(1);
        //pane.setOpaque(false);

        //pane.setDividerLocation(0.5);
        //    pane.setBorder(null);
        JXSplitPane splitPane;
        if (index == splitPanes.size()) {
            splitPane = (JXSplitPane) splitPanes.get(index - 1);
            Component comp = splitPane.getRightComponent();
            if (shiftComponent) {
                pane.setRightComponent(comp);
            } else {
                pane.setLeftComponent(comp);
            }
            splitPanes.add(index, pane);
        } else {
            splitPane = (JXSplitPane) splitPanes.get(index);
            Component comp = splitPane.getRightComponent();
            pane.setRightComponent(comp);
            if (shiftComponent) {
                pane.setLeftComponent(splitPane.getLeftComponent());
                splitPane.setLeftComponent(null);
            }
            splitPanes.add(index + 1, pane);
        }
        splitPane.setRightComponent(pane);
        pane.setStyle(pane.getParentStyle());
        registerFireLocationChangedListeners();
    }

    public void setComponent(int index, Component component) {
        if (index < splitPanes.size()) {
            ((JXSplitPane) splitPanes.get(index)).setLeftComponent(component);
        } else {
            ((JXSplitPane) splitPanes.get(index - 1)).setRightComponent(component);
        }
    //repaint();
    }

    public Component getPane(int index) {
        Component component;
        if (index < splitPanes.size()) {
            component = ((JXSplitPane) splitPanes.get(index)).getLeftComponent();
        } else {
            component = ((JXSplitPane) splitPanes.get(index - 1)).getRightComponent();
        }
        return component;
    }

    public int getPanesCount() {
        return splitPanes.size() + 1;
    }

    public int getDividerLocation(int index) {
        return ((JXSplitPane) splitPanes.get(index)).getDividerLocation();
    }

    public void setDividerLocation(int index, int location) {
        ((JXSplitPane) splitPanes.get(index)).setDividerLocation(location);
        fireLocationChanged(new DividerLocationEvent(this, index, location));
    }

    public int getPreferredSize(int index) {
        return getSplitOrientation().getPreferredSize(getPane(index));
    }

    public int getSize(int index) {
        return getSplitOrientation().getSize(getPane(index));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
  private void initComponents() {//GEN-BEGIN:initComponents



    setLayout(new java.awt.BorderLayout());



  }//GEN-END:initComponents

    /** Getter for property orientation.
     * @return Value of property orientation.
     *
     */
    public SplitOrientation getSplitOrientation() {
        return this.splitOrientation;
    }

    /** Setter for property orientation.
     * @param orientation New value of property orientation.
     *
     */
    public void setSplitOrientation(SplitOrientation splitOrientation) {
        this.splitOrientation = splitOrientation;
        for (int i = 0; i < splitPanes.size(); i++) {
            ((JXSplitPane) splitPanes.get(i)).setOrientation(splitOrientation.getSplitPaneConstant());
        }
    }

    public void addDividerLocationListener(DividerLocationListener listener) {
        dividerLocationListeners.add(listener);
    }

    public void removeDividerLocationListener(DividerLocationListener listener) {
        dividerLocationListeners.remove(listener);
    }

    private void fireLocationChanged(DividerLocationEvent e) {
        for (int i = 0; i < dividerLocationListeners.size(); i++) {
            ((DividerLocationListener) dividerLocationListeners.get(i)).locationChanged(e);
        }
    }

    private void registerFireLocationChangedListeners() {
        for (int i = 0; i < splitPanes.size(); i++) {
            JSplitPane splitPane = (JSplitPane) splitPanes.get(i);
            if (fireLocationChangedListeners.containsKey(splitPane)) {
                FireLocationChangedListener listener = (FireLocationChangedListener) fireLocationChangedListeners.get(splitPane);
                if (listener.getIndex() != i) {
                    listener.setIndex(i);
                }
            } else {
                FireLocationChangedListener listener = new FireLocationChangedListener(i);
                splitPane.addPropertyChangeListener("dividerLocation", listener);
                fireLocationChangedListeners.put(splitPane, listener);
            }
        }
    }

    private void unregisterFireLocationChangedListener(JSplitPane splitPane) {
        if (fireLocationChangedListeners.containsKey(splitPane)) {
            FireLocationChangedListener listener = (FireLocationChangedListener) fireLocationChangedListeners.get(splitPane);
            splitPane.removePropertyChangeListener("dividerLocation", listener);
            fireLocationChangedListeners.remove(splitPane);
        }
    }

    private class FireLocationChangedListener implements PropertyChangeListener {

        /** Holds value of property index. */
        private int index;

        public FireLocationChangedListener(int index) {
            this.index = index;
        }

        /** Getter for property index.
         * @return Value of property index.
         *
         */
        public int getIndex() {
            return this.index;
        }

        public void propertyChange(PropertyChangeEvent evt) {
            fireLocationChanged(new DividerLocationEvent(JMultiSplitPane.this, getIndex(), ((Integer) evt.getNewValue()).intValue()));
        }

        /** Setter for property index.
         * @param index New value of property index.
         *
         */
        public void setIndex(int index) {
            this.index = index;
        }
    }
  // Variables declaration - do not modify//GEN-BEGIN:variables

  // End of variables declaration//GEN-END:variables
}
