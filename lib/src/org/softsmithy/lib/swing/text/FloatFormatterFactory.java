
package org.softsmithy.lib.swing.text;

import java.util.*;
import javax.swing.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class FloatFormatterFactory extends RealNumberFormatterFactory {
  
  
  /** Creates a new instance of WholeNumberFormatterFactory */
  public FloatFormatterFactory(FloatFormatter formatter) {
    super(formatter);
  }
  
  
  public FloatFormatter getFloatFormatter(){
    return (FloatFormatter) getRealNumberFormatter();
  }
  

}
