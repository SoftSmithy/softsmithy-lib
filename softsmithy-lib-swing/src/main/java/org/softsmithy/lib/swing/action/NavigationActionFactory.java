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

 /*
 * NavigationActionFactory.java
 *
 * Created on 22. Juni 2004, 16:09
 */
package org.softsmithy.lib.swing.action;

/**
 * A factory for the standard navigation actions.
 *
 * <a href="https://www.oracle.com/technetwork/java/tbg-navigation-138651.html">Navigation Toolbar Button Graphics: Java look and feel Graphics Repository</a>
 *
 * @author puce
 */
public class NavigationActionFactory extends StandardActionFactory {

    /**
     * Creates a new instance of NavigationActionFactory.
     *
     * @param name the enum constant name
     */
    private NavigationActionFactory(String name) {
        super(name);
    }

    public static final NavigationActionFactory BACK = new NavigationActionFactory("navigationBack");
    public static final NavigationActionFactory DOWN = new NavigationActionFactory("navigationDown");
    public static final NavigationActionFactory FORWARD = new NavigationActionFactory("navigationForward");
    public static final NavigationActionFactory HOME = new NavigationActionFactory("navigationHome");
    public static final NavigationActionFactory UP = new NavigationActionFactory("navigationUp");
}
