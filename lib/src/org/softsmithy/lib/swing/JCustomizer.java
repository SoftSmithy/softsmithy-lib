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
 * JCustomizer.java
 *
 * Created on 19. August 2002, 13:45
 */

package org.softsmithy.lib.swing;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import org.softsmithy.lib.swing.customizer.*;
import org.softsmithy.lib.swing.event.*;
import org.softsmithy.lib.swing.style.*;
import org.softsmithy.lib.test.*;





/**
 * This component wraps another component to make it visually customizable.
 * Eg. the component can be moved or resized with the mouse.
 *
 * Note: JCustomizer expects a JCustomizerPane as its parent.
 * Note: If you're interested in the values of the properties "x", "y", "width"
 *       and "height", then you might want to register a CustomizerListener in
 *       addition to PropertyChangeListeners to listen for all changes, absolute
 *       and relative ones.
 *
 * @author  puce
 */
public class JCustomizer extends AbstractCustomizer {//implements CustomizerModelListener{
  
  private JComponent fComponent = new JPanel();
  private JPanel glassPane = new JPanel();
  
  private StateManager stateManager;
  private final Set listeners = new HashSet();
  private static final TableLayoutConstraints CONSTRAINTS = new TableLayoutConstraints();
  
  /** Holds value of property model. */
  //private CustomizerModel model;
  
  /** Creates a new instance of JCustomizer */
  public JCustomizer() {
    //setModel(new DefaultCustomizerModel());
    setLayout(new TableLayout(new double[][]{{TableLayout.FILL}, {TableLayout.FILL}}));//new BorderLayout());
    glassPane.setLayout(new BorderLayout());
    glassPane.setOpaque(false);
    add(glassPane, CONSTRAINTS);
    setStateManager(new StateManager(this));
    setRequestFocusEnabled(true);
    setCustomizableProperties(new LinkedHashSet(Arrays.asList(new String[] {"x", "y", "width", "height"}))); // to allow SelectionManager to listen for these properties
    //setStyle(getNoneStyle());
    
    
    //setComponent(new JLabel("testtest"));
    //this.setOpaque(false);
    
    //    Action deleteAction = new AbstractAction("delete") {
    //      public void actionPerformed(ActionEvent e) {
    //        ((JCustomizer) e.getSource()).fireCustomizerDelete(new CustomizerEvent(e.getSource(), 0, 0, 0, 0));
    //        System.out.println("From within JCustomizer");
    //        //        System.out.println(e.getSource() + " deleted!");
    //      }
    //    };
    //    getActionMap().put(deleteAction.getValue(Action.NAME),
    //    deleteAction);
    //
    //    getInputMap().put(KeyStroke.getKeyStroke("DELETE"), deleteAction.getValue(Action.NAME));
  }
  
  public JCustomizer(JComponent component){
    this();
    setComponent(component);
  }
  
  /** Getter for property fComponent.
   * @return Value of property fComponent.
   */
  public JComponent getComponent() {
    return fComponent;
  }
  
  /** Setter for property fComponent.
   * @param fComponent New value of property fComponent.
   */
  public void setComponent(JComponent component) {
    //removeAll();
    if (fComponent != null){
      this.remove(fComponent);
    }
    fComponent = component;
    //fComponent.setEnabled(false);
    //add(BorderLayout.CENTER, fComponent);
    add(fComponent, CONSTRAINTS);
    //??
    /*fComponent.addMouseListener(fManager);
    fComponent.addMouseMotionListener(fManager);
    fComponent.addFocusListener(fManager);*/
  }
  
  public StateManager getStateManager(){
    return stateManager;
  }
  
  protected void setStateManager(StateManager manager){
    if (stateManager != null){
      glassPane.removeMouseListener(stateManager);
      glassPane.removeMouseMotionListener(stateManager);
      removeFocusListener(stateManager);
      stateManager.unconfigureCustomizer();
    }
    stateManager = manager;
    glassPane.addMouseListener(stateManager);
    glassPane.addMouseMotionListener(stateManager);
    addFocusListener(stateManager);
    stateManager.configureCustomizer();
  }
  
