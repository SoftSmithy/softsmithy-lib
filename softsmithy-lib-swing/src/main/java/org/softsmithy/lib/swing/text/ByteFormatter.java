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

package org.softsmithy.lib.swing.text;

import java.math.*;
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.math.*;


/**
 *
 * @author  puce
 */
public class ByteFormatter extends WholeNumberFormatter {
  
  /** Creates a new instance of IntegerFormatter */
  public ByteFormatter() {
    this(JComponent.getDefaultLocale());// better than Locale.getDefault()?
  }
  
  public ByteFormatter(Locale locale){
    this(Byte.MIN_VALUE, Byte.MAX_VALUE, locale);
  }
  
  public ByteFormatter(byte minByteValue, byte maxByteValue){
    this(minByteValue, maxByteValue, JComponent.getDefaultLocale());// better than Locale.getDefault()?
  }
  
  public ByteFormatter(byte minByteValue, byte maxByteValue, Locale locale){
    super(locale);
    setMinimumMinimumValue(BigIntegers.MIN_BYTE);
    setMaximumMaximumValue(BigIntegers.MAX_BYTE);
    setMinimumByteValue(minByteValue);
    setMaximumByteValue(maxByteValue);
    //System.out.println("min: "+ getMinimum());
    //System.out.println("max: "+getMaximum());
  }
  
  public byte getMinimumByteValue(){
    return getMinimumNumberValue().byteValue();
  }
  
  public void setMinimumByteValue(byte minByteValue){
    setMinimumNumberValue(BigInteger.valueOf(minByteValue));
  }
  
  public byte getMaximumByteValue(){
    return getMaximumNumberValue().byteValue();
  }
  
  public void setMaximumByteValue(byte maxByteValue){
    setMaximumNumberValue(BigInteger.valueOf(maxByteValue));
  }
  
}
