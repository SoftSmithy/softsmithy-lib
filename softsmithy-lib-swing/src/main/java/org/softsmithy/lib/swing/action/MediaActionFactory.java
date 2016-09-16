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
 * MediaActionFactory.java
 *
 * Created on 22. Juni 2004, 16:40
 */
package org.softsmithy.lib.swing.action;

/**
 * http://java.sun.com/developer/techDocs/hi/repository/TBG_Media.html
 *
 * @author puce
 */
public enum MediaActionFactory implements StandardActionFactory {

    FAST_FORWARD("mediaFastForward"),
    MOVIE("mediaMovie"),
    PAUSE("mediaPause"),
    PLAY("mediaPlay"),
    REWIND("mediaRewind"),
    STEP_BACK("mediaStepBack"),
    STEP_FORWARD("mediaStepForward"),
    STOP("mediaStop"),
    VOLUME("mediaVolume");

    private final String name;

    MediaActionFactory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
