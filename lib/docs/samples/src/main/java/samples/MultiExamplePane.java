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

/*
 *                  Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
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

import java.awt.*;
import java.awt.geom.*;
import java.net.*;
import javax.swing.*;
import org.softsmithy.lib.awt.*;
import org.softsmithy.lib.awt.layout.*;
import org.softsmithy.lib.swing.*;
import org.softsmithy.lib.swing.customizer.*;
import org.softsmithy.lib.swing.icon.*;



/**
 *
 * @author  puce
 */
public class MultiExamplePane extends JPanel {
  
  /** Creates new form Example1 */
  public MultiExamplePane() {
    // create a pane that supports customizers and "snap-to-grid" feature
    initComponents();
    // create a CustomizerLayout (here: InfiniteTableLayout); use the default grid size
    InfiniteTableLayout itl = new InfiniteTableLayout(pane);
    // set the layout
    pane.setCustomizerLayout(itl);
    
    //create and configure a customizer for a simple component
    JCustomizer simpleCustomizer = new JCustomizer(new JLabel("A Simple Component"));
    pane.addCustomizer(simpleCustomizer, new AbsoluteTableConstraints(0, 0, 140, 20, simpleCustomizer, itl));
    
    //create and configure an editable customizer for a label
    JLabelCustomizer labelCustomizer = new JLabelCustomizer("A Label Customizer - double click to edit!");
    pane.addCustomizer(labelCustomizer, new AbsoluteTableConstraints(50, 50, 270, 20, labelCustomizer, itl));
    
    //create and configure a customizer for a shape
    JXIconCustomizer starCustomizer1 = new JXIconCustomizer(new ShapeIcon(new Star(0, 0, 100, 100)));
    starCustomizer1.setForeground(Color.MAGENTA);
    pane.addCustomizer(starCustomizer1, new AbsoluteTableConstraints(200, 100, 100, 100, starCustomizer1, itl));
    ShapeIcon star2 = new ShapeIcon(new Star(0, 0, 100, 100));
    star2.setFilled(true);
    JXIconCustomizer starCustomizer2 = new JXIconCustomizer(star2);
    starCustomizer2.setOpaque(false);
    starCustomizer2.setForeground(Color.YELLOW);
    pane.addCustomizer(starCustomizer2, new AbsoluteTableConstraints(0, 300, 100, 100, starCustomizer2, itl));
    
    JXIconCustomizer ellipseCustomizer1 = new JXIconCustomizer(new ShapeIcon(new Ellipse2D.Float(0, 0, 100, 100)));
    ellipseCustomizer1.setOpaque(false);
    ellipseCustomizer1.setForeground(Color.BLUE);
    pane.addCustomizer(ellipseCustomizer1, new AbsoluteTableConstraints(0, 150, 100, 100, ellipseCustomizer1, itl));
    ShapeIcon ellipse2 = new ShapeIcon(new Ellipse2D.Float(0, 0, 100, 100));
    ellipse2.setFilled(true);
    JXIconCustomizer ellipseCustomizer2 = new JXIconCustomizer(ellipse2);
    ellipseCustomizer2.setForeground(Color.DARK_GRAY);
    pane.addCustomizer(ellipseCustomizer2, new AbsoluteTableConstraints(500, 150, 100, 100, ellipseCustomizer2, itl));
    
    ShapeIcon rectangle1 = new ShapeIcon(new Rectangle(0, 0, 150, 50));
    rectangle1.setFilled(true);
    JXIconCustomizer rectangleCustomizer1 = new JXIconCustomizer(rectangle1);
    rectangleCustomizer1.setForeground(Color.RED);
    rectangleCustomizer1.setOpaque(false);
    pane.addCustomizer(rectangleCustomizer1, new AbsoluteTableConstraints(200,  300, 150, 50, rectangleCustomizer1, itl));
    JXIconCustomizer rectangleCustomizer2 = new JXIconCustomizer(new ShapeIcon(new Rectangle(0, 0, 150, 50)));
    rectangleCustomizer2.setForeground(Color.GREEN);
    rectangleCustomizer2.setOpaque(false);
    pane.addCustomizer(rectangleCustomizer2, new AbsoluteTableConstraints(400,  400, 150, 50, rectangleCustomizer2, itl));
    
    JLine2DCustomizer lineCustomizer1 = new JLine2DCustomizer();
    //lineCustomizer1.setOrientation(getOrientation());
    lineCustomizer1.setThickness(10);
    lineCustomizer1.setColor(Color.CYAN);
    pane.addCustomizer(lineCustomizer1, new AbsoluteTableConstraints(200,  400, 150, 50, lineCustomizer1, itl));
    JLine2DCustomizer lineCustomizer2 = new JLine2DCustomizer();
    lineCustomizer2.setOrientation(Line2DOrientation.VERTICAL);
    lineCustomizer2.setThickness(20);
    lineCustomizer2.setColor(Color.ORANGE);
    pane.addCustomizer(lineCustomizer2, new AbsoluteTableConstraints(400,  200, 50, 150, lineCustomizer2, itl));
    try{
      //      JXIconCustomizer logoCustomizer = new JXIconCustomizer(getImage(new URL(getDocumentBaseDir() + "logo.jpg")));
      //      pane.addCustomizer(logoCustomizer, new AbsoluteTableConstraints(150, 150, 200, 170, logoCustomizer, itl));
    } catch(Exception ex){
      ex.printStackTrace();
    }
    validate();
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane = new javax.swing.JScrollPane();
        pane = new org.softsmithy.lib.swing.JCustomizerPane();

        setLayout(new java.awt.BorderLayout());

        jScrollPane.setViewportView(pane);

        add(jScrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
  
  
  
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane;
    private org.softsmithy.lib.swing.JCustomizerPane pane;
    // End of variables declaration//GEN-END:variables
  
}
