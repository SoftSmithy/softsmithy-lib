/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.lib.util;

/**
 *
 * @author puce
 * @param <E>
 */
public interface SetChangeListener<E> {

    void elementAdded(SetChangeEvent<E> event);

    void elementRemoved(SetChangeEvent<E> event);
}
