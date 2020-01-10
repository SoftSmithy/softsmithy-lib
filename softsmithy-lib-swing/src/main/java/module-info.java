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

module org.softsmithy.lib.swing {
    exports org.softsmithy.lib.swing;
    exports org.softsmithy.lib.swing.action;
    exports org.softsmithy.lib.swing.chooser;
    exports org.softsmithy.lib.swing.event;
    exports org.softsmithy.lib.swing.filechooser;
    exports org.softsmithy.lib.swing.icon;
    exports org.softsmithy.lib.swing.multisplit;
    exports org.softsmithy.lib.swing.style;
    exports org.softsmithy.lib.swing.table;
    exports org.softsmithy.lib.swing.text;
    
    exports org.softsmithy.lib.swing.internal to org.softsmithy.lib.swing.customizer;
    
    requires transitive java.desktop;
    requires org.softsmithy.lib.core;
    requires org.softsmithy.lib.beans;
    requires org.softsmithy.lib.awt;
    requires org.slf4j;
}
