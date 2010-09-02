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
 * XIcon.java
 *
 * Created on 24. M�rz 2004, 00:41
 */

package org.softsmithy.lib.swing.icon;

import javax.swing.*;

/**
 *
 * @author  puce
 */
public interface XIcon extends Icon {
  
  public XIcon getScaledInstance(int newWidth, int newHeight);
  
}
