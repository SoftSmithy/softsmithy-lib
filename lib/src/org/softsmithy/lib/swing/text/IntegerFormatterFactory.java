
package org.softsmithy.lib.swing.text;

import java.util.*;

/**
 *
 * @author  puce
 */
public class IntegerFormatterFactory extends WholeNumberFormatterFactory {
  
  
  /** Creates a new instance of WholeNumberFormatterFactory */
  public IntegerFormatterFactory(IntegerFormatter formatter) {
    this(formatter, Locale.getDefault());
  }
  
  public IntegerFormatterFactory(IntegerFormatter formatter, Locale locale) {
    super(formatter, locale);
  }
  
  public IntegerFormatter getIntegerFormatter(){
    return (IntegerFormatter) getWholeNumberFormatter();
  }
  

}
