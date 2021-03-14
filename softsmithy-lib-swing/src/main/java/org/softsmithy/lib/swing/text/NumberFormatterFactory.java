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
public class NumberFormatterFactory<N extends Number & Comparable<N>, T extends AbstractXNumberFormatter<N>> extends JFormattedTextField.AbstractFormatterFactory {

    private final T numberFormatter;
    private final Class<? extends T> formatterType;

    /**
     * Creates a new instance of WholeNumberFormatterFactory
     */
    public NumberFormatterFactory(Class<? extends T> formatterType, T numberFormatter) {
        this.formatterType = formatterType;
        this.numberFormatter = numberFormatter;
    }

    @Override
    public T getFormatter(JFormattedTextField tf) {
        return getNumberFormatter();
    }

    public T getNumberFormatter() {
        return numberFormatter;
    }

    /**
     * @return the formatterType
     */
    public Class<? extends T> getFormatterType() {
        return formatterType;
    }

}
