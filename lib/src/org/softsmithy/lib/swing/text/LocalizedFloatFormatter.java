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
