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
 * BeanIntrospector.java
 *
 * Created on 24. September 2002, 12:47
 */

package org.softsmithy.lib.beans;

import java.beans.*;
import java.util.*;

/**
 *
 * @author  puce
 */
public final class BeanIntrospector {
  
  private static final Map properties = new HashMap();
  private static final Map eventSets = new HashMap();
  private static final Map methods = new HashMap();
  
  /** Creates a new instance of BeanIntrospector */
  private BeanIntrospector() {
  }
  
  public static PropertyDescriptor getPropertyDescriptor(String propertyName, Class beanClass, ResourceBundle rb) throws IntrospectionException{
    PropertyDescriptor descriptor = (PropertyDescriptor) getDescriptor(properties, propertyName, beanClass, rb);
    if (descriptor == null){
      descriptor = new PropertyDescriptor(propertyName, beanClass);
      localizeDescriptor(descriptor, rb);
      putDescriptor(properties, propertyName, beanClass, rb, descriptor);
    }
    return descriptor;
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
  
  private static FeatureDescriptor getDescriptor(Map descriptors, String featureName, Class beanClass, ResourceBundle rb) {
    FeatureDescriptor descriptor = null;
    if (descriptors.containsKey(beanClass)){
      Map featureNameMap = (Map) descriptors.get(beanClass);
      if (featureNameMap.containsKey(featureName)){
        Map rbMap = (Map) featureNameMap.get(featureName);
        descriptor = (FeatureDescriptor) rbMap.get(rb);
      }
    }
    return descriptor;
  }
  
  private static void putDescriptor(Map descriptors, String featureName, Class beanClass, ResourceBundle rb, FeatureDescriptor descriptor) {
    if (! descriptors.containsKey(beanClass)){
      descriptors.put(beanClass, new HashMap());
    }
    Map featureNameMap = (Map) descriptors.get(beanClass);
    if (! featureNameMap.containsKey(featureName)){
      featureNameMap.put(featureName, new HashMap());
    }
    Map rbMap = (Map) featureNameMap.get(featureName);
    rbMap.put(rb, descriptor);
  }
  
  private static void localizeDescriptor(FeatureDescriptor descriptor, ResourceBundle rb) {
    if (rb != null){
      try{
        descriptor.setDisplayName(rb.getString(descriptor.getName()));
      } catch(MissingResourceException ex){
        // ignore it
      }
    }
  }
  
}
