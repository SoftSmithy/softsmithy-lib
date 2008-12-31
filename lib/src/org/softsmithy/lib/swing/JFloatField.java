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
import java.text.*;
import javax.swing.JFormattedTextField.*;
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
        this(new FloatFormatterFactory(new FloatFormatter(format)));
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
        this(new FloatFormatterFactory(new FloatFormatter(format)));
        init(DEFAULT_VALUE, minValue, maxValue);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JFloatField(float value, float minValue, float maxValue){
        this(new FloatFormatterFactory(new FloatFormatter()));
        init(value, minValue, maxValue);
    }
    
    /**
     * Creates a new instance of this class.
     * @param factory the number formatter factory
     */
    public JFloatField(FloatFormatterFactory factory){
        super(factory);
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
        return getBigDecimalValue().floatValue();
    }
    
    /**
     * Sets the value.
     * @param value the value
     */
    public void setFloatValue(float value){
        setBigDecimalValue(new BigDecimal(value));
    }
    
    /**
     * Gets the minimum value.
     * @return the minimum value
     */
    public float getMinimumFloatValue(){
        return getMinimumBigDecimalValue().floatValue();
    }
    
    /**
     * Sets the minimum value.
     * @param minValue the minimum value
     */
    public void setMinimumFloatValue(float minValue){
        setMinimumBigDecimalValue(new BigDecimal(minValue));
    }
    
    /**
     * Gets the maximum value.
     * @return the maximum value
     */
    public float getMaximumFloatValue(){
        return getMaximumBigDecimalValue().floatValue();
    }
    
    /**
     * Sets the maximum value.
     * @param maxValue the maximum value
     */
    public void setMaximumFloatValue(float maxValue){
        setMaximumBigDecimalValue(new BigDecimal(maxValue));
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
        return (FloatFormatter) getRealNumberFormatter();
    }
    
    /**
     * Gets the number formatter factory.
     * @return the number formatter factory
     */
    public FloatFormatterFactory getFloatFormatterFactory(){
        return (FloatFormatterFactory) getRealNumberFormatterFactory();
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
    public void setFloatFormatterFactory(FloatFormatterFactory factory){
        setRealNumberFormatterFactory(factory);
    }
    
    /**
     * Sets the formatter factory.
     * Must be an instance of FloatFormatterFactory.
     * Calls the reinit method.
     * Ensures the value stays in the range defined by the minimum and maximum value of
     * the number formatter, which can be obtained by this formatter factory, by either
     * setting it to the maximum value if it is greater than the maximum value or to
     * the minimum value if it is smaller than the minimum value.
     * @param aff the number formatter factory
     */
    @Override
    public void setFormatterFactory(AbstractFormatterFactory aff) {
        if (! (aff instanceof FloatFormatterFactory)){
            throw new IllegalArgumentException("aff must be an instance of FloatFormatterFactory!");
        }
        super.setFormatterFactory(aff);
    }
    
    
    
}
