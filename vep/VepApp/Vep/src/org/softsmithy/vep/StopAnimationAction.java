/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.softsmithy.vep;

import java.util.Locale;
import javax.swing.Action;
import org.openide.util.Lookup;
import org.softsmithy.lib.swing.action.MediaActionFactory;

/**
 *
 * @author puce
 */
public class StopAnimationAction extends ContextAction<StopAnimation>{

    public StopAnimationAction() {
        this(VisualFieldViewTopComponent.findInstance().getLookup());
        MediaActionFactory.STOP.configureXAction(this, Locale.getDefault());
    }
    public StopAnimationAction(Lookup context) {
        super(context, StopAnimation.class);
    }

    @Override
    protected void performAction(StopAnimation context) {
        context.stopAnimation();
    }

    public Action createContextAwareInstance(Lookup context) {
        return new StopAnimationAction(context);
    }

}
