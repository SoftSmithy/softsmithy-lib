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

import java.text.*;
import javax.swing.text.*;
import org.softsmithy.lib.util.*;

/**
 * The base class of the number formatters.
 *
 * @author puce
 */
public abstract class AbstractXNumberFormatter<T extends Number & Comparable<T>> extends NumberFormatter {

    /**
     * Holds value of property maximumMaximumValue.
     */
    private T maximumMaximumValue = null;

    /**
     * Holds value of property minimumMinimumValue.
     */
    private T minimumMinimumValue = null;

    private Class<T> numberType;

    /**
     * Creates a new instance of this class.
     *
     * @param numberType the number type
     */
    public AbstractXNumberFormatter(Class<T> numberType) {
        init(numberType, null, null);
    }

    /**
     * Creates a new instance of this class.
     *
     * @param numberType the number type
     * @param format the number format
     */
    public AbstractXNumberFormatter(Class<T> numberType, NumberFormat format) {
        super(format);
        init(numberType, null, null);
    }

    /**
     * Creates a new instance of this class.
     *
     * @param numberType the number type
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public AbstractXNumberFormatter(Class<T> numberType, T minValue, T maxValue) {
        init(numberType, minValue, maxValue);
    }

    /**
     * Creates a new instance of this class.
     *
     * @param numberType the number type
     * @param format the number format
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public AbstractXNumberFormatter(Class<T> numberType, NumberFormat format, T minValue, T maxValue) {
        super(format);
        init(numberType, minValue, maxValue);
    }

    /**
     * Initializes this formatter.
     *
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    private void init(Class<T> numberType, T minValue, T maxValue) {
        this.numberType = numberType;
        super.setValueClass(numberType);
        setMinimumNumberValue(minValue);
        setMaximumNumberValue(maxValue);
    }

    /**
     * Sets the maximum value. 
     * It must be an instance of Number or null. 
     * It mustn't be smaller than the minimum value. 
     * It ensures that the maximum value is in the range of the maximum 
     * maximum value and the minimum minimum value.
     *
     * @param max the maximum number value
     */
    @Override
    public void setMaximum(Comparable max) {
        if (max != null && !numberType.isInstance(max)) {
            throw new IllegalArgumentException("max must be an instance of number type or null");
        }
        if (max != null && getMinimum() != null && Comparables.isLess(max, getMinimum())) {
            throw new IllegalArgumentException("max mustn't be smaller than minimum!");
        }
        //    if (max.compareTo(MAX_MAX_VALUE) > 0){
        //      throw new IllegalArgumentException("max mustn't be bigger than MAX_MAX_VALUE!");
        //    }
        super.setMaximum(maximumToRange(numberType.cast(max)));
    }

    /**
     * Sets the minimum value. 
     * It must be an instance of Number or null. 
     * It mustn't be bigger than the maximum value. 
     * It ensures that the minimum value is in the range of the maximum 
     * maximum value and the minimum minimum value.
     *
     * @param minimum the minimum number value
     */
    @Override
    public void setMinimum(Comparable minimum) {
        if (minimum != null && !numberType.isInstance(minimum)) {
            throw new IllegalArgumentException("minimum must be an instance of number type or null");
        }
        if (minimum != null && getMaximum() != null && Comparables.isGreater(minimum, getMaximum())) {
            throw new IllegalArgumentException("minimum mustn't be bigger than max!");
        }
        //    if (minimum.compareTo(getMinimumMinimumValue()) < 0){
        //      throw new IllegalArgumentException("minimum mustn't be smaller than the minimum minimum value!");
        //    }
        super.setMinimum(minimumToRange(numberType.cast(minimum)));
    }

    /**
     * Returns the <code>Number</code> representation of the 
     * <code>String</code> <code>text</code>.
     *
     * @param text <code>String</code> to convert
     * @return <code>Number</code> representation of text
     * @throws ParseException if there is an error in the conversion
     */
    @Override
    public Object stringToValue(String text) throws ParseException {
        Number value = null;
        try {
            String valueText = text;
            if (((DecimalFormat) getNumberFormat()).isGroupingUsed()) {
                valueText = valueText.replaceAll("\\" + ((DecimalFormat) getNumberFormat()).getDecimalFormatSymbols().getGroupingSeparator(), "");
            }
            valueText = valueText.replaceAll("\\" + ((DecimalFormat) getNumberFormat()).getDecimalFormatSymbols().getDecimalSeparator(), ".");
            value = valueToRange(stringToNumber(valueText));
            //System.out.println("First number conversion worked!");
        } catch (RuntimeException ex) { //try this, but only long and double precision supported (important for BigInteger and BigDecimal)!
            //System.out.println("First number conversion failed!");
            try {
                value = valueToRange(stringToNumber(getNumberFormat() != null ? getNumberFormat().parse(text).toString() : text));
                value = (Number) super.stringToValue(getNumberFormat() != null ? getNumberFormat().format(value.toString()) : value.toString()); // needed?
                //System.out.println("Second number conversion worked!");
            } catch (ParseException | RuntimeException nfe) {
                //System.out.println("Second number conversion failed!");
                //nfe.printStackTrace();
                value = (Number) super.stringToValue(text); // will throw a ParseException
                //System.out.println("Third number conversion worked!");
            }
        }
        return value;
    }

