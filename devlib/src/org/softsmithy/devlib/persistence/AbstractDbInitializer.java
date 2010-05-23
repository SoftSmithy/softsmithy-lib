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

package org.softsmithy.devlib.persistence;

import javax.persistence.EntityManager;

/**
 *
 * @author puce
 */
public abstract class AbstractDbInitializer implements DbInitializer {

    private final EntityManager entityManager;

    public AbstractDbInitializer(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * @return the entityManager
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

}
