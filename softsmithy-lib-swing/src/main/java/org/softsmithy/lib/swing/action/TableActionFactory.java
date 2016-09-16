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
 * TableActionFactory.java
 *
 * Created on 22. Juni 2004, 16:44
 */
package org.softsmithy.lib.swing.action;

/**
 * http://java.sun.com/developer/techDocs/hi/repository/TBG_Table.html
 *
 * @author puce
 */
public enum TableActionFactory implements StandardActionFactory {

    COLUMN_DELETE("tableColumnDelete"),
    COLUMN_INSERT_AFTER("tableColumnInsertAfter"),
    COLUMN_INSERT_BEFORE("tableColumnInsertBefore"),
    ROW_DELETE("tableRowDelete"),
    ROW_INSERT_AFTER("tableRowInsertAfter"),
    ROW_INSERT_BEFORE("tableRowInsertBefore");

    private final String name;

    TableActionFactory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
