/*
 * LocaleCellRenderer.java
 *
 * Created on 5. März 2003, 18:36
 */

package org.softsmithy.lib.swing;

import java.util.*;

/**
 *
 * @author  puce
 */
public class LocaleCellRenderer implements CellRenderer {
  
  /** Creates a new instance of LocaleCellRenderer */
  public LocaleCellRenderer() {
  }
  
  public Object getDisplayValue(Object value, Locale locale) {
    String displayName = null;
    if (value != null){
      if (! (value instanceof Locale)){
        throw new IllegalArgumentException("value must be an instance of Locale");
      }
      displayName = ((Locale) value).getDisplayName(locale);
    }
    return displayName;
  }
  
}
