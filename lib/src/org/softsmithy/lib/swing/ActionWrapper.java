package puce.swing;

import java.awt.event.*;
import java.beans.*;
import javax.swing.*;
import puce.awt.event.*;

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

