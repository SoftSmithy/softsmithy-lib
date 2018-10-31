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
 * AbstractStyle.java
 *
 * Created on 30. Januar 2003, 18:08
 */

package org.softsmithy.lib.swing.style;

/**
 *
 * @author  puce
 */
public abstract class AbstractStyle implements Style {
  
  /** Creates a new instance of AbstractStyle */
  public AbstractStyle() {
  }
  
  @Override
  public boolean isNull() {
    return false;
  }
  
  @Override
  public void startListening() {
  }
  
  @Override
  public void stopListening() {
  }
}