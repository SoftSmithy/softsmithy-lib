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

package org.softsmithy.lib.swing.chooser;

import java.awt.*;
import java.awt.event.*;
import org.softsmithy.lib.util.*;

public class Option extends TypesafeEnum {
  
  protected Option(String name){
    super(name);
  }

  public static final Option APPROVE = new Option("approve");
  public static final Option CANCEL = new Option("cancel");
  //public static final Option ERROR = new Option("error");
}
