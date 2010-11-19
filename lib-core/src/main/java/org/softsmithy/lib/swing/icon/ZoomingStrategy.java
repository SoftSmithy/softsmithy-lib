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
 * ZoomingStrategy.java
 *
 * Created on 13. Mai 2004, 00:13
 */

package org.softsmithy.lib.swing.icon;

import java.awt.*;

/**
 *
 * @author  puce
 * TODO: Check name
 */
public interface ZoomingStrategy {
  
  Dimension calculateDimension(int iconWidth, int iconHeight, int innerAreaWidth, int innerAreaHeight);
  
}
