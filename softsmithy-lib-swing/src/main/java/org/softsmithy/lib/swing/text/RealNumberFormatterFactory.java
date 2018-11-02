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

import javax.swing.*;

/**
 *
 * @author puce
 */
public class RealNumberFormatterFactory extends AbstractXNumberFormatterFactory {

    /**
     * Creates a new instance of WholeNumberFormatterFactory
     * @param formatter the formatter
     */
    public RealNumberFormatterFactory(RealNumberFormatter formatter) {
        super(formatter);
    }

    @Override
    public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
        return getRealNumberFormatter();
    }

    public RealNumberFormatter getRealNumberFormatter() {
        return (RealNumberFormatter) getAbstractXNumberFormatter();
    }

}
