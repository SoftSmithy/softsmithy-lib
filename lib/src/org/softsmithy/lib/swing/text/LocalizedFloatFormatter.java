/*
 * FloatFormatter.java
 *
 * Created on 9. September 2003, 19:18
 */

package org.softsmithy.lib.swing.text;

import java.math.*;
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.math.*;


/**
 *
 * @author  puce
 */
public class LocalizedFloatFormatter extends LocalizedRealNumberFormatter {
  
  public LocalizedFloatFormatter() {
    this(JComponent.getDefaultLocale());// better than Locale.getDefault()?
  }
  
  public LocalizedFloatFormatter(Locale locale){
    this(Float.MIN_VALUE, Float.MAX_VALUE, locale);
  }
  
  public LocalizedFloatFormatter(float minFloatValue, float maxFloatValue){
    this(minFloatValue, maxFloatValue, JComponent.getDefaultLocale());// better than Locale.getDefault()?
  }
  
  public LocalizedFloatFormatter(float minFloatValue, float maxFloatValue, Locale locale){
    super(locale);
    setMinimumMinimumValue(BigDecimals.MIN_FLOAT);
    setMaximumMaximumValue(BigDecimals.MAX_FLOAT);
    setMinimumFloatValue(minFloatValue);
    setMaximumFloatValue(maxFloatValue);
  }
  
  
  public float getMinimumFloatValue(){
    return getMinimumBigDecimalValue().floatValue();
  }
  
  public void setMinimumFloatValue(float minFloatValue){
    setMinimumBigDecimalValue(new BigDecimal(minFloatValue));
  }
  
  public float getMaximumFloatValue(){
    return getMaximumBigDecimalValue().floatValue();
  }
  
  public void setMaximumFloatValue(float maxFloatValue){
    setMaximumBigDecimalValue(new BigDecimal(maxFloatValue));
  }
  
}
