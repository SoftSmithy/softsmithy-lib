/*
 * Customizers.java
 *
 * Created on 31. Januar 2003, 19:43
 */

package org.softsmithy.lib.swing.customizer;

import java.util.*;
import org.softsmithy.lib.util.*;

/**
 *
 * @author  puce
 */
public class Customizers {
  
  private static final ResourceBundleCache CACHE = new ResourceBundleCache("org.softsmithy.lib.swing.customizer");
  
  /** Creates a new instance of Customizers */
  private Customizers() {
  }
  
  public static String getParentStyleName(Locale locale){
    return CACHE.getBundle(locale).getString("parentStyle");
  }
  
  public static String getNoneStyleName(Locale locale){
    return CACHE.getBundle(locale).getString("noneStyle");
  }
  
}
