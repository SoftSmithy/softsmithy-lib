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
 * Streams.java
 *
 * Created on 11. M�rz 2004, 18:27
 */

package org.softsmithy.lib.io;

import java.io.*;
import java.util.*;

/**
 *
 * @author  puce
 */
public class Streams {
  
  /** Creates a new instance of Streams */
  private Streams() {
  }
  
  /**
   * Is this good?
   * Does not close (any?) stream!
   * Return type may change to List<String> in future!?
   */
  public static String[] readLines(InputStream input) throws IOException{
    return readLines(new InputStreamReader(new BufferedInputStream(input)));
  }
  
  /**
   * Is this good?
   * Does not close (any?) stream!
   * Return type may change to List<String> in future!?
   */
  public static String[] readLines(Reader reader) throws IOException{
    List lines = new ArrayList();
    String line;
    BufferedReader breader = new BufferedReader(reader);
    while ((line = breader.readLine()) != null){
      lines.add(line);
    }
    return (String[]) lines.toArray(new String[lines.size()]);
  }
}
