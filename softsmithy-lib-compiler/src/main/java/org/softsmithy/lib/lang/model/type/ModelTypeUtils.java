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
package org.softsmithy.lib.lang.model.type;

import java.util.function.Supplier;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;

/**
 *
 * @author puce
 */
public class ModelTypeUtils {

    private ModelTypeUtils() {
    }

    public static TypeMirror getTypeMirror(Supplier<Class<?>> supplier) {
        TypeMirror typeMirror = null;
        try {
            supplier.get();
        } catch (MirroredTypeException mte) {
            typeMirror = mte.getTypeMirror();
        }
        return typeMirror;
    }
}
