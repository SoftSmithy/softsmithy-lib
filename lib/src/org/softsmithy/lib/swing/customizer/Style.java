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
 * Style.java
 *
 * Created on 9. Januar 2003, 18:11
 */

package org.softsmithy.lib.swing.customizer;

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
