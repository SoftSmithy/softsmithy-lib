
package org.softsmithy.lib.swing.text;

import java.util.*;

/**
 *
 * @author  puce
 */
public class ShortFormatterFactory extends WholeNumberFormatterFactory {
  
  
  /** Creates a new instance of WholeNumberFormatterFactory */
  public ShortFormatterFactory(ShortFormatter formatter) {
    this(formatter, Locale.getDefault());
  }
  
  public ShortFormatterFactory(ShortFormatter formatter, Locale locale) {
    super(formatter, locale);
  }
  
  public ShortFormatter getShortFormatter(){
    return (ShortFormatter) getWholeNumberFormatter();
  }
  

}
