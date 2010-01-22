/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.devlib.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

/**
 *
 * @author puce
 */
public abstract class AbstractDbInitializer implements DbInitializer {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public AbstractDbInitializer(String persistenceUnitName) {
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

    /**
     * @return the entityManagerFactory
     */
    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    /**
     * @return the entityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void close() {
        try {
            entityManager.getTransaction().commit();
        } catch (RollbackException e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
//            if (entityManager.getTransaction().isActive()) {
//                entityManager.getTransaction().rollback();
//            }
            entityManager.close();
        }
    }


}
