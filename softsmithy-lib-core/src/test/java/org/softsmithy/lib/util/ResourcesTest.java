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

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 *
 * @author puce
 */
public class ResourcesTest {

    @Nested
    class ToModulePackageResourcePath_package {

        @Test
        public void package_resourcePath() {
            String resourcePath = Resources.toModulePackageResourcePath(ResourcesTest.class.getPackage());

            assertEquals("org/softsmithy/lib/util", resourcePath);
        }
    }

    @Nested
    class ToModulePackageResourcePath_packageName {

        @Test
        public void packageName_resourcePath() {
            String resourcePath = Resources.toModulePackageResourcePath("org.softsmithy.lib.util");

            assertEquals("org/softsmithy/lib/util", resourcePath);
        }
    }

    @Nested
    class GetPackageName {

        @Test
        public void fileResourcePath_packageName() {
            String packageName = Resources.getPackageName("org/softsmithy/lib/util/someFile.txt");

            assertEquals("org.softsmithy.lib.util", packageName);
        }

        @Test
        public void dirResourcePath_packageName() {
            String packageName = Resources.getPackageName("org/softsmithy/lib/util/");

            assertEquals("org.softsmithy.lib.util", packageName);
        }

        @Test
        public void fileInDefaultPackage_empty() {
            String packageName = Resources.getPackageName("someFile.txt");

            assertEquals("", packageName);

        }
    }

    @Nested
    class CheckPackageIsOpen {

        @Test
        public void fileInDefaultPackage_empty() {
            Optional<Module> javaBase = ModuleLayer.boot().findModule("java.base");

            assertThrows(ResourceAccessException.class, () -> Resources.checkPackageIsOpen("some/pacakge/some-resource.properties", javaBase.get(), ResourcesTest.class));
        }
    }
}
