/*
 * CustomizerActionManager.java
 *
 * Created on 22. Oktober 2002, 16:33
 */

package org.softsmithy.lib.swing;

import java.util.*;
import org.softsmithy.lib.swing.event.*;

/**
 *
 * @author  puce
 */
public class CustomizerActionManager implements CustomizerSelectionListener {
  
  private final Map actions = new HashMap();
  
  /** Creates a new instance of CustomizerActionManager */
  public CustomizerActionManager() {
  }
  
  public void selectionChanged(CustomizerSelectionEvent e) {
    Set commonProperties = JCustomizer.getCommonCustomizableProperties(e.getSelectedCustomizers());
    for (Iterator i = actions.keySet().iterator(); i.hasNext();){
      XAction action = (XAction) i.next();
      if (e.getSelectedCustomizers().size() > 0){
        action.setEnabled(commonProperties.containsAll((Set) actions.get(action)));
      } else {
        action.setEnabled(false);
      }
    }
    
  }
  
  public void addAction(XAction action, Set properties){
    actions.put(action, properties);
  }
  
  public void removeAction(XAction action){
    actions.remove(action);
  }
  
}
