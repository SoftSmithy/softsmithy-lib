/*
 * Streams.java
 *
 * Created on 11. März 2004, 18:27
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
