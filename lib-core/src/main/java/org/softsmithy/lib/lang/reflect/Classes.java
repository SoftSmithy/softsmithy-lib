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

package org.softsmithy.lib.lang.reflect;

import java.beans.*;
import java.lang.reflect.*;
import java.util.*;

/**
 * A utility class for Classes.
 *
 * @author    Florian Brunner
 * @created   March 7, 200
 * @see       java.lang.Class
 */
public class Classes {
  
  private static final Map<Class<?>, Class<?>> WRAPPER_CLASSES;
  
  static{
    Map<Class<?>, Class<?>> wrappers = new HashMap<Class<?>, Class<?>>();
    wrappers.put(Boolean.TYPE, Boolean.class);
    wrappers.put(Character.TYPE, Character.class);
    wrappers.put(Byte.TYPE, Byte.class);
    wrappers.put(Short.TYPE, Short.class);
    wrappers.put(Integer.TYPE, Integer.class);
    wrappers.put(Long.TYPE, Long.class);
    wrappers.put(Float.TYPE, Float.class);
    wrappers.put(Double.TYPE, Double.class);
    wrappers.put(Void.TYPE, Void.class);
    WRAPPER_CLASSES = Collections.unmodifiableMap(wrappers);
  }
  /**
   * No public constructor.
   */
  private Classes() { }
  
  
  /**
   * Tests if the specified class implements (directly or indirectly) the
   * specified interface.
   *
   * @param aClass                        the specified class
   * @param anInterface                   the specified interface
   * @return                              true if the specified class implements
   *                                      (directly or indirectly) the specified
   *                                      interface; false otherwise
   * @extension IllegalArgumentException  if the parameter anInterface is not an interface
   */
  public static boolean implementsInterface(Class aClass, Class anInterface) {
    if (!anInterface.isInterface()) {
      throw new IllegalArgumentException(anInterface.getName() + " is not an interface!");
    }
    boolean impl = false;
    Class current = aClass;
    while ((current != null) && (!impl)) {
      impl = Arrays.asList(current.getInterfaces()).contains(anInterface);
      current = current.getSuperclass();
    }
    return impl;
  }
  
  /**
   * Tests if the first class extends (directly or indirectly) the second class.
   *
   * @param subclass                      the subclass
   * @param superclass                    the superclass
   * @return                              true if subclass extends (directly or
   *                                      indirectly) the superclass; false otherwise
   */
  public static boolean extendsClass(Class subclass, Class superclass) {
    boolean ext = false;
    for (Class current = subclass.getSuperclass(); (current != null) && (!ext); current = current.getSuperclass()) {
      ext = current.equals(superclass);
      
    }
    return ext;
  }
  
  public static Class<?> getTopMostCommonClass(Class<?> a, Class<?> b){
    Class baseClass;
    if (a == null || b == null){
      baseClass = null;
    } else if (a.equals(b)){
      baseClass = a;
    } else if (extendsClass(a, b)){
      baseClass = b;
    } else if (extendsClass(b, a)){
      baseClass = a;
    } else {
      baseClass = getTopMostCommonClass(a.getSuperclass(), b.getSuperclass());
    }
    return baseClass;
  }
  
  public static Class<?> getTopMostCommonClass(Class<?>[] classes){
    Class topMostCommonClass = null;
    if (classes.length > 0){
      topMostCommonClass = classes[0];
      for (int i=1; i<classes.length; i++){
        topMostCommonClass = Classes.getTopMostCommonClass(topMostCommonClass, classes[i]);
      }
    }
    return topMostCommonClass;
  }
  
  public static Class[] getClasses(Object[] objects){
    Class[] classes = new Class[objects.length];
    for (int i=0; i<objects.length; i++){
      classes[i] = objects[i].getClass();
    }
    return classes;
  }
  
  public static Class[] getClasses(Collection objects){
    Class[] classes = new Class[objects.size()];
    Iterator iterator = objects.iterator();
    for (int i=0; i<classes.length; i++){
      classes[i] = iterator.next().getClass();
    }
    return classes;
  }
  
