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
package org.softsmithy.lib.util.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ThreadLocalStorageTest {

    @Test
    public void setPropertyValue_getPropertyValue() {
        String propertyName = "foo";
        String propertyValue = "bar";
        ThreadLocalStorage.setPropertyValue(propertyName, propertyValue);
        Object result = ThreadLocalStorage.getPropertyValue(propertyName);
        assertEquals(propertyValue, (String) result);
    }

    @Test
    public void setPropertyValue_removeProperty() {
        String propertyName = "foo";
        String propertyValue = "bar";
        ThreadLocalStorage.setPropertyValue(propertyName, propertyValue);
        Object result = ThreadLocalStorage.removeProperty(propertyName);
        assertEquals(propertyValue, (String) result);
    }

}
