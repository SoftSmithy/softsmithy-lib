/*
 * CustomizerLayout.java
 *
 * Created on 2. September 2002, 19:01
 */

package org.softsmithy.lib.swing.customizer;

import java.awt.*;
import org.softsmithy.lib.awt.layout.*;

/**
 *
 * @author  puce
 */
public interface CustomizerLayout extends LayoutManager2 {
  
  CustomizerConstraints getConstraints(Component component);
  
  void setConstraints(Component component, CustomizerConstraints constraint);
  
  void layoutComponent(Container parent, Component comp);
  
}
