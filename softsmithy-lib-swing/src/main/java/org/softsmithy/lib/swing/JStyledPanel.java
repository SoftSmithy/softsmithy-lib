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
 * JStyledPanel.java
 *
 * Created on 5. Mai 2003, 14:31
 */
package org.softsmithy.lib.swing;

import org.softsmithy.lib.swing.style.NoneStyle;
import org.softsmithy.lib.swing.style.ParentStyle;
import org.softsmithy.lib.swing.style.Style;
import org.softsmithy.lib.swing.style.Styleable;
import org.softsmithy.lib.swing.style.Styles;

import javax.swing.*;
import java.awt.*;

/**
 * A styleable panel.
 *
 * @author puce
 */
public class JStyledPanel extends JPanel implements Styleable {

    /**
     * The none (null) style.
     */
    private final Style noneStyle = new NoneStyle(this);
    /**
     * The parent style.
     */
    private final Style parentStyle = new ParentStyle(this);
    /**
     * The style of this panel.
     */
    private Style style = noneStyle;
    private boolean inited = false;

    /**
     * Creates a new instance of this class.
     */
    public JStyledPanel() {
        inited = true;
    }

    /**
     * Gets the style.
     *
     * @return Value of property style.
     */
    @Override
    public Style getStyle() {
        return this.style;
    }

    /**
     * Sets the style.
     *
     * @param style the style
     */
    @Override
    public void setStyle(Style style) {
        Style oldStyle = this.style;
        this.style = Styles.mutateStyle(this, style);
        Styles.applyStyle(this, oldStyle, this.style);
        firePropertyChange("style", oldStyle, this.style);
    }

    /**
     * Gets the none style.
     *
     * @return the none style
     */
    @Override
    public Style getNoneStyle() {
        return this.noneStyle;
    }

    /**
     * Gets the parent style.
     *
     * @return the parent style
     */
    @Override
    public Style getParentStyle() {
        return this.parentStyle;
    }

    /**
     * Gets the background color of this component.
     *
     * @return this component's background color; if this component does not have a background color, the background
     * color of its parent is returned
     * @see #setBackground
     *
     */
    @Override
    public Color getBackground() {
        return Styles.getBackground(this, inited);
        //return (fComponent != null) ? fComponent.getBackground() : super.getBackground();
    }

    /**
     * Sets the background color of this component.
     *
     * @param bg the desired background <code>Color</code>
     * @see java.awt.Component#getBackground
     *
     */
     // @beaninfo preferred: true bound: true attribute: visualUpdate true description: The background color of the
    // component.
    @Override
    public void setBackground(Color bg) {
//    System.out.println("Set background!!!!!!!!!!!!!!!!!!");
//    System.out.println("Old bg: " + getBackground());
//    System.out.println("New bg: " + bg);
        Styles.setBackground(this, bg, inited);
    }

    @Override
    public void setDefaultBackground(Color bg) {
//    System.out.println("Set default background!!!!!!!!!!!!!!!!!!");
//    System.out.println("Old bg: " + getBackground());
//    System.out.println("New bg: " + bg);
        super.setBackground(bg);
    }

    /**
     * Gets the foreground color of this component.
     *
     * @return this component's foreground color; if this component does not have a foreground color, the foreground
     * color of its parent is returned
     * @see #setForeground
     */
// @beaninfo bound: true
    @Override
    public Color getForeground() {
        return Styles.getForeground(this, inited);
        //return (fComponent != null) ? fComponent.getForeground() : super.getForeground();
    }

    /**
     * Sets the foreground color of this component.
     *
     * @param fg the desired foreground <code>Color</code>
     * @see java.awt.Component#getForeground
     */
//     * @beaninfo preferred: true bound: true attribute: visualUpdate true description: The foreground color of the
//     * component.
    @Override
    public void setForeground(Color fg) {
        Styles.setForeground(this, fg, inited);
    }

    @Override
    public void setDefaultForeground(Color fg) {
        super.setForeground(fg); // to update listeners etc.
    }

