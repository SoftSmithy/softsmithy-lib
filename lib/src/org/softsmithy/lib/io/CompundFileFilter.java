/*
 * CompundFileFilter.java
 *
 * Created on 30. Oktober 2002, 23:18
 */

package org.softsmithy.lib.io;

import java.io.*;

/**
 *
 * @author  puce
 */
public abstract class CompundFileFilter implements FileFilter {
  
  /** Holds value of property firstFilter. */
  private FileFilter firstFilter;
  
  /** Holds value of property secondFilter. */
  private FileFilter secondFilter;
  
  /** Creates a new instance of CompundFileFilter */
  public CompundFileFilter(FileFilter first, FileFilter second) {
    this.firstFilter = first;
    this.secondFilter = second;
  }
  
  /** Getter for property firstFilter.
   * @return Value of property firstFilter.
   *
   */
  public FileFilter getFirstFilter() {
    return this.firstFilter;
  }
  
  /** Getter for property secondFilter.
   * @return Value of property secondFilter.
   *
   */
  public FileFilter getSecondFilter() {
    return this.secondFilter;
  }
  
}
