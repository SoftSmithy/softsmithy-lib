/*
 * StyleProvider.java
 *
 * Created on 30. Januar 2003, 18:53
 */

package org.softsmithy.lib.swing.customizer;

import java.util.*;

/**
 *
 * @author  puce
 */
public interface StyleProvider {
   
  Style getStyle(AbstractCustomizer customizer);
  String toString(Locale locale);
  
}
