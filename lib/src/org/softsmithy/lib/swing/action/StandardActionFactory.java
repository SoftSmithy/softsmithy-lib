/*
 * StandardActionFactory.java
 *
 * Created on 2. Juni 2004, 02:34
 */

package org.softsmithy.lib.swing.action;

import java.util.*;
import org.softsmithy.lib.util.*;

/**
 *
 * @author  puce
 */
public abstract class StandardActionFactory extends TypesafeEnum{
  
  private static final String RB_BASE_NAME ="org.softsmithy.lib.swing.StandardActions";
  //private static final Map standardActions = new HashMap();
  
  
  
  /** Creates a new instance of StandardActionFactory */
  protected StandardActionFactory(String name) {
    super(name);
  }
  
  public XAction createXAction(Object target, Locale locale) throws NoSuchMethodException{
    return XActions.createXAction(toString(), target, ResourceBundle.getBundle(RB_BASE_NAME, locale));
  }
  
  public void configureXAction(XAction action, Locale locale){
//    if (! standardActions.containsKey(locale)){
//      standardActions.put(locale, new HashMap());
//    }
//    if (! ((Map) standardActions.get(locale)).containsKey(toString())){
      XActions.configureXAction(action, toString(), ResourceBundle.getBundle(RB_BASE_NAME, locale));
//      ((Map) standardActions.get(locale)).put(name, standardAction);
//    } else {
//      standardAction = (XAction) ((Map) standardActions.get(locale)).get(name);
//    }
//    return standardAction;
//  }
  }
  
}
