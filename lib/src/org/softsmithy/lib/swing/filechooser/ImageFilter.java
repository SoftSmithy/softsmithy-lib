/*
 *                  Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

package org.softsmithy.lib.swing.filechooser;

import java.io.File;
import org.softsmithy.lib.io.Files;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 * Accept all directories and all gif, jpg, or tiff files. <br>
 * Adapted from:
 * http://java.sun.com/docs/books/tutorial/uiswing/components/example-swing/index.html#FileChooserDemo2
 * <br>
 *
 * @author    Florian Brunner
 */
public class ImageFilter extends FileFilter {

  /**
   * Whether the given file is accepted by this filter.
   *
   * @param f  the given file
   * @return   true is accepted; false otherwise
   */
  public boolean accept(File f) {
    if (f.isDirectory()) {
      return true;
    }

    String extension = Files.getExtension(f);
    if (extension != null) {
      if (extension.equalsIgnoreCase(Files.TIFF) ||
          extension.equalsIgnoreCase(Files.TIF) ||
          extension.equalsIgnoreCase(Files.JPEG) ||
          extension.equalsIgnoreCase(Files.JPG) ||
          extension.equalsIgnoreCase(Files.GIF)) {
        return true;
      } else {
        return false;
      }
    }

    return false;
  }

  /**
   * The description of this filter.
   *
   * @return   the description of this filter
   */
  public String getDescription() {
    return "Just Images";
  }
}
