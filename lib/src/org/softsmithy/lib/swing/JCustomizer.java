/*
 * JCustomizer.java
 *
 * Created on 19. August 2002, 13:45
 */

package puce.swing;

import java.awt.event.*;
import puce.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import puce.swing.border.*;
import puce.swing.JCustomizer.*;
import java.awt.*;



/**
 *
 * @author  puce
 */
public class JCustomizer extends JPanel{
  
  private JComponent fComponent;
  
  private StateManager fManager;
  
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
  
  public static class StateManager implements MouseInputListener, FocusListener {
    
    private State fCurrent;
    private State fNormal;
    private BoundState fMove;
    private ResizeState fNResize;
    private ResizeState fNEResize;
    private ResizeState fEResize;
    private ResizeState fSEResize;
    private ResizeState fSResize;
    private ResizeState fSWResize;
    private ResizeState fWResize;
    private ResizeState fNWResize;
    private ResizeState[] fResizes;
    
    //private JCustomizer customizer;
    
    public StateManager(final JCustomizer customizer){
      fNormal =  new State(customizer){
        public void mousePressed(MouseEvent e) {
          setState(getBoundStateAt(e.getPoint()));
          if(!customizer.hasFocus()){
            customizer.requestFocus();
          }
        }
      };
      
      fMove = new BoundState(customizer){
        public void applyCursor(){
          customizer.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }
        public void processMouseDragged(MouseEvent e) {
          customizer.moveRel(e.getX(), e.getY());
        }
      };
      fNResize = new ResizeState(customizer){
        public void applyCursor(){
          customizer.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
        }
        public HandleBorder.Handle getHandle(){
          return HANDLE_BORDER.getNHandle();
        }
        public void processMouseDragged(MouseEvent e) {
          customizer.reshapeRel(0, e.getY(), 0, -e.getY());
        }
      };
      fNEResize = new ResizeState(customizer){
        public void applyCursor(){
          customizer.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
        }
        public HandleBorder.Handle getHandle(){
          return HANDLE_BORDER.getNEHandle();
        }
        public void processMouseDragged(MouseEvent e) {
          customizer.reshapeRel(0, e.getY(), e.getX(), - e.getY());
        }
      };
      
      fEResize = new ResizeState(customizer){
        public void applyCursor(){
          customizer.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        }
        public HandleBorder.Handle getHandle(){
          return HANDLE_BORDER.getEHandle();
        }
        public void processMouseDragged(MouseEvent e) {
          customizer.resizeRel(e.getX(), 0);
        }
      };
      fSEResize = new ResizeState(customizer){
        public void applyCursor(){
          customizer.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        }
        public HandleBorder.Handle getHandle(){
          return HANDLE_BORDER.getSEHandle();
        }
        public void processMouseDragged(MouseEvent e) {
          customizer.resizeRel(e.getX(), e.getY());
        }
      };
      fSResize = new ResizeState(customizer){
        public void applyCursor(){
          customizer.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
        }
        public HandleBorder.Handle getHandle(){
          return HANDLE_BORDER.getSHandle();
        }
        public void processMouseDragged(MouseEvent e) {
          customizer.resizeRel(0, e.getY());
        }
      };
      fSWResize = new ResizeState(customizer){
        public void applyCursor(){
          customizer.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
        }
        public HandleBorder.Handle getHandle(){
          return HANDLE_BORDER.getSWHandle();
        }
        public void processMouseDragged(MouseEvent e) {
          customizer.reshapeRel(e.getX(), 0, - e.getX(), e.getY());
        }
      };
      fWResize = new ResizeState(customizer){
        public void applyCursor(){
          customizer.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
        }
        public HandleBorder.Handle getHandle(){
          return HANDLE_BORDER.getWHandle();
        }
        public void processMouseDragged(MouseEvent e) {
          customizer.reshapeRel(e.getX(), 0, - e.getX(), 0);
        }
      };
      fNWResize = new ResizeState(customizer){
        public void applyCursor(){
          customizer.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
        }
        public HandleBorder.Handle getHandle(){
          return HANDLE_BORDER.getNWHandle();
        }
        public void processMouseDragged(MouseEvent e) {
          customizer.reshapeRel(e.getX(), e.getY(), - e.getX(), - e.getY());
        }
      };
      fResizes = new ResizeState[]{fNResize, fNEResize, fEResize, fSEResize,
      fSResize, fSWResize, fWResize, fNWResize};
      setState(fNormal);
      
    }
    
    private void setState(State state){
      fCurrent = state;
      fCurrent.applyBorder();
      fCurrent.applyCursor();
    }
    
    private BoundState getBoundStateAt(Point p){
      BoundState state = fMove;
      for (int i=0; i<fResizes.length; i++){
        if (fResizes[i].contains(p)){
          state = fResizes[i];
          break;
        }
      }
      return state;
    }
    /** Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     */
    public void mouseClicked(MouseEvent e) {
      fCurrent.mouseClicked(e);
    }
    
