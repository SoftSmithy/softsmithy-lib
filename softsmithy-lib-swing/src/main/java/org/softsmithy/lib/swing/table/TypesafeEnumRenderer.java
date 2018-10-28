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
 * HorizontalAlignmentCellRenderer.java
 *
 * Created on 7. Oktober 2002, 22:32
 */
package org.softsmithy.lib.swing.table;

import java.util.*;
import javax.swing.table.*;
import org.softsmithy.lib.util.*;

/**
 *
 * @author puce
 */
@Deprecated
public class TypesafeEnumRenderer extends DefaultTableCellRenderer {

    /**
     * Holds value of property locale.
     */
    private Locale locale;

    /**
     * Creates a new instance of HorizontalAlignmentCellRenderer
     *
     * @param locale the locale
     */
    public TypesafeEnumRenderer(Locale locale) {
        this.locale = locale;
    }

    /**
     * {@inheritDoc }
     *
     */
    @Override
    protected void setValue(Object value) {
        super.setValue(((TypesafeEnum) value).getDisplayString(getLocale()));
    }

    /**
     * {@inheritDoc }
     *
     */
    @Override
    public Locale getLocale() {
        return this.locale;
    }

    /**
     * {@inheritDoc }
     *
     */
    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
