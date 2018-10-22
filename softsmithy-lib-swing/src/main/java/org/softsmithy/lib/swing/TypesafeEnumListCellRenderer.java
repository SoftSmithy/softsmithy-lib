/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

/*
 * TypesafeEnumListCellRenderer.java
 *
 * Created on 26. November 2002, 16:46
 */

package org.softsmithy.lib.swing;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.util.*;

/**
 *
 * @author  puce
 */
@Deprecated
public class TypesafeEnumListCellRenderer extends DefaultListCellRenderer {
  
  /** Holds value of property locale. */
  private Locale locale = Locale.getDefault();
  
  /** Creates a new instance of TypesafeEnumListCellRenderer */
  public TypesafeEnumListCellRenderer() {
  }
  
  public TypesafeEnumListCellRenderer(Locale locale) {
    this.locale = locale;
  }
  
    @Override
  public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    String typesafeEnumString = null;
    if (value != null){
      if (! (value instanceof TypesafeEnum)){
        throw new IllegalArgumentException("value must be an instance of TypesafeEnum");
      }
      typesafeEnumString = ((TypesafeEnum) value).getDisplayString(getLocale());
    }
    return super.getListCellRendererComponent(list, typesafeEnumString, index, isSelected, cellHasFocus);
  }
  
  /** Getter for property locale.
   * @return Value of property locale.
   *
   */
    @Override
  public Locale getLocale() {
    return this.locale;
  }
  
  /** Setter for property locale.
   * @param locale New value of property locale.
   *
   */
    @Override
  public void setLocale(Locale locale) {
    this.locale = locale;
  }
  
}
