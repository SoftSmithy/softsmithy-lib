/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

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
  
  @Override
  public void actionPerformed(ActionEvent e) {
  }
  
}
