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
 * PropertyTableModel.java
 *
 * Created on 19. September 2002, 18:11
 */

package org.softsmithy.lib.swing.customizer;

import java.util.*;
import org.softsmithy.lib.swing.*;
import org.softsmithy.lib.swing.event.*;

/**
 *
 * @author  puce
 */
public class CustomizerPropertyTableModel extends AbstractCustomizerPropertyTableModel implements CustomizerListener{
  
  /** Creates a new instance of PropertyTableModel */
  public CustomizerPropertyTableModel(List properties, JCustomizer activeCustomizer, ResourceBundle rb) {
    super(properties, activeCustomizer, rb);
    if (activeCustomizer != null){
      activeCustomizer.addCustomizerListener(this);
    }
  }
  
  public void customizerFinishReshapeRel(CustomizerEvent e) {
    updateBounds();
  }
  
  public void customizerReshapeRel(CustomizerEvent e) {
    updateBounds();
  }
  
  private void updateBounds(){
    String[] bounds = {"x", "y", "width", "height"};
    for (int i=0; i<bounds.length; i++){
      int index = getProperties().indexOf(bounds[i]);
      if (index >= 0){
        fireTableCellUpdated(index, 1);
      }
    }
  }
  
  public void stopListening(){
    super.stopListening();
    stopCustomizerListening();
  }
  
  private void stopCustomizerListening() {
    if (getActiveCustomizer() != null){
      ((JCustomizer) getActiveCustomizer()).removeCustomizerListener(this);
    }
  }
  

  
  
}
