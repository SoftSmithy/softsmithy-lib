/*
 * PropertyTable.java
 *
 * Created on 19. September 2002, 18:03
 */

package puce.swing.customizer;

import java.util.*;
import puce.swing.*;

/**
 *
 * @author  puce
 */
public class CustomizerPropertyTable extends JCellTable {
  
  /** Creates a new instance of PropertyTable */
  public CustomizerPropertyTable() {
    super(new CustomizerPropertyTableModel(new ArrayList(), new ArrayList(), null, Locale.getDefault()));
  }
  
}
