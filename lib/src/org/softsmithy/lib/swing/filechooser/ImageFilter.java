package puce.swing.filechooser;

import java.io.File;
import puce.io.Files;
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
      if (extension.equals(Files.TIFF_CAPITAL) ||
          extension.equals(Files.TIFF_SMALL) ||
          extension.equals(Files.TIF_CAPITAL) ||
          extension.equals(Files.TIF_SMALL) ||
          extension.equals(Files.JPEG_CAPITAL) ||
          extension.equals(Files.JPEG_SMALL) ||
          extension.equals(Files.JPG_CAPITAL) ||
          extension.equals(Files.JPG_SMALL) ||
          extension.equals(Files.GIF_CAPITAL) ||
          extension.equals(Files.GIF_SMALL)) {
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
