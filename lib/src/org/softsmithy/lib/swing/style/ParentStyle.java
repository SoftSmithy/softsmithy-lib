/*
 * ParentStyle.java
 *
 * Created on 11. Juli 2003, 18:15
 */

package org.softsmithy.lib.swing.style;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import org.softsmithy.lib.swing.customizer.*;

/**
 *
 * @author  puce
 */
public class ParentStyle extends AbstractStyle{
  private HierarchyListener parentListener = new ParentListener();
  private PropertyChangeListener parentBackgroundListener = new ParentBackgroundListener();
  private PropertyChangeListener parentForegroundListener = new ParentForegroundListener();
  private PropertyChangeListener parentFontListener = new ParentFontListener();
  private PropertyChangeListener parentOpaqueListener = new ParentOpaqueListener();
  
  /** Holds value of property styleable. */
  private final Styleable styleable;
  
  public ParentStyle(Styleable styleable){
    this.styleable = styleable;
  }
  
  public Color getBackground() {
    return getStyleable().getParent() != null ? getStyleable().getParent().getBackground() :
      getStyleable().getNoneStyle().getBackground();
  }
  
  public Font getFont() {
    return getStyleable().getParent() != null ? getStyleable().getParent().getFont() :
      getStyleable().getNoneStyle().getFont();
  }
  
  public Color getForeground() {
    return getStyleable().getParent() != null ? getStyleable().getParent().getForeground() :
      getStyleable().getNoneStyle().getForeground();
  }
  
  public String getName(Locale locale) {
    return Styles.getParentStyleName(locale);
  }
  
  public boolean isOpaque() {
    return getStyleable().getParent() != null ? getStyleable().getParent().isOpaque() :
      getStyleable().getNoneStyle().isOpaque();
  }
  
  public StyleProvider getStyleProvider(){
    return ParentStyleProvider.INSTANCE;
  }
  
  public void startListening() {
    super.startListening();
    getStyleable().addHierarchyListener(parentListener);
    if (getStyleable().getParent() != null){
      getStyleable().getParent().addPropertyChangeListener("background", parentBackgroundListener);
      getStyleable().getParent().addPropertyChangeListener("foreground", parentForegroundListener);
      getStyleable().getParent().addPropertyChangeListener("font", parentFontListener);
      getStyleable().getParent().addPropertyChangeListener("opaque", parentOpaqueListener);
    }
    
  }
  
  public void stopListening() {
    super.stopListening();
    getStyleable().removeHierarchyListener(parentListener);
    if (getStyleable().getParent() != null){
      getStyleable().getParent().removePropertyChangeListener("background", parentBackgroundListener);
      getStyleable().getParent().removePropertyChangeListener("foreground", parentForegroundListener);
      getStyleable().getParent().removePropertyChangeListener("font", parentFontListener);
      getStyleable().getParent().removePropertyChangeListener("opaque", parentOpaqueListener);
    }
  }
  
  /** Getter for property styleable.
   * @return Value of property styleable.
   *
   */
  public Styleable getStyleable() {
    return this.styleable;
  }
  
  private class ParentListener implements HierarchyListener{
    
    /** Called when the hierarchy has been changed. To discern the actual
     * type of change, call <code>HierarchyEvent.getChangeFlags()</code>.
     *
     * @see HierarchyEvent#getChangeFlags()
     *
     */
    public void hierarchyChanged(HierarchyEvent e) {
      System.out.println("hierarchyChanged");
      // optimize??
      getStyleable().getParent().removePropertyChangeListener("background", parentBackgroundListener);
      getStyleable().getParent().removePropertyChangeListener("foreground", parentForegroundListener);
      getStyleable().getParent().removePropertyChangeListener("font", parentFontListener);
      getStyleable().getParent().removePropertyChangeListener("opaque", parentOpaqueListener);
      getStyleable().getParent().addPropertyChangeListener("background", parentBackgroundListener);
      getStyleable().getParent().addPropertyChangeListener("foreground", parentForegroundListener);
      getStyleable().getParent().addPropertyChangeListener("font", parentFontListener);
      getStyleable().getParent().addPropertyChangeListener("opaque", parentOpaqueListener);
    }
    
  }
  
  private class ParentBackgroundListener implements PropertyChangeListener{
    
    /** This method gets called when a bound property is changed.
     * @param evt A PropertyChangeEvent object describing the event source
     *   	and the property that has changed.
     *
     */
    public void propertyChange(PropertyChangeEvent evt) {
      getStyleable().setDefaultBackground((Color) evt.getNewValue());
    }
    
  }
  private class ParentForegroundListener implements PropertyChangeListener{
    
    /** This method gets called when a bound property is changed.
     * @param evt A PropertyChangeEvent object describing the event source
     *   	and the property that has changed.
     *
     */
    public void propertyChange(PropertyChangeEvent evt) {
      getStyleable().setDefaultForeground((Color) evt.getNewValue());
    }
    
  }
  
  private class ParentFontListener implements PropertyChangeListener{
    
    /** This method gets called when a bound property is changed.
     * @param evt A PropertyChangeEvent object describing the event source
     *   	and the property that has changed.
     *
     */
    public void propertyChange(PropertyChangeEvent evt) {
      getStyleable().setDefaultFont((Font) evt.getNewValue());
    }
    
  }
  
  private class ParentOpaqueListener implements PropertyChangeListener{
    
    /** This method gets called when a bound property is changed.
     * @param evt A PropertyChangeEvent object describing the event source
     *   	and the property that has changed.
     *
     */
    public void propertyChange(PropertyChangeEvent evt) {
      getStyleable().setDefaultOpaque(((Boolean) evt.getNewValue()).booleanValue());
    }
    
  }
}