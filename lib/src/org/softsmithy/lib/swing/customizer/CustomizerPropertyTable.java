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
 * PropertyTable.java
 *
 * Created on 19. September 2002, 18:03
 */

package org.softsmithy.lib.swing.customizer;

import java.util.*;
import javax.swing.table.*;
import org.softsmithy.lib.swing.*;

/**
 *
 * @author  puce
 */
public class CustomizerPropertyTable extends JCellTable {
  
  /** Creates a new instance of PropertyTable */
  public CustomizerPropertyTable() {
    super(new CustomizerPropertyTableModel(new ArrayList(), null, null));
    init();
  }
  
  private void init(){
    setDefaultRenderer(Style.class, new StyleRenderer(getLocale()));
    setDefaultEditor(Style.class, new StyleCellEditor(getLocale())); // note: Style is an interface
  }
  
  public void setModel(TableModel model){
    if (! (model instanceof CustomizerPropertyTableModel)){
      throw new IllegalArgumentException("model must be a CustomizerPropertyTableModel");
    }
    super.setModel(model);
  }
  
  
  public CustomizerPropertyTableModel getCustomizerPropertyTableModel(){
    return (CustomizerPropertyTableModel) getModel();
  }
  
  public void getCustomizerPropertyTableModel(CustomizerPropertyTableModel model){
    setModel(model);
  }
  
  
}
