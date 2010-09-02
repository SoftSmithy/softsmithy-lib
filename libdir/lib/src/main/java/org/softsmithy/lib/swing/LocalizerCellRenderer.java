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
 * Created on 5. M�rz 2003, 18:51
 */

package org.softsmithy.lib.swing;

import org.softsmithy.lib.text.Localizer;
import java.util.*;
import org.softsmithy.lib.util.*;

/**
 *
 * @author  puce
 */
public class LocalizerCellRenderer<T> extends AbstractCellRenderer<T> {
  
    private final Localizer<T> localizer;
    
    public LocalizerCellRenderer(Localizer<T> localizer){
        this.localizer = localizer;
    }
  
  public Object getDisplayValue(T value, Locale locale) {
    String localizedString = null;
    if (value != null){
      localizedString = localizer.getDisplayString(value, locale);
    }
    return localizedString;
  }
  
}
