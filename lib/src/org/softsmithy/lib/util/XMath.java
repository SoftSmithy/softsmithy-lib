package puce.util;

/**
 * An extended math library.
 *  <br><br>
 * Copyright:    Copyright (c) 2002
 *
 * @author Florian Brunner
 */

public class XMath {

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
    double maxAngle = 2 * Math.PI;
    double angle = angrad;
    angle %= maxAngle;
    if (angle < 0) {
      angle += maxAngle;
    }
    return angle;
  }
}
