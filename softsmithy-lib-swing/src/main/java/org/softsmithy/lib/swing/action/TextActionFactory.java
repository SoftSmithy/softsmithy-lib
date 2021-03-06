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
 * A factory for the standard text actions.
 *
 * <a href="https://www.oracle.com/technetwork/java/tbg-text-140385.html">Text Toolbar Button Graphics: Java look and feel Graphics Repository</a>
 *
 * @author puce
 */
public class TextActionFactory extends StandardActionFactory {

    /**
     * Creates a new instance of TextActionFactory.
     *
     * @param name the enum constant name
     */
    private TextActionFactory(String name) {
        super(name);
    }

    public static final TextActionFactory ALIGN_CENTER = new TextActionFactory("textAlignCenter");
    public static final TextActionFactory ALIGN_JUSTIFY = new TextActionFactory("textAlignJustify");
    public static final TextActionFactory ALIGN_LEFT = new TextActionFactory("textAlignLeft");
    public static final TextActionFactory ALIGN_RIGHT = new TextActionFactory("textAlignRight");
    public static final TextActionFactory BOLD = new TextActionFactory("textBold");
    public static final TextActionFactory ITALIC = new TextActionFactory("textItalic");
    public static final TextActionFactory NORMAL = new TextActionFactory("textNormal");
    public static final TextActionFactory UNDERLINE = new TextActionFactory("textUnderline");
}
