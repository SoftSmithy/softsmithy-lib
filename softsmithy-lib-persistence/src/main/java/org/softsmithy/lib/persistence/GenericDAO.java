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

package org.softsmithy.lib.persistence;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import javax.persistence.EntityManager;

/**
 *
 * @author puce
 * @param <T> the entity bean type
 * @param <ID> the id type
 */
public abstract class GenericDAO<T, ID extends Serializable> {

    private final Class<T> entityBeanType;
    private final EntityManager entityManager;

    public GenericDAO(Class<T> entityBeanType, EntityManager entityManager) {
        this.entityBeanType = entityBeanType;
        this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
    public GenericDAO(EntityManager entityManager) {
        this.entityBeanType = (Class<T>) ((ParameterizedType) getClass().
                getGenericSuperclass()).getActualTypeArguments()[0];
        this.entityManager = entityManager;
    }

    /**
     * @return the entityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * @return the entityBeanType
     */
    public Class<T> getEntityBeanType() {
        return entityBeanType;
    }

    public T findById(ID id) {
        return entityManager.find(entityBeanType, id);
    }

    public T makePersistent(T entity) {
        return entityManager.merge(entity);
    }

    public void makeTransient(T entity) {
        entityManager.remove(entity);
    }

    public int deleteAllEntities() {
        return entityManager.createQuery("delete from " + entityBeanType.
                getSimpleName() + " e").executeUpdate();
    }
}
