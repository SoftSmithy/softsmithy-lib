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
 * CustomizerSelectionEvent.java
 *
 * Created on 20. September 2002, 19:14
 */

package org.softsmithy.lib.swing.event;

import java.util.*;

/**
 *
 * @author  puce
 */
public class CustomizerSelectionEvent extends EventObject {
  
  /** Holds value of property selectedCustomizers. */
  private Set selectedCustomizers;
  
  /** Creates a new instance of CustomizerSelectionEvent */
  public CustomizerSelectionEvent(Object source, Set selectedCustomizers) {
    super(source);
    this.selectedCustomizers = selectedCustomizers;
  }
  
  /** Getter for property selectedCustomizers.
   * @return Value of property selectedCustomizers.
   *
   */
  public Set getSelectedCustomizers() {
    return this.selectedCustomizers;
  }
  
}
