/*
 * JCustomizer.java
 *
 * Created on 19. August 2002, 13:45
 */

package puce.swing;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import puce.swing.customizer.*;
import puce.swing.event.*;
import puce.test.*;





/**
 *
 * @author  puce
 */
public class JCustomizer extends JPanel{
  
  private JComponent fComponent;
  private JPanel glassPane = new JPanel();
  
  private StateManager stateManager;
  private final Set listeners = new HashSet();
  private static final TableLayoutConstraints CONSTRAINTS = new TableLayoutConstraints();
  
  /** Creates a new instance of JCustomizer */
  public JCustomizer() {
    setLayout(new TableLayout(new double[][]{{TableLayout.FILL}, {TableLayout.FILL}}));//new BorderLayout());
    glassPane.setLayout(new BorderLayout());
    glassPane.setOpaque(false);
    add(glassPane, CONSTRAINTS);
    setStateManager(new StateManager(this));
    setRequestFocusEnabled(true);
    //setComponent(new JLabel("testtest"));
    this.setOpaque(false);
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
  }
  
  public void setBoundsRel(int dx, int dy, int dwidth, int dheight){
    Rectangle bounds = calculateBounds(dx, dy, dwidth, dheight);
    setBounds(bounds);
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
    getGlassPane().setToolTipText(text);
  }  
  
}
