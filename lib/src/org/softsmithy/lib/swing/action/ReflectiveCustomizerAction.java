/*
 * ReflectiveCustomizerAction.java
 *
 * Created on 2. Juni 2004, 02:09
 */

package org.softsmithy.lib.swing.action;

import java.util.*;

/**
 *
 * @author  puce
 */
public class ReflectiveCustomizerAction extends ReflectiveXAction implements CustomizerAction{
  
  /**
   * Holds value of property neededCustomizableProperties.
   */
  private Set neededCustomizableProperties;
  
  /** Creates a new instance of ReflectiveCustomizerAction */
  public ReflectiveCustomizerAction(Object target, String methodName) throws NoSuchMethodException {
    this(target, methodName, Collections.EMPTY_SET);
  }
  
  /** Creates a new instance of ReflectiveCustomizerAction */
  public ReflectiveCustomizerAction(Object target, String methodName, Set neededCustomizableProperties) throws NoSuchMethodException {
    super(target, methodName);
    setNeededCustomizableProperties(neededCustomizableProperties);
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
