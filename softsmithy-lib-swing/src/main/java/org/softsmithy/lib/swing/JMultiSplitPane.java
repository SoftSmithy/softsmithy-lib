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
import org.softsmithy.lib.swing.event.*;
import org.softsmithy.lib.swing.multisplit.*;

/**
 *
 * @author puce
 */
public class JMultiSplitPane extends JStyledPanel {

    private final List<JXSplitPane> splitPanes = new ArrayList<>();
    private final Map<JXSplitPane, FireLocationChangedListener> fireLocationChangedListeners = new HashMap<>();
    private final List<DividerLocationListener> dividerLocationListeners = new ArrayList<>();
    /**
     * Holds value of property orientation.
     */
    private SplitOrientation splitOrientation;

    public JMultiSplitPane() {
        this(2);
    }

    /**
     * Creates new form JMultiSplitPane
     *
     * @param initNumber the initial number of panes
     */
    public JMultiSplitPane(int initNumber) {
        this(initNumber, SplitOrientation.HORIZONTAL);
    }

    /**
     * Creates new form JMultiSplitPane
     *
     * @param splitOrientation the split orientation
     */
    public JMultiSplitPane(SplitOrientation splitOrientation) {
        this(2, splitOrientation);
    }

    /**
     * Creates new form JMultiSplitPane
     *
     * @param initNumber the initial number of panes
     * @param splitOrientation the split orientation
     */
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
            splitPane = splitPanes.get(index - 1);
            Component comp = splitPane.getRightComponent();
            if (shiftComponent) {
                pane.setRightComponent(comp);
            } else {
                pane.setLeftComponent(comp);
            }
            splitPanes.add(index, pane);
        } else {
            splitPane = splitPanes.get(index);
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
            splitPanes.get(index).setLeftComponent(component);
        } else {
            splitPanes.get(index - 1).setRightComponent(component);
        }
        //repaint();
    }

    public Component getPane(int index) {
        Component component;
        if (index < splitPanes.size()) {
            component = splitPanes.get(index).getLeftComponent();
        } else {
            component = splitPanes.get(index - 1).getRightComponent();
        }
        return component;
    }

    public int getPanesCount() {
        return splitPanes.size() + 1;
    }

    public int getDividerLocation(int index) {
        return splitPanes.get(index).getDividerLocation();
    }

    public void setDividerLocation(int index, int location) {
        splitPanes.get(index).setDividerLocation(location);
        fireLocationChanged(new DividerLocationEvent(this, index, location));
    }

    public int getPreferredSize(int index) {
        return getSplitOrientation().getPreferredSize(getPane(index));
    }

    public int getSize(int index) {
        return getSplitOrientation().getSize(getPane(index));
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
  private void initComponents() {//GEN-BEGIN:initComponents



    setLayout(new java.awt.BorderLayout());



  }//GEN-END:initComponents

    /**
     * Getter for property orientation.
     *
     * @return Value of property orientation.
     *
     */
    public SplitOrientation getSplitOrientation() {
        return this.splitOrientation;
    }

    /**
     * Setter for property orientation.
     *
     * @param splitOrientation New value of property orientation.
     *
     */
    public void setSplitOrientation(SplitOrientation splitOrientation) {
        this.splitOrientation = splitOrientation;
        for (int i = 0; i < splitPanes.size(); i++) {
            splitPanes.get(i).setOrientation(splitOrientation.getSplitPaneConstant());
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
            (dividerLocationListeners.get(i)).locationChanged(e);
        }
    }

    private void registerFireLocationChangedListeners() {
        for (int i = 0; i < splitPanes.size(); i++) {
            JXSplitPane splitPane = splitPanes.get(i);
            if (fireLocationChangedListeners.containsKey(splitPane)) {
                FireLocationChangedListener listener = fireLocationChangedListeners.get(splitPane);
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

    private void unregisterFireLocationChangedListener(JXSplitPane splitPane) {
        if (fireLocationChangedListeners.containsKey(splitPane)) {
            FireLocationChangedListener listener = fireLocationChangedListeners.get(splitPane);
            splitPane.removePropertyChangeListener("dividerLocation", listener);
            fireLocationChangedListeners.remove(splitPane);
        }
    }

    private class FireLocationChangedListener implements PropertyChangeListener {

        /**
         * Holds value of property index.
         */
        private int index;

        public FireLocationChangedListener(int index) {
            this.index = index;
        }

        /**
         * Getter for property index.
         *
         * @return Value of property index.
         *
         */
        public int getIndex() {
            return this.index;
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            fireLocationChanged(new DividerLocationEvent(JMultiSplitPane.this, getIndex(), ((Integer) evt.getNewValue())));
        }

        /**
         * Setter for property index.
         *
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
