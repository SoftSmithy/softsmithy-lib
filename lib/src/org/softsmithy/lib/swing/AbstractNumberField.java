/*
 * JIntegerField.java
 *
 * Created on 9. September 2003, 18:26
 */

package org.softsmithy.lib.swing;

import java.util.*;
import javax.swing.*;
import javax.swing.JFormattedTextField.*;
import org.softsmithy.lib.swing.text.*;


/**
 *
 * @author  puce
 */
public abstract class AbstractNumberField extends JFormattedTextField {
  
  
  public AbstractNumberField(Number value, AbstractXNumberFormatterFactory factory){
    this(value, null, null, factory);
  }
  
  
  public AbstractNumberField(Number minValue, Number maxValue, AbstractXNumberFormatterFactory factory){
    this(null, minValue, maxValue, factory);
  }
  
  public AbstractNumberField(Number value, Number minValue, Number maxValue, AbstractXNumberFormatterFactory factory){
    this(factory);
    setMinimumNumberValue(minValue);
    setMaximumNumberValue(maxValue);
    setNumberValue(value);
  }
  
  public AbstractNumberField(AbstractXNumberFormatterFactory factory){
    super(factory);
    setHorizontalAlignment(JTextField.TRAILING); // is this right???
    setNumberValue(null);
    reinit();
  }
  
  protected void reinit(){
  }
  
  public void setLocale(Locale l) {
    super.setLocale(l);
    reinit();
  }
  
  public Number getNumberValue(){
    return (Number) getValue();
  }
  
  public void setNumberValue(Number value){
    setValue(value);
  }
  
  public void setValue(Object value) {
    // use Number to recognize if the value is out of range if the range is
    // (Integer.MIN_VALUE, Integer.MAX_VALUE)
    if (value != null && ! (value instanceof Number)){
      throw new IllegalArgumentException("value must be an instance of Number");
    }
    //    if (value instanceof Integer){
    //      value = Number.valueOf(((Integer) value).intValue());
    //    }
    //    if (((Number) value).compareTo(getIntegerFormatter().getMinimum()) < 0){
    //      value = getIntegerFormatter().getMinimum();
    //    }
    if (value != null){
      value = getAbstractXNumberFormatter().valueToRange((Number) value);
    }
    super.setValue(value);
  }
  
  //  public Object getValue(){
  //    if (super.getValue() != null){
  //    System.out.println(super.getValue().getClass());
  //    }
  //    return super.getValue() != null ? new Integer(((Number) super.getValue()).intValue()) : null;
  //  }
  
  public Number getMinimumNumberValue(){
    return getAbstractXNumberFormatter().getMinimumNumberValue();
  }
  
  public void setMinimumNumberValue(Number minValue){
    getAbstractXNumberFormatter().setMinimumNumberValue(minValue);
    setValue(getValue());
  }
  
  public Number getMaximumNumberValue(){
    return getAbstractXNumberFormatter().getMaximumNumberValue();
  }
  
  public void setMaximumNumberValue(Number maxValue){
    getAbstractXNumberFormatter().setMaximumNumberValue(maxValue);
    setValue(getValue());
  }
  
  protected void setFormatter(AbstractFormatter formatter) {
    if (! (formatter instanceof AbstractXNumberFormatter)){
      throw new IllegalArgumentException("formatter must be an instance of AbstractXNumberFormatter!");
    }
    super.setFormatter(formatter);
  }
  
  public AbstractXNumberFormatter getAbstractXNumberFormatter(){
    return (AbstractXNumberFormatter) getFormatter();
  }
  
  public AbstractXNumberFormatterFactory getAbstractXNumberFormatterFactory(){
    return (AbstractXNumberFormatterFactory) getFormatterFactory();
  }
  
  public void setAbstractXNumberFormatterFactory(AbstractXNumberFormatterFactory factory){
    setFormatterFactory(factory);
  }
  
  public void setFormatterFactory(AbstractFormatterFactory aff) {
    if (! (aff instanceof AbstractXNumberFormatterFactory)){
      throw new IllegalArgumentException("aff must be an instance of AbstractXNumberFormatterFactory!");
    }
    super.setFormatterFactory(aff);
    reinit();
    setValue(getValue());
  }
  
}
