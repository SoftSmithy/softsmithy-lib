package puce.swing.customizer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import puce.swing.*;
import puce.swing.border.*;
import puce.swing.border.HandleBorder.*;
import puce.swing.event.*;

/*
 * Is this the right place? Should it be nested or in puce.swing?
 */
public class StateManager implements FocusListener, MouseInputListener {
  
  private JCustomizer customizer;
  private State current;
  private State normalState;
  private State selectedState;
  private BoundState moveState;
  private ResizeState nResizeState;
  private ResizeState nEResizeState;
  private ResizeState eResizeState;
  private ResizeState sEResizeState;
  private ResizeState sResizeState;
  private ResizeState sWResizeState;
  private ResizeState wResizeState;
  private ResizeState nWResizeState;
  private ResizeState[] resizeStates;
  
  
  //private JCustomizer customizer;
  
  public StateManager(final JCustomizer customizer){
    this.customizer = customizer;
    
    normalState =  new DefaultState(customizer){
      public void mousePressed(MouseEvent e) {
        JCustomizerPane pane = (JCustomizerPane) customizer.getParent();
        if (e.isControlDown()){
          pane.getSelectionManager().select(customizer, e.getPoint());
        } else {
          pane.getSelectionManager().singleSelect(customizer, e.getPoint());
        }
      }
    };
    
    selectedState = new DefaultState(customizer){
      private final Border LINE_BORDER = BorderFactory.createLineBorder(Color.BLUE);
      public void mousePressed(MouseEvent e) {
        JCustomizerPane pane = (JCustomizerPane) customizer.getParent();
        if (e.isControlDown()){
          pane.getSelectionManager().deselect(customizer);
        } else {
          pane.getSelectionManager().singleSelect(customizer, e.getPoint());
        }
      }
      public void applyBorder(){
        customizer.setBorder(LINE_BORDER);
      }
    };
    
    moveState = new BoundState(customizer){
      public void applyCursor(){
        customizer.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
      }
      public CustomizerEvent createCustomizerEvent(MouseEvent e) {
        return createCustomizerEvent(e, 1, 1, 0, 0);
      }
    };
    nResizeState = new ResizeState(customizer){
      public void applyCursor(){
        customizer.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
      }
      public Handle getHandle(){
        return HANDLE_BORDER.getNHandle();
      }
      public CustomizerEvent createCustomizerEvent(MouseEvent e) {
        return createCustomizerEvent(e, 0, 1, 0, -1);
      }
    };
    nEResizeState = new ResizeState(customizer){
      public void applyCursor(){
        customizer.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
      }
      public Handle getHandle(){
        return HANDLE_BORDER.getNEHandle();
      }
      public CustomizerEvent createCustomizerEvent(MouseEvent e) {
        return createCustomizerEvent(e, 0, 1, 1, - 1);
      }
    };
    
    eResizeState = new ResizeState(customizer){
      public void applyCursor(){
        customizer.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
      }
      public Handle getHandle(){
        return HANDLE_BORDER.getEHandle();
      }
      public CustomizerEvent createCustomizerEvent(MouseEvent e) {
        return createCustomizerEvent(e, 0, 0, 1, 0);
      }
    };
    sEResizeState = new ResizeState(customizer){
      public void applyCursor(){
        customizer.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
      }
      public Handle getHandle(){
        return HANDLE_BORDER.getSEHandle();
      }
      public CustomizerEvent createCustomizerEvent(MouseEvent e) {
        return createCustomizerEvent(e, 0, 0, 1, 1);
      }
    };
    sResizeState = new ResizeState(customizer){
      public void applyCursor(){
        customizer.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
      }
      public Handle getHandle(){
        return HANDLE_BORDER.getSHandle();
      }
      public CustomizerEvent createCustomizerEvent(MouseEvent e) {
        return createCustomizerEvent(e, 0, 0, 0, 1);
      }
    };
    sWResizeState = new ResizeState(customizer){
      public void applyCursor(){
        customizer.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
      }
      public Handle getHandle(){
        return HANDLE_BORDER.getSWHandle();
      }
      public CustomizerEvent createCustomizerEvent(MouseEvent e) {
        return createCustomizerEvent(e, 1, 0, - 1, 1);
      }
    };
    wResizeState = new ResizeState(customizer){
      public void applyCursor(){
        customizer.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
      }
      public Handle getHandle(){
        return HANDLE_BORDER.getWHandle();
      }
      public CustomizerEvent createCustomizerEvent(MouseEvent e) {
        return createCustomizerEvent(e, 1, 0, - 1, 0);
      }
    };
    nWResizeState = new ResizeState(customizer){
      public void applyCursor(){
        customizer.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
      }
      public Handle getHandle(){
        return HANDLE_BORDER.getNWHandle();
      }
      public CustomizerEvent createCustomizerEvent(MouseEvent e) {
        return createCustomizerEvent(e, 1, 1, - 1, - 1);
      }
    };
    resizeStates = new ResizeState[]{nResizeState, nEResizeState, eResizeState, sEResizeState,
    sResizeState, sWResizeState, wResizeState, nWResizeState};
    setState(normalState);
  }
  
