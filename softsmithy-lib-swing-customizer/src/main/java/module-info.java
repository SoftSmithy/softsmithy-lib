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

module org.softsmithy.lib.swing.customizer {
    exports org.softsmithy.lib.swing.customizer;
    exports org.softsmithy.lib.swing.customizer.action;
    exports org.softsmithy.lib.swing.customizer.border;
    exports org.softsmithy.lib.swing.customizer.event;
    exports org.softsmithy.lib.swing.customizer.layout;
    exports org.softsmithy.lib.swing.customizer.state;
    exports org.softsmithy.lib.swing.customizer.style;
    exports org.softsmithy.lib.swing.customizer.table;

    requires org.softsmithy.lib.beans;
    requires transitive org.softsmithy.lib.swing;
    requires org.slf4j;

}