  public static String createWrapper(Class aClass, String packageName){
    String[] names = aClass.getName().split("\\.");
    String className = names[names.length-1];
    String wrappedObj = Introspector.decapitalize(className);
    StringBuffer wrapper = new StringBuffer("package ").append(packageName).append(";\n\n");
    //    if (! aClass.getPackage().getName().equals(packageName)){
    //      wrapper.append("import ").append(aClass.getPackage().getName()).append(";\n\n");
    //    }
    wrapper.append("public class ").append(className).append("Wrapper ");
    wrapper.append(aClass.isInterface()? "implements " : "extends ");
    wrapper.append(aClass.getName()).append("{\n\n");
    wrapper.append("private ").append(aClass.getName()).append(" ").append(wrappedObj).append(";\n\n");
    wrapper.append("public ").append(className).append("Wrapper(");
    wrapper.append(aClass.getName()).append(" ").append(wrappedObj).append("){\n");
    wrapper.append("this.").append(wrappedObj).append(" = ").append(wrappedObj).append(";\n}\n\n");
    Method[] methods = aClass.getMethods();
    for (int i=0; i<methods.length; i++){
      int modifiers = methods[i].getModifiers();
      if (Modifier.isPublic(modifiers)){
        wrapper.append("public ");
      } else if (Modifier.isProtected(modifiers)){
        wrapper.append("protected ");
      }
      if (Modifier.isStatic(modifiers)){
        wrapper.append("static ");
      }
      wrapper.append(methods[i].getReturnType().getName()).append(" ");
      String[] methodNames = methods[i].getName().split("\\.");
      String methodName = methodNames[methodNames.length-1];
      wrapper.append(methodName).append("(");
      Class[] parameterTypes = methods[i].getParameterTypes();
      List<String> args = new ArrayList<String>();
      for (int j=0; j<parameterTypes.length; j++){
        wrapper.append(parameterTypes[j].getName()).append(" ");
        String[] typeNames = parameterTypes[j].getName().split("\\.");
        String argName = Introspector.decapitalize(typeNames[typeNames.length-1]);
        args.add(argName);
        wrapper.append(argName);
        if (j < parameterTypes.length-1){
          wrapper.append(", ");
        }
      }
      wrapper.append("){\n");
      if (! methods[i].getReturnType().equals(Void.TYPE)){
        wrapper.append("return ");
      }
      wrapper.append(wrappedObj).append(".").append(methodName).append("(");
      for (Iterator j=args.iterator(); j.hasNext();){
        wrapper.append(j.next());
        if (j.hasNext()){
          wrapper.append(", ");
        }
      }
      wrapper.append(");\n}\n\n");
    }
    wrapper.append("}");
    return wrapper.toString();
  }
  
  public static String createAdapter(Class anInterface){
    if (!anInterface.isInterface()) {
      throw new IllegalArgumentException(anInterface.getName() + " is not an interface!");
    }
    String adapter = "public class " + anInterface.getName() + "Adapter implements "
    + anInterface.getName() + "{+\n\n";
    Method[] methods = anInterface.getMethods();
    for (int i=0; i<methods.length; i++){
      adapter += methods[i].toString() + "{}\n\n";
    }
    return adapter;
  }
  
  public static Class getWrapperClass(Class primitiveClass) {
    if (! primitiveClass.isPrimitive()){
      throw new IllegalArgumentException("primitiveClass must be a primitive class!");
    }
    return WRAPPER_CLASSES.get(primitiveClass);
  }
  
  public static boolean containsMethod(Class<?> aClass, String name, Class<?>[] parameterTypes){
    boolean containsMethod = false;
    Method[] methods = aClass.getMethods();
    for (int i=0; i<methods.length; i++){
      if (methods[i].getName().equals(name)){
        containsMethod = Arrays.equals(methods[i].getParameterTypes(), parameterTypes);
      }
      if (containsMethod){
        break;
      }
    }
    return containsMethod;
  }
  
}
