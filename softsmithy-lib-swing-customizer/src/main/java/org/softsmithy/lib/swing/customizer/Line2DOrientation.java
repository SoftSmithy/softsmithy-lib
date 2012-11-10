/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

/*
 * Line2DOrientation.java
 *
 * Created on 13. November 2002, 15:43
 */
package org.softsmithy.lib.swing.customizer;

import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.softsmithy.lib.util.TypesafeEnum;

/**
 *
 * @author puce
 */
public abstract class Line2DOrientation extends TypesafeEnum {

    private static final String BASE_NAME = "org.softsmithy.lib.swing.customizer.Line2DOrientation";

    /**
     * Creates a new instance of Line2DOrientation
     */
    private Line2DOrientation(String name) {
        super(name);
    }

    @Override
    public String getResourceBundleBaseName() {
        return BASE_NAME;
    }

    public abstract Line2D getLine2D();
    public static final Line2DOrientation HORIZONTAL = new Line2DOrientation("horizontal") {
        @Override
        public Line2D getLine2D() {
            return new Line2D.Double(0, 0, 1, 0);
        }
    };
    public static final Line2DOrientation VERTICAL = new Line2DOrientation("vertical") {
        @Override
        public Line2D getLine2D() {
            return new Line2D.Double(0, 0, 0, 1);
        }
    };
    private static final Line2DOrientation[] PRIVATE_VALUES = {HORIZONTAL, VERTICAL};
    public static final List<Line2DOrientation> VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
}
