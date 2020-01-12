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
 * AbstractCustomizerPropertyTableModel.java
 *
 * Created on 6. Februar 2003, 19:24
 */
package org.softsmithy.lib.swing.table;

import java.beans.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import javax.swing.table.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softsmithy.lib.beans.BeanIntrospector;

/**
 *
 * @author puce
 */
public class PropertyTableModel extends AbstractTableModel implements CellTableModel {

    private static final Logger LOG = LoggerFactory.getLogger(PropertyTableModel.class);

    private List<String> propertyNames;
    private Object bean;
    private String propertiesRBBaseName;
    private ResourceBundle propertiesRB;
    private ResourceBundle columnNamesRB;
    private Locale locale;
    private final BeanPropertyListener beanPropertyListener = new BeanPropertyListener();

    /**
     * Creates a new instance of PropertyTableModel
     *
     * @param bean the bean to display
     * @param propertiesRBBaseName the base name of the properties resource bundle
     * @param locale the locale
     * @throws IntrospectionException if an introspection error occurs
     */
    public PropertyTableModel(Object bean, String propertiesRBBaseName, Locale locale) throws IntrospectionException {
        init(propertiesRBBaseName, locale);
        Map<String, String> propertyMap = new TreeMap<>();
        if (bean != null) {
            PropertyDescriptor[] descriptors = BeanIntrospector.getPropertyDescriptors(bean.getClass(), propertiesRB);
            for (PropertyDescriptor descriptor : descriptors) {
                if (BeanIntrospector.isPropertyReadable(descriptor.getName(), bean.getClass(), propertiesRB)) {
                    if (!descriptor.isHidden()) {
                        String key = descriptor.getDisplayName() != null ? descriptor.getDisplayName() : descriptor.getName();
                        propertyMap.put(key, descriptor.getName());
                    }
                }
            }
        }
        init(new ArrayList<>(propertyMap.values()), bean);
    }

    public PropertyTableModel(List<String> readableProperties, Object bean,
            String propertiesRBBaseName, Locale locale) {
        init(propertiesRBBaseName, locale);
        init(readableProperties, bean);
    }

    private void init(String propertiesRBBaseName, Locale locale) {
        this.propertiesRBBaseName = propertiesRBBaseName;
        setLocale(locale);
    }

    private void init(List<String> readableProperties, Object bean) {
        this.propertyNames = Collections.unmodifiableList(readableProperties);
        this.bean = bean;
        if (bean != null) {
            if (BeanIntrospector.supportsPropertyChangeListenersByPropertyName(bean.getClass())) {
                try {
                    for (Iterator i = readableProperties.iterator(); i.hasNext();) {
                        BeanIntrospector.addPropertyChangeListener(bean, (String) i.
                                next(), beanPropertyListener);
                    }
                } catch (NoSuchMethodException | IllegalAccessException ex1) { // should not happen here
                    LOG.error(ex1.getMessage(), ex1);
                }
            } else {
                if (BeanIntrospector.supportsPropertyChangeListeners(bean.
                        getClass())) {
                    try {
                        BeanIntrospector.addPropertyChangeListener(bean,
                                beanPropertyListener);
                    } catch (NoSuchMethodException | IllegalAccessException ex1) { // should not happen here
                        LOG.error(ex1.getMessage(), ex1);
                    }
                }
            }
        }
        //    if (activeCustomizer != null){
        //      for (Iterator i=properties.iterator(); i.hasNext();){
        //        activeCustomizer.addPropertyChangeListener((String) i.next(), this);
        //      }
        //    }
    }

    /**
     * Returns the number of columns in the model. A <code>JTable</code> uses this method to determine how many columns it should create and display by default.
     *
     * @return the number of columns in the model
     * @see #getRowCount
     *
     */
    @Override
    public int getColumnCount() {
        return 2;
    }

    /**
     * Returns the number of rows in the model. A <code>JTable</code> uses this method to determine how many rows it should display. This method should be quick, as it is called frequently during
     * rendering.
     *
     * @return the number of rows in the model
     * @see #getColumnCount
     *
     */
    @Override
    public int getRowCount() {
        return propertyNames.size();
    }

