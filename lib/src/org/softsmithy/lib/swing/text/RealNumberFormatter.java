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
 * IntegerFormatter.java
 *
 * Created on 9. September 2003, 19:18
 */

package org.softsmithy.lib.swing.text;

import java.math.*;
import java.text.*;

/**
 *
 * @author  puce
 */
public class RealNumberFormatter extends AbstractXNumberFormatter {
  
  
  /** Creates a new instance of IntegerFormatter */
  public RealNumberFormatter() {
    init();
  }
  
  public RealNumberFormatter(NumberFormat format){
    super(format);
    init();
  }
  
  public RealNumberFormatter(BigDecimal minIntValue, BigDecimal maxIntValue){
    super(minIntValue, maxIntValue);
    init();
  }
  public RealNumberFormatter(NumberFormat format, BigDecimal minIntValue, BigDecimal maxIntValue){
    super(format, minIntValue, maxIntValue);
    init();
  }
  
  private void init(){
    // use BigDecimal to recognize values out
    // of range if the range is (Integer.MIN_VALUE, Integer.MAX_VALUE)
    super.setValueClass(BigDecimal.class);
  }
  
  
  @Override
  public void setMaximum(Comparable max) {
    if (max != null && ! (max instanceof BigDecimal)){
      throw new IllegalArgumentException("max must be an instance of BigDecimal or null");
    }
    super.setMaximum(max);
  }
  
  @Override
  public void setMinimum(Comparable minimum) {
    if (minimum != null && ! (minimum instanceof BigDecimal)){
      throw new IllegalArgumentException("minimum must be an instance of BigDecimal or null");
    }
    super.setMinimum(minimum);
  }
  
  @Override
  public void setValueClass(Class valueClass) {
    throw new UnsupportedOperationException("This operation is not supported!"); // always use BigDecimal!
  }
  
  
  protected Number stringToNumber(String text) throws NumberFormatException{
    return new BigDecimal(text);
  }
  
  
  
  /** Getter for property maximumMaximumValue.
   * @return Value of property maximumMaximumValue.
   *
   */
  protected BigDecimal getMaximumMaximumBigDecimalValue() {
    return (BigDecimal) getMaximumMaximumValue();
  }
  
  /** Setter for property maximumMaximumValue.
   * @param maximumMaximumValue New value of property maximumMaximumValue.
   *
   */
  protected void setMaximumMaximumBigDecimalValue(BigDecimal maximumMaximumValue) {
    setMaximumMaximumValue(maximumMaximumValue);
  }
  
  /** Getter for property minimumMinimumValue.
   * @return Value of property minimumMinimumValue.
   *
   */
  protected BigDecimal getMinimumMinimumBigDecimalValue() {
    return (BigDecimal) getMinimumMinimumValue();
  }
  
  /** Setter for property minimumMinimumValue.
   * @param minimumMinimumValue New value of property minimumMinimumValue.
   *
   */
  protected void setMinimumMinimumBigDecimalValue(BigDecimal minimumMinimumValue) {
    setMinimumMinimumValue(minimumMinimumValue);
  }
  
  /** Getter for property maximumBigDecimalValue.
   * @return Value of property maximumBigDecimalValue.
   *
   */
  public BigDecimal getMaximumBigDecimalValue() {
    return (BigDecimal) getMaximum();
  }
  
  /** Setter for property maximumBigDecimalValue.
   * @param maximumBigDecimalValue New value of property maximumBigDecimalValue.
   *
   */
  public void setMaximumBigDecimalValue(BigDecimal maximumBigDecimalValue) {
    setMaximum(maximumBigDecimalValue);
  }
  
  /** Getter for property minimumBigDecimalValue.
   * @return Value of property minimumBigDecimalValue.
   *
   */
  public BigDecimal getMinimumBigDecimalValue() {
    return (BigDecimal) getMinimum();
  }
  
  /** Setter for property minimumBigDecimalValue.
   * @param minimumBigDecimalValue New value of property minimumBigDecimalValue.
   *
   */
  public void setMinimumBigDecimalValue(BigDecimal minimumBigDecimalValue) {
    setMinimum(minimumBigDecimalValue);
  }
  
  protected void setMaximumMaximumValue(Number maximumMaximumValue) {
    if (maximumMaximumValue != null && ! (maximumMaximumValue instanceof BigDecimal)){
      throw new IllegalArgumentException("maximumMaximumValue must be an instance of BigDecimal or null");
    }
    super.setMaximumMaximumValue(maximumMaximumValue);
  }
  
  protected void setMinimumMinimumValue(Number minimumMinimumValue) {
    if (minimumMinimumValue != null && ! (minimumMinimumValue instanceof BigDecimal)){
      throw new IllegalArgumentException("minimumMinimumValue must be an instance of BigDecimal or null");
    }
    super.setMinimumMinimumValue(minimumMinimumValue);
  }
  
}
