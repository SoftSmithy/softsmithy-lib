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

package org.softsmithy.lib.swing;

import java.awt.ComponentOrientation;
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import org.softsmithy.lib.util.*;



public abstract class HorizontalAlignment extends TypesafeEnum{
  
  private static final String BASE_NAME = "org.softsmithy.lib.swing.HorizontalAlignment";
  
  private HorizontalAlignment(String s){
    super(s);
  }
  
  
  @Override
  public String getResourceBundleBaseName(){
    return BASE_NAME;
  }
  
  public HorizontalAlignment orient(ComponentOrientation co){
    return this;
  }
  
  public void alignCustomizers(Collection customizers, int position, ComponentOrientation co) {
    if (customizers.size() > 0){
      for (Iterator i=customizers.iterator(); i.hasNext();){
        alignCustomizer((JCustomizer) i.next(), position, co);
      }
    }
  }
  
  public abstract int getSwingConstant();
  public abstract int getStyleConstant(ComponentOrientation co);
  public abstract String getHtmlConstant(ComponentOrientation co);
  public abstract void alignCustomizer(JCustomizer customizer, int position, ComponentOrientation co);
  
  
  public static final HorizontalAlignment LEFT = new HorizontalAlignment("left"){
    public int getSwingConstant(){
      return SwingConstants.LEFT;
    }
    public String getHtmlConstant(ComponentOrientation co) {
      return "left";
    }
    public int getStyleConstant(ComponentOrientation co){
      return StyleConstants.ALIGN_LEFT;
    }
    public void alignCustomizer(JCustomizer customizer, int position, ComponentOrientation co){
      //System.out.println("in Left Alignment");
      customizer.setX(position);
    }
  };
  
  public static final HorizontalAlignment CENTER = new HorizontalAlignment("center"){
    public int getSwingConstant(){
      return SwingConstants.CENTER;
    }
    public String getHtmlConstant(ComponentOrientation co) {
      return "center";
    }
    public int getStyleConstant(ComponentOrientation co){
      return StyleConstants.ALIGN_CENTER;
    }
    public void alignCustomizer(JCustomizer customizer, int position, ComponentOrientation co){
      customizer.setX(position-customizer.getWidth()/2);
    }
  };
  public static final HorizontalAlignment RIGHT = new HorizontalAlignment("right"){
    public int getSwingConstant(){
      return SwingConstants.RIGHT;
    }
    public String getHtmlConstant(ComponentOrientation co) {
      return "right";
    }
    public int getStyleConstant(ComponentOrientation co){
      return StyleConstants.ALIGN_RIGHT;
    }
    public void alignCustomizer(JCustomizer customizer, int position, ComponentOrientation co){
      //System.out.println("in Right Alignment");
      customizer.setX(position-customizer.getWidth());
    }
  };
  public static final HorizontalAlignment LEADING = new  RelativeHorizontalAlignment("leading"){
    public int getSwingConstant(){
      return SwingConstants.LEADING;
    }
    @Override
    public HorizontalAlignment orient(ComponentOrientation co){
      return co.isLeftToRight() ? LEFT : RIGHT;
    }
  };
  public static final HorizontalAlignment TRAILING = new RelativeHorizontalAlignment("trailing"){
    public int getSwingConstant(){
      return SwingConstants.TRAILING;
    }
    @Override
    public HorizontalAlignment orient(ComponentOrientation co){
      return co.isLeftToRight() ? RIGHT : LEFT;
    }
  };
  
  private static abstract class RelativeHorizontalAlignment extends HorizontalAlignment{
    
    private RelativeHorizontalAlignment(String s){
      super(s);
    }
    public String getHtmlConstant(ComponentOrientation co) {
      return orient(co).getHtmlConstant(co);
    }
    public int getStyleConstant(ComponentOrientation co){
      return orient(co).getStyleConstant(co);
    }
    public void alignCustomizer(JCustomizer customizer, int position, ComponentOrientation co) {
      orient(co).alignCustomizer(customizer, position, co);
    }
    
  }
  
  private static final HorizontalAlignment[] PRIVATE_VALUES = {LEFT, CENTER, RIGHT, LEADING, TRAILING};
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
  private static final Map alignments = new HashMap();
  static{
    for (int i=0; i<PRIVATE_VALUES.length; i++){
      alignments.put(new Integer(PRIVATE_VALUES[i].getSwingConstant()), PRIVATE_VALUES[i]);
    }
  }
  
  public static HorizontalAlignment getHorizontalAlignment(int swingConstant){
    return (HorizontalAlignment) alignments.get(new Integer(swingConstant));
  }
  
}