  protected void setState(State state){
    if (current != null){
      current.unconfigureCustomizer();
    }
    current = state;
    current.configureCustomizer();
  }
  
  public void setStateBound(Point point){
    setState(getBoundStateAt(point));
    if(!customizer.hasFocus()){
      customizer.requestFocus();
    }
    customizer.invalidate();
    customizer.doLayout();
  }
  
  private BoundState getBoundStateAt(Point p){
    BoundState state = moveState;
    for (int i=0; i<resizeStates.length; i++){
      if (resizeStates[i].contains(p)){
        state = resizeStates[i];
        break;
      }
    }
    return state;
  }
  /** Invoked when the mouse button has been clicked (pressed
   * and released) on a component.
   */
  public void mouseClicked(MouseEvent e) {
    current.mouseClicked(e);
    e.consume();
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
    current.mouseDragged(e);
    e.consume();
  }
  
  /** Invoked when the mouse enters a component.
   */
  public void mouseEntered(MouseEvent e) {
    current.mouseEntered(e);
    e.consume();
  }
  
  /** Invoked when the mouse exits a component.
   */
  public void mouseExited(MouseEvent e) {
    current.mouseExited(e);
    e.consume();
  }
  
  /** Invoked when the mouse button has been moved on a component
   * (with no buttons down).
   */
  public void mouseMoved(MouseEvent e) {
    current.mouseMoved(e);
    e.consume();
  }
  
  /** Invoked when a mouse button has been pressed on a component.
   */
  public void mousePressed(MouseEvent e) {
    current.mousePressed(e);
    e.consume();
  }
  
  /** Invoked when a mouse button has been released on a component.
   */
  public void mouseReleased(MouseEvent e) {
    current.mouseReleased(e);
    e.consume();
  }
  
  /** Invoked when a component gains the keyboard focus.
   */
  public void focusGained(FocusEvent e) {
    current.focusGained(e);
  }
  
  /** Invoked when a component loses the keyboard focus.
   */
  public void focusLost(FocusEvent e) {
    current.focusLost(e);
    //    JCustomizer customizer = (JCustomizer) e.getComponent();
    //    JCustomizerPane pane = (JCustomizerPane) customizer.getParent();
    //    if (pane.isSelected(customizer)){
    //      setState(selectedState);
    //    } else {
    //      setState(normalState);
    //    }
  }
  
  /** Getter for property nResizeState.
   * @return Value of property nResizeState.
   *
   */
  public ResizeState getNResizeState() {
    return nResizeState;
  }
  
  /** Getter for property eResizeState.
   * @return Value of property eResizeState.
   *
   */
  public ResizeState getEResizeState() {
    return eResizeState;
  }
  
  /** Getter for property nWResizeState.
   * @return Value of property nWResizeState.
   *
   */
  public ResizeState getNWResizeState() {
    return nWResizeState;
  }
  
  /** Getter for property normalState.
   * @return Value of property normalState.
   *
   */
  public State getNormalState() {
    return normalState;
  }
  
  /** Getter for property nEResizeState.
   * @return Value of property nEResizeState.
   *
   */
  public ResizeState getNEResizeState() {
    return nEResizeState;
  }
  
  /** Getter for property moveState.
   * @return Value of property moveState.
   *
   */
  public BoundState getMoveState() {
    return moveState;
  }
  
  /** Getter for property selectedState.
   * @return Value of property selectedState.
   *
   */
  public State getSelectedState() {
    return selectedState;
  }
  
  /** Getter for property sEResizeState.
   * @return Value of property sEResizeState.
   *
   */
  public ResizeState getSEResizeState() {
    return sEResizeState;
  }
  
  /** Getter for property sResizeState.
   * @return Value of property sResizeState.
   *
   */
  public ResizeState getSResizeState() {
    return sResizeState;
  }
  
