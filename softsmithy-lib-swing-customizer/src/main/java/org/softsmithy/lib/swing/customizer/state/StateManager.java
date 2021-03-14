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
package org.softsmithy.lib.swing.customizer.state;

import org.softsmithy.lib.swing.customizer.JCustomizer;
import org.softsmithy.lib.swing.customizer.JCustomizerPane;
import org.softsmithy.lib.swing.customizer.border.HandleBorder;
import org.softsmithy.lib.swing.customizer.border.HandleBorder.Handle;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/*
 * Is this the right place? Should it be nested or in puce.swing?
 */
public class StateManager implements FocusListener, MouseInputListener {

    private JCustomizer customizer;
    private State current;
    private final State normalState;
    private final State selectedState;
    private final BoundState moveState;
    private final ResizeState nResizeState;
    private final ResizeState nEResizeState;
    private final ResizeState eResizeState;
    private final ResizeState sEResizeState;
    private final ResizeState sResizeState;
    private final ResizeState sWResizeState;
    private final ResizeState wResizeState;
    private final ResizeState nWResizeState;
    private final ResizeState[] resizeStates;
    //  /** Holds value of property normalBorderColor. */
    //  private Color normalBorderColor = Color.GRAY;
    //
    //  /** Holds value of property selectedBorderColor. */
    //  private Color selectedBorderColor = Color.BLUE;
    /**
     * Holds value of property defaultNormalBorderColor.
     */
    private final BorderColor defaultNormalBorderColor = new DefaultNormalBorderColor();
    /**
     * Holds value of property defaultSelectedBorderColor.
     */
    private final BorderColor defaultSelectedBorderColor = new DefaultSelectedBorderColor();
    /**
     * Holds value of property customNormalBorderColor.
     */
    private final CustomBorderColor customNormalBorderColor = new CustomBorderColor(Color.GRAY);
    /**
     * Holds value of property customSelectedBorderColor.
     */
    private final CustomBorderColor customSelectedBorderColor = new CustomBorderColor(Color.BLUE);
    /**
     * Holds value of property currentNormalBorderColor.
     */
    private BorderColor currentNormalBorderColor;
    /**
     * Holds value of property currentSelectedBorderColor.
     */
    private BorderColor currentSelectedBorderColor;

    //private JCustomizer customizer;
    public StateManager(final JCustomizer customizer) {
        this.customizer = customizer;

        normalState = new NormalState(customizer);
        selectedState = new SelectedState(customizer);
        moveState = new MoveState(customizer);
        nResizeState = new NResizeState(customizer);
        nEResizeState = new NEResizeState(customizer);
        eResizeState = new EResizeState(customizer);
        sEResizeState = new SEResizeState(customizer);
        sResizeState = new SResizeState(customizer);
        sWResizeState = new SWResizeState(customizer);
        wResizeState = new WResizeState(customizer);
        nWResizeState = new NWResizeState(customizer);

        resizeStates = new ResizeState[]{nResizeState, nEResizeState, eResizeState, sEResizeState,
            sResizeState, sWResizeState, wResizeState, nWResizeState};
        setUsingDefaultNormalBorderColor(true);
        setUsingDefaultSelectedBorderColor(true);
        //    getNormalState().resetBorder(getNormalBorderColor());
        //    getSelectedState().resetBorder(getSelectedBorderColor());
        //    getMoveState().resetBorder(getSelectedBorderColor());
        setState(normalState);
    }

    protected void setState(State state) {
        if (current != null) {
            current.unconfigureCustomizer();
            current.setActive(false);
        }
        current = state;
        current.configureCustomizer();
        current.setActive(true);
    }

    public void setStateBound(Point point) {
        BoundState state = getBoundStateAt(point);
        state.setStart(point);
        setState(state);
        customizer.invalidate();
        customizer.doLayout();
    }

    private BoundState getBoundStateAt(Point p) {
        BoundState state = moveState;
        for (int i = 0; i < resizeStates.length; i++) {
            if (resizeStates[i].contains(p)) {
                state = resizeStates[i];
                break;
            }
        }
        return state;
    }

    public JCustomizer getCustomizer() {
        return customizer;
    }

    public void configureCustomizer() {
    }

    public void unconfigureCustomizer() {
    }

