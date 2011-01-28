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
import org.softsmithy.lib.test.*;





/**
 * This component wraps another component to make it visually customizable.
 * Eg. the component can be moved or resized with the mouse.
 * 
 * Note: JCustomizer expects a JCustomizerPane as its parent.
 * Note: For multi-selection: press the Ctrl-button while selecting.
 * Note: If you're interested in the values of the properties "x", "y", "width"
 *       and "height", then you might want to register a CustomizerListener in
 *       addition to PropertyChangeListeners to listen for all changes, absolute
 *       and relative ones.
 * @author puce
 */
public class JCustomizer extends AbstractCustomizer {//implements CustomizerModelListener{
  
    /**
     * The wrapped component.
     */
  private JComponent component;
    /**
     * The container of the wrapped component.
     */
  private final JPanel componentContainer = new JPanel();
    /**
     * The glass pane over the wrapped component.
     */
  private final JPanel glassPane = new JPanel(); // intercepts events
  
    /**
     * The state manager.
     */
  private StateManager stateManager;
    /**
     * The customizer listeners.
     */
  private final Set<CustomizerListener> listeners = new HashSet<CustomizerListener>();
    /**
     * The layout constraints.
     */
  private static final TableLayoutConstraints CONSTRAINTS = new TableLayoutConstraints();
  
  /** Holds value of property model. */
  //private CustomizerModel model;
  
  /**
     * Creates a new instance of this class.
     */
  public JCustomizer() {
    this(new JPanel());
  }
  
