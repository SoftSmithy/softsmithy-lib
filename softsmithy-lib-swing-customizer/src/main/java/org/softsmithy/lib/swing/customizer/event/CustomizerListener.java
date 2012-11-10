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
 * CustomizerListener.java
 *
 * Created on 30. August 2002, 16:40
 */

package org.softsmithy.lib.swing.customizer.event;

import java.util.*;

/**
 *
 * @author  puce
 */
public interface CustomizerListener extends EventListener {
  
  
  void customizerResetBoundsRel(CustomizerEvent e);
  void customizerReshapeRel(CustomizerEvent e);
//  void customizerDelete(CustomizerEvent e);
  
}
