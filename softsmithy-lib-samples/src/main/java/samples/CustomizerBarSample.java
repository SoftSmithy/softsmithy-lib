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

package samples;

import org.softsmithy.lib.swing.customizer.JCustomizer;
import org.softsmithy.lib.swing.customizer.JCustomizerPane;
import org.softsmithy.lib.swing.customizer.JLabelCustomizer;
import org.softsmithy.lib.swing.customizer.JTabbedCustomizerBar;
import org.softsmithy.lib.swing.customizer.JTableCustomizerBar;
import org.softsmithy.lib.swing.customizer.layout.AbsoluteTableConstraints;
import org.softsmithy.lib.swing.customizer.layout.InfiniteTableLayout;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;


public class CustomizerBarSample extends javax.swing.JFrame {

    /** Creates new form SimpleSingleCustomizerSample */
    public CustomizerBarSample() {
        initComponents();
        // create a pane that supports customizers and "snap-to-grid" feature
        JCustomizerPane pane = new JCustomizerPane();
        // create a CustomizerLayout
        final InfiniteTableLayout itl = new InfiniteTableLayout(10, 10, pane);
        // set the layout
        pane.setCustomizerLayout(itl);

        // Create a CustomizerBar, which can contain other CustomizerBars
        JTabbedCustomizerBar tabbedCB = new JTabbedCustomizerBar();
        
        // Create a non-editable TableModel
        TableModel fixedLabelModel = new AbstractTableModel() {            
            private final String[] labels = new String[]{"first fixed label", 
            "second fixed label", "third fixed label"};
            @Override
            public int getColumnCount() {
                return 1;
            }
            @Override
            public int getRowCount() {
                return labels.length;
            }
            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return labels[rowIndex];
            }
            
            @Override
            public String getColumnName(int index){
                return "Fixed Labels";
            }
        };
        
        // Create a table based CustomizerBar
        JTableCustomizerBar fixedLabelsCB = new JTableCustomizerBar(fixedLabelModel) {
            @Override
            public void consumeSelection(JCustomizerPane pane, Point point) {
                // Create a simple customizer, which displays the selected text
                JCustomizer customizer = new JCustomizer(new JLabel((String) getTableModel().getValueAt(getSelection(),0)));
                // place the custoimzer at the appropriate location with a default size (110, 20)
                pane.addCustomizer(customizer,
                        new AbsoluteTableConstraints(point.x, point.y, 110, 20, customizer, itl));
                // display the customizer
                pane.revalidate(); 
                // avoid that a new customizer gets created whenever the user clicks in the JCustomizerPane
                clearSelection();
            }
        };
        // add this CustomizerBar to the the tabbed CustomizerBar
        tabbedCB.insertTab("Fixed Labels", null, fixedLabelsCB, "Fixed labels", 0);
        
        // Create an editable TableModel
        DefaultTableModel customLabelModel = new DefaultTableModel(
                new String[][]{{"first custom label"}, 
                {"second custom label"}, {"third custom label"}}, 
                new String[]{"Custom Labels"});
        
        // Create a table based CustomizerBar
        JTableCustomizerBar customLabelsCB = new JTableCustomizerBar(customLabelModel) {
            @Override
            public void consumeSelection(JCustomizerPane pane, Point point) {
                // Create a JLabelCustomizer, which displays the selected text. 
                // Double-click this customizer to change its text!
                JLabelCustomizer customizer = new JLabelCustomizer((String) getTableModel().getValueAt(getSelection(),0));
                // place the custoimzer at the appropriate location with a default size (120, 20)
                pane.addCustomizer(customizer,
                        new AbsoluteTableConstraints(point.x, point.y, 120, 20, customizer, itl));
                 // display the customizer
                pane.revalidate();
                // avoid that a new customizer gets created whenever the user clicks in the JCustomizerPane
                clearSelection();
            }
        };
        // add this CustomizerBar to the the tabbed CustomizerBar
        tabbedCB.insertTab("Custom Labels", null, customLabelsCB, "Custom labels", 1);
        
        // register the tabbed CustomizerBar the the JCustomizerPane
        pane.setCustomizerBar(tabbedCB);
        
        // configure rest of the GUI
        scrollPane.setViewportView(pane);
        splitPane.setRightComponent(tabbedCB);
        splitPane.setDividerLocation(750);
        setSize(1000,  600);
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPane = new javax.swing.JSplitPane();
        scrollPane = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("A CustomizerBar Sample");

        splitPane.setResizeWeight(1.0);
        splitPane.setLeftComponent(scrollPane);

        getContentPane().add(splitPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new CustomizerBarSample().setVisible(true);
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JSplitPane splitPane;
    // End of variables declaration//GEN-END:variables

   
    
}
