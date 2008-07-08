/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.lib.swing.filechooser;

import java.io.File;
import java.util.Arrays;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author puce
 */
public class FileRootComboBoxModel implements ComboBoxModel {

    private final DefaultComboBoxModel defaultComboBoxModel;

    public FileRootComboBoxModel() {
        File[] listRoots = File.listRoots();
        Arrays.sort(listRoots);
        defaultComboBoxModel = new DefaultComboBoxModel(listRoots);
    }

    public void setSelectedItem(Object anItem) {
       defaultComboBoxModel.setSelectedItem(anItem);
    }

    public File getSelectedItem() {
       return (File) defaultComboBoxModel.getSelectedItem();
    }

    public int getSize() {
        return defaultComboBoxModel.getSize();
    }

    public Object getElementAt(int index) {
        return defaultComboBoxModel.getElementAt(index);
    }

    public void addListDataListener(ListDataListener l) {
        defaultComboBoxModel.addListDataListener(l);
    }

    public void removeListDataListener(ListDataListener l) {
        defaultComboBoxModel.removeListDataListener(l);
    }
}
