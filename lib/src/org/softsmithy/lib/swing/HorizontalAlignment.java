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

import java.io.*;
import java.util.*;
import javax.swing.*;
import org.softsmithy.lib.util.*;

public abstract class HorizontalAlignment extends TypesafeEnum implements Serializable{
  
  private static final ResourceBundleCache cache = new ResourceBundleCache("org.softsmithy.lib.swing.HorizontalAlignment");
  
  private HorizontalAlignment(String s){
    super(s);
  }
  
  //  private HorizontalAlignment(){ // should only be used by serialization
  //    super("");
  //  }
  
  public String toString(Locale locale){
    return cache.getBundle(locale).getString(toString());
  }
  
  private Object writeReplace() throws ObjectStreamException{
    return new Integer(getSwingConstant());
  }
  
  public abstract int getSwingConstant();
  public abstract String getHtmlConstant();
  
  
  public static final HorizontalAlignment LEFT = new LeftHorizontalAlignment();
  public static final HorizontalAlignment CENTER = new HorizontalAlignment("center"){
    public int getSwingConstant(){
      return SwingConstants.CENTER;
    }
    private Object readResolve() throws ObjectStreamException{
      return HorizontalAlignment.CENTER;
    }
    public String getHtmlConstant() {
      return "center";
    }
  };
  public static final HorizontalAlignment RIGHT = new RightHorizontalAlignment();
  public static final HorizontalAlignment LEADING = new LeadingHorizontalAlignment();
  public static final HorizontalAlignment TRAILING = new TrailingHorizontalAlignment();
  
  private static final HorizontalAlignment[] PRIVATE_VALUES = {LEFT, CENTER, RIGHT, LEADING, TRAILING};
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
  private static final Map alignments = new HashMap();
  static{
    for (int i=0; i<PRIVATE_VALUES.length; i++){
      alignments.put(new Integer(PRIVATE_VALUES[i].getSwingConstant()), PRIVATE_VALUES[i]);
    }
  }
  
  public static HorizontalAlignment getHorizontalAlignment(int horizontalAlignment){
    return (HorizontalAlignment) alignments.get(new Integer(horizontalAlignment));
  }
  
  private static class LeftHorizontalAlignment extends HorizontalAlignment{
    
    public LeftHorizontalAlignment(){
      super("left");
    }
    
    public int getSwingConstant(){
      return SwingConstants.LEFT;
    }
    private Object readResolve() throws ObjectStreamException{
      return HorizontalAlignment.LEFT;
    }
    public String getHtmlConstant() {
      return "left";
    }
  }
  
  private static class CenterHorizontalAlignment extends HorizontalAlignment{
    
    public CenterHorizontalAlignment(){
      super("center");
    }
    
    public int getSwingConstant(){
      return SwingConstants.CENTER;
    }
    private Object readResolve() throws ObjectStreamException{
      return HorizontalAlignment.CENTER;
    }
    public String getHtmlConstant() {
      return "center";
    }
  }
  
  private static class RightHorizontalAlignment extends HorizontalAlignment{
    
    public RightHorizontalAlignment(){
      super("right");
    }
    
    public int getSwingConstant(){
      return SwingConstants.RIGHT;
    }
    private Object readResolve() throws ObjectStreamException{
      return HorizontalAlignment.RIGHT;
    }
    public String getHtmlConstant() {
      return "right";
    }
  }
  
  private static class LeadingHorizontalAlignment extends HorizontalAlignment{
    
    public LeadingHorizontalAlignment(){
      super("leading");
    }
    
    public int getSwingConstant(){
      return SwingConstants.LEADING;
    }
    
    public String getHtmlConstant() {
      return "left"; // no i18n
    }
    private Object readResolve() throws ObjectStreamException{
      return HorizontalAlignment.LEADING;
    }
  }
  
  private static class TrailingHorizontalAlignment extends HorizontalAlignment{
    
    public TrailingHorizontalAlignment(){
      super("trailing");
    }
    
    public int getSwingConstant(){
      return SwingConstants.TRAILING;
    }
    
    private Object readResolve() throws ObjectStreamException{
      return HorizontalAlignment.TRAILING;
    }
    
    public String getHtmlConstant() {
      return "right"; // no i18n
    }
    
  }
}