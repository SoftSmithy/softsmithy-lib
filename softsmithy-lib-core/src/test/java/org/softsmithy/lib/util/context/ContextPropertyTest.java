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
package org.softsmithy.lib.util.context;

import org.junit.jupiter.api.Test;
import org.softsmithy.lib.util.concurrent.ThreadLocalStorage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ContextPropertyTest {

    private final ContextProperty<Path> testee = new ContextProperty<>("foo", Path.class);

    @Test
    public void getValue_setValue() {
        Path propertyValue = Paths.get("bar");
        Map<String, Object> someContext = new HashMap<>();
        someContext.put(testee.getName(), propertyValue);
        
        Path result1 = testee.getValue(someContext::get);
        testee.setValue(ThreadLocalStorage::setPropertyValue, result1);
        Path result2 = testee.getValue(ThreadLocalStorage::getPropertyValue);
        
        assertEquals(propertyValue, result2);
    }

    @Test
    public void validProperty_removeProperty_propertyRemoved() {
        Path propertyValue = Paths.get("bar");
        Map<String, Object> someContext = new HashMap<>();
        someContext.put(testee.getName(), propertyValue);
        
        Path result = testee.getValue(someContext::remove);
        assertEquals(propertyValue, result);
        assertFalse(someContext.containsKey(testee.getName()));
    }

}
