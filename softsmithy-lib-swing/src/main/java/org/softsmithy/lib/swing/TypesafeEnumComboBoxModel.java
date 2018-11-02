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
 * DefaultTypesafeEnumComboBoxModel.java
 *
 * Created on 25. November 2002, 17:52
 */
package org.softsmithy.lib.swing;

import java.util.*;
import javax.swing.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softsmithy.lib.util.*;

/**
 *
 * @author puce
 */
@Deprecated
public class TypesafeEnumComboBoxModel extends DefaultComboBoxModel {

    private static final Logger LOG = LoggerFactory.getLogger(TypesafeEnumComboBoxModel.class);

    /**
     * Holds value of property locale.
     */
    //private Locale locale = Locale.getDefault();
    public TypesafeEnumComboBoxModel() {
    }

    public TypesafeEnumComboBoxModel(Locale locale) {
        //this.locale = locale;
    }

    /**
     * Creates a new instance of DefaultTypesafeEnumComboBoxModel
     * @param typesafeEnums the typesafe enum elements
     * @param locale the locale
     */
    public TypesafeEnumComboBoxModel(TypesafeEnum[] typesafeEnums, Locale locale) {
        this(locale);
        addAllTypesafeEnums(typesafeEnums);
    }

    public TypesafeEnumComboBoxModel(Class typesafeEnumClass, Locale locale) {
        this(locale);
        if (!(TypesafeEnum.class.isAssignableFrom(typesafeEnumClass))) {
            throw new IllegalArgumentException("typesafeEnumClass must be a subclass of TypesafeEnum");
        }
        List enums = Collections.EMPTY_LIST;
        try {
            enums = (List) typesafeEnumClass.getField("VALUES").get(null);
        } catch (IllegalAccessException | NoSuchFieldException | RuntimeException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        for (Iterator i = enums.iterator(); i.hasNext();) {
            this.addElement(i.next());
        }
    }

    public TypesafeEnum getSelectedTypesafeEnum() {
        return (TypesafeEnum) getSelectedItem();
    }

    @Override
    public void removeElement(Object anObject) {
        if (!(anObject instanceof TypesafeEnum)) {
            throw new IllegalArgumentException("anObject must be a TypesafeEnum");
        }
        super.removeElement(anObject);
    }

    @Override
    public void insertElementAt(Object anObject, int index) {
        if (!(anObject instanceof TypesafeEnum)) {
            throw new IllegalArgumentException("anObject must be a TypesafeEnum");
        }
        super.insertElementAt(anObject, index);
    }

    @Override
    public void addElement(Object anObject) {
        if (!(anObject instanceof TypesafeEnum)) {
            throw new IllegalArgumentException("anObject must be a TypesafeEnum");
        }
        super.addElement(anObject);
    }

    /**
     * Set the value of the selected item. The selected item may be null.
     * <p>
     * @param anObject The combo box value or null for no selection.
     *
     */
    @Override
    public void setSelectedItem(Object anObject) {
        if (!(anObject instanceof TypesafeEnum)) {
            throw new IllegalArgumentException("anObject must be a TypesafeEnum");
        }
        super.setSelectedItem(anObject);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int getIndexOf(Object anObject) {
        if (!(anObject instanceof TypesafeEnum)) {
            throw new IllegalArgumentException("anObject must be a TypesafeEnum");
        }
        return super.getIndexOf(anObject);
    }

    public TypesafeEnum getTypesafeEnumAt(int index) {
        return (TypesafeEnum) getElementAt(index);
    }

    public void addAllTypesafeEnums(TypesafeEnum[] typesafeEnums) {
        for (int i = 0; i < typesafeEnums.length; i++) {
            this.addElement(typesafeEnums[i]);
        }
    }

}
