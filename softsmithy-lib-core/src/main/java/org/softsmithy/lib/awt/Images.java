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
 * Images.java
 *
 * Created on 12. Februar 2003, 15:32
 */

package org.softsmithy.lib.awt;

import java.awt.*;
import java.awt.image.*;

/**
 *
 * @author  puce
 */
class Images {
  
  /** Creates a new instance of Images */
  private Images() {
  }
  
//  public static Comparator getImageMaxWidthComparator(ImageObserver observer){
//    return new ImageMaxWidthComparator(observer);
//  }
//  
//  private static class ImageMaxWidthComparator extends ImageComparator{
//  }
  
  public static int maxWidth(Image[] images, ImageObserver observer){
    int maxWidth = 0;
    if (images.length > 0){
      maxWidth = images[0].getWidth(observer);
      if (maxWidth != -1){
        for (int i=1; i<images.length; i++){
          int width = images[i].getWidth(observer);
          if (width == -1){
            maxWidth = width;
            break;
          }
          if (width > maxWidth){
            maxWidth = width;
          }
        }
      }
    }
    return maxWidth;
  }
  
  public static int minWidth(Image[] images, ImageObserver observer){
    int minWidth = 0;
    if (images.length > 0){
      minWidth = images[0].getWidth(observer);
      if (minWidth != -1){
        for (int i=1; i<images.length; i++){
          int width = images[i].getWidth(observer);
          if (width == -1){
            minWidth = width;
            break;
          }
          if (width < minWidth){
            minWidth = width;
          }
        }
      }
    }
    return minWidth;
  }
  
  public static int maxHeight(Image[] images, ImageObserver observer){
    int maxHeight = 0;
    if (images.length > 0){
      maxHeight = images[0].getHeight(observer);
      if (maxHeight != -1){
        for (int i=1; i<images.length; i++){
          int height = images[i].getHeight(observer);
          if (height == -1){
            maxHeight = height;
            break;
          }
          if (height > maxHeight){
            maxHeight = height;
          }
        }
      }
    }
    return maxHeight;
  }
  
  public static int minHeight(Image[] images, ImageObserver observer){
    int minHeight = 0;
    if (images.length > 0){
      minHeight = images[0].getHeight(observer);
      if (minHeight != -1){
        for (int i=1; i<images.length; i++){
          int height = images[i].getHeight(observer);
          if (height == -1){
            minHeight = height;
            break;
          }
          if (height < minHeight){
            minHeight = height;
          }
        }
      }
    }
    return minHeight;
  }
  
  public static int totalWidth(Image[] images, ImageObserver observer){
    int totalWidth = 0;
    for (int i=0; i<images.length; i++){
      int width = images[i].getWidth(observer);
      if (width == -1){
        totalWidth = width;
        break;
      }
      totalWidth += width;
    }
    return totalWidth;
  }
  
  public static int totalHeight(Image[] images, ImageObserver observer){
    int totalHeight = 0;
    for (int i=0; i<images.length; i++){
      int height = images[i].getHeight(observer);
      if (height == -1){
        totalHeight = height;
        break;
      }
      totalHeight += height;
    }
    return totalHeight;
  }
  
}
