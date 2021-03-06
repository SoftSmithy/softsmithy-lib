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

package org.softsmithy.lib.util;

/**
 * An extended math library.
 *  <br><br>
 * Copyright:    Copyright (c) 2002
 *
 * @author Florian Brunner
 */

public class XMath {
    
    private static final double MAX_ANGLE = 2 * Math.PI;
    
    /**
     * No public constructor provided.
     */
    private XMath() { }
    
    /**
     * Trims an angle measured in radians to the standard interval [0, 2*PI]
     * (0 inclusive, 2*PI exclusive).
     *
     * @param angrad  the angle in radians to be trimmed
     * @return        the trimmed angle in radians
     */
    public static double toStandardInterval(double angrad) {
        double angle = angrad;
        angle %= MAX_ANGLE;
        if (angle < 0) {
            angle += MAX_ANGLE;
        }
        return angle;
    }
    
    //TODO: Use varargs
    public static double sum(double[] summands){
        double sum = 0.0;
        if (summands != null){
            for (int i=0; i<summands.length; i++){ // faster than new for loop (?)
                sum += summands[i];
            }
        }
        return sum;
    }
}
