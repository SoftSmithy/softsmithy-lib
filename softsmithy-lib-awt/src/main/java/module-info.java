module org.softsmithy.lib.awt {
    exports org.softsmithy.lib.awt;
    exports org.softsmithy.lib.awt.event;
    exports org.softsmithy.lib.awt.image;
    
    requires transitive java.desktop;
    requires org.softsmithy.lib.core;
    requires org.slf4j;
}
