
package org.softsmithy.lib.swing.text;

import java.util.*;
import javax.swing.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class WholeNumberFormatterFactory extends JFormattedTextField.AbstractFormatterFactory {
  
  private final WholeNumberFormatter formatter;
  
  /** Holds value of property locale. */
  private Locale locale;
  
  /** Creates a new instance of WholeNumberFormatterFactory */
  public WholeNumberFormatterFactory(WholeNumberFormatter formatter) {
    this(formatter, Locale.getDefault());
  }
  
  public WholeNumberFormatterFactory(WholeNumberFormatter formatter, Locale locale) {
    this.formatter = formatter;
    setLocale(locale);
  }
  
  public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
    return formatter;
  }
  
  public WholeNumberFormatter getWholeNumberFormatter(){
    return formatter;
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
    getWholeNumberFormatter().setLocale(locale);
  }
  

}
