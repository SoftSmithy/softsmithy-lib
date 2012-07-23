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
 * PropertiesFileNameFilter.java
 *
 * Created on 30. Oktober 2002, 22:23
 */

package org.softsmithy.lib.io;

import java.io.*;

/**
 *
 * @author  puce
 */
public class ExtensionFileFilter implements FileFilter {
  
  /** Holds value of property extension. */
  private String extension;
  
  /** Creates a new instance of PropertiesFileNameFilter */
  public ExtensionFileFilter(String extension) {
    this.extension = extension;
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
    return pathname.isFile() && Files.getExtension(pathname).equals(getExtension());
  }
  
  /** Getter for property extension.
   * @return Value of property extension.
   *
   */
  public String getExtension() {
    return this.extension;
  }
  
}
