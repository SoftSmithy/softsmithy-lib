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
 * XIcons.java
 *
 * Created on 18. Juni 2006, 19:15
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.softsmithy.lib.swing.icon;

import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 *
 * @author florian.brunner
 */
public class XIcons {
    
    /** Creates a new instance of XIcons */
    private XIcons() {
    }
    
    public  static XIcon calculateScaledIcon(XIcon icon, 
            ZoomingStrategy zoomingStrategy, JComponent component){
        Rectangle innerArea = SwingUtilities.calculateInnerArea(component, null);
        if (icon != null && innerArea.width > 0 && innerArea.height > 0){
            //      Dimension bounds = calculateIconBounds(innerArea.width, innerArea.height);
            Dimension bounds = zoomingStrategy.calculateDimension(icon.getIconWidth(),
                    icon.getIconHeight(), innerArea.width, innerArea.height);
            return icon.getScaledInstance(bounds.width, bounds.height);
            
        } else {
            return null;
        }
    }
}
