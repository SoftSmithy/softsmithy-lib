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
 * XDefaultPersistenceDelegate.java
 *
 * Created on 17. Juni 2003, 15:01
 */

package org.softsmithy.lib.beans;

import java.beans.*;
import java.lang.reflect.*;

/**
 *
 * @author  puce
 */
public class XDefaultPersistenceDelegate extends DefaultPersistenceDelegate {
  
  private String[] constructor;
  
  /** Creates a new instance of XDefaultPersistenceDelegate */
  public XDefaultPersistenceDelegate() {
    super(new String[0]);
  }
  
  public XDefaultPersistenceDelegate(String[] constructorPropertyNames) {
    super(constructorPropertyNames);
    this.constructor = constructorPropertyNames;
  }
  
  @Override
  protected Expression instantiate(Object oldInstance, Encoder out) {
    int nArgs = constructor.length;
    Class type = oldInstance.getClass();
    // System.out.println("writeObject: " + oldInstance);
    Object[] constructorArgs = new Object[nArgs];
    for(int i = 0; i < nArgs; i++) {
            /*
            1.2 introduces "public double getX()" et al. which return values
            which cannot be used in the constructors (they are the wrong type).
            In constructors, use public fields in preference to getters
            when they are defined.
             */
      String name = constructor[i];
      
      Field f = null;
      try {
        // System.out.println("Trying field " + name + " in " + type);
        f = type.getDeclaredField(name);
        f.setAccessible(true);
      } catch (NoSuchFieldException e1) {
      } catch (SecurityException e2){
      }
      if (f != null && !Modifier.isStatic(f.getModifiers())){
        try {
          constructorArgs[i] = f.get(oldInstance);
        } catch (IllegalAccessException e){
        } catch (Exception e) {
          // handleError(e, "Warning: Failed to get " + name + " property for " + oldInstance.getClass() + " constructor");
          out.getExceptionListener().exceptionThrown(e);
        }
      }
      if (constructorArgs[i] == null){
        try{
          constructorArgs[i] = type.getMethod("get"+capitalize(name), new Class[0]).invoke(oldInstance, new Object[0]);
        } catch (Exception e) {
          // handleError(e, "Warning: Failed to get " + name + " property for " + oldInstance.getClass() + " constructor");
          out.getExceptionListener().exceptionThrown(e);
        }
      }
    }
    return new Expression(oldInstance, oldInstance.getClass(), "new", constructorArgs);
  }
  
  private static String capitalize(String propertyName) {
    return propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
  }
}
