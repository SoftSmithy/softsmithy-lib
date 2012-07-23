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
package org.softsmithy.lib.swing.table;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author puce
 * @see JRowHeaderTable
 */
public class DefaultTableRowHeaderController implements TableRowHeaderController {

    private final JTable rowHeaderTable;

    /**
     * Creates a new instance of this class.
     * @param rowHeaderTable a row header table
     * @see JRowHeaderTable
     */
    public DefaultTableRowHeaderController(JTable rowHeaderTable) {
        this.rowHeaderTable = rowHeaderTable;
    }

    @Override
    public void configureRowHeader(JScrollPane scrollPane) {
        scrollPane.setRowHeaderView(rowHeaderTable);
        scrollPane.setCorner(JScrollPane.UPPER_LEADING_CORNER,
                rowHeaderTable.getTableHeader());
    }

    @Override
    public void unconfigureRowHeader(JScrollPane scrollPane) {
        scrollPane.setRowHeaderView(null);
        scrollPane.setCorner(JScrollPane.UPPER_LEADING_CORNER, null);
    }
}