    /**
     * Returns the <code>Number</code> representation of the 
     * <code>String</code> <code>text</code>.
     *
     * @param text <code>String</code> to convert
     * @return <code>Number</code> representation of text
     * @throws java.lang.NumberFormatException if there is an error in the conversion
     */
    protected abstract T stringToNumber(String text) throws NumberFormatException;

    public T valueToRange(T value) {
        return Comparables.toRange(value, getMinimumNumberValue(), getMaximumNumberValue());
    }

    private T minimumToRange(T min) {
        T inRangeValue = min;
        if (getMinimumMinimumValue() != null) {
            if (inRangeValue == null) {
                inRangeValue = getMinimumMinimumValue();
            } else {
                inRangeValue = Comparables.toRange(inRangeValue, getMinimumMinimumValue(), getMaximumMaximumValue());
            }
        }
        return inRangeValue;
    }

    private T maximumToRange(T max) {
        T inRangeValue = max;
        if (getMaximumMaximumValue() != null) {
            if (inRangeValue == null) {
                inRangeValue = getMaximumMaximumValue();
            } else {
                inRangeValue = Comparables.toRange(inRangeValue, getMinimumMinimumValue(), getMaximumMaximumValue());
            }
        }
        return inRangeValue;
    }

    /**
     * Getter for property maximumMaximumValue.
     *
     * @return Value of property maximumMaximumValue.
     *
     */
    protected T getMaximumMaximumValue() {
        return this.maximumMaximumValue;
    }

    /**
     * The maximum value for the maximum value. 
     * It must be an instance of Comparable or null, if there is no maximum 
     * maximum value. And it mustn't be smaller than the minimum minimum value, 
     * if both are not null. 
     * If the maximum or the minimum value are greater than the maximum maximum 
     * value, they will be set to the maximum maximum value instead.
     *
     * @param maximumMaximumValue the maximum value for the maximum value
     */
    protected void setMaximumMaximumValue(T maximumMaximumValue) {
        if (maximumMaximumValue != null && getMinimumMinimumValue() != null
                && Comparables.isLess(maximumMaximumValue, getMinimumMinimumValue())) {
            throw new IllegalArgumentException("maximumMaximumValue mustn't be smaller than minimumMinimumValue!");
        }
        this.maximumMaximumValue = maximumMaximumValue;
        setMinimumNumberValue(getMinimumNumberValue());
        setMaximumNumberValue(getMaximumNumberValue());
    }

    /**
     * Getter for property minimumMinimumValue.
     *
     * @return Value of property minimumMinimumValue.
     *
     */
    protected T getMinimumMinimumValue() {
        return this.minimumMinimumValue;
    }

    /**
     * The minimum value for the minimum value.
     */
    protected void setMinimumMinimumValue(T minimumMinimumValue) {
        if (minimumMinimumValue != null && getMaximumMaximumValue() != null
                && Comparables.isLess(minimumMinimumValue, getMaximumMaximumValue())) { // TODO: bug? IsGreater?
            throw new IllegalArgumentException("minimumMinimumValue mustn't be smaller than maximumMaximumValue!"); // TODO: bug? greater?
        }
        this.minimumMinimumValue = minimumMinimumValue;
        setMaximumNumberValue(getMaximumNumberValue());
        setMinimumNumberValue(getMinimumNumberValue());
    }

    /**
     * Getter for property maximumBigDecimalValue.
     *
     * @return Value of property maximumBigDecimalValue.
     *
     */
    public T getMaximumNumberValue() {
        return numberType.cast(getMaximum());
    }

    /**
     * Setter for property maximumNumberValue.
     *
     * @param maximumValue New value of property maximumNumberValue.
     *
     */
    public void setMaximumNumberValue(T maximumValue) {
        setMaximum(maximumValue);
    }

    /**
     * Getter for property minimumBigDecimalValue.
     *
     * @return Value of property minimumBigDecimalValue.
     *
     */
    public T getMinimumNumberValue() {
        return numberType.cast(getMinimum());
    }

    /**
     * Setter for property minimumNumberValue.
     *
     * @param minimumValue New value of property minimumNumberValue.
     *
     */
    public void setMinimumNumberValue(T minimumValue) {
        setMinimum(minimumValue);
    }

//    @Override
//    public void setValueClass(Class<?> valueClass) {
//        if (! numberType.isAssignableFrom(valueClass)){
//            throw new IllegalArgumentException("The number type must be assignable from valueClass");
//        }
//        super.setValueClass(valueClass);
//    }
    @Override
    public void setValueClass(Class<?> valueClass) {
        throw new UnsupportedOperationException("This operation is not supported!"); // always use numberType!
    }

    @Override
    public void setFormat(Format format) {
        if (format != null && !(format instanceof NumberFormat)) {
            throw new IllegalArgumentException("format must be an instance of NumberFormat or null");
        }
        super.setFormat(format);
    }

    public NumberFormat getNumberFormat() {
        return (NumberFormat) getFormat();
    }

    public void setNumberFormat(NumberFormat format) {
        setFormat(format);
    }
}
