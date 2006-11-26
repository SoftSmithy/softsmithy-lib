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

package org.softsmithy.lib.swing;

import java.math.*;
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.swing.text.*;

/**
 * A long field.
 * @author  puce
 */
public class JLongField extends JWholeNumberField {
    
    /**
     * The default value.
     */
    private static final long DEFAULT_VALUE = 0;
    
    /**
     * The default minimum value.
     */
    private static final long DEFAULT_MIN_VALUE = Long.MIN_VALUE;
    
    /**
     * The default maximum value.
     */
    private static final long DEFAULT_MAX_VALUE = Long.MAX_VALUE;
    
    /**
     * Creates a new instance of this class.
     */
    public JLongField() {
        this(Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param locale the locale
     */
    public JLongField(Locale locale){
        this(DEFAULT_VALUE, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     */
    public JLongField(long value){
        this(value, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param locale the locale
     */
    public JLongField(long value, Locale locale){
        this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JLongField(long minValue, long maxValue){
        this(minValue, maxValue, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
    public JLongField(long minValue, long maxValue, Locale locale){
        this(DEFAULT_VALUE, minValue, maxValue, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JLongField(long value, long minValue, long maxValue){
        this(value, minValue, maxValue, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
    public JLongField(long value, long minValue, long maxValue, Locale locale){
        super(new LongFormatterFactory(new LongFormatter()));
        setMinimumLongValue(minValue);
        setMaximumLongValue(maxValue);
        setLongValue(value);
        setLocale(locale);
    }
    
    /**
     * Gets the value.
     * @return the value.
     */
    public long getLongValue(){
        return getBigIntegerValue().longValue();
    }
    
    /**
     * Sets the value.
     * @param i the value
     */
    public void setLongValue(long i){
        setBigIntegerValue(BigInteger.valueOf(i));
    }
    
    /**
     * Gets the minimum value.
     * @return the minimum value
     */
    public long getMinimumLongValue(){
        return getMinimumBigIntegerValue().longValue();
    }
    
    /**
     * Sets the minimum value.
     * @param minLongValue the minimum value
     */
    public void setMinimumLongValue(long minLongValue){
        setMinimumBigIntegerValue(BigInteger.valueOf(minLongValue));
    }
    
    /**
     * Gets the maximum value.
     * @return the maximum value
     */
    public long getMaximumLongValue(){
        return getMaximumBigIntegerValue().longValue();
    }
    
    /**
     * Sets the maximum value.
     * @param maxLongValue the maximum value
     */
    public void setMaximumLongValue(long maxLongValue){
        setMaximumBigIntegerValue(BigInteger.valueOf(maxLongValue));
    }
    
    /**
     * Sets the formatter.
     * Must be an instance of LongFormatter.
     * You should not normally invoke this. See the documentation of the base class for
     * more information.
     * @param formatter the number formatter
     */
    protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
        if (! (formatter instanceof LongFormatter)){
            throw new IllegalArgumentException("formatter must be an instance of LongFormatter!");
        }
        super.setFormatter(formatter);
    }
    
    /**
     * Gets the number formatter.
     * @return the number formatter
     */
    public LongFormatter getLongFormatter(){
        return (LongFormatter) getWholeNumberFormatter();
    }
    
    /**
     * Gets the number formatter factory.
     * @return the number formatter factory
     */
    public LongFormatterFactory getLongFormatterFactory(){
        return (LongFormatterFactory) getWholeNumberFormatterFactory();
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
    public void setLongFormatterFactory(LongFormatterFactory factory){
        setWholeNumberFormatterFactory(factory);
    }
    
    /**
     * Sets the formatter factory.
     * Must be an instance of LongFormatterFactory.
     * Calls the reinit method.
     * Ensures the value stays in the range defined by the minimum and maximum value of
     * the number formatter, which can be obtained by this formatter factory, by either
     * setting it to the maximum value if it is greater than the maximum value or to
     * the minimum value if it is smaller than the minimum value.
     * @param aff the number formatter factory
     */
    public void setFormatterFactory(JFormattedTextField.AbstractFormatterFactory aff) {
        if (! (aff instanceof LongFormatterFactory)){
            throw new IllegalArgumentException("aff must be an instance of LongFormatterFactory!");
        }
        super.setFormatterFactory(aff);
    }
    
}
