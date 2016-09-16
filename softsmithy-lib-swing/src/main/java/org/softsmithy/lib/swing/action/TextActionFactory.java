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
 * TextActionFactory.java
 *
 * Created on 22. Juni 2004, 16:00
 */
package org.softsmithy.lib.swing.action;

/**
 * http://java.sun.com/developer/techDocs/hi/repository/TBG_Text.html
 *
 * @author puce
 */
public enum TextActionFactory implements StandardActionFactory {

    ALIGN_CENTER("textAlignCenter"),
    ALIGN_JUSTIFY("textAlignJustify"),
    ALIGN_LEFT("textAlignLeft"),
    ALIGN_RIGHT("textAlignRight"),
    BOLD("textBold"),
    ITALIC("textItalic"),
    NORMAL("textNormal"),
    UNDERLINE("textUnderline");

    private final String name;

    TextActionFactory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
