/*
 * TableActionFactory.java
 *
 * Created on 22. Juni 2004, 16:44
 */

package org.softsmithy.lib.swing.action;

/**
 *
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
