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
 * IntegerFormatter.java
 *
 * Created on 9. September 2003, 19:18
 */

package org.softsmithy.lib.swing.text;

import java.math.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class LocalizedRealNumberFormatter extends RealNumberFormatter {
 
  
  /** Holds value of property locale. */
  private Locale locale;
  
  /** Creates a new instance of IntegerFormatter */
  public LocalizedRealNumberFormatter() {
    this(JComponent.getDefaultLocale()); // better than Locale.getDefault()?
  }
  
  public LocalizedRealNumberFormatter(Locale locale){
    this(null, null, locale);
  }
  
  public LocalizedRealNumberFormatter(BigDecimal minIntValue, BigDecimal maxIntValue){
    this(minIntValue, maxIntValue, JComponent.getDefaultLocale()); // better than Locale.getDefault()?
  }
  
  public LocalizedRealNumberFormatter(BigDecimal minIntValue, BigDecimal maxIntValue, Locale locale){
    super(minIntValue, maxIntValue);
    setLocale(locale);
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
    setFormat(NumberFormat.getNumberInstance(locale));
  }
  
  
}
