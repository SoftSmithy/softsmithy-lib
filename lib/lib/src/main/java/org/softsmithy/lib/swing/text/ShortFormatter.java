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
public class ShortFormatter extends WholeNumberFormatter {
  
  /** Creates a new instance of ShortFormatter */
  public ShortFormatter() {
    this(JComponent.getDefaultLocale());// better than Locale.getDefault()?
  }
  
  public ShortFormatter(Locale locale){
    this(Short.MIN_VALUE, Short.MAX_VALUE, locale);
  }
  
  public ShortFormatter(short minShortValue, short maxShortValue){
    this(minShortValue, maxShortValue, JComponent.getDefaultLocale());// better than Locale.getDefault()?
  }
  
  public ShortFormatter(short minShortValue, short maxShortValue, Locale locale){
    super(locale);
    setMinimumMinimumValue(BigIntegers.MIN_SHORT);
    setMaximumMaximumValue(BigIntegers.MAX_SHORT);
    setMinimumShortValue(minShortValue);
    setMaximumShortValue(maxShortValue);
    //System.out.println("min: "+ getMinimum());
    //System.out.println("max: "+getMaximum());
  }
  
  public short getMinimumShortValue(){
    return getMinimumBigIntegerValue().shortValue();
  }
  
  public void setMinimumShortValue(short minShortValue){
    setMinimumBigIntegerValue(BigInteger.valueOf(minShortValue));
  }
  
  public short getMaximumShortValue(){
    return getMaximumBigIntegerValue().shortValue();
  }
  
  public void setMaximumShortValue(short maxShortValue){
    setMaximumBigIntegerValue(BigInteger.valueOf(maxShortValue));
  }
  
}
