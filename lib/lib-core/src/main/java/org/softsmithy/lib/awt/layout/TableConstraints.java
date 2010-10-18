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

package org.softsmithy.lib.awt.layout;

import java.awt.*;
import org.softsmithy.lib.swing.customizer.*;

public interface TableConstraints extends CustomizerConstraints{
  
  int getX();//Component comp);//, TableLayout tl);
  int getY();//Component comp);//, TableLayout tl);
  int getWidth();//Component comp);//, TableLayout tl);
  int getHeight();//Component comp);//, TableLayout tl);
  
  //Rectangle getAbsoluteBounds();//Component comp);//, TableLayout tl);
  Rectangle getRelativeBounds();//Component comp);//, TableLayout tl);
  void setRelativeBounds(Rectangle bounds);//, TableLayout tl);
  //void setAbsoluteBounds(Rectangle bounds);//, TableLayout tl);
  
}
