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
    this(Doubles.MAX_NEGATIVE_VALUE, Double.MAX_VALUE, locale);
  }
  
  public LocalizedDoubleFormatter(double minDoubleValue, double maxDoubleValue){
    this(minDoubleValue, maxDoubleValue, JComponent.getDefaultLocale());// better than Locale.getDefault()?
  }
  
  public LocalizedDoubleFormatter(double minDoubleValue, double maxDoubleValue, Locale locale){
    super(locale);
    setMinimumMinimumValue(BigDecimals.MAX_NEGATIVE_DOUBLE);
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
