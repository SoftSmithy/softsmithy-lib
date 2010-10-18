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
public class TypesafeEnumCellRenderer extends AbstractCellRenderer<TypesafeEnum> {

    /** Creates a new instance of TypesafeEnumCellRenderer */
    public TypesafeEnumCellRenderer() {
        super(HorizontalAlignment.LEADING);
    }

    @Override
    public Object getDisplayValue(TypesafeEnum value, Locale locale) {
        String typesafeEnumString = null;
        if (value != null) {
            typesafeEnumString = value.getDisplayString(locale);
        }
        return typesafeEnumString;
    }
}
