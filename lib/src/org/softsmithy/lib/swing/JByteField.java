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
 *
 * @author  puce
 */
public class JByteField extends JWholeNumberField {
  
  private static final byte DEFAULT_VALUE = 0;
  private static final byte DEFAULT_MIN_VALUE = Byte.MIN_VALUE;
  private static final byte DEFAULT_MAX_VALUE = Byte.MAX_VALUE;
  
  /** Creates a new instance of JByteField */
  public JByteField() {
    this(Locale.getDefault());
  }
  
  public JByteField(Locale locale){
    this(DEFAULT_VALUE, locale);
  }
  
  public JByteField(byte value){
    this(value, Locale.getDefault());
  }
  
  public JByteField(byte value, Locale locale){
    this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, locale);
  }
  
  public JByteField(byte minValue, byte maxValue){
    this(minValue, maxValue, Locale.getDefault());
  }
  
  public JByteField(byte minValue, byte maxValue, Locale locale){
    this(DEFAULT_VALUE, minValue, maxValue, locale);
  }
  
  public JByteField(byte value, byte minValue, byte maxValue){
    this(value, minValue, maxValue, Locale.getDefault());
  }
  
  public JByteField(byte value, byte minValue, byte maxValue, Locale locale){
    super(new ByteFormatterFactory(new ByteFormatter()));
    setMinimumByteValue(minValue);
    setMaximumByteValue(maxValue);
    setByteValue(value);
    setLocale(locale);
  }
  
  
  public byte getByteValue(){
    return getBigIntegerValue().byteValue();
  }
  
  public void setByteValue(byte i){
    setBigIntegerValue(BigInteger.valueOf(i));
  }
  
  public byte getMinimumByteValue(){
    return getMinimumBigIntegerValue().byteValue();
  }
  
  public void setMinimumByteValue(byte minByteValue){
    setMinimumBigIntegerValue(BigInteger.valueOf(minByteValue));
  }
  
  public byte getMaximumByteValue(){
    return getMaximumBigIntegerValue().byteValue();
  }
  
  public void setMaximumByteValue(byte minByteValue){
    setMaximumBigIntegerValue(BigInteger.valueOf(minByteValue));
  }
  
  protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
    if (! (formatter instanceof ByteFormatter)){
      throw new IllegalArgumentException("formatter must be an instance of ByteFormatter!");
    }
    super.setFormatter(formatter);
  }
  
  public ByteFormatter getByteFormatter(){
    return (ByteFormatter) getWholeNumberFormatter();
  }
  
  public ByteFormatterFactory getByteFormatterFactory(){
    return (ByteFormatterFactory) getWholeNumberFormatterFactory();
  }
  
  public void setByteFormatterFactory(ByteFormatterFactory factory){
    setWholeNumberFormatterFactory(factory);
  }
  
  public void setFormatterFactory(JFormattedTextField.AbstractFormatterFactory aff) {
    if (! (aff instanceof ByteFormatterFactory)){
      throw new IllegalArgumentException("aff must be an instance of ByteFormatterFactory!");
    }
    super.setFormatterFactory(aff);
  }
  
}
