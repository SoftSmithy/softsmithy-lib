package puce.util;

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
}
