/*
 * Styleable.java
 *
 * Created on 6. Mai 2003, 08:48
 */

package org.softsmithy.lib.swing.style;

import java.awt.*;
import java.awt.event.*;
import org.softsmithy.lib.swing.customizer.*;

/**
 *
 * @author  puce
 */
public interface Styleable {
  
  Style getStyle();
  void setStyle(Style style);
  Style getNoneStyle();
  Style getParentStyle();
  
  Container getParent();
  void addHierarchyListener(HierarchyListener listener);
  void removeHierarchyListener(HierarchyListener listener);
  void setDefaultBackground(Color background);
  void setDefaultForeground(Color foreground);
  void setDefaultFont(Font font);
  void setDefaultOpaque(boolean opaque);
  Color getDefaultBackground();
  Color getDefaultForeground();
  Font getDefaultFont();
  boolean isDefaultOpaque();
  
}
