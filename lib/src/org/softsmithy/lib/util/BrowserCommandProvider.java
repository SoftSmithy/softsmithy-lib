/*
 * BrowserCommandProvider.java
 *
 * Created on 8. Oktober 2004, 01:00
 */

package org.softsmithy.lib.util;

/**
 *
 * @author  puce
 */
public class BrowserCommandProvider{

  /**
   * Holds value of property browserCommand.
   */
  private String browserCommand;
  
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
