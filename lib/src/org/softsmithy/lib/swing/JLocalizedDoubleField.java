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
 * JLocalizedDoubleField.java
 *
 * Created on 9. September 2003, 18:26
 */

package org.softsmithy.lib.swing;

import java.math.*;
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.swing.text.*;

/**
 *
 * @author  puce
 */
public class JLocalizedDoubleField extends JLocalizedRealNumberField {
  
  private static final double DEFAULT_VALUE = 0;
  private static final double DEFAULT_MIN_VALUE = Double.MIN_VALUE;
  private static final double DEFAULT_MAX_VALUE = Double.MAX_VALUE;
  
  /** Creates a new instance of JLocalizedDoubleField */
  public JLocalizedDoubleField() {
    this(Locale.getDefault());
  }
  
  public JLocalizedDoubleField(Locale locale){
    this(DEFAULT_VALUE, locale);
  }
  
  public JLocalizedDoubleField(double value){
    this(value, Locale.getDefault());
  }
  
  public JLocalizedDoubleField(double value, Locale locale){
    this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, locale);
  }
  
  public JLocalizedDoubleField(double minValue, double maxValue){
    this(minValue, maxValue, Locale.getDefault());
  }
  
  public JLocalizedDoubleField(double minValue, double maxValue, Locale locale){
    this(DEFAULT_VALUE, minValue, maxValue, locale);
  }
  
  public JLocalizedDoubleField(double value, double minValue, double maxValue){
    this(value, minValue, maxValue, Locale.getDefault());
  }
  
  public JLocalizedDoubleField(double value, double minValue, double maxValue, Locale locale){
    super(new LocalizedDoubleFormatterFactory(new LocalizedDoubleFormatter()));
    setMinimumDoubleValue(minValue);
    setMaximumDoubleValue(maxValue);
    setDoubleValue(value);
    setLocale(locale);
  }
  
  
  public double getDoubleValue(){
    return getBigDecimalValue().doubleValue();
  }
  
  public void setDoubleValue(double i){
    setBigDecimalValue(new BigDecimal(i));
  }
  
  public double getMinimumDoubleValue(){
    return getMinimumBigDecimalValue().doubleValue();
  }
  
  public void setMinimumDoubleValue(double minDoubleValue){
    setMinimumBigDecimalValue(new BigDecimal(minDoubleValue));
  }
  
  public double getMaximumDoubleValue(){
    return getMaximumBigDecimalValue().doubleValue();
  }
  
  public void setMaximumDoubleValue(double minDoubleValue){
    setMaximumBigDecimalValue(new BigDecimal(minDoubleValue));
  }
  
  protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
    if (! (formatter instanceof LocalizedDoubleFormatter)){
      throw new IllegalArgumentException("formatter must be an instance of LocalizedDoubleFormatter!");
    }
    super.setFormatter(formatter);
  }
  
  public LocalizedDoubleFormatter getLocalizedDoubleFormatter(){
    return (LocalizedDoubleFormatter) getLocalizedRealNumberFormatter();
  }
  
  public LocalizedDoubleFormatterFactory getLocalizedDoubleFormatterFactory(){
    return (LocalizedDoubleFormatterFactory) getLocalizedRealNumberFormatterFactory();
  }
  
  public void setLocalizedDoubleFormatterFactory(LocalizedDoubleFormatterFactory factory){
    setLocalizedRealNumberFormatterFactory(factory);
  }
  
  public void setFormatterFactory(JFormattedTextField.AbstractFormatterFactory aff) {
    if (! (aff instanceof LocalizedDoubleFormatterFactory)){
      throw new IllegalArgumentException("aff must be an instance of LocalizedDoubleFormatterFactory!");
    }
    super.setFormatterFactory(aff);
  }
  
}
