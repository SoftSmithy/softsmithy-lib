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
import org.softsmithy.lib.swing.text.*;


/**
 * A number field for arbitrary big real numbers.
 * @author  puce
 */
public class JRealNumberField extends AbstractNumberField {
    
    /**
     * Creates a new instance of this class.
     */
    public JRealNumberField(){
        this(BigDecimal.valueOf(0));
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     */
    public JRealNumberField(BigDecimal value){
        this(value, null, null);
    }
    
    /**
     * Creates a new instance of this class.
     * @param format the number format
     */
    public JRealNumberField(NumberFormat format){
        this(new RealNumberFormatterFactory(new RealNumberFormatter(format)));
    }
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JRealNumberField(BigDecimal minValue, BigDecimal maxValue){
        this(BigDecimal.valueOf(0), minValue, maxValue);
    }
    
    /**
     * Creates a new instance of this class.
     * @param format the number format
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JRealNumberField(NumberFormat format, BigDecimal minValue, BigDecimal maxValue){
        this(new RealNumberFormatterFactory(new RealNumberFormatter(format)));
        init(BigDecimal.valueOf(0), minValue, maxValue);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JRealNumberField(BigDecimal value, BigDecimal minValue, BigDecimal maxValue){
        this(new RealNumberFormatterFactory(new RealNumberFormatter()));
        init(value, minValue, maxValue);
    }
    
    /**
     * Creates a new instance of this class.
     * @param factory the number formatter factory
     */
    public JRealNumberField(RealNumberFormatterFactory factory){
        super(factory);
        init(BigDecimal.valueOf(0), null, null);
    }
    
    /**
     * Initializes this number field.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    private void init(BigDecimal value, BigDecimal minValue, BigDecimal maxValue){
        setMinimumBigDecimalValue(minValue);
        setMaximumBigDecimalValue(maxValue);
        setBigDecimalValue(value);
    }
    
    /**
     * Gets the value.
     * @return the value
     */
    public BigDecimal getBigDecimalValue(){
        return (BigDecimal) getNumberValue();
    }
    
    
    /**
     * Sets the value.
     * @param value the value
     */
    public void setBigDecimalValue(BigDecimal value){
        setNumberValue(value);
    }
    
    /**
     * Sets the value.
     * Must be an instance of BigDecimal or null.
     * @param value the value
     */
    public void setValue(Object value) {
        // use BigDecimal to recognize if the value is out of range if the range is
        // (Integer.MIN_VALUE, Integer.MAX_VALUE)
        if (value != null && ! (value instanceof BigDecimal)){
            throw new IllegalArgumentException("value must be an instance of BigDecimal");
        }
        super.setValue(value);
    }
    
    //  public Object getValue(){
    //    if (super.getValue() != null){
    //    System.out.println(super.getValue().getClass());
    //    }
    //    return super.getValue() != null ? new Integer(((BigDecimal) super.getValue()).intValue()) : null;
    //  }
    
    /**
     * Gets the minimum value.
     * @return the minimum value
     */
    public BigDecimal getMinimumBigDecimalValue(){
        return (BigDecimal) getMinimumNumberValue();
    }
    
    /**
     * Sets the minimum value.
     * @param minValue the minimum value
     */
    public void setMinimumBigDecimalValue(BigDecimal minValue){
        setMinimumNumberValue(minValue);
    }
    
    /**
     * Gets the maximum value.
     * @return the maximum value
     */
    public BigDecimal getMaximumBigDecimalValue(){
        return (BigDecimal) getMaximumNumberValue();
    }
    
    /**
     * Sets the maximum value.
     * @param maxValue the maximum value
     */
    public void setMaximumBigDecimalValue(BigDecimal maxValue){
        setMaximumNumberValue(maxValue);
    }
    
    /**
     * Sets the formatter.
     * Must be an instance of RealNumberFormatter.
     * You should not normally invoke this. See the documentation of the base class for
     * more information.
     * @param formatter the number formatter
     */
    protected void setFormatter(AbstractFormatter formatter) {
        if (! (formatter instanceof RealNumberFormatter)){
            throw new IllegalArgumentException("formatter must be an instance of RealNumberFormatter!");
        }
        super.setFormatter(formatter);
    }
    
    /**
     * Gets the number formatter.
     * @return the number formatter
     */
    public RealNumberFormatter getRealNumberFormatter(){
        return (RealNumberFormatter) getAbstractXNumberFormatter();
    }
    
    /**
     * Gets the number formatter factory.
     * @return the number formatter factory
     */
    public RealNumberFormatterFactory getRealNumberFormatterFactory(){
        return (RealNumberFormatterFactory) getAbstractXNumberFormatterFactory();
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
    public void setRealNumberFormatterFactory(RealNumberFormatterFactory factory){
        setAbstractXNumberFormatterFactory(factory);
    }
    
    /**
     * Sets the formatter factory.
     * Must be an instance of RealNumberFormatterFactory.
     * Calls the reinit method.
     * Ensures the value stays in the range defined by the minimum and maximum value of
     * the number formatter, which can be obtained by this formatter factory, by either
     * setting it to the maximum value if it is greater than the maximum value or to
     * the minimum value if it is smaller than the minimum value.
     * @param aff the number formatter factory
     */
    public void setFormatterFactory(AbstractFormatterFactory aff) {
        if (! (aff instanceof RealNumberFormatterFactory)){
            throw new IllegalArgumentException("aff must be an instance of RealNumberFormatterFactory!");
        }
        super.setFormatterFactory(aff);
    }
    
    /**
     * Sets the maximum value.
     * Must be null or an instance of BigDecimal!
     * @param maxValue the maximum value (BigDecimal)
     */
    public void setMaximumNumberValue(Number maxValue) {
        if (maxValue != null && ! (maxValue instanceof BigDecimal)){
            throw new IllegalArgumentException("maxValue must be an instance of BigDecimal");
        }
        super.setMaximumNumberValue(maxValue);
    }
    
    /**
     * Sets the minimum value.
     * Must be null or an instance of BigDecimal!
     * @param minValue the minimum value (BigDecimal)
     */
    public void setMinimumNumberValue(Number minValue) {
        if (minValue != null && ! (minValue instanceof BigDecimal)){
            throw new IllegalArgumentException("minValue must be an instance of BigDecimal");
        }
        super.setMinimumNumberValue(minValue);
    }
    
}
