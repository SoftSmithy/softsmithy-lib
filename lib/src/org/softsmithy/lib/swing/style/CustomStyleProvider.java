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
 * CustomStyleProvider.java
 *
 * Created on 30. Januar 2003, 18:57
 */

package org.softsmithy.lib.swing.style;

import java.util.*;
import org.softsmithy.lib.swing.style.*;

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
  
  public Style getStyle(Styleable styleable) {
    minimizeCustomStyleProviderObjects();
    return getStyle();
  }
  
  public CustomStyle getStyle() {
    return style;
  }
  
  private void minimizeCustomStyleProviderObjects(){
    if (getStyle().getStyleProvider() != this){
      getStyle().setStyleProvider(this);
    }
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
