/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

package org.softsmithy.lib.swing.text;

import java.util.*;

/**
 *
 * @author  puce
 */
public class WholeNumberFormatterFactory<T extends WholeNumberFormatter> extends AbstractXNumberFormatterFactory<T> {
  
  /** Holds value of property locale. */
  private Locale locale;
  
  /** Creates a new instance of WholeNumberFormatterFactory */
  public WholeNumberFormatterFactory(T formatter) {
    this(formatter, Locale.getDefault());
  }
  
  public WholeNumberFormatterFactory(T formatter, Locale locale) {
    super(formatter);
    setLocale(locale);
  }
  
  @Deprecated
  public T getWholeNumberFormatter(){
    return getNumberFormatter();
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
    getNumberFormatter().setLocale(locale);
  }
  
  
}
