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

/*
 * Styleable.java
 *
 * Created on 6. Mai 2003, 08:48
 */

package org.softsmithy.lib.swing.style;

import java.awt.*;
import java.awt.event.*;

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
