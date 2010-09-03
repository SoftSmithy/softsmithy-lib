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
 * @author puce
 */
public abstract class AbstractXNumberFormatter extends NumberFormatter {
    
    
    /** Holds value of property maximumMaximumValue. */
    private Number maximumMaximumValue = null;
    
    /** Holds value of property minimumMinimumValue. */
    private Number minimumMinimumValue = null;
    
    /**
     * Creates a new instance of this class.
     */
    public AbstractXNumberFormatter() {
        init(null, null);
    }
    
    /**
     * Creates a new instance of this class.
     * @param format the number format
     */
    public AbstractXNumberFormatter(NumberFormat format){
        super(format);
        init(null, null);
    }
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public AbstractXNumberFormatter(Number minValue, Number maxValue){
        init(minValue, maxValue);
    }
    
    /**
     * Creates a new instance of this class.
     * @param format the number format
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public AbstractXNumberFormatter(NumberFormat format, Number minValue, Number maxValue){
        super(format);
        init(minValue, maxValue);
    }
    
    /**
     * Initializes this formatter.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    private void init(Number minValue, Number maxValue){
        setMinimumNumberValue(minValue);
        setMaximumNumberValue(maxValue);
    }
    
    
    /**
     * Sets the maximum value.
     * It must be an instance of Number or null.
     * It mustn't be smaller than the minimum value.
     * It ensures that the maximum value is in the range of the maximum
     * maximum value and the minimum minimum value.
     * @param max the maximum number value
     */
    @Override
    public void setMaximum(Comparable max) {
        if (max != null && ! (max instanceof Number)){
            throw new IllegalArgumentException("max must be an instance of Number or null");
        }
        if (max != null && getMinimum() != null && Comparables.isLess(max, getMinimum())){
            throw new IllegalArgumentException("max mustn't be smaller than minimum!");
        }
        //    if (max.compareTo(MAX_MAX_VALUE) > 0){
        //      throw new IllegalArgumentException("max mustn't be bigger than MAX_MAX_VALUE!");
        //    }
        super.setMaximum((Comparable) maximumToRange((Number) max));
    }
    
    /**
     * Sets the minimum value.
     * It must be an instance of Number or null.
     * It mustn't be bigger than the maximum value.
     * It ensures that the minimum value is in the range of the maximum
     * maximum value and the minimum minimum value.
     * @param minimum the minimum number value
     */
    @Override
    public void setMinimum(Comparable minimum) {
        if (minimum != null && ! (minimum instanceof Number)){
            throw new IllegalArgumentException("minimum must be an instance of Number or null");
        }
        if (minimum != null && getMaximum() != null && Comparables.isGreater(minimum, getMaximum())){
            throw new IllegalArgumentException("minimum mustn't be bigger than max!");
        }
        //    if (minimum.compareTo(getMinimumMinimumValue()) < 0){
        //      throw new IllegalArgumentException("minimum mustn't be smaller than the minimum minimum value!");
        //    }
        super.setMinimum((Comparable) minimumToRange((Number) minimum));
    }
    
