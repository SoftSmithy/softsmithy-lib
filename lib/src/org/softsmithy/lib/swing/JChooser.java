package org.softsmithy.lib.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class JChooser extends JDialog{
  
  private JComponent chooserPane;
  private JPanel buttonPane;
  
  public JChooser(Component parent, String title, boolean modal, JComponent chooserPane) throws HeadlessException {
    super(JOptionPane.getFrameForComponent(parent), title, modal);
    this.chooserPane = chooserPane;
    
    Container contentPane = getContentPane();
    contentPane.setLayout(new BorderLayout());
    contentPane.add(chooserPane, BorderLayout.CENTER);
    
        /*
         * Create Lower button panel
         */
    buttonPane = new JPanel();
    buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
    
    
    
    // The following few lines are used to register esc to close the dialog
    //    Action cancelKeyAction = new AbstractAction() {
    //      public void actionPerformed(ActionEvent e) {
    //        ((AbstractButton)e.getSource()).fireActionPerformed(e);
    //      }
    //    };
    //    KeyStroke cancelKeyStroke = KeyStroke.getKeyStroke((char)KeyEvent.VK_ESCAPE, false);
    //    InputMap inputMap = cancelButton.getInputMap(JComponent.
    //    WHEN_IN_FOCUSED_WINDOW);
    //    ActionMap actionMap = cancelButton.getActionMap();
    //    if (inputMap != null && actionMap != null) {
    //      inputMap.put(cancelKeyStroke, "cancel");
    //      actionMap.put("cancel", cancelKeyAction);
    //    }
    // end esc handling
    
    contentPane.add(buttonPane, BorderLayout.SOUTH);
    
    applyComponentOrientation(((parent == null) ? getRootPane() : parent).getComponentOrientation()); // needed??
    
    pack();
    setLocationRelativeTo(parent);
  }
  
//  public void show() {
//    initialColor = chooserPane.getColor();
//    super.show();
//  }
//  
//  public void reset() {
//    chooserPane.setColor(initialColor);
//  }
  public void setButtons(JButton[] buttons, JButton defaultButton){
    buttonPane.removeAll();
    for (int i=0; i<buttons.length; i++){
      buttonPane.add(buttons[i]);
    }
    getRootPane().setDefaultButton(defaultButton);
    //pack();
  }

//  static class Closer extends WindowAdapter implements Serializable{
//    public void windowClosing(WindowEvent e) {
//      Window w = e.getWindow();
//      w.hide();
//    }
//  }

//  static class DisposeOnClose extends ComponentAdapter implements Serializable{
//    public void componentHidden(ComponentEvent e) {
//      Window w = (Window)e.getComponent();
//      w.dispose();
//    }
//  }

}



