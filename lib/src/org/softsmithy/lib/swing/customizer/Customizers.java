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
  
  private static final ResourceBundleCache CACHE = new ResourceBundleCache("org.softsmithy.lib.swing.customizer.Customizers");
  
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
