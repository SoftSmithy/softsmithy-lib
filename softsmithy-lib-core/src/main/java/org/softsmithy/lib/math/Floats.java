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



package org.softsmithy.lib.math;


/**
 *
 * @author  puce
 */
public class Floats {
  

    /**
     * The IEEE754 format is symmetric with respect to the sign, so the negative float
     * value with the largest finite absolute value is equal to -Float.MAX_VALUE.
     */
  public static final float MAX_NEGATIVE_VALUE = -Float.MAX_VALUE;
    /**
     * The IEEE754 format is symmetric with respect to the sign, so the negative float
     * value with the smallest nonzero absolute value is equal to -Float.MIN_VALUE.
     */
  public static final float MIN_NEGATIVE_VALUE = -Float.MIN_VALUE;
  
  /** Creates a new instance of BigDecimals */
  private Floats() {
  }
  
  
}
