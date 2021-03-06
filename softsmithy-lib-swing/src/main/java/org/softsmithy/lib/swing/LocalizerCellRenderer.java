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
 * Created on 5. M�rz 2003, 18:51
 */
package org.softsmithy.lib.swing;

import java.util.Locale;
import org.softsmithy.lib.text.Localizer;

/**
 *
 * @author  puce
 */
public class LocalizerCellRenderer<T> extends AbstractCellRenderer<T> {

    private final Localizer<? super T> localizer;

    public LocalizerCellRenderer(Localizer<? super T> localizer) {
        this.localizer = localizer;
    }

    public LocalizerCellRenderer(Localizer<? super T> localizer, HorizontalAlignment horizontalAlignment) {
        super(horizontalAlignment);
        this.localizer = localizer;
    }

    @Override
    public String getDisplayValue(T value, Locale locale) {
        String localizedString = null;
        if (value != null) {
            localizedString = localizer.getDisplayString(value, locale);
        }
        return localizedString;
    }
}
