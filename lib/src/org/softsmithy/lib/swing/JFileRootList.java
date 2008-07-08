/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.softsmithy.lib.swing;

import javax.swing.JList;
import org.softsmithy.lib.swing.filechooser.FileRootCellRenderer;
import org.softsmithy.lib.swing.filechooser.FileRootComboBoxModel;

/**
 *
 * @author puce
 */
public class JFileRootList extends JList{

    public JFileRootList() {
        super(new FileRootComboBoxModel());
        setCellRenderer(new XDefaultListCellRenderer(new FileRootCellRenderer()));
    }

}
