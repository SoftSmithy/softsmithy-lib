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
 * XImageIcon.java
 *
 * Created on 24. M�rz 2004, 00:43
 */
package org.softsmithy.lib.swing.icon;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import org.softsmithy.lib.awt.image.ScalingAlgorithm;

/**
 *
 * @author puce
 */
public class XImageIcon extends ImageIcon implements XIcon {

    /**
     * Holds value of property scalingAlgorithm.
     */
    private ScalingAlgorithm scalingAlgorithm = ScalingAlgorithm.DEFAULT;

    /**
     * Creates a new instance of XImageIcon
     */
    public XImageIcon() {
    }

    public XImageIcon(byte[] imageData) {
        super(imageData);
    }

    public XImageIcon(byte[] imageData, String description) {
        super(imageData, description);
    }

    public XImageIcon(Image image) {
        super(image);
    }

    public XImageIcon(Image image, String description) {
        super(image, description);
    }

    public XImageIcon(String filename) {
        super(filename);
    }

    public XImageIcon(String filename, String description) {
        super(filename, description);
    }

    public XImageIcon(URL location) {
        super(location);
    }

    public XImageIcon(URL location, String description) {
        super(location, description);
    }

    @Override
    public XIcon getScaledInstance(int newWidth, int newHeight) {
        return new XImageIcon(
                getImage().getScaledInstance(newWidth, newHeight, getScalingAlgorithm().getImageConstant()));
    }

    /**
     * Getter for property scalingAlgorithm.
     *
     * @return Value of property scalingAlgorithm.
     *
     */
    public ScalingAlgorithm getScalingAlgorithm() {
        return this.scalingAlgorithm;
    }

    /**
     * Setter for property scalingAlgorithm.
     *
     * @param scalingAlgorithm New value of property scalingAlgorithm.
     *
     */
    public void setScalingAlgorithm(ScalingAlgorithm scalingAlgorithm) {
        this.scalingAlgorithm = scalingAlgorithm;
    }
}
