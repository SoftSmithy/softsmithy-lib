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

package org.softsmithy.lib.swing.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A trampoline class that reflectivly calls an action method. The method must
 * take an ActionEvent object as its single parameter.<br>
 * <p>
 *
 * Normally there are two standard ways to implement the ActionListener interface:
 * The first is to let a class implement the ActionListener interface and then add an
 * object of this class to the addActionListener methods of the components
 * firing ActionEvents. You then have to check the source in the
 * actionPerformed method. You end up with a big ugly, unmaintainable and
 * unstable if-then-else- statement. The second approach is to define a class for
 * every such component. You end up with tons of small classes all
 * increasing the memory footprint (typically 3K per class). 
 * Now this approach using reflection lets you define a method for each such component avoiding the
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
 * <p> Note: This class might change package in a future version!<br>
 * Note: This class might throw different Exceptions in a future version!<br>
 *
 *
 * @author    Florian Brunner
 */

public class ReflectiveXAction extends AbstractXAction {

  private static final Logger LOG = LoggerFactory.getLogger(ReflectiveXAction.class);
  private final static Class[] PARAMETER_TYPES = new Class[]{ActionEvent.class};

  private final Object fTarget;
  private final Method fMethod;

  /**
   * Creates a new ReflectiveAction
   *
   * @param target                     the object with the specified method
   * @param methodName                 the method name (must be public and take an ActionEvent
   *      obect as its single parameter)
   * @exception NoSuchMethodException  if no such method found
   */
  public ReflectiveXAction(Object target, String methodName) throws NoSuchMethodException {
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
   * Invoked when an action occurs. Springs to the method specified in the
   * constructor.
   *
   * @param e  an ActionEvent object
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      fMethod.invoke(fTarget, new Object[]{e});
    } catch (InvocationTargetException ex1) {
      // should I throw an unchecked exception?
      LOG.error(ex1.getMessage(), ex1);
    } catch (IllegalAccessException ex2) {
      // should I throw an unchecked exception?
      // cannot happen here!?
      LOG.error(ex2.getMessage(), ex2);
    }
  }

}
