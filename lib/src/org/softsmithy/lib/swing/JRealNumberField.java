/*
 * JIntegerField.java
 *
 * Created on 9. September 2003, 18:26
 */

package org.softsmithy.lib.swing;

import java.math.*;
import java.text.*;
import javax.swing.JFormattedTextField.*;
import org.softsmithy.lib.swing.text.*;


/**
 *
 * @author  puce
 */
public class JRealNumberField extends AbstractNumberField {
  
  
  public JRealNumberField(){
    this(BigDecimal.valueOf(0));
  }
  
  public JRealNumberField(BigDecimal value){
    this(value, null, null);
  }
  
  public JRealNumberField(NumberFormat format){
    this(new RealNumberFormatterFactory(new RealNumberFormatter(format)));
  }
  
  public JRealNumberField(BigDecimal minValue, BigDecimal maxValue){
    this(BigDecimal.valueOf(0), minValue, maxValue);
  }
  
  public JRealNumberField(NumberFormat format, BigDecimal minValue, BigDecimal maxValue){
    this(new RealNumberFormatterFactory(new RealNumberFormatter(format)));
    init(BigDecimal.valueOf(0), minValue, maxValue);
  }
  
  public JRealNumberField(BigDecimal value, BigDecimal minValue, BigDecimal maxValue){
    this(new RealNumberFormatterFactory(new RealNumberFormatter()));
    init(value, minValue, maxValue);
  }
  
  public JRealNumberField(RealNumberFormatterFactory factory){
    super(factory);
    init(BigDecimal.valueOf(0), null, null);
  }
  
  private void init(BigDecimal value, BigDecimal minValue, BigDecimal maxValue){
    setMinimumBigDecimalValue(minValue);
    setMaximumBigDecimalValue(maxValue);
    setBigDecimalValue(value);
  }
  
  public BigDecimal getBigDecimalValue(){
    return (BigDecimal) getNumberValue();
  }
  
  public void setBigDecimalValue(BigDecimal value){
    setNumberValue(value);
  }
  
  public void setValue(Object value) {
    // use BigDecimal to recognize if the value is out of range if the range is
    // (Integer.MIN_VALUE, Integer.MAX_VALUE)
    if (value != null && ! (value instanceof BigDecimal)){
      throw new IllegalArgumentException("value must be an instance of BigDecimal");
    }
    super.setValue(value);
  }
  
  //  public Object getValue(){
  //    if (super.getValue() != null){
  //    System.out.println(super.getValue().getClass());
  //    }
  //    return super.getValue() != null ? new Integer(((BigDecimal) super.getValue()).intValue()) : null;
  //  }
  
  public BigDecimal getMinimumBigDecimalValue(){
    return (BigDecimal) getMinimumNumberValue();
  }
  
  public void setMinimumBigDecimalValue(BigDecimal minValue){
    setMinimumNumberValue(minValue);
  }
  
  public BigDecimal getMaximumBigDecimalValue(){
    return (BigDecimal) getMaximumNumberValue();
  }
  
  public void setMaximumBigDecimalValue(BigDecimal minValue){
    setMaximumNumberValue(minValue);
  }
  
  protected void setFormatter(AbstractFormatter formatter) {
    if (! (formatter instanceof RealNumberFormatter)){
      throw new IllegalArgumentException("formatter must be an instance of RealNumberFormatter!");
    }
    super.setFormatter(formatter);
  }
  
  public RealNumberFormatter getRealNumberFormatter(){
    return (RealNumberFormatter) getAbstractXNumberFormatter();
  }
  
  public RealNumberFormatterFactory getRealNumberFormatterFactory(){
    return (RealNumberFormatterFactory) getAbstractXNumberFormatterFactory();
  }
  
  public void setRealNumberFormatterFactory(RealNumberFormatterFactory factory){
    setAbstractXNumberFormatterFactory(factory);
  }
  
  public void setFormatterFactory(AbstractFormatterFactory aff) {
    if (! (aff instanceof RealNumberFormatterFactory)){
      throw new IllegalArgumentException("aff must be an instance of RealNumberFormatterFactory!");
    }
    super.setFormatterFactory(aff);
  }
  
  public void setMaximumNumberValue(Number maxValue) {
    if (maxValue != null && ! (maxValue instanceof BigDecimal)){
      throw new IllegalArgumentException("maxValue must be an instance of BigDecimal");
    }
    super.setMaximumNumberValue(maxValue);
  }
  
  public void setMinimumNumberValue(Number minValue) {
    if (minValue != null && ! (minValue instanceof BigDecimal)){
      throw new IllegalArgumentException("minValue must be an instance of BigDecimal");
    }
    super.setMinimumNumberValue(minValue);
  }
  
}
