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
 * JLocalizedFloatField.java
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
public class JLocalizedFloatField extends JLocalizedRealNumberField {
  
  private static final float DEFAULT_VALUE = 0;
  private static final float DEFAULT_MIN_VALUE = Float.MIN_VALUE;
  private static final float DEFAULT_MAX_VALUE = Float.MAX_VALUE;
  
  /** Creates a new instance of JLocalizedFloatField */
  public JLocalizedFloatField() {
    this(Locale.getDefault());
  }
  
  public JLocalizedFloatField(Locale locale){
    this(DEFAULT_VALUE, locale);
  }
  
  public JLocalizedFloatField(float value){
    this(value, Locale.getDefault());
  }
  
  public JLocalizedFloatField(float value, Locale locale){
    this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, locale);
  }
  
  public JLocalizedFloatField(float minValue, float maxValue){
    this(minValue, maxValue, Locale.getDefault());
  }
  
  public JLocalizedFloatField(float minValue, float maxValue, Locale locale){
    this(DEFAULT_VALUE, minValue, maxValue, locale);
  }
  
  public JLocalizedFloatField(float value, float minValue, float maxValue){
    this(value, minValue, maxValue, Locale.getDefault());
  }
  
  public JLocalizedFloatField(float value, float minValue, float maxValue, Locale locale){
    super(new LocalizedFloatFormatterFactory(new LocalizedFloatFormatter()));
    setMinimumFloatValue(minValue);
    setMaximumFloatValue(maxValue);
    setFloatValue(value);
    setLocale(locale);
  }
  
  
  public float getFloatValue(){
    return getBigDecimalValue().floatValue();
  }
  
  public void setFloatValue(float i){
    setBigDecimalValue(new BigDecimal(i));
  }
  
  public float getMinimumFloatValue(){
    return getMinimumBigDecimalValue().floatValue();
  }
  
  public void setMinimumFloatValue(float minFloatValue){
    setMinimumBigDecimalValue(new BigDecimal(minFloatValue));
  }
  
  public float getMaximumFloatValue(){
    return getMaximumBigDecimalValue().floatValue();
  }
  
  public void setMaximumFloatValue(float minFloatValue){
    setMaximumBigDecimalValue(new BigDecimal(minFloatValue));
  }
  
  protected void setFormatter(JFormattedTextField.AbstractFormatter formatter) {
    if (! (formatter instanceof LocalizedFloatFormatter)){
      throw new IllegalArgumentException("formatter must be an instance of LocalizedFloatFormatter!");
    }
    super.setFormatter(formatter);
  }
  
  public LocalizedFloatFormatter getLocalizedFloatFormatter(){
    return (LocalizedFloatFormatter) getLocalizedRealNumberFormatter();
  }
  
  public LocalizedFloatFormatterFactory getLocalizedFloatFormatterFactory(){
    return (LocalizedFloatFormatterFactory) getLocalizedRealNumberFormatterFactory();
  }
  
  public void setLocalizedFloatFormatterFactory(LocalizedFloatFormatterFactory factory){
    setLocalizedRealNumberFormatterFactory(factory);
  }
  
  public void setFormatterFactory(JFormattedTextField.AbstractFormatterFactory aff) {
    if (! (aff instanceof LocalizedFloatFormatterFactory)){
      throw new IllegalArgumentException("aff must be an instance of LocalizedFloatFormatterFactory!");
    }
    super.setFormatterFactory(aff);
  }
  
}
