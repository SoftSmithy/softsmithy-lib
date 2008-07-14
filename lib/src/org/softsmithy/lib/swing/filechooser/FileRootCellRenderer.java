/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.lib.swing.filechooser;

import java.io.File;
import java.util.Locale;
import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;
import org.softsmithy.lib.swing.AbstractCellRenderer;

/**
 *
 * @author puce
 */
public class FileRootCellRenderer extends AbstractCellRenderer<File> {

    public Object getDisplayValue(File value, Locale inLocale) {
        if (value != null) {
            return FileSystemView.getFileSystemView().getSystemDisplayName(value);
        } else {
            return "";
        }
    }

    @Override
    public Icon getIcon(File value) {
        if (value != null) {
            return FileSystemView.getFileSystemView().getSystemIcon(value);
        } else {
            return null;
        }
    }
}
