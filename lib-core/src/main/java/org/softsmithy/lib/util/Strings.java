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
        StringBuilder string = new StringBuilder();
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
    
    /**
     * Cuts a string to the specified length.
     * @param s the string to cut
     * @param length the maximal length of the string
     * @return if the string is smaller or equal to the specified length,
     * the string will just be returned.
     * Otherwise the first n characters of the string will be returned,
     * where n is equal to the specified length.
     */
    // TODO good name? better 'substring'? Or 'left' and 'right'? only length parameter? 
    public static String cut(String s, int length){
        return s.length() <= length ? s : s.substring(0, length);
    }
    
}
