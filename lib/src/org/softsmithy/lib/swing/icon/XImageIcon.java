/*
 * XImageIcon.java
 *
 * Created on 24. M�rz 2004, 00:43
 */

package org.softsmithy.lib.swing.icon;

import java.awt.*;
import java.net.*;
import javax.swing.*;
import org.softsmithy.lib.awt.image.*;

/**
 *
 * @author  puce
 */
public class XImageIcon extends ImageIcon implements XIcon {
  
  /** Holds value of property scalingAlgorithm. */
  private ScalingAlgorithm scalingAlgorithm = ScalingAlgorithm.DEFAULT;
  
  /** Creates a new instance of XImageIcon */
  public XImageIcon() {
  }
  
  
  public XImageIcon(byte[] imageData){
    super(imageData);
  }
  
  public XImageIcon(byte[] imageData, String description){
    super(imageData, description);
  }
  
  public XImageIcon(Image image){
    super(image);
  }
  
  public XImageIcon(Image image, String description){
    super(image, description);
  }
  
  public XImageIcon(String filename){
    super(filename);
  }
  
  public XImageIcon(String filename, String description){
    super(filename, description);
  }
  
  public XImageIcon(URL location){
    super(location);
  }
  
  public XImageIcon(URL location, String description){
    super(location, description);
  }
  
  public XIcon getScaledInstance(int newWidth, int newHeight) {
    return new XImageIcon(getImage().getScaledInstance(newWidth, newHeight, getScalingAlgorithm().getImageConstant()));
  }
  
  /** Getter for property scalingAlgorithm.
   * @return Value of property scalingAlgorithm.
   *
   */
  public ScalingAlgorithm getScalingAlgorithm() {
    return this.scalingAlgorithm;
  }
  
  /** Setter for property scalingAlgorithm.
   * @param scalingAlgorithm New value of property scalingAlgorithm.
   *
   */
  public void setScalingAlgorithm(ScalingAlgorithm scalingAlgorithm) {
    this.scalingAlgorithm = scalingAlgorithm;
  }
  
}
