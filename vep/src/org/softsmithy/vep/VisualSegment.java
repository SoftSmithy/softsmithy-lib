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
 * VisualSegment.java
 *
 * Created on 4. Mai 2006, 23:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.softsmithy.vep;

import  java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author florian.brunner
 */
public class VisualSegment implements Shape{
    
    private Area area;
    /** Creates a new instance of VisualSegment */
    public VisualSegment(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3, Point2D.Double p4) {
        int[] xPoints = new int[]{(int) Math.round(p1.x), (int) Math.round(p2.x), (int) Math.round(p3.x), (int) Math.round(p4.x)};
        int[] yPoints = new int[]{(int) Math.round(p1.y), (int) Math.round(p2.y), (int) Math.round(p3.y), (int) Math.round(p4.y)};
        Polygon trapezoid = new Polygon(xPoints, yPoints, 4);
        area = new Area(trapezoid);
    }

    public Rectangle getBounds() {
        return area.getBounds();
    }

    public Rectangle2D getBounds2D() {
        return area.getBounds2D();
    }

    public boolean contains(double x, double y) {
        return area.contains(x, y);
    }

    public boolean contains(Point2D p) {
        return area.contains(p);
    }

    public boolean intersects(double x, double y, double w, double h) {
        return area.intersects(x, y, w, h);
    }

    public boolean intersects(Rectangle2D r) {
        return area.intersects(r);
    }

    public boolean contains(double x, double y, double w, double h) {
        return area.contains(x, y, w, h);
    }

    public boolean contains(Rectangle2D r) {
        return area.contains(r);
    }

    public PathIterator getPathIterator(AffineTransform at) {
        return area.getPathIterator(at);
    }

    public PathIterator getPathIterator(AffineTransform at, double flatness) {
        return area.getPathIterator(at, flatness);
    }
    
}
