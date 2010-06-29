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
  
  public static <T extends Comparable<? super T>> boolean isGreater(T c1, T c2){
    return c1.compareTo(c2) > 0;
  }
  
  public static <T> boolean isGreater(T o1, T o2, Comparator<? super T> comparator){
    return comparator.compare(o1, o2) > 0;
  }
  
  public static <T extends Comparable<? super T>> boolean isGreaterEqual(T c1, T c2) {
    return c1.compareTo(c2) >= 0;
  }
  
  public static <T> boolean isGreaterEqual(T o1, T o2, Comparator<? super T> comparator) {
    return comparator.compare(o1, o2) >= 0;
  }
  
  public static <T extends Comparable<? super T>> boolean isLess(T c1, T c2){
    return c1.compareTo(c2) < 0;
  }
  
  public static <T> boolean isLess(T o1, T o2, Comparator<? super T> comparator){
    return comparator.compare(o1, o2) < 0;
  }
  
  public static <T extends Comparable<? super T>> boolean isLessEqual(T c1, T c2) {
    return c1.compareTo(c2) <= 0;
  }
  
  public static <T> boolean isLessEqual(T o1, T o2, Comparator<? super T> comparator) {
    return comparator.compare(o1, o2) <= 0;
  }
  
  public static <T extends Comparable<? super T>> boolean isEqual(T c1, T c2){
    return c1.compareTo(c2) == 0;
  }
  
  public static <T> boolean isEqual(T o1, T o2, Comparator<? super T> comparator){
    return comparator.compare(o1, o2) == 0;
  }
  
  public static <T extends Comparable<? super T>> T min(T c1, T c2){
    return isLess(c1, c2) ? c1 : c2;
  }
  
  public static <T> T min(T o1, T o2, Comparator<? super T> comparator){
    return isLess(o1, o2, comparator) ? o1 : o2;
  }
  
  public static <T extends Comparable<? super T>> T max(T c1, T c2){
    return isGreater(c1, c2) ? c1 : c2;
  }
  
  public static <T> T max(T o1, T o2, Comparator<? super T> comparator){
    return isGreater(o1, o2, comparator) ? o1 : o2;
  }
  
  public static <T extends Comparable<? super T>> T toRange(T value, T min, T max){
    T inRangeValue = value;
    if (min != null){
      inRangeValue = max(inRangeValue, min);
    }
    if (max != null){
      inRangeValue = min(inRangeValue, max);
    }
    return inRangeValue;
  }
  
  public static <T> T toRange(T value, T min, T max, Comparator<? super T> comparator){
    T inRangeValue = value;
    if (min != null){
      inRangeValue = max(inRangeValue, min, comparator);
    }
    if (max != null){
      inRangeValue = min(inRangeValue, max, comparator);
    }
    return inRangeValue;
  }
}
