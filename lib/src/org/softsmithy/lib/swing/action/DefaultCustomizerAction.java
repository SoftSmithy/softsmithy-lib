/*
 * DefaultCustomizerAction.java
 *
 * Created on 23. Juni 2004, 18:47
 */

package org.softsmithy.lib.swing.action;

import java.util.*;

/**
 *
 * @author  puce
 */
public class DefaultCustomizerAction extends DefaultXAction implements CustomizerAction{
  
  /**
   * Holds value of property neededCustomizableProperties.
   */
  private Set neededCustomizableProperties;
  
  /** Creates a new instance of DefaultCustomizerAction */
  public DefaultCustomizerAction() {
    this(Collections.EMPTY_SET);
  }
  
  public DefaultCustomizerAction(Set neededCustomizableProperties) {
    setNeededCustomizableProperties(Collections.EMPTY_SET);
  }
  
  /**
   * Getter for property neededCustomizableProperties.
   * @return Value of property neededCustomizableProperties.
   */
  public Set getNeededCustomizableProperties() {
    return this.neededCustomizableProperties;
  }
  
  /**
   * Setter for property neededCustomizableProperties.
   * @param neededCustomizableProperties New value of property neededCustomizableProperties.
   */
  public void setNeededCustomizableProperties(Set neededCustomizableProperties) {
    this.neededCustomizableProperties = neededCustomizableProperties;
  }
  
}
