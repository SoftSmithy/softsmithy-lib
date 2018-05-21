/*
 * MultiExampleFrame2.java
 *
 * Created on 1. Juni 2004, 18:46
 */

package examples.swing.customizer;

/**
 *
 * @author  puce
 */
public class MultiExampleFrame extends javax.swing.JFrame {
  
  /** Creates new form MultiExampleFrame2 */
  public MultiExampleFrame() {
    initComponents();
    setContentPane(new MultiExamplePane());
    setSize(640, 480);
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  private void initComponents() {//GEN-BEGIN:initComponents

    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        exitForm(evt);
      }
    });

    pack();
  }//GEN-END:initComponents
  
  /** Exit the Application */
  private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
    System.exit(0);
  }//GEN-LAST:event_exitForm
  
  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    new MultiExampleFrame().show();
  }
  
  // Variables declaration - do not modify//GEN-BEGIN:variables
  // End of variables declaration//GEN-END:variables
  
}