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
