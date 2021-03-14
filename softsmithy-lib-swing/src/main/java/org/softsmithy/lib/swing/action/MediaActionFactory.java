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
 * A factory for the standard media actions.
 *
 * <a href="https://www.oracle.com/technetwork/java/tbg-media-137102.html">Media Toolbar Button Graphics: Java look and feel Graphics Repository</a>
 *
 * @author puce
 */
public class MediaActionFactory extends StandardActionFactory {

    /**
     * Creates a new instance of MediaActionFactory.
     * 
     * @param name the enum constant name
     */
    private MediaActionFactory(String name) {
        super(name);
    }
    public static final MediaActionFactory FAST_FORWARD = new MediaActionFactory("mediaFastForward");
    public static final MediaActionFactory MOVIE = new MediaActionFactory("mediaMovie");
    public static final MediaActionFactory PAUSE = new MediaActionFactory("mediaPause");
    public static final MediaActionFactory PLAY = new MediaActionFactory("mediaPlay");
    public static final MediaActionFactory REWIND = new MediaActionFactory("mediaRewind");
    public static final MediaActionFactory STEP_BACK = new MediaActionFactory("mediaStepBack");
    public static final MediaActionFactory STEP_FORWARD = new MediaActionFactory("mediaStepForward");
    public static final MediaActionFactory STOP = new MediaActionFactory("mediaStop");
    public static final MediaActionFactory VOLUME = new MediaActionFactory("mediaVolume");
}
