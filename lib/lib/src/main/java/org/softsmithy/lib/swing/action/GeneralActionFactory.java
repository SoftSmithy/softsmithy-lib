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
 * GeneralActionFactory.java
 *
 * Created on 2. Juni 2004, 02:47
 */

package org.softsmithy.lib.swing.action;

/**
 * http://java.sun.com/developer/techDocs/hi/repository/TBG_General.html
 * @author  puce
 */
public class GeneralActionFactory extends StandardActionFactory{
  
  /** Creates a new instance of GeneralActionFactory */
  private GeneralActionFactory(String name) {
    super(name);
  }
  public static final GeneralActionFactory ABOUT = new GeneralActionFactory("about");
  public static final GeneralActionFactory ADD = new GeneralActionFactory("add");
  public static final GeneralActionFactory ALIGN_BOTTOM = new GeneralActionFactory("alignBottom");
  public static final GeneralActionFactory ALIGN_CENTER = new GeneralActionFactory("alignCenter");
  public static final GeneralActionFactory ALIGN_JUSTIFY_HORIZONTAL = new GeneralActionFactory("alignJustifyHorizontal");
  public static final GeneralActionFactory ALIGN_JUSTIFY_VERTICAL = new GeneralActionFactory("alignJustifyVertical");
  public static final GeneralActionFactory ALIGN_LEFT = new GeneralActionFactory("alignLeft");
  public static final GeneralActionFactory ALIGN_RIGHT = new GeneralActionFactory("alignRight");
  public static final GeneralActionFactory ALIGN_TOP = new GeneralActionFactory("alignTop");
  public static final GeneralActionFactory BOOKMARKS = new GeneralActionFactory("bookmarks");
  public static final GeneralActionFactory COMPOSE_MAIL = new GeneralActionFactory("composeMail");
  public static final GeneralActionFactory CONTEXTUAL_HELP = new GeneralActionFactory("contextualHelp");
  public static final GeneralActionFactory COPY = new GeneralActionFactory("copy");
  public static final GeneralActionFactory CUT = new GeneralActionFactory("cut");
  public static final GeneralActionFactory DELETE = new GeneralActionFactory("delete");
  public static final GeneralActionFactory EDIT = new GeneralActionFactory("edit");
  public static final GeneralActionFactory EXPORT = new GeneralActionFactory("export");
  public static final GeneralActionFactory FIND = new GeneralActionFactory("find");
  public static final GeneralActionFactory FIND_AGAIN = new GeneralActionFactory("findAgain");
  public static final GeneralActionFactory HELP = new GeneralActionFactory("help");
  public static final GeneralActionFactory HISTORY = new GeneralActionFactory("history");
  /**
   * "Import" is a keyword, so there can't be a method with that name. Therefor the key is "generalImport".
   */
  public static final GeneralActionFactory IMPORT = new GeneralActionFactory("generalImport");
  public static final GeneralActionFactory INFORMATION = new GeneralActionFactory("information");
  public static final GeneralActionFactory NEW = new GeneralActionFactory("new");
  public static final GeneralActionFactory OPEN = new GeneralActionFactory("open");
  public static final GeneralActionFactory PAGE_SETUP = new GeneralActionFactory("pageSetup");
  public static final GeneralActionFactory PASTE = new GeneralActionFactory("paste");
  public static final GeneralActionFactory PREFERENCES = new GeneralActionFactory("preferences");
  public static final GeneralActionFactory PRINT = new GeneralActionFactory("print");
  public static final GeneralActionFactory PRINT_PREVIEW = new GeneralActionFactory("printPreview");
  public static final GeneralActionFactory PROPERTIES = new GeneralActionFactory("properties");
  public static final GeneralActionFactory REDO = new GeneralActionFactory("redo");
  public static final GeneralActionFactory REFRESH = new GeneralActionFactory("refresh");
  public static final GeneralActionFactory REMOVE = new GeneralActionFactory("remove");
  public static final GeneralActionFactory REPLACE = new GeneralActionFactory("replace");
  public static final GeneralActionFactory SAVE = new GeneralActionFactory("save");
  public static final GeneralActionFactory SAVE_ALL = new GeneralActionFactory("saveAll");
  public static final GeneralActionFactory SAVE_AS = new GeneralActionFactory("saveAs");
  public static final GeneralActionFactory SEARCH = new GeneralActionFactory("search");
  public static final GeneralActionFactory SEND_MAIL = new GeneralActionFactory("sendMail");
  public static final GeneralActionFactory STOP = new GeneralActionFactory("stop");
  public static final GeneralActionFactory TIP_OF_THE_DAY = new GeneralActionFactory("tipOfTheDay");
  public static final GeneralActionFactory UNDO = new GeneralActionFactory("undo");
  public static final GeneralActionFactory ZOOM = new GeneralActionFactory("zoom");
  public static final GeneralActionFactory ZOOM_IN = new GeneralActionFactory("zoomIn");
  public static final GeneralActionFactory ZOOM_OUT = new GeneralActionFactory("zoomOut");
  
}
