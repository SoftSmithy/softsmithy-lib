
package org.softsmithy.lib.swing.text;

import java.util.*;
import javax.swing.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class DoubleFormatterFactory extends RealNumberFormatterFactory {
  
  
  /** Creates a new instance of WholeNumberFormatterFactory */
  public DoubleFormatterFactory(DoubleFormatter formatter) {
    super(formatter);
  }
  
  
  public DoubleFormatter getDoubleFormatter(){
    return (DoubleFormatter) getRealNumberFormatter();
  }
  

}
