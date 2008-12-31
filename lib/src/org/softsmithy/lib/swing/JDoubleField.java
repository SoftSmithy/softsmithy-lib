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
import java.text.*;
import javax.swing.JFormattedTextField.*;
import org.softsmithy.lib.math.Doubles;
import org.softsmithy.lib.swing.text.*;


/**
 * A double field.
 * @author  puce
 */
public class JDoubleField extends JRealNumberField {
    
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
    public JDoubleField(){
        this(DEFAULT_VALUE);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     */
    public JDoubleField(double value){
        this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
    }
    
    /**
     * Creates a new instance of this class.
     * @param format the number format
     */
    public JDoubleField(NumberFormat format){
        this(new DoubleFormatterFactory(new DoubleFormatter(format)));
    }
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JDoubleField(double minValue, double maxValue){
        this(DEFAULT_VALUE, minValue, maxValue);
    }
    
    /**
     * Creates a new instance of this class.
     * @param format the number format
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JDoubleField(NumberFormat format, double minValue, double maxValue){
        this(new DoubleFormatterFactory(new DoubleFormatter(format)));
        init(DEFAULT_VALUE, minValue, maxValue);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JDoubleField(double value, double minValue, double maxValue){
        this(new DoubleFormatterFactory(new DoubleFormatter()));
        init(value, minValue, maxValue);
    }
    
    /**
     * Creates a new instance of this class.
     * @param factory the number formatter factory
     */
    public JDoubleField(DoubleFormatterFactory factory){
        super(factory);
        init(DEFAULT_VALUE, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
    }
    
    /**
     * Initializes this number field.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    private void init(double value, double minValue, double maxValue){
        setMinimumDoubleValue(minValue);
        setMaximumDoubleValue(maxValue);
        setDoubleValue(value);
    }
    
    /**
     * Gets the value.
     * @return the value.
     */
    public double getDoubleValue(){
        return getBigDecimalValue().doubleValue();
    }
    
    /**
     * Sets the value.
     * @param value the value
     */
    public void setDoubleValue(double value){
        setBigDecimalValue(new BigDecimal(value));
    }
    
    /**
     * Gets the minimum value.
     * @return the minimum value
     */
    public double getMinimumDoubleValue(){
        return getMinimumBigDecimalValue().doubleValue();
    }
    
    /**
     * Sets the minimum value.
     * @param minValue the minimum value
     */
    public void setMinimumDoubleValue(double minValue){
        setMinimumBigDecimalValue(new BigDecimal(minValue));
    }
    
    /**
     * Gets the maximum value.
     * @return the maximum value
     */
    public double getMaximumDoubleValue(){
        return getMaximumBigDecimalValue().doubleValue();
    }
    
    /**
     * Sets the maximum value.
     * @param maxValue the maximum value
     */
    public void setMaximumDoubleValue(double maxValue){
        setMaximumBigDecimalValue(new BigDecimal(maxValue));
    }
    
    /**
     * Sets the formatter.
     * Must be an instance of DoubleFormatter.
     * You should not normally invoke this. See the documentation of the base class for
     * more information.
     * @param formatter the number formatter
     */
    @Override
    protected void setFormatter(AbstractFormatter formatter) {
        if (! (formatter instanceof DoubleFormatter)){
            throw new IllegalArgumentException("formatter must be an instance of DoubleFormatter!");
        }
        super.setFormatter(formatter);
    }
    
    /**
     * Gets the number formatter.
     * @return the number formatter
     */
    public DoubleFormatter getDoubleFormatter(){
        return (DoubleFormatter) getRealNumberFormatter();
    }
    
    /**
     * Gets the number formatter factory.
     * @return the number formatter factory
     */
    public DoubleFormatterFactory getDoubleFormatterFactory(){
        return (DoubleFormatterFactory) getRealNumberFormatterFactory();
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
    public void setDoubleFormatterFactory(DoubleFormatterFactory factory){
        setRealNumberFormatterFactory(factory);
    }
    
    /**
     * Sets the formatter factory.
     * Must be an instance of DoubleFormatterFactory.
     * Calls the reinit method.
     * Ensures the value stays in the range defined by the minimum and maximum value of
     * the number formatter, which can be obtained by this formatter factory, by either
     * setting it to the maximum value if it is greater than the maximum value or to
     * the minimum value if it is smaller than the minimum value.
     * @param aff the number formatter factory
     */
    @Override
    public void setFormatterFactory(AbstractFormatterFactory aff) {
        if (! (aff instanceof DoubleFormatterFactory)){
            throw new IllegalArgumentException("aff must be an instance of DoubleFormatterFactory!");
        }
        super.setFormatterFactory(aff);
    }
    
}
