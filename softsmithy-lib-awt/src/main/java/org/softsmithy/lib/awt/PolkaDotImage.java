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
 * PolkaDot.java
 *
 * Created on 11. Februar 2003, 18:39
 */

package org.softsmithy.lib.awt;

import java.awt.*;
import java.awt.geom.*;

/**
 *
 * @author  puce
 */
class PolkaDotImage extends ShapeImage{
  
  /** Creates a new instance of PolkaDot */
  public PolkaDotImage(int radius, int size, Color circleColor, Color backgroundColor) {
    super(new Ellipse2D.Float(size/2-radius, size/2-radius, 2*radius, 2*radius), circleColor, true, null);
  }

}

