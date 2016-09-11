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
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is Examples of SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

/*
 * Example1.java
 *
 * Created on 9. M�rz 2003, 18:23
 */
package samples;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;
import java.util.LinkedHashSet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.softsmithy.lib.awt.Star;
import org.softsmithy.lib.swing.customizer.JCustomizer;
import org.softsmithy.lib.swing.customizer.JLabelCustomizer;
import org.softsmithy.lib.swing.customizer.JXIconCustomizer;
import org.softsmithy.lib.swing.icon.ShapeIcon;
import org.softsmithy.lib.swing.icon.XImageIcon;
import org.softsmithy.lib.swing.customizer.layout.AbsoluteTableConstraints;
import org.softsmithy.lib.swing.customizer.layout.InfiniteTableLayout;

/**
 *
 * @author puce
 */
public class MultiExamplePane2 extends JPanel {

    /**
     * Creates new form Example1
     */
    public MultiExamplePane2() {
        // create a pane that supports customizers and "snap-to-grid" feature
        initComponents();
        // create a CustomizerLayout (here: InfiniteTableLayout); use the default grid size
        InfiniteTableLayout itl = new InfiniteTableLayout(pane);
        // set the layout
        pane.setCustomizerLayout(itl);

        //create and configure a customizer for a simple component
        JCustomizer simpleCustomizer = new JCustomizer(new JLabel("A Simple Component"));
        simpleCustomizer.setCustomizableProperties(new LinkedHashSet<String>(Arrays.asList(
                new String[]{"x", "y", "width", "height"}))); // to allow SelectionManager to listen for these properties
        pane.addCustomizer(simpleCustomizer, new AbsoluteTableConstraints(0, 0, 140, 20, simpleCustomizer, itl));

        //create and configure an editable customizer for a label
        JLabelCustomizer labelCustomizer = new JLabelCustomizer("A Label Customizer - double click to edit!");
        labelCustomizer.setCustomizableProperties(new LinkedHashSet<String>(Arrays.asList(
                new String[]{"x", "y", "width", "height"}))); // to allow SelectionManager to listen for these properties
        pane.addCustomizer(labelCustomizer, new AbsoluteTableConstraints(50, 50, 270, 20, labelCustomizer, itl));

        //create and configure a customizer for a shape
        JXIconCustomizer starCustomizer = new JXIconCustomizer(new ShapeIcon(new Star(0, 0, 100, 100)));
        //starCustomizer.setFilled(true);
        //starCustomizer.setOpaque(false);
        starCustomizer.setForeground(Color.YELLOW);
        starCustomizer.setCustomizableProperties(new LinkedHashSet<String>(Arrays.asList(
                new String[]{"x", "y", "width", "height"}))); // to allow SelectionManager to listen for these properties
        pane.addCustomizer(starCustomizer, new AbsoluteTableConstraints(200, 100, 100, 100, starCustomizer, itl));

        JXIconCustomizer ellipseCustomizer = new JXIconCustomizer(new ShapeIcon(new Ellipse2D.Float(0, 0, 100, 100)));
        //ellipseCustomizer.setFilled(true);
        ellipseCustomizer.setOpaque(false);
        ellipseCustomizer.setForeground(Color.BLUE);
        ellipseCustomizer.setCustomizableProperties(new LinkedHashSet<String>(Arrays.asList(
                new String[]{"x", "y", "width", "height"}))); // to allow SelectionManager to listen for these properties
        pane.addCustomizer(ellipseCustomizer, new AbsoluteTableConstraints(0, 150, 100, 100, ellipseCustomizer, itl));

        try {
            JXIconCustomizer logoCustomizer = new JXIconCustomizer(new XImageIcon("logo.jpg"));
            logoCustomizer.setCustomizableProperties(new LinkedHashSet<String>(Arrays.asList(
                    new String[]{"x", "y", "width", "height"}))); // to allow SelectionManager to listen for these properties
            pane.addCustomizer(logoCustomizer, new AbsoluteTableConstraints(350, 250, 197, 167, logoCustomizer, itl));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        validate();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pane = new org.softsmithy.lib.swing.customizer.JCustomizerPane();

        setLayout(new java.awt.BorderLayout());
        add(pane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.softsmithy.lib.swing.customizer.JCustomizerPane pane;
    // End of variables declaration//GEN-END:variables
}