    /**
     * Invoked when the mouse button has been clicked (pressed and released) on a component.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        current.mouseClicked(e);
        e.consume();
    }

    /**
     * Invoked when a mouse button is pressed on a component and then dragged.
     * <code>MOUSE_DRAGGED</code> events will continue to be delivered to the component where the drag originated until
     * the mouse button is released (regardless of whether the mouse position is within the bounds of the component).
     * <p> Due to platform-dependent Drag&amp;Drop implementations,
     * <code>MOUSE_DRAGGED</code> events may not be delivered during a native Drag&amp;Drop operation.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        current.mouseDragged(e);
        e.consume();
    }

    /**
     * Invoked when the mouse enters a component.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        current.mouseEntered(e);
        e.consume();
    }

    /**
     * Invoked when the mouse exits a component.
     */
    @Override
    public void mouseExited(MouseEvent e) {
        current.mouseExited(e);
        e.consume();
    }

    /**
     * Invoked when the mouse button has been moved on a component (with no buttons down).
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        current.mouseMoved(e);
        e.consume();
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        current.mousePressed(e);
        e.consume();
    }

    /**
     * Invoked when a mouse button has been released on a component.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        current.mouseReleased(e);
        e.consume();
    }

    /**
     * Invoked when a component gains the keyboard focus.
     */
    @Override
    public void focusGained(FocusEvent e) {
        current.focusGained(e);
    }

    /**
     * Invoked when a component loses the keyboard focus.
     */
    @Override
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

    /**
     * Getter for property nResizeState.
     *
     * @return Value of property nResizeState.
     *
     */
    public ResizeState getNResizeState() {
        return nResizeState;
    }

    /**
     * Getter for property eResizeState.
     *
     * @return Value of property eResizeState.
     *
     */
    public ResizeState getEResizeState() {
        return eResizeState;
    }

    /**
     * Getter for property nWResizeState.
     *
     * @return Value of property nWResizeState.
     *
     */
    public ResizeState getNWResizeState() {
        return nWResizeState;
    }

    /**
     * Getter for property normalState.
     *
     * @return Value of property normalState.
     *
     */
    public State getNormalState() {
        return normalState;
    }

    /**
     * Getter for property nEResizeState.
     *
     * @return Value of property nEResizeState.
     *
     */
    public ResizeState getNEResizeState() {
        return nEResizeState;
    }

    /**
     * Getter for property moveState.
     *
     * @return Value of property moveState.
     *
     */
    public BoundState getMoveState() {
        return moveState;
    }

    /**
     * Getter for property selectedState.
     *
     * @return Value of property selectedState.
     *
     */
    public State getSelectedState() {
        return selectedState;
    }

    /**
     * Getter for property sEResizeState.
     *
     * @return Value of property sEResizeState.
     *
     */
    public ResizeState getSEResizeState() {
        return sEResizeState;
    }

    /**
     * Getter for property sResizeState.
     *
     * @return Value of property sResizeState.
     *
     */
    public ResizeState getSResizeState() {
        return sResizeState;
    }

    /**
     * Getter for property sWResizeState.
     *
     * @return Value of property sWResizeState.
     *
     */
    public ResizeState getSWResizeState() {
        return sWResizeState;
    }

    /**
     * Getter for property wResizeState.
     *
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

    /**
     * Getter for property normalBorderColor.
     *
     * @return Value of property normalBorderColor.
     *
     */
    public Color getNormalBorderColor() {
        return getCurrentNormalBorderColor().getColor();
    }

    //  /** Setter for property normalBorderColor.
    //   * @param normalBorderColor New value of property normalBorderColor.
    //   *
    //   */
    //  public void setNormalBorderColor(Color normalBorderColor) {
    //    this.normalBorderColor = normalBorderColor;
    //    getNormalState().resetBorder(normalBorderColor);
    //  }
    /**
     * Getter for property selectedBorderColor.
     *
     * @return Value of property selectedBorderColor.
     *
     */
    public Color getSelectedBorderColor() {
        return getCurrentSelectedBorderColor().getColor();
    }

    //  /** Setter for property selectedBorderColor.
    //   * @param selectedBorderColor New value of property selectedBorderColor.
    //   *
    //   */
    //  public void setSelectedBorderColor(Color selectedBorderColor) {
    //    this.selectedBorderColor = selectedBorderColor;
    //    getSelectedState().resetBorder(selectedBorderColor);
    //    getMoveState().resetBorder(selectedBorderColor);
    //  }
    /**
     * Getter for property usingDefaultNormalBorderColor.
     *
     * @return Value of property usingDefaultNormalBorderColor.
     *
     */
    public boolean isUsingDefaultNormalBorderColor() {
        return getCurrentNormalBorderColor().isDefault();
    }

