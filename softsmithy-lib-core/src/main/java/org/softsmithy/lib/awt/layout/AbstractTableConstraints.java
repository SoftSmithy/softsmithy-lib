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
 * AbstractTableConstraints.java
 *
 * Created on 10. Juli 2003, 16:15
 */
package org.softsmithy.lib.awt.layout;

import java.awt.*;

/**
 *
 * @author  puce
 */
public abstract class AbstractTableConstraints implements TableConstraints {

    /** Holds value of property tableLayout. */
    private final TableLayout tableLayout;
    /** Holds value of property component. */
    private final Component component;

    /** Creates a new instance of AbstractTableConstraints */
    public AbstractTableConstraints(Component component, TableLayout tl) {
        this.component = component;
        this.tableLayout = tl;
    }

    /** Getter for property tableLayout.
     * @return Value of property tableLayout.
     *
     */
    public TableLayout getTableLayout() {
        return this.tableLayout;
    }

    @Override
    public void setAbsoluteBounds(Rectangle bounds) {
        setAbsoluteBoundsOnly(bounds);
        updateTableLayout();
    }

    @Override
    public void setRelativeBounds(Rectangle bounds) {
        setRelativeBoundsOnly(bounds);
        updateTableLayout();
    }

    /** Getter for property component.
     * @return Value of property component.
     *
     */
    public Component getComponent() {
        return this.component;
    }

    protected void updateTableLayout() {
//    if (getTableLayout().getTableConstraints(getComponent()) == this){
//      getTableLayout().setTableConstraints(getComponent(), this);
//    }
    }

    protected abstract void setAbsoluteBoundsOnly(Rectangle bounds);

    protected abstract void setRelativeBoundsOnly(Rectangle bounds);
}
