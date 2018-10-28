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
package org.softsmithy.lib.swing;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import org.softsmithy.lib.lang.reflect.Classes;
import org.softsmithy.lib.swing.table.TableRowHeaderController;
import org.softsmithy.lib.swing.table.TypesafeEnumRenderer;
import org.softsmithy.lib.swing.table.XDefaultTableCellRenderer;
import org.softsmithy.lib.util.TypesafeEnum;


public class JXTable extends JTable {

    private TableRowHeaderController tableRowHeaderController;
    //
    // Constructors
    //

    /**
     * Constructs a default <code>JTable</code> that is initialized with a default
     * data model, a default column model, and a default selection
     * model.
     *
     * @see #createDefaultDataModel
     * @see #createDefaultColumnModel
     * @see #createDefaultSelectionModel
     */
    public JXTable() {
        super();
        init();
    }

    /**
     * Constructs a <code>JTable</code> that is initialized with
     * <code>dm</code> as the data model, a default column model,
     * and a default selection model.
     *
     * @param dm        the data model for the table
     * @see #createDefaultColumnModel
     * @see #createDefaultSelectionModel
     */
    public JXTable(TableModel dm) {
        super(dm);
        init();
    }

    /**
     * Constructs a <code>JTable</code> that is initialized with
     * <code>dm</code> as the data model, <code>cm</code>
     * as the column model, and a default selection model.
     *
     * @param dm        the data model for the table
     * @param cm        the column model for the table
     * @see #createDefaultSelectionModel
     */
    public JXTable(TableModel dm, TableColumnModel cm) {
        super(dm, cm);
        init();
    }

