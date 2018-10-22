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
public class FileRootComboBoxModel implements ComboBoxModel<File> {

    private final DefaultComboBoxModel<File> defaultComboBoxModel;

    public FileRootComboBoxModel() {
        File[] listRoots = File.listRoots();
        Arrays.sort(listRoots);
        defaultComboBoxModel = new DefaultComboBoxModel<>(listRoots);
    }

    @Override
    public void setSelectedItem(Object anItem) {
       defaultComboBoxModel.setSelectedItem(anItem);
    }

    @Override
    public File getSelectedItem() {
       return (File) defaultComboBoxModel.getSelectedItem();
    }

    @Override
    public int getSize() {
        return defaultComboBoxModel.getSize();
    }

    @Override
    public File getElementAt(int index) {
        return defaultComboBoxModel.getElementAt(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {
        defaultComboBoxModel.addListDataListener(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        defaultComboBoxModel.removeListDataListener(l);
    }
}
