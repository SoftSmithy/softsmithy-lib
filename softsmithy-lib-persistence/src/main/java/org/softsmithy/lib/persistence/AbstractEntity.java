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
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * A mapped base class for JPA entities with reasonable defaults:
 *
 * <ul>
 * <li>A generated technical id (surrogate PK) of type {@link Long} with the generation strategy set to {@link javax.persistence.GenerationType#AUTO}.</li>
 * <li>Since a PK should not change, there is no setter method for the id property.</li>
 * <li>Field access</li>
 * <li>implements Serializable</li>
 * </ul>
 *
 * @author puce
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    public static final String PRIMARY_GENERATOR = "PRIMARY_GENERATOR";

    /**
     * The technical id of this entity (surrogate PK).
     */
    @Id
    @GeneratedValue(generator = PRIMARY_GENERATOR)
    @Column(name = "ID")
    private Long id;

    /**
     * Gets the technical id of this entity (surrogate PK).
     *
     * @return the technical id of this entity (surrogate PK)
     */
    public Long getId() {
        return id;
    }
}
