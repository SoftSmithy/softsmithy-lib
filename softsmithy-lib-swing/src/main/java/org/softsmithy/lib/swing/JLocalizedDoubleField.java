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
 * JLocalizedDoubleField.java
 *
 * Created on 9. September 2003, 18:26
 */

package org.softsmithy.lib.swing;

import java.math.*;
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.math.Doubles;
import org.softsmithy.lib.swing.text.*;

/**
 * A localized double field.
 * @author  puce
 */
public class JLocalizedDoubleField extends JLocalizedRealNumberField {
    
    /**
     * The default value.
     */
    private static final double DEFAULT_VALUE = 0;
    
    /**
     * The default minimum value.
     */
    private static final double DEFAULT_MIN_VALUE = Doubles.MAX_NEGATIVE_VALUE;
    
    /**
     * The default maximum value.
     */
    private static final double DEFAULT_MAX_VALUE = Double.MAX_VALUE;
    
    /**
     * Creates a new instance of this class.
     */
    public JLocalizedDoubleField() {
        this(Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param locale the locale
     */
    public JLocalizedDoubleField(Locale locale){
        this(DEFAULT_VALUE, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     */
    public JLocalizedDoubleField(double value){
        this(value, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param locale the locale
     */
    public JLocalizedDoubleField(double value, Locale locale){
        this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JLocalizedDoubleField(double minValue, double maxValue){
        this(minValue, maxValue, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
    public JLocalizedDoubleField(double minValue, double maxValue, Locale locale){
        this(DEFAULT_VALUE, minValue, maxValue, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JLocalizedDoubleField(double value, double minValue, double maxValue){
        this(value, minValue, maxValue, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
    public JLocalizedDoubleField(double value, double minValue, double maxValue, Locale locale){
        super(new LocalizedDoubleFormatter());
        setMinimumDoubleValue(minValue);
        setMaximumDoubleValue(maxValue);
        setDoubleValue(value);
        setLocale(locale);
    }
    
    /**
     * Gets the value.
     * @return the value.
     */
    public double getDoubleValue(){
        return getNumberValue().doubleValue();
    }
    
    /**
     * Sets the value.
     * @param i the value
     */
    public void setDoubleValue(double i){
        setNumberValue(new BigDecimal(i));
    }
    
    /**
     * Gets the minimum value.
     * @return the minimum value
     */
    public double getMinimumDoubleValue(){
        return getMinimumNumberValue().doubleValue();
    }
    
    /**
     * Sets the minimum value.
     * @param minDoubleValue the minimum value
     */
    public void setMinimumDoubleValue(double minDoubleValue){
        setMinimumNumberValue(new BigDecimal(minDoubleValue));
    }
    
    /**
     * Gets the maximum value.
     * @return the maximum value
     */
    public double getMaximumDoubleValue(){
        return getMaximumNumberValue().doubleValue();
    }
    
    /**
     * Sets the maximum value.
     * @param maxDoubleValue the maximum value
     */
    public void setMaximumDoubleValue(double maxDoubleValue){
        setMaximumNumberValue(new BigDecimal(maxDoubleValue));
    }
    
    /**
     * Sets the formatter.
     * Must be an instance of LocalizedDoubleFormatter.
     * You should not normally invoke this. See the documentation of the base class for
     * more information.
     * @param formatter the number formatter
     */
    @Override
    protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
        if (! (formatter instanceof LocalizedDoubleFormatter)){
            throw new IllegalArgumentException("formatter must be an instance of LocalizedDoubleFormatter!");
        }
        super.setFormatter(formatter);
    }
    
    
    /**
     * Gets the number formatter.
     * @return the number formatter
     */
    public LocalizedDoubleFormatter getLocalizedDoubleFormatter(){
        return (LocalizedDoubleFormatter) getLocalizedRealNumberFormatter();
    }
    
}
