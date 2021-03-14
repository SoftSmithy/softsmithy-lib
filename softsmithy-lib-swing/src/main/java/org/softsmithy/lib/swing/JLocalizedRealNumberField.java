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
 * JDecimalField.java
 *
 * Created on 9. September 2003, 18:26
 */

package org.softsmithy.lib.swing;

import java.math.*;
import java.util.*;
import javax.swing.*;
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
        this(new NumberFormatterFactory<>(LocalizedRealNumberFormatter.class, new LocalizedRealNumberFormatter()));
        setMinimumNumberValue(minValue);
        setMaximumNumberValue(maxValue);
        setNumberValue(value);
        setLocale(locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param factory the number formatter factory
     */
    protected JLocalizedRealNumberField(NumberFormatterFactory<BigDecimal, RealNumberFormatter> factory){
        super(factory);
    }
    
    /**
     * Applys the locale to the number formatter factory.
     * It gets called by the constructors,
     * the setLocale and the setFormatterFactory methods.
     */
    @Override
    protected void reinit(){
        getLocalizedRealNumberFormatter().setLocale(getLocale());
    }
    
    /**
     * Sets the formatter.
     * Must be an instance of LocalizedRealNumberFormatter.
     * You should not normally invoke this. See the documentation of the base class for
     * more information.
     * @param formatter the number formatter
     */
    @Override
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
        return (LocalizedRealNumberFormatter) getAbstractXNumberFormatter();
    }
    
}
