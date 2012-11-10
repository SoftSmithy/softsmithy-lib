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
 * FullZooming.java
 *
 * Created on 13. Mai 2004, 00:26
 */
package org.softsmithy.lib.swing.icon;

import java.awt.*;

/**
 *
 * @author puce TODO: CHeck name
 */
public class FullZooming implements ZoomingStrategy {

    /*
     * TODO: Check: Really use static instances?
     */
    public static final FullZooming RESPECTING_ASPECT_RATIO_INSTANCE = new FullZooming();
    public static final FullZooming NON_RESPECTING_ASPECT_RATIO_INSTANCE = new FullZooming(false);
    /**
     * Holds value of property respectingAspectRatio.
     */
    private boolean respectingAspectRatio;

    /**
     * Creates a new instance of FullZooming
     */
    private FullZooming() {
        this(true);
    }

    private FullZooming(boolean respectingAspectRatio) {
        setRespectingAspectRatio(respectingAspectRatio);
    }

    @Override
    public Dimension calculateDimension(int iconWidth, int iconHeight, int innerAreaWidth, int innerAreaHeight) {
        Dimension bounds;
        if (isRespectingAspectRatio()) {
            if (((double) innerAreaWidth) / iconWidth <= ((double) innerAreaHeight) / iconHeight) {
                bounds = new Dimension(innerAreaWidth, -1); //(int) Math.round(((double) width * height) / getIcon().getIconWidth()));
            } else {
                bounds = new Dimension(-1, innerAreaHeight); //(int) Math.round(((double) width * height) / getIcon().getIconHeight()), height);
            }
        } else {
            bounds = new Dimension(innerAreaWidth, innerAreaHeight);
        }
        return bounds;
    }

    /**
     * Getter for property respectingAspectRatio.
     *
     * @return Value of property respectingAspectRatio.
     */
    public boolean isRespectingAspectRatio() {
        return this.respectingAspectRatio;
    }

    /**
     * Setter for property respectingAspectRatio.
     *
     * @param respectingAspectRatio New value of property respectingAspectRatio.
     */
    private void setRespectingAspectRatio(boolean respectingAspectRatio) {
        this.respectingAspectRatio = respectingAspectRatio;
    }
}
