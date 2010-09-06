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
 * IntegerCellRenderer.java
 *
 * Created on 11. September 2003, 19:37
 */
package org.softsmithy.lib.swing;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author  puce
 */
public class LocalizedRealNumberCellRenderer extends AbstractCellRenderer<Object> {

    /** Creates a new instance of IntegerCellRenderer */
    public LocalizedRealNumberCellRenderer() {
        super(HorizontalAlignment.TRAILING);
    }

    @Override
    public Object getDisplayValue(Object value, Locale locale) {
        NumberFormat nf = NumberFormat.getNumberInstance(locale);
        return nf.format(value);
    }
}
