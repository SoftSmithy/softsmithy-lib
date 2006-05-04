/*
 * VisualField.java
 *
 * Created on 4. Mai 2006, 23:13
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.softsmithy.vep;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author florian.brunner
 */
public class VisualField {
    
    /** Creates a new instance of VisualField */
    public VisualField() {
    }
    
    public void paint(Graphics2D g){
        Color oldColor = g.getColor();
        
        
        Point2D.Double center = new Point2D.Double(210, 160);
        double radiusInc = 20;
        double radius = 0;
        List<Area> circles = new ArrayList<Area>();
        for (int i=0; i<5; i++){
            radius += radiusInc;
            Area circle = new Area(new Ellipse2D.Double(center.x - radius, center.y - radius, 2*radius, 2*radius));
            for (Area area : circles){
                circle.subtract(area);
            }
            circles.add(circle);
            if (i == 0){
                g.setColor(Color.black);
            } else if ((i & 1) == 0){
                g.setColor(Color.red);
            } else {
                g.setColor(Color.lightGray);
            }
            g.fill(circle);
        }
        /*Area fixation = new Area(new Ellipse2D.Double(center.x - 10, center.y - 10, 20, 20));
        Line2D.Double line = new Line2D.Double(center.x - 20, center.y, center.x + 20, center.y);
        
        Area circle1 = new Area(new Ellipse2D.Double(center.x - 20, center.y - 20, 40, 40));
        circle1.subtract(fixation);
        Area circle2 = new Area(new Ellipse2D.Double(center.x - 40, center.y - 40, 80, 80));
        circle2.subtract(fixation);
        circle2.subtract(circle1);

        g.setColor(Color.black);
        g.fill(fixation);
        g.draw(line);
        g.setColor(Color.red);
        g.fill(circle1);
        g.setColor(Color.lightGray);
        g.fill(circle2);

        g.setColor(Color.red);
        VisualSegment s1 = new VisualSegment(new Point2D.Double(100, 200), new Point2D.Double(200, 200), new Point2D.Double(250, 320), new Point2D.Double(50, 320));
        //g.fill(s1);*/
        g.setColor(oldColor);
    }
    
}