  //  public void moveRel(int dx, int dy) {
  //    JCustomizerPane pane = (JCustomizerPane) getParent();
  //    TableLayout tl = (TableLayout) pane.getLayout();
  //    Point loc = calculateLocation(dx, dy);
  //    TableConstraints tc = tl.getConstraints(this);
  //    //Point location = tl.location(p);
  //
  //    tl.setConstraints(this,
  //    new DefaultTableConstraints(tl.columnIndex(loc.x), tl.rowIndex(loc.y), tc.getWidth(), tc.getHeight()));
  //    tl.layoutComponent(pane, this); //??
  //  }
  //
  //  public void resizeRel(int dwidth, int dheight){
  //    JCustomizerPane pane = (JCustomizerPane) getParent();
  //    InfiniteTableLayout tl = (InfiniteTableLayout) pane.getLayout();
  //    TableConstraints tc = tl.getConstraints(this);
  //    Dimension dim = calculateSize(dwidth, dheight);
  //    int colSpan = tl.colSpan(tc.getX(), dim.width);
  //    int rowSpan =  tl.rowSpan(tc.getY(), dim.height);
  //    tl.setConstraints(this,
  //    new DefaultTableConstraints(tc.getX(), tc.getY(), colSpan, rowSpan));
  //    tl.layoutComponent(pane, this);
  //  }
  
  //  public void reshapeRelOnly(int dx, int dy, int dwidth, int dheight) {
  //    getParentCustomizerPane().getCustomizerLayout().setAbsoluteBounds(this,
  //    calculateBounds(dx, dy, dwidth, dheight));
  //    //    CustomizerConstraints constr = cl.getCustomizerConstraints(this);
  //    //    constr.setAbsoluteBounds(bounds);
  //    //cl.setCustomizerConstraints(this, constr);
  //  }
  
  /**
   * Fires a CustomizerEvent (relative coordinates) but no PropertyChangeEvents (absolute coordinates)!
   */
  public void reshapeRel(int dx, int dy, int dwidth, int dheight) {
    reshapeRelOnly(dx, dy, dwidth, dheight);
    fireCustomizerReshapeRel(new CustomizerEvent(this, dx, dy, dwidth, dheight));
  }
  
  protected void reshapeRelOnly(int dx, int dy, int dwidth, int dheight){
    getParentCustomizerPane().setAbsoluteCustomizerBounds(this, calculateBounds(dx, dy, dwidth, dheight));
    //    reshapeRelOnly(dx, dy, dwidth, dheight);
    //    JCustomizerPane pane = (JCustomizerPane) getParent();
    //    CustomizerLayout cl = pane.getCustomizerLayout();
    //    cl.layoutCustomizer(pane, this);
    revalidate(); // seems to be necessary?!?
    repaint();
  }
  
  /**
   * Fires a CustomizerEvent (relative coordinates) but no PropertyChangeEvents (absolute coordinates)!
   */
  public void setBoundsRel(int dx, int dy, int dwidth, int dheight){
    setBoundsRelOnly(dx, dy, dwidth, dheight);
    fireCustomizerResetBoundsRel(new CustomizerEvent(this, dx, dy, dwidth, dheight));
  }
  
  protected void setBoundsRelOnly(int dx, int dy, int dwidth, int dheight){
    Rectangle bounds = calculateBounds(dx, dy, dwidth, dheight);
    setBounds(bounds);
    doLayout(); // seems to be necessary?!?
    repaint();
  }
  
  private Rectangle calculateBounds(int dx, int dy, int dwidth, int dheight){
    Rectangle bounds = getBounds();
    return new Rectangle(bounds.x + dx, bounds.y + dy, bounds.width + dwidth,
    bounds.height + dheight);
  }
  //  private Point calculateLocation(int dx, int dy){
  //    Point location = getLocation();
  //    return new Point(location.x + dx, location.y + dy);
  //  }
  //  private Dimension calculateSize(int dwidth, int dheight) {
  //    Dimension dim = getSize();
  //    int width = dim.width + dwidth;
  //    int height = dim.height + dheight;
  //    /*if (width < 1){
  //      width = 1;
  //    }
  //    if (height < 1){
  //      height = 1;
  //    }*/
  //    return new Dimension(width, height);
  //  }
  
