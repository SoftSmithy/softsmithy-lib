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
import java.util.Locale;
import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;
import org.softsmithy.lib.swing.AbstractCellRenderer;

/**
 *
 * @author puce
 */
public class FileRootCellRenderer extends AbstractCellRenderer<File> {

    @Override
    public Object getDisplayValue(File value, Locale inLocale) {
        if (value != null) {
            String displayName = FileSystemView.getFileSystemView().getSystemDisplayName(value);
            if (!displayName.equals("")) {
                return displayName;
            } else {
                return value;
            }
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
