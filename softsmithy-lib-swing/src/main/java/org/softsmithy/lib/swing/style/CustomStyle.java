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

/*
 * ComponentStyle.java
 *
 * Created on 9. Januar 2003, 17:35
 */

package org.softsmithy.lib.swing.style;

import java.awt.*;
import java.util.Locale;

/**
 *
 * @author  puce
 */
public class CustomStyle extends AbstractStyle{
  
  private StyleProvider styleProvider = new CustomStyleProvider(this);
  
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
  @Override
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
  @Override
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
  @Override
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
  @Override
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
  
  @Override
  public String getName(Locale locale) {
    return getName();
  }
  
  @Override
  public StyleProvider getStyleProvider() {
    return styleProvider;
  }
  
  /** Setter for property styleProvider.
   * @param styleProvider New value of property styleProvider.
   *
   */
  public void setStyleProvider(StyleProvider styleProvider) {
    this.styleProvider = styleProvider;
  }
  
}
