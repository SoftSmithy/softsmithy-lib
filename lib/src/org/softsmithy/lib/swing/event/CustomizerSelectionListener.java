/*
 * CustomizerSelectionListener.java
 *
 * Created on 20. September 2002, 19:11
 */

package puce.swing.event;

import java.util.*;

/**
 *
 * @author  puce
 */
public interface CustomizerSelectionListener extends EventListener {
  void selectionChanged(CustomizerSelectionEvent e);
}
