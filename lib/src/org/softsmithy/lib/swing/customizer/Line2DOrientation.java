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
 * Line2DOrientation.java
 *
 * Created on 13. November 2002, 15:43
 */

package org.softsmithy.lib.swing.customizer;

import java.awt.geom.*;
import java.awt.geom.Line2D.*;
import java.util.*;
import org.softsmithy.lib.util.*;

/**
 *
 * @author  puce
 */
public abstract class Line2DOrientation extends TypesafeEnum {
  
  private static final String BASE_NAME = "org.softsmithy.lib.swing.customizer.Line2DOrientation";
  
  
  /** Creates a new instance of Line2DOrientation */
  private Line2DOrientation(String name) {
    super(name);
  }
  
  @Override
  public String getResourceBundleBaseName(){
    return BASE_NAME;
  }
  
  public abstract Line2D getLine2D();
  
  public static final Line2DOrientation HORIZONTAL = new Line2DOrientation("horizontal"){
    public Line2D getLine2D(){
      return new Line2D.Double(0, 0, 1, 0);
    }
  };
  public static final Line2DOrientation VERTICAL = new Line2DOrientation("vertical"){
    public  Line2D getLine2D(){
      return new Line2D.Double(0, 0, 0, 1);
    }
  };
  
  private static final Line2DOrientation[] PRIVATE_VALUES = {HORIZONTAL, VERTICAL};
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
  
}
