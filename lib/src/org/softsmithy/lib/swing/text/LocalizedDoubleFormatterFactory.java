
package org.softsmithy.lib.swing.text;

import java.util.*;
import javax.swing.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class LocalizedDoubleFormatterFactory extends LocalizedRealNumberFormatterFactory {
  
  
  /** Creates a new instance of WholeNumberFormatterFactory */
  public LocalizedDoubleFormatterFactory(LocalizedRealNumberFormatter formatter) {
    super(formatter);
  }
  
  public LocalizedDoubleFormatterFactory(LocalizedRealNumberFormatter formatter, Locale locale) {
    super(formatter, locale);
  }
  
  public LocalizedDoubleFormatter getLocalizedDoubleFormatter(){
    return (LocalizedDoubleFormatter) getLocalizedRealNumberFormatter();
  }

}
