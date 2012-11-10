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

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * https://tips4java.wordpress.com/2008/11/18/row-number-table/
 *	Use a JTable as a renderer for row numbers of a given main table.
 *  This table must be added to the row header of the scrollpane that
 *  contains the main table.
 */
public class JRowNumberHeaderTable extends JRowHeaderTable {

    public JRowNumberHeaderTable(JTable mainTable) {
        super(mainTable, new RowNumberTableModel());
        getColumnModel().getColumn(0).setPreferredWidth(50);
        setPreferredScrollableViewportSize(getPreferredSize());
    }

    @Override
    public TableCellRenderer getDefaultRenderer(Class columnClass) {
        return getTableHeader().getDefaultRenderer();
    }

    private static class RowNumberTableModel extends AbstractRowHeaderTableModel {

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return rowIndex + 1;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return Integer.class;
        }

        @Override
        public String getColumnName(int column) {
            return " ";
        }
    }
}