    /**
     * Returns the <code>Number</code> representation of the
     * <code>String</code> <code>text</code>.
     * @param text <code>String</code> to convert
     * @return <code>Number</code> representation of text
     * @throws ParseException if there is an error in the conversion
     */
    @Override
    public Object stringToValue(String text) throws ParseException {
        Number value = null;
        try{
            String valueText = text;
            if (((DecimalFormat) getNumberFormat()).isGroupingUsed()){
                valueText = valueText.replaceAll("\\" + ((DecimalFormat) getNumberFormat()).getDecimalFormatSymbols().getGroupingSeparator(), "");
            }
            valueText = valueText.replaceAll("\\" + ((DecimalFormat) getNumberFormat()).getDecimalFormatSymbols().getDecimalSeparator(), ".");
            value = valueToRange(stringToNumber(valueText));
            //System.out.println("First number conversion worked!");
        } catch(Exception ex){ //try this, but only long and double precision supported (important for BigInteger and BigDecimal)!
            //System.out.println("First number conversion failed!");
            try{
                value = valueToRange(stringToNumber(getNumberFormat() != null ? getNumberFormat().parse(text).toString() : text));
                value = (Number) super.stringToValue(getNumberFormat() != null ? getNumberFormat().format(value.toString()) : value.toString()); // needed?
                //System.out.println("Second number conversion worked!");
            } catch (Exception nfe){
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
     * @param text <code>String</code> to convert
     * @return <code>Number</code> representation of text
     * @throws java.lang.NumberFormatException if there is an error in the conversion
     */
    protected abstract Number stringToNumber(String text) throws NumberFormatException;
    
    public Number valueToRange(Number value){
        if (! (value instanceof Comparable)){
            throw new IllegalArgumentException("value must be an instance of Comparable");
        }
        return (Number) Formatters.valueToRange(this, (Comparable) value);
    }
    
    private Number minimumToRange(Number min){
        if (min != null && ! (min instanceof Comparable)){
            throw new IllegalArgumentException("min must be an instance of Comparable");
        }
        Comparable inRangeValue = (Comparable) min;
        if (getMinimumMinimumValue() != null){
            if (inRangeValue == null){
                inRangeValue = (Comparable) getMinimumMinimumValue();
            } else {
                inRangeValue = Comparables.toRange(inRangeValue, (Comparable) getMinimumMinimumValue(), (Comparable) getMaximumMaximumValue());
            }
        }
        return (Number) inRangeValue;
    }
    
    private Number maximumToRange(Number max){
        if (max != null && ! (max instanceof Comparable)){
            throw new IllegalArgumentException("max must be an instance of Comparable");
        }
        Comparable inRangeValue = (Comparable) max;
        if (getMaximumMaximumValue() != null){
            if (inRangeValue == null){
                inRangeValue = (Comparable) getMaximumMaximumValue();
            } else {
                inRangeValue = Comparables.toRange(inRangeValue, (Comparable) getMinimumMinimumValue(), (Comparable) getMaximumMaximumValue());
            }
        }
        return (Number) inRangeValue;
    }
    
    /** Getter for property maximumMaximumValue.
     * @return Value of property maximumMaximumValue.
     *
     */
    protected Number getMaximumMaximumValue() {
        return this.maximumMaximumValue;
    }
    
    
    /**
     * The maximum value for the maximum value.
     * It must be an instance of Comparable or null, if there is no maximum
     * maximum value. And it mustn't be smaller than the minimum minimum value,
     * if both are not null.
     * If the maximum or the minimum value are greater than the maximum maximum
     * value, they will be set to the maximum maximum value instead.
     * @param maximumMaximumValue the maximum value for the maximum value
     */
    protected void setMaximumMaximumValue(Number maximumMaximumValue) {
        if (maximumMaximumValue != null && ! (maximumMaximumValue instanceof Comparable)){
            throw new IllegalArgumentException("maximumMaximumValue must be an instance of Comparable or null");
        }
        if (maximumMaximumValue != null && getMinimumMinimumValue() != null
                && Comparables.isLess((Comparable) maximumMaximumValue, (Comparable) getMinimumMinimumValue())){
            throw new IllegalArgumentException("maximumMaximumValue mustn't be smaller than minimumMinimumValue!");
        }
        this.maximumMaximumValue = maximumMaximumValue;
        setMinimumNumberValue(getMinimumNumberValue());
        setMaximumNumberValue(getMaximumNumberValue());
    }
    
    /** Getter for property minimumMinimumValue.
     * @return Value of property minimumMinimumValue.
     *
     */
    protected Number getMinimumMinimumValue() {
        return this.minimumMinimumValue;
    }
    
    /**
     * The minimum value for the minimum value.
     */
    protected void setMinimumMinimumValue(Number minimumMinimumValue) {
        if (minimumMinimumValue != null && ! (minimumMinimumValue instanceof Comparable)){
            throw new IllegalArgumentException("minimumMinimumValue must be an instance of Comparable or null");
        }
        if (minimumMinimumValue != null && getMaximumMaximumValue() != null
                && Comparables.isLess((Comparable) minimumMinimumValue, (Comparable) getMaximumMaximumValue())){ // TODO: bug? IsGreater?
            throw new IllegalArgumentException("minimumMinimumValue mustn't be smaller than maximumMaximumValue!"); // TODO: bug? greater?
        }
        this.minimumMinimumValue = minimumMinimumValue;
        setMaximumNumberValue(getMaximumNumberValue());
        setMinimumNumberValue(getMinimumNumberValue());
    }
    
    /** Getter for property maximumBigDecimalValue.
     * @return Value of property maximumBigDecimalValue.
     *
     */
    public Number getMaximumNumberValue() {
        return (Number) getMaximum();
    }
    
    /** Setter for property maximumBigDecimalValue.
     * @param maximumBigDecimalValue New value of property maximumBigDecimalValue.
     *
     */
    public void setMaximumNumberValue(Number maximumValue) {
        if (maximumValue != null && ! (maximumValue instanceof Comparable)){
            throw new IllegalArgumentException("maximumValue must be an instance of Comparable or null");
        }
        setMaximum((Comparable) maximumValue);
    }
    
    /** Getter for property minimumBigDecimalValue.
     * @return Value of property minimumBigDecimalValue.
     *
     */
    public Number getMinimumNumberValue() {
        return (Number) getMinimum();
    }
    
    /** Setter for property minimumBigDecimalValue.
     * @param minimumBigDecimalValue New value of property minimumBigDecimalValue.
     *
     */
    public void setMinimumNumberValue(Number minimumValue) {
        if (minimumValue != null && ! (minimumValue instanceof Comparable)){
            throw new IllegalArgumentException("minimumValue must be an instance of Comparable or null");
        }
        setMinimum((Comparable) minimumValue);
    }
    
    @Override
    public void setValueClass(Class valueClass) {
        if (! Number.class.isAssignableFrom(valueClass)){
            throw new IllegalArgumentException("Number class must be assignable from valueClass");
        }
        super.setValueClass(valueClass);
    }
    
    @Override
    public void setFormat(Format format) {
        if (format != null && ! (format instanceof NumberFormat)){
            throw new IllegalArgumentException("format must be an instance of NumberFormat or null");
        }
        super.setFormat(format);
    }
    
    public NumberFormat getNumberFormat(){
        return (NumberFormat) getFormat();
    }
    
    public void setNumberFormat(NumberFormat format){
        setFormat(format);
    }
}
