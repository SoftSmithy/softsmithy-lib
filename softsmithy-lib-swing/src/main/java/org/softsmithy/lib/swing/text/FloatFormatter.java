/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

/*
 * FloatFormatter.java
 *
 * Created on 9. September 2003, 19:18
 */

package org.softsmithy.lib.swing.text;

import java.math.*;
import java.text.*;
import org.softsmithy.lib.math.*;

/**
 *
 * @author  puce
 */
public class FloatFormatter extends RealNumberFormatter {
  
  /** Creates a new instance of FloatFormatter */
  public FloatFormatter() {
    init(Floats.MAX_NEGATIVE_VALUE, Float.MAX_VALUE);
  }
  
  public FloatFormatter(NumberFormat format){
    super(format);
    init(Floats.MAX_NEGATIVE_VALUE, Float.MAX_VALUE);
  }
  
  public FloatFormatter(float minFloatValue, float maxFloatValue){
    init(minFloatValue, maxFloatValue);
  }
  
  public FloatFormatter(NumberFormat format, float minFloatValue, float maxFloatValue){
    super(format);
    init(minFloatValue, maxFloatValue);
  }
  
  private void init(float minFloatValue, float maxFloatValue){
    setMinimumMinimumValue(BigDecimals.MAX_NEGATIVE_FLOAT);
    setMaximumMaximumValue(BigDecimals.MAX_FLOAT);
    setMinimumFloatValue(minFloatValue);
    setMaximumFloatValue(maxFloatValue);
    //System.out.println("min: "+ getMinimum());
    //System.out.println("max: "+getMaximum());
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
