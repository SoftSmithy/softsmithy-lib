/*
 * ResourceBundleManager.java
 *
 * Created on 7. Oktober 2002, 19:16
 */

package org.softsmithy.lib.util;

import java.util.*;

/**
 *
 * @author  puce
 */
public class ResourceBundleCache {
  
  private final String baseName;
  private final Map resourceBundles = new HashMap();
  
  /** Creates a new instance of ResourceBundleManager */
  public ResourceBundleCache(String baseName) {
    this.baseName = baseName;
  }
  
  public ResourceBundle getBundle(Locale locale){
    ResourceBundle rb;
    if (! resourceBundles.containsKey(locale)){
      rb = ResourceBundle.getBundle(baseName, locale);
      resourceBundles.put(locale, rb);
    } else {
      rb = (ResourceBundle) resourceBundles.get(locale);
    }
    return rb;
  }
  
}
