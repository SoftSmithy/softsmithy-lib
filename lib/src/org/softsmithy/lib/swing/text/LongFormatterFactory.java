
package org.softsmithy.lib.swing.text;

import java.util.*;

/**
 *
 * @author  puce
 */
public class LongFormatterFactory extends WholeNumberFormatterFactory {
  
  
  /** Creates a new instance of WholeNumberFormatterFactory */
  public LongFormatterFactory(LongFormatter formatter) {
    this(formatter, Locale.getDefault());
  }
  
  public LongFormatterFactory(LongFormatter formatter, Locale locale) {
    super(formatter, locale);
  }
  
  public LongFormatter getLongFormatter(){
    return (LongFormatter) getWholeNumberFormatter();
  }
  

}
