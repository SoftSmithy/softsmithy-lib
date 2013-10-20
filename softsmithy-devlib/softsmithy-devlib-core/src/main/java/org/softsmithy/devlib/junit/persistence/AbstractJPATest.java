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
package org.softsmithy.devlib.junit.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.softsmithy.devlib.persistence.DbInitializer;
import org.softsmithy.lib.persistence.ResourceLocalTransactionController;

/**
 *
 * @author puce
 */
public abstract class AbstractJPATest<T extends DbInitializer> {

    private ResourceLocalTransactionController transactionController;
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
        setUpBeforeInitDb();
        initDb();
        newTransaction();
    }

    protected void setUpBeforeInitDb() {
        transactionController =
                new ResourceLocalTransactionController(getPersistenceUnitName());
        dbInitializer = createNewDbInitializer();
    }

    @After
    public void tearDown() throws Exception {
        clearDb();
        tearDownAfterClearDb();
    }

    protected void tearDownAfterClearDb() {
        if (transactionController != null) {
            transactionController.close();
        }
    }

    protected abstract String getPersistenceUnitName();

    protected abstract T createNewDbInitializer();

    protected void initDb() {
        dbInitializer.initDb();
    }

    protected void clearDb() throws Exception {
        if (dbInitializer != null) {
            dbInitializer.clearDb();
        }
    }

    /**
     * @return the dbInitializer
     */
    public T getDbInitializer() {
        return dbInitializer;
    }

    protected EntityManager getEntityManager() {
        return transactionController.getEntityManager();
    }

    protected EntityManagerFactory getEntityManagerFactory() {
        return transactionController.getEntityManagerFactory();
    }

    protected void newTransaction() {
        transactionController.newTransaction();
    }
}
