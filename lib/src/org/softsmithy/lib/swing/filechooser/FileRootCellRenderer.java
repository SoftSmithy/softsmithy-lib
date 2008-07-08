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
import org.softsmithy.lib.swing.HorizontalAlignment;

/**
 *
 * @author puce
 */
public class FileRootCellRenderer extends AbstractCellRenderer{

    public FileRootCellRenderer() {
        super(HorizontalAlignment.LEADING);
    }

    public Object getDisplayValue(Object value, Locale inLocale) {
        if (value != null){
            if (! (value instanceof File)){
                  throw new IllegalArgumentException("value must be an instance of File!");
            } else {
                return FileSystemView.getFileSystemView().getSystemDisplayName((File) value);
            }
        } else {
            return "";
        }
    }

    @Override
    public Icon getIcon(Object value) {
        if (value != null){
            if (! (value instanceof File)){
                  throw new IllegalArgumentException("value must be an instance of File!");
            } else {
                return FileSystemView.getFileSystemView().getSystemIcon((File) value);
            }
        } else {
            return null;
        }
    }
    
    

}
