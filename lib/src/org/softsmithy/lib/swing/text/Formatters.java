/*
 * Formatters.java
 *
 * Created on 25. September 2003, 19:24
 */

package org.softsmithy.lib.swing.text;

import javax.swing.text.*;
import org.softsmithy.lib.util.*;

/**
 *
 * @author  puce
 */
public class Formatters {
  
  /** Creates a new instance of Formatters */
  private Formatters() {
  }
  
  public static Comparable valueToRange(InternationalFormatter formatter, Comparable value){
    return Comparables.toRange(value, formatter.getMinimum(), formatter.getMaximum());
  }
  
}
