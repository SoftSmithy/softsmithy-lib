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
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.swing.text.*;

/**
 * A byte field.
 * @author puce
 */
public class JByteField extends JWholeNumberField {
  
    /**
     * The default value.
     */
  private static final byte DEFAULT_VALUE = 0;
    /**
     * The default minimum value.
     */
  private static final byte DEFAULT_MIN_VALUE = Byte.MIN_VALUE;
    /**
     * The default maximum value.
     */
  private static final byte DEFAULT_MAX_VALUE = Byte.MAX_VALUE;
  
  /**
     * Creates a new instance of this class.
     */
  public JByteField() {
    this(Locale.getDefault());
  }
  
    /**
     * Creates a new instance of this class.
     * @param locale the locale
     */
  public JByteField(Locale locale){
    this(DEFAULT_VALUE, locale);
  }
  
    /**
     * Creates a new instance of this class.
     * @param value the value
     */
  public JByteField(byte value){
    this(value, Locale.getDefault());
  }
  
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param locale the locale
     */
  public JByteField(byte value, Locale locale){
    this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, locale);
  }
  
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
  public JByteField(byte minValue, byte maxValue){
    this(minValue, maxValue, Locale.getDefault());
  }
  
    /**
     * Creates a new instance of this class.
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
  public JByteField(byte minValue, byte maxValue, Locale locale){
    this(DEFAULT_VALUE, minValue, maxValue, locale);
  }
  
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     */
  public JByteField(byte value, byte minValue, byte maxValue){
    this(value, minValue, maxValue, Locale.getDefault());
  }
  
    /**
     * Creates a new instance of this class.
     * @param value the value
     * @param minValue the minimum value
     * @param maxValue the maximum value
     * @param locale the locale
     */
  public JByteField(byte value, byte minValue, byte maxValue, Locale locale){
    super(new ByteFormatterFactory(new ByteFormatter()));
    setMinimumByteValue(minValue);
    setMaximumByteValue(maxValue);
    setByteValue(value);
    setLocale(locale);
  }
  
  
    /**
     * Gets the value.
     * @return the value.
     */
  public byte getByteValue(){
    return getBigIntegerValue().byteValue();
  }
  
    /**
     * Set the value.
     * @param i the value
     */
  public void setByteValue(byte i){
    setBigIntegerValue(BigInteger.valueOf(i));
  }
  
    /**
     * Gets the minimum value.
     * @return the minimum value
     */
  public byte getMinimumByteValue(){
    return getMinimumBigIntegerValue().byteValue();
  }
  
    /**
     * Sets the minimum value.
     * @param minByteValue the minimum value
     */
  public void setMinimumByteValue(byte minByteValue){
    setMinimumBigIntegerValue(BigInteger.valueOf(minByteValue));
  }
  
    /**
     * Gets the maximum value.
     * @return the maximum value
     */
  public byte getMaximumByteValue(){
    return getMaximumBigIntegerValue().byteValue();
  }
  
    /**
     * Sets the maximum value.
     * @param maxByteValue the maximum value
     */
  public void setMaximumByteValue(byte maxByteValue){
    setMaximumBigIntegerValue(BigInteger.valueOf(maxByteValue));
  }
  
    /**
     * Sets the formatter.
     * Must be an instance of ByteFormatter.
     * You should not normally invoke this. See the documentation of the base class for
     * more information.
     * @param formatter the number formatter
     */
  protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
    if (! (formatter instanceof ByteFormatter)){
      throw new IllegalArgumentException("formatter must be an instance of ByteFormatter!");
    }
    super.setFormatter(formatter);
  }
  
    /**
     * Gets the number formatter.
     * @return the number formatter
     */
  public ByteFormatter getByteFormatter(){
    return (ByteFormatter) getWholeNumberFormatter();
  }
  
    /**
     * Gets the number formatter factory.
     * @return the number formatter factory
     */
  public ByteFormatterFactory getByteFormatterFactory(){
    return (ByteFormatterFactory) getWholeNumberFormatterFactory();
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
  public void setByteFormatterFactory(ByteFormatterFactory factory){
    setWholeNumberFormatterFactory(factory);
  }
  
        /**
     * Sets the formatter factory.
     * Must be an instance of ByteFormatterFactory.
     * Calls the reinit method.
     * Ensures the value stays in the range defined by the minimum and maximum value of
     * the number formatter, which can be obtained by this formatter factory, by either
     * setting it to the maximum value if it is greater than the maximum value or to
     * the minimum value if it is smaller than the minimum value.
     * @param aff the number formatter factory
     */
  public void setFormatterFactory(JFormattedTextField.AbstractFormatterFactory aff) {
    if (! (aff instanceof ByteFormatterFactory)){
      throw new IllegalArgumentException("aff must be an instance of ByteFormatterFactory!");
    }
    super.setFormatterFactory(aff);
  }
  
}
