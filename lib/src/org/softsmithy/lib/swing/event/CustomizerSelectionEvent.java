/*
 * CustomizerSelectionEvent.java
 *
 * Created on 20. September 2002, 19:14
 */

package puce.swing.event;

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