    /**
     * Setter for property usingDefaultNormalBorderColor.
     *
     * @param usingDefaultNormalBorderColor New value of property usingDefaultNormalBorderColor.
     *
     */
    public void setUsingDefaultNormalBorderColor(boolean usingDefaultNormalBorderColor) {
        if (usingDefaultNormalBorderColor) {
            setCurrentNormalBorderColor(defaultNormalBorderColor);
        } else {
            setCurrentNormalBorderColor(customNormalBorderColor);
        }
    }

    /**
     * Getter for property usingDefaultSelectedBorderColor.
     *
     * @return Value of property usingDefaultSelectedBorderColor.
     *
     */
    public boolean isUsingDefaultSelectedBorderColor() {
        return getCurrentSelectedBorderColor().isDefault();
    }

    /**
     * Setter for property usingDefaultSelectedBorderColor.
     *
     * @param usingDefaultSelectedBorderColor New value of property usingDefaultSelectedBorderColor.
     *
     */
    public void setUsingDefaultSelectedBorderColor(boolean usingDefaultSelectedBorderColor) {
        if (usingDefaultSelectedBorderColor) {
            setCurrentSelectedBorderColor(defaultSelectedBorderColor);
        } else {
            setCurrentSelectedBorderColor(customSelectedBorderColor);
        }
    }

    /**
     * Getter for property defaultNormalBorderColor.
     *
     * @return Value of property defaultNormalBorderColor.
     *
     */
    private BorderColor getDefaultNormalBorderColor() {
        return this.defaultNormalBorderColor;
    }

    /**
     * Getter for property defaultSelectedBorderColor.
     *
     * @return Value of property defaultSelectedBorderColor.
     *
     */
    private BorderColor getDefaultSelectedBorderColor() {
        return this.defaultSelectedBorderColor;
    }

    /**
     * Getter for property customNormalBorderColor.
     *
     * @return Value of property customNormalBorderColor.
     *
     */
    public Color getCustomNormalBorderColor() {
        return this.customNormalBorderColor.getColor();
    }

    public void setCustomNormalBorderColor(Color color) {
        customNormalBorderColor.setColor(color);
    }

    /**
     * Getter for property customSelectedBorderColor.
     *
     * @return Value of property customSelectedBorderColor.
     *
     */
    public Color getCustomSelectedBorderColor() {
        return this.customSelectedBorderColor.getColor();
    }

    public void setCustomSelectedBorderColor(Color color) {
        customSelectedBorderColor.setColor(color);
    }

    /**
     * Getter for property currentNormalBorderColor.
     *
     * @return Value of property currentNormalBorderColor.
     *
     */
    private BorderColor getCurrentNormalBorderColor() {
        return this.currentNormalBorderColor;
    }

    /**
     * Setter for property currentNormalBorderColor.
     *
     * @param currentNormalBorderColor New value of property currentNormalBorderColor.
     *
     */
    private void setCurrentNormalBorderColor(BorderColor currentNormalBorderColor) {
        if (this.currentNormalBorderColor != null) {
            this.currentNormalBorderColor.stopListening();
        }
        this.currentNormalBorderColor = currentNormalBorderColor;
        this.currentNormalBorderColor.startListening();
        getNormalState().resetBorder(currentNormalBorderColor.getColor());
    }

    /**
     * Getter for property currentSelectedBorderColor.
     *
     * @return Value of property currentSelectedBorderColor.
     *
     */
    private BorderColor getCurrentSelectedBorderColor() {
        return this.currentSelectedBorderColor;
    }

    /**
     * Setter for property currentSelectedBorderColor.
     *
     * @param currentSelectedBorderColor New value of property currentSelectedBorderColor.
     *
     */
    public void setCurrentSelectedBorderColor(BorderColor currentSelectedBorderColor) {
        if (this.currentSelectedBorderColor != null) {
            this.currentSelectedBorderColor.stopListening();
        }
        this.currentSelectedBorderColor = currentSelectedBorderColor;
        this.currentSelectedBorderColor.startListening();
        resetSelectedBorder();
    }

