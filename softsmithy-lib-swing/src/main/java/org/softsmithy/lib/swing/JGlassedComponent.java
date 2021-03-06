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
 * JGlassedComponent.java
 *
 * Created on 8. August 2003, 15:09
 */

package org.softsmithy.lib.swing;

import org.softsmithy.lib.swing.internal.TableLayout;
import javax.swing.*;

/**
 *
 * @author  puce
 */
class JGlassedComponent extends JXPanel {
    
     private JComponent component = null;
  
  /** Holds value of property viewGlassed. */
  private boolean glassed = false;
  
  /** Holds value of property viewGlassPane. */
  private JComponent glassPane = new JXLabel();
  
  /** Creates a new instance of JGlassedComponent */
  public JGlassedComponent() {
    setLayout(new TableLayout(new double[][]{{TableLayout.FILL}, {TableLayout.FILL}}));
  }
  
  public JComponent getComponent() {
    return component;
  }
  
//  
//  public void setComponent(JComponent component) {
//    if (this.component != null){
//      viewPane.remove(this.component);
//    }
//    if (component != null){
//      viewPane.add(component, CONSTRAINTS);
//    }
//    this.component = component;
//
//  }
  
}
