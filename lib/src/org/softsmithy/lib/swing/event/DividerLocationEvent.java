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
 * DividerLocationEvent.java
 *
 * Created on 23. Juni 2003, 11:04
 */

package org.softsmithy.lib.swing.event;

import java.util.*;

/**
 *
 * @author  puce
 */
public class DividerLocationEvent extends EventObject {
  
  /** Holds value of property index. */
  private int index;
  
  /** Holds value of property location. */
  private int location;
  
  /** Creates a new instance of DividerLocationEvent */
  public DividerLocationEvent(Object source, int index, int location) {
    super(source);
    this.index = index;
    this.location = location;
  }
  
  /** Getter for property index.
   * @return Value of property index.
   *
   */
  public int getIndex() {
    return this.index;
  }
  
  /** Getter for property location.
   * @return Value of property location.
   *
   */
  public int getLocation() {
    return this.location;
  }
  
}
