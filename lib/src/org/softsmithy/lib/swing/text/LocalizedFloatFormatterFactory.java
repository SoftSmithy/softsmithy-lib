
package org.softsmithy.lib.swing.text;

import java.util.*;
import javax.swing.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class LocalizedFloatFormatterFactory extends LocalizedRealNumberFormatterFactory {
  
  
  /** Creates a new instance of WholeNumberFormatterFactory */
  public LocalizedFloatFormatterFactory(LocalizedRealNumberFormatter formatter) {
    super(formatter);
  }
  
  public LocalizedFloatFormatterFactory(LocalizedRealNumberFormatter formatter, Locale locale) {
    super(formatter, locale);
  }
  
  public LocalizedFloatFormatter getLocalizedFloatFormatter(){
    return (LocalizedFloatFormatter) getLocalizedRealNumberFormatter();
  }

}
