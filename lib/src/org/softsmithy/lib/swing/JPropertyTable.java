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

package org.softsmithy.lib.swing;

import java.util.*;
import javax.swing.table.*;
import org.softsmithy.lib.swing.table.*;

/**
 *
 * @author  puce
 */
public class JPropertyTable extends JCellTable{
  
  
  
  /** Creates a new instance of PropertyTable */
  public JPropertyTable() {
    this(new PropertyTableModel(new ArrayList(), null, null, Locale.getDefault()));
  }
  
  public JPropertyTable(PropertyTableModel ptm){
    super(ptm);
  }
  
  public void setModel(TableModel model){
    if (! (model instanceof PropertyTableModel)){
      throw new IllegalArgumentException("model must be a CustomizerPropertyTableModel");
    }
    super.setModel(model);
  }
  
  
  public PropertyTableModel getPropertyTableModel(){
    return (PropertyTableModel) getModel();
  }
  
  public void setPropertyTableModel(PropertyTableModel model){
    setModel(model);
  }
  
  /** Sets the locale of this component.  This is a bound property.
   * @param l the locale to become this component's locale
   * @see #getLocale
   * @since JDK1.1
   *
   */
  public void setLocale(Locale locale) {
    super.setLocale(locale);
    if (getPropertyTableModel() != null){
      getPropertyTableModel().setLocale(locale);
    }
  }
  
}
