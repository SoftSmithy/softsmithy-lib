/*
 * NavigationActionFactory.java
 *
 * Created on 22. Juni 2004, 16:09
 */

package org.softsmithy.lib.swing.action;

/**
 *
 * @author  puce
 */
public class NavigationActionFactory extends StandardActionFactory{
  
  /** Creates a new instance of NavigationActionFactory */
  private NavigationActionFactory(String name) {
    super(name);
  }
 
  public static final NavigationActionFactory BACK = new NavigationActionFactory("navigationBack");
  public static final NavigationActionFactory DOWN = new NavigationActionFactory("navigationDown");
  public static final NavigationActionFactory FORWARD = new NavigationActionFactory("navigationForward");
  public static final NavigationActionFactory HOME = new NavigationActionFactory("navigationHome");
  public static final NavigationActionFactory UP = new NavigationActionFactory("navigationUp");
}
