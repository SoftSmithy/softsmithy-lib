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
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.swing.text.*;

/**
 * An integer field.
 * @author puce
 */
public class JIntegerField extends JWholeNumberField {
    
    /**
     * The default value.
     */
    private static final int DEFAULT_VALUE = 0;
    /**
     * The default minimum value.
     */
    private static final int DEFAULT_MIN_VALUE = Integer.MIN_VALUE;
    /**
     * The default maximum value.
     */
    private static final int DEFAULT_MAX_VALUE = Integer.MAX_VALUE;
    
    /**
     * Creates a new instance of this class.
     */
    public JIntegerField() {
        this(Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param locale the locale
     */
    public JIntegerField(Locale locale){
        this(DEFAULT_VALUE, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     */
    public JIntegerField(int value){
        this(value, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param locale the locale
     */
    public JIntegerField(int value, Locale locale){
        this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JIntegerField(int minValue, int maxValue){
        this(minValue, maxValue, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
    public JIntegerField(int minValue, int maxValue, Locale locale){
        this(DEFAULT_VALUE, minValue, maxValue, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JIntegerField(int value, int minValue, int maxValue){
        this(value, minValue, maxValue, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
    public JIntegerField(int value, int minValue, int maxValue, Locale locale){
        super(new NumberFormatterFactory<>(IntegerFormatter.class, new IntegerFormatter()));
        setMinimumIntValue(minValue);
        setMaximumIntValue(maxValue);
        setIntValue(value);
        setLocale(locale);
    }
    
    
    /**
     * Gets the value.
     * @return the value
     */
    public int getIntValue(){
        return getNumberValue().intValue();
    }
    
    /**
     * Sets the value
     * @param i the value
     */
    public void setIntValue(int i){
        setNumberValue(BigInteger.valueOf(i));
    }
    
    /**
     * Gets the minimum value.
     * @return the minimum value.
     */
    public int getMinimumIntValue(){
        return getMinimumNumberValue().intValue();
    }
    
    /**
     * Sets the maximum value.
     * @param minIntValue the minimum value
     */
    public void setMinimumIntValue(int minIntValue){
        setMinimumNumberValue(BigInteger.valueOf(minIntValue));
    }
    
    /**
     * Gets the maximum value.
     * @return the maximum value
     */
    public int getMaximumIntValue(){
        return getMaximumNumberValue().intValue();
    }
    
    /**
     * Sets the maximum value.
     * @param maxIntValue the maximum value
     */
    public void setMaximumIntValue(int maxIntValue){
        setMaximumNumberValue(BigInteger.valueOf(maxIntValue));
    }
    
    /**
     * Sets the formatter.
     * Must be an instance of IntegerFormatter.
     * You should not normally invoke this. See the documentation of the base class for
     * more information.
     * @param formatter the number formatter
     */
    @Override
    protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
        if (! (formatter instanceof IntegerFormatter)){
            throw new IllegalArgumentException("formatter must be an instance of IntegerFormatter!");
        }
        super.setFormatter(formatter);
    }
    
    /**
     * Gets the number formatter.
     * @return the number formatter
     */
    public IntegerFormatter getIntegerFormatter(){
        return (IntegerFormatter) getAbstractXNumberFormatter();
    }

    
}