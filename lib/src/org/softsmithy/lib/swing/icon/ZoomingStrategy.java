/*
 * ZoomingStrategy.java
 *
 * Created on 13. Mai 2004, 00:13
 */

package org.softsmithy.lib.swing.icon;

import java.awt.*;

/**
 *
 * @author  puce
 * TODO: Check name
 */
public interface ZoomingStrategy {
  
  Dimension calculateDimension(int iconWidth, int iconHeight, int innerAreaWidth, int innerAreaHeight);
  
}
