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
 * ParentStyleProvider.java
 *
 * Created on 31. Januar 2003, 19:32
 */

package org.softsmithy.lib.swing.style;

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
  
  public Style getStyle(Styleable styleable) {
    return styleable.getParentStyle();
  }
  
  public String toString(Locale locale) {
    return Styles.getParentStyleName(locale);
  }
  
}
