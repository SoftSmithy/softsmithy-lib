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
 * <a href="https://www.oracle.com/technetwork/java/tbg-table-141372.html">Table Toolbar Button Graphics: Java look and feel Graphics Repository</a>
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
