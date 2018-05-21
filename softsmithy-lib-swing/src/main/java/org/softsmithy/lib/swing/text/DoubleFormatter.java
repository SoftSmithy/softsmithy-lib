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
 * DoubleFormatter.java
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
public class DoubleFormatter extends RealNumberFormatter {
  
  /** Creates a new instance of DoubleFormatter */
  public DoubleFormatter() {
    init(Doubles.MAX_NEGATIVE_VALUE, Double.MAX_VALUE);
  }
  
  public DoubleFormatter(NumberFormat format){
    super(format);
    init(Doubles.MAX_NEGATIVE_VALUE, Double.MAX_VALUE);
  }
  
  public DoubleFormatter(double minDoubleValue, double maxDoubleValue){
    init(minDoubleValue, maxDoubleValue);
  }
  
  public DoubleFormatter(NumberFormat format, double minDoubleValue, double maxDoubleValue){
    super(format);
    init(minDoubleValue, maxDoubleValue);
  }
  
  private void init(double minDoubleValue, double maxDoubleValue){
    setMinimumMinimumValue(BigDecimals.MAX_NEGATIVE_DOUBLE);
    setMaximumMaximumValue(BigDecimals.MAX_DOUBLE);
    setMinimumDoubleValue(minDoubleValue);
    setMaximumDoubleValue(maxDoubleValue);
    //System.out.println("min: "+ getMinimum());
    //System.out.println("max: "+getMaximum());
  }
  
  public double getMinimumDoubleValue(){
    return getMinimumNumberValue().doubleValue();
  }
  
  public void setMinimumDoubleValue(double minDoubleValue){
    setMinimumNumberValue(new BigDecimal(minDoubleValue));
  }
  
  public double getMaximumDoubleValue(){
    return getMaximumNumberValue().doubleValue();
  }
  
  public void setMaximumDoubleValue(double maxDoubleValue){
    setMaximumNumberValue(new BigDecimal(maxDoubleValue));
  }
  
}
