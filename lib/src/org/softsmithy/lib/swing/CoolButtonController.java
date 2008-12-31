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
 * CoolButtonController.java
 *
 * Created on 6. Oktober 2002, 17:00
 */
package org.softsmithy.lib.swing;

import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author  puce
 */
public class CoolButtonController extends MouseAdapter implements ItemListener {

    private AbstractButton button;
    private boolean mouseIsOver = false;

    /** Creates a new instance of CoolButtonController */
    public CoolButtonController(AbstractButton button) { // TODO: should be public?
        this.button = button;
        button.setBorderPainted(false);
    // button.setFocusPainted(false);
    }

    /** Invoked when the mouse enters a component.
     *
     */
    public void mouseEntered(MouseEvent e) {
        if (!button.isBorderPainted() && button.isEnabled()) {
            button.setBorderPainted(true);
        }
        mouseIsOver = true;
    }

    /** Invoked when the mouse exits a component.
     *
     */
    public void mouseExited(MouseEvent e) {
        if (button.isBorderPainted() && !button.isSelected()) {
            button.setBorderPainted(false);
        }
        mouseIsOver = false;
    }

    /** Invoked when an item has been selected or deselected by the user.
     * The code written for this method performs the operations
     * that need to occur when an item is selected (or deselected).
     *
     */
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            if (!mouseIsOver && button.isBorderPainted()) {
                button.setBorderPainted(false);
            }
        } else { // ItemEvent.SELECTED
            if (!mouseIsOver && !button.isBorderPainted() && button.isEnabled()) {
                button.setBorderPainted(true);
            }
        }
    }

    public static void addCoolButtonController(AbstractButton button) {
        CoolButtonController controller = new CoolButtonController(button);
        button.addMouseListener(controller);
        button.addItemListener(controller);
    }

    public static void removeCoolButtonController(AbstractButton button) {
        CoolButtonController controller = new CoolButtonController(button);
        button.removeMouseListener(controller);
        button.removeItemListener(controller);
    }

    /** Returns a hash code value for the object. This method is
     * supported for the benefit of hashtables such as those provided by
     * <code>java.util.Hashtable</code>.
     * <p>
     * The general contract of <code>hashCode</code> is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during
     *     an execution of a Java application, the <tt>hashCode</tt> method
     *     must consistently return the same integer, provided no information
     *     used in <tt>equals</tt> comparisons on the object is modified.
     *     This integer need not remain consistent from one execution of an
     *     application to another execution of the same application.
     * <li>If two objects are equal according to the <tt>equals(Object)</tt>
     *     method, then calling the <code>hashCode</code> method on each of
     *     the two objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal
     *     according to the {@link java.lang.Object#equals(java.lang.Object)}
     *     method, then calling the <tt>hashCode</tt> method on each of the
     *     two objects must produce distinct integer results.  However, the
     *     programmer should be aware that producing distinct integer results
     *     for unequal objects may improve the performance of hashtables.
     * </ul>
     * <p>
     * As much as is reasonably practical, the hashCode method defined by
     * class <tt>Object</tt> does return distinct integers for distinct
     * objects. (This is typically implemented by converting the internal
     * address of the object into an integer, but this implementation
     * technique is not required by the
     * Java<font size="-2"><sup>TM</sup></font> programming language.)
     *
     * @return  a hash code value for this object.
     * @see     java.lang.Object#equals(java.lang.Object)
     * @see     java.util.Hashtable
     *
     */
    public int hashCode() {
        int hashCode = 17;
        hashCode = 37 * hashCode + button.hashCode();
        return hashCode;
    }

    /** Indicates whether some other object is "equal to" this one.
     * <p>
     * The <code>equals</code> method implements an equivalence relation:
     * <ul>
     * <li>It is <i>reflexive</i>: for any reference value <code>x</code>,
     *     <code>x.equals(x)</code> should return <code>true</code>.
     * <li>It is <i>symmetric</i>: for any reference values <code>x</code> and
     *     <code>y</code>, <code>x.equals(y)</code> should return
     *     <code>true</code> if and only if <code>y.equals(x)</code> returns
     *     <code>true</code>.
     * <li>It is <i>transitive</i>: for any reference values <code>x</code>,
     *     <code>y</code>, and <code>z</code>, if <code>x.equals(y)</code>
     *     returns  <code>true</code> and <code>y.equals(z)</code> returns
     *     <code>true</code>, then <code>x.equals(z)</code> should return
     *     <code>true</code>.
     * <li>It is <i>consistent</i>: for any reference values <code>x</code>
     *     and <code>y</code>, multiple invocations of <tt>x.equals(y)</tt>
     *     consistently return <code>true</code> or consistently return
     *     <code>false</code>, provided no information used in
     *     <code>equals</code> comparisons on the object is modified.
     * <li>For any non-null reference value <code>x</code>,
     *     <code>x.equals(null)</code> should return <code>false</code>.
     * </ul>
     * <p>
     * The <tt>equals</tt> method for class <code>Object</code> implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any reference values <code>x</code> and <code>y</code>,
     * this method returns <code>true</code> if and only if <code>x</code> and
     * <code>y</code> refer to the same object (<code>x==y</code> has the
     * value <code>true</code>).
     * <p>
     * Note that it is generally necessary to override the <tt>hashCode</tt>
     * method whenever this method is overridden, so as to maintain the
     * general contract for the <tt>hashCode</tt> method, which states
     * that equal objects must have equal hash codes.
     *
     * @param   obj   the reference object with which to compare.
     * @return  <code>true</code> if this object is the same as the obj
     *          argument; <code>false</code> otherwise.
     * @see     #hashCode()
     * @see     java.util.Hashtable
     *
     */
    public boolean equals(Object obj) {
        if (!(obj instanceof CoolButtonController)) {
            return false;
        }
        CoolButtonController controller = (CoolButtonController) obj;
        return (button == controller.button || (button != null && button.equals(controller.button))); // TODO: don't test for mouseIsOver!?
    }
}
