/*
 * ANDFileFilter.java
 *
 * Created on 30. Oktober 2002, 23:23
 */

package org.softsmithy.lib.io;

import java.io.*;

/**
 *
 * @author  puce
 */
public class ANDFileFilter extends CompundFileFilter {
  
  /** Creates a new instance of ANDFileFilter */
  public ANDFileFilter(FileFilter first, FileFilter second) {
    super(first, second);
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
    return getFirstFilter().accept(pathname) && getSecondFilter().accept(pathname);
  }
  
}
