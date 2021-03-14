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
 * BeanIntrospector.java
 *
 * Created on 24. September 2002, 12:47
 */

package org.softsmithy.lib.beans;

import org.softsmithy.lib.lang.reflect.Classes;
import org.softsmithy.lib.lang.reflect.InvocationTargetRuntimeException;

import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author  puce
 */
public final class BeanIntrospector {
  
  private static final Map<Class<?>, Map<String, Map<ResourceBundle, FeatureDescriptor>>> PROPERTIES = new HashMap<>();
//  private static final Map eventSets = new HashMap();
//  private static final Map methods = new HashMap();
  private static final Object[] EMPTY_OBJECT_ARRAY = new Object[]{};
  
  /** Creates a new instance of BeanIntrospector */
  private BeanIntrospector() {
  }
 
  public static PropertyDescriptor getPropertyDescriptor(String propertyName, Class<?> beanClass, ResourceBundle rb) throws IntrospectionException{
    PropertyDescriptor descriptor = (PropertyDescriptor) getDescriptor(PROPERTIES, propertyName, beanClass, rb);
    if (descriptor == null){
      descriptor = new PropertyDescriptor(propertyName, beanClass);
      setPropertyDescriptor(descriptor, beanClass, rb);
    }
      return descriptor;
  }
  
  public static PropertyDescriptor[] getPropertyDescriptors(Class<?> beanClass, ResourceBundle rb) throws IntrospectionException{
    PropertyDescriptor[] descriptors = Introspector.getBeanInfo(beanClass).getPropertyDescriptors();
    for (int i=0; i<descriptors.length; i++){
      PropertyDescriptor descriptor = (PropertyDescriptor) getDescriptor(PROPERTIES, descriptors[i].getName(), beanClass, rb);
      if (descriptor == null){
        setPropertyDescriptor(descriptors[i], beanClass, rb);
      } else {
        descriptors[i] = descriptor;
      }
    }
    return descriptors;
  }
  
  public static boolean isPropertyReadable(String propertyName, Class<?> beanClass, ResourceBundle rb) throws IntrospectionException{
    return getPropertyDescriptor(propertyName, beanClass, rb).getReadMethod() != null;
  }
  
  public static boolean isPropertyWriteable(String propertyName, Class<?> beanClass, ResourceBundle rb) throws IntrospectionException{
    return getPropertyDescriptor(propertyName, beanClass, rb).getWriteMethod() != null;
  }
  
  public static Object getPropertyValue(String propertyName, Object bean, ResourceBundle rb) throws IntrospectionException, IllegalAccessException, InvocationTargetException{
    return getPropertyDescriptor(propertyName, bean.getClass(), rb).getReadMethod().invoke(bean, EMPTY_OBJECT_ARRAY);
  }
  
  public static void setPropertyValue(String propertyName, Object newValue, Object bean, ResourceBundle rb) throws IntrospectionException, IllegalAccessException, InvocationTargetException{
    getPropertyDescriptor(propertyName, bean.getClass(), rb).getWriteMethod().invoke(bean, new Object[]{newValue});
  }
  
  public static boolean supportsPropertyChangeListeners(Class<?> beanClass){
    boolean supportsPropertyChangeListeners = Classes.containsMethod(beanClass, "addPropertyChangeListener", new Class<?>[]{PropertyChangeListener.class})
    && Classes.containsMethod(beanClass, "removePropertyChangeListener", new Class<?>[]{PropertyChangeListener.class});
//    if (supportsPropertyChangeListeners){
//      try { // ensure that no IllegalAccessException will be thrown (is this code right?)
//        supportsPropertyChangeListeners = getAddPropertyChangeListenerMethod(beanClass).isAccessible()
//        && getRemovePropertyChangeListenerMethod(beanClass).isAccessible();
//      } catch (NoSuchMethodException ex){ // should not happen here
//        ex.printStackTrace();
//      }
//    }
    return supportsPropertyChangeListeners;
  }
  
  public static boolean supportsPropertyChangeListenersByPropertyName(Class<?> beanClass){
    boolean supportsPropertyChangeListenersByPropertyName = Classes.containsMethod(beanClass, "addPropertyChangeListener", new Class<?>[]{String.class, PropertyChangeListener.class})
    && Classes.containsMethod(beanClass, "removePropertyChangeListener", new Class<?>[]{String.class, PropertyChangeListener.class});
//    if (supportsPropertyChangeListenersByPropertyName){
//      try { // ensure that no IllegalAccessException will be thrown (is this code right?)
//        supportsPropertyChangeListenersByPropertyName = getAddPropertyChangeListenerByPropertyNameMethod(beanClass).isAccessible()
//        && getRemovePropertyChangeListenerByPropertyNameMethod(beanClass).isAccessible();
//      } catch (NoSuchMethodException ex){ // should not happen here
//        ex.printStackTrace();
//      }
//    }
    return supportsPropertyChangeListenersByPropertyName;
  }
  
