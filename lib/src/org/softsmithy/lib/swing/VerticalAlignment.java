/*
 * VerticalAlignment.java
 *
 * Created on 7. Oktober 2002, 19:49
 */

package org.softsmithy.lib.swing;

import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.util.*;

/**
 *
 * @author  puce
 */
public abstract class VerticalAlignment extends TypesafeEnum {
  
  private static final ResourceBundleCache cache = new ResourceBundleCache("puce.swing.VerticalAlignment");
  
  /** Creates a new instance of VerticalAlignment */
  private VerticalAlignment(String s){
    super(s);
  }
  
  public String toString(Locale locale){
    return cache.getBundle(locale).getString(toString());
  }
  
  public abstract int getSwingConstant();
  
  public static final VerticalAlignment TOP = new VerticalAlignment("top"){
    public int getSwingConstant(){
      return SwingConstants.TOP;
    }
  };
  public static final VerticalAlignment CENTER = new VerticalAlignment("center"){
    public int getSwingConstant(){
      return SwingConstants.CENTER;
    }
  };
  public static final VerticalAlignment BOTTOM = new VerticalAlignment("bottom"){
    public int getSwingConstant(){
      return SwingConstants.BOTTOM;
    }
  };
  
  private static final VerticalAlignment[] PRIVATE_VALUES = {TOP, CENTER, BOTTOM};
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
  private static final Map alignments = new HashMap();
  static{
    for (int i=0; i<PRIVATE_VALUES.length; i++){
      alignments.put(new Integer(PRIVATE_VALUES[i].getSwingConstant()), PRIVATE_VALUES[i]);
    }
  }
  
  public static VerticalAlignment getVerticalAlignment(int verticalAlignment){
    return (VerticalAlignment) alignments.get(new Integer(verticalAlignment));
  }
}

