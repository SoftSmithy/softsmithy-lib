/*
 * PropertyTable.java
 *
 * Created on 19. September 2002, 18:03
 */

package puce.swing.customizer;

import java.util.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class CustomizerPropertyTable extends JTable {
  
  /** Creates a new instance of PropertyTable */
  public CustomizerPropertyTable() {
    setModel(new CustomizerPropertyTableModel(new ArrayList(), new ArrayList(), null));
  }
  
}
