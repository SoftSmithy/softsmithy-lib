package puce.swing.multisplit;

import javax.swing.*;
import puce.util.*;

public abstract class SplitOrientation extends TypesafeEnum {
  
  private SplitOrientation(String name){
    super(name);
  }
  
  public abstract int getSplitPaneConstant();
  
  public static final SplitOrientation HORIZONTAL = new SplitOrientation("horizontal"){
    public int getSplitPaneConstant(){
      return JSplitPane.HORIZONTAL_SPLIT;
    }
  };
  
  public static final SplitOrientation VERTICAL = new SplitOrientation("vertical"){
    public int getSplitPaneConstant(){
      return JSplitPane.VERTICAL_SPLIT;
    }
  };
  
}