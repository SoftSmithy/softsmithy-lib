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
 * IntegerFormatter.java
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
public class IntegerFormatter extends WholeNumberFormatter {
  
  /** Creates a new instance of IntegerFormatter */
  public IntegerFormatter() {
    this(JComponent.getDefaultLocale());// better than Locale.getDefault()?
  }
  
  public IntegerFormatter(Locale locale){
    this(Integer.MIN_VALUE, Integer.MAX_VALUE, locale);
  }
  
  public IntegerFormatter(int minIntValue, int maxIntValue){
    this(minIntValue, maxIntValue, JComponent.getDefaultLocale());// better than Locale.getDefault()?
  }
  
  public IntegerFormatter(int minIntValue, int maxIntValue, Locale locale){
    super(locale);
    setMinimumMinimumValue(BigIntegers.MIN_INTEGER);
    setMaximumMaximumValue(BigIntegers.MAX_INTEGER);
    setMinimumIntValue(minIntValue);
    setMaximumIntValue(maxIntValue);
    //System.out.println("min: "+ getMinimum());
    //System.out.println("max: "+getMaximum());
  }
  
  public int getMinimumIntValue(){
    return getMinimumNumberValue().intValue();
  }
  
  public void setMinimumIntValue(int minIntValue){
    setMinimumNumberValue(BigInteger.valueOf(minIntValue));
  }
  
  public int getMaximumIntValue(){
    return getMaximumNumberValue().intValue();
  }
  
  public void setMaximumIntValue(int maxIntValue){
    setMaximumNumberValue(BigInteger.valueOf(maxIntValue));
  }
  
}
