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

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author puce
 */
public abstract class AbstractRowHeaderTableModel extends AbstractTableModel
        implements RowHeaderTableModel {

    private final WrappedTableModelListener tableModelListener =
            new WrappedTableModelListener();
    private TableModel tableModel;

    @Override
    public int getRowCount() {
        return getTableModel() != null ? getTableModel().getRowCount() : 0;
    }

    @Override
    public TableModel getTableModel() {
        return tableModel;
    }

    @Override
    public void setTableModel(TableModel tableModel) {
        if (this.tableModel != null) {
            tableModel.removeTableModelListener(tableModelListener);
        }
        this.tableModel = tableModel;
        if (this.tableModel != null) {
            tableModel.addTableModelListener(tableModelListener);
        }
    }

    private class WrappedTableModelListener implements TableModelListener {

        @Override
        public void tableChanged(TableModelEvent e) {
            fireTableChanged(new TableModelEvent(
                    AbstractRowHeaderTableModel.this, e.getFirstRow(), e.
                    getLastRow(), e.getColumn(),
                    e.getType()));
        }
    }
}
