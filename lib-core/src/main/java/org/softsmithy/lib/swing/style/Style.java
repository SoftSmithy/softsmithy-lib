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
 * Style.java
 *
 * Created on 9. Januar 2003, 18:11
 */

package org.softsmithy.lib.swing.style;

import java.awt.*;
import java.util.*;

/**
 *
 * @author  puce
 */
public interface Style {
  
  String getName(Locale locale);
  Color getBackground();
  Color getForeground();
  boolean isOpaque();
  Font getFont();
  boolean isNull();
  StyleProvider getStyleProvider();
  void startListening();
  void stopListening();
    
}
