/*
 * Comparables.java
 *
 * Created on 17. September 2003, 16:35
 */

package org.softsmithy.lib.util;

import java.util.*;

/**
 *
 * @author  puce
 */
public class Comparables {
  
  /** Creates a new instance of Comparables */
  private Comparables() {
  }
  
  public static boolean isGreater(Comparable c1, Comparable c2){
    return c1.compareTo(c2) > 0;
  }
  
  public static boolean isGreater(Object o1, Object o2, Comparator comparator){
    return comparator.compare(o1, o2) > 0;
  }
  
  public static boolean isGreaterEqual(Comparable c1, Comparable c2) {
    return c1.compareTo(c2) >= 0;
  }
  
  public static boolean isGreaterEqual(Object o1, Object o2, Comparator comparator) {
    return comparator.compare(o1, o2) >= 0;
  }
  
  public static boolean isLess(Comparable c1, Comparable c2){
    return c1.compareTo(c2) < 0;
  }
  
  public static boolean isLess(Object o1, Object o2, Comparator comparator){
    return comparator.compare(o1, o2) < 0;
  }
  
  public static boolean isLessEqual(Comparable c1, Comparable c2) {
    return c1.compareTo(c2) <= 0;
  }
  
  public static boolean isLessEqual(Object o1, Object o2, Comparator comparator) {
    return comparator.compare(o1, o2) <= 0;
  }
  
  public static boolean isEqual(Comparable c1, Comparable c2){
    return c1.compareTo(c2) == 0;
  }
  
  public static boolean isEqual(Object o1, Object o2, Comparator comparator){
    return comparator.compare(o1, o2) == 0;
  }
  
  public static Comparable min(Comparable c1, Comparable c2){
    return isLess(c1, c2) ? c1 : c2;
  }
  
  public static Object min(Object o1, Object o2, Comparator comparator){
    return isLess(o1, o2, comparator) ? o1 : o2;
  }
  
  public static Comparable max(Comparable c1, Comparable c2){
    return isGreater(c1, c2) ? c1 : c2;
  }
  
  public static Object max(Object o1, Object o2, Comparator comparator){
    return isGreater(o1, o2, comparator) ? o1 : o2;
  }
  
  public static Comparable toRange(Comparable value, Comparable min, Comparable max){
    Comparable inRangeValue = value;
    if (min != null){
      inRangeValue = max(inRangeValue, min);
    }
    if (max != null){
      inRangeValue = min(inRangeValue, max);
    }
    return inRangeValue;
  }
  
  public static Object toRange(Object value, Object min, Object max, Comparator comparator){
    Object inRangeValue = value;
    if (min != null){
      inRangeValue = max(inRangeValue, min, comparator);
    }
    if (max != null){
      inRangeValue = min(inRangeValue, max, comparator);
    }
    return inRangeValue;
  }
}
