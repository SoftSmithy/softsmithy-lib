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
 * http://java.sun.com/developer/techDocs/hi/repository/TBG_Navigation.html
 *
 * @author puce
 */
public enum NavigationActionFactory implements StandardActionFactory {

    BACK("navigationBack"),
    DOWN("navigationDown"),
    FORWARD("navigationForward"),
    HOME("navigationHome"),
    UP("navigationUp");

    private final String name;

    NavigationActionFactory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
