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
public class JWholeNumberField extends AbstractNumberField {
  
  
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
    setBigIntegerValue(BigInteger.ZERO);
  }
  
  protected void reinit(){
    getWholeNumberFormatterFactory().setLocale(getLocale());
  }
  
  
  public BigInteger getBigIntegerValue(){
    return (BigInteger) getNumberValue();
  }
  
  public void setBigIntegerValue(BigInteger value){
    setNumberValue(value);
  }
  
  public void setValue(Object value) {
    // use BigInteger to recognize if the value is out of range if the range is
    // (Integer.MIN_VALUE, Integer.MAX_VALUE)
    if (value != null && ! (value instanceof BigInteger)){
      throw new IllegalArgumentException("value must be an instance of BigInteger");
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
    return (BigInteger) getMinimumNumberValue();
  }
  
  public void setMinimumBigIntegerValue(BigInteger minValue){
    setMinimumNumberValue(minValue);
  }
  
  
  public BigInteger getMaximumBigIntegerValue(){
    return (BigInteger) getMaximumNumberValue();
  }
  
  public void setMaximumBigIntegerValue(BigInteger minValue){
    setMaximumNumberValue(minValue);
  }
  
  protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
    if (! (formatter instanceof WholeNumberFormatter)){
      throw new IllegalArgumentException("formatter must be an instance of WholeNumberFormatter!");
    }
    super.setFormatter(formatter);
  }
  
  public WholeNumberFormatter getWholeNumberFormatter(){
    return (WholeNumberFormatter) getAbstractXNumberFormatter();
  }
  
  public WholeNumberFormatterFactory getWholeNumberFormatterFactory(){
    return (WholeNumberFormatterFactory) getAbstractXNumberFormatterFactory();
  }
  
  public void setWholeNumberFormatterFactory(WholeNumberFormatterFactory factory){
    setAbstractXNumberFormatterFactory(factory);
  }
  
  public void setFormatterFactory(JFormattedTextField.AbstractFormatterFactory aff) {
    if (! (aff instanceof WholeNumberFormatterFactory)){
      throw new IllegalArgumentException("aff must be an instance of WholeNumberFormatterFactory!");
    }
    super.setFormatterFactory(aff);
  }
  
  public void setMaximumNumberValue(Number maxValue) {
    if (maxValue != null && ! (maxValue instanceof BigInteger)){
      throw new IllegalArgumentException("maxValue must be an instance of BigInteger");
    }
    super.setMaximumNumberValue(maxValue);
  }
  
  public void setMinimumNumberValue(Number minValue) {
    if (minValue != null && ! (minValue instanceof BigInteger)){
      throw new IllegalArgumentException("minValue must be an instance of BigInteger");
    }
    super.setMinimumNumberValue(minValue);
  }
  
}
