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

/**
 * A JUnit 5 utility library for testing JPA classes.
 */
module org.softsmithy.devlib.junit.persistence {
    exports org.softsmithy.devlib.junit.persistence;

    requires org.softsmithy.lib.persistence;
    requires org.softsmithy.devlib.persistence;
    requires org.junit.jupiter.api;
}
