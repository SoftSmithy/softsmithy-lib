/*
 * CustomizerAction.java
 *
 * Created on 2. Juni 2004, 02:04
 */

package org.softsmithy.lib.swing.action;

import java.util.*;

/**
 *
 * @author  puce
 */
public interface CustomizerAction extends XAction{
  
  public Set getNeededCustomizableProperties();
  public void setNeededCustomizableProperties(Set neededCustomizableProperties);
  
}
