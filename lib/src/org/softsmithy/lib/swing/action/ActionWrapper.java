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

package org.softsmithy.lib.swing.action;

import java.awt.event.*;
import java.beans.*;
import javax.swing.*;
import org.softsmithy.lib.awt.event.*;

public class ActionWrapper extends ActionListenerWrapper implements Action{
  private Action action;
  public ActionWrapper(Action action){
    super(action);
    this.action = action;
  }
  public java.lang.Object getValue(java.lang.String string){
    return action.getValue(string);
  }
  public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener){
    action.addPropertyChangeListener(propertyChangeListener);
  }
  public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener){
    action.removePropertyChangeListener(propertyChangeListener);
  }
  public void putValue(java.lang.String string, java.lang.Object object){
    action.putValue(string, object);
  }
  public void setEnabled(boolean isEnabled){
    action.setEnabled(isEnabled);
  }
  public boolean isEnabled(){
    return action.isEnabled();
  }
}

