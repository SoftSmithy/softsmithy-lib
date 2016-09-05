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
package org.softsmithy.lib.util;

/**
 * A provider for a unique key. Once a non-null unique key is returned, the key must not change!
 *
 * @author puce
 * @param <T> the type of the unique key
 */
@FunctionalInterface
public interface UniqueKeyProvider<T> {

    /**
     * Gets a unique key. Once a non-null unique key is returned, the key must not change!
     *
     * @return a unique key
     */
    T getUniqueKey();
}
