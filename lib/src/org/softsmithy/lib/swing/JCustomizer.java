/*
 * JCustomizer.java
 *
 * Created on 19. August 2002, 13:45
 */

package puce.swing;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import puce.swing.customizer.*;
import puce.swing.event.*;




/**
 *
 * @author  puce
 */
public class JCustomizer extends JPanel{
  
  private JComponent fComponent;
  
  private final StateManager fManager;
  private final Set listeners = new HashSet();
  
  /** Creates a new instance of JCustomizer */
  public JCustomizer() {
    setLayout(new BorderLayout());
    fManager = new StateManager(this);
    addMouseListener(fManager);
    addMouseMotionListener(fManager);
    addFocusListener(fManager);
    setRequestFocusEnabled(true);
    setComponent(new JLabel("testtest"));
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
    removeAll();
    fComponent = component;
    //fComponent.setEnabled(false);
    add("Center", fComponent);
    //??
    /*fComponent.addMouseListener(fManager);
    fComponent.addMouseMotionListener(fManager);
    fComponent.addFocusListener(fManager);*/
  }
  
  public StateManager getStateManager(){
    return fManager;
  }
  
  public void moveRel(int dx, int dy) {
    JCustomizerPane pane = (JCustomizerPane) getParent();
    TableLayout tl = (TableLayout) pane.getLayout();
    Point loc = calculateLocation(dx, dy);
    TableConstraints tc = tl.getConstraints(this);
    //Point location = tl.location(p);
    
    tl.setConstraints(this,
    new DefaultTableConstraints(tl.columnIndex(loc.x), tl.rowIndex(loc.y), tc.getWidth(), tc.getHeight()));
    pane.doLayout(); //??
  }
  
  public void resizeRel(int dwidth, int dheight){
    JCustomizerPane pane = (JCustomizerPane) getParent();
    InfiniteTableLayout tl = (InfiniteTableLayout) pane.getLayout();
    TableConstraints tc = tl.getConstraints(this);
    Dimension dim = calculateSize(dwidth, dheight);
    int colSpan = tl.colSpan(tc.getX(), dim.width);
    int rowSpan =  tl.rowSpan(tc.getY(), dim.height);
    tl.setConstraints(this,
    new DefaultTableConstraints(tc.getX(), tc.getY(), colSpan, rowSpan));
    pane.doLayout();
  }
  
  public void reshapeRel(int dx, int dy, int dwidth, int dheight) {
    JCustomizerPane pane = (JCustomizerPane) getParent();
    InfiniteTableLayout tl = (InfiniteTableLayout) pane.getLayout();
    Point loc = calculateLocation(dx, dy);
    Dimension dim = calculateSize(dwidth, dheight);
    int x = tl.columnIndex(loc.x);
    int y = tl.rowIndex(loc.y);
    int colSpan =  tl.colSpan(x, dim.width);
    int rowSpan =  tl.rowSpan(y, dim.height);
    tl.setConstraints(this,  new DefaultTableConstraints(x, y, colSpan, rowSpan));
    pane.doLayout();
  }
  
  private Point calculateLocation(int dx, int dy){
    Point location = getLocation();
    return new Point(location.x + dx, location.y + dy);
  }
  private Dimension calculateSize(int dwidth, int dheight) {
    Dimension dim = getSize();
    int width = dim.width + dwidth;
    int height = dim.height + dheight;
    /*if (width < 1){
      width = 1;
    }
    if (height < 1){
      height = 1;
    }*/
    return new Dimension(width, height);
  }
  
  public void addCustomizerListener(CustomizerListener listener){
    listeners.add(listener);
  }
  
  public void removeCustomizerListener(CustomizerListener listener){
    listeners.remove(listener);
  }
  
  public void fireCustomizerMoveRel(CustomizerEvent e){
    for (Iterator i=listeners.iterator(); i.hasNext();){
      ((CustomizerListener) i.next()).customizerMoveRel(e);
    }    
  }
  
  public void fireCustomizerResizeRel(CustomizerEvent e){
    for (Iterator i=listeners.iterator(); i.hasNext();){
      ((CustomizerListener) i.next()).customizerResizeRel(e);
    }
  }
  public void fireCustomizerReshapeRel(CustomizerEvent e){
    for (Iterator i=listeners.iterator(); i.hasNext();){
      ((CustomizerListener) i.next()).customizerReshapeRel(e);
    }
  }
  
  
}
