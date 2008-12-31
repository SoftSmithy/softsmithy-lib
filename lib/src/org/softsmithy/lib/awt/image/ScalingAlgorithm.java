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

package org.softsmithy.lib.awt.image;

import java.awt.Image;
import java.util.*;
import org.softsmithy.lib.util.*;



public abstract class ScalingAlgorithm extends TypesafeEnum{ // better ImageScalingAlgorithm?
  
  private static final String BASE_NAME = "org.softsmithy.lib.awt.image.ScalingAlgorithm";
  
  private ScalingAlgorithm(String s){
    super(s);
  }
  
  
  @Override
  public String getResourceBundleBaseName(){
    return BASE_NAME;
  }
  
  public abstract int getImageConstant();
  
  
  public static final ScalingAlgorithm AREA_AVERAGING = new ScalingAlgorithm("areaAveraging"){
    public int getImageConstant(){
      return Image.SCALE_AREA_AVERAGING;
    }
  };
  
  
  public static final ScalingAlgorithm DEFAULT = new ScalingAlgorithm("default"){
    public int getImageConstant(){
      return Image.SCALE_DEFAULT;
    }
  };
  
  public static final ScalingAlgorithm FAST = new ScalingAlgorithm("fast"){
    public int getImageConstant(){
      return Image.SCALE_FAST;
    }
  };
  
  public static final ScalingAlgorithm REPLICATE = new ScalingAlgorithm("replicate"){
    public int getImageConstant(){
      return Image.SCALE_REPLICATE;
    }
  };
  
  public static final ScalingAlgorithm SMOOTH = new ScalingAlgorithm("smooth"){
    public int getImageConstant(){
      return Image.SCALE_SMOOTH;
    }
  };
  
  
  private static final ScalingAlgorithm[] PRIVATE_VALUES = {AREA_AVERAGING, DEFAULT, FAST, REPLICATE, SMOOTH};
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
  private static final Map<Integer, ScalingAlgorithm> algorithms = new HashMap<Integer, ScalingAlgorithm>();
  static{
    for (int i=0; i<PRIVATE_VALUES.length; i++){
      algorithms.put(new Integer(PRIVATE_VALUES[i].getImageConstant()), PRIVATE_VALUES[i]);
    }
  }
  
  public static ScalingAlgorithm getScalingAlgorithm(int imageConstant){
    return (ScalingAlgorithm) algorithms.get(new Integer(imageConstant));
  }
  
}