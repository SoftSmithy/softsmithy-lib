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
 * StyleProvider.java
 *
 * Created on 30. Januar 2003, 18:53
 */

package org.softsmithy.lib.swing.style;

import java.util.*;

/**
 *
 * @author  puce
 */
public interface StyleProvider {
   
  Style getStyle(Styleable styleable);
  String toString(Locale locale);
  
}
