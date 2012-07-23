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
 * JImageCustomizer.java
 *
 * Created on 12. September 2002, 15:01
 */

package org.softsmithy.lib.swing;

import java.awt.*;
import javax.swing.*;
import org.softsmithy.lib.swing.icon.*;

/**
 * JXIconCustomizer allows visual scaling of icons.
 * @author  puce
 */
public class JXIconCustomizer extends JCustomizer { // should it be renamed to IconCustomizer because it is abstract
  
  
  //  private final ComponentLayoutListener iconUpdateListener = new IconUpdateListener();
  
  /** Creates a new instance of this class */
  public JXIconCustomizer() {
    this(new JXIconLabel());
    getXIconLabel().setText(null);    
    getXIconLabel().setHorizontalAlignment(SwingConstants.CENTER);
    initForDefaultLabel();
  }
  
  /** Creates a new instance of this class */
  public JXIconCustomizer(JXIconLabel label){
    super(label);
  }
  
  /** Creates a new instance of this class */
  public JXIconCustomizer(XIcon icon){
    this(new JXIconLabel(icon));
    initForDefaultLabel();
  }
  
  private void initForDefaultLabel(){
    setBackground(Color.WHITE);
    getXIconLabel().setZoomingStrategy(FullZooming.NON_RESPECTING_ASPECT_RATIO_INSTANCE);
  }
  
  /** Getter for property imageSrc.
   * @return Value of property imageSrc.
   *
   */
  public XIcon getXIcon() {
    return getXIconLabel().getXIcon();
  }
  
  public void setXIcon(XIcon icon){
    getXIconLabel().setXIcon(icon);
  }
  
  
  
  /** Setter for property fComponent.
   * @param fComponent New value of property fComponent.
   *
   */
  @Override
  public void setComponent(JComponent component) {
    if (! (component instanceof JXIconLabel)){
      throw new IllegalArgumentException("comp must be a JXIconLabel");
    }
    JXIconLabel label = (JXIconLabel) component;
    super.setComponent(label);
    //    adjustIcon();
  }
  
  public void setXIconLabel(JXIconLabel label){
    setComponent(label);
  }
  
  public JXIconLabel getXIconLabel(){
    return (JXIconLabel) getComponent();
  }
  
  //  public void adjustIcon(){
  //    getXIconLabel().resizeIcon();
  //  }
  //
  //  protected void reshapeRelOnly(int dx, int dy, int dwidth, int dheight) {
  //    super.reshapeRelOnly(dx, dy, dwidth, dheight);
  //    adjustIcon();
  //  }
  //
  //  public void addNotify() {
  //    super.addNotify();
  //    getParentCustomizerPane().addComponentLayoutListener(this, iconUpdateListener);
  //  }
  //
  //  public void removeNotify() {
  //    getParentCustomizerPane().removeComponentLayoutListener(this, iconUpdateListener);
  //    super.removeNotify();
  //  }
  //
  //  public void applyBorder(Border border) {
  //    super.applyBorder(border);
  //    if (getComponent() != null){
  //      adjustIcon();
  //    }
  //  }
  //
  //  private class IconUpdateListener implements ComponentLayoutListener{
  //
  //    public void componentLayouted(ComponentLayoutEvent e) {
  //      adjustIcon();
  //    }
  //
  //  }
  
}
