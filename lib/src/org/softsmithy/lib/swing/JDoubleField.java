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
import org.softsmithy.lib.swing.text.*;


/**
 *
 * @author  puce
 */
public class JDoubleField extends JRealNumberField {
  
  private static final double DEFAULT_VALUE = 0;
  private static final double DEFAULT_MIN_VALUE = Double.MIN_VALUE;
  private static final double DEFAULT_MAX_VALUE = Double.MAX_VALUE;
  
  public JDoubleField(){
    this(DEFAULT_VALUE);
  }
  
  public JDoubleField(double value){
    this(value, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
  }
  
  public JDoubleField(NumberFormat format){
    this(new DoubleFormatterFactory(new DoubleFormatter(format)));
  }
  
  public JDoubleField(double minValue, double maxValue){
    this(DEFAULT_VALUE, minValue, maxValue);
  }
  
  public JDoubleField(NumberFormat format, double minValue, double maxValue){
    this(new DoubleFormatterFactory(new DoubleFormatter(format)));
    init(DEFAULT_VALUE, minValue, maxValue);
  }
  
  public JDoubleField(double value, double minValue, double maxValue){
    this(new DoubleFormatterFactory(new DoubleFormatter()));
    init(value, minValue, maxValue);
  }
  
  public JDoubleField(DoubleFormatterFactory factory){
    super(factory);
    init(DEFAULT_VALUE, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
  }
  
  private void init(double value, double minValue, double maxValue){
    setMinimumDoubleValue(minValue);
    setMaximumDoubleValue(maxValue);
    setDoubleValue(value);
  }
  
  public double getDoubleValue(){
    return getBigDecimalValue().doubleValue();
  }
  
  public void setDoubleValue(double value){
    setBigDecimalValue(new BigDecimal(value));
  }
  
  
  public double getMinimumDoubleValue(){
    return getMinimumBigDecimalValue().doubleValue();
  }
  
  public void setMinimumDoubleValue(double minValue){
    setMinimumBigDecimalValue(new BigDecimal(minValue));
  }
  
  public double getMaximumDoubleValue(){
    return getMaximumBigDecimalValue().doubleValue();
  }
  
  public void setMaximumDoubleValue(double minValue){
    setMaximumBigDecimalValue(new BigDecimal(minValue));
  }
  
  protected void setFormatter(AbstractFormatter formatter) {
    if (! (formatter instanceof DoubleFormatter)){
      throw new IllegalArgumentException("formatter must be an instance of DoubleFormatter!");
    }
    super.setFormatter(formatter);
  }
  
  public DoubleFormatter getDoubleFormatter(){
    return (DoubleFormatter) getRealNumberFormatter();
  }
  
  public DoubleFormatterFactory getDoubleFormatterFactory(){
    return (DoubleFormatterFactory) getRealNumberFormatterFactory();
  }
  
  public void setDoubleFormatterFactory(DoubleFormatterFactory factory){
    setRealNumberFormatterFactory(factory);
  }
  
  public void setFormatterFactory(AbstractFormatterFactory aff) {
    if (! (aff instanceof DoubleFormatterFactory)){
      throw new IllegalArgumentException("aff must be an instance of DoubleFormatterFactory!");
    }
    super.setFormatterFactory(aff);
  }
  
  
  
}
