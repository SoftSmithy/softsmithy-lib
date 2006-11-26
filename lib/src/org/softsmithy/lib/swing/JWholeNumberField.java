/*
 *                  Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
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
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import org.softsmithy.lib.swing.text.*;

/**
 * A number field for arbitrary big whole numbers.
 * @author puce
 */
public class JWholeNumberField extends AbstractNumberField {
    
    
    /**
     * Creates a new instance of this class.
     */
    public JWholeNumberField() {
        this(Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param locale the locale
     */
    public JWholeNumberField(Locale locale){
        this(BigInteger.ZERO, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     */
    public JWholeNumberField(BigInteger value){
        this(value, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param locale the locale
     */
    public JWholeNumberField(BigInteger value, Locale locale){
        this(value, null, null, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JWholeNumberField(BigInteger minValue, BigInteger maxValue){
        this(minValue, maxValue, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
    public JWholeNumberField(BigInteger minValue, BigInteger maxValue, Locale locale){
        this(BigInteger.ZERO, minValue, maxValue, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JWholeNumberField(BigInteger value, BigInteger minValue, BigInteger maxValue){
        this(value, minValue, maxValue, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
    public JWholeNumberField(BigInteger value, BigInteger minValue, BigInteger maxValue, Locale locale){
        this(new WholeNumberFormatterFactory(new WholeNumberFormatter()));
        setMinimumBigIntegerValue(minValue);
        setMaximumBigIntegerValue(maxValue);
        setBigIntegerValue(value);
        setLocale(locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param factory the number formatter factory
     */
    public JWholeNumberField(WholeNumberFormatterFactory factory){
        super(factory);
        setBigIntegerValue(BigInteger.ZERO);
    }
    
    /**
     * Applys the locale to the number formatter factory.
     * It gets called by the constructors,
     * the setLocale and the setFormatterFactory methods.
     */
    protected void reinit(){
        getWholeNumberFormatterFactory().setLocale(getLocale());
    }
    
    
    /**
     * Gets the value.
     * @return the value
     */
    public BigInteger getBigIntegerValue(){
        return (BigInteger) getNumberValue();
    }
    
    /**
     * Sets the value.
     * @param value the value
     */
    public void setBigIntegerValue(BigInteger value){
        setNumberValue(value);
    }
    
    /**
     * Sets the value.
     * Must be an instance of BigInteger or null.
     * @param value the value
     */
    public void setValue(Object value) {
        // use BigInteger to recognize if the value is out of range if the range is
        // (Integer.MIN_VALUE, Integer.MAX_VALUE)
        if (value != null && ! (value instanceof BigInteger)){
            throw new IllegalArgumentException("value must be an instance of BigInteger");
        }
        super.setValue(value);
    }
    
    //  public Object getValue(){
    //    if (super.getValue() != null){
    //    System.out.println(super.getValue().getClass());
    //    }
    //    return super.getValue() != null ? new Integer(((BigInteger) super.getValue()).intValue()) : null;
    //  }
    
    /**
     * Gets the minimum value.
     * @return the minimum value
     */
    public BigInteger getMinimumBigIntegerValue(){
        return (BigInteger) getMinimumNumberValue();
    }
    
    /**
     * Sets the minimum value.
     * @param minValue the minimum value
     */
    public void setMinimumBigIntegerValue(BigInteger minValue){
        setMinimumNumberValue(minValue);
    }
    
    
    /**
     * Gets the maximum value.
     * @return the maximum value
     */
    public BigInteger getMaximumBigIntegerValue(){
        return (BigInteger) getMaximumNumberValue();
    }
    
    /**
     * Sets the maximum value.
     * @param maxValue the maximum value
     */
    public void setMaximumBigIntegerValue(BigInteger maxValue){
        setMaximumNumberValue(maxValue);
    }
    
    /**
     * Sets the formatter.
     * Must be an instance of WholeNumberFormatter.
     * You should not normally invoke this. See the documentation of the base class for
     * more information.
     * @param formatter the number formatter
     */
    protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
        if (! (formatter instanceof WholeNumberFormatter)){
            throw new IllegalArgumentException("formatter must be an instance of WholeNumberFormatter!");
        }
        super.setFormatter(formatter);
    }
    
    /**
     * Gets the number formatter.
     * @return the number formatter
     */
    public WholeNumberFormatter getWholeNumberFormatter(){
        return (WholeNumberFormatter) getAbstractXNumberFormatter();
    }
    
    /**
     * Gets the number formatter factory.
     * @return the number formatter factory
     */
    public WholeNumberFormatterFactory getWholeNumberFormatterFactory(){
        return (WholeNumberFormatterFactory) getAbstractXNumberFormatterFactory();
    }
    
    /**
     * Sets the number formatter factory.
     * Calls the reinit method.
     * Ensures the value stays in the range defined by the minimum and maximum value of
     * the number formatter, which can be obtained by this formatter factory, by either
     * setting it to the maximum value if it is greater than the maximum value or to
     * the minimum value if it is smaller than the minimum value.
     * @param factory the number formatter factory
     */
    public void setWholeNumberFormatterFactory(WholeNumberFormatterFactory factory){
        setAbstractXNumberFormatterFactory(factory);
    }
    
    /**
     * Sets the formatter factory.
     * Must be an instance of WholeNumberFormatterFactory.
     * Calls the reinit method.
     * Ensures the value stays in the range defined by the minimum and maximum value of
     * the number formatter, which can be obtained by this formatter factory, by either
     * setting it to the maximum value if it is greater than the maximum value or to
     * the minimum value if it is smaller than the minimum value.
     * @param aff the number formatter factory
     */
    public void setFormatterFactory(JFormattedTextField.AbstractFormatterFactory aff) {
        if (! (aff instanceof WholeNumberFormatterFactory)){
            throw new IllegalArgumentException("aff must be an instance of WholeNumberFormatterFactory!");
        }
        super.setFormatterFactory(aff);
    }
    
    /**
     * Sets the maximum value.
     * Must be null or an instance of BigInteger!
     * @param maxValue the maximum value (BigInteger)
     */
    public void setMaximumNumberValue(Number maxValue) {
        if (maxValue != null && ! (maxValue instanceof BigInteger)){
            throw new IllegalArgumentException("maxValue must be an instance of BigInteger");
        }
        super.setMaximumNumberValue(maxValue);
    }
    
    /**
     * Sets the minimum value.
     * Must be null or an instance of BigInteger!
     * @param minValue the minimum value (BigInteger)
     */
    public void setMinimumNumberValue(Number minValue) {
        if (minValue != null && ! (minValue instanceof BigInteger)){
            throw new IllegalArgumentException("minValue must be an instance of BigInteger");
        }
        super.setMinimumNumberValue(minValue);
    }
    
}
