/*
 * DoubleFormatter.java
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
public class LocalizedDoubleFormatter extends LocalizedRealNumberFormatter {
  
  public LocalizedDoubleFormatter() {
    this(JComponent.getDefaultLocale());// better than Locale.getDefault()?
  }
  
  public LocalizedDoubleFormatter(Locale locale){
    this(Double.MIN_VALUE, Double.MAX_VALUE, locale);
  }
  
  public LocalizedDoubleFormatter(double minDoubleValue, double maxDoubleValue){
    this(minDoubleValue, maxDoubleValue, JComponent.getDefaultLocale());// better than Locale.getDefault()?
  }
  
  public LocalizedDoubleFormatter(double minDoubleValue, double maxDoubleValue, Locale locale){
    super(locale);
    setMinimumMinimumValue(BigDecimals.MIN_DOUBLE);
    setMaximumMaximumValue(BigDecimals.MAX_DOUBLE);
    setMinimumDoubleValue(minDoubleValue);
    setMaximumDoubleValue(maxDoubleValue);
  }
  
  
  public double getMinimumDoubleValue(){
    return getMinimumBigDecimalValue().doubleValue();
  }
  
  public void setMinimumDoubleValue(double minDoubleValue){
    setMinimumBigDecimalValue(new BigDecimal(minDoubleValue));
  }
  
  public double getMaximumDoubleValue(){
    return getMaximumBigDecimalValue().doubleValue();
  }
  
  public void setMaximumDoubleValue(double maxDoubleValue){
    setMaximumBigDecimalValue(new BigDecimal(maxDoubleValue));
  }
  
}
