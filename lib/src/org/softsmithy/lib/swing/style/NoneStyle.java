/*
 * AbstractNoneStyle.java
 *
 * Created on 11. Juli 2003, 18:37
 */

package org.softsmithy.lib.swing.style;

import java.awt.*;
import java.util.*;
import org.softsmithy.lib.swing.customizer.*;

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