    /**
     * Constructs a <code>JTable</code> that is initialized with
     * <code>dm</code> as the data model, <code>cm</code> as the
     * column model, and <code>sm</code> as the selection model.
     * If any of the parameters are <code>null</code> this method
     * will initialize the table with the corresponding default model.
     * The <code>autoCreateColumnsFromModel</code> flag is set to false
     * if <code>cm</code> is non-null, otherwise it is set to true
     * and the column model is populated with suitable
     * <code>TableColumns</code> for the columns in <code>dm</code>.
     *
     * @param dm        the data model for the table
     * @param cm        the column model for the table
     * @param sm        the row selection model for the table
     * @see #createDefaultDataModel
     * @see #createDefaultColumnModel
     * @see #createDefaultSelectionModel
     */
    public JXTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
        super(dm, cm, sm);
        init();
    }

    /**
     * Constructs a <code>JTable</code> with <code>numRows</code>
     * and <code>numColumns</code> of empty cells using
     * <code>DefaultTableModel</code>.  The columns will have
     * names of the form "A", "B", "C", etc.
     *
     * @param numRows           the number of rows the table holds
     * @param numColumns        the number of columns the table holds
     * @see javax.swing.table.DefaultTableModel
     */
    public JXTable(int numRows, int numColumns) {
        super(numRows, numColumns);
        init();
    }

    /**
     * Constructs a <code>JTable</code> to display the values in the
     * <code>Vector</code> of <code>Vectors</code>, <code>rowData</code>,
     * with column names, <code>columnNames</code>.  The
     * <code>Vectors</code> contained in <code>rowData</code>
     * should contain the values for that row. In other words,
     * the value of the cell at row 1, column 5 can be obtained
     * with the following code:
     * {@code ((Vector)rowData.elementAt(1)).elementAt(5);}
     * 
     * @param rowData           the data for the new table
     * @param columnNames       names of each column
     */
    public JXTable(Vector rowData, Vector columnNames) {
        super(rowData, columnNames);
        init();
    }

    /**
     * Constructs a <code>JTable</code> to display the values in the two dimensional array,
     * <code>rowData</code>, with column names, <code>columnNames</code>.
     * <code>rowData</code> is an array of rows, so the value of the cell at row 1,
     * column 5 can be obtained with the following code:
     * {@code  rowData[1][5];}
     * <p>
     * All rows must be of the same length as <code>columnNames</code>.
     * </p>
     * 
     * @param rowData           the data for the new table
     * @param columnNames       names of each column
     */
    public JXTable(final Object[][] rowData, final Object[] columnNames) {
        super(rowData, columnNames);
        init();
    }

    private void init() {
        setDefaultRenderer(Color.class, new ColorCellRenderer(true));
        setDefaultRenderer(Font.class, new FontCellRenderer(true));
        setDefaultRenderer(Locale.class, new XDefaultTableCellRenderer(
                new LocaleCellRenderer()));
        setDefaultRenderer(Integer.class, new XDefaultTableCellRenderer(
                new WholeNumberCellRenderer()));
        setDefaultRenderer(Long.class, new XDefaultTableCellRenderer(
                new WholeNumberCellRenderer()));
        setDefaultRenderer(Short.class, new XDefaultTableCellRenderer(
                new WholeNumberCellRenderer()));
        setDefaultRenderer(Byte.class, new XDefaultTableCellRenderer(
                new WholeNumberCellRenderer()));
        setDefaultRenderer(BigInteger.class, new XDefaultTableCellRenderer(
                new WholeNumberCellRenderer()));
        setDefaultRenderer(Double.class, new XDefaultTableCellRenderer(
                new LocalizedRealNumberCellRenderer()));
        setDefaultRenderer(Float.class, new XDefaultTableCellRenderer(
                new LocalizedRealNumberCellRenderer()));
        setDefaultRenderer(BigDecimal.class, new XDefaultTableCellRenderer(
                new LocalizedRealNumberCellRenderer()));
        //setDefaultRenderer(HorizontalAlignment.class, new HorizontalAlignmentRenderer(getLocale()));
        setDefaultRenderer(TypesafeEnum.class, new TypesafeEnumRenderer(                getLocale()));

        setDefaultEditor(Color.class, new ColorCellEditor());
        setDefaultEditor(Font.class, new FontCellEditor());
        setDefaultEditor(Integer.class, new IntegerCellEditor(getLocale()));
        setDefaultEditor(Long.class, new LongCellEditor(getLocale()));
        setDefaultEditor(Short.class, new ShortCellEditor(getLocale()));
        setDefaultEditor(Byte.class, new ByteCellEditor(getLocale()));
        setDefaultEditor(BigInteger.class, new BigIntegerCellEditor(getLocale()));
        setDefaultEditor(Float.class, new LocalizedFloatCellEditor(getLocale()));
        setDefaultEditor(Double.class,
                new LocalizedDoubleCellEditor(getLocale()));
        setDefaultEditor(BigDecimal.class, new LocalizedBigDecimalCellEditor(
                getLocale()));
        setDefaultEditor(HorizontalAlignment.class, new HorizontalAlignmentCellEditor(
                getLocale()));
        setDefaultEditor(TypesafeEnum.class, new TypesafeEnumCellEditor(
                getLocale()));
    }

    // E.g. useable after locale has changed
    public void reinit() {
        init();
    }

    /** Returns the editor to be used when no editor has been set in
     * a <code>TableColumn</code>. During the editing of cells the editor is fetched from
     * a <code>Hashtable</code> of entries according to the class of the cells in the column. If
     * there is no entry for this <code>columnClass</code> the method returns
     * the entry for the most specific superclass. The <code>JTable</code> installs entries
     * for <code>Object</code>, <code>Number</code>, and <code>Boolean</code>, all of which can be modified
     * or replaced.
     *
     * @param   columnClass  return the default cell editor for this columnClass
     * @return the default cell editor to be used for this columnClass
     * @see     #setDefaultEditor
     * @see     #getColumnClass
     *
     */
    @Override
    public TableCellEditor getDefaultEditor(Class columnClass) {
        TableCellEditor editor = super.getDefaultEditor(columnClass);
        if (editor == null && columnClass != null && columnClass.isPrimitive()) {
            editor =                    super.getDefaultEditor(Classes.getWrapperClass(columnClass));
        }
        return editor;
    }

    /** Returns the cell renderer to be used when no renderer has been set in
     * a <code>TableColumn</code>. During the rendering of cells the renderer is fetched from
     * a <code>Hashtable</code> of entries according to the class of the cells in the column. If
     * there is no entry for this <code>columnClass</code> the method returns
     * the entry for the most specific superclass. The <code>JTable</code> installs entries
     * for <code>Object</code>, <code>Number</code>, and <code>Boolean</code>, all of which can be modified
     * or replaced.
     *
     * @param   columnClass   return the default cell renderer
     * 			      for this columnClass
     * @return  the renderer for this columnClass
     * @see     #setDefaultRenderer
     * @see     #getColumnClass
     *
     */
    @Override
    public TableCellRenderer getDefaultRenderer(Class columnClass) {
        TableCellRenderer renderer = super.getDefaultRenderer(columnClass);
        if (renderer == null && columnClass != null && columnClass.isPrimitive()) {
            renderer = super.getDefaultRenderer(Classes.getWrapperClass(                    columnClass));
        }
        return renderer;
    }

    @Override
    protected void configureEnclosingScrollPane() {
        super.configureEnclosingScrollPane();

        JScrollPane scrollPane = getScrollPaneParent();

        if (tableRowHeaderController != null && scrollPane != null) {
            tableRowHeaderController.configureRowHeader(scrollPane);
        }
    }

    @Override
    protected void unconfigureEnclosingScrollPane() {
        JScrollPane scrollPane = getScrollPaneParent();

        if (tableRowHeaderController != null && scrollPane != null) {
            tableRowHeaderController.unconfigureRowHeader(scrollPane);
        }

        super.unconfigureEnclosingScrollPane();
    }

    public TableRowHeaderController getTableRowHeaderController() {
        return tableRowHeaderController;
    }

    public void setTableRowHeaderController(
            TableRowHeaderController tableRowHeaderFactory) {
        JScrollPane scrollPane = getScrollPaneParent();
        if (this.tableRowHeaderController != null && scrollPane != null) {
            tableRowHeaderFactory.unconfigureRowHeader(scrollPane);
        }
        this.tableRowHeaderController = tableRowHeaderFactory;
        if (this.tableRowHeaderController != null && scrollPane != null) {
            tableRowHeaderFactory.configureRowHeader(scrollPane);
        }
    }

    private JScrollPane getScrollPaneParent() {
        Component c = getParent();
        if (c instanceof JViewport) {
            JViewport viewport = (JViewport) c;
            if (viewport instanceof JViewport) {
                Container viewportParent = viewport.getParent();
                if (viewportParent instanceof JScrollPane) {
                    return (JScrollPane) viewportParent;
                }
            }
        }
        return null;
    }
}
