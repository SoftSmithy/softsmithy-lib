/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

package org.softsmithy.lib.swing.text;

import java.util.*;

/**
 *
 * @author  puce
 */
public class LocalizedFloatFormatterFactory extends LocalizedRealNumberFormatterFactory<LocalizedFloatFormatter> {
  
  
  /** Creates a new instance of WholeNumberFormatterFactory */
  public LocalizedFloatFormatterFactory(LocalizedFloatFormatter formatter) {
    super(formatter);
  }
  
  public LocalizedFloatFormatterFactory(LocalizedFloatFormatter formatter, Locale locale) {
    super(formatter, locale);
  }
  
  
  @Deprecated
  public LocalizedFloatFormatter getLocalizedFloatFormatter(){
    return getNumberFormatter();
  }

}
