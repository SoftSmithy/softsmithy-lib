/*
 * ComponentStyle.java
 *
 * Created on 9. Januar 2003, 17:35
 */

package org.softsmithy.lib.swing.customizer;

import java.awt.*;
import java.util.*;

/**
 *
 * @author  puce
 */
public class CustomStyle extends AbstractStyle{
  
  private final StyleProvider styleProvider = new CustomStyleProvider(this);
  
  private String name;
  
  /** Holds value of property foreground. */
  private Color foreground;
  
  /** Holds value of property background. */
  private Color background;
  
  /** Holds value of property opaque. */
  private boolean opaque;
  
  /** Holds value of property font. */
  private Font font;
  
  /** Creates a new instance of ComponentStyle */
  public CustomStyle() {
  }
  
  /** Getter for property foreground.
   * @return Value of property foreground.
   *
   */
  public Color getForeground() {
    return this.foreground;
  }
  
  /** Setter for property foreground.
   * @param foreground New value of property foreground.
   *
   */
  public void setForeground(Color foreground) {
    this.foreground = foreground;
  }
  
  /** Getter for property background.
   * @return Value of property background.
   *
   */
  public Color getBackground() {
    return this.background;
  }
  
  /** Setter for property background.
   * @param background New value of property background.
   *
   */
  public void setBackground(Color background) {
    this.background = background;
  }
  
  /** Getter for property opaque.
   * @return Value of property opaque.
   *
   */
  public boolean isOpaque() {
    return this.opaque;
  }
  
  /** Setter for property opaque.
   * @param opaque New value of property opaque.
   *
   */
  public void setOpaque(boolean opaque) {
    this.opaque = opaque;
  }
  
  /** Getter for property font.
   * @return Value of property font.
   *
   */
  public Font getFont() {
    return this.font;
  }
  
  /** Setter for property font.
   * @param font New value of property font.
   *
   */
  public void setFont(Font font) {
    this.font = font;
  }
  
  public String getName() {
    return name;
  }
  
  /** Setter for property name.
   * @param name New value of property name.
   *
   */
  public void setName(String name) {
    this.name = name;
  }
  
  public String getName(Locale locale) {
    return getName();
  }
  
  public StyleProvider getStyleProvider() {
    return styleProvider;
  }
  
}
