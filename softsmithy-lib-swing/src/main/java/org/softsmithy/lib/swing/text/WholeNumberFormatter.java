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
 * IntegerFormatter.java
 *
 * Created on 9. September 2003, 19:18
 */
package org.softsmithy.lib.swing.text;

import java.math.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

/**
 * use BigInteger to recognize values out of range if the range is (Integer.MIN_VALUE, Integer.MAX_VALUE)
 *
 * @author puce
 */
public class WholeNumberFormatter extends AbstractXNumberFormatter<BigInteger> {

    /**
     * Holds value of property locale.
     */
    private Locale locale;

    /**
     * Creates a new instance of IntegerFormatter
     */
    public WholeNumberFormatter() {
        this(JComponent.getDefaultLocale());// better than Locale.getDefault()?
    }

    public WholeNumberFormatter(Locale locale) {
        this(null, null, locale);
    }

    public WholeNumberFormatter(BigInteger minIntValue, BigInteger maxIntValue) {
        this(minIntValue, maxIntValue, JComponent.getDefaultLocale());// better than Locale.getDefault()?
    }

    public WholeNumberFormatter(BigInteger minIntValue, BigInteger maxIntValue, Locale locale) {
        super(BigInteger.class, minIntValue, maxIntValue);
        setLocale(locale);
    }

    /**
     * Getter for property locale.
     *
     * @return Value of property locale.
     *
     */
    public Locale getLocale() {
        return this.locale;
    }

    /**
     * Setter for property locale.
     *
     * @param locale New value of property locale.
     *
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
        setNumberFormat(NumberFormat.getIntegerInstance(locale));
    }

    /**
     * Getter for property maximumMaximumValue.
     *
     * @return Value of property maximumMaximumValue.
     *
     */
    @Deprecated
    protected BigInteger getMaximumMaximumBigIntegerValue() {
        return getMaximumMaximumValue();
    }

    /**
     * Setter for property maximumMaximumValue.
     *
     * @param maximumMaximumValue New value of property maximumMaximumValue.
     *
     */
    @Deprecated
    protected void setMaximumMaximumBigIntegerValue(BigInteger maximumMaximumValue) {
        setMaximumMaximumValue(maximumMaximumValue);
    }

    /**
     * Getter for property minimumMinimumValue.
     *
     * @return Value of property minimumMinimumValue.
     *
     */
    @Deprecated
    protected BigInteger getMinimumMinimumBigIntegerValue() {
        return getMinimumMinimumValue();
    }

    /**
     * Setter for property minimumMinimumValue.
     *
     * @param minimumMinimumValue New value of property minimumMinimumValue.
     *
     */
    @Deprecated
    protected void setMinimumMinimumBigIntegerValue(BigInteger minimumMinimumValue) {
        setMinimumMinimumValue(minimumMinimumValue);
    }

    /**
     * Getter for property maximumBigIntegerValue.
     *
     * @return Value of property maximumBigIntegerValue.
     *
     */
    @Deprecated
    public BigInteger getMaximumBigIntegerValue() {
        return getMaximumNumberValue();
    }

    /**
     * Setter for property maximumBigIntegerValue.
     *
     * @param maximumBigIntegerValue New value of property maximumBigIntegerValue.
     *
     */
    @Deprecated
    public void setMaximumBigIntegerValue(BigInteger maximumBigIntegerValue) {
        setMaximumNumberValue(maximumBigIntegerValue);
    }

    /**
     * Getter for property minimumBigIntegerValue.
     *
     * @return Value of property minimumBigIntegerValue.
     *
     */
    @Deprecated
    public BigInteger getMinimumBigIntegerValue() {
        return getMinimumNumberValue();
    }

    /**
     * Setter for property minimumBigIntegerValue.
     *
     * @param minimumBigIntegerValue New value of property minimumBigIntegerValue.
     *
     */
    @Deprecated
    public void setMinimumBigIntegerValue(BigInteger minimumBigIntegerValue) {
        setMinimumNumberValue(minimumBigIntegerValue);
    }

    @Override
    protected BigInteger stringToNumber(String text) throws NumberFormatException {
        return new BigInteger(text);
    }

}
