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
 * CustomizerEnvironment.java
 *
 * Created on 30. Januar 2003, 19:10
 */

package org.softsmithy.lib.swing.customizer;

import java.util.*;
import org.softsmithy.lib.swing.style.*;
/**
 *
 * @author  puce
 */
public class CustomizerEnvironment {
  
  private static final CustomizerEnvironment customizerEnvironment = new CustomizerEnvironment();
  
  private List styleProviders = Collections.unmodifiableList(Arrays.asList(new Object[]{NoneStyleProvider.INSTANCE, ParentStyleProvider.INSTANCE}));
  
  /** Creates a new instance of CustomizerEnvironment */
  protected CustomizerEnvironment() {
  }
  
  public List getAllStyleProviders(){
    return styleProviders;
  }
  
  public void setAllStyleProviders(List styleProviders){
    this.styleProviders = styleProviders;
  }
  
  public static CustomizerEnvironment getLocalCustomizerEnvironment(){
    return customizerEnvironment;
  }
}
