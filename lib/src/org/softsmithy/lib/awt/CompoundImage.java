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
 * CompoundImage.java
 *
 * Created on 12. Februar 2003, 15:08
 */

package org.softsmithy.lib.awt;

import java.awt.*;
import java.awt.image.*;

/**
 *
 * @author  puce
 */
class CompoundImage extends ImageWrapper {
  
  /** Creates a new instance of CompoundImage */
  public CompoundImage(Image[][] images, CompoundStrategy cs, ImageObserver observer) {
    super(cs.compoundImages(images, observer));
  }
  
  public static interface CompoundStrategy{
    Image compoundImages(Image[][] images, ImageObserver observer);
  }
  
  public static class LeftRightTopDownStrategy implements CompoundStrategy{
    
    public Image compoundImages(Image[][] images, ImageObserver observer) {
      Image image = null;
      int totalWidths[] = new int[images.length];
      int maxHeights[] = new int[images.length];
      int maxWidth = 0;
      int totalHeight = 0;
      boolean unknown = false;
      for (int i=0; i<images.length; i++){
        totalWidths[i] = Images.totalWidth(images[i], observer);
        if (totalWidths[i] == -1){
          unknown = true;
          break;
        }
        if (totalWidths[i] > maxWidth){
          maxWidth = totalWidths[i];
        }
        maxHeights[i] = Images.maxHeight(images[i], observer);
        if (maxHeights[i] == -1){
          unknown = true;
          break;
        }
        totalHeight += maxHeights[i];
      }
      if (! unknown && maxWidth > 0 && totalHeight > 0){
        BufferedImage bi = new BufferedImage(maxWidth, totalHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D big = bi.createGraphics();
        for (int i=0; i<images.length; i++){
          for (int j=0; j<images[i].length; j++){
          //  big.drawImage(images[i][j]);
          }
        }
        image = bi;
      }
      return image;
    }
    
  }
  
//  public static class TopDownLeftRightStrategy implements CompoundStrategy{
//    
//    public Image compoundImages(Image[][] images, ImageObserver observer) {
//    }
//    
//  }
}
