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
 * JWindowMenu.java
 *
 * Created on 15. Oktober 2002, 18:00
 */
package org.softsmithy.lib.swing;

import java.awt.event.*;
import java.beans.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author  puce
 */
class JWindowMenu extends JMenu {

    private ButtonGroup group = new ButtonGroup();
    private List buttons = new ArrayList();
    private Map items = new HashMap();
    private Map frames = new HashMap();
    private RadioListener listener = new RadioListener();
    private FrameListener framelistener = new FrameListener();

    /** Creates a new instance of JWindowMenu */
    public JWindowMenu() {
    }

    public void addWindow(JInternalFrame frame) {
        JRadioButtonMenuItem item = new JRadioButtonMenuItem();
        group.add(item);
        item.setText(group.getButtonCount() + " " + frame.getTitle());
        item.addActionListener(this.listener);
        item.setSelected(true);
        selectFrame(frame);
        buttons.add(item);
        items.put(frame, item);
        frames.put(item, frame);
        add(item);
    }

    public void removeWindow(JInternalFrame frame) {
        JRadioButtonMenuItem item = (JRadioButtonMenuItem) items.get(frame);
        remove(item);
        group.remove(item);
        items.remove(frame);
        frames.remove(item);
        buttons.remove(item);
        if (buttons.size() > 0) {
            ((JRadioButtonMenuItem) buttons.get(buttons.size() - 1)).doClick();
        }

    }

    private void ensureIsLast(JRadioButtonMenuItem item) {
        buttons.remove(item);
        buttons.add(item);
    }

    private void selectFrame(JInternalFrame frame) {
        JDesktopPane pane = frame.getDesktopPane();
        if (pane != null) {
            pane.setSelectedFrame(frame);
        } else {
            try {
                frame.setSelected(true);
            } catch (PropertyVetoException ex) {
            }
        }
    }

    private class RadioListener implements ActionListener {

        /** Invoked when an action occurs.
         *
         */
        public void actionPerformed(ActionEvent e) {
            JRadioButtonMenuItem item = (JRadioButtonMenuItem) e.getSource();
            ensureIsLast(item);
            selectFrame((JInternalFrame) frames.get(item));
        }
    }

    private class FrameListener extends InternalFrameAdapter {

        /** Invoked when an internal frame is activated.
         *
         */
        @Override
        public void internalFrameActivated(InternalFrameEvent e) {
            JRadioButtonMenuItem item = (JRadioButtonMenuItem) items.get(e.getSource());
            item.setSelected(true);
            ensureIsLast(item);
        }
    }
}
