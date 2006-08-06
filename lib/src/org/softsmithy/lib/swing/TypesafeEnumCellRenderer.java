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
 * TypesafeEnumCellRenderer.java
 *
 * Created on 5. März 2003, 18:51
 */

package org.softsmithy.lib.swing;

import java.util.*;
import org.softsmithy.lib.util.*;

/**
 *
 * @author  puce
 */
public class TypesafeEnumCellRenderer extends AbstractCellRenderer {
  
  /** Creates a new instance of TypesafeEnumCellRenderer */
  public TypesafeEnumCellRenderer() {
    super(HorizontalAlignment.LEADING);
  }
  
  public Object getDisplayValue(Object value, Locale locale) {
    String typesafeEnumString = null;
    if (value != null){
      if (! (value instanceof TypesafeEnum)){
        throw new IllegalArgumentException("value must be an instance of TypesafeEnum");
      }
      typesafeEnumString = ((TypesafeEnum) value).toString(locale);
    }
    return typesafeEnumString;
  }
  
}
