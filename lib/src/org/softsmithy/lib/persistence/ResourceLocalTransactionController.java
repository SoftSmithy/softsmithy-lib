/*
 *                  Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */
package org.softsmithy.lib.persistence;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

/**
 *
 * @author puce
 */
public class ResourceLocalTransactionController {

    private static Logger logger = Logger.getLogger(ResourceLocalTransactionController.class.
            getPackage().getName());
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public ResourceLocalTransactionController(String persistenceUnitName) {
                //                    Map<String, String> configOverrides = new HashMap<String, String>();
        //            configOverrides.put("javax.persistence.transaction",
        //                    PersistenceUnitTransactionType.RESOURCE_LOCAL.name());
        //            configOverrides.put(TopLinkProperties.JDBC_DRIVER,
        //                    ClientDriver.class.getName());
        //            configOverrides.put(TopLinkProperties.JDBC_URL,
        //                    "jdbc:derby://localhost:1527/contactcenter");
        //            configOverrides.put(EntityManagerFactoryProvider.DDL_GENERATION,
        //                    EntityManagerFactoryProvider.DROP_AND_CREATE);
        entityManagerFactory = Persistence.createEntityManagerFactory(
                persistenceUnitName); //, configOverrides);
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    public void newTransaction() {
        try {
            commit();
        } finally {
            entityManager.getTransaction().begin();
            logger.info("Transaction started: Active: " + entityManager.
                    getTransaction().isActive());
        }
    }

    private void commit(){
         try {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().commit();
            }
        } catch (RollbackException e) {
            if (entityManager.getTransaction().isActive()) {
                // this should usually not be the case.
                // commit() will rollback itself before throwing the RollbackException!
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

     /**
     * TODO: good to commit on close?
     */
    public void close() {
        try {
           commit();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
