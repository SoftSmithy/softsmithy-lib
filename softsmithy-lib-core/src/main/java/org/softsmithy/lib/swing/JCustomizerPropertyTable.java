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
import org.softsmithy.lib.swing.customizer.*;
import org.softsmithy.lib.swing.event.*;
import org.softsmithy.lib.swing.style.*;
import org.softsmithy.lib.swing.table.*;

/**
 *
 * @author  puce
 */
public class JCustomizerPropertyTable extends JPropertyTable implements CustomizerSelectionListener {

    private static final String PROPERTIES_BUNDLE_BASE_NAME = "org.softsmithy.lib.swing.customizer.Properties";
    /** Holds value of property rbBaseName. */
    private String rbBaseName = PROPERTIES_BUNDLE_BASE_NAME;

    /** Creates a new instance of PropertyTable */
    public JCustomizerPropertyTable() {
        this(new CustomizerPropertyTableModel(new ArrayList<String>(), null, PROPERTIES_BUNDLE_BASE_NAME, Locale.getDefault()));
    }

    public JCustomizerPropertyTable(CustomizerPropertyTableModel cptm) {
        super(cptm);
        init();
    }

    private void init() {
        setDefaultRenderer(Style.class, new StyleRenderer(getLocale()));
        setDefaultEditor(Style.class, new StyleCellEditor(getLocale())); // note: Style is an interface
        // for performance issues set max to 10000 (should be enough anyway)
        setPropertyEditor("x", new IntegerCellEditor(0, 10000, getLocale()));
        setPropertyEditor("y", new IntegerCellEditor(0, 10000, getLocale()));
        setPropertyEditor("width", new IntegerCellEditor(1, 10000, getLocale()));
        setPropertyEditor("height", new IntegerCellEditor(1, 10000, getLocale()));
        setPropertyEditor("thickness", new LocalizedFloatCellEditor(1, 10000, getLocale()));
    }

    @Override
    public void reinit() {
        super.reinit();
        init();
    }

    @Override
    public void setModel(TableModel model) {
        if (!(model instanceof CustomizerPropertyTableModel)) {
            throw new IllegalArgumentException("model must be a CustomizerPropertyTableModel");
        }
        if (getCustomizerPropertyTableModel() != null) {
            getCustomizerPropertyTableModel().stopListening();
        }
        super.setModel(model);
    }

    @Override
    public void setPropertyTableModel(PropertyTableModel model) {
        if (!(model instanceof CustomizerPropertyTableModel)) {
            throw new IllegalArgumentException("model must be a CustomizerPropertyTableModel");
        }
        setModel(model);
    }

    public CustomizerPropertyTableModel getCustomizerPropertyTableModel() {
        return (CustomizerPropertyTableModel) getModel();
    }

    public void setCustomizerPropertyTableModel(CustomizerPropertyTableModel model) {
        setModel(model);
    }

    @Override
    public void selectionChanged(CustomizerSelectionEvent e) {
        setCustomizerPropertyTableModel(new CustomizerPropertyTableModel(new ArrayList<String>(e.getCommonCustomizableProperties()), e.getActiveCustomizer(), getRbBaseName(), getLocale()));
    }

    /** Getter for property rbBaseName.
     * @return Value of property rbBaseName.
     *
     */
    public String getRbBaseName() {
        return this.rbBaseName;
    }

    /** Setter for property rbBaseName.
     * @param rbBaseName New value of property rbBaseName.
     *
     */
    public void setRbBaseName(String rbBaseName) {
        this.rbBaseName = rbBaseName;
        if (getCustomizerPropertyTableModel() != null) {
            getCustomizerPropertyTableModel().setPropertiesRBBaseName(rbBaseName);
        }
    }
}
