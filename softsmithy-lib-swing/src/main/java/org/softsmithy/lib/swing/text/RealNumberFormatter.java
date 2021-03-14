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

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * use BigDecimal to recognize values out of range if the range is (Integer.MIN_VALUE, Integer.MAX_VALUE)
 *
 * @author puce
 */
public class RealNumberFormatter extends AbstractXNumberFormatter<BigDecimal> {

    /**
     * Creates a new instance of RealNumberFormatter
     */
    public RealNumberFormatter() {
        super(BigDecimal.class);
    }

    public RealNumberFormatter(NumberFormat format) {
        super(BigDecimal.class, format);
    }

    public RealNumberFormatter(BigDecimal minIntValue, BigDecimal maxIntValue) {
        super(BigDecimal.class, minIntValue, maxIntValue);
    }

    public RealNumberFormatter(NumberFormat format, BigDecimal minIntValue, BigDecimal maxIntValue) {
        super(BigDecimal.class, format, minIntValue, maxIntValue);
    }


    protected BigDecimal stringToNumber(String text) throws NumberFormatException {
        return new BigDecimal(text);
    }

    /**
     * Getter for property maximumMaximumValue.
     *
     * @return Value of property maximumMaximumValue.
     *
     */
    @Deprecated
    protected BigDecimal getMaximumMaximumBigDecimalValue() {
        return getMaximumMaximumValue();
    }

    /**
     * Setter for property maximumMaximumValue.
     *
     * @param maximumMaximumValue New value of property maximumMaximumValue.
     *
     */
    @Deprecated
    protected void setMaximumMaximumBigDecimalValue(BigDecimal maximumMaximumValue) {
        setMaximumMaximumValue(maximumMaximumValue);
    }

    /**
     * Getter for property minimumMinimumValue.
     *
     * @return Value of property minimumMinimumValue.
     *
     */
    @Deprecated
    protected BigDecimal getMinimumMinimumBigDecimalValue() {
        return getMinimumMinimumValue();
    }

    /**
     * Setter for property minimumMinimumValue.
     *
     * @param minimumMinimumValue New value of property minimumMinimumValue.
     *
     */
    @Deprecated
    protected void setMinimumMinimumBigDecimalValue(BigDecimal minimumMinimumValue) {
        setMinimumMinimumValue(minimumMinimumValue);
    }

    /**
     * Getter for property maximumBigDecimalValue.
     *
     * @return Value of property maximumBigDecimalValue.
     *
     */
    @Deprecated
    public BigDecimal getMaximumBigDecimalValue() {
        return getMaximumNumberValue();
    }

    /**
     * Setter for property maximumBigDecimalValue.
     *
     * @param maximumBigDecimalValue New value of property maximumBigDecimalValue.
     *
     */
    @Deprecated
    public void setMaximumBigDecimalValue(BigDecimal maximumBigDecimalValue) {
        setMaximumNumberValue(maximumBigDecimalValue);
    }

    /**
     * Getter for property minimumBigDecimalValue.
     *
     * @return Value of property minimumBigDecimalValue.
     *
     */
    @Deprecated
    public BigDecimal getMinimumBigDecimalValue() {
        return getMinimumNumberValue();
    }

    /**
     * Setter for property minimumBigDecimalValue.
     *
     * @param minimumBigDecimalValue New value of property minimumBigDecimalValue.
     *
     */
    @Deprecated
    public void setMinimumBigDecimalValue(BigDecimal minimumBigDecimalValue) {
        setMinimumNumberValue(minimumBigDecimalValue);
    }

}
