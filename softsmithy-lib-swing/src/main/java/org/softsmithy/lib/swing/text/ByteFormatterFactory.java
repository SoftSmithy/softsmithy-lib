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
package org.softsmithy.lib.swing.text;

import java.util.*;

/**
 *
 * @author puce
 */
public class ByteFormatterFactory extends WholeNumberFormatterFactory {

    /**
     * Creates a new instance of WholeNumberFormatterFactory
     * @param formatter the formatter
     */
    public ByteFormatterFactory(ByteFormatter formatter) {
        this(formatter, Locale.getDefault());
    }

    public ByteFormatterFactory(ByteFormatter formatter, Locale locale) {
        super(formatter, locale);
    }

    public ByteFormatter getByteFormatter() {
        return (ByteFormatter) getWholeNumberFormatter();
    }

}
