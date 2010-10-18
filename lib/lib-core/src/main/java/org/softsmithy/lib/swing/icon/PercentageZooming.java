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
 * PercentageZooming.java
 *
 * Created on 13. Mai 2004, 00:38
 */

package org.softsmithy.lib.swing.icon;

import java.awt.*;

/**
 *
 * @author  puce
 * TODO: Check name
 */
public class PercentageZooming implements ZoomingStrategy{
  
  /**
   * Holds value of property percentage.
   */
  private double percentage;
  
  /** Creates a new instance of PercentageZooming */
  public PercentageZooming() {
    this(1.0);
  }
  
  public PercentageZooming(double percentage) {
    setPercentage(percentage);
  }
  
  public Dimension calculateDimension(int iconWidth, int iconHeight, int innerAreaWidth, int innerAreaHeight) {
    return new Dimension((int) Math.round(getPercentage() * iconWidth), (int) Math.round(getPercentage() * iconHeight));
  }
  
  /**
   * Getter for property percentage.
   * @return Value of property percentage.
   */
  public double getPercentage() {
    return this.percentage;
  }
  
  /**
   * Setter for property percentage.
   * @param percentage New value of property percentage.
   */
  public void setPercentage(double percentage) {
    if (percentage <= 0.0){
      throw new IllegalArgumentException("Percentage must be grater zero!");
    }
    this.percentage = percentage;
  }
  
}
