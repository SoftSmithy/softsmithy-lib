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
