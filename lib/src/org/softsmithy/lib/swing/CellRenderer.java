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
 * CellRenderer.java
 *
 * Created on 5. März 2003, 16:34
 */

package org.softsmithy.lib.swing;

import java.util.*;

/**
 *
 * @author  puce
 * Note: with jdk 1.5 this interface might become parameterized!
 */
public interface CellRenderer {
    /**
     * Note: with jdk 1.5 the type of value might change to the parameterized type of this interface
     */
  Object getDisplayValue(Object value, Locale inLocale);
  HorizontalAlignment getHorizontalAlignment();
}
