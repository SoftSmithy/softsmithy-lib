/*
 * CustomizerEnvironment.java
 *
 * Created on 30. Januar 2003, 19:10
 */

package org.softsmithy.lib.swing.customizer;

import java.util.*;

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