    /**
     * Gets the font of this component.
     *
     * @return this component's font; if a font has not been set for this component, the font of its parent is returned
     * @see #setFont
     * @since JDK1.0
     *
     */
    @Override
    public Font getFont() {
        return Styles.getFont(this, inited);
        //return (fComponent != null) ? fComponent.getFont() : super.getFont();
    }

    /**
     * Sets the font for this component.
     *
     * @param font the desired <code>Font</code> for this component
     * @see java.awt.Component#getFont
     *
     */
//         * @beaninfo preferred: true bound: true attribute: visualUpdate true description: The font for the component.
    @Override
    public void setFont(Font font) {
        Styles.setFont(this, font, inited);
    }

    @Override
    public void setDefaultFont(Font font) {
        super.setFont(font);
    }

    /**
     * Returns true if this component is completely opaque. <p> An opaque component paints every pixel within its
     * rectangular bounds. A non-opaque component paints only a subset of its pixels or none at all, allowing the pixels
     * underneath it to "show through". Therefore, a component that does not fully paint its pixels provides a degree of
     * transparency. <p> Subclasses that guarantee to always completely paint their contents should override this method
     * and return true.
     *
     * @return true if this component is completely opaque
     * @see #setOpaque
     *
     */
    @Override
    public boolean isOpaque() {
        return Styles.isOpaque(this, inited);
        //return (fComponent != null) ? fComponent.isOpaque() : super.isOpaque();
    }

    /**
     * If true the component paints every pixel within its bounds. Otherwise, the component may not paint some or all of
     * its pixels, allowing the underlying pixels to show through. <p> The default value of this property is false for
     * <code>JComponent</code>. However, the default value for this property on most standard
     * <code>JComponent</code> subclasses (such as
     * <code>JButton</code> and
     * <code>JTree</code>) is look-and-feel dependent.
     *
     * @param isOpaque true if this component should be opaque
     * @see #isOpaque
     *
     */
//         * @beaninfo bound: true expert: true description: The component's opacity
    @Override
    public void setOpaque(boolean isOpaque) {
        Styles.setOpaque(this, isOpaque, inited);
    }

    @Override
    public void setDefaultOpaque(boolean isOpaque) {
        super.setOpaque(isOpaque);
    }

    @Override
    public Color getDefaultBackground() {
        return super.getBackground();
    }

    @Override
    public Font getDefaultFont() {
        return super.getFont();
    }

    @Override
    public Color getDefaultForeground() {
        return super.getForeground();
    }

