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
 * LeadingCellRenderer.java
 *
 * Created on 11. September 2003, 19:51
 */
package org.softsmithy.lib.swing;

import javax.swing.*;

/**
 *
 * @author puce
 */
public abstract class AbstractCellRenderer<T> implements CellRenderer<T> {

    /**
     * Holds value of property horizontalAlignment.
     */
    private final HorizontalAlignment horizontalAlignment;

    public AbstractCellRenderer() {
        this(HorizontalAlignment.LEADING);
    }

    /**
     * Creates a new instance of LeadingCellRenderer
     * @param horizontalAlignment the horizontal alignment
     */
    public AbstractCellRenderer(HorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    /**
     * Getter for property horizontalAlignment.
     *
     * @return Value of property horizontalAlignment.
     *
     */
    @Override
    public HorizontalAlignment getHorizontalAlignment() {
        return this.horizontalAlignment;
    }

    @Override
    public Icon getIcon(T value) {
        return null;
    }
}