  public void addCustomizerListener(CustomizerListener listener){
    listeners.add(listener);
  }
  
  public void removeCustomizerListener(CustomizerListener listener){
    listeners.remove(listener);
  }
  
  //  public void fireCustomizerMoveRel(CustomizerEvent e){
  //    for (Iterator i=listeners.iterator(); i.hasNext();){
  //      ((CustomizerListener) i.next()).customizerMoveRel(e);
  //    }
  //  }
  //
  //  public void fireCustomizerResizeRel(CustomizerEvent e){
  //    for (Iterator i=listeners.iterator(); i.hasNext();){
  //      ((CustomizerListener) i.next()).customizerResizeRel(e);
  //    }
  //  }
  public void fireCustomizerResetBoundsRel(CustomizerEvent e){
    for (Iterator i=listeners.iterator(); i.hasNext();){
      ((CustomizerListener) i.next()).customizerResetBoundsRel(e);
    }
  }
  
  public void fireCustomizerReshapeRel(CustomizerEvent e){
    for (Iterator i=listeners.iterator(); i.hasNext();){
      ((CustomizerListener) i.next()).customizerReshapeRel(e);
    }
  }
  
  //  /*package-private*/ void registerListeners(JCustomizerPane parent){
  //    // default: do nothing
  //  }
  //
  //  /*package-private*/ void unregisterListeners(JCustomizerPane parent){
  //    // default: do nothing
  //  }
  
  //  public void fireCustomizerDelete(CustomizerEvent e){
  //    for (Iterator i=listeners.iterator(); i.hasNext();){
  //      ((CustomizerListener) i.next()).customizerDelete(e);
  //    }
  //  }
  //
  /** Getter for property glassPane.
   * @return Value of property glassPane.
   *
   */
  public JPanel getGlassPane() {
    return glassPane;
  }
  
  /** Registers the text to display in a tool tip.
   * The text displays when the cursor lingers over the component.
   * <p>
   * See <a href="http://java.sun.com/docs/books/tutorial/uiswing/components/tooltip.html">How to Use Tool Tips</a>
   * in <em>The Java Tutorial</em>
   * for further documentation.
   *
   * @param text  the string to display; if the text is <code>null</code>,
   *              the tool tip is turned off for this component
   * @see #TOOL_TIP_TEXT_KEY
   * @beaninfo
   *   preferred: true
   * description: The text to display in a tool tip.
   *
   */
  public void setToolTipText(String text) {
    super.setToolTipText(text);
    if (getGlassPane() != null){
      getGlassPane().setToolTipText(text);
    }
    if (fComponent != null){
      fComponent.setToolTipText(text);
    }
  }
  
  public void applyBorder(Border border) {
    getGlassPane().setBorder(border);
  }
  
  /**
   * Fires a PropertyChangeEvent (absolute coordinates) but no CustomizerEvent (relative coordinates)!
   */
  public void setX(int x) {
    int oldValue = getX();
    reshapeRelOnly(x-getX(), 0, 0, 0);
    firePropertyChange("x", oldValue, getX());
  }
  
  /**
   * Fires a PropertyChangeEvent (absolute coordinates) but no CustomizerEvent (relative coordinates)!
   */
  public void setY(int y) {
    int oldValue = getY();
    reshapeRelOnly(0, y-getY(), 0, 0);
    firePropertyChange("y", oldValue, getY());
  }
  
  /**
   * Fires a PropertyChangeEvent (absolute coordinates) but no CustomizerEvent (relative coordinates)!
   */
  public void setWidth(int width) {
    int oldValue = getWidth();
    reshapeRelOnly(0, 0, width-getWidth(), 0);
    firePropertyChange("width", oldValue, getWidth());
  }
  
