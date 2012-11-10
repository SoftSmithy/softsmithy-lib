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
 * XAbstractTableModel.java
 *
 * Created on 26. September 2002, 12:17
 */
package org.softsmithy.lib.swing.table;

import javax.swing.table.*;

/**
 *
 * @author  puce
 */
public abstract class AbstractCellTableModel extends AbstractTableModel implements CellTableModel {

    /** Creates a new instance of XAbstractTableModel */
    public AbstractCellTableModel() {
    }

    @Override
    public Class<?> getCellClass(int row, int column) {
        return getValueAt(row, column).getClass();
    }

    /**  Returns <code>Object.class</code> regardless of <code>columnIndex</code>.
     *
     *  @param columnIndex  the column being queried
     *  @return the Object.class
     *
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getCellClass(0, columnIndex);
    }
}
