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

import java.util.EventObject;
import java.util.Set;

/**
 *
 * @author puce
 * @param <E>
 */
public class SetChangeEvent<E> extends EventObject {

    private static final long serialVersionUID = 1474545101104526021L;

    private final Set<E> sourceSet;
    private final E element;

    /**
     *
     * @param source
     * @param element
     */
    public SetChangeEvent(Set< E> source, E element) {
        super(source);
        this.sourceSet = source;
        this.element = element;
    }

    /**
     * @return the sourceSet
     */
    public Set<? extends E> getSourceSet() {
        return sourceSet;
    }

    /**
     * @return the element
     */
    public E getElement() {
        return element;
    }

}
