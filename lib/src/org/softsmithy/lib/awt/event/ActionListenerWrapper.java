package org.softsmithy.lib.awt.event;

import java.awt.event.*;

public class ActionListenerWrapper implements ActionListener{
  
  private ActionListener actionListener;
  
  public ActionListenerWrapper(ActionListener actionListener){
    this.actionListener = actionListener;
  }
  public void actionPerformed(ActionEvent actionEvent){
    actionListener.actionPerformed(actionEvent);
  }
}
