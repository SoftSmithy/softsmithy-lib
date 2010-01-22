/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.devlib.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author puce
 */
public interface DbInitializer {

    void initDb();

    void clearDb();

    EntityManagerFactory getEntityManagerFactory();

    /**
     * @return the entityManager
     */
    EntityManager getEntityManager();

    void close();
}
