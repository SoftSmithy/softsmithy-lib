package puce.swing.filechooser;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

import puce.io.Files;

/**
 * Some image file views. <br>
 * Adapted from:
 * http://java.sun.com/docs/books/tutorial/uiswing/components/example-swing/index.html#FileChooserDemo2
 * <br>
 *
 * @author    Florian Brunner
 */

public class ImageFileView extends FileView {
  private ImageIcon jpgIcon = new ImageIcon("images/jpgIcon.gif");
  private ImageIcon gifIcon = new ImageIcon("images/gifIcon.gif");
  private ImageIcon tiffIcon = new ImageIcon("images/tiffIcon.gif");

  /**
   * The name of the file. Normally this would be simply f.getName()
   *
   * @param f  the file
   * @return   the name
   */
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
  public String getTypeDescription(File f) {
    String extension = Files.getExtension(f);
    String type = null;

    if (extension != null) {
      if (extension.equals(Files.JPEG_CAPITAL) ||
          extension.equals(Files.JPEG_SMALL) ||
          extension.equals(Files.JPG_CAPITAL) ||
          extension.equals(Files.JPG_SMALL)) {
        type = "JPEG Image";
      } else if (extension.equals(Files.GIF_CAPITAL) ||
          extension.equals(Files.GIF_SMALL)) {
        type = "GIF Image";
      } else if (extension.equals(Files.TIFF_CAPITAL) ||
          extension.equals(Files.TIFF_SMALL) ||
          extension.equals(Files.TIF_CAPITAL) ||
          extension.equals(Files.TIF_SMALL)) {
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
  public Icon getIcon(File f) {
    String extension = Files.getExtension(f);
    Icon icon = null;

    if (extension != null) {
      if (extension.equals(Files.JPEG_CAPITAL) ||
          extension.equals(Files.JPEG_SMALL) ||
          extension.equals(Files.JPG_CAPITAL) ||
          extension.equals(Files.JPG_SMALL)) {
        icon = jpgIcon;
      } else if (extension.equals(Files.GIF_CAPITAL) ||
          extension.equals(Files.GIF_SMALL)) {
        icon = gifIcon;
      } else if (extension.equals(Files.TIFF_CAPITAL) ||
          extension.equals(Files.TIFF_SMALL) ||
          extension.equals(Files.TIF_CAPITAL) ||
          extension.equals(Files.TIF_SMALL)) {
        icon = tiffIcon;
      }
    }
    return icon;
  }
}
