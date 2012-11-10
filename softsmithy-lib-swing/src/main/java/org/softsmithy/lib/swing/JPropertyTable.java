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
 * PropertyTable.java
 *
 * Created on 19. September 2002, 18:03
 */
package org.softsmithy.lib.swing;

import java.util.*;
import javax.swing.table.*;
import org.softsmithy.lib.swing.table.*;

/**
 *
 * @author  puce
 */
public class JPropertyTable extends JCellTable {

    private final Map<String, TableCellRenderer> propertyRenderers = new HashMap<String, TableCellRenderer>();
    private final Map<String, TableCellEditor> propertyEditors = new HashMap<String, TableCellEditor>();

    /** Creates a new instance of PropertyTable */
    public JPropertyTable() {
        this(new PropertyTableModel(new ArrayList<String>(), null, null, Locale.getDefault()));
    }

    public JPropertyTable(PropertyTableModel ptm) {
        super(ptm);
        init();
    }

    private void init() {
        //setPropertyRenderer("htmlBody", new TextFieldTableCellRenderer());
    }

    @Override
    public void reinit() {
        super.reinit();
        init();
    }

    @Override
    public void setModel(TableModel model) {
        if (!(model instanceof PropertyTableModel)) {
            throw new IllegalArgumentException("model must be a CustomizerPropertyTableModel");
        }
        super.setModel(model);
    }

    public PropertyTableModel getPropertyTableModel() {
        return (PropertyTableModel) getModel();
    }

    public void setPropertyTableModel(PropertyTableModel model) {
        setModel(model);
    }

    /** Sets the locale of this component.  This is a bound property.
     * @param l the locale to become this component's locale
     * @see #getLocale
     * @since JDK1.1
     *
     */
    @Override
    public void setLocale(Locale locale) {
        super.setLocale(locale);
        if (getPropertyTableModel() != null) {
            getPropertyTableModel().setLocale(locale);
        }
    }

    public TableCellEditor getPropertyEditor(String propertyName) {
        return this.propertyEditors.get(propertyName);
    }

    public void setPropertyEditor(String propertyName, TableCellEditor propertyEditor) {
        this.propertyEditors.put(propertyName, propertyEditor);
    }

    public TableCellRenderer getPropertyRenderer(String propertyName) {
        return propertyRenderers.get(propertyName);
    }

    public void setPropertyRenderer(String propertyName, TableCellRenderer propertyRenderer) {
        propertyRenderers.put(propertyName, propertyRenderer);
    }

    /** Returns an appropriate editor for the cell specified by
     * <code>row</code> and <code>column</code>. If the
     * <code>TableColumn</code> for this column has a non-null editor,
     * returns that.  If not, finds the class of the data in this
     * column (using <code>getColumnClass</code>)
     * and returns the default editor for this type of data.
     * <p>
     * <b>Note:</b>
     * Throughout the table package, the internal implementations always
     * use this method to provide editors so that this default behavior
     * can be safely overridden by a subclass.
     *
     * @param row       the row of the cell to edit, where 0 is the first row
     * @param column    the column of the cell to edit,
     * 			where 0 is the first column
     * @return          the editor for this cell;
     * 			if <code>null</code> return the default editor for
     *  		this type of cell
     * @see DefaultCellEditor
     *
     *
     */
    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        TableCellEditor editor = null;
        switch (column) {
            case 0:
                editor = super.getCellEditor(row, column);
                break;
            case 1:
                editor = getPropertyEditor(getPropertyName(row));
                if (editor == null) {
                    editor = super.getCellEditor(row, column);
                }
                break;
            default:
                editor = super.getCellEditor(row, column);
                break; // should not happen;
        }
        return editor;
    }

    /** Returns an appropriate renderer for the cell specified by this row and
     * column. If the <code>TableColumn</code> for this column has a non-null
     * renderer, returns that.  If not, finds the class of the data in
     * this column (using <code>getColumnClass</code>)
     * and returns the default renderer for this type of data.
     * <p>
     * <b>Note:</b>
     * Throughout the table package, the internal implementations always
     * use this method to provide renderers so that this default behavior
     * can be safely overridden by a subclass.
     *
     * @param row       the row of the cell to render, where 0 is the first row
     * @param column    the column of the cell to render,
     * 			where 0 is the first column
     * @return the assigned renderer; if <code>null</code>
     * 			returns the default renderer
     * 			for this type of object
     * @see javax.swing.table.DefaultTableCellRenderer
     * @see javax.swing.table.TableColumn#setCellRenderer
     * @see #setDefaultRenderer
     *
     *
     */
    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        TableCellRenderer renderer = null;
        switch (column) {
            case 0:
                renderer = super.getCellRenderer(row, column);
                break;
            case 1:
                renderer = getPropertyRenderer(getPropertyName(row));
                if (renderer == null) {
                    renderer = super.getCellRenderer(row, column);
                }
                break;
            default:
                renderer = super.getCellRenderer(row, column);
                break; // should not happen;
        }
        return renderer;
    }

    public String getPropertyName(int row) {
        return getPropertyTableModel().getPropertyName(row);
    }
}
