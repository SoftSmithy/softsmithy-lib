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
 * Strings.java
 *
 * Not yet used!
 *
 * Created on 30. Oktober 2002, 10:43
 */

package org.softsmithy.lib.util;

/**
 *
 * @author  puce
 */
public class Strings {
  
  /** Creates a new instance of Strings */
  private Strings() {
  }
  
  public static String join(String[] strings, String delimiter){
    return join(strings, false, delimiter, false);
  }
  
  public static String join(String[] strings, boolean prepend, String delimiter){
    return join(strings, prepend, delimiter, false);
  }
  
  public static String join(String[] strings, String delimiter, boolean append){
    return join(strings, false, delimiter, append);
  }
  
  public static String join(String[] strings, boolean prepend, String delimiter, boolean append){
    StringBuffer string = new StringBuffer();
    if (prepend){
      string.append(delimiter);
    }
    for (int i=0; i<strings.length; i++){
      string.append(strings[i]);
      if (i < strings.length-1){
        string.append(delimiter);
      }
    }
    if (append){
      string.append(delimiter);
    }
    return string.toString();
  }
  
  
}
