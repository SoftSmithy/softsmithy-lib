/*
 * BeanIntrospector.java
 *
 * Created on 24. September 2002, 12:47
 */

package puce.beans;

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
  
  public static PropertyDescriptor getPropertyDescriptor(String propertyName, Class beanClass) throws IntrospectionException{
    BeanInfo info = Introspector.getBeanInfo(beanClass);
    if (! properties.containsKey(info)){
      Map map = createFeatureMap(info.getPropertyDescriptors());
      properties.put(info, map);
    }
    return (PropertyDescriptor) ((Map) properties.get(info)).get(propertyName);
  }
  
  public static EventSetDescriptor getEventSetDescriptor(String eventSetName, Class beanClass) throws IntrospectionException{
    BeanInfo info = Introspector.getBeanInfo(beanClass);
    if (! eventSets.containsKey(info)){
      Map map = createFeatureMap(info.getEventSetDescriptors());
      eventSets.put(info, map);
    }
    return (EventSetDescriptor) ((Map) eventSets.get(info)).get(eventSetName);
  }
  
  public static MethodDescriptor getMethodDescriptor(String methodName, Class beanClass) throws IntrospectionException{
    BeanInfo info = Introspector.getBeanInfo(beanClass);
    if (! methods.containsKey(info)){
      Map map = createFeatureMap(info.getMethodDescriptors());
      methods.put(info, map);
    }
    return (MethodDescriptor) ((Map) methods.get(info)).get(methodName);
  }
  
  private static Map createFeatureMap(FeatureDescriptor[] descriptors){
    Map map = new HashMap();
    for (int i=0; i<descriptors.length; i++){
      map.put(descriptors[i].getName(), descriptors[i]);
    }
    return map;
  }
  
  
}
