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
 * Action which shows VisualFieldView component.
 */
public class VisualFieldViewAction extends AbstractAction {

    public VisualFieldViewAction() {
        super(NbBundle.getMessage(VisualFieldViewAction.class, "CTL_VisualFieldViewAction"));
//        putValue(SMALL_ICON, new ImageIcon(Utilities.loadImage(VisualFieldViewTopComponent.ICON_PATH, true)));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = VisualFieldViewTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
