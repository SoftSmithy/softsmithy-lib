/*
 * Style.java
 *
 * Created on 9. Januar 2003, 18:11
 */

package org.softsmithy.lib.swing.customizer;

import java.awt.*;
import java.util.*;

/**
 *
 * @author  puce
 */
public interface Style {
  
  String getName(Locale locale);
  Color getBackground();
  Color getForeground();
  boolean isOpaque();
  Font getFont();
  boolean isNull();
  StyleProvider getStyleProvider();
  
  
}
