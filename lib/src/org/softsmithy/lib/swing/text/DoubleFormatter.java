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

/*
 * DoubleFormatter.java
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
public class DoubleFormatter extends RealNumberFormatter {
  
  /** Creates a new instance of DoubleFormatter */
  public DoubleFormatter() {
    init(Double.MIN_VALUE, Double.MAX_VALUE);
  }
  
  public DoubleFormatter(NumberFormat format){
    super(format);
    init(Double.MIN_VALUE, Double.MAX_VALUE);
  }
  
  public DoubleFormatter(double minDoubleValue, double maxDoubleValue){
    init(minDoubleValue, maxDoubleValue);
  }
  
  public DoubleFormatter(NumberFormat format, double minDoubleValue, double maxDoubleValue){
    super(format);
    init(minDoubleValue, maxDoubleValue);
  }
  
  private void init(double minDoubleValue, double maxDoubleValue){
    setMinimumMinimumValue(BigDecimals.MIN_DOUBLE);
    setMaximumMaximumValue(BigDecimals.MAX_DOUBLE);
    setMinimumDoubleValue(minDoubleValue);
    setMaximumDoubleValue(maxDoubleValue);
    System.out.println("min: "+ getMinimum());
    System.out.println("max: "+getMaximum());
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
