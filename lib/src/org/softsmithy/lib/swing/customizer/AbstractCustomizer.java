/*
 * AbstractCustomizer.java
 *
 * Created on 6. Februar 2003, 17:45
 */

package org.softsmithy.lib.swing.customizer;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public abstract class AbstractCustomizer extends JPanel {
  
  /** Holds value of property customizableProperties. */
  private Set customizableProperties = Collections.EMPTY_SET;
  
  /** Holds value of property noneStyle. */
  private final Style noneStyle = new NoneStyle();
  
  /** Holds value of property parentStyle. */
  private final Style parentStyle = new ParentStyle();
  
  /** Holds value of property style. */
  private Style style = noneStyle;
  
  private boolean inited = false;
  
  /** Creates a new instance of AbstractCustomizer */
  public AbstractCustomizer() {
    inited = true;
  }
  
  /** Getter for property customizableProperties.
   * @return Value of property customizableProperties.
   *
   */
  public Set getCustomizableProperties() {
    return this.customizableProperties;
  }
  
  /** Setter for property customizableProperties.
   * @param customizableProperties New value of property customizableProperties.
   *
   */
  public void setCustomizableProperties(Set customizableProperties) {
    this.customizableProperties = customizableProperties;
  }
  
  // better place for this method?
  // really on AbstractCustomizer or just on subclasses?
  public static Set getCommonCustomizableProperties(Collection customizers) {
    Set properties = Collections.EMPTY_SET;
    Iterator i = customizers.iterator();
    if (i.hasNext()){
      AbstractCustomizer customizer = (AbstractCustomizer) i.next();
      properties = new LinkedHashSet(customizer.getCustomizableProperties());
      for (;i.hasNext();){
        AbstractCustomizer custom = (AbstractCustomizer) i.next();
        properties.retainAll(custom.getCustomizableProperties());
      }
    }
    return properties;
  }
  
  /** Getter for property style.
   * @return Value of property style.
   *
   */
  public Style getStyle() {
    return this.style;
  }
  
  /** Setter for property style.
   * @param style New value of property style.
   *
   */
  public void setStyle(Style style) {
    Style oldStyle = this.style;
    if (style == null){
      this.style = getNoneStyle();
    } else {
      this.style = style.getStyleProvider().getStyle(this);
    }
    setBackgroundOnly(this.style.getBackground());
    setForegroundOnly(this.style.getForeground());
    setFontOnly(this.style.getFont());
    setOpaqueOnly(this.style.isOpaque());
    firePropertyChange("style", oldStyle, this.style);
  }
  
  private void ensureNoneStyle(){
    if (inited && ! style.isNull()){
      setStyle(getNoneStyle());
    }
  }
  
  /** Getter for property noneStyle.
   * @return Value of property noneStyle.
   *
   */
  public Style getNoneStyle() {
    return this.noneStyle;
  }
  
  /** Getter for property parentStyle.
   * @return Value of property parentStyle.
   *
   */
  public Style getParentStyle() {
    return this.parentStyle;
  }
  
  /** Gets the background color of this component.
   * @return this component's background color; if this component does
   * 		not have a background color,
   * 		the background color of its parent is returned
   * @see #setBackground
   * @since JDK1.0
   *
   */
  public Color getBackground() {
    if (inited){
      return getStyle().getBackground();
    } else {
      return super.getBackground();
    }
    //return (fComponent != null) ? fComponent.getBackground() : super.getBackground();
  }
  
  /** Sets the background color of this component.
   *
   * @param bg the desired background <code>Color</code>
   * @see java.awt.Component#getBackground
   *
   * @beaninfo
   *    preferred: true
   *        bound: true
   *    attribute: visualUpdate true
   *  description: The background color of the component.
   *
   */
  public void setBackground(Color bg) {
    ensureNoneStyle();
    setBackgroundOnly(bg);
  }
  
  protected void setBackgroundOnly(Color bg){
    super.setBackground(bg);
  }
  
  /** Gets the foreground color of this component.
   * @return this component's foreground color; if this component does
   * not have a foreground color, the foreground color of its parent
   * is returned
   * @see #setForeground
   * @since JDK1.0
   * @beaninfo
   *       bound: true
   *
   */
  public Color getForeground() {
    if (inited){
    return getStyle().getForeground();
    } else {
      return super.getForeground();
    }
    //return (fComponent != null) ? fComponent.getForeground() : super.getForeground();
  }
  
  
  /** Sets the foreground color of this component.
   *
   * @param fg  the desired foreground <code>Color</code>
   * @see java.awt.Component#getForeground
   *
   * @beaninfo
   *    preferred: true
   *        bound: true
   *    attribute: visualUpdate true
   *  description: The foreground color of the component.
   *
   */
  public void setForeground(Color fg) {
    ensureNoneStyle();
    setForegroundOnly(fg);
  }
  
  protected void setForegroundOnly(Color fg){
    super.setForeground(fg); // to update listeners etc.
  }
  
  /** Gets the font of this component.
   * @return this component's font; if a font has not been set
   * for this component, the font of its parent is returned
   * @see #setFont
   * @since JDK1.0
   *
   */
  public Font getFont() {
    if (inited){
    return getStyle().getFont();
    } else {
      return super.getFont();
    }
    //return (fComponent != null) ? fComponent.getFont() : super.getFont();
  }
  
  /** Sets the font for this component.
   *
   * @param the desired <code>Font</code> for this component
   * @see java.awt.Component#getFont
   *
   * @beaninfo
   *    preferred: true
   *        bound: true
   *    attribute: visualUpdate true
   *  description: The font for the component.
   *
   */
  public void setFont(Font font) {
    ensureNoneStyle();
    setFontOnly(font);
  }
  
  protected void setFontOnly(Font font){
    super.setFont(font);
  }
  
  /** Returns true if this component is completely opaque.
   * <p>
   * An opaque component paints every pixel within its
   * rectangular bounds. A non-opaque component paints only a subset of
   * its pixels or none at all, allowing the pixels underneath it to
   * "show through".  Therefore, a component that does not fully paint
   * its pixels provides a degree of transparency.
   * <p>
   * Subclasses that guarantee to always completely paint their contents
   * should override this method and return true.
   *
   * @return true if this component is completely opaque
   * @see #setOpaque
   *
   */
  public boolean isOpaque() {
    if (inited){
    return getStyle().isOpaque();
    } else {
      return super.isOpaque();
    }
    //return (fComponent != null) ? fComponent.isOpaque() : super.isOpaque();
  }
  
  /** If true the component paints every pixel within its bounds.
   * Otherwise, the component may not paint some or all of its
   * pixels, allowing the underlying pixels to show through.
   * <p>
   * The default value of this property is false for <code>JComponent</code>.
   * However, the default value for this property on most standard
   * <code>JComponent</code> subclasses (such as <code>JButton</code> and
   * <code>JTree</code>) is look-and-feel dependent.
   *
   * @param isOpaque  true if this component should be opaque
   * @see #isOpaque
   * @beaninfo
   *        bound: true
   *       expert: true
   *  description: The component's opacity
   *
   */
  public void setOpaque(boolean isOpaque) {
    ensureNoneStyle();
    setOpaqueOnly(isOpaque);
  }
  
  
  
  protected void setOpaqueOnly(boolean isOpaque){
    super.setOpaque(isOpaque);
  }
  
  
  
  protected class NoneStyle implements Style{
    
    public Color getBackground() {
      return AbstractCustomizer.super.getBackground();
    }
    
    public Font getFont() {
      return AbstractCustomizer.super.getFont();
    }
    
    public Color getForeground() {
      return AbstractCustomizer.super.getForeground();
    }
    
    public String getName(Locale locale) {
      return Customizers.getNoneStyleName(locale);
    }
    
    public boolean isOpaque() {
      return AbstractCustomizer.super.isOpaque();
    }
    
    public boolean isNull() {
      return true;
    }
    
    public StyleProvider getStyleProvider(){
      return NoneStyleProvider.INSTANCE;
    }
    
  }
  
  private class ParentStyle extends AbstractStyle{
    
    public Color getBackground() {
      return AbstractCustomizer.this.getParent().getBackground();
    }
    
    public Font getFont() {
      return AbstractCustomizer.this.getParent().getFont();
    }
    
    public Color getForeground() {
      return AbstractCustomizer.this.getParent().getForeground();
    }
    
    public String getName(Locale locale) {
      return Customizers.getParentStyleName(locale);
    }
    
    public boolean isOpaque() {
      return AbstractCustomizer.this.getParent().isOpaque();
    }
    
    public StyleProvider getStyleProvider(){
      return ParentStyleProvider.INSTANCE;
    }
    
  }
  
}