  /** Getter for property sWResizeState.
   * @return Value of property sWResizeState.
   *
   */
  public ResizeState getSWResizeState() {
    return sWResizeState;
  }
  
  /** Getter for property wResizeState.
   * @return Value of property wResizeState.
   *
   */
  public ResizeState getWResizeState() {
    return wResizeState;
  }
  
  public void setStateNResize() {
    setState(nResizeState);
  }
  
  public void setStateEResize() {
    setState(eResizeState);
  }
  
  public void setStateNWResize() {
    setState(nWResizeState);
  }
  
  public void setStateNormal() {
    setState(normalState);
  }
  
  public void setStateNEResize() {
    setState(nEResizeState);
  }
  
  public void setStateMove() {
    setState(moveState);
  }
  
  public void setStateSelected() {
    setState(selectedState);
  }
  
  public void setStateSEResize() {
    setState(sEResizeState);
  }
  
  public void setStateSResize() {
    setState(sResizeState);
  }
  
  public void setStateSWResize() {
    setState(sWResizeState);
  }
  
  public void setStateWResize() {
    setState(wResizeState);
  }
  
  
  
  public static class DefaultState extends StateAdapter{
    
    private final JCustomizer customizer;
    
    public DefaultState(JCustomizer customizer){
      this.customizer = customizer;
    }
    
    public void applyBorder(){
      customizer.setBorder(null);
    }
    
    public void applyCursor(){
      customizer.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
    
    public void configureCustomizer(){
      applyBorder();
      applyCursor();
    }
    
    public JCustomizer getCustomizer(){
      return customizer;
    }
    
    
  }
  
  public static class ActiveState extends DefaultState{
    
    public ActiveState(JCustomizer customizer){
      super(customizer);
    }
    
    /** Invoked when a component loses the keyboard focus.
     */
    public void focusLost(FocusEvent e) {
      JCustomizer customizer = getCustomizer();
      JCustomizerPane pane = (JCustomizerPane) customizer.getParent();
      StateManager manager = customizer.getStateManager();
      if (pane.getSelectionManager().isSelected(customizer)){
        manager.setStateSelected();
      } else {
        pane.getSelectionManager().deselect(customizer);
      }
    }
  }
  public static abstract class BoundState extends ActiveState{
    
    protected static final HandleBorder HANDLE_BORDER = new HandleBorder(Color.BLUE);
    
    /** Holds value of property startX. */
    private int startX;
    
    /** Holds value of property startY. */
    private int startY;
    
    private boolean dragging;
    
    /** Holds value of property lastX. */
    private int lastX;
    
    /** Holds value of property lastY. */
    private int lastY;
    
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
      lastX = e.getX();
      lastY = e.getY();
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
      if (! dragging){
        dragging = true;
      }
      //      e.translatePoint(- startX, - startY);
      getCustomizer().fireCustomizerReshapeRel(createCustomizerEvent(e));
      //      e.translatePoint(startX, startY);
      lastX = e.getX();
      lastY = e.getY();
    }
    
    public void mouseReleased(MouseEvent e){
      if (dragging){
        getCustomizer().fireCustomizerFinishDragging(createCustomizerEvent(e));
        dragging = false;
      }
    }
    
    public abstract CustomizerEvent createCustomizerEvent(MouseEvent e);
    
    public CustomizerEvent createCustomizerEvent(MouseEvent e, int xFactor,
    int yFactor, int widthFactor, int heightFactor){
      int dx = e.getX() - getStartX();
      int dy = e.getY() - getStartY();
      int dwidth;
      int dheight;
      if (xFactor == 0){
        dwidth = e.getX() - getLastX();
      } else {
        dwidth = e.getX() - getStartX();
      }
      if (yFactor == 0){
        dheight = e.getY() - getLastY();
      } else {
        dheight = e.getY() - getStartY();
      }
      return new CustomizerEvent(getCustomizer(), xFactor*dx,  yFactor*dy, widthFactor*dwidth, heightFactor*dheight);
    }
    
    /** Getter for property lastX.
     * @return Value of property lastX.
     *
     */
    public int getLastX() {
      return this.lastX;
    }
    
    /** Getter for property lastY.
     * @return Value of property lastY.
     *
     */
    public int getLastY() {
      return this.lastY;
    }
    
  }
  
  public static abstract class ResizeState extends BoundState{
    public ResizeState(JCustomizer customizer){
      super(customizer);
      getHandle().setRect(customizer.getWidth(), customizer.getHeight());
    }
    public abstract Handle getHandle();
    public boolean contains(Point p){
      return getHandle().contains(p);
    }
  }
}