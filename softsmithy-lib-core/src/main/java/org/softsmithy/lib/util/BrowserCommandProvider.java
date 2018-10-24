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

/*
 * BrowserCommandProvider.java
 *
 * Created on 8. Oktober 2004, 01:00
 */

package org.softsmithy.lib.util;

/**
 *
 * @author  puce
 * @deprecated 
 */
@Deprecated
public class BrowserCommandProvider{

  /**
   * Holds value of property browserCommand.
   */
  private final String browserCommand;
  
  public BrowserCommandProvider(String browserCommand){
    this.browserCommand = browserCommand;
  }

  /**
   * Getter for property browserCommand.
   * @return Value of property browserCommand.
   */
  public String getBrowserCommand() {
    return this.browserCommand;
  }

  
}
