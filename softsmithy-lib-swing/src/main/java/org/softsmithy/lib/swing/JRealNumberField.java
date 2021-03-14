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
 * JIntegerField.java
 *
 * Created on 9. September 2003, 18:26
 */
package org.softsmithy.lib.swing;

import java.math.*;
import java.text.*;
import org.softsmithy.lib.swing.text.*;

/**
 * A number field for arbitrary big real numbers.
 *
 * @author puce
 */
public class JRealNumberField extends AbstractNumberField<BigDecimal, RealNumberFormatter> {

    /**
     * Creates a new instance of this class.
     */
    public JRealNumberField() {
        this(BigDecimal.valueOf(0));
    }

    /**
     * Creates a new instance of this class.
     *
     * @param value the value
     */
    public JRealNumberField(BigDecimal value) {
        this(value, null, null);
    }

    /**
     * Creates a new instance of this class.
     *
     * @param format the number format
     */
    public JRealNumberField(NumberFormat format) {
        this(new NumberFormatterFactory<>(RealNumberFormatter.class, new RealNumberFormatter(format)));
    }

    /**
     * Creates a new instance of this class.
     *
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JRealNumberField(BigDecimal minValue, BigDecimal maxValue) {
        this(BigDecimal.valueOf(0), minValue, maxValue);
    }

    /**
     * Creates a new instance of this class.
     *
     * @param format the number format
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JRealNumberField(NumberFormat format, BigDecimal minValue, BigDecimal maxValue) {
        this(new NumberFormatterFactory<>(RealNumberFormatter.class, new RealNumberFormatter(format)));
        init(BigDecimal.valueOf(0), minValue, maxValue);
    }

    /**
     * Creates a new instance of this class.
     *
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JRealNumberField(BigDecimal value, BigDecimal minValue, BigDecimal maxValue) {
        this(new NumberFormatterFactory<>(RealNumberFormatter.class, new RealNumberFormatter()));
        init(value, minValue, maxValue);
    }

    /**
     * Creates a new instance of this class.
     *
     * @param factory the number formatter factory
     */
    public JRealNumberField(NumberFormatterFactory<BigDecimal, RealNumberFormatter> factory) {
        super(BigDecimal.class, factory);
        init(BigDecimal.valueOf(0), null, null);
    }

    /**
     * Initializes this number field.
     *
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    private void init(BigDecimal value, BigDecimal minValue, BigDecimal maxValue) {
        setMinimumNumberValue(minValue);
        setMaximumNumberValue(maxValue);
        setNumberValue(value);
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    @Deprecated
    public BigDecimal getBigDecimalValue() {
        return getNumberValue();
    }

    /**
     * Sets the value.
     *
     * @param value the value
     */
    @Deprecated
    public void setBigDecimalValue(BigDecimal value) {
        setNumberValue(value);
    }

    //  public Object getValue(){
    //    if (super.getValue() != null){
    //    System.out.println(super.getValue().getClass());
    //    }
    //    return super.getValue() != null ? new Integer(((BigDecimal) super.getValue()).intValue()) : null;
    //  }
    /**
     * Gets the minimum value.
     *
     * @return the minimum value
     */
    @Deprecated
    public BigDecimal getMinimumBigDecimalValue() {
        return getMinimumNumberValue();
    }

    /**
     * Sets the minimum value.
     *
     * @param minValue the minimum value
     */
    @Deprecated
    public void setMinimumBigDecimalValue(BigDecimal minValue) {
        setMinimumNumberValue(minValue);
    }

    /**
     * Gets the maximum value.
     *
     * @return the maximum value
     */
    @Deprecated
    public BigDecimal getMaximumBigDecimalValue() {
        return getMaximumNumberValue();
    }

    /**
     * Sets the maximum value.
     *
     * @param maxValue the maximum value
     */
    @Deprecated
    public void setMaximumBigDecimalValue(BigDecimal maxValue) {
        setMaximumNumberValue(maxValue);
    }

    /**
     * Gets the number formatter.
     *
     * @return the number formatter
     */
    @Deprecated
    public RealNumberFormatter getRealNumberFormatter() {
        return getAbstractXNumberFormatter();
    }

}
