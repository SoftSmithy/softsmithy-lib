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
    this(Floats.MAX_NEGATIVE_VALUE, Float.MAX_VALUE, locale);
  }
  
  public LocalizedFloatFormatter(float minFloatValue, float maxFloatValue){
    this(minFloatValue, maxFloatValue, JComponent.getDefaultLocale());// better than Locale.getDefault()?
  }
  
  public LocalizedFloatFormatter(float minFloatValue, float maxFloatValue, Locale locale){
    super(locale);
    setMinimumMinimumValue(BigDecimals.MAX_NEGATIVE_FLOAT);
    setMaximumMaximumValue(BigDecimals.MAX_FLOAT);
    setMinimumFloatValue(minFloatValue);
    setMaximumFloatValue(maxFloatValue);
  }
  
  
  public float getMinimumFloatValue(){
    return getMinimumNumberValue().floatValue();
  }
  
  public void setMinimumFloatValue(float minFloatValue){
    setMinimumNumberValue(new BigDecimal(minFloatValue));
  }
  
  public float getMaximumFloatValue(){
    return getMaximumNumberValue().floatValue();
  }
  
  public void setMaximumFloatValue(float maxFloatValue){
    setMaximumNumberValue(new BigDecimal(maxFloatValue));
  }
  
}
