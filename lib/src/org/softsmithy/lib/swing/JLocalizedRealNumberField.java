/*
 * JDecimalField.java
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
public class JLocalizedRealNumberField extends JRealNumberField {
  
  
  /** Creates a new instance of JDecimalField */
  public JLocalizedRealNumberField() {
    this(Locale.getDefault());
  }
  
  public JLocalizedRealNumberField(Locale locale){
    this(BigDecimal.valueOf(0), locale);
  }
  
  public JLocalizedRealNumberField(BigDecimal value){
    this(value, Locale.getDefault());
  }
  
  public JLocalizedRealNumberField(BigDecimal value, Locale locale){
    this(value, null, null, locale);
  }
  
  public JLocalizedRealNumberField(BigDecimal minValue, BigDecimal maxValue){
    this(minValue, maxValue, Locale.getDefault());
  }
  
  public JLocalizedRealNumberField(BigDecimal minValue, BigDecimal maxValue, Locale locale){
    this(BigDecimal.valueOf(0), minValue, maxValue, locale);
  }
  
  public JLocalizedRealNumberField(BigDecimal value, BigDecimal minValue, BigDecimal maxValue){
    this(value, minValue, maxValue, Locale.getDefault());
  }
  
  public JLocalizedRealNumberField(BigDecimal value, BigDecimal minValue, BigDecimal maxValue, Locale locale){
    this(new LocalizedRealNumberFormatterFactory(new LocalizedRealNumberFormatter()));
    setMinimumBigDecimalValue(minValue);
    setMaximumBigDecimalValue(maxValue);
    setBigDecimalValue(value);
    setLocale(locale);
  }
  
  public JLocalizedRealNumberField(LocalizedRealNumberFormatterFactory factory){
    super(factory);
  }
  
  protected void reinit(){
    getLocalizedRealNumberFormatterFactory().setLocale(getLocale());
  }
  
  
  protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
    if (! (formatter instanceof LocalizedRealNumberFormatter)){
      throw new IllegalArgumentException("formatter must be an instance of LocalizedRealNumberFormatter!");
    }
    super.setFormatter(formatter);
  }
  
  public LocalizedRealNumberFormatter getLocalizedRealNumberFormatter(){
    return (LocalizedRealNumberFormatter) getRealNumberFormatter();
  }
  
  public LocalizedRealNumberFormatterFactory getLocalizedRealNumberFormatterFactory(){
    return (LocalizedRealNumberFormatterFactory) getRealNumberFormatterFactory();
  }
  
  public void setLocalizedRealNumberFormatterFactory(LocalizedRealNumberFormatterFactory factory){
    setRealNumberFormatterFactory(factory);
  }
  
  
  public void setFormatterFactory(JFormattedTextField.AbstractFormatterFactory aff) {
    if (! (aff instanceof LocalizedRealNumberFormatterFactory)){
      throw new IllegalArgumentException("aff must be an instance of LocalizedRealNumberFormatterFactory!");
    }
    super.setFormatterFactory(aff);
  }
  
}
