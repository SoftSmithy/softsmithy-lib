
package org.softsmithy.lib.swing;

import java.math.*;
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.swing.text.*;

/**
 *
 * @author  puce
 */
public class JLongField extends JWholeNumberField {
  
  private static final long DEFAULT_VALUE = 0;
  private static final long DEFAULT_MIN_VALUE = Long.MIN_VALUE;
  private static final long DEFAULT_MAX_VALUE = Long.MAX_VALUE;
  
  /** Creates a new instance of JLongField */
  public JLongField() {
    this(Locale.getDefault());
  }
  
  public JLongField(Locale locale){
    this(DEFAULT_VALUE, locale);
  }
  
  public JLongField(long value){
    this(value, Locale.getDefault());
  }
  
  public JLongField(long value, Locale locale){
    this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, locale);
  }
  
  public JLongField(long minValue, long maxValue){
    this(minValue, maxValue, Locale.getDefault());
  }
  
  public JLongField(long minValue, long maxValue, Locale locale){
    this(DEFAULT_VALUE, minValue, maxValue, locale);
  }
  
  public JLongField(long value, long minValue, long maxValue){
    this(value, minValue, maxValue, Locale.getDefault());
  }
  
  public JLongField(long value, long minValue, long maxValue, Locale locale){
    super(new LongFormatterFactory(new LongFormatter()));
    setMinimumLongValue(minValue);
    setMaximumLongValue(maxValue);
    setLongValue(value);
    setLocale(locale);
  }
  
  
  public long getLongValue(){
    return getBigIntegerValue().longValue();
  }
  
  public void setLongValue(long i){
    setBigIntegerValue(BigInteger.valueOf(i));
  }
  
  public long getMinimumLongValue(){
    return getMinimumBigIntegerValue().longValue();
  }
  
  public void setMinimumLongValue(long minLongValue){
    setMinimumBigIntegerValue(BigInteger.valueOf(minLongValue));
  }
  
  public long getMaximumLongValue(){
    return getMaximumBigIntegerValue().longValue();
  }
  
  public void setMaximumLongValue(long minLongValue){
    setMaximumBigIntegerValue(BigInteger.valueOf(minLongValue));
  }
  
  protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
    if (! (formatter instanceof LongFormatter)){
      throw new IllegalArgumentException("formatter must be an instance of LongFormatter!");
    }
    super.setFormatter(formatter);
  }
  
  public LongFormatter getLongFormatter(){
    return (LongFormatter) getWholeNumberFormatter();
  }
  
  public LongFormatterFactory getLongFormatterFactory(){
    return (LongFormatterFactory) getWholeNumberFormatterFactory();
  }
  
  public void setLongFormatterFactory(LongFormatterFactory factory){
    setWholeNumberFormatterFactory(factory);
  }
  
  public void setFormatterFactory(JFormattedTextField.AbstractFormatterFactory aff) {
    if (! (aff instanceof LongFormatterFactory)){
      throw new IllegalArgumentException("aff must be an instance of LongFormatterFactory!");
    }
    super.setFormatterFactory(aff);
  }
  
}
