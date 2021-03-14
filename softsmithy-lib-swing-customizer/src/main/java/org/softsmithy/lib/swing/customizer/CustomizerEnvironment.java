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
 * CustomizerEnvironment.java
 *
 * Created on 30. Januar 2003, 19:10
 */

package org.softsmithy.lib.swing.customizer;

import org.softsmithy.lib.swing.style.NoneStyleProvider;
import org.softsmithy.lib.swing.style.ParentStyleProvider;
import org.softsmithy.lib.swing.style.StyleProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author  puce
 */
public class CustomizerEnvironment {
  
  private static final CustomizerEnvironment LOCAL_CUSTOMIZER_ENVIRONMENT = new CustomizerEnvironment();
  
  private List<StyleProvider> styleProviders = Collections.unmodifiableList(Arrays.asList(new StyleProvider[]{NoneStyleProvider.INSTANCE, ParentStyleProvider.INSTANCE}));
  
  /** Creates a new instance of CustomizerEnvironment */
  protected CustomizerEnvironment() {
  }
  
  public List<StyleProvider> getAllStyleProviders(){
    return styleProviders;
  }
  
  public void setAllStyleProviders(List<StyleProvider> styleProviders){
    this.styleProviders = styleProviders;
  }
  
  public static CustomizerEnvironment getLocalCustomizerEnvironment(){
    return LOCAL_CUSTOMIZER_ENVIRONMENT;
  }
}
