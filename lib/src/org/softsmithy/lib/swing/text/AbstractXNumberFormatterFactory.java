
package org.softsmithy.lib.swing.text;

import java.util.*;
import javax.swing.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class AbstractXNumberFormatterFactory extends JFormattedTextField.AbstractFormatterFactory {
  
  private final AbstractXNumberFormatter formatter;
  
  /** Creates a new instance of WholeNumberFormatterFactory */
  public AbstractXNumberFormatterFactory(AbstractXNumberFormatter formatter) {
     this.formatter = formatter;
  }
  
  public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf) {
    return getAbstractXNumberFormatter();
  }
  
  public AbstractXNumberFormatter getAbstractXNumberFormatter(){
    return formatter;
  }
  

}
