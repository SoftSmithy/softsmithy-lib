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
 * CustomizerActionManager.java
 *
 * Created on 22. Oktober 2002, 16:33
 */

package org.softsmithy.lib.swing;

import java.util.*;
import org.softsmithy.lib.swing.event.*;

/**
 * The CustomizerActionManager enables and disables Actions when the current 
 * selection of customizers changes. With each action a set of properties can be specified 
 * (the set can be empty). 
 * If all selected customizers support all specified properties of an Action, 
 * the Action is enabled else disabled.
 * If the selection is empty, all registered Actions are disabled. 
 *
 * The CustomizerActionManager must be registered as a CustomizerSelectionListener
 * to a SelectionManager of a JCustomizerPane.
 *
 * @author  puce
 */
public class CustomizerActionManager implements CustomizerSelectionListener {
  
  private final Map actions = new HashMap();
  
  /** Creates a new instance of CustomizerActionManager */
  public CustomizerActionManager() {
  }
  
  public void selectionChanged(CustomizerSelectionEvent e) {
    Set commonProperties = e.getCommonCustomizableProperties(); //JCustomizer.getCommonCustomizableProperties(e.getSelectedCustomizers());
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
