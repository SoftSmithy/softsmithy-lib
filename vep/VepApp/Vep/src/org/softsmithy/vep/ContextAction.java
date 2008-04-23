/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.vep;

import java.awt.event.ActionEvent;
import org.openide.util.ContextAwareAction;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.softsmithy.lib.swing.action.AbstractXAction;

/**
 *
 * @author puce
 */
public abstract class ContextAction<T>
        extends AbstractXAction
        implements ContextAwareAction {

    private final Lookup context;
    private final Lookup.Result<T> result;

    public ContextAction(Lookup context, Class<T> contextClass) {
        this.context = context;
        result = context.lookupResult(contextClass);
        result.addLookupListener(new LookupListener() {

            public void resultChanged(LookupEvent arg0) {
                setState();
            }
        });
        setState();
    }

    private void setState() {
        setEnabled(!result.allItems().isEmpty());
    }

    public void actionPerformed(ActionEvent arg0) {
        performAction(result.allInstances().iterator().next());
    }

    protected abstract void performAction(T context);

}
