/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.vep;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;

/**
 * Action which shows VisualFieldController component.
 */
public class VisualFieldControllerAction extends AbstractAction {

    public VisualFieldControllerAction() {
        super(NbBundle.getMessage(VisualFieldControllerAction.class, "CTL_VisualFieldControllerAction"));
//        putValue(SMALL_ICON, new ImageIcon(Utilities.loadImage(VisualFieldControllerTopComponent.ICON_PATH, true)));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = VisualFieldControllerTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
