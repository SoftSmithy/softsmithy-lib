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
