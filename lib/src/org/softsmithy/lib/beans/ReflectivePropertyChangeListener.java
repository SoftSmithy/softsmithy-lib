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

package org.softsmithy.lib.beans;

import java.beans.*;
import java.lang.reflect.*;



/**
 * A trampoline class that reflectivly calls an action method. The method must
 * take an ActionEvent object as its single parameter.<br>
 * <p>
 *
 * Normally there are two standard ways to implement the ActionListener interface:
 * The first is to let a class implement the ActionListener interface and then add an
 * object of this class to the addActionListener methods of the components
 * fireing ActionEvents. You then have to check the source in the
 * actionPerformed method. You end up with a big ugly, unmaintainable and
 * unstable if-then-else- statement. The second approach is to define a class for
 * every such component. You end up with tons of small classes all
 * increasing the memory footprint (typically 3K per class). This approach using
 * reflection lets you define a method for each such component avoiding the
 * mentioned drawbacks. The new drawbacks are however:
 * <ol>
 *   <li> Reflectivly calling method does not identify typos during
 *   compile-time.</li>
 *   <li> Calling a method refectivly is slower (but it's unlikely the user
 *   notices it).</li>
 * </ol>
 * More to read about reflective actions: <a
 * href="http://java.sun.com/docs/books/performance/1st_edition/html/JPClassLoading.fm.html#11197">
 * Chapter 6.2.3, p. 73</a> of the Java Series Book <a
 * href="http://java.sun.com/docs/books/performance/"> Java Platform Performance
 * Strategies and Tactics</a> by Steve Wilson and Jeff Kesselman.<br>
 * </p> Note: This class might change package in a future version!<br>
 * Note: This class might throw different Exceptions in a future version!<br>
 *
 *
 * @author    Florian Brunner
 */

public class ReflectivePropertyChangeListener implements PropertyChangeListener {

  private final static Class[] PARAMETER_TYPES = new Class[]{PropertyChangeEvent.class};

  private final Object fTarget;
  private final Method fMethod;

  /**
   * Creates a new ReflectiveAction
   *
   * @param target                     the object with the specified method
   * @param methodName                 the method name (must take an ActionEvent
   *      obect as its single parameter)
   * @exception NoSuchMethodException  if no such method found
   */
  public ReflectivePropertyChangeListener(Object target, String methodName) throws NoSuchMethodException {
    fTarget = target;
    fMethod = fTarget.getClass().getMethod(methodName, PARAMETER_TYPES);
    // would this be a good idea?
    /*
     *  if (! fMethod.isAccessible()){ // to avoid an uncheckable IllegalAccessException later!?
     *  throw new puce.lang.reflect.NotAccessibleException(fMethod.toString() + " is not accessible!");
     *  }
     */
  }

  /**
   * Invoked when an item has been selected or deselected by the user. 
   * Springs to the method specified in the constructor.
   *
   * @param e  an ActionEvent object
   */
  public void propertyChange(PropertyChangeEvent e) {
    try {
      fMethod.invoke(fTarget, new Object[]{e});
    } catch (InvocationTargetException ex1) {
      // should I throw an unchecked exception?
      ex1.printStackTrace();
    } catch (IllegalAccessException ex2) {
      // should I throw an unchecked exception?
      // cannot happen here!?
      ex2.printStackTrace();
    }
  }

}
