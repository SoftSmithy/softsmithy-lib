/*
 *                  Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

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
