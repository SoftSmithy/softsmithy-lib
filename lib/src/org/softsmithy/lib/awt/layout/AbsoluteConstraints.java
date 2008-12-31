/*
 *                 Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is NetBeans. The Initial Developer of the Original
 * Code is Sun Microsystems, Inc. Portions Copyright 1997-2000 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

package org.softsmithy.lib.awt.layout;

import java.awt.Dimension;
import java.awt.Point;

/** 
 * Originated from org.netbeans.lib.awtextra
 *
 * An object that encapsulates position and (optionally) size for
 * Absolute positioning of components.
 *
 * @see AbsoluteLayout
 * @version 1.01, Aug 19, 1998
 */
public class AbsoluteConstraints{
  
  /** The X position of the component */
  private final int x;
  /** The Y position of the component */
  private final  int y;
  /** The width of the component */
  private final  int width;
  /** The height of the component */
  private final  int height;
  
  
  /** Creates a new AbsoluteConstraints for specified position and size.
   * @param pos  The position to be represented by this AbsoluteConstraints
   * @param size The size to be represented by this AbsoluteConstraints or null
   *             if the component's preferred size should be used
   */
  public AbsoluteConstraints(Point pos, Dimension size) {
    this.x = pos.x;
    this.y = pos.y;
    this.width = size.width;
    this.height = size.height;
  }
  
  /** Creates a new AbsoluteConstraints for specified position and size.
   * @param x      The X position to be represented by this AbsoluteConstraints
   * @param y      The Y position to be represented by this AbsoluteConstraints
   * @param width  The width to be represented by this AbsoluteConstraints or -1 if the
   *               component's preferred width should be used
   * @param height The height to be represented by this AbsoluteConstraints or -1 if the
   *               component's preferred height should be used
   */
  public AbsoluteConstraints(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }
  
  /** @return The X position represented by this AbsoluteConstraints */
  public int getX() {
    return x;
  }
  
  /** @return The Y position represented by this AbsoluteConstraints */
  public int getY() {
    return y;
  }
  
  /** @return The width represented by this AbsoluteConstraints or -1 if the
   * component's preferred width should be used
   */
  public int getWidth() {
    return width;
  }
  
  /** @return The height represented by this AbsoluteConstraints or -1 if the
   * component's preferred height should be used
   */
  public int getHeight() {
    return height;
  }
  
  @Override
  public String toString() {
    return super.toString() +" [x="+x+", y="+y+", width="+width+", height="+height+"]";
  }
  
}

