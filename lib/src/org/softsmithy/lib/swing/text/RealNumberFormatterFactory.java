
package org.softsmithy.lib.swing.text;

import java.util.*;
import javax.swing.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class RealNumberFormatterFactory extends AbstractXNumberFormatterFactory {
  
  
  /** Creates a new instance of WholeNumberFormatterFactory */
  public RealNumberFormatterFactory(RealNumberFormatter formatter) {
    super(formatter);
  }
  
  public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
    return getRealNumberFormatter();
  }
  
  public RealNumberFormatter getRealNumberFormatter(){
    return (RealNumberFormatter) getAbstractXNumberFormatter();
  }
  

}
