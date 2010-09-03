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
 * CellRenderer.java
 *
 * Created on 5. März 2003, 16:34
 */

package org.softsmithy.lib.swing;

import java.util.*;
import javax.swing.Icon;

/**
 * An abstraction to share common implementations between various kinds of 
 * cell renderers (eg. cell renderers for lists, tables, trees). <br/>
 * <br/>
 * Note: with jdk 1.5 this interface might become parameterized!
 * @author puce
 * 
 * @see XDefaultListCellRenderer
 * @see org.softsmithy.lib.swing.table.XDefaultTableCellRenderer
 */
public interface CellRenderer<T> {
    /**
     * Gets the display value.<br/>
     * <br/>
     * Note: with jdk 1.5 the type of value might change to the parameterized type of this interface
     * @param value the source value
     * @param inLocale the Locale used for localization
     * @return the display value
     */
  Object getDisplayValue(T value, Locale inLocale);
    /**
     * Gets the horizontal alignment.
     * @return the horizontal alignment
     */
  HorizontalAlignment getHorizontalAlignment();
  
  Icon getIcon(T value);
}
