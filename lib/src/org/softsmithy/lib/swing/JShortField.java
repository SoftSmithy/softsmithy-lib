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
public class JShortField extends JWholeNumberField {
  
  private static final short DEFAULT_VALUE = 0;
  private static final short DEFAULT_MIN_VALUE = Short.MIN_VALUE;
  private static final short DEFAULT_MAX_VALUE = Short.MAX_VALUE;
  
  /** Creates a new instance of JShortField */
  public JShortField() {
    this(Locale.getDefault());
  }
  
  public JShortField(Locale locale){
    this(DEFAULT_VALUE, locale);
  }
  
  public JShortField(short value){
    this(value, Locale.getDefault());
  }
  
  public JShortField(short value, Locale locale){
    this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, locale);
  }
  
  public JShortField(short minValue, short maxValue){
    this(minValue, maxValue, Locale.getDefault());
  }
  
  public JShortField(short minValue, short maxValue, Locale locale){
    this(DEFAULT_VALUE, minValue, maxValue, locale);
  }
  
  public JShortField(short value, short minValue, short maxValue){
    this(value, minValue, maxValue, Locale.getDefault());
  }
  
  public JShortField(short value, short minValue, short maxValue, Locale locale){
    super(new ShortFormatterFactory(new ShortFormatter()));
    setMinimumShortValue(minValue);
    setMaximumShortValue(maxValue);
    setShortValue(value);
    setLocale(locale);
  }
  
  
  public short getShortValue(){
    return getBigIntegerValue().shortValue();
  }
  
  public void setShortValue(short i){
    setBigIntegerValue(BigInteger.valueOf(i));
  }
  
  public short getMinimumShortValue(){
    return getMinimumBigIntegerValue().shortValue();
  }
  
  public void setMinimumShortValue(short minShortValue){
    setMinimumBigIntegerValue(BigInteger.valueOf(minShortValue));
  }
  
  public short getMaximumShortValue(){
    return getMaximumBigIntegerValue().shortValue();
  }
  
  public void setMaximumShortValue(short minShortValue){
    setMaximumBigIntegerValue(BigInteger.valueOf(minShortValue));
  }
  
  protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
    if (! (formatter instanceof ShortFormatter)){
      throw new IllegalArgumentException("formatter must be an instance of ShortFormatter!");
    }
    super.setFormatter(formatter);
  }
  
  public ShortFormatter getShortFormatter(){
    return (ShortFormatter) getWholeNumberFormatter();
  }
  
  public ShortFormatterFactory getShortFormatterFactory(){
    return (ShortFormatterFactory) getWholeNumberFormatterFactory();
  }
  
  public void setShortFormatterFactory(ShortFormatterFactory factory){
    setWholeNumberFormatterFactory(factory);
  }
  
  public void setFormatterFactory(JFormattedTextField.AbstractFormatterFactory aff) {
    if (! (aff instanceof ShortFormatterFactory)){
      throw new IllegalArgumentException("aff must be an instance of ShortFormatterFactory!");
    }
    super.setFormatterFactory(aff);
  }
  
}
