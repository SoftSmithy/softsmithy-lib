/*
 * CustomStyleProvider.java
 *
 * Created on 30. Januar 2003, 18:57
 */

package org.softsmithy.lib.swing.customizer;

import java.util.*;
import org.softsmithy.lib.swing.*;

/**
 *
 * @author  puce
 */
public class CustomStyleProvider implements StyleProvider {
  
  private CustomStyle style;
  
  /** Creates a new instance of CustomStyleProvider */
  public CustomStyleProvider(CustomStyle style) {
    this.style = style;
  }
  
  public Style getStyle(AbstractCustomizer customizer) {
    return getStyle();
  }
  
  public CustomStyle getStyle() {
    return style;
  }
  
  /** Returns a string representation of the object. In general, the
   * <code>toString</code> method returns a string that
   * "textually represents" this object. The result should
   * be a concise but informative representation that is easy for a
   * person to read.
   * It is recommended that all subclasses override this method.
   * <p>
   * The <code>toString</code> method for class <code>Object</code>
   * returns a string consisting of the name of the class of which the
   * object is an instance, the at-sign character `<code>@</code>', and
   * the unsigned hexadecimal representation of the hash code of the
   * object. In other words, this method returns a string equal to the
   * value of:
   * <blockquote>
   * <pre>
   * getClass().getName() + '@' + Integer.toHexString(hashCode())
   * </pre></blockquote>
   *
   * @return  a string representation of the object.
   *
   */
  public String toString() {
    return getStyle().getName();
  }
  
  public String toString(Locale locale) {
    return getStyle().getName(locale);
  }
  
  
}
