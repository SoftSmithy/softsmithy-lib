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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JTable;
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

        setSelectionModel(mainTable.getSelectionModel());
        rowHeaderTableModel.setTableModel(mainTable.getModel());

        setPreferredScrollableViewportSize(getPreferredSize());
    }

//    @Override
//    public void addNotify() {
//        super.addNotify();
//
//        Component c = getParent();
//
//        //  Keep scrolling of the row table in sync with the main table.
//
//        if (c instanceof JViewport) {
//            JViewport viewport = (JViewport) c;
//            viewport.addChangeListener(new ChangeListener() {
//
//                @Override
//                public void stateChanged(ChangeEvent e) {
//                    //  Keep the scrolling of the row table in sync with main table
//
//                    JViewport viewport = (JViewport) e.getSource();
//                    JScrollPane scrollPane = (JScrollPane) viewport.getParent();
//                    scrollPane.getVerticalScrollBar().setValue(viewport.
//                            getViewPosition().y);
//                }
//            });
//        }
//    }

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
}
