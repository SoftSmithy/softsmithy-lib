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
 * AbstractCustomizer.java
 *
 * Created on 6. Februar 2003, 17:45
 */

package org.softsmithy.lib.swing.customizer;

import java.awt.*;
import java.beans.*;
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.beans.*;
import org.softsmithy.lib.swing.*;

/**
 *
 * @author  puce
 */
public abstract class AbstractCustomizer extends JStyledPanel {
  
  /** Holds value of property customizableProperties. */
  private Set customizableProperties = Collections.EMPTY_SET;
  
  /** Creates a new instance of AbstractCustomizer */
  public AbstractCustomizer() {
  }
  
  /** Getter for property customizableProperties.
   * @return Value of property customizableProperties.
   *
   */
  public Set getCustomizableProperties() {
    return this.customizableProperties;
  }
  
  /** 
   * Setter for property customizableProperties.
   * This property is used by different other classes to find which properties 
   * to listen for. A set of bound property names is expected (Set of Strings).
   * @param customizableProperties New value of property customizableProperties.
   *
   */
  public void setCustomizableProperties(Set customizableProperties) {
    this.customizableProperties = customizableProperties;
  }
  
  // better place for this method?
  // really on AbstractCustomizer or just on subclasses?
  public static Set getCommonCustomizableProperties(Collection customizers) {
    Set properties = Collections.EMPTY_SET;
    Iterator i = customizers.iterator();
    if (i.hasNext()){
      AbstractCustomizer customizer = (AbstractCustomizer) i.next();
      properties = new LinkedHashSet(customizer.getCustomizableProperties());
      for (;i.hasNext();){
        AbstractCustomizer custom = (AbstractCustomizer) i.next();
        properties.retainAll(custom.getCustomizableProperties());
      }
    }
    return properties;
  }
  

  
}
