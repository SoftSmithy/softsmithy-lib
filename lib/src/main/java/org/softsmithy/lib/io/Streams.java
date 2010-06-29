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
 * A utility class for Streams.
 * @author puce
 */
public class Streams {
    
    /** Creates a new instance of Streams */
    private Streams() {
    }
    
    /**
     * Reads the lines of an input stream.
     * 
     * Is this good?
     * Does not close (any?) stream!
     * Return type may change to List<String> in future!?
     * @param input the input stream
     * @throws java.io.IOException 
     * @return the lines read from the input stream
     */
    public static String[] readLines(InputStream input) throws IOException{
        return readLines(new InputStreamReader(new BufferedInputStream(input)));
    }
    
    /**
     * Reads the lines from a Reader.
     * 
     * Is this good?
     * Does not close (any?) stream!
     * Return type may change to List<String> in future!?
     * @param reader the reader
     * @throws java.io.IOException 
     * @return the lines read from the reader
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
    
    /**
     * Copies bytes from an InputStream to an OutputStream.
     * @param in the InputStream
     * @param out the OutputStream
     * @throws java.io.IOException 
     */
    public static void copy(InputStream in, OutputStream out) throws IOException{
        int c;
        
        while ((c = in.read()) != -1) {
            out.write(c);
        }
    }
}
