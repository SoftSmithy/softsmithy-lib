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
