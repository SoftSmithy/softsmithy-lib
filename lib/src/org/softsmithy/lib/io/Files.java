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

package org.softsmithy.lib.io;

import java.io.*;
import java.util.*;

/**
 * A utility class for Files. <br>
 * Note: The API may change in a future version!
 *<br><br>
 * @author    Florian Brunner
 * @see java.io.File
 */
public class Files {
  
  /**
   * A file extension. May be removed in a future version.
   */
  public final static String JPEG = "jpeg";
  /**
   * A file extension. May be removed in a future version.
   */
  public final static String JPG = "jpg";
  /**
   * A file extension. May be removed in a future version.
   */
  public final static String GIF = "gif";
  /**
   * A file extension. May be removed in a future version.
   */
  public final static String TIFF = "tiff";
  /**
   * A file extension. May be removed in a future version.
   */
  public final static String TIF = "tif";
  /**
   * A file extension. May be removed in a future version.
   */
  public final static String TXT = "txt";
  
  /**
   * Gets the extension of a file
   *
   * @param f  the file whose extension should be fetched
   * @return   the extension of the specified file
   */
  public static String getExtension(File f) {
    String ext = "";
    String s = f.getName();
    int i = s.lastIndexOf('.');
    
    if (i > 0 && i < s.length() - 1) {
      ext = s.substring(i + 1).toLowerCase();
    }
    return ext;
  }
  
  
  /**
   * Gets the first name of a file (from start to the first point).
   *
   * @param f  the file whose first name should be fetched
   * @return   the first name of the specified file
   */
  public static String getFirstName(File f) {
    String firstName = "";
    String s = f.getName();
    int i = s.indexOf('.');
    
    if (i > -1) {
      firstName = s.substring(0, i);
    }
    return firstName;
  }
  
  /**
   *  Gets the name of a file (from start to the last point).
   *
   * @param f  the file whose name should be fetched
   * @return   the name of the specified file
   *
   * Should this method be renamed?
   */
  public static String getName(File f) {
    String firstName = "";
    String s = f.getName();
    int i = s.lastIndexOf('.');
    
    if (i > -1) {
      firstName = s.substring(0, i);
    }
    return firstName;
  }
  
  /**
   * Return type may change to List<String> in future!?
   */
  public static String[] readLines(File file) throws FileNotFoundException, IOException{
    FileReader reader = new FileReader(file);
    String[] lines = Streams.readLines(reader);
    reader.close(); // is this ok?
    return lines;
  }
  
}
