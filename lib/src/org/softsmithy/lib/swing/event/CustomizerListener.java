/*
 * CustomizerListener.java
 *
 * Created on 30. August 2002, 16:40
 */

package puce.swing.event;

import java.util.*;

/**
 *
 * @author  puce
 */
public interface CustomizerListener extends EventListener {
  
  public void customizerMoveRel(CustomizerEvent e);
  public void customizerResizeRel(CustomizerEvent e);
  public void customizerReshapeRel(CustomizerEvent e);
  
}
