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
 * JXViewport.java
 *
 * Created on 31. Juli 2003, 16:34
 */

package org.softsmithy.lib.swing;

import org.softsmithy.lib.swing.internal.TableLayout;
import org.softsmithy.lib.swing.internal.TableLayoutConstraints;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author  puce
 */
public class JXViewport extends JViewport {
  
  private static final TableLayoutConstraints CONSTRAINTS = new TableLayoutConstraints();
  private static final Dimension defaultDimension = new Dimension(0, 0);
  
  private final JStyledPanel viewPane = new JStyledPanel();
  private Component viewComponent = null;
  
  /** Holds value of property viewGlassed. */
  private boolean viewGlassed = false;
  
  /** Holds value of property viewGlassPane. */
  private JComponent viewGlassPane = new JXLabel();
  
  /** Creates a new instance of JXViewport */
  public JXViewport() {
    viewPane.setLayout(new TableLayout(new double[][]{{TableLayout.FILL}, {TableLayout.FILL}}));
    viewPane.setStyle(viewPane.getParentStyle());
    //    viewPane.setBackground(Color.RED);
    //    viewPane.setOpaque(true);
    //    //viewPane.setPreferredSize(new Dimension(200, 200));
    //    viewPane.setSize(new Dimension(200, 200));
    //viewPane.setMinimumSize(new Dimension(200, 200));
    //viewPane.setOpaque(false);
    super.setView(viewPane);
  }
  
  @Override
  public void setView(Component viewComponent) {
    throw new UnsupportedOperationException("Use 'setViewComponent' instead!");
  }
  
  public Component getViewComponent() {
    return viewComponent;
  }
  
  
  public void setViewComponent(Component viewComponent) {
    if (this.viewComponent != null){
      viewPane.remove(this.viewComponent);
    }
    if (viewComponent != null){
      viewPane.add(viewComponent, CONSTRAINTS);
      //      viewPane.setPreferredSize(view.getPreferredSize());
      //      viewPane.setMinimumSize(view.getMinimumSize());
      //      viewPane.setMaximumSize(view.getMaximumSize());
      //      viewPane.setSize(view.getSize().equals(defaultDimension) ? view.getPreferredSize() : view.getSize());
    } else {
      
      //            viewPane.setPreferredSize(defaultDimension);
      //      viewPane.setMinimumSize(defaultDimension);
      //      viewPane.setSize(defaultDimension);
    }
    this.viewComponent = viewComponent;
//    viewPane.getLayout().layoutContainer(viewPane);
//    viewPane.revalidate();
//    revalidate();
//    repaint();
  }
  
  /** Getter for property viewGlassed.
   * @return Value of property viewGlassed.
   *
   */
  public boolean isViewGlassed() {
    return this.viewGlassed;
  }
  
  /** Setter for property viewGlassed.
   * @param viewGlassed New value of property viewGlassed.
   *
   */
  public void setViewGlassed(boolean viewGlassed) {
    if (viewGlassed && viewGlassPane != null){
      viewPane.add(viewGlassPane, CONSTRAINTS, 0);
      this.viewGlassed = viewGlassed;
    } else {
      if (this.viewGlassed){
        viewPane.remove(viewGlassPane);
        this.viewGlassed = false;
      }
    }
    viewPane.validate(); // seems to be necessary!???
    revalidate();
    repaint();
  }
  
  /** Getter for property viewGlassPane.
   * @return Value of property viewGlassPane.
   *
   */
  public JComponent getViewGlassPane() {
    return this.viewGlassPane;
  }
  
  /** Setter for property viewGlassPane.
   * @param viewGlassPane New value of property viewGlassPane.
   *
   */
  public void setViewGlassPane(JComponent viewGlassPane) {
    if (isViewGlassed()){
      setViewGlassed(false);
      this.viewGlassPane = viewGlassPane;
      setViewGlassed(true);
    } else {
      this.viewGlassPane = viewGlassPane;
    }
  }
  
  @Override
  public void setViewSize(Dimension newSize) {
    super.setViewSize(newSize);
    Dimension oldSize = viewPane.getSize();
    if (!newSize.equals(oldSize)) {
      viewPane.setSize(newSize);
    }
  }
  
  @Override
  public void setViewPosition(Point p) {
    super.setViewPosition(p);
  }
  
}
