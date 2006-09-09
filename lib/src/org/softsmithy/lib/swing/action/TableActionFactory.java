/*
 *                  Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
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
 * @author  puce
 */
public class TableActionFactory extends StandardActionFactory{
  
  /** Creates a new instance of TableActionFactory */
  private TableActionFactory(String name) {
    super(name);
  }
  public static final TableActionFactory COLUMN_DELETE = new TableActionFactory("tableColumnDelete");
  public static final TableActionFactory COLUMN_INSERT_AFTER = new TableActionFactory("tableColumnInsertAfter");
  public static final TableActionFactory COLUMN_INSERT_BEFORE = new TableActionFactory("tableColumnInsertBefore");
  public static final TableActionFactory ROW_DELETE = new TableActionFactory("tableRowDelete");
  public static final TableActionFactory ROW_INSERT_AFTER = new TableActionFactory("tableRowInsertAfter");
  public static final TableActionFactory ROW_INSERT_BEFORE = new TableActionFactory("tableRowInsertBefore");
}