  private static Method getAddPropertyChangeListenerMethod(Class<?> beanClass) throws NoSuchMethodException{
    return beanClass.getMethod("addPropertyChangeListener", new Class<?>[]{PropertyChangeListener.class});
  }
  
  private static Method getAddPropertyChangeListenerByPropertyNameMethod(Class<?> beanClass) throws NoSuchMethodException{
    return beanClass.getMethod("addPropertyChangeListener", new Class<?>[]{String.class, PropertyChangeListener.class});
  }
  
  private static Method getRemovePropertyChangeListenerMethod(Class<?> beanClass) throws NoSuchMethodException{
    return beanClass.getMethod("removePropertyChangeListener", new Class<?>[]{PropertyChangeListener.class});
  }
  
  private static Method getRemovePropertyChangeListenerByPropertyNameMethod(Class<?> beanClass) throws NoSuchMethodException{
    return beanClass.getMethod("removePropertyChangeListener", new Class<?>[]{String.class, PropertyChangeListener.class});
  }
  
  public static void addPropertyChangeListener(Object bean, PropertyChangeListener listener) throws NoSuchMethodException, IllegalAccessException{
    try{
      getAddPropertyChangeListenerMethod(bean.getClass()).invoke(bean, new Object[]{listener});
    } catch(InvocationTargetException ex){ // no checked exception should have been thrown
      throw new InvocationTargetRuntimeException(ex.getCause(), ex.getLocalizedMessage());
    }
  }
  
  public static void addPropertyChangeListener(Object bean, String propertyName, PropertyChangeListener listener) throws NoSuchMethodException, IllegalAccessException{
    try{
      getAddPropertyChangeListenerByPropertyNameMethod(bean.getClass()).invoke(bean, new Object[]{propertyName, listener});
    } catch(InvocationTargetException ex){ // no checked exception should have been thrown
      throw new InvocationTargetRuntimeException(ex.getCause(), ex.getLocalizedMessage());
    }
  }
  
  public static void removePropertyChangeListener(Object bean, PropertyChangeListener listener) throws NoSuchMethodException, IllegalAccessException{
    try{
      getRemovePropertyChangeListenerMethod(bean.getClass()).invoke(bean, new Object[]{listener});
    } catch(InvocationTargetException ex){ // no checked exception should have been thrown
      throw new InvocationTargetRuntimeException(ex.getCause(), ex.getLocalizedMessage());
    }
  }
  
  public static void removePropertyChangeListener(Object bean, String propertyName, PropertyChangeListener listener) throws NoSuchMethodException, IllegalAccessException{
    try{
      getRemovePropertyChangeListenerByPropertyNameMethod(bean.getClass()).invoke(bean, new Object[]{propertyName, listener});
    } catch(InvocationTargetException ex){ // no checked exception should have been thrown
      throw new InvocationTargetRuntimeException(ex.getCause(), ex.getLocalizedMessage());
    }
  }
  
  /*public static EventSetDescriptor getEventSetDescriptor(String eventSetName, Class beanClass, ResourceBundle rb) throws IntrospectionException{
    EventSetDescriptor descriptor = (EventSetDescriptor) getDescriptor(eventSets, eventSetName, beanClass, rb);
    if (descriptor == null){
      descriptor = new EventSetDescriptor(eventSetName, beanClass);
      localizeDescriptor(descriptor, rb);
      putDescriptor(eventSets, eventSetName, beanClass, rb, descriptor);
    }
    return descriptor;
  }*/
  
  /*public static MethodDescriptor getMethodDescriptor(String methodName, Class beanClass, ResourceBundle rb) throws IntrospectionException{
    MethodDescriptor descriptor = (MethodDescriptor) getDescriptor(methods, methodName, beanClass, rb);
    if (descriptor == null){
      descriptor = new MethodDescriptor(methodName, beanClass);
      localizeDescriptor(descriptor, rb);
      putDescriptor(methods, methodName, beanClass, rb, descriptor);
    }
    return descriptor;
  }*/
  
