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
 * Styles.java
 *
 * Created on 11. Juli 2003, 16:56
 */

package org.softsmithy.lib.swing.style;

import java.awt.*;
import java.util.*;

/**
 *
 * @author  puce
 */
public class Styles {
  
  private static final String STYLES_RB_BASE_NAME = "org.softsmithy.lib.swing.style.Styles";
  
  /** Creates a new instance of Styles */
  private Styles() {
  }
  
  public static void applyStyle(Styleable styleable, Style oldStyle, Style newStyle){
    oldStyle.stopListening();
    newStyle.startListening();
    styleable.setDefaultBackground(newStyle.getBackground());
    styleable.setDefaultForeground(newStyle.getForeground());
    styleable.setDefaultFont(newStyle.getFont());
    styleable.setDefaultOpaque(newStyle.isOpaque());
  }
  
  public static void ensureNoneStyle(Styleable styleable, boolean inited){
    if (inited && ! styleable.getStyle().isNull()){
      styleable.setStyle(styleable.getNoneStyle());
    }
  }
  
  public static Style mutateStyle(Styleable styleable, Style style){
    Style mutatedStyle;
    if (style == null){
      mutatedStyle = styleable.getNoneStyle();
    } else {
      mutatedStyle = style.getStyleProvider().getStyle(styleable);
    }
    return mutatedStyle;
  }
  
  public static void setBackground(Styleable styleable, Color background, boolean inited){
    Styles.ensureNoneStyle(styleable, inited);
    styleable.setDefaultBackground(background);
  }
  
  public static void setForeground(Styleable styleable, Color foreground, boolean inited){
    Styles.ensureNoneStyle(styleable, inited);
    styleable.setDefaultForeground(foreground);
  }
  
  public static void setFont(Styleable styleable, Font font, boolean inited){
    Styles.ensureNoneStyle(styleable, inited);
    styleable.setDefaultFont(font);
  }
  
  public static void setOpaque(Styleable styleable, boolean isOpaque, boolean inited){
    Styles.ensureNoneStyle(styleable, inited);
    styleable.setDefaultOpaque(isOpaque);
  }
  
  public static Color getBackground(Styleable styleable, boolean inited){
    if (inited){
      return styleable.getStyle().getBackground();
    } else {
      return styleable.getDefaultBackground();
    }
  }
  
  public static Color getForeground(Styleable styleable, boolean inited){
    if (inited){
      return styleable.getStyle().getForeground();
    } else {
      return styleable.getDefaultForeground();
    }
  }
  
  public static Font getFont(Styleable styleable, boolean inited){
    if (inited){
      return styleable.getStyle().getFont();
    } else {
      return styleable.getDefaultFont();
    }
  }
  
  public static boolean isOpaque(Styleable styleable, boolean inited){
    if (inited){
      return styleable.getStyle().isOpaque();
    } else {
      return styleable.isDefaultOpaque();
    }
  }
  
  public static String getParentStyleName(Locale locale){
    return ResourceBundle.getBundle(STYLES_RB_BASE_NAME, locale).getString("parentStyle");
  }
  
  public static String getNoneStyleName(Locale locale){
    return ResourceBundle.getBundle(STYLES_RB_BASE_NAME, locale).getString("noneStyle");
  }
  
}
