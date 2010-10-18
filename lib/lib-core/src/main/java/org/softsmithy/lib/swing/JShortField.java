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

package org.softsmithy.lib.swing;

import java.math.*;
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.swing.text.*;

/**
 * A short field.
 * @author  puce
 */
public class JShortField extends JWholeNumberField {
    
    /**
     * The default value.
     */
    private static final short DEFAULT_VALUE = 0;
    
    /**
     * The default minimum value.
     */
    private static final short DEFAULT_MIN_VALUE = Short.MIN_VALUE;
    
    /**
     * The default maximum value.
     */
    private static final short DEFAULT_MAX_VALUE = Short.MAX_VALUE;
    
    /**
     * Creates a new instance of this class.
     */
    public JShortField() {
        this(Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param locale the locale
     */
    public JShortField(Locale locale){
        this(DEFAULT_VALUE, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     */
    public JShortField(short value){
        this(value, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param locale the locale
     */
    public JShortField(short value, Locale locale){
        this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JShortField(short minValue, short maxValue){
        this(minValue, maxValue, Locale.getDefault());
    }
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
    public JShortField(short minValue, short maxValue, Locale locale){
        this(DEFAULT_VALUE, minValue, maxValue, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JShortField(short value, short minValue, short maxValue){
        this(value, minValue, maxValue, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
    public JShortField(short value, short minValue, short maxValue, Locale locale){
        super(new ShortFormatterFactory(new ShortFormatter()));
        setMinimumShortValue(minValue);
        setMaximumShortValue(maxValue);
        setShortValue(value);
        setLocale(locale);
    }
    
    
    /**
     * Gets the value.
     * @return the value.
     */
    public short getShortValue(){
        return getBigIntegerValue().shortValue();
    }
    
    /**
     * Sets the value.
     * @param i the value
     */
    public void setShortValue(short i){
        setBigIntegerValue(BigInteger.valueOf(i));
    }
    
    /**
     * Gets the minimum value.
     * @return the minimum value
     */
    public short getMinimumShortValue(){
        return getMinimumBigIntegerValue().shortValue();
    }
    
    /**
     * Sets the minimum value.
     *
     * @param minShortValue the minimum value
     */
    public void setMinimumShortValue(short minShortValue){
        setMinimumBigIntegerValue(BigInteger.valueOf(minShortValue));
    }
    
    /**
     * Gets the maximum value.
     * @return the maximum value
     */
    public short getMaximumShortValue(){
        return getMaximumBigIntegerValue().shortValue();
    }
    
    
    /**
     * Sets the maximum value.
     *
     *
     * @param maxShortValue the maximum value
     */
    public void setMaximumShortValue(short maxShortValue){
        setMaximumBigIntegerValue(BigInteger.valueOf(maxShortValue));
    }
    
    /**
     * Sets the formatter.
     * Must be an instance of ShortFormatter.
     * You should not normally invoke this. See the documentation of the base class for
     * more information.
     * @param formatter the number formatter
     */
    @Override
    protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
        if (! (formatter instanceof ShortFormatter)){
            throw new IllegalArgumentException("formatter must be an instance of ShortFormatter!");
        }
        super.setFormatter(formatter);
    }
    
    /**
     * Gets the number formatter.
     * @return the number formatter
     */
    public ShortFormatter getShortFormatter(){
        return (ShortFormatter) getWholeNumberFormatter();
    }
    
    /**
     * Gets the number formatter factory.
     * @return the number formatter factory
     */
    public ShortFormatterFactory getShortFormatterFactory(){
        return (ShortFormatterFactory) getWholeNumberFormatterFactory();
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
    public void setShortFormatterFactory(ShortFormatterFactory factory){
        setWholeNumberFormatterFactory(factory);
    }
    
    /**
     * Sets the formatter factory.
     * Must be an instance of ShortFormatterFactory.
     * Calls the reinit method.
     * Ensures the value stays in the range defined by the minimum and maximum value of
     * the number formatter, which can be obtained by this formatter factory, by either
     * setting it to the maximum value if it is greater than the maximum value or to
     * the minimum value if it is smaller than the minimum value.
     * @param aff the number formatter factory
     */
    @Override
    public void setFormatterFactory(JFormattedTextField.AbstractFormatterFactory aff) {
        if (! (aff instanceof ShortFormatterFactory)){
            throw new IllegalArgumentException("aff must be an instance of ShortFormatterFactory!");
        }
        super.setFormatterFactory(aff);
    }
    
}
