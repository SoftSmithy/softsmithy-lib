
package org.softsmithy.lib.swing.text;

import java.util.*;

/**
 *
 * @author  puce
 */
public class ByteFormatterFactory extends WholeNumberFormatterFactory {
  
  
  /** Creates a new instance of WholeNumberFormatterFactory */
  public ByteFormatterFactory(ByteFormatter formatter) {
    this(formatter, Locale.getDefault());
  }
  
  public ByteFormatterFactory(ByteFormatter formatter, Locale locale) {
    super(formatter, locale);
  }
  
  public ByteFormatter getByteFormatter(){
    return (ByteFormatter) getWholeNumberFormatter();
  }
  

}
