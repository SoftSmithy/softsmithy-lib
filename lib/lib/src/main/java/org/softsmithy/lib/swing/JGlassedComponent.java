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
 * JGlassedComponent.java
 *
 * Created on 8. August 2003, 15:09
 */

package org.softsmithy.lib.swing;

import javax.swing.*;
import org.softsmithy.lib.test.*;

/**
 *
 * @author  puce
 */
class JGlassedComponent extends JXPanel {
  
    private static final TableLayoutConstraints CONSTRAINTS = new TableLayoutConstraints();
    
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
