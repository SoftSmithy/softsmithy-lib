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
 *
 * @author  puce
 */
public class JIntegerField extends JWholeNumberField {
  
  private static final int DEFAULT_VALUE = 0;
  private static final int DEFAULT_MIN_VALUE = Integer.MIN_VALUE;
  private static final int DEFAULT_MAX_VALUE = Integer.MAX_VALUE;
  
  /** Creates a new instance of JIntegerField */
  public JIntegerField() {
    this(Locale.getDefault());
  }
  
  public JIntegerField(Locale locale){
    this(DEFAULT_VALUE, locale);
  }
  
  public JIntegerField(int value){
    this(value, Locale.getDefault());
  }
  
  public JIntegerField(int value, Locale locale){
    this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, locale);
  }
  
  public JIntegerField(int minValue, int maxValue){
    this(minValue, maxValue, Locale.getDefault());
  }
  
  public JIntegerField(int minValue, int maxValue, Locale locale){
    this(DEFAULT_VALUE, minValue, maxValue, locale);
  }
  
  public JIntegerField(int value, int minValue, int maxValue){
    this(value, minValue, maxValue, Locale.getDefault());
  }
  
  public JIntegerField(int value, int minValue, int maxValue, Locale locale){
    super(new IntegerFormatterFactory(new IntegerFormatter()));
    setMinimumIntValue(minValue);
    setMaximumIntValue(maxValue);
    setIntValue(value);
    setLocale(locale);
  }
  
  
  public int getIntValue(){
    return getBigIntegerValue().intValue();
  }
  
  public void setIntValue(int i){
    setBigIntegerValue(BigInteger.valueOf(i));
  }
  
  public int getMinimumIntValue(){
    return getMinimumBigIntegerValue().intValue();
  }
  
  public void setMinimumIntValue(int minIntValue){
    setMinimumBigIntegerValue(BigInteger.valueOf(minIntValue));
  }
  
  public int getMaximumIntValue(){
    return getMaximumBigIntegerValue().intValue();
  }
  
  public void setMaximumIntValue(int minIntValue){
    setMaximumBigIntegerValue(BigInteger.valueOf(minIntValue));
  }
  
  protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
    if (! (formatter instanceof IntegerFormatter)){
      throw new IllegalArgumentException("formatter must be an instance of IntegerFormatter!");
    }
    super.setFormatter(formatter);
  }
  
  public IntegerFormatter getIntegerFormatter(){
    return (IntegerFormatter) getWholeNumberFormatter();
  }
  
  public IntegerFormatterFactory getIntegerFormatterFactory(){
    return (IntegerFormatterFactory) getWholeNumberFormatterFactory();
  }
  
  public void setIntegerFormatterFactory(IntegerFormatterFactory factory){
    setWholeNumberFormatterFactory(factory);
  }
  
  public void setFormatterFactory(JFormattedTextField.AbstractFormatterFactory aff) {
    if (! (aff instanceof IntegerFormatterFactory)){
      throw new IllegalArgumentException("aff must be an instance of IntegerFormatterFactory!");
    }
    super.setFormatterFactory(aff);
  }
  
}
