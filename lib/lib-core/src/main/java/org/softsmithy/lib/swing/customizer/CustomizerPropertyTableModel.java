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
 * PropertyTableModel.java
 *
 * Created on 19. September 2002, 18:11
 */
package org.softsmithy.lib.swing.customizer;

import java.util.*;
import org.softsmithy.lib.swing.*;
import org.softsmithy.lib.swing.event.*;

/**
 *
 * @author  puce
 */
public class CustomizerPropertyTableModel extends AbstractCustomizerPropertyTableModel implements CustomizerListener {

    /** Creates a new instance of PropertyTableModel */
    public CustomizerPropertyTableModel(List properties, JCustomizer activeCustomizer, String propertiesRBBaseName, Locale locale) {
        super(properties, activeCustomizer, propertiesRBBaseName, locale);
        if (activeCustomizer != null) {
            activeCustomizer.addCustomizerListener(this);
        }
    }

    public void customizerReshapeRel(CustomizerEvent e) {
        updateBounds();
    }

    public void customizerResetBoundsRel(CustomizerEvent e) {
        updateBounds();
    }

    private void updateBounds() {
        String[] bounds = {"x", "y", "width", "height"};
        for (int i = 0; i < bounds.length; i++) {
            int index = getPropertyNames().indexOf(bounds[i]);
            if (index >= 0) {
                fireTableCellUpdated(index, 1);
            }
        }
    }

    @Override
    public void stopListening() {
        super.stopListening();
        stopCustomizerListening();
    }

    private void stopCustomizerListening() {
        if (getActiveCustomizer() != null) {
            ((JCustomizer) getActiveCustomizer()).removeCustomizerListener(this);
        }
    }
}