    private void resetSelectedBorder() {
        getSelectedState().resetBorder(getCurrentSelectedBorderColor().getColor());
        getMoveState().resetBorder(getCurrentSelectedBorderColor().getColor());
        for (ResizeState resizeState : resizeStates) {
            resizeState.setBorder(getMoveState().getBorder());
        }
    }

    /**
     * The default state. Fires an ActionEvent when double-clicked!
     */
    public static class DefaultState extends AbstractState {

        private Border border;
        private final JCustomizer customizer;

//    public DefaultState(JCustomizer customizer){
//      this(customizer, Color.GRAY);
//    }
        public DefaultState(JCustomizer customizer) {//, Color color){
            this.customizer = customizer;
            //resetBorder(color);
        }

        @Override
        public void applyBorder() {
            customizer.applyBorder(border);
        }

        @Override
        public void applyCursor() {
            customizer.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }

        @Override
        public void resetBorder(Color borderColor) {
            setBorder(BorderFactory.createLineBorder(borderColor, 1)); //10));
        }

        public Border getBorder() {
            return border;
        }

        public void setBorder(Border border) {
            this.border = border;
            if (isActive()) {
                applyBorder();
            }
        }

        @Override
        public void configureCustomizer() {
            applyBorder();
            applyCursor();
        }

