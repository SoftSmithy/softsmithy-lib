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
 * TextActionFactory.java
 *
 * Created on 22. Juni 2004, 16:00
 */

package org.softsmithy.lib.swing.action;

/**
 *
 * @author  puce
 */
public class TextActionFactory extends StandardActionFactory{
  
  /** Creates a new instance of TextActionFactory */
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
