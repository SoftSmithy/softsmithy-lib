/*
 * JIntegerField.java
 *
 * Created on 9. September 2003, 18:26
 */

package org.softsmithy.lib.swing;

import java.math.*;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import org.softsmithy.lib.swing.text.*;

/**
 *
 * @author  puce
 */
public class JWholeNumberField extends JFormattedTextField {
  
  
  /** Creates a new instance of JIntegerField */
  public JWholeNumberField() {
    this(Locale.getDefault());
  }
  
  public JWholeNumberField(Locale locale){
    this(BigInteger.ZERO, locale);
  }
  
  public JWholeNumberField(BigInteger value){
    this(value, Locale.getDefault());
  }
  
  public JWholeNumberField(BigInteger value, Locale locale){
    this(value, null, null, locale);
  }
  
  public JWholeNumberField(BigInteger minValue, BigInteger maxValue){
    this(minValue, maxValue, Locale.getDefault());
  }
  
  public JWholeNumberField(BigInteger minValue, BigInteger maxValue, Locale locale){
    this(BigInteger.ZERO, minValue, maxValue, locale);
  }
  
  public JWholeNumberField(BigInteger value, BigInteger minValue, BigInteger maxValue){
    this(value, minValue, maxValue, Locale.getDefault());
  }
  
  public JWholeNumberField(BigInteger value, BigInteger minValue, BigInteger maxValue, Locale locale){
    this(new WholeNumberFormatterFactory(new WholeNumberFormatter()));
    setMinimumBigIntegerValue(minValue);
    setMaximumBigIntegerValue(maxValue);
    setBigIntegerValue(value);
    setLocale(locale);
  }
  
  public JWholeNumberField(WholeNumberFormatterFactory factory){
    super(factory);
    setHorizontalAlignment(JTextField.TRAILING); // is this right???
    setBigIntegerValue(BigInteger.ZERO);
  }
  
  protected void reinit(){
    getWholeNumberFormatterFactory().setLocale(getLocale());
  }
  
  public void setLocale(Locale l) {
    super.setLocale(l);
    reinit();
  }
  
  public BigInteger getBigIntegerValue(){
    return (BigInteger) getValue();
  }
  
  public void setBigIntegerValue(BigInteger value){
    setValue(value);
  }
  
  public void setValue(Object value) {
    // use BigInteger to recognize if the value is out of range if the range is
    // (Integer.MIN_VALUE, Integer.MAX_VALUE)
    if (value != null && ! (value instanceof BigInteger)){
      throw new IllegalArgumentException("value must be an instance of BigInteger");
    }
    //    if (value instanceof Integer){
    //      value = BigInteger.valueOf(((Integer) value).intValue());
    //    }
    //    if (((BigInteger) value).compareTo(getIntegerFormatter().getMinimum()) < 0){
    //      value = getIntegerFormatter().getMinimum();
    //    }
    if (value != null){
      value = getWholeNumberFormatter().valueToRange((BigInteger) value);
    }
    super.setValue(value);
  }
  
  //  public Object getValue(){
  //    if (super.getValue() != null){
  //    System.out.println(super.getValue().getClass());
  //    }
  //    return super.getValue() != null ? new Integer(((BigInteger) super.getValue()).intValue()) : null;
  //  }
  
  public BigInteger getMinimumBigIntegerValue(){
    return getWholeNumberFormatter().getMinimumBigIntegerValue();
  }
  
  public void setMinimumBigIntegerValue(BigInteger minValue){
    getWholeNumberFormatter().setMinimumBigIntegerValue(minValue);
    setValue(getValue());
  }
  
  public BigInteger getMaximumBigIntegerValue(){
    return getWholeNumberFormatter().getMaximumBigIntegerValue();
  }
  
  public void setMaximumBigIntegerValue(BigInteger minValue){
    getWholeNumberFormatter().setMaximumBigIntegerValue(minValue);
    setValue(getValue());
  }
  
  protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
    if (! (formatter instanceof WholeNumberFormatter)){
      throw new IllegalArgumentException("formatter must be an instance of WholeNumberFormatter!");
    }
    super.setFormatter(formatter);
  }
  
  public WholeNumberFormatter getWholeNumberFormatter(){
    return (WholeNumberFormatter) getFormatter();
  }
  
  public WholeNumberFormatterFactory getWholeNumberFormatterFactory(){
    return (WholeNumberFormatterFactory) getFormatterFactory();
  }
  
  public void setWholeNumberFormatterFactory(WholeNumberFormatterFactory factory){
    setFormatterFactory(factory);
  }
  
  public void setFormatterFactory(JFormattedTextField.AbstractFormatterFactory aff) {
    if (! (aff instanceof WholeNumberFormatterFactory)){
      throw new IllegalArgumentException("aff must be an instance of WholeNumberFormatterFactory!");
    }
    super.setFormatterFactory(aff);
    reinit();
    setValue(getValue());
  }
  
}
