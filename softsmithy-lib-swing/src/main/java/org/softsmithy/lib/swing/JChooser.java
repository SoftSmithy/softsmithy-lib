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
package org.softsmithy.lib.swing;

import java.awt.*;
import javax.swing.*;
import org.softsmithy.lib.swing.chooser.*;

public abstract class JChooser extends JDialog {

    private JComponent chooserPane;
    private JPanel buttonPane;
    private Option option = Option.CANCEL;

    public JChooser(Component parent, String title, JComponent chooserPane) throws HeadlessException {
        this(parent, title, true, chooserPane);
    }

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

    public JComponent getChooserPane() {
        return chooserPane;
    }

    //  public void show() {
    //    initialColor = chooserPane.getColor();
    //    super.show();
    //  }
    //
    //  public void reset() {
    //    chooserPane.setColor(initialColor);
    //  }
    public void setButtons(JButton[] buttons, JButton defaultButton) {
        buttonPane.removeAll();
        for (int i = 0; i < buttons.length; i++) {
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
    public Option showUp() {
        super.show();
        return getOption();
    }

    /** Getter for property option.
     * @return Value of property option.
     *
     */
    protected Option getOption() {
        return this.option;
    }

    /** Setter for property option.
     * @param option New value of property option.
     *
     */
    protected void setOption(Option option) {
        this.option = option;
    }
}