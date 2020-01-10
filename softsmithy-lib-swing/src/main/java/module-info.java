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
