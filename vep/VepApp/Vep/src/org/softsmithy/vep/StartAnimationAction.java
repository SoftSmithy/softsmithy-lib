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
public class StartAnimationAction extends ContextAction<StartAnimation>{

    public StartAnimationAction() {
        this(VisualFieldViewTopComponent.findInstance().getLookup());
        MediaActionFactory.PLAY.configureXAction(this, Locale.getDefault());
    }
    public StartAnimationAction(Lookup context) {
        super(context, StartAnimation.class);
    }

    @Override
    protected void performAction(StartAnimation context) {
        context.startAnimation();
    }

    public Action createContextAwareInstance(Lookup context) {
        return new StartAnimationAction(context);
    }

}
