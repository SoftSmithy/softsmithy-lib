/*
 * Styleable.java
 *
 * Created on 6. Mai 2003, 08:48
 */

package org.softsmithy.lib.swing.style;

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
  
}
