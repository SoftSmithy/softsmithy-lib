/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

/*
 * ResourceBundleManager.java
 *
 * Created on 7. Oktober 2002, 19:16
 */

package org.softsmithy.lib.util;

import java.util.*;

/**
 * Not used anymore: ResourceBundle caches itself (see source). This was missunderstood before.
 * @author  puce
 */
class ResourceBundleCache {
  
  private final String baseName;
  private final Map<Locale, ResourceBundle> resourceBundles = new HashMap<Locale, ResourceBundle>();
  
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
