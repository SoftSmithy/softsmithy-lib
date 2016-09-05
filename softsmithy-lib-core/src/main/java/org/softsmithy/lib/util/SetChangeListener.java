/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (GitHub user: puce77). All Rights Reserved.
 *
 * Contributor(s): .
 */
package org.softsmithy.lib.util;

import java.util.EventListener;
import java.util.Set;

/**
 * A listener for observable {@link Set}s.
 *
 * @author puce
 * @param <E> the type of the elements of the set
 */
public interface SetChangeListener<E> extends EventListener {

    /**
     * This method gets called when an element was added to the observed set.
     *
     * @param event the change event
     */
    void elementAdded(SetChangeEvent<E> event);

    /**
     * This method gets called when an element was removed from the observed set.
     *
     * @param event the change event
     */
    void elementRemoved(SetChangeEvent<E> event);
}