        @Override
        public JCustomizer getCustomizer() {
            return customizer;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() > 1) {
                JCustomizerPane pane = (JCustomizerPane) getCustomizer().getParent();
                pane.getSelectionManager().singleSelect(getCustomizer(), e.getPoint());
                getCustomizer().fireActionEvent(new ActionEvent(getCustomizer(), ActionEvent.ACTION_PERFORMED, ""));
            }
        }
    }

    private static class NormalState extends DefaultState {

        public NormalState(JCustomizer customizer) {
            super(customizer);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            JCustomizerPane pane = (JCustomizerPane) getCustomizer().getParent();
            if (e.isControlDown()) {
                pane.getSelectionManager().select(getCustomizer(), e.getPoint());
            } else {
                pane.getSelectionManager().singleSelect(getCustomizer(), e.getPoint());
            }
        }
    }

    private static class SelectedState extends DefaultState {

        public SelectedState(JCustomizer customizer) {
            super(customizer);//, Color.BLUE);
        }

        //      private final Border LINE_BORDER = BorderFactory.createLineBorder(Color.BLUE);
        @Override
        public void mousePressed(MouseEvent e) {
            JCustomizerPane pane = (JCustomizerPane) getCustomizer().getParent();
            if (e.isControlDown()) {
                pane.getSelectionManager().deselect(getCustomizer());
            } else {
                pane.getSelectionManager().singleSelect(getCustomizer(), e.getPoint());
            }
        }
        //      public void applyBorder(){
        //        customizer.applyBorder(LINE_BORDER);
        //      }
    }

    public static abstract class BoundState extends DefaultState {

        //    protected static HandleBorder HANDLE_BORDER;
        /**
         * Holds value of property startX.
         */
        private int startX;
        /**
         * Holds value of property startY.
         */
        private int startY;
        private boolean dragging;
        /**
         * Holds value of property lastX.
         */
        private int lastX;
        /**
         * Holds value of property lastY.
         */
        private int lastY;

        public BoundState(JCustomizer customizer) {
            super(customizer);
        }
        //    public void applyBorder(){
        //      getCustomizer().applyBorder(HANDLE_BORDER);
        //    }

        public HandleBorder getHandleBorder() {
            return (HandleBorder) getBorder();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            StateManager manager = getCustomizer().getStateManager();
            BoundState state = manager.getBoundStateAt(e.getPoint());
            if (state != this) {
                manager.setState(state);
            }
        }

        /**
         * Getter for property startX.
         *
         * @return Value of property startX.
         */
        public int getStartX() {
            return this.startX;
        }

        /**
         * Getter for property startY.
         *
         * @return Value of property startY.
         */
        public int getStartY() {
            return this.startY;
        }

        /**
         * Invoked when a mouse button has been pressed on a component.
         */
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            //      if(!getCustomizer().hasFocus()){
            //        getCustomizer().requestFocus();
            //      }
            startX = e.getX();
            startY = e.getY();
            lastX = e.getX();
            lastY = e.getY();
        }

        /**
         * Invoked when a mouse button is pressed on a component and then dragged.
         * <code>MOUSE_DRAGGED</code> events will continue to be delivered to the component where the drag originated
         * until the mouse button is released (regardless of whether the mouse position is within the bounds of the
         * component). <p> Due to platform-dependent Drag&amp;Drop implementations,
         * <code>MOUSE_DRAGGED</code> events may not be delivered during a native Drag&amp;Drop operation.
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            if (!isDragging()) {
                dragging = true;
            }
            //      e.translatePoint(- startX, - startY);
            Rectangle relRect = createRelRectangle(e);
            getCustomizer().setBoundsRel(relRect.x, relRect.y, relRect.width, relRect.height);//fireCustomizerResetBoundsRel(createRelRectangle(e));
            //      e.translatePoint(startX, startY);
            lastX = e.getX();
            lastY = e.getY();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (isDragging()) {
                Rectangle relRect = createRelRectangle(e);
                getCustomizer().reshapeRel(relRect.x, relRect.y, relRect.width, relRect.height);//fireCustomizerReshapeRel(createCustomizerEvent(e));
                dragging = false;
            }
        }

        public abstract Rectangle createRelRectangle(MouseEvent e);

        public Rectangle createRelRectangle(MouseEvent e, int xFactor,
                int yFactor, int widthFactor, int heightFactor) {
            int dx = e.getX() - getStartX();
            int dy = e.getY() - getStartY();
            int dwidth;
            int dheight;
            if (xFactor == 0) {
                dwidth = e.getX() - getLastX();
            } else {
                dwidth = e.getX() - getStartX();
            }
            if (yFactor == 0) {
                dheight = e.getY() - getLastY();
            } else {
                dheight = e.getY() - getStartY();
            }
            return new Rectangle(xFactor * dx, yFactor * dy, widthFactor * dwidth, heightFactor * dheight);
        }

        /**
         * Getter for property lastX.
         *
         * @return Value of property lastX.
         *
         */
        public int getLastX() {
            return this.lastX;
        }

        /**
         * Getter for property lastY.
         *
         * @return Value of property lastY.
         *
         */
        public int getLastY() {
            return this.lastY;
        }

        public void setStart(Point point) {
            startX = point.x;
            startY = point.y;
        }

        public boolean isDragging() {
            return dragging;
        }

        @Override
        public void resetBorder(Color borderColor) {
            setBorder(new HandleBorder(borderColor, 0));
            for (int i = 0; i < getHandleBorder().getHandles().length; i++) {
                getHandleBorder().getHandles()[i].setRect(getCustomizer().getWidth(), getCustomizer().getHeight());
            }
        }
    }

    private static class MoveState extends BoundState {

        public MoveState(JCustomizer customizer) {
            super(customizer);
        }

        @Override
        public void applyCursor() {
            getCustomizer().setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }

        @Override
        public Rectangle createRelRectangle(MouseEvent e) {
            return createRelRectangle(e, 1, 1, 0, 0);
        }
    }

    public static abstract class ResizeState extends BoundState {

        private boolean draggingStarted = false;

        public ResizeState(JCustomizer customizer) {
            super(customizer);
            //getHandle().setRect(customizer.getWidth(), customizer.getHeight());
        }

        public abstract Handle getHandle();

        public boolean contains(Point p) {
            return getHandle().contains(p);
        }

        /**
         * Invoked when the mouse exits a component.
         *
         */
        @Override
        public void mouseExited(MouseEvent e) {
            if (!draggingStarted) {
                StateManager manager = getCustomizer().getStateManager();
                manager.setState(manager.getMoveState());
            }
        }

        /**
         * Invoked when a mouse button has been pressed on a component.
         *
         */
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            draggingStarted = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            draggingStarted = false;
        }
    }

    private static class NResizeState extends ResizeState {

        public NResizeState(JCustomizer customizer) {
            super(customizer);
        }

        @Override
        public void applyCursor() {
            getCustomizer().setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
        }

        @Override
        public Handle getHandle() {
            return getHandleBorder().getNHandle();
        }

        @Override
        public Rectangle createRelRectangle(MouseEvent e) {
            return createRelRectangle(e, 0, 1, 0, -1);
        }
    }

    private static class NEResizeState extends ResizeState {

        public NEResizeState(JCustomizer customizer) {
            super(customizer);
        }

        @Override
        public void applyCursor() {
            getCustomizer().setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
        }

        @Override
        public Handle getHandle() {
            return getHandleBorder().getNEHandle();
        }

        @Override
        public Rectangle createRelRectangle(MouseEvent e) {
            return createRelRectangle(e, 0, 1, 1, - 1);
        }
    }

    private static class EResizeState extends ResizeState {

        public EResizeState(JCustomizer customizer) {
            super(customizer);
        }

        @Override
        public void applyCursor() {
            getCustomizer().setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        }

        @Override
        public Handle getHandle() {
            return getHandleBorder().getEHandle();
        }

        @Override
        public Rectangle createRelRectangle(MouseEvent e) {
            return createRelRectangle(e, 0, 0, 1, 0);
        }
    }

    private static class SEResizeState extends ResizeState {

        public SEResizeState(JCustomizer customizer) {
            super(customizer);
        }

        @Override
        public void applyCursor() {
            getCustomizer().setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        }

        @Override
        public Handle getHandle() {
            return getHandleBorder().getSEHandle();
        }

        @Override
        public Rectangle createRelRectangle(MouseEvent e) {
            return createRelRectangle(e, 0, 0, 1, 1);
        }
    }

    private static class SResizeState extends ResizeState {

        public SResizeState(JCustomizer customizer) {
            super(customizer);
        }

        @Override
        public void applyCursor() {
            getCustomizer().setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
        }

        @Override
        public Handle getHandle() {
            return getHandleBorder().getSHandle();
        }

        @Override
        public Rectangle createRelRectangle(MouseEvent e) {
            return createRelRectangle(e, 0, 0, 0, 1);
        }
    }

    private static class SWResizeState extends ResizeState {

        public SWResizeState(JCustomizer customizer) {
            super(customizer);
        }

        @Override
        public void applyCursor() {
            getCustomizer().setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
        }

        @Override
        public Handle getHandle() {
            return getHandleBorder().getSWHandle();
        }

        @Override
        public Rectangle createRelRectangle(MouseEvent e) {
            return createRelRectangle(e, 1, 0, - 1, 1);
        }
    }

    private static class WResizeState extends ResizeState {

        public WResizeState(JCustomizer customizer) {
            super(customizer);
        }

        @Override
        public void applyCursor() {
            getCustomizer().setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
        }

        @Override
        public Handle getHandle() {
            return getHandleBorder().getWHandle();
        }

        @Override
        public Rectangle createRelRectangle(MouseEvent e) {
            return createRelRectangle(e, 1, 0, - 1, 0);
        }
    }

    private static class NWResizeState extends ResizeState {

        public NWResizeState(JCustomizer customizer) {
            super(customizer);
        }

        @Override
        public void applyCursor() {
            getCustomizer().setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
        }

        @Override
        public Handle getHandle() {
            return getHandleBorder().getNWHandle();
        }

        @Override
        public Rectangle createRelRectangle(MouseEvent e) {
            return createRelRectangle(e, 1, 1, - 1, - 1);
        }
    }

    private static interface BorderColor {

        Color getColor();

        boolean isDefault();

        void startListening();

        void stopListening();
    }

    private static abstract class AbstractDefaultBorderColor implements BorderColor {

        @Override
        public boolean isDefault() {
            return true;
        }
    }

    private class DefaultNormalBorderColor extends AbstractDefaultBorderColor {

        private final HierarchyListener parentListener = new ParentListener();
        private final PropertyChangeListener defaultNormalCustomizerBorderColorListener = new DefaultNormalCustomizerBorderColorListener();

        @Override
        public Color getColor() {
            return getCustomizer().getParentCustomizerPane() != null ? getCustomizer().getParentCustomizerPane().
                    getDefaultNormalCustomizerBorderColor() : customNormalBorderColor.getColor();
        }

        @Override
        public void startListening() {
            getCustomizer().addHierarchyListener(parentListener);
            if (getCustomizer().getParentCustomizerPane() != null) {
                getCustomizer().getParentCustomizerPane().
                        addPropertyChangeListener("defaultNormalCustomizerBorderColor",
                        defaultNormalCustomizerBorderColorListener);
            }

        }

        @Override
        public void stopListening() {
            getCustomizer().removeHierarchyListener(parentListener);
            if (getCustomizer().getParentCustomizerPane() != null) {
                getCustomizer().getParentCustomizerPane().removePropertyChangeListener(
                        "defaultNormalCustomizerBorderColor", defaultNormalCustomizerBorderColorListener);
            }
        }

        private class ParentListener implements HierarchyListener {

            /**
             * Called when the hierarchy has been changed. To discern the actual type of change, call
             * <code>HierarchyEvent.getChangeFlags()</code>.
             *
             * @see HierarchyEvent#getChangeFlags()
             *
             */
            @Override
            public void hierarchyChanged(HierarchyEvent e) {
                //System.out.println("hierarchyChanged!!!");
                // optimize??
                if (e.getChangedParent() != null) { // why is this neccessary?
                    e.getChangedParent().removePropertyChangeListener("defaultNormalCustomizerBorderColor",
                            defaultNormalCustomizerBorderColorListener);
                }
                if (getCustomizer().getParentCustomizerPane() != null) {
                    getCustomizer().getParentCustomizerPane().addPropertyChangeListener(
                            "defaultNormalCustomizerBorderColor", defaultNormalCustomizerBorderColorListener);
                }
                getNormalState().resetBorder(getColor());
            }
        }

        private class DefaultNormalCustomizerBorderColorListener implements PropertyChangeListener {

            /**
             * This method gets called when a bound property is changed.
             *
             * @param evt A PropertyChangeEvent object describing the event source and the property that has changed.
             *
             */
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                //System.out.println("Default Normal Border Color changed");
                getNormalState().resetBorder(getColor());
            }
        }
    }

    private class DefaultSelectedBorderColor extends AbstractDefaultBorderColor {

        private final HierarchyListener parentListener = new ParentListener();
        private final PropertyChangeListener defaultSelectedCustomizerBorderColorListener = new DefaultSelectedCustomizerBorderColorListener();

        @Override
        public Color getColor() {
            return getCustomizer().getParentCustomizerPane() != null ? getCustomizer().getParentCustomizerPane().
                    getDefaultSelectedCustomizerBorderColor() : customSelectedBorderColor.getColor();
        }

        @Override
        public void startListening() {
            getCustomizer().addHierarchyListener(parentListener);
            if (getCustomizer().getParentCustomizerPane() != null) {
                getCustomizer().getParentCustomizerPane().addPropertyChangeListener(
                        "defaultSelectedCustomizerBorderColor", defaultSelectedCustomizerBorderColorListener);
            }

        }

        @Override
        public void stopListening() {
            getCustomizer().removeHierarchyListener(parentListener);
            if (getCustomizer().getParentCustomizerPane() != null) {
                getCustomizer().getParentCustomizerPane().removePropertyChangeListener(
                        "defaultSelectedCustomizerBorderColor", defaultSelectedCustomizerBorderColorListener);
            }

        }

        private class ParentListener implements HierarchyListener {

            /**
             * Called when the hierarchy has been changed. To discern the actual type of change, call
             * <code>HierarchyEvent.getChangeFlags()</code>.
             *
             * @see HierarchyEvent#getChangeFlags()
             *
             */
            @Override
            public void hierarchyChanged(HierarchyEvent e) {
                //System.out.println("hierarchyChanged");
                // optimize??
                if (e.getChangedParent() != null) { // why is this neccessary?
                    e.getChangedParent().removePropertyChangeListener("defaultSelectedCustomizerBorderColor",
                            defaultSelectedCustomizerBorderColorListener);
                }
                if (getCustomizer().getParentCustomizerPane() != null) {
                    getCustomizer().getParentCustomizerPane().addPropertyChangeListener(
                            "defaultSelectedCustomizerBorderColor", defaultSelectedCustomizerBorderColorListener);
                }
                resetSelectedBorder();
            }
        }

        private class DefaultSelectedCustomizerBorderColorListener implements PropertyChangeListener {

            /**
             * This method gets called when a bound property is changed.
             *
             * @param evt A PropertyChangeEvent object describing the event source and the property that has changed.
             *
             */
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                //System.out.println("Default Selected Border Color changed");
                resetSelectedBorder();
            }
        }
    }

    private static class CustomBorderColor implements BorderColor {

        private Color color;

        public CustomBorderColor(Color color) {
            this.color = color;
        }

        @Override
        public Color getColor() {
            return this.color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public boolean isDefault() {
            return false;
        }

        @Override
        public void startListening() {
        }

        @Override
        public void stopListening() {
        }
    }
}