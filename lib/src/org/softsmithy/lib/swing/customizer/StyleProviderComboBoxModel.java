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
 * StyleProviderComboBoxModel.java
 *
 * Created on 6. Februar 2003, 15:06
 */

package org.softsmithy.lib.swing.customizer;

import java.util.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class StyleProviderComboBoxModel extends DefaultComboBoxModel {
  
  /** Creates a new instance of StyleProviderComboBoxModel */
  public StyleProviderComboBoxModel() {
    super(new Vector(CustomizerEnvironment.getLocalCustomizerEnvironment().getAllStyleProviders()));
  }

  
}
