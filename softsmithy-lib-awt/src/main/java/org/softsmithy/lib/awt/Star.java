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

package org.softsmithy.lib.awt;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * A star shape.
 *
 * @author puce
 */

public class Star implements Shape {
    
    /**
     * 18 degree (= 10th part of a full rotation - 18 degree)
     */
    private final static double ANGLE_18 = Math.PI / 10;
    // 54 degree (= 5th part of a full rotation - 18 degree)
    /**
     *
     */
    private final static double ANGLE_54 = 3 * Math.PI / 10;
    
    
    /**
     * The cosine of 18 degree.
     */
    private final static double COS_18 = Math.cos(ANGLE_18);
    /**
     * The cosine of 54 degree.
     */
    private final static double COS_54 = Math.cos(ANGLE_54);
    /**
     * The sine of 18 degree.
     */
    private final static double SIN_18 = Math.sin(ANGLE_18);
    /**
     * The sine of 54 degree.
     */
    private final static double SIN_54 = Math.sin(ANGLE_54);
    
    /**
     * The shape data.
     */
    private final Polygon fPolygon = new Polygon();
    
    /**
     * Creates a new Star Shape.
     *
     * @param x       x-coordinate of the top-left corner
     * @param y       y-coordinate of the top-left corner
     * @param width   width of the star
     * @param height  height of the star
     */
    public Star(int x, int y, int width, int height) {
        double bigRadiusX = ((double) width) / 2;
        double smallRadiusX = ((double) width) / 4;
        double bigRadiusY = ((double) height) / 2;
        double smallRadiusY = ((double) height) / 4;
        double centerX = x - 2 + (((double) width) / 2); // -2 seems to be necessary?? (Tested with ShapeIcon and JXIconCustomizer)
        double centerY = y + (((double) height) / 2);
        fPolygon.addPoint((int) Math.round(centerX), (int) Math.round(centerY - bigRadiusY));
        fPolygon.addPoint((int) Math.round(centerX + smallRadiusX * COS_54), (int) Math.round(centerY - smallRadiusY * SIN_54));
        fPolygon.addPoint((int) Math.round(centerX + bigRadiusX * COS_18), (int) Math.round(centerY - bigRadiusY * SIN_18));
        fPolygon.addPoint((int) Math.round(centerX + smallRadiusX * COS_18), (int) Math.round(centerY + smallRadiusY * SIN_18));
        fPolygon.addPoint((int) Math.round(centerX + bigRadiusX * COS_54), (int) Math.round(centerY + bigRadiusY * SIN_54));
        fPolygon.addPoint((int) Math.round(centerX), (int) Math.round(centerY + smallRadiusY));
        fPolygon.addPoint((int) Math.round(centerX - bigRadiusX * COS_54), (int) Math.round(centerY + bigRadiusY * SIN_54));
        fPolygon.addPoint((int) Math.round(centerX - smallRadiusX * COS_18), (int) Math.round(centerY + smallRadiusY * SIN_18));
        fPolygon.addPoint((int) Math.round(centerX - bigRadiusX * COS_18), (int) Math.round(centerY - bigRadiusY * SIN_18));
        fPolygon.addPoint((int) Math.round(centerX - smallRadiusX * COS_54), (int) Math.round(centerY - smallRadiusY * SIN_54));
        
    }
    
    
    /**
     * Gets the bounding box of this Star. The bounding box is the smallest
     * rectangle whose sides are parallel to the x and y axes of the coordinate
     * space, and can completely contain the Star.
     * @return a Rectangle that defines the bounds of this Star.
     * @see #getBounds2D()
     */
    @Override
    public Rectangle getBounds() {
        return fPolygon.getBounds();
    }
    
    /**
     * Returns the high precision bounding box of the Shape.
     *
     * @return   a Rectangle2D that precisely bounds the Shape.
     * @see      #getBounds()
     */
    @Override
    public Rectangle2D getBounds2D() {
        return fPolygon.getBounds2D();
    }
    
    
    /**
     * Determines if the specified coordinates are inside this Star. For the
     * definition of insideness, see the class comments of Shape.
     *
     * @param x  x-coordinate
     * @param y  y-coordinate
     * @return   true if the Star contains the specified coordinates; false
     *      otherwise.
     */
    