    /**
     * Creates a new instance of this class.
     * @param component the wrapped component
     */
  public JCustomizer(JComponent component){
    //setModel(new DefaultCustomizerModel());
    setLayout(new TableLayout(new double[][]{{TableLayout.FILL}, {TableLayout.FILL}}));//new BorderLayout());
    glassPane.setLayout(new BorderLayout());
    glassPane.setOpaque(false);
    add(glassPane, CONSTRAINTS);
    componentContainer.setLayout(new BorderLayout());
    componentContainer.setOpaque(false);
    add(componentContainer, CONSTRAINTS);
    setComponent(component);
    setStateManager(new StateManager(this));
    setRequestFocusEnabled(true);
    setCustomizableProperties(new LinkedHashSet<String>(Arrays.asList(new String[] {"x", "y", "width", "height"}))); // to allow SelectionManager to listen for these properties
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
  
  /**
     * Gets the wrapped component.
     * @return the wrapped component
     */
  public JComponent getComponent() {
    return component;
  }
  
    /**
     * Gets the container of the wrapped component.
     * @return the container of the wrapped component
     */
  private JPanel getComponentContainer(){
    return componentContainer;
  }
  
  /**
     * Sets the wrapped component.
     * @param component the wrapped component
     */
  public void setComponent(JComponent component) {
    if (this.component != null){
      //this.remove(this.component);
      getComponentContainer().remove(this.component);
    }
    this.component = component;
    //add(this.component, CONSTRAINTS);
    getComponentContainer().add(component, BorderLayout.CENTER);
    
  }
  
    /**
     * Gets the state manager.
     * @return the state manager
     */
  public StateManager getStateManager(){
    return stateManager;
  }
  
    /**
     * Sets the state manager.
     * @param manager the state manager
     */
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
     * Relatively reshapes this customizer. </br>
     * This will ask the parent to relayout its components. </br>
     * Fires a CustomizerEvent (relative coordinates) but no PropertyChangeEvents (absolute coordinates)!
     * @param dx delta x
     * @param dy delta y
     * @param dwidth delta width
     * @param dheight delta height
     */
  public void reshapeRel(int dx, int dy, int dwidth, int dheight) {
    reshapeRelOnly(dx, dy, dwidth, dheight);
    fireCustomizerReshapeRel(new CustomizerEvent(this, dx, dy, dwidth, dheight));
  }
  
    /**
     * Relatively reshapes this customizer without firing an event. </br>
     * This will ask the parent to relayout its components.
     * @param dx delta x
     * @param dy delta y
     * @param dwidth delta width
     * @param dheight delta height
     */
  protected void reshapeRelOnly(int dx, int dy, int dwidth, int dheight){
    getParentCustomizerPane().setAbsoluteCustomizerBounds(this, calculateBounds(dx, dy, dwidth, dheight));
    //    reshapeRelOnly(dx, dy, dwidth, dheight);
    //    JCustomizerPane pane = (JCustomizerPane) getParent();
    //    CustomizerLayout cl = pane.getCustomizerLayout();
    //    cl.layoutCustomizer(pane, this);
    revalidate(); // seems to be necessary?!?
    doLayout();
    getComponentContainer().doLayout();
    repaint();
  }
  
  /**
     * Relatively changes the bounds of this customizer. </br>
     * This will NOT ask the parent to relayout its components. </br>
     * Fires a CustomizerEvent (relative coordinates) but no PropertyChangeEvents (absolute coordinates)!
     * @param dx delta x
     * @param dy delta y
     * @param dwidth delta width
     * @param dheight delta height
     */
  public void setBoundsRel(int dx, int dy, int dwidth, int dheight){
    setBoundsRelOnly(dx, dy, dwidth, dheight);
    fireCustomizerResetBoundsRel(new CustomizerEvent(this, dx, dy, dwidth, dheight));
  }
  
    /**
     * Relatively changes the bounds of this customizer without firing an event. </br>
     * This will NOT ask the parent to relayout its components. </br>
     * @param dx delta x
     * @param dy delta y
     * @param dwidth delta width
     * @param dheight delta height
     */
  protected void setBoundsRelOnly(int dx, int dy, int dwidth, int dheight){
    Rectangle bounds = calculateBounds(dx, dy, dwidth, dheight);
    setBounds(bounds);
    doLayout(); // seems to be necessary?!?
    repaint();
  }
  
    /**
     * Calculates the bounds.
     * @param dx delta x
     * @param dy delta y
     * @param dwidth delta width
     * @param dheight delta height
     * @return the bounds
     */
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
  
    /**
     * Adds a CustomizerListener.
     * @param listener a CustomizerListener
     */
  public void addCustomizerListener(CustomizerListener listener){
    listeners.add(listener);
  }
  
    /**
     * Removes a CustomizerListener.
     * @param listener a CustomizerListener
     */
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
    /**
     * Fires a CustomizerEvent to notify the CustomizerListeners that the bounds
     * of this customizer have been relatively changed.
     * @param e the CustomizerEvent
     */
  public void fireCustomizerResetBoundsRel(CustomizerEvent e){
    for (Iterator i=listeners.iterator(); i.hasNext();){
      ((CustomizerListener) i.next()).customizerResetBoundsRel(e);
    }
  }
  
      /**
     * Fires a CustomizerEvent to notify the CustomizerListeners this customizer 
     * have been relatively reshaped.
     * @param e the CustomizerEvent
     */
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
  /**
     * Gets the glass pane over the wrapped component.
     * @return the glass pane over the wrapped component
     */
  public JPanel getGlassPane() {
    return glassPane;
  }
  
  /** 
   * Registers the text to display in a tool tip.
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
  @Override
  public void setToolTipText(String text) {
    super.setToolTipText(text);
    if (getGlassPane() != null){
      getGlassPane().setToolTipText(text);
    }
    if (getComponent() != null){
      getComponent().setToolTipText(text);
    }
  }
  
    /**
     * Applys a border to this customizer. </br>
     * Usually you will use this method rather than setBorder.
     * @param border a border
     */
  public void applyBorder(Border border) {
    getGlassPane().setBorder(border); // painted on glass pane so the handles of HandleBorder are always visible
    Insets insets = getGlassPane().getInsets();
    if (! insets.equals(getComponentContainer().getInsets())){
      getComponentContainer().setBorder(BorderFactory.createEmptyBorder(insets.top, 
      insets.left, insets.bottom, insets.right));
    }
    
    
    //    System.out.println("bounds before: "+getComponent().getBounds());
    //    Insets insets = getGlassPane().getInsets();
    //    getComponentContainer().setBorder(BorderFactory.createEmptyBorder(insets.top, insets.left, insets.bottom, insets.right));
    //    getComponentContainer().doLayout();
    //    System.out.println("bounds after: "+getComponent().getBounds());
    //    repaint();
  }
  
  /**
     * Sets the x coordinate. </br>
     * Fires a PropertyChangeEvent (absolute coordinates) but no CustomizerEvent (relative coordinates)!
     * @param x the x coordinate
     */
  public void setX(int x) {
    int oldValue = getX();
    reshapeRelOnly(x-getX(), 0, 0, 0);
    firePropertyChange("x", oldValue, getX());
  }
  
  /**
     * Sets the y coordinate. </br>
     * Fires a PropertyChangeEvent (absolute coordinates) but no CustomizerEvent (relative coordinates)!
     * @param y the y coordinate
     */
  public void setY(int y) {
    int oldValue = getY();
    reshapeRelOnly(0, y-getY(), 0, 0);
    firePropertyChange("y", oldValue, getY());
  }
  
  /**
     * Sets the width. </br>
     * Fires a PropertyChangeEvent (absolute coordinates) but no CustomizerEvent (relative coordinates)!
     * @param width the width
     */
  public void setWidth(int width) {
    int oldValue = getWidth();
    reshapeRelOnly(0, 0, width-getWidth(), 0);
    firePropertyChange("width", oldValue, getWidth());
  }
  
  /**
     * Sets the height. </br>
     * Fires a PropertyChangeEvent (absolute coordinates) but no CustomizerEvent (relative coordinates)!
     * @param height the height
     */
  public void setHeight(int height) {
    int oldValue = getX();
    reshapeRelOnly(0, 0, 0, height-getHeight());
    firePropertyChange("height", oldValue, getHeight());
  }
  
  /** 
   * Returns the tooltip string that has been set with
   * <code>setToolTipText</code>.
   *
   * @return the text of the tool tip
   * @see #TOOL_TIP_TEXT_KEY
   *
   */
  @Override
  public String getToolTipText() {
    return (getComponent() != null) ? getComponent().getToolTipText() : super.getToolTipText();
  }
  
  @Override
  public void setDefaultBackground(Color bg){
    super.setDefaultBackground(bg);
    if (getComponent() != null){
      getComponent().setBackground(bg);
    }
  }
  
  @Override
  public void setDefaultForeground(Color fg){
    super.setDefaultForeground(fg); // to update listeners etc.
    if (getComponent() != null){
      getComponent().setForeground(fg);
    }
  }
  
  @Override
  public void setDefaultFont(Font font){
    super.setDefaultFont(font);
    if (getComponent() != null){
      getComponent().setFont(font);
    }
  }
  
  @Override
  public void setDefaultOpaque(boolean isOpaque){
    super.setDefaultOpaque(isOpaque);
    if (getComponent() != null){
      getComponent().setOpaque(isOpaque);
    }
  }
  
    /**
     * Gets the parent JCustomizerPane. </br>
     * Note: If the parent is not an instance of JCustomizerPane a ClassCastException
     * will be thrown!
     * @return the parent JCustomizerPane
     */
  public JCustomizerPane getParentCustomizerPane() {
    // There seems to be no way to ensure this!?
    return (JCustomizerPane) getParent();
  }
  
  
    /**
     * Adds an ActionListener.
     * @param l an ActionListener
     */
  public void addActionListener(ActionListener l) {
    listenerList.add(ActionListener.class, l);
  }
  
    /**
     * Removes an ActionListener.
     * @param l an ActionListener
     */
  public void removeActionListener(ActionListener l) {
    listenerList.remove(ActionListener.class, l);
  }
  
    /**
     * Fires an ActionEvent.
     * @param ev an ActionEvent
     */
  public void fireActionEvent(ActionEvent ev){
    ActionListener[] actionListeners = getListeners(ActionListener.class);
    for (int i=0; i<actionListeners.length; i++){
      actionListeners[i].actionPerformed(ev);
    }
  }
  
    /**
     * Gets the color of the border of the normal state.
     * @return the color of the border of the normal state
     */
  public Color getNormalBorderColor(){
    return getStateManager().getNormalBorderColor();
  }
  
  //  public void setNormalBorederColor(Color normalBorderColor){
  //    getStateManager().setNormalBorderColor(normalBorderColor);
  //  }
  //
    /**
     * Gets the color of the border of the selected state.
     * @return the color of the border of the selected state
     */
  public Color getSelectedBorderColor(){
    return getStateManager().getSelectedBorderColor();
  }
  
  //  public void setSelectedBorderColor(Color selectedBorderColor){
  //    getStateManager().setSelectedBorderColor(selectedBorderColor);
  //  }
  
  
  
  /**
     * Tells if this customizer is using the default color of the border of the normal 
     * state.
     * @return true if the default color is used, else false
     */
  public boolean isUsingDefaultNormalBorderColor() {
    return getStateManager().isUsingDefaultNormalBorderColor();
  }
  
  /**
     * Specifies if this customizer should use the default or a custom color of 
     * the border of the normal state.
     * @param usingDefaultNormalBorderColor true if the default color should be used, else false
     */
  public void setUsingDefaultNormalBorderColor(boolean usingDefaultNormalBorderColor) {
    getStateManager().setUsingDefaultNormalBorderColor(usingDefaultNormalBorderColor);
  }
  

    /**
     * Tells if this customizer is using the default color of the border of the selected 
     * state.
     * @return true if the default color is used, else false
     */
  public boolean isUsingDefaultSelectedBorderColor() {
    return getStateManager().isUsingDefaultSelectedBorderColor();
  }
  
  /**
     * Specifies if this customizer should use the default or a custom color of 
     * the border of the selected state.
     * @param usingDefaultSelectedBorderColor true if the default color should be used, else false
     */
  public void setUsingDefaultSelectedBorderColor(boolean usingDefaultSelectedBorderColor) {
    getStateManager().setUsingDefaultSelectedBorderColor(usingDefaultSelectedBorderColor);
  }
    
}
