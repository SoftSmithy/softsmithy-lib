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
 * JImageCustomizer.java
 *
 * Created on 12. September 2002, 15:01
 */

package org.softsmithy.lib.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import org.softsmithy.lib.awt.*;
import org.softsmithy.lib.awt.event.*;

/**
 *
 * @author  puce
 */
abstract class JIconCustomizer extends JCustomizer { // should it be renamed to IconCustomizer because it is abstract
  
  
  private final ComponentLayoutListener imageUpdateListener = new ImageUpdateListener();
  
  /** Creates a new instance of JImageCustomizer */
  public JIconCustomizer() {
    this(new JLabel());
    initForDefaultLabel();
  }
  
  public JIconCustomizer(JLabel label){
    super(label);
  }
  
  public JIconCustomizer(Icon icon){
    this(new JLabel(icon));
    initForDefaultLabel();
  }
  
  private void initForDefaultLabel(){
    setBackground(Color.WHITE);
  }
  
  /** Getter for property imageSrc.
   * @return Value of property imageSrc.
   *
   */
  public Icon getIcon() {
    return ((JLabel) getComponent()).getIcon();
  }
  
  
  
  /** Setter for property fComponent.
   * @param fComponent New value of property fComponent.
   *
   */
  public void setComponent(JComponent component) {
    if (! (component instanceof JLabel)){
      throw new IllegalArgumentException("comp must be a JLabel");
    }
    JLabel label = (JLabel) component;
    super.setComponent(label);
    adjustIcon();
  }
  
  public void setLabel(JLabel label){
    setComponent(label);
  }
  
  public JLabel getLabel(){
    return (JLabel) getComponent();
  }
  
  public abstract void adjustIcon();
  
  protected void reshapeRelOnly(int dx, int dy, int dwidth, int dheight) {
    super.reshapeRelOnly(dx, dy, dwidth, dheight);
    adjustIcon();
  }
  
  public void addNotify() {
    super.addNotify();
    getParentCustomizerPane().addComponentLayoutListener(this, imageUpdateListener);
  }
  
  public void removeNotify() {
    getParentCustomizerPane().removeComponentLayoutListener(this, imageUpdateListener);
    super.removeNotify();
  }
  
  public void applyBorder(Border border) {
    super.applyBorder(border);
    adjustIcon();
  }
  
  private class ImageUpdateListener implements ComponentLayoutListener{
    
    public void componentLayouted(ComponentLayoutEvent e) {
      adjustIcon();
    }
    
  }
  
}
