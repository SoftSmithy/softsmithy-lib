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
 * IntegerCellRenderer.java
 *
 * Created on 11. September 2003, 19:37
 */

package org.softsmithy.lib.swing;

import java.text.*;
import java.util.*;

/**
 *
 * @author  puce
 */
public class WholeNumberCellRenderer extends AbstractCellRenderer{
  
  /** Creates a new instance of IntegerCellRenderer */
  public WholeNumberCellRenderer() {
    super(HorizontalAlignment.TRAILING);
  }
  
  public Object getDisplayValue(Object value, Locale locale) {
    NumberFormat nf = NumberFormat.getIntegerInstance(locale);
    return nf.format(value);
  }
  
}
