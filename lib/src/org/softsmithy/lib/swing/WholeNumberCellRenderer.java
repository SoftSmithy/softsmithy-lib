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
