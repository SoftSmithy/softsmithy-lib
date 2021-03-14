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
import org.softsmithy.lib.swing.text.*;

/**
 * A number field for arbitrary big whole numbers.
 * @author puce
 */
public class JWholeNumberField extends AbstractNumberField<BigInteger, WholeNumberFormatter> {
    
    
    /**
     * Creates a new instance of this class.
     */
    public JWholeNumberField() {
        this(Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param locale the locale
     */
    public JWholeNumberField(Locale locale){
        this(BigInteger.ZERO, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     */
    public JWholeNumberField(BigInteger value){
        this(value, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param locale the locale
     */
    public JWholeNumberField(BigInteger value, Locale locale){
        this(value, null, null, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JWholeNumberField(BigInteger minValue, BigInteger maxValue){
        this(minValue, maxValue, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
    public JWholeNumberField(BigInteger minValue, BigInteger maxValue, Locale locale){
        this(BigInteger.ZERO, minValue, maxValue, locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
    public JWholeNumberField(BigInteger value, BigInteger minValue, BigInteger maxValue){
        this(value, minValue, maxValue, Locale.getDefault());
    }
    
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
    public JWholeNumberField(BigInteger value, BigInteger minValue, BigInteger maxValue, Locale locale){
        this(new NumberFormatterFactory<>(WholeNumberFormatter.class, new WholeNumberFormatter()));
        setMinimumNumberValue(minValue);
        setMaximumNumberValue(maxValue);
        setNumberValue(value);
        setLocale(locale);
    }
    
    /**
     * Creates a new instance of this class.
     * @param factory the number formatter factory
     */
    protected JWholeNumberField(NumberFormatterFactory<BigInteger, WholeNumberFormatter> factory){
        super(BigInteger.class, factory);
        setNumberValue(BigInteger.ZERO);
    }
    
    /**
     * Applys the locale to the number formatter factory.
     * It gets called by the constructors,
     * the setLocale and the setFormatterFactory methods.
     */
    @Override
    protected void reinit(){
        getNumberFormatterFactory().getNumberFormatter().setLocale(getLocale());
    }
    
    
    /**
     * Gets the value.
     * @return the value
     */
    @Deprecated
    public BigInteger getBigIntegerValue(){
        return getNumberValue();
    }
    
    /**
     * Sets the value.
     * @param value the value
     */
    @Deprecated
    public void setBigIntegerValue(BigInteger value){
        setNumberValue(value);
    }
    
    
    //  public Object getValue(){
    //    if (super.getValue() != null){
    //    System.out.println(super.getValue().getClass());
    //    }
    //    return super.getValue() != null ? new Integer(((BigInteger) super.getValue()).intValue()) : null;
    //  }
    
    /**
     * Gets the minimum value.
     * @return the minimum value
     */
    @Deprecated
    public BigInteger getMinimumBigIntegerValue(){
        return getMinimumNumberValue();
    }
    
    /**
     * Sets the minimum value.
     * @param minValue the minimum value
     */
    @Deprecated
    public void setMinimumBigIntegerValue(BigInteger minValue){
        setMinimumNumberValue(minValue);
    }
    
    
    /**
     * Gets the maximum value.
     * @return the maximum value
     */
    @Deprecated
    public BigInteger getMaximumBigIntegerValue(){
        return getMaximumNumberValue();
    }
    
    /**
     * Sets the maximum value.
     * @param maxValue the maximum value
     */
    @Deprecated
    public void setMaximumBigIntegerValue(BigInteger maxValue){
        setMaximumNumberValue(maxValue);
    }
   
    
    /**
     * Gets the number formatter.
     * @return the number formatter
     */
    @Deprecated
    public WholeNumberFormatter getWholeNumberFormatter(){
        return getAbstractXNumberFormatter();
    }
    
}
