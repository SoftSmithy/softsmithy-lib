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
 * DirectoryFilter.java
 *
 * Created on 30. Oktober 2002, 22:42
 */

package org.softsmithy.lib.io;

import java.io.*;

/**
 *
 * @author  puce
 */
public class DirectoryFilter implements FileFilter {
  
  private static final DirectoryFilter INSTANCE = new DirectoryFilter();
  
  /** Creates a new instance of DirectoryFilter */
  private DirectoryFilter() {
  }
  
  /** Tests whether or not the specified abstract pathname should be
   * included in a pathname list.
   *
   * @param  pathname  The abstract pathname to be tested
   * @return  <code>true</code> if and only if <code>pathname</code>
   *          should be included
   *
   */
  public boolean accept(File pathname) {
    return pathname.isDirectory();
  }
  
  public static DirectoryFilter getInstance(){
    return INSTANCE;
  }
  
}
