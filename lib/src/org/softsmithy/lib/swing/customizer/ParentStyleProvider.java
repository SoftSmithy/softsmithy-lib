/*
 * ParentStyleProvider.java
 *
 * Created on 31. Januar 2003, 19:32
 */

package org.softsmithy.lib.swing.customizer;

import java.util.*;
import org.softsmithy.lib.util.*;

/**
 *
 * @author  puce
 */
public class ParentStyleProvider implements StyleProvider, Singleton {
  
  public static final ParentStyleProvider INSTANCE = new ParentStyleProvider();
  
  /** Creates a new instance of ParentStyleProvider */
  private ParentStyleProvider() {
  }
  
  public Style getStyle(AbstractCustomizer customizer) {
    return customizer.getParentStyle();
  }
  
  public String toString(Locale locale) {
    return Customizers.getParentStyleName(locale);
  }
  
}
