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

package org.softsmithy.lib.swing;

import java.awt.event.*;
import javax.swing.event.EventListenerList;

/**
 * Delegates ActionEvents to registered ActionListeners.<br>
 * Note: Not yet a public class. Is this class really usefull?
 * Is 'delegate' the right word?<br>
 *
 *
 * @author    Florian Brunner
 */

class XActionDelegator extends AbstractXAction {

  private EventListenerList listeners;

  // ActionListener registration and invocation.

  /**
   * Forwards the ActionEvent to the registered listener.
   *
   * @param evt  an ActionEvent object
   */
  public void actionPerformed(ActionEvent evt) {
    if (listeners != null) {
      Object[] listenerList = listeners.getListenerList();

      // Recreate the ActionEvent and stuff the value of the ACTION_COMMAND_KEY
      ActionEvent e = new ActionEvent(evt.getSource(), evt.getID(),
          getActionCommand());
      for (int i = 0; i <= listenerList.length - 2; i += 2) {
        ((ActionListener) listenerList[i + 1]).actionPerformed(e);
      }
    }
  }

  /**
   * Registers a listener for ActionEvents.
   *
   * @param listener  the listener to register
   */
  public void addActionListener(ActionListener l) {
    if (listeners == null) {
      listeners = new EventListenerList();
    }
    listeners.add(ActionListener.class, l);
  }

  /**
   * Removes a listener from the listener list for ActionEvents.
   *
   * @param listener  the listener to remove
   */
  public void removeActionListener(ActionListener l) {
    if (listeners == null) {
      return;
    }
    listeners.remove(ActionListener.class, l);
  }

}
