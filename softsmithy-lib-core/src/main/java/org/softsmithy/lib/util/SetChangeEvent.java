/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
