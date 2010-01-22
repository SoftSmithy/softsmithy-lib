/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.devlib.junit;

import org.softsmithy.devlib.persistence.DbInitializer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author puce
 */
public abstract class AbstractJPATest<T extends DbInitializer> {

    private T dbInitializer;

    public AbstractJPATest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        dbInitializer = createNewDbInitializer();

        try {
            initDb();
            newTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @After
    public void tearDown() throws Exception {
        clearDb();
        dbInitializer.close();
    }

    protected abstract T createNewDbInitializer();

    protected void initDb() {
        dbInitializer.initDb();
    }

    protected void clearDb() throws Exception {
        dbInitializer.clearDb();
    }

    /**
     * @return the dbInitializer
     */
    public T getDbInitializer() {
        return dbInitializer;
    }

    protected EntityManager getEntityManager() {
        return dbInitializer.getEntityManager();
    }

    protected EntityManagerFactory getEntityManagerFactory() {
        return dbInitializer.getEntityManagerFactory();
    }

    protected void newTransaction() {
        try {
            if (getEntityManager().getTransaction().isActive()) {
                getEntityManager().getTransaction().commit();
            }
        } catch (RollbackException e) {
            getEntityManager().getTransaction().rollback();
            throw e;
        } finally {
            getEntityManager().getTransaction().begin();
            System.out.println("Transaction started: Active: " + getEntityManager().
                    getTransaction().isActive());
        }
    }
}
