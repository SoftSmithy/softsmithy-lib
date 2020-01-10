
import javax.annotation.processing.Processor;
import org.softsmithy.lib.util.impl.ServiceProviderAnnotationProcessor;

module org.softsmithy.lib.core {
    exports org.softsmithy.lib.io;
    exports org.softsmithy.lib.lang.reflect;
    exports org.softsmithy.lib.math;
    exports org.softsmithy.lib.nio.file;
    exports org.softsmithy.lib.text;
    exports org.softsmithy.lib.util;
    exports org.softsmithy.lib.util.concurrent;
    exports org.softsmithy.lib.util.context;
    exports org.softsmithy.lib.util.zip;

    provides Processor with ServiceProviderAnnotationProcessor;

    requires java.compiler;
    requires org.slf4j;
}