    @Override
    public boolean contains(double x, double y) {
        return fPolygon.contains(x, y);
    }
    
    /**
     * Tests if a specified Point2D is inside the boundary of this Star.
     *
     * @param p  a specified Point2D
     * @return   true if this Star contains the specified Point2D; false
     *      otherwise.
     * @see      #contains(double x, double y)
     */
    
    @Override
    public boolean contains(Point2D p) {
        return fPolygon.contains(p);
    }
    
    
    /**
     * Tests if the interior of this Star intersects the interior of a
     * specified set of rectangular coordinates.
     *
     * @param x  x-coordinate of the top-left corner of the specified rectangular area
     * @param y  y-coordinate of the top-left corner of the specified rectangular area
     * @param w  the width of the specified rectangular area
     * @param h  the height of the specified rectangular area
     * @return   true if the interior of this Star and the interior of the
     *      specified set of rectangular coordinates intersect each other; false
     *      otherwise.
     * @see      java.awt.geom.Area
     */
    
    @Override
    public boolean intersects(double x, double y, double w, double h) {
        return fPolygon.intersects(x, y, w, h);
    }
    
    
    /**
     * Tests if the interior of this Star intersects the interior of a
     * specified Rectangle2D.
     *
     * @param r  a specified Rectangle2D
     * @return   true if this Star and the interior of the specified
     *      Rectangle2D intersect each other; false otherwise.
     * @see      #intersects(double, double, double, double)
     */
    @Override
    public boolean intersects(Rectangle2D r) {
        return fPolygon.intersects(r);
    }
    
    
    /**
     * Tests if the interior of this Star entirely contains the specified set
     * of rectangular coordinates.
     *
     * @param x              x-coordinate of the top-left corner of the specified rectangular area
     * @param y              y-coordinate of the top-left corner of the specified rectangular area
     * @param w              the width of the set of rectangular coordinates
     * @param h              the height of the set of rectangular coordinates
     * @return               true if this Star entirely contains the specified
     *      set of rectangular coordinates; false otherwise.
     * @see                  java.awt.geom.Area
     * @see                  #intersects(double,   double, double, double)
     */
    @Override
    public boolean contains(double x, double y, double w, double h) {
        return fPolygon.contains(x, y, w, h);
    }
    
    
    /**
     * Tests if the interior of this Star entirely contains the specified
     * Rectangle2D.
     *
     * @param r  the specified Rectangle2D
     * @return   true if this Star entirely contains the specified Rectangle2D;
     *      false otherwise.
     * @see      #contains(double, double, double, double)
     */
    @Override
    public boolean contains(Rectangle2D r) {
        return fPolygon.contains(r);
    }
    
    
    /**
     * Returns an iterator object that iterates along the boundary of this Star
     * and provides access to the geometry of the outline of this Star. An
     * optional AffineTransform can be specified so that the coordinates returned
     * in the iteration are transformed accordingly.
     *
     * @param at  an optional AffineTransform to be applied to the coordinates as
     *      they are returned in the iteration, or null if untransformed
     *      coordinates are desired
     * @return    a PathIterator object that provides access to the geometry of
     *      this Star.
     */
    @Override
    public PathIterator getPathIterator(AffineTransform at) {
        return fPolygon.getPathIterator(at);
    }
    
    /**
     * Returns an iterator object that iterates along the boundary of the Shape
     * and provides access to the geometry of the outline of the Shape. Only
     * SEG_MOVETO, SEG_LINETO, and SEG_CLOSE point types are returned by the
     * iterator. Since Stars are already flat, the flatness parameter is
     * ignored. An optional AffineTransform can be specified in which case the
     * coordinates returned in the iteration are transformed accordingly.
     *
     * @param at        an optional AffineTransform to be applied to the
     *      coordinates as they are returned in the iteration, or null if
     *      untransformed coordinates are desired
     * @param flatness  the maximum amount that the control points for a given
     *      curve can vary from colinear before a subdivided curve is replaced by
     *      a straight line connecting the endpoints. Since Stars are already
     *      flat the flatness parameter is ignored.
     * @return          a PathIterator object that provides access to the Shape
     *      object's geometry.
     */
    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return fPolygon.getPathIterator(at, flatness);
    }
}