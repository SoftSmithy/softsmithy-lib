/*
 * MediaActionFactory.java
 *
 * Created on 22. Juni 2004, 16:40
 */

package org.softsmithy.lib.swing.action;

/**
 *
 * @author  puce
 */
public class MediaActionFactory extends StandardActionFactory{
  
  /** Creates a new instance of MediaActionFactory */
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
