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
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.swing.text.*;

/**
 * An integer field.
 * @author puce
 */
public class JIntegerField extends JWholeNumberField {
  
    /**
     * The default value.
     */
  private static final int DEFAULT_VALUE = 0;
    /**
     * The default minimum value.
     */
  private static final int DEFAULT_MIN_VALUE = Integer.MIN_VALUE;
    /**
     * The default maximum value.
     */
  private static final int DEFAULT_MAX_VALUE = Integer.MAX_VALUE;
  
  /**
     * Creates a new instance of this class.
     */
  public JIntegerField() {
    this(Locale.getDefault());
  }
  
    /**
     * Creates a new instance of this class.
     * @param locale the locale
     */
  public JIntegerField(Locale locale){
    this(DEFAULT_VALUE, locale);
  }
  
    /**
     * Creates a new instance of this class.
     * @param value the value
     */
  public JIntegerField(int value){
    this(value, Locale.getDefault());
  }
  
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param locale the locale
     */
  public JIntegerField(int value, Locale locale){
    this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, locale);
  }
  
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
  public JIntegerField(int minValue, int maxValue){
    this(minValue, maxValue, Locale.getDefault());
  }
  
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
  public JIntegerField(int minValue, int maxValue, Locale locale){
    this(DEFAULT_VALUE, minValue, maxValue, locale);
  }
  
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
  public JIntegerField(int value, int minValue, int maxValue){
    this(value, minValue, maxValue, Locale.getDefault());
  }
  
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
  public JIntegerField(int value, int minValue, int maxValue, Locale locale){
    super(new IntegerFormatterFactory(new IntegerFormatter()));
    setMinimumIntValue(minValue);
    setMaximumIntValue(maxValue);
    setIntValue(value);
    setLocale(locale);
  }
  
  
    /**
     * Gets the value.
     * @return the value
     */
  public int getIntValue(){
    return getBigIntegerValue().intValue();
  }
  
    /**
     * Sets the value
     * @param i the value
     */
  public void setIntValue(int i){
    setBigIntegerValue(BigInteger.valueOf(i));
  }
  
    /**
     * Gets the minimum value.
     * @return the minimum value.
     */
  public int getMinimumIntValue(){
    return getMinimumBigIntegerValue().intValue();
  }
  
    /**
     * Sets the maximum value.
     * @param minIntValue the minimum value
     */
  public void setMinimumIntValue(int minIntValue){
    setMinimumBigIntegerValue(BigInteger.valueOf(minIntValue));
  }
  
    /**
     * Gets the maximum value.
     * @return the maximum value
     */
  public int getMaximumIntValue(){
    return getMaximumBigIntegerValue().intValue();
  }
  
    /**
     * Set the maximum value.
     * @param maxIntValue the maximum value
     */
  public void setMaximumIntValue(int maxIntValue){
    setMaximumBigIntegerValue(BigInteger.valueOf(maxIntValue));
  }
  
    /**
     * Sets the formatter.
     * Must be an instance of IntegerFormatter.
     * You should not normally invoke this. See the documentation of the base class for
     * more information.
     * @param formatter the number formatter
     */
  protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
    if (! (formatter instanceof IntegerFormatter)){
      throw new IllegalArgumentException("formatter must be an instance of IntegerFormatter!");
    }
    super.setFormatter(formatter);
  }
  
    /**
     * Gets the number formatter.
     * @return the number formatter
     */
  public IntegerFormatter getIntegerFormatter(){
    return (IntegerFormatter) getWholeNumberFormatter();
  }
  
    /**
     * Gets the number formatter factory.
     * @return the number formatter factory
     */
  public IntegerFormatterFactory getIntegerFormatterFactory(){
    return (IntegerFormatterFactory) getWholeNumberFormatterFactory();
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
  public void setIntegerFormatterFactory(IntegerFormatterFactory factory){
    setWholeNumberFormatterFactory(factory);
  }
  
      /**
     * Sets the formatter factory.
     * Must be an instance of IntegerFormatterFactory.
     * Calls the reinit method.
     * Ensures the value stays in the range defined by the minimum and maximum value of
     * the number formatter, which can be obtained by this formatter factory, by either
     * setting it to the maximum value if it is greater than the maximum value or to
     * the minimum value if it is smaller than the minimum value.
     * @param aff the number formatter factory
     */
  public void setFormatterFactory(JFormattedTextField.AbstractFormatterFactory aff) {
    if (! (aff instanceof IntegerFormatterFactory)){
      throw new IllegalArgumentException("aff must be an instance of IntegerFormatterFactory!");
    }
    super.setFormatterFactory(aff);
  }
  
}
