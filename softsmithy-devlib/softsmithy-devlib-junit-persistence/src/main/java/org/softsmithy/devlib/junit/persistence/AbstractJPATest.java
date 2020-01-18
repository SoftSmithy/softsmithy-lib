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
package org.softsmithy.devlib.junit.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.softsmithy.devlib.persistence.DbInitializer;
import org.softsmithy.lib.persistence.ResourceLocalTransactionController;

/**
 * A base class for JPA tests.
 *
 * @author puce
 * @param <T> the {@link DbInitializer} type
 */
public abstract class AbstractJPATest<T extends DbInitializer> {

    private ResourceLocalTransactionController transactionController;
    private T dbInitializer;

    @BeforeEach
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

    @AfterEach
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
