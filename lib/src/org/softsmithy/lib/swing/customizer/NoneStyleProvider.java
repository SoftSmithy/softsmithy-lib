/*
 * NoneStyleProvider.java
 *
 * Created on 31. Januar 2003, 19:26
 */

package org.softsmithy.lib.swing.customizer;

import java.util.*;
import org.softsmithy.lib.util.*;

/**
 *
 * @author  puce
 */
public class NoneStyleProvider implements StyleProvider, Singleton {
  
  public static final NoneStyleProvider INSTANCE = new NoneStyleProvider();
  
  /** Creates a new instance of NoneStyleProvider */
  private NoneStyleProvider() {
  }
  
  public Style getStyle(AbstractCustomizer customizer) {
    return customizer.getNoneStyle();
  }
  
  public String toString(Locale locale) {
    return Customizers.getNoneStyleName(locale);
  }
  
}
