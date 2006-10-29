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
import java.text.*;
import javax.swing.JFormattedTextField.*;
import org.softsmithy.lib.math.Floats;
import org.softsmithy.lib.swing.text.*;


/**
 *
 * @author  puce
 */
public class JFloatField extends JRealNumberField {
  
  private static final float DEFAULT_VALUE = 0;
  private static final float DEFAULT_MIN_VALUE = Floats.MAX_NEGATIVE_VALUE;
  private static final float DEFAULT_MAX_VALUE = Float.MAX_VALUE;
  
  public JFloatField(){
    this(DEFAULT_VALUE);
  }
  
  public JFloatField(float value){
    this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
  }
  
  public JFloatField(NumberFormat format){
    this(new FloatFormatterFactory(new FloatFormatter(format)));
  }
  
  public JFloatField(float minValue, float maxValue){
    this(DEFAULT_VALUE, minValue, maxValue);
  }
  
  public JFloatField(NumberFormat format, float minValue, float maxValue){
    this(new FloatFormatterFactory(new FloatFormatter(format)));
    init(DEFAULT_VALUE, minValue, maxValue);
  }
  
  public JFloatField(float value, float minValue, float maxValue){
    this(new FloatFormatterFactory(new FloatFormatter()));
    init(value, minValue, maxValue);
  }
  
  public JFloatField(FloatFormatterFactory factory){
    super(factory);
    init(DEFAULT_VALUE, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
  }
  
  private void init(float value, float minValue, float maxValue){
    setMinimumFloatValue(minValue);
    setMaximumFloatValue(maxValue);
    setFloatValue(value);
  }
  
  public float getFloatValue(){
    return getBigDecimalValue().floatValue();
  }
  
  public void setFloatValue(float value){
    setBigDecimalValue(new BigDecimal(value));
  }
  
  
  public float getMinimumFloatValue(){
    return getMinimumBigDecimalValue().floatValue();
  }
  
  public void setMinimumFloatValue(float minValue){
    setMinimumBigDecimalValue(new BigDecimal(minValue));
  }
  
  public float getMaximumFloatValue(){
    return getMaximumBigDecimalValue().floatValue();
  }
  
  public void setMaximumFloatValue(float minValue){
    setMaximumBigDecimalValue(new BigDecimal(minValue));
  }
  
  protected void setFormatter(AbstractFormatter formatter) {
    if (! (formatter instanceof FloatFormatter)){
      throw new IllegalArgumentException("formatter must be an instance of FloatFormatter!");
    }
    super.setFormatter(formatter);
  }
  
  public FloatFormatter getFloatFormatter(){
    return (FloatFormatter) getRealNumberFormatter();
  }
  
  public FloatFormatterFactory getFloatFormatterFactory(){
    return (FloatFormatterFactory) getRealNumberFormatterFactory();
  }
  
  public void setFloatFormatterFactory(FloatFormatterFactory factory){
    setRealNumberFormatterFactory(factory);
  }
  
  public void setFormatterFactory(AbstractFormatterFactory aff) {
    if (! (aff instanceof FloatFormatterFactory)){
      throw new IllegalArgumentException("aff must be an instance of FloatFormatterFactory!");
    }
    super.setFormatterFactory(aff);
  }
  
  
  
}
