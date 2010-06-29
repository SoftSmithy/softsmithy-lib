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
public abstract class CompoundFileFilter implements FileFilter {
  
  /** Holds value of property firstFilter. */
  private FileFilter firstFilter;
  
  /** Holds value of property secondFilter. */
  private FileFilter secondFilter;
  
  /** Creates a new instance of CompundFileFilter */
  public CompoundFileFilter(FileFilter first, FileFilter second) {
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
