/*
 * JIntegerField.java
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
 *
 * @author  puce
 */
public class JFloatField extends JRealNumberField {
  
  private static final float DEFAULT_VALUE = 0;
  private static final float DEFAULT_MIN_VALUE = Float.MIN_VALUE;
  private static final float DEFAULT_MAX_VALUE = Float.MAX_VALUE;
  
  public JFloatField(){
    this(DEFAULT_VALUE);
  }
  
  public JFloatField(float value){
    this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
  }
  
  public JFloatField(float minValue, float maxValue){
    this(DEFAULT_VALUE, minValue, maxValue);
  }
  
  public JFloatField(float value, float minValue, float maxValue){
    this(new FloatFormatterFactory(new FloatFormatter()));
    setMinimumFloatValue(minValue);
    setMaximumFloatValue(maxValue);
    setFloatValue(value);
  }
  
  public JFloatField(FloatFormatterFactory factory){
    super(factory);
    setFloatValue(DEFAULT_VALUE);
  }
  
  
  public float getFloatValue(){
    return getBigDecimalValue().floatValue();
  }
  
  public void setFloatValue(float value){
    setBigDecimalValue(new BigDecimal(value));
  }
  
  
  public float getMinimumFloatValue(){
    return getMinimumBigDecimalValue().floatValue();
  }
  
  public void setMinimumFloatValue(float minValue){
    setMinimumBigDecimalValue(new BigDecimal(minValue));
  }
  
  public float getMaximumFloatValue(){
    return getMaximumBigDecimalValue().floatValue();
  }
  
  public void setMaximumFloatValue(float minValue){
    setMaximumBigDecimalValue(new BigDecimal(minValue));
  }
  
  protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
    if (! (formatter instanceof FloatFormatter)){
      throw new IllegalArgumentException("formatter must be an instance of FloatFormatter!");
    }
    super.setFormatter(formatter);
  }
  
  public FloatFormatter getFloatFormatter(){
    return (FloatFormatter) getRealNumberFormatter();
  }
  
  public FloatFormatterFactory getFloatFormatterFactory(){
    return (FloatFormatterFactory) getRealNumberFormatterFactory();
  }
  
  public void setFloatFormatterFactory(FloatFormatterFactory factory){
    setRealNumberFormatterFactory(factory);
  }
  
  public void setFormatterFactory(JFormattedTextField.AbstractFormatterFactory aff) {
    if (! (aff instanceof FloatFormatterFactory)){
      throw new IllegalArgumentException("aff must be an instance of FloatFormatterFactory!");
    }
    super.setFormatterFactory(aff);
  }
  
  
  
}
