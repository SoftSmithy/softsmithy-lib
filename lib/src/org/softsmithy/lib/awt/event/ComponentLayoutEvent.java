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
 * CustomizerEvent.java
 *
 * Created on 30. August 2002, 15:42
 */

package org.softsmithy.lib.awt.event;

import java.awt.*;
import java.util.*;

/**
 *
 * @author  puce
 */
public class ComponentLayoutEvent extends EventObject {
  
  /** Holds value of property bounds. */
  private final Rectangle bounds;  
  
  /** Holds value of property component. */
  private Component component;
  
  /** Creates a new instance of CustomizerEvent */
  public ComponentLayoutEvent(Object source, Component component, Rectangle bounds) {
    super(source);
    this.component = component;
    this.bounds = bounds;
  }
  
  /** Getter for property bounds.
   * @return Value of property bounds.
   *
   */
  public Rectangle getBounds() {
    return this.bounds;
  }
  
  /** Getter for property component.
   * @return Value of property component.
   *
   */
  public Component getComponent() {
    return this.component;
  }  

}
