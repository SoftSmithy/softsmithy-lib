/*
 * FloatFormatter.java
 *
 * Created on 9. September 2003, 19:18
 */

package org.softsmithy.lib.swing.text;

import java.math.*;
import java.text.*;
import java.util.*;
import org.softsmithy.lib.math.*;

/**
 *
 * @author  puce
 */
public class FloatFormatter extends RealNumberFormatter {
  
  /** Creates a new instance of FloatFormatter */
  public FloatFormatter() {
    init(Float.MIN_VALUE, Float.MAX_VALUE);
  }
  
  public FloatFormatter(NumberFormat format){
    super(format);
    init(Float.MIN_VALUE, Float.MAX_VALUE);
  }
  
  public FloatFormatter(float minFloatValue, float maxFloatValue){
    init(minFloatValue, maxFloatValue);
  }
  
  public FloatFormatter(NumberFormat format, float minFloatValue, float maxFloatValue){
    super(format);
    init(minFloatValue, maxFloatValue);
  }
  
  private void init(float minFloatValue, float maxFloatValue){
    setMinimumMinimumValue(BigDecimals.MIN_FLOAT);
    setMaximumMaximumValue(BigDecimals.MAX_FLOAT);
    setMinimumFloatValue(minFloatValue);
    setMaximumFloatValue(maxFloatValue);
    System.out.println("min: "+ getMinimum());
    System.out.println("max: "+getMaximum());
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
