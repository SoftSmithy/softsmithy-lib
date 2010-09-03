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
import java.text.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class WholeNumberFormatter extends AbstractXNumberFormatter {
  
  /** Holds value of property locale. */
  private Locale locale;
  
  /** Creates a new instance of IntegerFormatter */
  public WholeNumberFormatter() {
    this(JComponent.getDefaultLocale());// better than Locale.getDefault()?
  }
  
  public WholeNumberFormatter(Locale locale){
    this(null, null, locale);
  }
  
  public WholeNumberFormatter(BigInteger minIntValue, BigInteger maxIntValue){
    this(minIntValue, maxIntValue, JComponent.getDefaultLocale());// better than Locale.getDefault()?
  }
  
  public WholeNumberFormatter(BigInteger minIntValue, BigInteger maxIntValue, Locale locale){
    super(minIntValue, maxIntValue);
    setLocale(locale);
    // use BigInteger to recognize values out
    // of range if the range is (Integer.MIN_VALUE, Integer.MAX_VALUE)
    super.setValueClass(BigInteger.class);
  }
  
  /** Getter for property locale.
   * @return Value of property locale.
   *
   */
  public Locale getLocale() {
    return this.locale;
  }
  
  /** Setter for property locale.
   * @param locale New value of property locale.
   *
   */
  public void setLocale(Locale locale) {
    this.locale = locale;
    setFormat(NumberFormat.getIntegerInstance(locale));
  }
  
  
  @Override
  public void setMaximum(Comparable max) {
    if (max != null && ! (max instanceof BigInteger)){
      throw new IllegalArgumentException("max must be an instance of BigInteger or null");
    }
    super.setMaximum(max);
  }
  
  @Override
  public void setMinimum(Comparable minimum) {
    if (minimum != null && ! (minimum instanceof BigInteger)){
      throw new IllegalArgumentException("minimum must be an instance of BigInteger or null");
    }
    super.setMinimum(minimum);
  }
  
  @Override
  public void setValueClass(Class valueClass) {
    throw new UnsupportedOperationException("This operation is not supported!"); // always use BigInteger!
  }
  
  /** Getter for property maximumMaximumValue.
   * @return Value of property maximumMaximumValue.
   *
   */
  protected BigInteger getMaximumMaximumBigIntegerValue() {
    return (BigInteger) getMaximumMaximumValue();
  }
  
  /** Setter for property maximumMaximumValue.
   * @param maximumMaximumValue New value of property maximumMaximumValue.
   *
   */
  protected void setMaximumMaximumBigIntegerValue(BigInteger maximumMaximumValue) {
    setMaximumMaximumValue(maximumMaximumValue);
  }
  
  /** Getter for property minimumMinimumValue.
   * @return Value of property minimumMinimumValue.
   *
   */
  protected BigInteger getMinimumMinimumBigIntegerValue() {
    return (BigInteger) getMinimumMinimumValue();
  }
  
  /** Setter for property minimumMinimumValue.
   * @param minimumMinimumValue New value of property minimumMinimumValue.
   *
   */
  protected void setMinimumMinimumBigIntegerValue(BigInteger minimumMinimumValue) {
    setMinimumMinimumValue(minimumMinimumValue);
  }
  
  /** Getter for property maximumBigIntegerValue.
   * @return Value of property maximumBigIntegerValue.
   *
   */
  public BigInteger getMaximumBigIntegerValue() {
    return (BigInteger) getMaximum();
  }
  
  /** Setter for property maximumBigIntegerValue.
   * @param maximumBigIntegerValue New value of property maximumBigIntegerValue.
   *
   */
  public void setMaximumBigIntegerValue(BigInteger maximumBigIntegerValue) {
    setMaximum(maximumBigIntegerValue);
  }
  
  /** Getter for property minimumBigIntegerValue.
   * @return Value of property minimumBigIntegerValue.
   *
   */
  public BigInteger getMinimumBigIntegerValue() {
    return (BigInteger) getMinimum();
  }
  
  /** Setter for property minimumBigIntegerValue.
   * @param minimumBigIntegerValue New value of property minimumBigIntegerValue.
   *
   */
  public void setMinimumBigIntegerValue(BigInteger minimumBigIntegerValue) {
    setMinimum(minimumBigIntegerValue);
  }
  
  protected Number stringToNumber(String text) throws NumberFormatException {
    return new BigInteger(text);
  }
  
}
