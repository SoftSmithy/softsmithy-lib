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
 * CustomizerLayout.java
 *
 * Created on 2. September 2002, 19:01
 */

package org.softsmithy.lib.swing.customizer;

import java.awt.*;
import org.softsmithy.lib.awt.layout.*;

/**
 *
 * @author  puce
 */
public interface CustomizerLayout extends LayoutManager2 {
  
  CustomizerConstraints getConstraints(Component component);
  
  void setConstraints(Component component, CustomizerConstraints constraint);
  
  void layoutComponent(Container parent, Component comp);
  
}
