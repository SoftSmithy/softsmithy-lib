/*
 * CellRenderer.java
 *
 * Created on 5. M�rz 2003, 16:34
 */

package org.softsmithy.lib.swing;

import java.util.*;

/**
 *
 * @author  puce
 */
public interface CellRenderer {
  Object getDisplayValue(Object value, Locale locale);
}
