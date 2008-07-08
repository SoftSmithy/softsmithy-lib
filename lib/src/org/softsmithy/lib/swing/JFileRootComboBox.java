/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.softsmithy.lib.swing;

import javax.swing.JComboBox;
import org.softsmithy.lib.swing.filechooser.FileRootCellRenderer;
import org.softsmithy.lib.swing.filechooser.FileRootComboBoxModel;

/**
 *
 * @author puce
 */
public class JFileRootComboBox extends JComboBox{

    public JFileRootComboBox() {
        super(new FileRootComboBoxModel());
        setRenderer(new XDefaultListCellRenderer(new FileRootCellRenderer()));
    }
    

}
