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
import java.awt.Image;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.softsmithy.lib.swing.icon.XIcon;
import org.softsmithy.lib.swing.icon.XImageIcon;

/**
 *
 * @author florian.brunner
 */
public class VisualField {
    
    
    private XIcon defaultVisualField;
    private XIcon leftVisualField;
    private XIcon rightVisualField;
    private XIcon leftHalfVisualField;
    private XIcon rightHalfVisualField;
    
    /** Creates a new instance of VisualField */
    public VisualField(double fixationRadius, double radiusInc, int nSections, int nOutterCircles) {
        
        double centerCoord = nOutterCircles * radiusInc + fixationRadius;
        Point2D.Double center = new Point2D.Double(centerCoord, centerCoord);
        double radius = fixationRadius;
        Area fixation = new Area(new Ellipse2D.Double(center.x - radius, center.y - radius, 2*radius, 2*radius));
        
        
        List<Area> circles = new ArrayList<Area>();
        for (int i=0; i<nOutterCircles; i++){
            radius += radiusInc;
            Area circle = new Area(new Ellipse2D.Double(center.x - radius, center.y - radius, 2*radius, 2*radius));
            //circle.subtract(fixation);
            for (Area area : circles){
                circle.subtract(area);
            }
            circles.add(circle);
        }
        double angle = ((double) 360)/ nSections;
        Area section = new Area(new Arc2D.Double(circles.get(nOutterCircles-1).getBounds2D(), 90, angle, Arc2D.PIE ));
        List<List<Area>> segments = new ArrayList<List<Area>>();
        
        AffineTransform at = AffineTransform.getRotateInstance(2*Math.PI / nSections, center.x, center.y);
        
        for (int i=0; i<nSections; i++){
            section.transform(at);
            List<Area> sectionSegments = new ArrayList<Area>();
            for (int j=0; j<nOutterCircles; j++){
                Area segment = new Area(section);
                segment.subtract(fixation);
                for (int k=0; k<nOutterCircles; k++){
                    if (j != k){
                        segment.subtract(circles.get(k));
                    }
                }
                sectionSegments.add(segment);
            }
            segments.add(sectionSegments);
        }
        createImages(fixation, segments, (int) Math.ceil(2*radius));
    }
    
    public void paint(Graphics2D g){
        
        /*g.drawImage(defaultVisualField, 0, 0, null);
        g.drawImage(leftVisualField, 0, 300, null);
        g.drawImage(rightVisualField, 300, 300, null);
        g.drawImage(leftHalfVisualField, 0, 600, null);
        g.drawImage(rightHalfVisualField, 300, 600, null);*/
        //g.fill(section);
        
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
        
    }
    
    
    private void createImages(Area fixation, List<List<Area>> segments, int radius) {
        defaultVisualField = new XImageIcon(createImage(fixation, segments, radius, Collections.EMPTY_SET));
        leftVisualField = new XImageIcon(createImage(fixation, segments, radius, new HashSet(Arrays.asList(10, 14))));
        rightVisualField = new XImageIcon(createImage(fixation, segments, radius, new HashSet(Arrays.asList(2,6))));
        leftHalfVisualField = new XImageIcon(createImage(fixation, segments, radius, new HashSet(Arrays.asList(8))));
        rightHalfVisualField = new XImageIcon(createImage(fixation, segments, radius, new HashSet(Arrays.asList(0, 8))));
        
    }
    
    private Image createImage(Area fixation, List<List<Area>> segments, int radius,
            Set<Integer> nonColorSwitchingIndices) {
        BufferedImage bi = new BufferedImage(radius, radius, BufferedImage.TYPE_INT_ARGB);
        Graphics2D big = bi.createGraphics();
        big.setBackground(new Color(0,0,0, 0));
        Color oldColor = big.getColor();

        
        boolean red = true;
        for (int i=0; i<segments.size(); i++){
            if (! nonColorSwitchingIndices.contains(i)){
                red = !red;
            }
            List<Area> list = segments.get(i);
            for (Area segment : list){
                if (red){
                    big.setColor(Color.red);
                } else {
                    big.setColor(Color.lightGray);
                }
                red = !red;
                big.fill(segment);
            }
            
        }
        big.setColor(Color.BLACK);
        big.fill(fixation);
        big.setColor(oldColor);
        return bi;
    }

    public List<XIcon> getLeftImages() {
        return Arrays.asList(defaultVisualField, leftVisualField);
    }
    
    public List<XIcon> getRightImages() {
        return Arrays.asList(defaultVisualField, rightVisualField);
    }
    
    public List<XIcon> getLeftHalfmages() {
        return Arrays.asList(defaultVisualField, leftHalfVisualField);
    }
    
    public List<XIcon> getRightHalfImages() {
        return Arrays.asList(defaultVisualField, rightHalfVisualField);
    }
    
}
