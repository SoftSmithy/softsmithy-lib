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
 * AbstractNoneStyle.java
 *
 * Created on 11. Juli 2003, 18:37
 */

package org.softsmithy.lib.swing.style;

import java.awt.*;
import java.util.*;

/**
 *
 * @author  puce
 */
public class NoneStyle implements Style {
  
  /** Holds value of property styleable. */
  private final Styleable styleable;
  
  /** Creates a new instance of AbstractNoneStyle */
  public NoneStyle(Styleable styleable) {
    this.styleable = styleable;
  }
  
  public Color getBackground() {
    return getStyleable().getDefaultBackground();
  }
  
  public Font getFont() {
    return getStyleable().getDefaultFont();
  }
  
  public Color getForeground() {
    return getStyleable().getDefaultForeground();
  }
  
  public boolean isOpaque() {
    return getStyleable().isDefaultOpaque();
  }
  
  public String getName(Locale locale) {
    return Styles.getNoneStyleName(locale);
  }
  
  public boolean isNull() {
    return true;
  }
  
  public StyleProvider getStyleProvider(){
    return NoneStyleProvider.INSTANCE;
  }
  
  public void startListening() {
  }
  
  public void stopListening() {
  }
  
  /** Getter for property styleable.
   * @return Value of property styleable.
   *
   */
  public Styleable getStyleable() {
    return this.styleable;
  }
  
}
