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
 * ParentStyleProvider.java
 *
 * Created on 31. Januar 2003, 19:32
 */

package org.softsmithy.lib.swing.customizer;

import java.util.*;
import org.softsmithy.lib.util.*;
import org.softsmithy.lib.swing.style.*;

/**
 *
 * @author  puce
 */
public class ParentStyleProvider implements StyleProvider, Singleton {
  
  public static final ParentStyleProvider INSTANCE = new ParentStyleProvider();
  
  /** Creates a new instance of ParentStyleProvider */
  private ParentStyleProvider() {
  }
  
  public Style getStyle(Styleable styleable) {
    return styleable.getParentStyle();
  }
  
  public String toString(Locale locale) {
    return Customizers.getParentStyleName(locale);
  }
  
}
