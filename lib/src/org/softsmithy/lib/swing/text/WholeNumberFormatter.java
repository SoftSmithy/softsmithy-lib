/*
 * IntegerFormatter.java
 *
 * Created on 9. September 2003, 19:18
 */

package org.softsmithy.lib.swing.text;

import java.math.*;
import java.text.*;
import java.util.*;
import javax.swing.text.*;

/**
 *
 * @author  puce
 */
public class WholeNumberFormatter extends NumberFormatter {
  
//  public static final BigInteger MIN_MIN_VALUE = BigInteger.valueOf(Integer.MIN_VALUE);
//  public static final BigInteger MAX_MAX_VALUE = BigInteger.valueOf(Integer.MAX_VALUE);
  
  /** Holds value of property locale. */
  private Locale locale;
  
  /** Holds value of property maximumMaximumValue. */
  private BigInteger maximumMaximumValue = null;
  
  /** Holds value of property minimumMinimumValue. */
  private BigInteger minimumMinimumValue = null;
  
  /** Creates a new instance of IntegerFormatter */
  public WholeNumberFormatter() {
    this(Locale.getDefault());
  }
  
  public WholeNumberFormatter(Locale locale){
    this(null, null, locale);
  }
  
  public WholeNumberFormatter(BigInteger minIntValue, BigInteger maxIntValue, Locale locale){
    setLocale(locale);
    // use BigInteger to recognize values out
    // of range if the range is (Integer.MIN_VALUE, Integer.MAX_VALUE)
    super.setValueClass(BigInteger.class);
    setMinimumBigIntegerValue(minIntValue);
    setMaximumBigIntegerValue(maxIntValue);
//    System.out.println("min: "+ getMinimum());
//    System.out.println("max: "+getMaximum());
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
  
  
  public void setMaximum(Comparable max) {
    if (! (max instanceof BigInteger)){
      throw new IllegalArgumentException("max must be an instance of BigInteger");
    }
    if (getMinimum() != null && max.compareTo(getMinimum()) < 0){
      throw new IllegalArgumentException("max mustn't be smaller than minimum!");
    }
    //    if (max.compareTo(MAX_MAX_VALUE) > 0){
    //      throw new IllegalArgumentException("max mustn't be bigger than MAX_MAX_VALUE!");
    //    }
    super.setMaximum(maximumToRange((BigInteger) max));
  }
  
  public void setMinimum(Comparable minimum) {
    if (! (minimum instanceof BigInteger)){
      throw new IllegalArgumentException("minimum must be an instance of BigInteger");
    }
    if (getMaximum() != null && minimum.compareTo(getMaximum()) > 0){
      throw new IllegalArgumentException("minimum mustn't be bigger than max!");
    }
    //    if (minimum.compareTo(getMinimumMinimumValue()) < 0){
    //      throw new IllegalArgumentException("minimum mustn't be smaller than the minimum minimum value!");
    //    }
    super.setMinimum(minimumToRange((BigInteger) minimum));
  }
  
  public void setValueClass(Class valueClass) {
    throw new UnsupportedOperationException("This operation is not supported!"); // always use BigInteger!
  }
  
  public Object stringToValue(String text) throws ParseException {
    BigInteger value = null;
    if (((DecimalFormat) getFormat()).isGroupingUsed()){
      text = text.replaceAll("\\" + ((DecimalFormat) getFormat()).getDecimalFormatSymbols().getGroupingSeparator(), "");
    }
    try{
      value = valueToRange(new BigInteger(text));
      value = (BigInteger) super.stringToValue(value.toString()); // needed?
    } catch (NumberFormatException nfe){
      nfe.printStackTrace();
      value = (BigInteger) super.stringToValue(text); // will throw a ParseException
    }
    return value;
  }
  
  public BigInteger valueToRange(BigInteger value){
    BigInteger inRangeValue = value;
    if (getMinimumBigIntegerValue() != null){
      inRangeValue = inRangeValue.max(getMinimumBigIntegerValue());
    }
    if (getMaximumBigIntegerValue() != null){
      inRangeValue = inRangeValue.min(getMaximumBigIntegerValue());
    }
    //    if (inRangeValue.compareTo(getMinimum()) < 0){
    //      inRangeValue = (BigInteger) getMinimum();
    //      System.out.println("value (min): "+inRangeValue);
    //    } else if (inRangeValue.compareTo(getMaximum()) > 0){
    //      inRangeValue = (BigInteger) getMaximum();
    //      System.out.println("max2: "+getMaximum());
    //      System.out.println("value (max): "+inRangeValue);
    //    }
    return inRangeValue;
  }
  
  private BigInteger minimumToRange(BigInteger min){
    BigInteger inRangeValue = min;
    if (getMinimumMinimumValue() != null){
      if (inRangeValue == null){
        inRangeValue = getMinimumMinimumValue();
      } else {
        inRangeValue = inRangeValue.max(getMinimumMinimumValue());
      }
    }
    return inRangeValue;
  }
  
  private BigInteger maximumToRange(BigInteger max){
    BigInteger inRangeValue = max;
    if (getMaximumMaximumValue() != null){
      if (inRangeValue == null){
        inRangeValue = getMaximumMaximumValue();
      } else {
        inRangeValue = inRangeValue.min(getMaximumMaximumValue());
      }
    }
    return inRangeValue;
  }
  
  /** Getter for property maximumMaximumValue.
   * @return Value of property maximumMaximumValue.
   *
   */
  protected BigInteger getMaximumMaximumValue() {
    return this.maximumMaximumValue;
  }
  
  /** Setter for property maximumMaximumValue.
   * @param maximumMaximumValue New value of property maximumMaximumValue.
   *
   */
  protected void setMaximumMaximumValue(BigInteger maximumMaximumValue) {
    this.maximumMaximumValue = maximumMaximumValue;
    setMaximumBigIntegerValue(getMaximumBigIntegerValue());
  }
  
  /** Getter for property minimumMinimumValue.
   * @return Value of property minimumMinimumValue.
   *
   */
  protected BigInteger getMinimumMinimumValue() {
    return this.minimumMinimumValue;
  }
  
  /** Setter for property minimumMinimumValue.
   * @param minimumMinimumValue New value of property minimumMinimumValue.
   *
   */
  protected void setMinimumMinimumValue(BigInteger minimumMinimumValue) {
    this.minimumMinimumValue = minimumMinimumValue;
    setMinimumBigIntegerValue(getMinimumBigIntegerValue());
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
    setMaximum(maximumBigIntegerValue);;
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
    setMaximum(minimumBigIntegerValue);
  }
  
}
