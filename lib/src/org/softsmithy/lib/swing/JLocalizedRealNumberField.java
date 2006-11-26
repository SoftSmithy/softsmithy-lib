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
 * JDecimalField.java
 *
 * Created on 9. September 2003, 18:26
 */

package org.softsmithy.lib.swing;

import java.math.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import org.softsmithy.lib.swing.text.*;

/**
 * A localized number field for arbitrary big real numbers.
 * @author  puce
 */
public class JLocalizedRealNumberField extends JRealNumberField {
    
    
    /**
     * Creates a new instance of this class.
     */
    public JLocalizedRealNumberField() {
        this(Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param locale the locale
     */
    public JLocalizedRealNumberField(Locale locale){
        this(BigDecimal.valueOf(0), locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     */
    public JLocalizedRealNumberField(BigDecimal value){
        this(value, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param locale the locale
     */
    public JLocalizedRealNumberField(BigDecimal value, Locale locale){
        this(value, null, null, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JLocalizedRealNumberField(BigDecimal minValue, BigDecimal maxValue){
        this(minValue, maxValue, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
    public JLocalizedRealNumberField(BigDecimal minValue, BigDecimal maxValue, Locale locale){
        this(BigDecimal.valueOf(0), minValue, maxValue, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JLocalizedRealNumberField(BigDecimal value, BigDecimal minValue, BigDecimal maxValue){
        this(value, minValue, maxValue, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
    public JLocalizedRealNumberField(BigDecimal value, BigDecimal minValue, BigDecimal maxValue, Locale locale){
        this(new LocalizedRealNumberFormatterFactory(new LocalizedRealNumberFormatter()));
        setMinimumBigDecimalValue(minValue);
        setMaximumBigDecimalValue(maxValue);
        setBigDecimalValue(value);
        setLocale(locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param factory the number formatter factory
     */
    public JLocalizedRealNumberField(LocalizedRealNumberFormatterFactory factory){
        super(factory);
    }
    
    /**
     * Applys the locale to the number formatter factory.
     * It gets called by the constructors,
     * the setLocale and the setFormatterFactory methods.
     */
    protected void reinit(){
        getLocalizedRealNumberFormatterFactory().setLocale(getLocale());
    }
    
    /**
     * Sets the formatter.
     * Must be an instance of LocalizedRealNumberFormatter.
     * You should not normally invoke this. See the documentation of the base class for
     * more information.
     * @param formatter the number formatter
     */
    protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
        if (! (formatter instanceof LocalizedRealNumberFormatter)){
            throw new IllegalArgumentException("formatter must be an instance of LocalizedRealNumberFormatter!");
        }
        super.setFormatter(formatter);
    }
    
    /**
     * Gets the number formatter.
     * @return the number formatter
     */
    public LocalizedRealNumberFormatter getLocalizedRealNumberFormatter(){
        return (LocalizedRealNumberFormatter) getRealNumberFormatter();
    }
    
    /**
     * Gets the number formatter factory.
     * @return the number formatter factory
     */
    public LocalizedRealNumberFormatterFactory getLocalizedRealNumberFormatterFactory(){
        return (LocalizedRealNumberFormatterFactory) getRealNumberFormatterFactory();
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
    public void setLocalizedRealNumberFormatterFactory(LocalizedRealNumberFormatterFactory factory){
        setRealNumberFormatterFactory(factory);
    }
    
    /**
     * Sets the formatter factory.
     * Must be an instance of LocalizedRealNumberFormatterFactory.
     * Calls the reinit method.
     * Ensures the value stays in the range defined by the minimum and maximum value of
     * the number formatter, which can be obtained by this formatter factory, by either
     * setting it to the maximum value if it is greater than the maximum value or to
     * the minimum value if it is smaller than the minimum value.
     * @param aff the number formatter factory
     */
    public void setFormatterFactory(JFormattedTextField.AbstractFormatterFactory aff) {
        if (! (aff instanceof LocalizedRealNumberFormatterFactory)){
            throw new IllegalArgumentException("aff must be an instance of LocalizedRealNumberFormatterFactory!");
        }
        super.setFormatterFactory(aff);
    }
    
}