  /*private static Map createFeatureMap(FeatureDescriptor[] descriptors, ResourceBundle rb){
    Map map = new HashMap();
    for (int i=0; i<descriptors.length; i++){
      if (rb != null){
        try{
          descriptors[i].setDisplayName(rb.getString(descriptors[i].getName()));
        } catch(MissingResourceException ex){
          // ignore it
        }
      }
      map.put(descriptors[i].getName(), descriptors[i]);
    }
    return map;
  }
   
  public PropertyDescriptor[] copyPropertyDescriptors(PropertyDescriptor[] src){
    PropertyDescriptor[] target = new PropertyDescriptor[src.length];
    for (int i=0; i<src.length; i++){
      target[i] = new PropertyDescriptor(src[i].getName(), src[i].getReadMethod(), src[i].getWriteMethod());
      copyPropertyDescriptor(src[i], target[i]);
    }
    return target;
  }
   
  public void copyPropertyDescriptor(PropertyDescriptor src, PropertyDescriptor target) {
    copyFeatureDescriptor(src, target);
    target.setBound(src.isBound());
    target.setConstrained(src.isConstrained());
    target.setPropertyEditorClass(src.getPropertyEditorClass());
    target.setReadMethod(src.getReadMethod());
    target.setWriteMethod(src.getWriteMethod());
  }
   
  public void copyFeatureDescriptor(FeatureDescriptor src, FeatureDescriptor target) {
    target.setDisplayName(src.getDisplayName());
    target.setExpert(src.isExpert());
    target.setHidden(src.isHidden());
    target.setName(src.getName());
    target.setPreferred(src.isPreferred());
    target.setShortDescription(src.getShortDescription());
    // copy the values? how??
  }*/
  
  private static FeatureDescriptor getDescriptor(Map<Class<?>, Map<String, Map<ResourceBundle, FeatureDescriptor>>> descriptors, String featureName, Class<?> beanClass, ResourceBundle rb) {
    FeatureDescriptor descriptor = null;
    if (descriptors.containsKey(beanClass)){
      Map<String, Map<ResourceBundle, FeatureDescriptor>> featureNameMap = descriptors.get(beanClass);
      if (featureNameMap.containsKey(featureName)){
        Map<ResourceBundle, FeatureDescriptor> rbMap = featureNameMap.get(featureName);
        descriptor = rbMap.get(rb);
      }
    }
    return descriptor;
  }
  
  private static void putDescriptor(Map<Class<?>, Map<String, Map<ResourceBundle, FeatureDescriptor>>> descriptors, FeatureDescriptor descriptor, Class<?> beanClass, ResourceBundle rb) {
    if (! descriptors.containsKey(beanClass)){
      descriptors.put(beanClass, new HashMap<>());
    }
    Map<String, Map<ResourceBundle, FeatureDescriptor>> featureNameMap = descriptors.get(beanClass);
    if (! featureNameMap.containsKey(descriptor.getName())){
      featureNameMap.put(descriptor.getName(), new HashMap<>());
    }
    Map<ResourceBundle, FeatureDescriptor> rbMap = featureNameMap.get(descriptor.getName());
    rbMap.put(rb, descriptor);
  }
  
  private static void localizeDescriptor(FeatureDescriptor descriptor, ResourceBundle rb) {
    if (rb != null){
      try{
        descriptor.setDisplayName(rb.getString(descriptor.getName()+".displayName"));
      } catch(MissingResourceException ex){
        // ignore it
      }
      try{
        descriptor.setShortDescription(rb.getString(descriptor.getName()+".shortDescription"));
      } catch(MissingResourceException ex){
        // ignore it
      }
      try{
        descriptor.setExpert(Boolean.valueOf(rb.getString(descriptor.getName()+".expert")));
      } catch(MissingResourceException ex){
        // ignore it
      }
      try{
        descriptor.setHidden(Boolean.valueOf(rb.getString(descriptor.getName()+".hidden")));
      } catch(MissingResourceException ex){
        // ignore it
      }
      try{
        descriptor.setPreferred(Boolean.valueOf(rb.getString(descriptor.getName()+".preferred")));
      } catch(MissingResourceException ex){
        // ignore it
      }
    }
  }
  
  private static void setPropertyDescriptor(PropertyDescriptor descriptor, Class<?> beanClass, ResourceBundle rb){
    localizePropertyDescriptor(descriptor, rb);
    putDescriptor(PROPERTIES, descriptor, beanClass, rb);
  }
  
  private static void localizePropertyDescriptor(PropertyDescriptor descriptor, ResourceBundle rb){
    if (rb != null){
      localizeDescriptor(descriptor, rb);
      try{
        descriptor.setBound(Boolean.valueOf(rb.getString(descriptor.getName()+".bound")));
      } catch(MissingResourceException ex){
        // ignore it
      }
      try{
        descriptor.setConstrained(Boolean.valueOf(rb.getString(descriptor.getName()+".constrained")));
      } catch(MissingResourceException ex){
        // ignore it
      }
    }
  }
  
}