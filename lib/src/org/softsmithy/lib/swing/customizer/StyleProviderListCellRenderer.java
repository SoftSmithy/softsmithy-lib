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
 * Copy of TypesafeEnumListCellRenderer.java -> generalize!
 *
 * Created on 26. November 2002, 16:46
 */

package org.softsmithy.lib.swing.customizer;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.util.*;

/**
 *
 * @author  puce
 */
public class StyleProviderListCellRenderer extends DefaultListCellRenderer {
  
  /** Holds value of property locale. */
  private Locale locale = Locale.getDefault();
  
  /** Creates a new instance of TypesafeEnumListCellRenderer */
  public StyleProviderListCellRenderer() {
  }
  
  public StyleProviderListCellRenderer(Locale locale) {
    this.locale = locale;
  }
  
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    String styleProviderString = null;
    if (value != null){
      if (! (value instanceof StyleProvider)){
        throw new IllegalArgumentException("value must be an instance of StyleProvider");
      }
      styleProviderString = ((StyleProvider) value).toString(getLocale());
    }
    return super.getListCellRendererComponent(list, styleProviderString, index, isSelected, cellHasFocus);
  }
  
  /** Getter for property locale.
   * @return Value of property locale.
   *
   */
  public Locale getLocale() {
    return this.locale;
  }
  
  /** Setter for property locale.
   * @param locale New value of property locale.
   *
   */
  public void setLocale(Locale locale) {
    this.locale = locale;
  }
  
}
