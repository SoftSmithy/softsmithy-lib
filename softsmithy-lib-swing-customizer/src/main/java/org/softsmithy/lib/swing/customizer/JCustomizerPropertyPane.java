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
 * PropertyPane.java
 *
 * Created on 19. September 2002, 17:57
 */
package org.softsmithy.lib.swing.customizer;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.TableCellEditor;
import org.softsmithy.lib.swing.customizer.event.CustomizerSelectionEvent;
import org.softsmithy.lib.swing.customizer.event.CustomizerSelectionListener;

/**
 *
 * @author puce
 */
class JCustomizerPropertyPane extends JPanel implements CustomizerSelectionListener {

    private final JCustomizerPropertyTable table;
//    private static final String PROPERTIES_BUNDLE_BASE_NAME = "org.softsmithy.lib.swing.customizer.Properties";

    /**
     * Creates new form PropertyPane
     */
    public JCustomizerPropertyPane() {
        initComponents();
        table = new JCustomizerPropertyTable();
        add(BorderLayout.CENTER, new JScrollPane(table));
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    public void setDefaultCellEditor(Class<?> aClass, TableCellEditor editor) {
        table.setDefaultEditor(aClass, editor);
    }

    @Override
    public void selectionChanged(CustomizerSelectionEvent e) {
        table.getCustomizerPropertyTableModel().stopListening();
        //table.setModel(new CustomizerPropertyTableModel(new ArrayList(e.getCommonCustomizableProperties()), e.getActiveCustomizer(), ResourceBundle.getBundle(PROPERTIES_BUNDLE_BASE_NAME, getLocale()), getLocale()));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
