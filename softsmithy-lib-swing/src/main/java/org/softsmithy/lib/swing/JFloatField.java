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
import javax.swing.JFormattedTextField.AbstractFormatter;
import org.softsmithy.lib.math.Floats;
import org.softsmithy.lib.swing.text.*;


/**
 * A float field.
 * @author  puce
 */
public class JFloatField extends JRealNumberField {
    
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
    public JFloatField(){
        this(DEFAULT_VALUE);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     */
    public JFloatField(float value){
        this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
    }
    
    /**
     * Creates a new instance of this class.
     * @param format the number format
     */
    public JFloatField(NumberFormat format){
        this(new FloatFormatter(format));
    }
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JFloatField(float minValue, float maxValue){
        this(DEFAULT_VALUE, minValue, maxValue);
    }
    
    /**
     * Creates a new instance of this class.
     * @param format the number format
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JFloatField(NumberFormat format, float minValue, float maxValue){
        this(new FloatFormatter(format));
        init(DEFAULT_VALUE, minValue, maxValue);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JFloatField(float value, float minValue, float maxValue){
        this(new FloatFormatter());
        init(value, minValue, maxValue);
    }
    
    /**
     * Creates a new instance of this class.
     * @param factory the number formatter factory
     */
    private JFloatField(FloatFormatter formatter){
        super(new NumberFormatterFactory<>(FloatFormatter.class, formatter));
        init(DEFAULT_VALUE, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
    }
    
    /**
     * Initializes this number field.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    private void init(float value, float minValue, float maxValue){
        setMinimumFloatValue(minValue);
        setMaximumFloatValue(maxValue);
        setFloatValue(value);
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
     * @param value the value
     */
    public void setFloatValue(float value){
        setNumberValue(new BigDecimal(value));
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
     * @param minValue the minimum value
     */
    public void setMinimumFloatValue(float minValue){
        setMinimumNumberValue(new BigDecimal(minValue));
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
     * @param maxValue the maximum value
     */
    public void setMaximumFloatValue(float maxValue){
        setMaximumNumberValue(new BigDecimal(maxValue));
    }
    
    /**
     * Sets the formatter.
     * Must be an instance of FloatFormatter.
     * You should not normally invoke this. See the documentation of the base class for
     * more information.
     * @param formatter the number formatter
     */
    @Override
    protected void setFormatter(AbstractFormatter formatter) {
        if (! (formatter instanceof FloatFormatter)){
            throw new IllegalArgumentException("formatter must be an instance of FloatFormatter!");
        }
        super.setFormatter(formatter);
    }
    
    /**
     * Gets the number formatter.
     * @return the number formatter
     */
    public FloatFormatter getFloatFormatter(){
        return (FloatFormatter) getAbstractXNumberFormatter();
    }
    
}
