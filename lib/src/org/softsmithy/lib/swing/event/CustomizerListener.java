/*
 * CustomizerListener.java
 *
 * Created on 30. August 2002, 16:40
 */

package org.softsmithy.lib.swing.event;

import java.util.*;

/**
 *
 * @author  puce
 */
public interface CustomizerListener extends EventListener {
  
  
  void customizerReshapeRel(CustomizerEvent e);
  void customizerFinishReshapeRel(CustomizerEvent e);
  void customizerDelete(CustomizerEvent e);
  
}
