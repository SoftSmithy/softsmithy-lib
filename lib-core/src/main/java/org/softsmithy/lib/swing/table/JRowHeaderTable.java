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

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.softsmithy.lib.swing.JXTable;

/**
 * https://tips4java.wordpress.com/2008/11/18/row-number-table/
 */
public class JRowHeaderTable extends JXTable {

    private final JTable mainTable;
    private final RowHeaderTableModel rowHeaderTableModel;

    public JRowHeaderTable(final JTable mainTable,
            final RowHeaderTableModel rowHeaderTableModel) {
        super(rowHeaderTableModel);

        this.mainTable = mainTable;
        this.rowHeaderTableModel = rowHeaderTableModel;
        mainTable.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent e) {
                //  Keep the row table in sync with the main table

                if ("selectionModel".equals(e.getPropertyName())) {
                    setSelectionModel(mainTable.getSelectionModel());
                }

                if ("model".equals(e.getPropertyName())) {
                    rowHeaderTableModel.setTableModel(mainTable.getModel());
                }
            }
        });

        setFocusable(false);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //setAutoCreateColumnsFromModel(false);
        //setModel(main.getModel());
        setSelectionModel(mainTable.getSelectionModel());
        rowHeaderTableModel.setTableModel(mainTable.getModel());

//        TableColumn column = new TableColumn();
//        column.setHeaderValue(" ");
//        addColumn(column);
        // column.setCellRenderer(new RowNumberRenderer());

        setPreferredScrollableViewportSize(getPreferredSize());
    }

    @Override
    public void addNotify() {
        super.addNotify();

        Component c = getParent();

        //  Keep scrolling of the row table in sync with the main table.

        if (c instanceof JViewport) {
            JViewport viewport = (JViewport) c;
            viewport.addChangeListener(new ChangeListener() {

                @Override
                public void stateChanged(ChangeEvent e) {
                    //  Keep the scrolling of the row table in sync with main table

                    JViewport viewport = (JViewport) e.getSource();
                    JScrollPane scrollPane = (JScrollPane) viewport.getParent();
                    scrollPane.getVerticalScrollBar().setValue(viewport.
                            getViewPosition().y);
                }
            });
        }
    }

    /*
     *  Delegate method to main table
     */
    @Override
    public int getRowHeight(int row) {
        return getMainTable().getRowHeight(row);
    }

    /**
     * @return the mainTable
     */
    public JTable getMainTable() {
        return mainTable;
    }

    /**
     * @return the rowHeaderTableModel
     */
    public RowHeaderTableModel getRowHeaderTableModel() {
        return rowHeaderTableModel;
    }
//
//  Implement the ChangeListener
//
//
//  Implement the PropertyChangeListener
//
    /*
     *  Borrow the renderer from JDK1.4.2 table header
     */
//    private static class RowNumberRenderer extends DefaultTableCellRenderer {
//
//        public RowNumberRenderer() {
//            setHorizontalAlignment(JLabel.CENTER);
//        }
//
//        public Component getTableCellRendererComponent(
//                JTable table, Object value, boolean isSelected, boolean hasFocus,
//                int row, int column) {
//            if (table != null) {
//                JTableHeader header = table.getTableHeader();
//
//                if (header != null) {
//                    setForeground(header.getForeground());
//                    setBackground(header.getBackground());
//                    setFont(header.getFont());
//                }
//            }
//
//            if (isSelected) {
//                setFont(getFont().deriveFont(Font.BOLD));
//            }
//
//            setText((value == null) ? "" : value.toString());
//            setBorder(UIManager.getBorder("TableHeader.cellBorder"));
//
//            return this;
//        }
//    }
}
