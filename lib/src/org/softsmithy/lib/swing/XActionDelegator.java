package puce.swing;

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
