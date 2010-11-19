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
        @Override
    public int getSwingConstant(){
      return SwingConstants.LEFT;
    }
        @Override
    public String getHtmlConstant(ComponentOrientation co) {
      return "left";
    }
        @Override
    public int getStyleConstant(ComponentOrientation co){
      return StyleConstants.ALIGN_LEFT;
    }
        @Override
    public void alignCustomizer(JCustomizer customizer, int position, ComponentOrientation co){
      //System.out.println("in Left Alignment");
      customizer.setX(position);
    }
  };
  
  public static final HorizontalAlignment CENTER = new HorizontalAlignment("center"){
        @Override
    public int getSwingConstant(){
      return SwingConstants.CENTER;
    }
        @Override
    public String getHtmlConstant(ComponentOrientation co) {
      return "center";
    }
        @Override
    public int getStyleConstant(ComponentOrientation co){
      return StyleConstants.ALIGN_CENTER;
    }
        @Override
    public void alignCustomizer(JCustomizer customizer, int position, ComponentOrientation co){
      customizer.setX(position-customizer.getWidth()/2);
    }
  };
  public static final HorizontalAlignment RIGHT = new HorizontalAlignment("right"){
        @Override
    public int getSwingConstant(){
      return SwingConstants.RIGHT;
    }
        @Override
    public String getHtmlConstant(ComponentOrientation co) {
      return "right";
    }
        @Override
    public int getStyleConstant(ComponentOrientation co){
      return StyleConstants.ALIGN_RIGHT;
    }
        @Override
    public void alignCustomizer(JCustomizer customizer, int position, ComponentOrientation co){
      //System.out.println("in Right Alignment");
      customizer.setX(position-customizer.getWidth());
    }
  };
  public static final HorizontalAlignment LEADING = new  RelativeHorizontalAlignment("leading"){
        @Override
    public int getSwingConstant(){
      return SwingConstants.LEADING;
    }
    @Override
    public HorizontalAlignment orient(ComponentOrientation co){
      return co.isLeftToRight() ? LEFT : RIGHT;
    }
  };
  public static final HorizontalAlignment TRAILING = new RelativeHorizontalAlignment("trailing"){
        @Override
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
        @Override
    public String getHtmlConstant(ComponentOrientation co) {
      return orient(co).getHtmlConstant(co);
    }
        @Override
    public int getStyleConstant(ComponentOrientation co){
      return orient(co).getStyleConstant(co);
    }
        @Override
    public void alignCustomizer(JCustomizer customizer, int position, ComponentOrientation co) {
      orient(co).alignCustomizer(customizer, position, co);
    }
    
  }
  
  private static final HorizontalAlignment[] PRIVATE_VALUES = {LEFT, CENTER, RIGHT, LEADING, TRAILING};
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
  private static final Map<Integer, HorizontalAlignment> alignments = new HashMap<Integer, HorizontalAlignment>();
  static{
    for (int i=0; i<PRIVATE_VALUES.length; i++){
      alignments.put(new Integer(PRIVATE_VALUES[i].getSwingConstant()), PRIVATE_VALUES[i]);
    }
  }
  
  public static HorizontalAlignment getHorizontalAlignment(int swingConstant){
    return alignments.get(new Integer(swingConstant));
  }
  
}