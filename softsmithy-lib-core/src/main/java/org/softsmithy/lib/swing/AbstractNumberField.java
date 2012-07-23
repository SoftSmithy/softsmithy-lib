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

import java.util.*;
import javax.swing.*;
import javax.swing.JFormattedTextField.*;
import org.softsmithy.lib.swing.text.*;


/**
 * The base class of the number fields.
 * @author  puce
 */
public abstract class AbstractNumberField extends JFormattedTextField {
    
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param factory the number formatter factory
     */
    public AbstractNumberField(Number value, AbstractXNumberFormatterFactory factory){
        this(value, null, null, factory);
    }
    
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param factory the number formatter factory
     */
    public AbstractNumberField(Number minValue, Number maxValue, AbstractXNumberFormatterFactory factory){
        this(null, minValue, maxValue, factory);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param factory the number formatter factory
     */
    public AbstractNumberField(Number value, Number minValue, Number maxValue, AbstractXNumberFormatterFactory factory){
        this(factory);
        setMinimumNumberValue(minValue);
        setMaximumNumberValue(maxValue);
        setNumberValue(value);
    }
    
    /**
     * Creates a new instance of this class.
     * @param factory the number formatter factory
     */
    public AbstractNumberField(AbstractXNumberFormatterFactory factory){
        super(factory);
        setHorizontalAlignment(JTextField.TRAILING); // is this right???
        setNumberValue(null);
        reinit(); // TODO: Not good! Shouldn't call a protected method here.
    }
    
    /**
     * Reinits this component.
     * By default it does nothing but may be overriden by subclasses.
     * It gets called by the constructors,
     * the setLocale and the setFormatterFactory methods.
     */
    protected void reinit(){
    }
    
    /**
     * Sets the locale of this component.
     * Calls the reinit method.
     * @param locale the locale
     */
    @Override
    public void setLocale(Locale locale) {
        super.setLocale(locale);
        reinit();
    }
    
    /**
     * Gets the value as a Number.
     * @return the value as a Number
     */
    public Number getNumberValue(){
        return (Number) getValue();
    }
    
    /**
     * Sets the number value.
     * @param value the number value
     */
    public void setNumberValue(Number value){
        setValue(value);
    }
    
    /**
     * Sets the value.
     * Must be an instance of Number.
     * @param value the number value
     */
    @Override
    public void setValue(Object value) {
        // use Number to recognize if the value is out of range if the range is
        // (Integer.MIN_VALUE, Integer.MAX_VALUE)
        if (value != null && ! (value instanceof Number)){
            throw new IllegalArgumentException("value must be an instance of Number");
        }
        //    if (value instanceof Integer){
        //      value = Number.valueOf(((Integer) value).intValue());
        //    }
        //    if (((Number) value).compareTo(getIntegerFormatter().getMinimum()) < 0){
        //      value = getIntegerFormatter().getMinimum();
        //    }
        if (value != null){
            value = getAbstractXNumberFormatter().valueToRange((Number) value);
        }
        super.setValue(value);
    }
    
    //  public Object getValue(){
    //    if (super.getValue() != null){
    //    System.out.println(super.getValue().getClass());
    //    }
    //    return super.getValue() != null ? new Integer(((Number) super.getValue()).intValue()) : null;
    //  }
    
    /**
     * Gets the minimum number value.
     * @return the minimum number value
     */
    public Number getMinimumNumberValue(){
        return getAbstractXNumberFormatter().getMinimumNumberValue();
    }
    
    /**
     * Sets the minimum number value.
     * If the value is smaller than the minimum value, it is set to the minimum value
     * instead.
     * @param minValue the minimum number value
     */
    public void setMinimumNumberValue(Number minValue){
        getAbstractXNumberFormatter().setMinimumNumberValue(minValue);
        setValue(getValue());
    }
    
    /**
     * Gets the maximum number value.
     * @return the maximum number value
     */
    public Number getMaximumNumberValue(){
        return getAbstractXNumberFormatter().getMaximumNumberValue();
    }
    
    /**
     * Sets the maximum number value.
     * If the value is greater than the maximum value, it is set to the maximum value
     * instead.
     * @param maxValue the maximum number value
     */
    public void setMaximumNumberValue(Number maxValue){
        getAbstractXNumberFormatter().setMaximumNumberValue(maxValue);
        setValue(getValue());
    }
    
    /**
     * Sets the formatter.
     * Must be an instance of AbstractXNumberFormatter.
     * You should not normally invoke this. See the documentation of the base class for
     * more information.
     * @param formatter the number formatter
     */
    @Override
    protected void setFormatter(AbstractFormatter formatter) {
        if (! (formatter instanceof AbstractXNumberFormatter)){
            throw new IllegalArgumentException("formatter must be an instance of AbstractXNumberFormatter!");
        }
        super.setFormatter(formatter);
    }
    
    /**
     * Gets the number formatter.
     * @return the number formatter
     */
    public AbstractXNumberFormatter getAbstractXNumberFormatter(){
        return (AbstractXNumberFormatter) getFormatter();
    }
    
    /**
     * Gets the number formatter factory.
     * @return the number formatter factory
     */
    public AbstractXNumberFormatterFactory getAbstractXNumberFormatterFactory(){
        return (AbstractXNumberFormatterFactory) getFormatterFactory();
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
    public void setAbstractXNumberFormatterFactory(AbstractXNumberFormatterFactory factory){
        setFormatterFactory(factory);
    }
    
    /**
     * Sets the formatter factory.
     * Must be an instance of AbstractXNumberFormatterFactory.
     * Calls the reinit method.
     * Ensures the value stays in the range defined by the minimum and maximum value of
     * the number formatter, which can be obtained by this formatter factory, by either
     * setting it to the maximum value if it is greater than the maximum value or to
     * the minimum value if it is smaller than the minimum value.
     * @param aff the number formatter factory
     */
    @Override
    public void setFormatterFactory(AbstractFormatterFactory aff) {
        if (! (aff instanceof AbstractXNumberFormatterFactory)){
            throw new IllegalArgumentException("aff must be an instance of AbstractXNumberFormatterFactory!");
        }
        super.setFormatterFactory(aff);
        reinit();
        setValue(getValue());
    }
    
}