    @Override
    public boolean isDefaultOpaque() {
        return super.isOpaque();
    }
//  protected class NoneStyle extends AbstractNoneStyle{
//    
//
//    
//  }
//  public class ParentStyle extends AbstractStyle{
//    private HierarchyListener parentListener = new ParentListener();
//    private PropertyChangeListener parentBackgroundListener = new ParentBackgroundListener();
//    private PropertyChangeListener parentForegroundListener = new ParentForegroundListener();
//    private PropertyChangeListener parentFontListener = new ParentFontListener();
//    private PropertyChangeListener parentOpaqueListener = new ParentOpaqueListener();
//    
//    public Color getBackground() {
//      return JStyledPanel.this.getParent() != null ? JStyledPanel.this.getParent().getBackground() :
//        JStyledPanel.this.getNoneStyle().getBackground();
//    }
//    
//    public Font getFont() {
//      return JStyledPanel.this.getParent() != null ? JStyledPanel.this.getParent().getFont() :
//        JStyledPanel.this.getNoneStyle().getFont();
//    }
//    
//    public Color getForeground() {
//      return JStyledPanel.this.getParent() != null ? JStyledPanel.this.getParent().getForeground() :
//        JStyledPanel.this.getNoneStyle().getForeground();
//    }
//    
//    public String getName(Locale locale) {
//      return Customizers.getParentStyleName(locale);
//    }
//    
//    public boolean isOpaque() {
//      return JStyledPanel.this.getParent() != null ? JStyledPanel.this.getParent().isOpaque() :
//        JStyledPanel.this.getNoneStyle().isOpaque();
//    }
//    
//    public StyleProvider getStyleProvider(){
//      return ParentStyleProvider.INSTANCE;
//    }
//    
//    public void startListening() {
//      super.startListening();
//      JStyledPanel.this.addHierarchyListener(parentListener);
//      if (JStyledPanel.this.getParent() != null){
//        JStyledPanel.this.getParent().addPropertyChangeListener("background", parentBackgroundListener);
//        JStyledPanel.this.getParent().addPropertyChangeListener("foreground", parentForegroundListener);
//        JStyledPanel.this.getParent().addPropertyChangeListener("font", parentFontListener);
//        JStyledPanel.this.getParent().addPropertyChangeListener("opaque", parentOpaqueListener);
//      }
//      
//    }
//    
//    public void stopListening() {
//      super.stopListening();
//      JStyledPanel.this.removeHierarchyListener(parentListener);
//      if (JStyledPanel.this.getParent() != null){
//        JStyledPanel.this.getParent().removePropertyChangeListener("background", parentBackgroundListener);
//        JStyledPanel.this.getParent().removePropertyChangeListener("foreground", parentForegroundListener);
//        JStyledPanel.this.getParent().removePropertyChangeListener("font", parentFontListener);
//        JStyledPanel.this.getParent().removePropertyChangeListener("opaque", parentOpaqueListener);
//      }
//    }
//    
//    private class ParentListener implements HierarchyListener{
//      
//      /** Called when the hierarchy has been changed. To discern the actual
//       * type of change, call <code>HierarchyEvent.getChangeFlags()</code>.
//       *
//       * @see HierarchyEvent#getChangeFlags()
//       *
//       */
//      public void hierarchyChanged(HierarchyEvent e) {
//        System.out.println("hierarchyChanged");
//        // optimize??
//        JStyledPanel.this.getParent().removePropertyChangeListener("background", parentBackgroundListener);
//        JStyledPanel.this.getParent().removePropertyChangeListener("foreground", parentForegroundListener);
//        JStyledPanel.this.getParent().removePropertyChangeListener("font", parentFontListener);
//        JStyledPanel.this.getParent().removePropertyChangeListener("opaque", parentOpaqueListener);
//        JStyledPanel.this.getParent().addPropertyChangeListener("background", parentBackgroundListener);
//        JStyledPanel.this.getParent().addPropertyChangeListener("foreground", parentForegroundListener);
//        JStyledPanel.this.getParent().addPropertyChangeListener("font", parentFontListener);
//        JStyledPanel.this.getParent().addPropertyChangeListener("opaque", parentOpaqueListener);
//      }
//      
//    }
//    
//    private class ParentBackgroundListener implements PropertyChangeListener{
//      
//      /** This method gets called when a bound property is changed.
//       * @param evt A PropertyChangeEvent object describing the event source
//       *   	and the property that has changed.
//       *
//       */
//      public void propertyChange(PropertyChangeEvent evt) {
//        JStyledPanel.this.setBackgroundOnly((Color) evt.getNewValue());
//      }
//      
//    }
//    private class ParentForegroundListener implements PropertyChangeListener{
//      
//      /** This method gets called when a bound property is changed.
//       * @param evt A PropertyChangeEvent object describing the event source
//       *   	and the property that has changed.
//       *
//       */
//      public void propertyChange(PropertyChangeEvent evt) {
//        JStyledPanel.this.setForegroundOnly((Color) evt.getNewValue());
//      }
//      
//    }
//    
//    private class ParentFontListener implements PropertyChangeListener{
//      
//      /** This method gets called when a bound property is changed.
//       * @param evt A PropertyChangeEvent object describing the event source
//       *   	and the property that has changed.
//       *
//       */
//      public void propertyChange(PropertyChangeEvent evt) {
//        JStyledPanel.this.setFontOnly((Font) evt.getNewValue());
//      }
//      
//    }
//    
//    private class ParentOpaqueListener implements PropertyChangeListener{
//      
//      /** This method gets called when a bound property is changed.
//       * @param evt A PropertyChangeEvent object describing the event source
//       *   	and the property that has changed.
//       *
//       */
//      public void propertyChange(PropertyChangeEvent evt) {
//        JStyledPanel.this.setOpaqueOnly(((Boolean) evt.getNewValue()).booleanValue());
//      }
//      
//    }
//  }
}
