/*
 * DefaultXAction.java
 *
 * Created on 13. Mai 2004, 02:07
 */

package org.softsmithy.lib.swing.action;

import java.awt.event.*;

/**
 * Provides an empty actionPerformed method body.
 * Usefull for JCheckBox and JToggleButton, if they are only listening to ItemEvents.
 *
 * @author  puce
 * TODO: Check name
 * TODO: Check: Usefull to provide DefaultXAction AND AbstractXAction?
 */
public class DefaultXAction extends AbstractXAction{
  
  /** Creates a new instance of DefaultXAction */
  public DefaultXAction() {
  }
  
  public void actionPerformed(ActionEvent e) {
  }
  
}