    /**
     * Returns the value for the cell at <code>columnIndex</code> and <code>rowIndex</code>.
     *
     * @param	rowIndex	the row whose value is to be queried
     * @param	columnIndex the column whose value is to be queried
     * @return	the value Object at the specified cell
     *
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;
        try {
            switch (columnIndex) {
                case 0:
                    value = getPropertyDescriptor(rowIndex).getDisplayName();
                    break;
                case 1:
                    value = BeanIntrospector.getPropertyValue(getPropertyName(rowIndex), bean, propertiesRB);
                    break;
            }
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException | RuntimeException ex) {
            LOG.error(ex.getMessage(), ex);
            //value = "";
        }
        //System.out.print(value);
        return value;
    }

    /**
     * This empty implementation is provided so users don't have to implement this method if their data model is not editable.
     *
     * @param aValue value to assign to cell
     * @param rowIndex row of cell
     * @param columnIndex column of cell
     *
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            BeanIntrospector.setPropertyValue(getPropertyName(rowIndex), aValue, bean, propertiesRB);
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException | RuntimeException ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    private PropertyDescriptor getPropertyDescriptor(int index) throws IntrospectionException {
        return BeanIntrospector.getPropertyDescriptor(getPropertyName(index), bean.getClass(), propertiesRB);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        boolean isCellEditable = false;
        try {
            isCellEditable = (columnIndex == 1)
                    && BeanIntrospector.isPropertyWriteable(getPropertyName(rowIndex), bean.getClass(), propertiesRB);
        } catch (IntrospectionException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return isCellEditable;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String getColumnName(int column) {
        String columnName = "";
        try {
            switch (column) {
                case 0:
                    columnName = columnNamesRB.getString("property");
                    break;
                case 1:
                    columnName = columnNamesRB.getString("value");
                    break;
                default:
                    columnName = super.getColumnName(column); // should not happen
            }
        } catch (RuntimeException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return columnName;
    }

//    /** This method gets called when a bound property is changed.
//     * @param evt A PropertyChangeEvent object describing the event source
//     *   	and the property that has changed.
//     *
//     */
    //  public void propertyChange(PropertyChangeEvent evt) {
    //    System.out.println("in");
    //    int index = customizers.indexOf(evt.getPropertyName());
    //    if (index >= 0){
    //      fireTableCellUpdated(index, 1);
    //    }
    //
    //  }
    @Override
    public Class getCellClass(int row, int column) {
        Class cellClass;
        try {
            switch (column) {
                case 0:
                    cellClass = String.class;
                    break;
                case 1:
                    cellClass = getPropertyDescriptor(row).getPropertyType();
                    break;
                default:
                    cellClass = getColumnClass(column); // should not happen
            }
        } catch (IntrospectionException ex) {
            cellClass = getColumnClass(column);
        }
        //System.out.print(cellClass+": ");
        return cellClass;
    }
//
//    /** This method gets called when a bound property is changed.
//     * @param evt A PropertyChangeEvent object describing the event source
//     *   	and the property that has changed.
//     *
//     */
    //  public void propertyChange(PropertyChangeEvent evt) {
    //    //    System.out.println(evt.getPropertyName() + " changed");
    //    this.fireTableCellUpdated(propertyNames.indexOf(evt.getPropertyName()), 1);
    //  }

    public List<String> getPropertyNames() {
        return this.propertyNames;
    }

    public String getPropertyName(int row) {
        return propertyNames.get(row);
    }

    public Object getBean() {
        return this.bean;
    }

    public Locale getLocale() {
        return locale;
    }

    /**
     * Setter for property locale.
     *
     * @param locale New value of property locale.
     *
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
        this.columnNamesRB = ResourceBundle.getBundle(
                "org.softsmithy.lib.swing.table.PropertyTableModel", locale);
        if (getPropertiesRBBaseName() != null) {
            this.propertiesRB = ResourceBundle.getBundle(getPropertiesRBBaseName(), locale);
        }
        fireTableStructureChanged();
    }

    public String getPropertiesRBBaseName() {
        return this.propertiesRBBaseName;
    }

    public void setPropertiesRBBaseName(String propertiesRBBaseName) {
        this.propertiesRBBaseName = propertiesRBBaseName;
        if (propertiesRBBaseName == null) {
            this.propertiesRB = null;
        } else {
            this.propertiesRB = ResourceBundle.getBundle(propertiesRBBaseName, locale);
        }
        fireTableDataChanged();
    }

    public void stopListening() {
        stopPropertyChangeListening();
    }

    private void stopPropertyChangeListening() {
        if (getBean() != null) {
            if (BeanIntrospector.supportsPropertyChangeListenersByPropertyName(getBean().getClass())) {
                try {
                    for (String propertyName : getPropertyNames()) {
                        BeanIntrospector.removePropertyChangeListener(getBean(), propertyName, beanPropertyListener);
                    }
                } catch (NoSuchMethodException | IllegalAccessException ex1) { // should not happen here
                    LOG.error(ex1.getMessage(), ex1);
                }
            } else {
                if (BeanIntrospector.supportsPropertyChangeListeners(getBean().getClass())) {
                    try {
                        BeanIntrospector.removePropertyChangeListener(getBean(), beanPropertyListener);
                    } catch (NoSuchMethodException | IllegalAccessException ex1) { // should not happen here
                        LOG.error(ex1.getMessage(), ex1);
                    }
                }
            }
        }
    }

    private class BeanPropertyListener implements PropertyChangeListener {

        /**
         * This method gets called when a bound property is changed.
         *
         * @param evt A PropertyChangeEvent object describing the event source and the property that has changed.
         *
         */
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            int row = propertyNames.indexOf(evt.getPropertyName());
            if (row >= 0) {
                PropertyTableModel.this.fireTableCellUpdated(row, 1);
            }
        }
    }
}
