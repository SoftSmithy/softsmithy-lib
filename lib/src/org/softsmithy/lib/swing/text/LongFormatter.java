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

package org.softsmithy.lib.swing.text;

import java.math.*;
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.math.*;


/**
 *
 * @author  puce
 */
public class LongFormatter extends WholeNumberFormatter {
  
  /** Creates a new instance of IntegerFormatter */
  public LongFormatter() {
    this(JComponent.getDefaultLocale());// better than Locale.getDefault()?
  }
  
  public LongFormatter(Locale locale){
    this(Long.MIN_VALUE, Long.MAX_VALUE, locale);
  }
  
  public LongFormatter(long minLongValue, long maxLongValue){
    this(minLongValue, maxLongValue, JComponent.getDefaultLocale());// better than Locale.getDefault()?
  }
  
  public LongFormatter(long minLongValue, long maxLongValue, Locale locale){
    super(locale);
    setMinimumMinimumValue(BigIntegers.MIN_LONG);
    setMaximumMaximumValue(BigIntegers.MAX_LONG);
    setMinimumLongValue(minLongValue);
    setMaximumLongValue(maxLongValue);
    //System.out.println("min: "+ getMinimum());
    //System.out.println("max: "+getMaximum());
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
  
  public void setMaximumLongValue(long maxLongValue){
    setMaximumBigIntegerValue(BigInteger.valueOf(maxLongValue));
  }
  
}
