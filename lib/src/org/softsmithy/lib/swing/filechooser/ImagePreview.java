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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

/**
 * A utility class for Files. <br>
 * Adapted from:
 * http://java.sun.com/docs/books/tutorial/uiswing/components/example-swing/index.html#FileChooserDemo2
 * <br>
 * Note: The API may change in a future version!
 *
 * @author    Florian Brunner
 */

public class ImagePreview extends JComponent
     implements PropertyChangeListener {
  private ImageIcon thumbnail = null;
  private File file = null;

  /**
   * Creates a new ImagePreview.
   *
   * @param fc  a file chooser
   */
  public ImagePreview(JFileChooser fc) {
    setPreferredSize(new Dimension(100, 50));
    fc.addPropertyChangeListener(this);
  }

  /**
   * Loads the image.
   */
  public void loadImage() {
    if (file == null) {
      return;
    }

    ImageIcon tmpIcon = new ImageIcon(file.getPath());
    if (tmpIcon.getIconWidth() > 90) {
      thumbnail = new ImageIcon(tmpIcon.getImage().
          getScaledInstance(90, -1,
          Image.SCALE_DEFAULT));
    } else {
      thumbnail = tmpIcon;
    }
  }

  /**
   * This method gets called when a bound property is changed.
   * Observes JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.
   *
   * @param e  a PropertyChangeEvent object
   */
  public void propertyChange(PropertyChangeEvent e) {
    String prop = e.getPropertyName();
    if (prop.equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)) {
      file = (File) e.getNewValue();
      if (isShowing()) {
        loadImage();
        repaint();
      }
    }
  }

  /**
   * Paints this component.
   *
   * @param g  the graphics context
   */
  public void paintComponent(Graphics g) {
    if (thumbnail == null) {
      loadImage();
    }
    if (thumbnail != null) {
      int x = getWidth() / 2 - thumbnail.getIconWidth() / 2;
      int y = getHeight() / 2 - thumbnail.getIconHeight() / 2;

      if (y < 0) {
        y = 0;
      }

      if (x < 5) {
        x = 5;
      }
      thumbnail.paintIcon(this, g, x, y);
    }
  }
}