    /** Invoked when a mouse button is pressed on a component and then
     * dragged.  <code>MOUSE_DRAGGED</code> events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&Drop implementations,
     * <code>MOUSE_DRAGGED</code> events may not be delivered during a native
     * Drag&Drop operation.
     */
    public void mouseDragged(MouseEvent e) {
      fCurrent.mouseDragged(e);
    }
    
    /** Invoked when the mouse enters a component.
     */
    public void mouseEntered(MouseEvent e) {
      fCurrent.mouseEntered(e);
    }
    
    /** Invoked when the mouse exits a component.
     */
    public void mouseExited(MouseEvent e) {
      fCurrent.mouseExited(e);
    }
    
    /** Invoked when the mouse button has been moved on a component
     * (with no buttons down).
     */
    public void mouseMoved(MouseEvent e) {
      fCurrent.mouseMoved(e);
    }
    
    /** Invoked when a mouse button has been pressed on a component.
     */
    public void mousePressed(MouseEvent e) {
      fCurrent.mousePressed(e);
    }
    
    /** Invoked when a mouse button has been released on a component.
     */
    public void mouseReleased(MouseEvent e) {
      fCurrent.mouseReleased(e);
    }
    
    /** Invoked when a component gains the keyboard focus.
     */
    public void focusGained(FocusEvent e) {
    }
    
    /** Invoked when a component loses the keyboard focus.
     */
    public void focusLost(FocusEvent e) {
      setState(fNormal);
    }
    
    public static class State{
      
      protected static final HandleBorder HANDLE_BORDER = new HandleBorder();
      private JCustomizer fCustomizer;
      
      public State(JCustomizer customizer){
        fCustomizer = customizer;
      }
      
      public void applyBorder(){
        fCustomizer.setBorder(null);
      }
      
      public void applyCursor(){
        fCustomizer.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      }
      
      public JCustomizer getCustomizer(){
        return fCustomizer;
      }
      
      /** Invoked when the mouse button has been clicked (pressed
       * and released) on a component.
       */
      public void mouseClicked(MouseEvent e) {
      }
      
      /** Invoked when a mouse button is pressed on a component and then
       * dragged.  <code>MOUSE_DRAGGED</code> events will continue to be
       * delivered to the component where the drag originated until the
       * mouse button is released (regardless of whether the mouse position
       * is within the bounds of the component).
       * <p>
       * Due to platform-dependent Drag&Drop implementations,
       * <code>MOUSE_DRAGGED</code> events may not be delivered during a native
       * Drag&Drop operation.
       */
      public void mouseDragged(MouseEvent e) {
      }
      
      /** Invoked when the mouse enters a component.
       */
      public void mouseEntered(MouseEvent e) {
      }
      
      /** Invoked when the mouse exits a component.
       */
      public void mouseExited(MouseEvent e) {
      }
      
      /** Invoked when the mouse button has been moved on a component
       * (with no buttons down).
       */
      public void mouseMoved(MouseEvent e) {
      }
      
      /** Invoked when a mouse button has been pressed on a component.
       */
      public void mousePressed(MouseEvent e) {
      }
      
      /** Invoked when a mouse button has been released on a component.
       */
      public void mouseReleased(MouseEvent e) {
      }
      
    }
    
    public static abstract class BoundState extends State{
      
      /** Holds value of property startX. */
      private int startX;
      
      /** Holds value of property startY. */
      private int startY;
      
      public BoundState(JCustomizer customizer){
        super(customizer);
      }
      public void applyBorder(){
        getCustomizer().setBorder(HANDLE_BORDER);
      }
      public void mouseMoved(MouseEvent e){
        StateManager manager = getCustomizer().getStateManager();
        BoundState state = manager.getBoundStateAt(e.getPoint());
        if (state != this){
          manager.setState(state);
        }
      }
      
      /** Getter for property startX.
       * @return Value of property startX.
       */
      public int getStartX() {
        return this.startX;
      }
      
      /** Getter for property startY.
       * @return Value of property startY.
       */
      public int getStartY() {
        return this.startY;
      }
      
      /** Invoked when a mouse button has been pressed on a component.
       */
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        startX = e.getX();
        startY = e.getY();
      }
      
      /** Invoked when a mouse button is pressed on a component and then
       * dragged.  <code>MOUSE_DRAGGED</code> events will continue to be
       * delivered to the component where the drag originated until the
       * mouse button is released (regardless of whether the mouse position
       * is within the bounds of the component).
       * <p>
       * Due to platform-dependent Drag&Drop implementations,
       * <code>MOUSE_DRAGGED</code> events may not be delivered during a native
       * Drag&Drop operation.
       */
      public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        e.translatePoint(- startX, - startY);
        processMouseDragged(e);
        e.translatePoint(startX, startY);
        startX = e.getX();
        startY = e.getY();
      }
      
      public abstract void processMouseDragged(MouseEvent e);
      
    }
    
    public static abstract class ResizeState extends BoundState{
      public ResizeState(JCustomizer customizer){
        super(customizer);
        getHandle().setRect(customizer.getWidth(), customizer.getHeight());
      }
      public abstract HandleBorder.Handle getHandle();
      public boolean contains(Point p){
        return getHandle().contains(p);
      }
    }
  }
  
}
