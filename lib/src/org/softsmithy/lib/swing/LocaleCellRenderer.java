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
 * LocaleCellRenderer.java
 *
 * Created on 5. März 2003, 18:36
 */

package org.softsmithy.lib.swing;

import java.util.*;
import org.softsmithy.lib.util.LocaleDisplay;

/**
 *
 * @author  puce
 */
public class LocaleCellRenderer extends AbstractCellRenderer {
    
    private final LocaleDisplay localeDisplay;
    
    /** Creates a new instance of LocaleCellRenderer */
    public LocaleCellRenderer() {
        this(LocaleDisplay.NAME);
    }
    
    public LocaleCellRenderer(LocaleDisplay localeDisplay){
        super(HorizontalAlignment.LEADING);
        this.localeDisplay = localeDisplay;
    }
    
    public Object getDisplayValue(Object value, Locale inLocale) {
        String displayName = null;
        if (value != null){
            if (! (value instanceof Locale)){
                throw new IllegalArgumentException("value must be an instance of Locale");
            }
            displayName = localeDisplay.getString((Locale) value, inLocale);
        }
        return displayName;
    }
    
}
