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
 *
 * @author  puce
 */
public class JCustomizer extends JPanel {//implements CustomizerModelListener{
  
  private JComponent fComponent = new JPanel();
  private JPanel glassPane = new JPanel();
  
  private StateManager stateManager;
  private final Set listeners = new HashSet();
  private static final TableLayoutConstraints CONSTRAINTS = new TableLayoutConstraints();
  
  /** Holds value of property customizableProperties. */
  private Set customizableProperties = Collections.EMPTY_SET;
  
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
    //setComponent(new JLabel("testtest"));
    //this.setOpaque(false);
    Action deleteAction = new AbstractAction("delete") {
      public void actionPerformed(ActionEvent e) {
        ((JCustomizer) e.getSource()).fireCustomizerDelete(new CustomizerEvent(e.getSource(), 0, 0, 0, 0));
        //        System.out.println(e.getSource() + " deleted!");
      }
    };
    getActionMap().put(deleteAction.getValue(Action.NAME),
    deleteAction);
    
    getInputMap().put(KeyStroke.getKeyStroke("DELETE"), deleteAction.getValue(Action.NAME));
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
    }
    stateManager = manager;
    glassPane.addMouseListener(stateManager);
    glassPane.addMouseMotionListener(stateManager);
    addFocusListener(stateManager);
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
  
  public void reshapeRel(int dx, int dy, int dwidth, int dheight) {
    JCustomizerPane pane = (JCustomizerPane) getParent();
    CustomizerLayout cl = (CustomizerLayout) pane.getLayout();
    Rectangle bounds = calculateBounds(dx, dy, dwidth, dheight);
    CustomizerConstraints constr = cl.getConstraints(this);
    constr.setAbsoluteBounds(bounds, cl);
    cl.setConstraints(this, constr);
    cl.layoutComponent(pane, this);
    repaint();
  }
  
  public void setBoundsRel(int dx, int dy, int dwidth, int dheight){
    Rectangle bounds = calculateBounds(dx, dy, dwidth, dheight);
    setBounds(bounds);
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
  public void fireCustomizerReshapeRel(CustomizerEvent e){
    for (Iterator i=listeners.iterator(); i.hasNext();){
      ((CustomizerListener) i.next()).customizerReshapeRel(e);
    }
  }
  
  public void fireCustomizerFinishDragging(CustomizerEvent e){
    for (Iterator i=listeners.iterator(); i.hasNext();){
      ((CustomizerListener) i.next()).customizerFinishReshapeRel(e);
    }
  }
  
  public void fireCustomizerDelete(CustomizerEvent e){
    for (Iterator i=listeners.iterator(); i.hasNext();){
      ((CustomizerListener) i.next()).customizerDelete(e);
    }
  }
  
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
  
  public void setX(int x) {
    reshapeRel(x-getX(), 0, 0, 0);
  }
  
  public void setY(int y) {
    reshapeRel(0, y-getY(), 0, 0);
  }
  
  public void setWidth(int width) {
    reshapeRel(0, 0, width-getWidth(), 0);
  }
  
  public void setHeight(int height) {
    reshapeRel(0, 0, 0, height-getHeight());
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
    super.setFont(font);
    if (fComponent != null){
      fComponent.setFont(font);
    }
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
    return (fComponent != null) ? fComponent.getForeground() : super.getForeground();
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
    super.setBackground(bg);
    if (fComponent != null){
      fComponent.setBackground(bg);
    }
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
    super.setOpaque(isOpaque);
    if (fComponent != null){
      fComponent.setOpaque(isOpaque);
    }
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
    return (fComponent != null) ? fComponent.isOpaque() : super.isOpaque();
  }
  
  /** Gets the font of this component.
   * @return this component's font; if a font has not been set
   * for this component, the font of its parent is returned
   * @see #setFont
   * @since JDK1.0
   *
   */
  public Font getFont() {
    return (fComponent != null) ? fComponent.getFont() : super.getFont();
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
    return (fComponent != null) ? fComponent.getBackground() : super.getBackground();
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
    super.setForeground(fg); // to update listeners etc.
    if (fComponent != null){
      fComponent.setForeground(fg);
    }
  }
  
  // better place for this method?
  public static Set getCommonCustomizableProperties(Collection customizers) {
    Set properties = Collections.EMPTY_SET;
    Iterator i = customizers.iterator();
    if (i.hasNext()){
      JCustomizer customizer = (JCustomizer) i.next();
      properties = new LinkedHashSet(customizer.getCustomizableProperties());
      for (;i.hasNext();){
        JCustomizer custom = (JCustomizer) i.next();
        properties.retainAll(custom.getCustomizableProperties());
      }
    }
    return properties;
  }

}

    
