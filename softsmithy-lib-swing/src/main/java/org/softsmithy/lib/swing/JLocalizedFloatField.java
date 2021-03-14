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
 * JLocalizedFloatField.java
 *
 * Created on 9. September 2003, 18:26
 */

package org.softsmithy.lib.swing;

import java.math.*;
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.math.Floats;
import org.softsmithy.lib.swing.text.*;

/**
 * A localized float field.
 * @author  puce
 */
public class JLocalizedFloatField extends JLocalizedRealNumberField {
    
    /**
     * The default value.
     */
    private static final float DEFAULT_VALUE = 0;
    
    /**
     * The default minimum value.
     */
    private static final float DEFAULT_MIN_VALUE = Floats.MAX_NEGATIVE_VALUE;
    
    /**
     * The default maximum value.
     */
    private static final float DEFAULT_MAX_VALUE = Float.MAX_VALUE;
    
    /**
     * Creates a new instance of this class.
     */
    public JLocalizedFloatField() {
        this(Locale.getDefault());
    }
    
    
    /**
     * Creates a new instance of this class.
     * @param locale the locale
     */
    public JLocalizedFloatField(Locale locale){
        this(DEFAULT_VALUE, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     */
    public JLocalizedFloatField(float value){
        this(value, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param locale the locale
     */
    public JLocalizedFloatField(float value, Locale locale){
        this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JLocalizedFloatField(float minValue, float maxValue){
        this(minValue, maxValue, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
    public JLocalizedFloatField(float minValue, float maxValue, Locale locale){
        this(DEFAULT_VALUE, minValue, maxValue, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JLocalizedFloatField(float value, float minValue, float maxValue){
        this(value, minValue, maxValue, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
    public JLocalizedFloatField(float value, float minValue, float maxValue, Locale locale){
        super(new NumberFormatterFactory<>(LocalizedFloatFormatter.class, new LocalizedFloatFormatter()));
        setMinimumFloatValue(minValue);
        setMaximumFloatValue(maxValue);
        setFloatValue(value);
        setLocale(locale);
    }
    
    /**
     * Gets the value.
     * @return the value.
     */
    public float getFloatValue(){
        return getNumberValue().floatValue();
    }
    
    /**
     * Sets the value.
     * @param i the value
     */
    public void setFloatValue(float i){
        setNumberValue(new BigDecimal(i));
    }
    
    /**
     * Gets the minimum value.
     * @return the minimum value
     */
    public float getMinimumFloatValue(){
        return getMinimumNumberValue().floatValue();
    }
    
    /**
     * Sets the minimum value.
     * @param minFloatValue the minimum value
     */
    public void setMinimumFloatValue(float minFloatValue){
        setMinimumNumberValue(new BigDecimal(minFloatValue));
    }
    
    /**
     * Gets the maximum value.
     * @return the maximum value
     */
    public float getMaximumFloatValue(){
        return getMaximumNumberValue().floatValue();
    }
    
    /**
     * Sets the maximum value.
     * @param maxFloatValue the maximum value
     */
    public void setMaximumFloatValue(float maxFloatValue){
        setMaximumNumberValue(new BigDecimal(maxFloatValue));
    }
    
    /**
     * Sets the formatter.
     * Must be an instance of LocalizedFloatFormatter.
     * You should not normally invoke this. See the documentation of the base class for
     * more information.
     * @param formatter the number formatter
     */
    @Override
    protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
        if (! (formatter instanceof LocalizedFloatFormatter)){
            throw new IllegalArgumentException("formatter must be an instance of LocalizedFloatFormatter!");
        }
        super.setFormatter(formatter);
    }
    
    /**
     * Gets the number formatter.
     * @return the number formatter
     */
    public LocalizedFloatFormatter getLocalizedFloatFormatter(){
        return (LocalizedFloatFormatter) getLocalizedRealNumberFormatter();
    }
    
}
