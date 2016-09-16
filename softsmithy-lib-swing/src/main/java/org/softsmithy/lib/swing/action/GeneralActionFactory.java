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
 *
 * @author puce
 */
public enum GeneralActionFactory implements StandardActionFactory {

    ABOUT("about"),
    ADD("add"),
    ALIGN_BOTTOM("alignBottom"),
    ALIGN_CENTER("alignCenter"),
    ALIGN_JUSTIFY_HORIZONTAL("alignJustifyHorizontal"),
    ALIGN_JUSTIFY_VERTICAL("alignJustifyVertical"),
    ALIGN_LEFT("alignLeft"),
    ALIGN_RIGHT("alignRight"),
    ALIGN_TOP("alignTop"),
    BOOKMARKS("bookmarks"),
    COMPOSE_MAIL("composeMail"),
    CONTEXTUAL_HELP("contextualHelp"),
    COPY("copy"),
    CUT("cut"),
    DELETE("delete"),
    EDIT("edit"),
    EXPORT("export"),
    FIND("find"),
    FIND_AGAIN("findAgain"),
    HELP("help"),
    HISTORY("history"),
    /**
     * "Import" is a keyword, so there can't be a method with that name. Therefor the key is "generalImport".
     */
    IMPORT("generalImport"),
    INFORMATION("information"),
    NEW("new"),
    OPEN("open"),
    PAGE_SETUP("pageSetup"),
    PASTE("paste"),
    PREFERENCES("preferences"),
    PRINT("print"),
    PRINT_PREVIEW("printPreview"),
    PROPERTIES("properties"),
    REDO("redo"),
    REFRESH("refresh"),
    REMOVE("remove"),
    REPLACE("replace"),
    SAVE("save"),
    SAVE_ALL("saveAll"),
    SAVE_AS("saveAs"),
    SEARCH("search"),
    SEND_MAIL("sendMail"),
    STOP("stop"),
    TIP_OF_THE_DAY("tipOfTheDay"),
    UNDO("undo"),
    ZOOM("zoom"),
    ZOOM_IN("zoomIn"),
    ZOOM_OUT("zoomOut");

    private final String name;

    GeneralActionFactory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
