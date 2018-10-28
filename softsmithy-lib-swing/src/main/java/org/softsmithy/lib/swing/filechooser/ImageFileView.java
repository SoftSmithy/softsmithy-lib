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
import javax.swing.*;
import javax.swing.filechooser.*;
import org.softsmithy.lib.io.Files;

/**
 * Some image file views. <br>
 * Adapted from:
 * https://docs.oracle.com/javase/tutorial/uiswing/examples/components/#FileChooserDemo2
 * <br>
 *
 * @author    Florian Brunner
 */

public class ImageFileView extends FileView {
  private final ImageIcon jpgIcon = new ImageIcon("images/jpgIcon.gif");
  private final ImageIcon gifIcon = new ImageIcon("images/gifIcon.gif");
  private final ImageIcon tiffIcon = new ImageIcon("images/tiffIcon.gif");
  
  /**
   * The name of the file. Normally this would be simply f.getName()
   *
   * @param f  the file
   * @return   the name
   */
  @Override
  public String getName(File f) {
    // let the L&F FileView figure this out
    return null;
  }
  
  /**
   * Gets a human readable description of the file.
   *
   * @param f  the file
   * @return   a human readable description of the file
   */
  @Override
  public String getDescription(File f) {
    // let the L&F FileView figure this out
    return null;
  }
  
  /**
   * Whether the directory is traversable or not.
   * This might be useful, for example, if you want a directory to represent a
   * compound document and don't want the user to descend into it.
   *
   * @param f  the file
   * @return   true if traversable; false otherwise
   */
  @Override
  public Boolean isTraversable(File f) {
    // let the L&F FileView figure this out
    return null;
  }
  
  /**
   * A human readable description of the type of some image files.
   *
   * @param f  the image file
   * @return   a human readable description
   */
  @Override
  public String getTypeDescription(File f) {
    String extension = Files.getExtension(f);
    String type = null;
    
    if (extension != null) {
      if (extension.equalsIgnoreCase(Files.JPEG) ||
      extension.equalsIgnoreCase(Files.JPG)) {
        type = "JPEG Image";
      } else if (extension.equalsIgnoreCase(Files.GIF)) {
        type = "GIF Image";
      } else if (extension.equalsIgnoreCase(Files.TIFF) ||
      extension.equalsIgnoreCase(Files.TIF)) {
        type = "TIFF Image";
      }
    }
    return type;
  }
  
  /**
   * The icon that represents this image file in the JFileChooser.
   *
   * @param f  the image file
   * @return   the icon that represents this image file in the JFileChooser
   */
  @Override
  public Icon getIcon(File f) {
    String extension = Files.getExtension(f);
    Icon icon = null;
    
    if (extension != null) {
      if (extension.equalsIgnoreCase(Files.JPEG) ||
      extension.equalsIgnoreCase(Files.JPG)) {
        icon = jpgIcon;
      } else if (extension.equalsIgnoreCase(Files.GIF)) {
        icon = gifIcon;
      } else if (extension.equalsIgnoreCase(Files.TIFF) ||
      extension.equalsIgnoreCase(Files.TIF)) {
        icon = tiffIcon;
      }
    }
    return icon;
  }
}
