/*
 * XIcon.java
 *
 * Created on 24. März 2004, 00:41
 */

package org.softsmithy.lib.swing;

import javax.swing.*;

/**
 *
 * @author  puce
 */
public interface XIcon extends Icon {
  
  public XIcon getScaledInstance(int newWidth, int newHeight);
  
}