  /**
   * Fires a PropertyChangeEvent (absolute coordinates) but no CustomizerEvent (relative coordinates)!
   */
  public void setHeight(int height) {
    int oldValue = getX();
    reshapeRelOnly(0, 0, 0, height-getHeight());
    firePropertyChange("height", oldValue, getHeight());
  }
  
  /** Returns the tooltip string that has been set with
   * <code>setToolTipText</code>.
   *
   * @return the text of the tool tip
   * @see #TOOL_TIP_TEXT_KEY
   *
   */
  public String getToolTipText() {
    return (fComponent != null) ? fComponent.getToolTipText() : super.getToolTipText();
  }
  
  public void setDefaultBackground(Color bg){
    super.setDefaultBackground(bg);
    if (fComponent != null){
      fComponent.setBackground(bg);
    }
  }
  
  public void setDefaultForeground(Color fg){
    super.setDefaultForeground(fg); // to update listeners etc.
    if (fComponent != null){
      fComponent.setForeground(fg);
    }
  }
  
  public void setDefaultFont(Font font){
    super.setDefaultFont(font);
    if (fComponent != null){
      fComponent.setFont(font);
    }
  }
  
  public void setDefaultOpaque(boolean isOpaque){
    super.setDefaultOpaque(isOpaque);
    if (fComponent != null){
      fComponent.setOpaque(isOpaque);
    }
  }
  
  public JCustomizerPane getParentCustomizerPane() {
    // There seems to be no way to ensure this!?
    return (JCustomizerPane) getParent();
  }
  
  
  public void addActionListener(ActionListener l) {
    listenerList.add(ActionListener.class, l);
  }
  
  public void removeActionListener(ActionListener l) {
    listenerList.remove(ActionListener.class, l);
  }
  
  public void fireActionEvent(ActionEvent ev){
    ActionListener[] listeners = (ActionListener[]) getListeners(ActionListener.class);
    for (int i=0; i<listeners.length; i++){
      listeners[i].actionPerformed(ev);
    }
  }
  
  public Color getNormalBorderColor(){
    return getStateManager().getNormalBorderColor();
  }
  
  //  public void setNormalBorederColor(Color normalBorderColor){
  //    getStateManager().setNormalBorderColor(normalBorderColor);
  //  }
  //
  public Color getSelectedBorderColor(){
    return getStateManager().getSelectedBorderColor();
  }
  
  //  public void setSelectedBorderColor(Color selectedBorderColor){
  //    getStateManager().setSelectedBorderColor(selectedBorderColor);
  //  }
  
  
  
  /** Getter for property usingDefaultNormalBorderColor.
   * @return Value of property usingDefaultNormalBorderColor.
   *
   */
  public boolean isUsingDefaultNormalBorderColor() {
    return getStateManager().isUsingDefaultNormalBorderColor();
  }
  
  /** Setter for property usingDefaultNormalBorderColor.
   * @param usingDefaultNormalBorderColor New value of property usingDefaultNormalBorderColor.
   *
   */
  public void setUsingDefaultNormalBorderColor(boolean usingDefaultNormalBorderColor) {
    getStateManager().setUsingDefaultNormalBorderColor(usingDefaultNormalBorderColor);
  }
  
  /** Getter for property usingDefaultSelectedBorderColor.
   * @return Value of property usingDefaultSelectedBorderColor.
   *
   */
  public boolean isUsingDefaultSelectedBorderColor() {
    return getStateManager().isUsingDefaultSelectedBorderColor();
  }
  
  /** Setter for property usingDefaultSelectedBorderColor.
   * @param usingDefaultSelectedBorderColor New value of property usingDefaultSelectedBorderColor.
   *
   */
  public void setUsingDefaultSelectedBorderColor(boolean usingDefaultSelectedBorderColor) {
    getStateManager().setUsingDefaultSelectedBorderColor(usingDefaultSelectedBorderColor);
  }
  
  
}

