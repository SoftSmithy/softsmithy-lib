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
  
  public ReflectiveCustomizerAction(Object target, StandardActionFactory saf, Locale locale) throws NoSuchMethodException {
    this(target, saf, locale, Collections.EMPTY_SET);
  }
  
  public ReflectiveCustomizerAction(Object target, StandardActionFactory saf, Locale locale, Set neededCustomizableProperties) throws NoSuchMethodException {
    this(target, saf.toString(), neededCustomizableProperties);
    saf.configureXAction(this, locale);
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
